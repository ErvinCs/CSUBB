%secant_optimized.speed > secant.speed
#Newton
function x3 = secant_optimized_adica_newton(a, b, f, x0, err)
  iterNo = 1000;
  x0 = a;
  x1 = b;
  for i=1:iterNo
    if abs(a-b) < err
      fprintf("IterNo=%d\n", 1000-i);
      return
    endif
    x2 = x1 - f(x1) / f'(x1);
    x0 = x1;
    x1 = x2;
  endfor
endfunction