#B = (2 1 1 1 2);
##A = (3 -1 0 0 0 0; -1 3 -1 0 0 0 ; 0 -1 3 -1 0 0; 0 0 -1 3 -1 0; 0 0 0 -1 3 -1 0; 0 0 0 0 3 -1);
#A = diag(-ones(1, 5), -1) + diag(-ones(1, 5), 1) + diag(3*ones(1,6), 0)
#err = 10^(-10);
function X = gausSeidel(A, B, err)
  xOld = B;
  xNew = xOld;
  n = length(B);
  
  for i=1:n
    xNew(i) = (1/A(i,i)) * (B(i) - A(i,1:i-1)*xNew(1:i-1) - A(i,i+1:n)*xOld(i+1:n));
  endfor
  iter = 1;
  
  while (norm(xNew - xOld) > err )
    xOld = xNew;
    for i=1:n
    xNew(i) = (1/A(i,i)) * (B(i) - A(i,1:i-1)*xNew(1:i-1) - A(i,i+1:n)*xOld(i+1:n));
  endfor
  iter++;
  endwhile
  fprintf('IterNo=%d\n', iter);  
  xNew
endfunction
