grammar little;
program             :   PROGRAM id BEGIN pgm_body END EOF;
id                  :   IDENTIFIER;
pgm_body            :   decl func_declarations;
decl                :   string_decl decl | var_decl decl | empty;

string_decl         :   STRING id ASSIGN str END_OP;
str                 :   STRINGLITERAL;
var_decl            :   var_type id_list END_OP;
var_type            :   INT | FLOAT;
any_type            :   var_type | VOID;
id_list             :   id id_tail;
id_tail             :   LIS_OP id id_tail | empty;

param_decl_list     :   param_decl param_decl_tail | empty;
param_decl          :   var_type id;
param_decl_tail     :   LIS_OP param_decl param_decl_tail | empty;
func_declarations   :   func_decl func_declarations | empty;
func_decl           :   FUNCTION any_type id LPAREN param_decl_list RPAREN BEGIN func_body END;
func_body           :   decl stmt_list;

stmt_list           :   stmt stmt_list | empty;
stmt                :   base_stmt | if_stmt | while_stmt;
base_stmt           :   assign_stmt | read_stmt | write_stmt | return_stmt;

assign_stmt         :   assign_expr END_OP;
assign_expr         :   id ASSIGN expr;
read_stmt           :   READ LPAREN id_list RPAREN END_OP;
write_stmt          :   WRITE LPAREN id_list RPAREN END_OP;
return_stmt         :   RETURN expr END_OP;

expr                :   expr_prefix factor;
expr_prefix         :   expr_prefix factor addop | empty;
factor              :   factor_prefix postfix_expr;
factor_prefix       :   factor_prefix postfix_expr mulop | empty;
postfix_expr        :   primary | call_expr;
call_expr           :   id LPAREN expr_list RPAREN;
expr_list           :   expr expr_list_tail | empty;
expr_list_tail      :   LIS_OP expr expr_list_tail | empty;
primary             :   LPAREN expr RPAREN | id | INTLITERAL | FLOATLITERAL;
addop               :   ADD_OP | SUB_OP;
mulop               :   MUL_OP | DIV_OP;

if_stmt             :   IF LPAREN cond RPAREN decl stmt_list else_part ENDIF;
else_part           :   ELSE decl stmt_list | empty;
cond                :   expr compop expr;
compop              :   LES_OP | GRE_OP | EQU_OP | NEQ_OP | LEQ_OP | GEQ_OP;
while_stmt          :   WHILE LPAREN cond RPAREN decl stmt_list ENDWHILE;

empty               :   ;

PROGRAM             :   'PROGRAM';
BEGIN               :   'BEGIN';
END                 :   'END';
FUNCTION            :   'FUNCTION';
READ                :   'READ';
WRITE               :   'WRITE';
IF                  :   'IF';
ELSE                :   'ELSE';
ENDIF               :   'ENDIF';
WHILE               :   'WHILE';
ENDWHILE            :   'ENDWHILE';
CONTINUE            :   'CONTINUE';
BREAK               :   'BREAK';
RETURN              :   'RETURN';
INT                 :   'INT';
VOID                :   'VOID';
STRING              :   'STRING';
FLOAT               :   'FLOAT';

IDENTIFIER          :   [A-z][A-z0-9]*;
INTLITERAL          :   [0-9]+;
FLOATLITERAL        :   [0-9]*'.'[0-9]+;
STRINGLITERAL       :   '"'(~["]|[\\])*'"';
COMMENT             :   '--'~('\n' | '\r')* -> channel(HIDDEN);

ADD_OP              :   '+';
SUB_OP              :   '-';
MUL_OP              :   '*';
DIV_OP              :   '/';
LES_OP              :   '<';
GRE_OP              :   '>';
EQU_OP              :   '=';
NEQ_OP              :   '!=';
LEQ_OP              :   '<=';
GEQ_OP              :   '>=';
LIS_OP              :   ',';
END_OP              :   ';';
LPAREN              :   '(';
RPAREN              :   ')';
ASSIGN              :   ':=';

WHITESPACE          :   (' ' | '\n' | '\t' | '\r')+ -> channel(HIDDEN);
ERROR               :   .;