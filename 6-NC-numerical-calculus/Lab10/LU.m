function x = LU(A,b)
  [L,U] = lu(A)
  y = forward_substitution(L,b)
  x = backward_substitution(U,y);
endfunction
