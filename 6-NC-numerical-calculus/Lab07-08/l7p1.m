function R = l7p1()
  f = @(x) 2 ./ (1 + x*x);
  a = 0;
  b = 1;
  
  %Trapezium formula
  fprintf('BaseTrapeziun: %f\n', baseRectangle(f, a, b));
  
  %Graph of f and the trapezium
  %x1 = a:0.001:b;
  %y1 = f(x1);
  %plot(x1, y1);
  
  x2 = [0, 0, 1, 1];
  y2 = [0, f(0), f(1), 0];
  plot(x2,y2,'r-');
  
  %Simpson's formula
  fprintf('BaseSimpson: %f\n', baseSimpson(f, a, b));
endfunction
