%divdif2(x, f, df)
function T = l5divdiff2(x, f, df)
  n = length(x);
  T = NaN * ones(2*n, 2*n);
  
  z = zeros(1,2*n);
  z(1:2:end) = x;
  z(2:2:end) = x;
  T=NaN * ones(2*n, 2*n-1);
  T(1:2:end, 1) = f
  T(2:2: end, 1) = f
  T(1:2:end, 2) = df
  T(2:2:end-2,2) = diff(f)./diff(x)
  
  for i = 3:2*n
    T(1:2*n-i+1, i)=diff(T(1:2*n-i+2, i-1))./(z(i:end) - z(1:end-i+1))';
  endfor
  
endfunction

%hermite(x, f, df, X): H2m-1(X)
%x - time
%f - distance
%df - speedf
