function lab2p1cde()
  n = 3;
  p = 0.5;
  
  % c)
  k11 = 0;
  k12 = 2:n;
  c1 = pdf('bino', k11, n, p)    % X  = 0
  c2 = pdf('bino', k12, n, p)    % X != 1
  
  % d)
  k21 = 0:2;
  k22 = 0:1 ;
  d1 = cdf('bino', k21, n, p)    % X <= 2
  d2 = cdf('bino', k22, n, p)    % X <  2
  
  % e)
  k31 = 1:n;
  k32 = 2:n;
  e1 = cdf('bino', k31, n, p)    % X >= 1      
  e2 = cdf('bino', k32, n, p)    % X >  1
endfunction
