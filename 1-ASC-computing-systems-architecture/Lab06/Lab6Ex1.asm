bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DD 1,2,3,4,5
    len EQU ($-a)/4   
    ; $-a = # of bytes from the segment definition until a's end
    ; typeof(a) = DD so /4

segment code use32 class=code
    start:
        MOV EAX, 0      ; sum
        MOV ESI, 0      ; id
        MOV ECX, len
        JECXZ over_loop
        loop1:
            ADD EAX, [a+ESI]
            ADD ESI, 4
            ; alt1 - ADD EAX, [a+ESI*4]
            ; alt1 - INC ESI
            loop loop1
        over_loop:
            
        
        push    dword 0      
        call    [exit]       
