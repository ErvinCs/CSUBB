#Adaptive Quadrature + Repeated Simspon
function R = l8p3()
  f = @(x) 100 ./ (x.^2) .* sin(10 ./ x);
  x = linspace(1,3,100);
  eps = 10^(-4);

  a = 1;
  b = 3;

  plot(x, f(x));
  fprintf("AdQuad: %f\n", adaptQuad(f, a, b, eps));
  fprintf("RepSimps(%d): %f\n", 50, repSimpson(f, a, b, 50));
  fprintf("RepSimps(%d): %f\n", 100, repSimpson(f, a, b, 100));
endfunction
