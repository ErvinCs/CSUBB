function lab3p21()
  clc
  clear all
  close all
  
  n = 30;
  p = 0.65;
  k = 0:n;
  y = pdf('bino', k, n, p);
  figure(1)
    hold on
    bar(k, y)
    
  m = n * p;
  sig = sqrt(n*p*(1-p));
  x = (m - 3*sig) : 0.01  :(m + 3*sig);  %Cherbuchev(?) property
  z = pdf('Normal', x, m, sig);
  plot(x, z, 'Color', 'r', 'LineWidth', 2)
  
endfunction
