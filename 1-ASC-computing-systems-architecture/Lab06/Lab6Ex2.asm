; Lab5 Problem26
; S - byte string. 
; Obtain the maximum of the elements found on the even positions and the minimum of the elements found on the odd positions of S. 
;
; Example:
; S: 1, 4, 2, 3, 8, 4, 9, 5
; max_poz_pare: 9
; min_poz_impare: 3
;
; C-Code
; for(i=0; i<len(s)/2; i+=2) {
;   if(a[2*i] > max) 
;       max = a[2*i]
;   if(a[2*i+1] < min)
;       min = a[2*i+1]
;   if(len(s) % 2 == 1)
;       if(a[len(s) - 1] > max)
;            max = a[len(s)-1]

bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    s DB 1, 2, 3, 4, 5, 6, 1
    len EQU ($-s)

segment code use32 class=code
    start:
        MOV ECX, len
        MOV ESI, 0
        MOV AL, -1      ; min
        MOV BL, 0       ; max
        
        loop1:
            CMP BL, [s+ESI]
            JAE skip1
            ; BL < [s+ESI]
            MOV BL, [s+ESI]
            ; end of string
        skip1:
            DEC ECX
            CMP ECX, 0
            JE over_loop
            ; minimum
            INC ESI
            CMP AL, [s+ESI]
            JBE skip2
            MOV AL, [s+ESI]
        skip2:
            INC ESI
            DEC ECX
            CMP ECX, 0
            JNE loop1
        over_loop:
    
        push    dword 0      
        call    [exit]       
