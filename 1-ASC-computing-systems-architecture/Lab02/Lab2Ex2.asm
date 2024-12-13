; (a,b,c) : byte
;  d : word
; (d-b*c+b*2)/a
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                         
segment data use32 class=data
    a DB 2
    b DB 3
    c DB 4
    d DB 10
    
segment code use32 class=code
    start:
        MOV AL, [b] ; AL = b
        MUL byte [c] ; AX = b*c
        
        MOV BX, AX ; BX = AX = b*c
        
        MOV AL, 2   ; AL = 2
        MUL byte [b] ; AX = b*2
        
        ADD AX, [d] ; AX = d+b*2
        SUB AX, BX  ; AX = d+b*2-b*c
        
        DIV byte [a] ; AL = quotient; AH = remainder
    
        push    dword 0      
        call    [exit]   