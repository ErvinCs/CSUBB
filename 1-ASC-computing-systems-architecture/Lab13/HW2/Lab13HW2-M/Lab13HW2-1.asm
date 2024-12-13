bits 32
global _compare

; nasm ../Lab13HW2-1.asm -fwin32 -o Lab13HW2-1.obj

segment data use32 class=data public
    msg_input_curr DB "Enter number: ", 0
    format_read DB "%d", 0
    curr dd 0
    
segment code use32 class=code public                          
    ; Takes 2 numbers as parameters, compares them and returns the greater one.
    ; param1 - positive integer
	; param2 - positive integer
    ; return - max(param1, param2) in EAX
    _compare:
        ; ret    - ESP+4
        ; param1 - ESP+8
		; param1 - ESP+12
        PUSH EBP
        MOV EBP, ESP
        
        ; EDX <- a  (passed as a parameter)
        MOV EDX, [EBP + 8]

		; EAX <- b  (passed as a parameter)
		MOV EAX, [EBP + 12]
        
        ; Update Maximum 
        CMP EAX, EDX
        ; if EAX >= EDX then skip               
        ; else if EAX < EDX then EAX <- EDX     
        JAE skip
        MOV EAX, EDX
        skip:
        
        MOV ESP, EBP
        POP EBP
        RET