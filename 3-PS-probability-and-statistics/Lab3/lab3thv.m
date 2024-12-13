
function lab3th()
  %For Lab3p1 Let m=1, s=2, n=10
  %m=1;
  %s=2;
  %n=10;
  
  % a)
  %P(x <= 0) = F(0) 
  % for normal:  
  %pdf('Normal', 0, m, s)
  % for student: 
  %cdf('t', 0, n)
  
  %P(x >= 0) = 1 - P(x < 0) = 1 - F(0)
  %Important: F(a) = P(x <= a) = P(x < a)
  
  % b)
  %P(a <= x <= b) = F(b) - F(a)
  
  %P(-1 <= x <= 1) = F(1) - F(-1)
  %P(x <= -1 or x >= 1) = 1 - P(-1 < x < 1) = 1 - (F(1) - F(-1))
  
  % c)
  %use ICDF
  %P(x < xalpha) = alpha %xalpha in (0,1)
  %F(xalpha) = alpha
  %xalpha = F^(-1)(alpha) -> ICDF => alpha = 0.6
  %Two functions: functia de densitate & functia de repartitie
  
  % d) ~ca la c)
  %P(x > xbeta) = beta <=> 1-P(x <= xbeta) = beta <=> F(xbeta) = 1 - beta <=> xbeta = F^(-1)(1-beta)
  %ICDF (1 - beta)
  
  %For Lab3p2
  
  
endfunction
