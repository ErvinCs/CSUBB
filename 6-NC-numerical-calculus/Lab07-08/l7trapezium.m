%Draw a trapezium
%Problem 1 - a,b
%Problem 2 - Result = 0.429

function A = l7trapezium()

  f = @(x) 2 ./ (1 + x .^ 2);

  hold on;

  a = 0;
  b = 1;
  A = (b - a) / 2 * (f(a) + f(b));

  x = a:0.01:b;
  y = f(x);

  plot(x, y);
  hold on;
  plot([0 0 1 1], [0 f(0) f(1) 0]);
  ylim([0, 2]);

  axis = ([a-0.1, b+0.1, 0, 2.5]);
  
end