%Draw a parabola
%Repeated Simspon
f = @(x) x .* log(x);

function A = l7simpson(f, a, b, n)
    x = linspace(a, b, n + 1);
    ret = (b - a) / 6* (f(a) + 4* f((a+b)/2) + f(b));
endfunction
