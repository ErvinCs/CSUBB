%ASTA NU MERGE
function l6p34(degree, X)
  %Generate points
  clf; hold on; axis equal;
  axis([0 2 0 1]);
  x = 0; y = 0;
  xx = []
  yy = []
  while x >= 0 && x <= 2 && y >= 0 && y <= 4
    [x, y] = ginput(1);
    xx = [xx, x];
    yy = [yy, x];
    plot(x, y, '*', 'MarkerSize', 3);
  endwhile
  x(end) = [];
  y(end) = [];
  
  %Use Spline or least square
    %Least square
  func = @(k, X) polyval(polyfit(xx, yy, k), X)
  fplot(func, [min(xx), max(xx)])
  
    %Spline
   % t = linspace(0, 1, length(X))
   % tt = linspace(0,1, 1000)
   % plot(ppval(spline(t, [1, X, 1], tt)
   % plot(ppval(--"--,Y,--"--)
endfunction
