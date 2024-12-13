%Definite integral - Riemann sum
%P1
function A = l8rectangle(f, a, b, n)
  x = linspace((a + ((b-a) / (2*n))), (b - ((b-a)/(2*n))), n);
  res = ((b - a) / n) * sum(f(x(1:n)))
  
  hold on;
  clf;
  
  %flpot(f, [a,b])
endfunction
