function R = adaptQuad(f, a, b, err)
  s1 = baseSimpson(f, a, b);
  s2 = baseSimpson(f, a, (a + b) / 2) + baseSimpson(f, (a + b) / 2, b);
  
  if (abs(s1 - s2) < 15 * err)
    R = s2;
  else
    R = adaptQuad(f, a, (a + b) / 2, err / 2) + adaptQuad(f, (a + b) / 2, b, err / 2);  
  endif
endfunction
