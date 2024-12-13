function R = l7p6()
  f = @(x) e ^ ((-1) * (x^2));
  const = 2 / pi;
  a = 0;
  b = 0.5;
  
  n1 = 4;
  n2 = 10;
  
  fprintf('RepSimpson(n=%d): %f\n', n1, repSimpson(f, a, b, n1));
  fprintf('RepSimpson(n=%d): %f\n', n2, repSimpson(f, a, b, n2));
  fprintf('Correct value erf(0.5)=%f\n', 0.52049);
  
endfunction
