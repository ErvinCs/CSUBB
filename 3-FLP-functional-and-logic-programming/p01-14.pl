%
seteq([],S2).
seteq([H1|T1],S2) :-
    inset(S2,H1),
    seteq(T1,S2).

%
inset([],_) :-
    false.
inset([H|_],V) :-
    V = H.
inset([_|T],V) :-
    inset(T,V).

%
nth([],_,C,N).
nth([H|_],E,C,N) :-
    C =:= N,
    E = H.
nth([_|T],E,C,N) :-
    C =\= N,
    nth(T,E,C+1,N).
wnth(L,N,X) :-
    C is 1,
    nth(L,X,C,N).


