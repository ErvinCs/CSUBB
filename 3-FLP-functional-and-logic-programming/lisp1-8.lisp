;(1 2 3 4) - (3 5) => (1 2 4)
;s1 and s2 = lists; s1 = (s11 s12 ... s1n);	s2 = (s21 s22 ... s2n)
;				{ nil ,s1 = null
;dif(s1 s2) = 	{ s1  ,s2 = null
;				{ (dif(s12 ... s1n, s2))		,s11 in s2 
;				{ (s11 dif(s12 ... s1n, s2))	,s11 not in s2
(defun dif1(s1 s2)
	(cond
		((null s1) NIL) 
		((null s2) s1)
		((not (member (car s1) s2)) (cons (car s1) (dif1 (cdr s1) s2)))
		((member (car s1) s2) (dif1 (cdr s1) s2))
	)
)
(setq rdif1 (dif1 '(1 2 3 4) '(3)))
(write rdif1)
(terpri)

;Built-in
(defun dif2(s1 s2)
	(nset-difference s1 s2)
)
(setq rdif2 (dif2 '(1 2 3 4) '(3)))
(write rdif2)
(terpri)

;-----------------------------------------
;(1 2 (3 (4 5)) 6) => (6 ((5 4) 3) 2 1)
;lis = a list(lis1 lis2 ... lisn)
;				{ nil, lis is empty
;invOne(lis) =  {
;				{ (append(lis2 ... lisn) lis1) ,otherwise
(defun invOne(lis)
	(cond
		((null lis) NIL)
		(t(append (invOne (cdr lis)) (list(car lis))))
	)
)
;				{ lis ,lis is an atom
;invAll(lis) =  {
;				{ invAll(ln ... l1) ,otherwise
(defun invAll(lis)
	(COND
		((atom lis) lis)
		(t(mapcar 'invAll (invOne lis)))
		;(invOne lis) - inverseaza atomii
		;((atom lis) lis) - nu inverseaza atomii
		;mapcar 'invAll - aplica invAll asupra tuturor elementelor din lista
	)
)
(setq rinvAll(invAll '(1 2 (3 (4 5)) 6)))
(write rinvAll)
(terpri)

;-----------------------------------------
;(1 2 (3 (4 5) (6 7)) 8 (9 10 11)) => (1 3 9)
;lis = a list(lis1 lis2 ... lisn)
;				{ nil 			,lis1 is not a list
;firstEl(lis) = { tail		  	,head is even
;				{ (head tail) 	,head is odd 
;
(defun firstEl (lis)
	(if (listp lis)				;if lis1 is a list 
		(let ((head (car lis))					;head <- lis1
              (tail (mapcan #'firstEl lis))) 	;tail <- first elements of all the sublists
        (if (and (numberp head) (oddp head))	;if head is an odd number
            (cons head tail)					;return (head tail) cell
			tail)								;else return tail
		)
      '()						;else return NIL
	)
)
(setq fr (firstEl '(1 2 (3 (4 5) (6 7)) 8 (9 10 11))))
(write fr)
(terpri)

;-----------------------------------------
;(1 2 3 (4 5(6)) a b 4) => 10
;lis = a list(lis1 lis2 ... lisn)
;				{ 0 ,lis is empty
;sumNum(lis) =  { (lis1 + sumNum(lis2 ... lisn)) ,lis1 is a number
;				{ (sumNum(lis1) + sumNum(lis2 ... lisn)) ,lis1 is a list
;				{ (sumNum(lis2 ... lisn)) ,otherwise(lis1 is not a number)
(defun sumNum(lis)
	(cond
		((null lis) 0)
		((numberp (car lis)) (+(car lis) (sumNum (cdr lis))))
		((listp (car lis)) (+(sumNum (car lis)) (sumNum (cdr lis))))
		(t(sumNum (cdr lis)))
	)
)
(setq s (sumNum '(1 (2 (3 a b)) 4)))
(write s)
(terpri)










