function N = l4p2(x, f, X)
  
    T = l2p4v2(x,f);
    C = T(1, :);
    N = ones(size(X));
    for l = 1:length(X)
      N(l) = C * cumprod([1, X(l) - x(1:end-1)])';
    endfor
    
    % fplot(@(X) l4p2(x, f, X), [1, 4])
    % hold on
    % fplot( @(x) log10(x), [1,4])
    
endfunction