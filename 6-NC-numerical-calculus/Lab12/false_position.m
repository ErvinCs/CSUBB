function c = false_position(a, b, f, x0, err)
  iterNo = 1000;
  for i=1:iterNo
    c = a - (b - a) / (f(b) - f(a)) * f(a);
    if f(c)*f(a) < 0
      b = c;
    else
      a = c;
    endif
    if abs(a-b) < err
       fprintf("IterNo=%d\n", 1000-i);
      return
    endif
  endfor
endfunction