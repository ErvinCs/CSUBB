; a+b-(c+d)+100h
; a,b,c,d : word
bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a DW 5  
    b DW 10
    c DW 3
    d DW 2
    h DW 256    ; 100h\s
    r DW 0
    ; Expected result: 0x010A

segment code use32 class=code
    start:
        MOV AX, [a]
        ADD AX, [b] ; AX = a+b
    
        MOV BX, [c]
        ADD BX, [d] ; BX = c+d
    
        SUB AX, BX  ; AX = (a+b) - (c+d)
    
        ADD AX, [h]
        
        MOV [r], AX
    
        push dword 0
        call [exit]