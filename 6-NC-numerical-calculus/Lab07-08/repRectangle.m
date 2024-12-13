%Definite integral - Riemann sum
%P1
function R = repRectangle(f, a, b, n)
  x = linspace((a + ((b-a) / (2*n))), (b - ((b-a)/(2*n))), n);
  R = ((b - a) / n) * sum(f(x(1:n)))
endfunction
