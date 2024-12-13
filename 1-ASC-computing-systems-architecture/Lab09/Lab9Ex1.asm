bits 32 
global start        
extern exit, fopen, fprintf, fclose               
import exit msvcrt.dll    
import fopen msvcrt.dll    
import fprintf msvcrt.dll    
import fclose msvcrt.dll    
                 
segment data use32 class=data
    filename db "afile.txt", 0
    access_mode db "w", 0           ; Write mode
    fd DD 0
    a DD 1, 2, 3, -20
    lena EQU ($-a)/4
    format DB "%d ", 0

segment code use32 class=code
    start:
        ; fopen(filename, access_mode)
        PUSH access_mode
        PUSH filename
        CALL [fopen]
        ADD ESP, 4*2
        
        ; EAX <- file descriptor (handle)
        MOV [fd], EAX
        CMP EAX, 0                  ; Check if the file has succesfully opened
        JE end_prog
        
        ; Write file
        MOV ESI, a
        CLD
        MOV ECX, lena
        JECXZ end_prog
        
        loop1:
            LODSD
            PUSHA
            
            ; fprintf(fd, format, EAX)
            PUSH EAX
            PUSH format
            PUSH DWORD [fd]
            CALL [fprintf]
            ADD ESP, 4*3            ; Clear the Stack
            PUSHA
            
            LOOP loop1
        
        ; Close file: fclose(fd)
        PUSH DWORD [fd]
        CALL [fclose]
        ADD ESP, 4
        
        end_prog:
        
        push    dword 0      
        call    [exit]       
