%Problems 3-4
function A = l7reptrap(f, a, b, n)
  x = linspace(a, b, n + 1);
  ret = (b - a) / (2 * n) * (f(a) + f(b) + 2 * sum(f(x(2:n))))
  
endfunction

