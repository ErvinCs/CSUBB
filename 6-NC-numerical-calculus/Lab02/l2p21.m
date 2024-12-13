function l2p21(N)
  clf; hold on;
  Tn = @(n, t) cos(n*acos(t));
  t = -1:0.1:1;
  for n=1:N
     plot(t, Tn(n,t))
  endfor
endfunction
