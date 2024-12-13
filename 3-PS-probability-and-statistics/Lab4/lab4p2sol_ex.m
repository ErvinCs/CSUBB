% 1)
% RAND  - Uniform(0,1)
% RANDN - Normal(0,1) 
% HIST
% 99% of data is in (-3, 3)
% [0,1] --> [a,b]
%    y --> w = a + (b-a)*y 
%    for y = 0 => a
%    for y = 1 => b
% NORMRND, BINORND, POISSRND
%
% 2)
% Bernoulli distribtuion - T or F
% Bino distribtuion - 'p' is fixed - PDF('bino', 0:n, n, p)
% BINORND(n, p, 1, 100)
% Binomial random using rand
%    if rand <  p then '1'
%    if rand >= p then '0'
% rel_freq_1 = sum(X)/S;
% rel_freq_0 = 1-rel_fr_1;
%
% Geometric distribution - probability of k failures before a success
%    pdf('geo', k, p)
%
% Pascal distribtuion - probability of k failures before n successes
% 
% DOC PDF 
%






function lab4p2sol_ex()
% 1)
% Generate X e Bern(p)
clear ALL
p = input('probability of success = ');
U = rand;
X = (U < p);
clear X;

% Generate a sample
N = input('number of simulations = ');
for i = 1:N
    U = rand;
    X(i) = (U < p);
end
UX = unique(X)
fr = hist(X, length(UX));
relfr = fr / N

% 2)
% Generate X e Bino(p)
clear ALL
n = input('number of trials = ');
p = input('probability of success = ');
U = rand(n, 1);
X = sum(U < p);
clear X;

% Generate a sample
N = input('number of simulations = ');
for i = 1:N
    U = rand(n, 1);
    X(i) = sum(U < p);
end
UX = unique(X);
fr = hist(X, length(UX));
relfr = fr / N;

% Compare graphically to the Bino(n,p) distribution
xpdf = 0:n;
ypdf = binopdf(xpdf, n, p);
clf;
plot(xpdf, ypdf, 'bo', UX, relfr, 'rx', 'MarkerSize', 10);
legend('binopdf', 'simulation');

% 3)
% Generate X e Geo(p)
clear ALL
p = input('probability of success = ');
X = 0;
while(rand >= p)
    X = X+1;
end;
clear X;

% Generate a sample
N = input('number of simulations = ');
for i = 1:N
    X(i) = 0;
    while(rand >= p)
        X(i) = X(i)+1;
    end;
end

fprintf('%d ', X);
hist(X);
figure(2);
hist(geornd(p, 1, N));

% 4)
% Generate X e NB(p)
clear ALL
n = input('rank of success = ');
p = input('probability of success = ');
for j = 1:n
    Y(j) = 0;
    while(rand >= p)
        Y(j) = Y(j)+1;
    end;
end
X = sum(Y);
clear X;

% Generate a sample
N = input('number of simulations = ');
for i = 1:N
    for j = 1:n
        Y(j) = 0;
        while(rand >= p)
            Y(j) = Y(j)+1;
        end;
    end
    X(i) = sum(Y);
end


fprintf('%d ', X);
hist(X);
figure(2);
hist(nbinrnd(n, p, 1, N));
endfunction
