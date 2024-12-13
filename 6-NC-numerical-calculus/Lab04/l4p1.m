function L4 = l4p1(x, f, X)
  n = length(x);
  T = [f', NaN*ones(n, n-1)];
  for i=2:n
    T(1:n-i+1,i) = diff(T(1:n-i+2, i-1))./(x(i:end) - x(1:end-i+1))'; % asta imi face tabelul ala
  endfor
 
  prima_linie = T(1,:);
  product = 1;
  suma = f(1);
  for i = 2:n
    product = product * (X - x(i-1));
    suma = suma + T(1, i) * product;
  endfor
 
  L4 = suma
endfunction
% T = divdif(x, f);
% C = T(1, :);
% N = C * cumprod([1, X-x(1:end-1)]';