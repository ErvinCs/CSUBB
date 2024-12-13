function R = l8p2()
  f = @(x) 2 ./ (1 + x.^2);
  a = 0;
  b = 1;
  n = 5;

  fprintf("BaseRomberg: %f\n", baseRomberg(f, a, b, n));

  fprintf("AltRomberg: %f\n", altRomberg(f, a, b, n));
endfunction
