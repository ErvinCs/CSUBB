bits 32 
global start        
extern exit               
import exit msvcrt.dll    
             
% include "Lab11Ex1-M2.asm"
             
segment data use32 class=data
    format DB "%d", 0
    n DB 0

segment code use32 class=code
    start:
        ; scanf(format, n)
        PUSH n
        PUSH format
        CALL [scanf]
        ADD ESP, 4*2

        ; factorial(n)
        PUSH DWORD [n]      ; Push n to the stack as a parameter to 'factorial'
        CALL factorial            ; No square brackets around 'factorial' since it is a user-defined function

        ; printf(format, EAX)
        PUSH EAX
        PUSH format
        CALL [printf]
        ADD ESP, 4*2
        push    dword 0      
        call    [exit]       
