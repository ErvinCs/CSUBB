;(3 1 2 4) (3 4 5) => (1 2 3 4 5)
;a = (a1 a2 ... an); b = (b1 b2 ... bn)
;				{ union(a2...an, b1...bn)  		,a1 is in b
;union(a ,b) =	{ union(a2...an, a1 b1...bn)	,a1 is not in b
;				{ b		,a is empty
(defun union (a b)
	(cond
		((null a) b)
		((null b) a)
		((member (car a) b) (union (cdr a) b))
		((not(member (car a) b)) (union (cdr a) (cons (car a) b)))
	)
)
(setq r (union '(3 1 2 4) '(3 4 5)))
(write r)
(terpri)

;(1 2 (3 (4)) => 24
(defun prod (lis)
	(cond
		((null lis) 1)
		((listp (car lis)) (prod (car lis)))
		((not(numberp (car lis))) (prod (cdr lis)))
		(t(* (prod(cdr lis)) (car lis)))
	)
)	;DO THIS WITH MAP
(setq p (prod '(1 2 (3 (4)))))
(write p)
(terpri)

;(3 5 2 3 4 3 2 1 6) => (1 3 2 3 3 2 5 6)
;(defun sortk2 (lis)
;	(cond
;		((null lis) lis)
;		
;	)
;		
;)
;(setq s (sortk2 '(3 5 2 3 4 3 2 1 6)))
;(write s)
;(terpri)

;(2 3 1 4 0) => 0
(defun minim (lis m)
	(cond
		((null lis) m)
		((<= m (car lis)) (minim (cdr lis) m))
		((> m (car lis)) (minim (cdr lis) (car lis)))
	)
)
(setq m1 (minim '(2 3 1 4 0) (car '(2 3 1 4 0))))
(write m1)
(terpri)

;(1 3 2 1 2 3 1 2 3) => (1 4 7)
(defun elemPos(lis e poz r)
	(cond
		((null lis) r)
		((eq(e (car lis))) (elemPos (cdr lis) e (+ poz 1) (cons r poz)))
		(t (elemPos (cdr lis) e (+ poz 1)))
	)
)
(setq pozs (elemPos '(1 3 2 1 2 3 1 2 3) 1 1 '()))
(write "pozs")
(terpri)
(write pozs)
(terpri)
(write "r")
(terpri)
(write r)
(terpri)

