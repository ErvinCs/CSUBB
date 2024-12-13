%INTRO

v = [1, 2, 3]
%or
v = [1 2 3]; 
%end with ';' to not print

v'  %transposed 'v'

w = [1; 2; 3;];  %each element on a line

%help [command] 

A = [1, 2; 3, 4; 5, 6];  %matrix

B = zeros(3, 4);

%Commands: 
% det(A), inv(A), 
% max(A), max(A,1), max(A,2), min(A) ...
% sum(A)
% prod(A)
% zeros(x), zeros(x,y)
% ones(x), ones(x,y)
% clear all
% legend
% xlabel, ylabel

%Operations:
% A*B
% A.
% A.*B.

X = 0:0.1:1; 
Y = 10:-2:1;
% X = from: step: to
% Implicit step is 1

Z = 0:1:10;
Z.^2
%Raise to the power of 2 all elements