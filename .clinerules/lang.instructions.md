Especificação da Linguagem Lang
Autores: Elton Cardoso¹, Leonardo Reis¹, Rodrigo Ribeiro²¹ Universidade Federal de Ouro Preto² Universidade Federal de Juiz de Fora
Introdução
Este documento apresenta a especificação da linguagem de programação lang, desenvolvida para uso educacional na disciplina DCC018 - Teoria de Compiladores. A linguagem lang não é um subconjunto de nenhuma outra linguagem, mas incorpora construções inspiradas em várias linguagens conhecidas. A descrição da linguagem é dividida em seções que abordam seus aspectos sintáticos, semânticos e de tipagem, incluindo uma gramática formal em notação EBNF.
1. Estrutura Sintática
A linguagem lang é definida por uma gramática livre de contexto, apresentada em notação EBNF. Nesta notação:

Metassímbolos são delimitados por aspas simples quando usados como tokens.
Palavras reservadas são escritas em letras maiúsculas.
A notação [E] indica que E é opcional, ou seja, pode ser derivado ou omitido.
A notação {E} indica zero ou mais repetições de E.

Um programa em lang consiste em um conjunto de definições de tipos de dados e funções. A estrutura sintática é dividida em:

Tipos de dados e declarações
Funções
Comandos
Expressões

1.1. Tipos de Dados e Declarações
Programas em lang podem definir tipos de dados estruturados (registros) usando a palavra reservada data. Após data, segue o nome do tipo (iniciado por letra maiúscula) e uma lista de declarações de variáveis delimitadas por chaves. Por exemplo, um tipo para representar uma fração pode ser definido como:
data Racional {
    numerador: Int;
    denominador: Int;
}

1.2. Funções
Funções em lang podem retornar múltiplos valores. A sintaxe de uma função inclui a palavra reservada fun, o nome da função, uma lista de parâmetros, o tipo de retorno, e um bloco de comandos. Um exemplo de função que soma duas frações é:
fun add(r1: Racional, r2: Racional): Racional {
    r: new Racional;
    r.denominador = r1.denominador * r2.denominador;
    r.numerador = r1.numerador * r2.denominador + r2.numerador * r1.denominador;
    return r;
}

1.3. Comandos
A linguagem lang suporta oito comandos básicos, classificados em atribuição, seleção, entrada/saída, retorno, iteração, e chamadas de funções/procedimentos.
1.3.1. Atribuição
O comando de atribuição segue a sintaxe de linguagens como C e Java, onde uma variável à esquerda recebe o valor da expressão à direita. Exemplo:
x = 5;

1.3.2. Seleção
Dois comandos de seleção estão disponíveis: if-then e if-then-else. Exemplo:
if (x > 0) {
    print(x);
} else {
    print(-x);
}

1.3.3. Entrada/Saída
Os comandos read e print lidam com entrada e saída, respectivamente. read armazena o valor lido em uma variável, enquanto print exibe o valor de uma expressão. Exemplos:
read x;
print x;

1.3.4. Retorno
O comando return especifica os valores retornados por uma função, separados por vírgulas. Exemplo:
return x, y;

1.3.5. Iteração
O comando iterate suporta iteração com duas formas:

iterate (expr) cmd: Executa o comando cmd um número de vezes determinado pela avaliação da expressão expr, que deve resultar em um inteiro.

iterate (ID: expr) cmd: Similar, mas inicializa a variável ID com o contador da iteração (ou elementos de um vetor, se expr for um vetor).


Exemplo:
iterate (x: 10) {
    print(x);
}

1.3.6. Chamadas de Funções e Procedimentos
Chamadas de procedimentos consistem no nome do procedimento seguido por uma lista de expressões entre parênteses. Exemplo:
main();

Chamadas de funções especificam variáveis para armazenar os valores retornados. Exemplo:
divmod(5, 2) q, r;

Um bloco de comandos é delimitado por chaves {} e pode conter zero ou mais comandos.
1.4. Expressões
Expressões em lang são abstrações sobre valores e incluem:

Operadores aritméticos (+, -, *, /, %)
Operadores lógicos (&&, ||, !)
Operadores de comparação (==, !=, <, >)
Valores literais (inteiros, caracteres, booleanos, floats, registros, vetores)
Chamadas de funções

Parênteses podem ser usados para definir a precedência das operações. O conjunto de operadores lógicos é reduzido, e operações como "ou exclusivo" não são suportadas diretamente. Exemplo de expressão condicional:
if (!(p) && !(q)) {
    // bloco
}

Chamadas de funções são expressões, e um índice pode ser usado para selecionar um valor específico do retorno. Exemplo:
divmod(5, 2)[0] // retorna o quociente
divmod(5, 2)[1] // retorna o resto

1.5. Escopo
Um nome é visível desde sua definição até o final do bloco onde foi definido, incluindo blocos internos. Não é permitido redefinir um nome em blocos internos. Exemplo de código inválido:
read num;
x = false;
if (true) {
    x = 0; // válido
    x = true; // inválido: redefinição de x
}

3.1.1. Sintaxe Abstrata e Contextos
A sintaxe abstrata de expressões é definida pela seguinte gramática:
e ::= n | c | f | e op e | op e | ID | e[e] | e.ID | new T | e(e, ..., e)[n]

Onde:

n: constante inteira
c: constante de caractere
f: constante de ponto flutuante
op: operador binário ou unário
ID: identificador
T: tipo

3.1.2. Tipos e Operadores
Os operadores suportados e seus tipos são:



Operador
Tipo



+, -, *, /
a, a → a, onde a ∈ {Int, Float}


%
Int, Int → Int


==, !=, <, >
a, a → Bool, onde a ∈ {Int, Float, Char}


&&, `



!
Bool → Bool


print
a → Void, onde a ∈ {Int, Char, Bool, Float}


3.1.3. Verificação de Tipos
A verificação de tipos utiliza contextos Θ (funções), Δ (tipos definidos), e Γ (variáveis). As regras para chamadas de funções são:

Obter o tipo da função: Θ(f) = [τ₁: τ₁, ..., τₘ: τₘ] → [τ₀, ..., τₚ]
Verificar que cada argumento eᵢ tem tipo τᵢ correspondente.
Verificar que os índices de retorno estão dentro do limite p.

Para alocação dinâmica de arranjos:
Θ; Δ; Γ ⊢ e : Int    τ ∉ {Int, Float, Char, Bool} ∪ dom(Δ)
------------------------------------------------------
Θ; Δ; Γ ⊢ new τ[e] : τ

Para alocação de tipos definidos:
τ ∈ dom(Δ)
----------------
Θ; Δ; Γ ⊢ new τ : τ

3.1.4. Semântica Estática de Comandos
A semântica estática de comandos é definida por julgamentos que verificam a validade dos comandos em relação aos contextos de tipos. Para declarações de variáveis:

Verificar que a expressão de inicialização tem o mesmo tipo da variável.
Atualizar o contexto com a nova variável.

Para comandos condicionais:
Θ; Δ; Γ ⊢ e : Bool    Θ; Δ; Γ ⊢ c₁    Θ; Δ; Γ ⊢ c₂
---------------------------------------------------
Θ; Δ; Γ ⊢ if (e) { c₁ } else { c₂ }

Para comandos read e print, a verificação é direta, garantindo que o tipo da variável ou expressão é compatível.
3.2.2. Semântica Dinâmica
A semântica dinâmica descreve a execução dos comandos e expressões. Para comandos:

Atribuição: Avalia a expressão e atualiza o ambiente com o valor resultante.
Condicional: Avalia a expressão condicional e executa o bloco correspondente (then ou else).
Iteração: Avalia a expressão para obter um inteiro n ou um arranjo de tamanho n, e executa o comando n vezes. Para iterate (ID: expr) cmd, inicializa ID com valores do arranjo ou contador.

Para funções:

Avaliar os argumentos e atualizar o ambiente.
Executar o corpo da função, retornando um arranjo de valores.
Selecionar o valor de retorno pelo índice especificado.

Exemplo de execução de iterate:
iterate (x: 10) {
    print(x);
}

Equivale a 10 iterações, com x variando de 10 a 1.
Conclusão
A linguagem lang é projetada para ser simples, mas abrangente o suficiente para ensinar conceitos de compiladores, incluindo sintaxe, semântica estática e dinâmica, e gerenciamento de tipos. Sua estrutura suporta tipos estruturados, funções com múltiplos retornos, e comandos básicos de controle de fluxo, mantendo uma semântica bem definida para análise e implementação.