#!/bin/bash

# Removido 'set -e' para garantir que todos os testes rodem

# --- Configuração ---
TEST_DIR="testes"
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'
YELLOW='\033[0;33m'
PASSED_COUNT=0
FAILED_COUNT=0

# --- Verificações e Compilação ---
echo "========================================"
echo "INICIANDO PROCESSO DE TESTE RECURSIVO"
echo "========================================"
if [ ! -d "$TEST_DIR" ]; then
    echo -e "${RED}ERRO: A pasta de testes '$TEST_DIR' não foi encontrada!${NC}"
    exit 1
fi
echo "-> Pasta de testes encontrada."
echo -e "\n[PASSO 1/2] Limpando o projeto..."
make clean
echo -e "\n[PASSO 2/2] Compilando o projeto..."
make || { echo -e "${RED}Falha na compilação. Abortando testes.${NC}"; exit 1; }
echo "Compilação inicial concluída."
echo -e "\n--- Iniciando execução dos testes ---"

# --- Função para Testes Sintáticos (-syn) ---
run_syn_tests() {
    local base_dir=$1
    local expected_output=$2
    if [ ! -d "$base_dir" ]; then return; fi
    echo -e "\n--- Testando Sintaxe em '$base_dir' (Esperado: $expected_output) ---"
    for file in $(find "$base_dir" -type f \( -name "*.lan" -o -name "*.lang" \)); do
        # MODIFICADO para usar 'run-only'
        actual_output=$(make run-only ARGS="-syn \"$file\"" 2>/dev/null)

        if [ "$actual_output" == "$expected_output" ]; then
            echo -e "[ ${GREEN}PASS${NC} ] ${file#$TEST_DIR/}"
            ((PASSED_COUNT++))
        else
            echo -e "[ ${RED}FAIL${NC} ] ${file#$TEST_DIR/} - Esperado: '$expected_output', Recebido: '$actual_output'"
            ((FAILED_COUNT++))
        fi
    done
}

# --- Função para Testes do Interpretador/Semântica (-i) ---
run_irt_tests() {
    local base_dir=$1
    if [ ! -d "$base_dir" ]; then return; fi
    echo -e "\n--- Testando Interpretador/Semântica em '$base_dir' ---"
    for file in $(find "$base_dir" -type f -name "*.lan"); do
        local test_path_and_name=$(basename "$file" .lan)
        local test_dir=$(dirname "$file")
        local expected_output_file="$test_dir/$test_path_and_name.inst"

        echo -e "---------------------------------"
        echo -e "Rodando teste: ${YELLOW}${file#$TEST_DIR/}${NC}"

        if [ -f "$expected_output_file" ]; then
            local all_passed=true
            local case_num=1
            
            # Processa cada caso de teste no arquivo .inst
            local in_input=false
            local in_output=false
            local current_input=""
            local current_output=""
            
            while IFS= read -r line; do
                if [[ "$line" == "---in----" ]]; then
                    # Se já tínhamos um caso anterior, testa ele
                    if [[ -n "$current_input" || -n "$current_output" ]]; then
                        echo "  Caso de teste $case_num:"
                        
                        echo "$current_input" > "temp_input_$case_num.txt"
                        echo "$current_output" > "temp_expected_$case_num.txt"
                        
                        make run-only ARGS="-i \"$file\"" < "temp_input_$case_num.txt" > "temp_actual_$case_num.txt" 2>/dev/null
                        
                        if diff -wB "temp_actual_$case_num.txt" "temp_expected_$case_num.txt" > /dev/null; then
                            echo -e "    [ ${GREEN}PASS${NC} ]"
                        else
                            echo -e "    [ ${RED}FAIL${NC} ]"
                            echo "    Entrada:"
                            cat "temp_input_$case_num.txt" | sed 's/^/      /'
                            echo "    Esperado:"
                            cat "temp_expected_$case_num.txt" | sed 's/^/      /'
                            echo "    Obtido:"
                            cat "temp_actual_$case_num.txt" | sed 's/^/      /'
                            all_passed=false
                        fi
                        rm -f "temp_input_$case_num.txt" "temp_expected_$case_num.txt" "temp_actual_$case_num.txt"
                        ((case_num++))
                    fi
                    in_input=true
                    in_output=false
                    current_input=""
                    current_output=""
                elif [[ "$line" == "---out---" ]]; then
                    in_input=false
                    in_output=true
                elif [[ "$in_input" == true ]]; then
                    if [[ -n "$current_input" ]]; then
                        current_input="$current_input"$'\n'"$line"
                    else
                        current_input="$line"
                    fi
                elif [[ "$in_output" == true ]]; then
                    if [[ -n "$current_output" ]]; then
                        current_output="$current_output"$'\n'"$line"
                    else
                        current_output="$line"
                    fi
                fi
            done < "$expected_output_file"
            
            # Processa o último caso
            if [[ -n "$current_input" || -n "$current_output" ]]; then
                echo "  Caso de teste $case_num:"
                
                echo "$current_input" > "temp_input_$case_num.txt"
                echo "$current_output" > "temp_expected_$case_num.txt"
                
                make run-only ARGS="-i \"$file\"" < "temp_input_$case_num.txt" > "temp_actual_$case_num.txt" 2>/dev/null
                
                if diff -wB "temp_actual_$case_num.txt" "temp_expected_$case_num.txt" > /dev/null; then
                    echo -e "    [ ${GREEN}PASS${NC} ]"
                else
                    echo -e "    [ ${RED}FAIL${NC} ]"
                    echo "    Entrada:"
                    cat "temp_input_$case_num.txt" | sed 's/^/      /'
                    echo "    Esperado:"
                    cat "temp_expected_$case_num.txt" | sed 's/^/      /'
                    echo "    Obtido:"
                    cat "temp_actual_$case_num.txt" | sed 's/^/      /'
                    all_passed=false
                fi
                rm -f "temp_input_$case_num.txt" "temp_expected_$case_num.txt" "temp_actual_$case_num.txt"
            fi
            
            if [ "$all_passed" = true ]; then
                echo -e "[ ${GREEN}PASS${NC} ] Todos os casos passaram."
                ((PASSED_COUNT++))
            else
                echo -e "[ ${RED}FAIL${NC} ] Alguns casos falharam."
                ((FAILED_COUNT++))
            fi
        else
             echo -e "${YELLOW}Aviso: Arquivo .inst não encontrado para '${file#$TEST_DIR/}'. Teste ignorado.${NC}"
        fi
    done
}

# --- Execução dos Testes ---
run_syn_tests "$TEST_DIR/sintaxe/certo" "accept"
run_syn_tests "$TEST_DIR/sintaxe/errado" "reject"
run_irt_tests "$TEST_DIR/semantica/certo"

# --- Resumo Final ---
echo -e "\n========================================"
echo -e "Resumo dos Testes:"
echo -e "  ${GREEN}Passaram: $PASSED_COUNT${NC}"
echo -e "  ${RED}Falharam: $FAILED_COUNT${NC}"
echo "========================================"

if [ $FAILED_COUNT -ne 0 ]; then
    exit 1
fi
