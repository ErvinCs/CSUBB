#Kill upper triangle
function x = forward_substitution(A,b)
  x = NaN(size(b));
  n = length(b);
  for i = 1:n
    x(i) = 1 / A(i,i) * (b(i) - sum(A(i,1:i-1) * x(1:i-1)));
  endfor
endfunction
