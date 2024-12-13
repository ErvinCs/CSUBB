%len(C-Counter, L-List)
%len(in, out)
len(0,[]).
len(L+1, [_|T]) :- len(L,T).

%parity(L-List)
%parity(in)
parity([]).
parity([_|[_|T1]]) :-
    parity(T1).

%                          {True,L is empty
%parity(l1, l2, ..., ln) = {parity(l3, l4, ..., ln)   ,otherwise
%                          {False,L has 1 element
%

%min(L-List, R-Minimum)
%min(in, out)
lmin([],_).
lmin([H1, H2|R], H2) :-
    lmin(R,_),
    H1>H2.
lmin([_|T], R) :-
    lmin(T,R).

%
%
%                               { min(l1, l3, l4, ..., ln),l1<l2
%lmin(l1, l2, ..., ln, R) =	{ R = l1,L has 1 element
%                               { min(l2, l3, l4, ..., ln),l2<l1
%
%

%del(L-List, M-Minimum, R-Result)
%del(in, in, out)
del([],_,[]).
del([H|T],M,T) :-
    H =:= M.
del([H|T],M,[H|L]) :-
    H>M,
    del(T,M,L).


%
%                             {del(l2, ..., ln, M, L),l1 > M, L-the tail of the result
%del(l1, l2, ..., ln, M, R) = {
%                             {del(l2, ..., ln, M, T),l1 = M, T
%

%delete(L-List, R-Result)
%delete(in, out)
delete([],[]).
delete(L,R) :-
    lmin(L,M),
    del(L,M,R).
%
%delete(l1, l2, ..., ln, R) =	{ del(l1, l2, ..., ln, M, R) M - the minimum of the list
%

