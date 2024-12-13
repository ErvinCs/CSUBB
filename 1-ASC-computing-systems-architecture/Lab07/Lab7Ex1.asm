bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DB 'ana are mere'
    lena EQU $-a    
    voc DB 'aeiouAEIOU'
    lenv EQU $-voc

segment code use32 class=code
    start:
        MOV ESI, a
        MOV EDI, voc
        CLD
        MOV ECX, lena
        
        JECXZ end_prog
        loop1:
            LODSB           ; AL = [ESI] // AL = a[i]
            PUSH ECX        ; Save ECX on the stack
            MOV ECX, lenv
            
            loop2:
                SCASB       ; CMP AL, [EDI]
                            ; INC EDI
                            ; Set flags
                JNE skip1   ; Skip if vocala was not found
                INC EBX     ; Found vocala 
                skip1:
            LOOP loop2
            
            POP ECX
            MOV EDI, voc
        LOOP loop1    
        end_prog:
        
        push    dword 0      
        call    [exit]       
