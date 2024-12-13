%delete(List to delete from, Atom to delete, List)
%delete(in, in ,out)
%                          {[l1,...,
%                           ,l1=e
%delete([l1,...,ln],e,R) = {delete([l2,...,ln],e,R)
%                           ,otherwise
%
delete([H|T],H,T).
delete([H|T],X,[H|RT]):-
    delete(T,X,RT).

%comb(List, Atom, List)
%comb(in, in, out)
%
%                        {
%comb([l1,...,ln],k,R) = {
%                        {
%
comb(_,0,[]).
comb([X|T],K,[X|Comb]):-
    K>0,
    K1 is K-1,
    comb(T,K1,Comb).
comb([_|T],K,Comb):-
    K>0,
    comb(T,K,Comb).
allcomb(L,K,R):-
    findall(X,comb(L,K,X),R).

%perm(Input list, List)
%perm(in, out)
%
%perm([l1,...,ln],R) = {delete([l1,...,ln],l1,R) and
%                       perm(R,[l2,...,ln])
%                       ,list is not empty
%
perm([],[]).
perm(L,[H|T]):-
    delete(L,H,R),
    perm(R,T).
allperm(L,R) :-
    findall(X,perm(L,X),R).


%aranj(List, Atom, List)
%aranj(in, in, out)
%
%aranj([l1,...,ln],k,R) = {delete([l1,...,ln],l1,Rest) and
%                          aranj(R,k-1,[l2,...,ln])
%                          (where R = [l2,...,ln])
%                          ,k>0
aranj(_,0,[]).
aranj(L,K,[H|T]):-
    K>0,
    K1 is K-1,
    delete(L,H,R),
    aranj(R,K1,T).
allaranj(L,K,R) :-
    findall(X,aranj(L,K,X),R).

