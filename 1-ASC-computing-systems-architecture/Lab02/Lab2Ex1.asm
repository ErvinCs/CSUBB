; a,b,c : dword
;(a+b-c) 
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                         
segment data use32 class=data
    a DD 3
    b DD 10
    c DD 2

segment code use32 class=code
    start:
        MOV EAX, [a]
        ADD EAX, [b]
        SUB EAX, [c]
    
        push    dword 0      
        call    [exit]       