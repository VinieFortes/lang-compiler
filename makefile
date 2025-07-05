# --- Salve na raiz do projeto: lang-compiler/makefile ---

JAVAC := javac -Xlint:unchecked
ANTLR_JAR := /home/vinicius/IdeaProjects/lang-compiler/antlr-4.13.2-complete.jar
ANTLR_TOOL := java -jar $(ANTLR_JAR)

OUT_DIR := out
SRC_ROOT := src/main/java
ANTLR_GRAMMAR_DIR := src/main/antlr4

PACKAGE_NAME := langcompiler
PACKAGE_PATH := $(subst .,/,$(PACKAGE_NAME))

CP := .:$(ANTLR_JAR):$(OUT_DIR)

JAVA_FILES := $(shell find $(SRC_ROOT) -name '*.java')

all: compile

antlr:
	@echo "Gerando o parser ANTLR..."
	@mkdir -p $(SRC_ROOT)/$(PACKAGE_PATH)
	@$(ANTLR_TOOL) -visitor -o $(SRC_ROOT)/$(PACKAGE_PATH) -package $(PACKAGE_NAME) $(ANTLR_GRAMMAR_DIR)/Lang.g4

compile: antlr
	@echo "Compilando o projeto Java..."
	@mkdir -p $(OUT_DIR)
	@$(JAVAC) -cp $(CP) -d $(OUT_DIR) $(JAVA_FILES)

run: compile
	@java -cp $(CP) $(PACKAGE_NAME).Main $(ARGS)

run-only:
	@java -cp $(CP) $(PACKAGE_NAME).Main $(ARGS)

clean:
	@echo "Limpando o projeto..."
	@rm -rf $(OUT_DIR)
	@rm -f $(SRC_ROOT)/$(PACKAGE_PATH)/Lang*.java $(SRC_ROOT)/$(PACKAGE_PATH)/Lang*.tokens