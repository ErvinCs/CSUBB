; (c-d-a)+(b+b)-(c+a)
; a : byte; b : word; c : double word; d : qword
; Signed
; Result: 0
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DB -100
    b DW 200
    c DD 500
    d DQ 600
    

segment code use32 class=code
    start:
        MOV AX, [b]
        ADD AX, [b]     ; AX = b+b
        CWDE            ; EAX = b+b
        MOV ECX, EAX    ; ECX = b+b = 400
        
        AND EAX, 00h
        MOV AL, [a]     ; AX = a
        CBW
        CWDE            ; EAX = a
        ADD EAX, [c]    ; EAX = c+a = 400
    
        SUB ECX, EAX    ; ECX = (b+b)-(c+a) = 0
        
        AND EAX, 00h
        MOV AL, [a]     ; AL = a
        CBW             ; AX = a
        MOV BX, AX      ; BX = a
        
        MOV EAX, [c]    ; EAX = c
        SUB AX, BX      ; EAX = c-a
        CDQ             ; EAX:EDX = c-a
        SUB EAX, [d]
        SBB EDX, [d+4]  ; EAX:EDX = c-a-d
        
        ADD EAX, ECX    ; EAX:EDX = (c-a-d)+(b+)-(c+a)

        push    dword 0      
        call    [exit]       
