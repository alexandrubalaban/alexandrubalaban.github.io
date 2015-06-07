%{
#include <stdio.h>
%}
tip int|char|float
id [o-9]
p1 [(]
p2 [)]
params [0-9][a-z]
spatiu [ ]+
nr {cifra}+
%%
\<({nr},)*\> {ECHO;fprintf(yyout,"\n");}
ab+  {printf ("[%s]\n", yytext);}
.|\n ;  
%%
int main(int argc, char** argv){
FILE *g;
if(argc>0)
 yyin = fopen(argv[1],"r");

if(!(g = fopen("out.txt","w")))
    fprintf(stderr,"eroare creare fisier!");
else
 yyout=g;

yylex();

}