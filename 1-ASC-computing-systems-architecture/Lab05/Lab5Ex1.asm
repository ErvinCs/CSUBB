; Compute sum of elements in the array of bytes 'a'
; sum = 0; 
; for(i=0; i<len(a); i++) 
; { sum += a[i]; }    

bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DB 1,2,3,4,5,6
    len EQU ($-a)

segment code use32 class=code
    start:
        MOV AL, 0               ; sum = 0
        MOV ECX, 0              ; i = 0
        
        loop1:
            CMP ECX, len
            JGE exit1
            ADD AL, [a+ECX]     ; AL += a[ECX]
            INC ECX
            JMP loop1
        exit1:
        
        push    dword 0      
        call    [exit]       
; TODO - Do this but with an array of double words