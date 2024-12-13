; for(int i = 0; i < len(a); i++)
;   printf("a[%d]=%d", i, a[i]);

bits 32 
global start        
extern exit               
extern printf
import exit msvcrt.dll    
import printf msvcrt.dll
                          
segment data use32 class=data
    a DD 1, 2, 3, 10, 15, 20
    len EQU ($-a)/4
    s_format DB "a[%d] = %d, ", 0     ; %d - expects a double-word

segment code use32 class=code
    start:
        MOV ESI, a
        MOV ECX, len
        
        JECXZ end_prog 
        CLD
        
        MOV EBX, 0                  ; Use EBX for string index
        loop1:
            LODSD
            ; printf(format(3rd), EBX(2nd), EAX(1st))
            PUSHA                   ; Save all registers on the stack
            
            PUSH EAX
            PUSH EBX
            PUSH s_format
            
            CALL [printf]
    
            ADD ESP, 4*3            ; size = 4, count = 3
                                    ; Adding to the stack pointer ESP moves it towards Base, substracting from it moves it upwards
            POPA
            INC EBX
            
            loop loop1
        end_prog:
        
        push    dword 0      
        call    [exit]       
