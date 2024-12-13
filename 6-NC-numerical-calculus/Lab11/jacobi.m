function jacobi(A, B, err)
  M = diag(diag(A));
  N = M - A;
  T = M \ N;
  c = M \ B;
  xOld = B;
  xNew = xOld;
  n = length(B);
  
  xNew = T*xOld + c;
  iter = 1;
  
  while (norm(xNew - xOld) > ((1 - norm(T)) / norm(T)) * err)
    xOld = xNew;
    xNew = T*xOld + c;
    iter++;
  endwhile
  fprintf('IterNo=%d\n', iter);  
  xNew
endfunction
