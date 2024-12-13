function R = repTrap(f, a, b, n)
  x = linspace(a, b, n + 1);
  R = (b - a) / (2 * n) .* (f(a) + f(b) + 2 .* sum(f(x(2:n-1))));
endfunction

