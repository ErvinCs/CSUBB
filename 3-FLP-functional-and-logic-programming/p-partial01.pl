%Se da o lista/
%Sa se determine elementul minim al listei
%Daca minimul se afla in lista initiala pe o pozitie impara,
%dupa acest minim se va adauga patratul valorii sale.
%Ex.: L = [10, 12, 10, 10]
%     LR = [10, 100, 12, 10, 100, 10]
min([],_).
min([H1,H2|R], H2) :-
    min(R,_),
    H1>H2.
min([_|T], R) :-
    min(T,R).


%does not work :'(
%addpoz([],_,_,[]).
%addpoz([H|T],M,P,[RH|RT]) :-
%    mod(P,2) =:= 1,
%    PN is P+1,
%    app(RT,100,RA),
%    addpoz(T,M,PN,RA).
%addpoz([H|T],M,P,[RH|RT]) :-
%    mod(P,2) =:= 0,
%    PN is P+1,
%    addpoz(T,M,PN,RT).
%wadd(L,R) :-
%    min(L,M),
%    P is 1,
%    addpoz(L,M,P,R).


app([],E,[E]).
app([H|T],E,[H|L]):-
	app(T,E,L).
