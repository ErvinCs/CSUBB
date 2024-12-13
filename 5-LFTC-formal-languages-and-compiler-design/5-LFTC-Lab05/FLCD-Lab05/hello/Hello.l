%{

#include "y.tab.h"
int yyerror(char *errormsg);

%}

%%

("hi"|"oi")"\n"       { return HI;  }
("tchau"|"bye")"\n"   { return BYE; }
.                     { yyerror("Unknown char");  }

%%

int main(void)
{
   yyparse();
   return 0;
}

int yywrap(void)
{
   return 0;
}

int yyerror(char *errormsg)
{
    fprintf(stderr, "%s\n", errormsg);
    exit(1);
}