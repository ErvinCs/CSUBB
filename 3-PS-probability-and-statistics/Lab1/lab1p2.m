%x in [-4, 4]; f(x) = abs(x)
%x in [0, 2*PI]; g(x) = sin(x)
%Plot f & g
function lab1p2() 
  close all %If any figures are already drawn, close them
  
  x1 = -4:0.01:4;
  x2 = 0:0.01:2*pi;
  
  f1 = abs(x1);
  f2 = sin(x2);
  
  %Plot f on figure 1
  figure(1)
    plot(x1, f1)
  %Plot g on figure 2
  figure(2)
    plot(x2, f2)
  
  %Plot f & g on the same figure
  figure(3)
    plot(x1, f1, 'Color', 'r')
    hold on %Use to plot in the same figure
    plot(x2, f2, 'Color', 'g')
    
endfunction
