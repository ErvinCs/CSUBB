function R = l9p1()
  A = [400 -201; -800 401];
  B = [200; -200];

  x = A\B

  cond(A)

  A = [401 -201; -800 401];
  B = [200; -200];

  x = A\B

  cond(A)
endfunction
