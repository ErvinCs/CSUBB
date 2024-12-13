function [N, dN] = l5newton(x, f, df, X)   
   n = length(x);
   T = l5divdiff2(x,f,df);
   C = T(1, :);
  for k = 1:length(x)
    dN(k) = C(2);
    N = ones(size(X));
    
    for i = 3:(2*n)
      for j = 1:i-1
        dN(i) = dN(k) + prod(X(k) - C(i) * z(setdiff(1:i-1,j)) %z([1:j-1, j+1:i-1])
      end
      dN(k) = C(i) * dN(k);
    end
   end
endfunction
%x - time
%f - distance
%df - speedf