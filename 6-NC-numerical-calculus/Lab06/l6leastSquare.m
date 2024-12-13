%Problem2
%help polyfit
function l6leastSquare(time, temps, k, X) 
  %time = [1, 2, 3, 4, 5, 6, 7];
  %temps = [13, 15, 20, 14, 15, 13, 10];
  
  %plot(time, temps, '*r')
  
  func = @(k, X) polyval(polyfit(time, temps, k), X)
  x = 0:0.1:100;
  plot(x, func(3, x), '*r')
  
endfunction
