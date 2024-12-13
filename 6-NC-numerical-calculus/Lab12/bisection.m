#a = 1
#b = 2
#f = @(x) (x-2)^2 - log(x)
#err = 0.0001
#x0 = (1 + 2) / 2
function c = bisection(a, b, f, x0, err)
  iterNo = 1000;
  do
    iterNo = iterNo - 1;
    c = (a + b) / 2;
    if (f(c) * f(a) < 0) 
      b = c;
    else
      a = c;
    endif
  until ((iterNo == 0) || (abs(a - b) < err))
  fprintf("IterNo=%d\n", 1000-iterNo);
endfunction
