%Dubleaza numarul de noduri pana eroarea ii acceptabila
%P2
function Q = baseRomberg(f, a, b, n)
  %Trapez
  Q = ((f(a) + f(b)) * (b - a)) / 2;
  for k = 1:n
    x = a + (b-a) / 2^k * (2 * (1:2^(k-1)) - 1);
    Q = 1/2 * Q + (b-a) / 2^k * sum(f(x));
  endfor
  
endfunction
