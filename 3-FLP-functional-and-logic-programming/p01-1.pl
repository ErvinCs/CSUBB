%gcd(in, in, out)
gcd(A,B,G) :-
    A = B,
    G = A.
gcd(A,B,G) :-
    A < B,
    B1 is B-A,
    gcd(A,B1,G).
gcd(A,B,G) :-
    A > B,
    gcd(B,A,G).

%lcm(in, in, out)
lcm(X,Y,LCM) :-
    gcd(X,Y,GCD),
    LCM is X*Y//GCD.

%llcm(in, in, out)
llcm(_,[],_).
llcm(L1,[H|T],LCM) :-
    lcm(L1,H,LCM),
    llcm(H,T,LCM).
%wllcm(in, out)
wllcm([H|T], R) :- 
	llcm(H,T,R).
