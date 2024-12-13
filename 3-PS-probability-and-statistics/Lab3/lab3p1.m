function lab3p1()
  % Normal
  m=1;
  s=2;
  % Student
  n=10;
  
  % a)
  a1 = cdf('Normal', 0, m, s);
  at = cdf('t', 0, n);
  a2 = 1 - a1;
  
  fprintf('Normal: ');
  fprintf('a1) P(X<=0)=%f\n', a1);
  fprintf('Student: ');
  fprintf('a2) P(X>=0)=%f\n', a2);
  
  % b)
  bn1 = cdf('Normal', -1, m, s);
  bn2 = cdf('Normal', 1, m, s);
  bt1 = cdf('t', -1, n);
  bt2 = cdf('t', 1, n);
  br1 = bn1 - bn2;
  br2 = 1 - br1;
  
  fprintf('Normal')
  fprintf('b1) P(-1<=X<=1)=%f\n', br1)
  fprintf('b2) P(X<=-1 OR X>=1)=%f\n', br2)
  fprintf('Student')
  %TODO
  
  % c)
  %alpha = 0.6;    %Given
  %%ICDF is not yet implemented by Octave Forge
  %xna = icdf('Normal', alpha, m, s);
  %xta = icdf('t', alpha, n);
  %
  %fprintf('Normal');
  %fprintf('Quantil of order (%f) is = %f\n', alpha, xna);
  %fprintf('Student');
  %TODO
  
  
  % d)
  %beta = 0.3;    %Given
  %%ICDF is not yet implemented by Octave Forge
  %xnb = icdf('Normal', 1-beta, m, s);
  %xtb = icdf('t', 1-beta, n);
  %
  %fprintf('Normal');
  %fprintf('Quantil of order (1-%f) is = %f\n', 1-beta, xnb);
  %fprintf('Student');
  %TODO
  
endfunction
