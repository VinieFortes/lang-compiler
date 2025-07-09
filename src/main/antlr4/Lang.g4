// Vinicius da Silva Fortes
// Matricula 201935029
grammar Lang;

prog: (def | cmd)* EOF;
def: data | fun;
data: ABSTRACT? DATA TYID '{' (decl | fun)* '}';
decl: ID '::' type ';';
fun: ID '(' params? ')' (':' type (',' type)*)? cmd;
params: ID '::' type (',' ID '::' type)*;
type: btype ('[' ']')*;
btype: INT_TYPE | CHAR_TYPE | BOOL_TYPE | FLOAT_TYPE | TYID;

cmd
    : block                                             #CmdBlock
    | IF '(' exp ')' cmd (ELSE cmd)?                    #CmdIf
    | ITERATE '(' itcond ')' cmd                        #CmdIterate
    | READ lvalue ';'                                   #CmdRead
    | PRINT exp ';'                                     #CmdPrint
    | RETURN exp (',' exp)* ';'                         #CmdReturn
    | lvalue '=' exp ';'                                #CmdAssign
    | ID '(' exps? ')' ('<' lvalue (',' lvalue)* '>')? ';' #CmdFunCall
    ;

itcond: exp | ID ':' exp;
block: '{' cmd* '}';

lvalue
    : ID                  #LValueId
    | lvalue '[' exp ']'  #LValueArray
    | lvalue '.' ID       #LValueField
    ;
exps: exp (',' exp)*;

exp
    : exp AND exp              #ExpOp
    | equalityExp              #ExpEquality
    ;

equalityExp
    : equalityExp (EQ | NEQ) equalityExp  #EqualityExpOp
    | relationalExp                       #EqualityExpRelational
    ;

relationalExp
    : additiveExp LT additiveExp  #RelationalExpOp
    | additiveExp                 #RelationalExpAdditive
    ;

additiveExp
    : additiveExp (ADD | SUB) additiveExp  #AdditiveExpOp
    | multiplicativeExp                    #AdditiveExpMultiplicative
    ;

multiplicativeExp
    : multiplicativeExp (MUL | DIV | MOD) multiplicativeExp  #MultiplicativeExpOp
    | unaryExp                                               #MultiplicativeExpUnary
    ;

unaryExp
    : (SUB | NOT) unaryExp     #UnaryExpOp
    | primaryExp               #UnaryExpPrimary
    ;

primaryExp
    : lvalue                   #PrimaryExpLValue
    | ID '(' exps? ')' '[' exp ']' #PrimaryExpFunCallReturn
    | NEW type ('[' exp ']')?  #PrimaryExpNew
    | literal                  #PrimaryExpLiteral
    | '(' exp ')'              #PrimaryExpParen
    ;
literal: INT | FLOAT | CHAR | TRUE | FALSE | NULL;

ABSTRACT: 'abstract';
DATA: 'data';
IF: 'if';
ELSE: 'else';
ITERATE: 'iterate';
READ: 'read';
PRINT: 'print';
RETURN: 'return';
NEW: 'new';
TRUE: 'true';
FALSE: 'false';
NULL: 'null';
INT_TYPE: 'Int';
CHAR_TYPE: 'Char';
BOOL_TYPE: 'Bool';
FLOAT_TYPE: 'Float';

TYID: [A-Z] [a-zA-Z0-9_]*;
ID: [a-z] [a-zA-Z0-9_]*;

INT: [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+ | '.' [0-9]+;
CHAR: '\'' ( ~['\\] | ESCAPE_SEQ ) '\'';

LPAREN: '('; RPAREN: ')';
LBRACE: '{'; RBRACE: '}';
LBRACK: '['; RBRACK: ']';
SEMI: ';'; COMMA: ','; DOT: '.';
ASSIGN: '='; COLON: '::';
LT: '<'; EQ: '=='; NEQ: '!=';
ADD: '+'; SUB: '-'; MUL: '*'; DIV: '/'; MOD: '%';
AND: '&&'; NOT: '!';

fragment ESCAPE_SEQ: '\\' ( [ntbr\\'] | [0-9][0-9][0-9] );

WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '--' .*? '\r'? '\n' -> skip;
BLOCK_COMMENT: '{-' .*? '-}' -> skip;