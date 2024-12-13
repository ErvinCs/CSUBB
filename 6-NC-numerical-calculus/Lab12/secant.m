%secand.speed > false_position.speed
function x2 = secant(a, b, f, err)
  iterNo = 1000;
  x0 = a;
  x1 = b;
  for i=1:iterNo
    x2 = x1 - (x1 - x0) / (f(x1) - f(x0)) * f(x1);
    if abs(x2-x1) < err
      fprintf("IterNo=%d\n", i);
      return
    endif
    x0 = x1
    x1 = x2
  endfor
endfunction
