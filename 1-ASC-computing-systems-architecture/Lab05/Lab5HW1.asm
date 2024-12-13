; Lab5 - Problem 12
; Two character strings S1 and S2 are given. 
; Obtain the string D by concatenating the elements found on even positions in S2 and the elements found on odd positions in S1. 

bits 32 
global start        
extern exit               
import exit msvcrt.dll   
                          
segment data use32 class=data
    s1 DB 'abcdef'      ; Out: bdf
    len1 EQU $-s1
    s2 DB 'abcdefgh'    ; Out: aceg
    len2 EQU $-s2
    res times ((len1 + len2) / 2) + 1 DB 0 
    const2 DB 2
    ; Expected: acegbdf
    
segment code use32 class=code
    start:
        MOV EDI, res
        CLD
        
        ; Parse s2 - even positions (consider 0 even)
        MOV ESI, s2
        MOV ECX, len2
        AND BX, 0   ; Probably could have used ECX as a counter somehow 
                    ;  but using a separate counter seems easier
        JECXZ end1
        loop1:
            MOV AX, BX
            DIV BYTE [const2]   ; AH = BX % 2
            CMP AH, 0           ; Check position parity
            JNZ skip1           ; Skip if position is not even
            MOVSB               ; EDI[BX] = ESI[BX]
                                ; inc(EDI), inc(ESI)
            inc(BX)             ; Increment counter
            LOOP loop1
            skip1:  
            inc(ESI)
            inc(BX)             ; Increment counter
            LOOP loop1
        end1:
        
        ; Parse s1 - odd positions
        MOV ESI, s1
        MOV ECX, len1
        AND BX, 0
        
        JECXZ end2        
        loop2:
            MOV AX, BX
            DIV BYTE [const2]   ; AH = BX % 2
            CMP AH, 0           ; Check position parity
            JZ skip2            ; Skip if position is  even
            MOVSB               ; EDI[BX] = ESI[BX]
                                ; inc(EDI), inc(ESI)
            inc(BX)             ; Increment counter
            JECXZ end2          ; fix - ECX is 0, then it moves to 0xFF for some reason 
            LOOP loop2
            skip2:  
            inc(ESI)
            inc(BX)             ; Increment counter
            JECXZ end2          ; fix - ECX is 0, then it moves to 0xFF for some reason
            LOOP loop2
        end2:
        
        push    dword 0      
        call    [exit]       
