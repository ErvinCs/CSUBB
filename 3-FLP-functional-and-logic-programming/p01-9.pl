app([],E,[E]).
app([H|T], E, [H|L]) :-
    app(T, E, L).

apppoz([],E,[E|T],Poz).
addpoz([H|T],E,[RH|RT],Poz) :-
    Poz > 0,
    Poz1 is Poz-1,
    addpoz(T,E,RT,Poz1).
addpoz([H|T],E,[RH|RT],Poz) :-
    Poz =:= 0,
    app(T,E,New).
