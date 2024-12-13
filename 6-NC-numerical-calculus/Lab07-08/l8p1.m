function R = l8p1()
  f = @(x) exp(-x.^2);
  a = 1;
  b = 1.5;

  %Rectangle formula 
  fprintf('BaseRectangle: %f\n', baseRectangle(f, a, b));
  
  %Graph of f and the integral's rectangle
  %Create a new figure object (window)
  figure
  subplot(2, 1, 1)
  x1 = a:0.001:b;
  y1 = f(x1);
  hold on
  plot(x1, y1)

  x2 = [1 1.5 1.5 1 1];
  y2 = [0 0 f(1.25) f(1.25) 0];
  plot(x2,y2,'r-');

  %Repeated rectangle
  subplot(2, 1, 2)
  hold on
  plot(x, y)

  x2 = [1 1.5 1.5 1 1];
  y2 = [0 0 f(1.5) f(1.5) 0];
  plot(x2,y2,'r-');

  fprintf('RepRectangle(%d): %f\n', 50, repRectangle(f, a, b, 50));
  fprintf('RepRectangle(%d): %f\n', 500, repRectangle(f, a, b, 500));
endfunction
