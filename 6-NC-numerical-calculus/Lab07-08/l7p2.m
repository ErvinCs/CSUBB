function R = l7p2()
  f = @(x,y) log(x + 2*y);
  a = 1.4;
  b = 2;
  c = 1;
  d = 1.5;
  
  fprintf('TrapDouble: %f\n', trapDouble(f, a, b, c, d));
endfunction
