; (c-b+a)-(d+a)
; a : byte; b : word; c : double word; d : qword
; Unsigned
; Result: 0x64
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DB 100
    b DW 300
    c DD 600
    d DQ 200 

segment code use32 class=code
    start:
        AND ECX, 00h
        MOV ECX, [c]    ; ECX = c
   
        SUB CX, [b]     ; ECX = c-b
        ADD CL, [a]     ; ECX = c-b+a
    
        AND EAX, 00h    ; EAX = 0
        AND EDX, 00h    ; EDX = 0
        
        MOV AL, [a]     ; AL = a
        ADD EAX, [d]
        ADC EAX, [d+4]  ; EAX:EDX = a+d
        
        SUB ECX, EAX    ; EAX:EDX = (c-b+a)-(d+a)
        
        push    dword 0      
        call    [exit]       
