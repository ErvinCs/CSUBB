% Lab2 - (a) (b) (f)
function lab2p1abf()
  n = 3;
  p = 0.5;
  k = 0:n;
  
  y = pdf('bino', k, n, p);
  z = cdf('bino', k, n, p);
  
  figure(1)
    hold on
    plot(k, y, 'b*')
  figure(2)
    plot(k, z, 'ro')
  figure(3)
    stairs(k, z)
    
  N = 1000;
  A = rand(3, N);
  A < 0.5
  x = sum(A < 0.5)
    v(1) = sum(x==0) / N
    v(2) = sum(x==1) / N
    v(3) = sum(x==2) / N
    v(4) = sum(x==3) / N
  figure(1)
    plot(k, v, 'ro')
endfunction
