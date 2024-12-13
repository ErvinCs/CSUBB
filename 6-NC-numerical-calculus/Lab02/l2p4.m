function T = l2p4(x,f)
  n = length(x);
  T = [f(x)', NaN*ones(n, n-1)];
  for i=2:n
    T(1:n-i+1,i) = diff(T(1:n - i + 2, i - 1));
  endfor
  T = [x', T];
  % h = 0.25;
  % a = 1;
  % ai = @(i) a + i * h;
  % f = @(x) sqrt(5 * x .^ 2 + 1)
  % x = a:h:2.5
  % l2p4(x,f)
endfunction
