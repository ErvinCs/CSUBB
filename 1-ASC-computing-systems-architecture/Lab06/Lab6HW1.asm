; Lab6 - Problem 15
; Given an array S of doublewords, 
; Build the array of bytes D formed from bytes of doublewords sorted as unsigned numbers in descending order.

bits 32 
global start        
extern exit, printf               
import exit msvcrt.dll
import printf msvcrt.dll      
                          
segment data use32 class=data
    a DD 0xABCDEF00;, 0x12345678, 2
    ; [00][EF][CD][AB], [78][56][34][12], [02][00][00][00]
    ; => (b16) EF, CD, AB, 78, 56, 34, 12, 02, 00, 00, 00 
    ; => (b10) 239, 205, 161, 120, 86, 52, 18, 2, 0, 0, 0
    len_a EQU ($-a)/4
    res TIMES len_a*4 DB 0
    
segment code use32 class=code
    start:
        MOV ESI, a
        MOV EDI, res
        MOV ECX, len_a*4
        CLD   
        
        ; Create the array of bytes (unsorted) in res
        JECXZ end_init
        loop_create:
            LODSB       ; AL = a[ECX]
            STOSB       ; EDI = AL
            loop loop_create
        end_init:
        
        ; Bubble Sort the array of bytes in res
        MOV DX, 1
        loop1:
            CMP DX, 0
            JE end_all
            MOV DX, 0
            MOV ESI, res
            CLD
            MOV ECX, len_a*4-1
            loop2:
                MOV AL, [ESI]
                CMP AL, [ESI+1]
                JAE skip_swap
                    MOV AH, [ESI+1]
                    MOV [ESI+1], AL
                    MOV [ESI], AH
                    MOV DX, 1
                skip_swap:
                INC ESI
            LOOP loop2
        JMP loop1
        end_all:
        
        push    dword 0      
        call    [exit]       
