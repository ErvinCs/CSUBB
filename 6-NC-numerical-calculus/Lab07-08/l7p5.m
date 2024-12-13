function R = l7p5()
  f = @(x) (1 ./ (4 + sin(20*x)));
  a = 0;
  b = 3.1415;
  
  n1 = 10;
  n2 = 30;
  fprintf('RepSimpson(n=%d): %f\n', n1, repSimpson(f, a, b, n1));
  fprintf('RepSimpson(n=%d): %f\n', n2, repSimpson(f, a, b, n2));
endfunction
