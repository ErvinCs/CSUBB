%Dubleaza numarul de noduri pana eroarea ii acceptabila
%P2
function Q = altRomberg(f, a, b, n)
  %Trapez
  %T00 
  %T10 T11
  %T20 T21 T22
  %Daca diferenta dintre T(i)(i) si T(i-1)(i-1) < precizie atunci te opresti
  
  T = zeros(n);
  T(1,1) = ((f(a) + f(b)) * (b - a)) / 2;
  
  %Gen prima coloana
  for k = 1:n
    x = a + (b-a) / 2^k * (2 * (1:2^(k-1)) - 1);
    T(k+1, 1) = 1/2 * T(k,1) + (b-a) / 2^k * sum(f(x));
  endfor
  
  for j = 2:(n+1)
    for i=j:(n+1)
      T(i,j) = (4 .^ ((-1)*(j-1)) * T(i-1, j-1) - T(i, j-1)) / (4 .^ ((-1)*(j - 1)) - 1);
    endfor
  endfor
  
  Q = T(i,j);
  
endfunction