%subst(List, Atom to substitute, List to substitute with, List)
%subst(in, in, in, out).
%
%                                    {subst([l2,...,ln],e,[s1,...,sn],R)
%                                     ,l1 != e
%subst([l1,...,ln],e,[s1,...,sn],R) ={
%                                    {subst([l2,...,ln],e,[s1,...,sn,X)
%                                     and cat([s1,...,sn],X,R)
%                                     (where X = [l2,...,ln]
%                                      => R = [s1,...,sn,l2,...,ln])
%                                     ,otherwise(l1 = e)
subst([],_,_,[]).
subst([H|T],E,S,[H|RT]) :-
    H =\= E,
    !,
    subst(T,E,S,RT).
subst([H|T],E,S,R) :-
    subst(T,E,S,X),
    cat(S,X,R).

%cat(List to concatenate to, List to concatenate with, List)
%cat(in, in, out)
%
%                                {cat([l1,...,ln,n1],[n2,...,nn],R)
%                                 ,otherwise
%cat([l1,...,ln],[n1,...nn],R) = {
%                                {R, list1 or list2 is empty
%
cat([],[],[]).
cat([],L,L).
cat(L,[],L).
cat(L,[H|T],R):-
	app(L,H,New),
	cat(New,T,R).

%app(List to append to, Element to append, List)
%app(in, in, out)
%
%                       {ln+1 <- e ,list is empty (l1<-e, actually)
%app([l1,...,ln],e,R) = {
%                       {app([l2,...,ln],e,R) ,otherwise
%
app([],E,[E]).
app([H|T],E,[H|R]):-
	app(T,E,R).

%-------------------------------

%hetsubst(List to modify, List to modify with, List)
%hetsubst(in, in, out)
%
%                                  {subs([l11,...,l1n],l1,[s1,...,sn],R]
%                                   and hets[l2,...,ln],[s1,...,sn],R)
%                                   ,l1 is list
%hets([l1,...,ln],[s1,...,sn],R) = {
%                                  {hets([l2,...,ln],[s1,...,sn],R)
%                                   ,l1 is not list
%
hetsubst([],[],[]).
hetsubst([],New,R).
hetsubst([H|T],New,[RH|RT]) :-
    is_list(H),
    getfirst(H,First),
    subst(H,First,New,RH),
    hetsubst(T,New,RT).
hetsubst([H|T],New,[H|RT]) :-
    !,
    hetsubst(T,New,RT).

%getfirst(List, Atom)
%getfirst(in, out)
%                          {True, list is empty
%getfirst([l1,...,ln],R) = {
%                          {l1, otherwise
getfirst([],_).
getfirst([H|_],R) :-
    R is H.
