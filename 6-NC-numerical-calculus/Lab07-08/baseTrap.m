%Draw a trapezium
%Problem 1
  %f = @(x) 2 ./ (1 + x .^ 2);
  %a = 0;
  %b = 1;
%Problem 2 - Result = 0.429

function R = baseTrap(f, a, b)
  R = (b - a) / 2 * (f(a) + f(b));
end