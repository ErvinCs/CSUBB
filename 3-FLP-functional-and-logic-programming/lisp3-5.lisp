;Sum_of_evens - Sum_of_odds
;(1 (2 3 (4 5)) 6) => 3
;lis = a list (lis1 lis2 ... lisn)
;					{ nil 											,lis is null
;					{ (lis1 (liniarize(lis2 ... lisn)))				,lis1 is a number
;liniarize(lis) = 	{ (liniarize(lis2 ... lisn))					,lis1 is an atom
;					{ (liniarize(lis1) liniarize(lis2 ... lisn))	,otherwise(lis1 is a list)
(defun liniarize(lis)
	(cond
		((null lis) nil)
		((numberp (car lis)) (cons (car lis) (liniarize (cdr lis))))
		((atom (car lis)) (liniarize (cdr lis)))
		(T (append(liniarize (car lis)) (liniarize (cdr lis))))
	)
)
(setq L (liniarize '(1 (2 3 (4 5)) 6)))
(write L)
(terpri)
;					{ 0 ,lis is null
;even-odd(lis) = 	{ even-odd(lis2 ... lisn) + lis1 ,lis1 is even
;					{ even-odd(lis2 ... lisn) - lis1 ,lis1 is odd
;
(defun even-odd(lis) 
	(cond
		((null lis) 0)
		((=(mod (car lis) 2) 0) (+ (even-odd (cdr lis)) (car lis))) 			
		((=(mod (car lis) 2) 1) (- (even-odd (cdr lis)) (car lis)))			    
	)
)
(setq rez (even-odd(liniarize '(1 (2 3 (4 5)) 6))))
(write rez)
(terpri)