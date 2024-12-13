% Polynomial interpolation - Lmf(x) & Ai
% Lagrange polynomial - Polinomul de grad minim cu care se poate aproxima o functie
function L = l3p1(x, y, X)
  m = length(x);
  A = ones(1, m);
  for i = 1:m
    A(i) = 1 / prod(x(i) - x([1:i-1, i+1:m]));
  endfor
  for k = 1:length(X)
    s1 = sum((A.*y) ./ (X(k) - x));
    s2 = sum(A ./ (X(k) - x));
    L(k) = s1 / s2;
  endfor
  % X = 1930:0.1:1980;
  % plot(X, 13p1(x,y,X)
  % Calculeaza direct
  % s1 = sum((A.*y) ./ (X - x));
  % s2 = sum(A ./ (X - x));
  % L = s1 / s2
endfunction
% P4
% X = ? 0:0.1:10
% x = 0:10
% y = 0:0.46:10
% f = @(x) (1 + cos (pi * x)) / (1 + x)
% plot(X, l3p1(x, y ?))
