; Read the length of an array, then read each element of that array

bits 32 
global start        
extern exit               
extern scanf
extern printf
import exit msvcrt.dll    
import printf msvcrt.dll
import scanf msvcrt.dll
                          
segment data use32 class=data
    a times 100 DD 0
    len DD 0
    s_format DB "%d", 0
    msg_len DB "len=", 0
    msg_elem DB "a[%d]=", 0

segment code use32 class=code
    start:
        PUSH msg_len
        CALL [printf]
        ADD ESP, 4
        
        ; len = scanf(format, &len)
        PUSH len
        PUSH s_format
        CALL [scanf]
        ADD ESP, 4 * 2
        MOV ECX, [len]
        JECXZ end_prog
        MOV EBX, 0
        MOV EDI, a
        CLD
        
        loop1:
            PUSHA
            
            ; printf(msg_elem, ebx)
            PUSH EBX
            PUSH msg_elem
            CALL [printf]
            ADD ESP, 4*2
            
            ;scanf(format, &[EDI])
            PUSH EDI
            PUSH s_format
            CALL [scanf]
            ADD ESP, 4*2
            
            POPA
            ADD EDI, 4
            INC EBX
            LOOP loop1

        end_prog:


        push    dword 0      
        call    [exit]       
