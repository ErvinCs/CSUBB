function R=l7p3()
  r = 110;
  p = 75;
  h = ((60 * r) / (r^2 - p^2));
  
  f = @(x) sqrt((1 - (p / r) ^ 2) * sin(x));
  
  a = 0;
  b = 2 * 3.1415;
  
  n1 = 250;
  n2 = 500;
  
  fprintf('RepTrap(n=%d): %f\n', n1, h * repTrap(f, a, b, n1));
  fprintf('RepTrap(n=%d): %f\n', n2, h * repTrap(f, a, b, n2));
  %Expected: 6.3131
endfunction
