function N = l4newton(x, f, X)
  
    T = l2p4v2(x,f);
    C = T(1, :);
    N = C * cumprod([1, X - x(1:end-1)])';
    
endfunction