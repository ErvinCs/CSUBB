;Given the words A and B, compute the doubleword C as follows:
; (1)   the bits 0-2 of C have the value 0
; (2)   the bits 3-5 of C have the value 1
; (3)   the bits 6-9 of C are the same as the bits 11-14 of A
; (4)   the bits 10-15 of C are the same as the bits 1-6 of B
; (5)   the bits 16-31 of C have the value 1
; Problem 15

bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DW 0101010101010101b
    b DW 1111000011110000b
    ; Expected: 1111 1111 1111 1111 1110 0010 1011 1000
    
segment code use32 class=code
    start:
        ; (1)
        MOV EBX, 0
        
        ; (2)
        OR BX, 0000000000111000b    ; Set to 1 the bits on 3-5
        
        ; (3)
        MOV AX, [a]     
        AND AX, 0111100000000000b   ; Mask bits 11-14 in A
        SHR AX, 5                   ; Move the bits to positions 6-9
        OR BX, AX
        
        ; (4)
        MOV AX, [b]
        AND AX, 0000000001111110b   ; Mask bits 1-6 in B
        SHL AX, 9
        OR BX, AX
        
        ; (5)
        MOV AX, BX
        SHL EBX, 16
        MOV BX, 0xFFFF
        
        ; exit(0)
        push    dword 0      
        call    [exit]       
