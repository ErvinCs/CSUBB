%Represent:
%f(x) = (1/(s1qrt(2*PI*s))) * e ^ -((x-m)^2/2*s) 
%1) Let m = 0, s = 1    //Normal Dis1tribution
%2) Let m = -1, s = 1
%3) Let m = 1, s = 1
%4) Let m = 0, s = 2

%s - represents variance (sigma)
%normal distribution in (-3*sigma, 3*sigma) - sigma^2 = 1
function lab1p3()
  x = -3:0.01:3;
  
  s1 = 1;
  m1 = 0;
  m2 = -1;
  m3 = 1;
  f1 = 1/sqrt(2*pi*s1) * exp(-(x-m1).^2/(2*s1)); %m1, s1
  f2 = 1/sqrt(2*pi*s1) * exp(-(x-m2).^2/(2*s1)); %m2, s1
  f3 = 1/sqrt(2*pi*s1) * exp(-(x-m3).^2/(2*s1)); %m3, s1
  s2 = 2;
  f4 = 1/sqrt(2*pi*s1) * exp(-(x-m1).^2/(2*s2)); %m1, s2
  
  figure(1)
    hold on
    plot(x, f1, 'Color', 'k', 'LineWidth', 2, 'Linestyle', ':');
    
  figure(2)
    hold on
    title('Gauss')
    plot(x, f1, 'b')
    plot(x, f2, 'r')
    plot(x, f3, 'g')
  xlabel('x')
  ylabel('y')
  legend('m=0', 'm=1', 'm=-1')
  
  figure(3)
    hold on
    plot(x, f1, 'r')
    plot(x, f4, 'k')
endfunction
