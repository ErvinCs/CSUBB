%del(in, in, out)
del([],_,[]).
del([H|T],E,Tr) :-
    H = E,
    del(T,E,Tr).
del([H|T],E,[Hr|Tr]) :-
    Hr = H,
    del(T,E,Tr).

