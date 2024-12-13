%{

#include <stdio.h>
#include <stdlib.h>
extern int yylex(void);
extern int yyparse(void);
extern void yyerror(const char *s);
extern FILE* yyin;

%}

%token Program
%token Int 
%token Char
%token Struct 
%token String 
%token Identifier 
%token Const 
%token Beginp 
%token Endp 
%token Read 
%token Write
%token If 
%token Then 
%token Else 
%token While 
%token Do 
%token Done
%token Or 
%token And 
%token Not 
%token True 
%token False

%%

program: 
		Beginp decllist stmtlist Endp
idc: 
		Identifier
		| Const
decl:
		data_type Identifier
decllist:
		decl 
		| decl decllist
data_type: 
		Int
		| Char
		| Struct Identifier '{' decllist '}'
arithexpr:
		idc '+' idc
		| idc '-' idc
		| idc '*' idc
		| idc '/' idc
		| idc '%' idc
boolexpr:
		True
		| False
		| idc '==' idc
		| idc '<=' idc	
		| idc '>=' idc
		| idc '>' idc
		| idc '<' idc
		| Not idc
		| idc Or idc
		| idc And idc
expr:
		arithexpr 
        | boolexpr 
assignstmt: 
		Identifier '=' Identifier 
		| Identifier '=' Const
		| Identifier '=' True
		| Identifier '=' False
inputstmt: 
		Read Identifier
ouputstmt:   
		Write idc
condstmt: 
		If boolexpr Then '{' stmtlist '}'
whilestmt:
		While boolexpr Do '{' decllist stmtlist '}' Done
stmt:
		assignstmt
		| inputstmt
		| ouputstmt
		| condstmt 
		| whilestmt
stmtlist:
		stmt
		| stmt stmtlist
		| /* empty */


		
%%

int main(int argc, char *argv[]) {
    ++argv, --argc; /* skip over program name */ 
    
    // sets the input for flex file
    if (argc > 0) 
        yyin = fopen(argv[0], "r"); 
    else 
        yyin = stdin; 
    
    //read each line from the input file and process it
	while (!feof(yyin)) {
        yyparse();
    }
    printf("The file is sintactly correct!\n");
    return 0;
}

void yyerror(const char *s) {
    printf("Error: %s! \n", s);
    exit(1);
}