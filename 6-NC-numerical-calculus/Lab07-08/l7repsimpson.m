%Problem5
f = @(x) 1 ./ (4 + sin(20 .* x));

%coef = @(x) 2 ./ sqrt(3.14)
%intf = e .* -(t*t) %wat dis t?

function A = l7repsimpson(f, a, b, n)
  x = linspace(a, b, n + 1);
  ret = (b - a) / (6 * n) * (f(a) + f(b) + 2 * sum(f(x(2:n))) + 4 * sum(f((x(1:n) + x(2:n+1))/2)))
  
endfunction
