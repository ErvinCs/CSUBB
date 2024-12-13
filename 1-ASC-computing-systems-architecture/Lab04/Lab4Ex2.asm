; Given the words A and B, compute the doubleword C as follows:
; (1)   the bits 0-3 of C are the same as the bits 5-8 of B
; (2)   the bits 4-8 of C are the same as the bits 0-4 of A
; (3)   the bits 9-15 of C are the same as the bits 6-12 of A
; (4)   the bits 16-31 of C are the same as the bits of B
; Problem 2
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DW 0111100111111010b
    b DW 0101010101010101b
    c DD 0
    ; Expected: 1010 0111 1011 1111 0101 0101 0101 0101
    
segment code use32 class=code
    start:
        ; (4)
        MOV ECX, 0
        
        ; (1)
        MOV AX, [a]
        AND AX, 0000000111111000b
        SHL AX, 3
        OR CX, AX
        
        ; (2)
        MOV BX, [b]
        AND BX, 0000000000011100b
        SHL BX, 4
        OR CX, BX
        
        ; (3)
        MOV AX, [a]
        AND AX, 0001111111000000b
        SHL AX, 3
        OR CX, AX
        
        ; ECX = c
        MOV ECX, [c]
        
        push    dword 0      
        call    [exit]       
