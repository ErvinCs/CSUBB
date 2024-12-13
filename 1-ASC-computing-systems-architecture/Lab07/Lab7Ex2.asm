; Caractere identice pe pozitii corespondente in cele 2 siruri
; Print in consola

bits 32 
global start        
extern exit, printf               
import exit msvcrt.dll
import printf msvcrt.dll    
                          
segment data use32 class=data
    ; Define a string to be used by prtnf
    str_format DB 'Number of identical characters: %d', 0   ; %d - decimal placeholder
    a DB 'ana are mere'
    lena EQU $-a    
    b DB 'AnA arE Mere'
    lenv EQU $-b

segment code use32 class=code
    start:
        MOV ESI, a
        MOV EDI, b
        CLD 
        MOV ECX, lena
        
        JECXZ end_prog
        MOV EBX, 0
        loop1:
            CMPSB       ; CMP [ESI], [EDI]
            JNE skip_inc
            INC EBX
            skip_inc:
            LOOP loop1
        end_prog:

        ; printf(format, EBX)
        ; Params must be on the stack. Params are placed (and searched for) on the stack from right-to-left
        PUSH EBX        ; Push EBX on the stack
        PUSH str_format ; Push the char pointer (address) of the string on the stack
        CALL [printf]   ; Call printf with the pushed params
        
        ADD ESP, 4*2    ; Pop 2 things of 4 bytes (2 double words) from the stack
        
        push dword 0      
        call [exit]       
