function R = baseSimpson(f, a, b)
  R = (b - a) ./ 6 .* (f(a) + 4 .* f((a + b) / 2) + f(b));
endfunction