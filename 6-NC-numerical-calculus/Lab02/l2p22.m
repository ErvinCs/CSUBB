function l2p22(N)
  clf; hold on;
  T0 = @(x) ones(size(x));
  fplot(T0, [-1, 1]);
  
  T1 = @(x) x;
  fplot(T0, [-1,1]);
  
  for i=2:N
    aux = T0
    T1 = @(x) 2*x.*T1(x) - T0(x);
    T0 = aux
    fplot(T, [-1, 1])
  endfor
endfunction
