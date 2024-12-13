; Lab6 Problem26
; A string of doublewords is given. 
; Compute the string formed by the high bytes of the low words from the elements of the doubleword string and these bytes should be multiple of 10.

bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DD 1, 2, 3, 0xABCDEF00
    ; 01[00]0000, 02[00]0000, 03[00]0000, 00[EF]CDAB
    len_a EQU ($-a)/4
    b TIMES len_a DB 0
    const_ten DB 10
    
segment code use32 class=code
    start:
        MOV ECX, len_a
        CLD
        MOV ESI, a
        MOV EDI, b
        JECXZ end_prog
        loop1:
            LODSD       ; EAX = a[i]
            MOV DL, AH  ; Save current value of AH into DL
            MOV AL, AH  ; Move the bits from AH into AL
            CBW         ; Convert AL to AX
            IDIV BYTE [const_ten]   ; AL = quotient
                                    ; AH = remainder
            CMP AH, 0   ; If remainder == 0 then skip
            JNE skip1
            MOV AL, DL  ; Recover the saved byte
            STOSB       ; b[j] = AL
        skip1:
            loop loop1
        end_prog:
        
        push    dword 0      
        call    [exit]       
