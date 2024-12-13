;Convert a tree of type (2) to a tree of type (1)
;(A (B) (C (D) (E))) => (A 2 B 0 C 2 D 0 E 0)
;lis = a list(lis1 lis2 ... lisn)
;					{ nil 											,lis is empty
;convert(lis) = 	{ convert(lis1) 								,lis1 is a list of length 1
;					{ (lis1 length-1) and convert(lis2 ... lisn)    ,lis1 is an atom
;					{ lis11											,otherwise(lis1 is a list of length > 1) where lis11 is car(lis1)
(defun convert(lis)
  (cond
    ((null lis) nil)													;nil ,if lis is null
    ((and (= (length lis) 1) (listp (car lis))) (convert(car lis)))		;convert(lis1) ,if lis1 is a list and it's length is 1
    ((atom (car lis)) (append (list (car lis) (- (length lis) 1)) (convert(cdr lis))))		;(lis1 length-1) and convert(lis2 ... lisn) ,if lis1 is an atom
    (t (append (list (car (car lis)) 0) (convert(cdr lis))))			; lis11 and convert(lis2 ... lisn) ,otherwise(lis1 is a list of length > 1 and lis11 is car(lis1))
  )
)
(setq tr '(A (B) (C (D) (E))))
(write tr)
(terpri)
(setq tr (convert tr))
(write tr)
(terpri)