; Transform A : word to B : dword such that the bits are:
; (1)   0-3 (b) = 0
; (2)   4-7 (b) = 8-11 (a)
; (3)   8-9 (b) = 10-11 (b) = invert of 0-1 (a)
; (4)   12-15 (b) = 1
; (5)   16-31 (b) = 0-15 (b)
; Problem 18
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DW 1010110111100110b
    b DD 0
    
segment code use32 class=code
    start:
        ; (1)
        MOV EBX, 0                  ; All bits 0
        
        ; (2)
        MOV AX, [a]
        AND AX, 0000111100000000b   ; Build a mask to isolate bits 8-11 of a
        SHR AX, 4                   ; Move the 4 bits of a to postions 4-7
        OR BX, AX                   ; Move the bits from a to b
        
        ; (3)
        MOV AX, [a]
        NOT AX                      ; Invert a
        AND AX, 0000000000000011b   ; Isolate bits 0-1 of a
        SHL AX, 8                   ; Move the bits to positions 8-9
        OR BX, AX
        SHL AX, 2                   ; Move 1 bits to postions 10-11
        OR BX, AX
        
        ; (4)
        OR BX, 1111000000000000b    ; Set bits 12-15 to 1
        
        ; (5)
        MOV AX, BX
        SHL EBX, 16
        OR BX, AX
        
        push    dword 0      
        call    [exit]       
