;R1
;Se da o lista neliniaraformata din atomi numerici si nenumerici.
;Sa se stearga: elementul maxim de valoare para din lista
(defun firstN (lis)
	(cond
		((null lis) nil)
		((and (listp (car lis)) (and (numberp (firstN (car lis))) (eq(mod (car lis) 2) 0))) (firstN (car lis)))
		((numberp (car lis)) (car lis))
		(t (firstN (cdr lis)))
	)
)

(defun maxim (lis m)
	(cond
		((null lis) m)
		((listp (car lis)) (maxim (cdr lis) (maxim (car lis) (firstN(car lis)))))
		((and(numberp(car lis)) (and(>= m (car lis)) (eq (mod (car lis) 2) 0))) (maxim (cdr lis) m))
		((and(numberp(car lis)) (and(<= m (car lis)) (eq (mod (car lis) 2) 0))) (maxim (cdr lis) (car lis)))
		((atom (car lis)) (maxim (cdr lis) m))
		(t (maxim (cdr lis) m))
	)
)
(setq m1 (maxim '(a 1 (2) (3 c 1 (4 b)) 0) (firstN '(a 1 (2) (3 c 1 (4 b)) 0))))
(write m1)
(terpri)
(setq m1 (maxim '(a b (c)) (firstN '(a b (c)))))
(write m1)
(terpri)