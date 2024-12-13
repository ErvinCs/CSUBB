%Problem1
%help ppval
%help spline
function l6spline(X, Y, XI, func)
   %When called with two arguments, return the piecewise polynomial PP
   %that may be used with 'ppval' to evaluate the polynomial at
   %specific points.

   %When called with a third input argument, 'spline' evaluates the
   %spline at the points XI.  The third calling form 'spline (X, Y,
   %XI)' is equivalent to 'ppval (spline (X, Y), XI)'.
   
   %spline(X, Y);
   ppval(spline(X, Y), XI)
   YY = [0, 1, 0, -1, 0];   %sin specific codomain
   fplot(func, [0,2*3.14])
   hold on
   plot(X, YY, '*r')
endfunction
