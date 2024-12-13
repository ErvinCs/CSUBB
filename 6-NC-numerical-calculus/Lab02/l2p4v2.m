function T = l2p4v2(x,f)
  n = length(x);
  T = [f', NaN*ones(n, n-1)];
  for i=2:n
    T(1:n-i+1,i) = diff(T(1:n-i+2, i-1))./(x(i:end) - x(1:end-i+1))'; %asta imi face tabelul ala
  endfor
  % T = [x', T];
  
  % x = 1:0.5:3
  % f = exp(x)
endfunction
