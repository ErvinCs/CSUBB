%{

#include "y.tab.h"


%}

%option noyywrap
IDENTIFIER		[a-zA-Z_]{0,7}
CONSTANT		('+'|'-')?([1-9])([0-9]*)
STRING			"[a-z0-9]*"

%%

program			{ return Program; }
int 			{ return Int; }
char 			{ return Char; }
struct 			{ return Struct; }
beginp 			{ return Beginp; }
endp 			{ return Endp; }
read 			{ return Read; }
write 			{ return Write; }
if 				{ return If; }
then 			{ return Then; }
else 			{ return Else; }
while 			{ return While; }
do 				{ return Do; }
done 			{ return Done; }
&& 				{ return And; }
\|\| 				{ return Or; }
\! 				{ return Not; }
\true 			{ return True; }
\false 			{ return False; }
{IDENTIFIER} 		{ return Identifier; }
{CONSTANT}		{ return Const; }
{STRING}		{ return String; }
[ \t]+          /* eat up whitespace */
.               { printf("%s","Lexical Error: Undefined atom");  }

%%

//int main(argc, argv)
//int argc;
//char **argv;
//{
//	++argv, --argc; /* Program name */
//    if (argc > 0)
//    	yyin = fopen(argv[0], "r");
//    else
//     	yyin = stdin;
//    yylex();
    //show();
		//yyparse();
		//return 0;
//}
//int main(void)
//{
//   yyparse();
//   return 0;
//}


//int yywrap(void)
//{
//   return 0;
//}

//int yyerror(char *errormsg)
//{
//   fprintf(stderr, "%s\n", errormsg);
//    exit(1);
//}