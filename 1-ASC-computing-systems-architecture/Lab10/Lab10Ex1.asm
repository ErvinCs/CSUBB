; Count the number of digits in a file
; 
; open
;   do
;     fread
;     count digits
;   while EAX > 0
; close

bits 32 
global start        
extern exit, fopen, fread, fclose, printf               
import exit msvcrt.dll
import fopen msvcrt.dll
import fread msvcrt.dll
import fclose msvcrt.dll
import printf msvcrt.dll
                          
segment data use32 class=data
    filename DB "input_ex_1.txt", 0     ; Note that the file must exist
    access_mode DB "r", 0               ; Read mode
    fd DD 0                             ; File descriptor
    len EQU 100                         ; Buffer size
    buffer times len DB 0               ; Buffer
    
    
segment code use32 class=code
    start:
        ; fopen(filename, access_mode)
        PUSH access_mode
        PUSH filename
        CALL [fopen]
        ADD ESP, 4*2                    ; Free the stack (move the pointer)
        
        ; EAX holds fd
        MOV [fd], EAX
        CMP EAX, 0                      ; Check if the file descriptor is valid
        JE end_prog
        
        MOV EBX, 0                      ; Digit counter
        loop1:
            ; fread(buffer, 1, len, fd) 
            PUSH DWORD [fd]
            PUSH DWORD len              ; Note that len is a constant
            PUSH DWORD 1
            PUSH buffer                 ; Since 'buffer' is an address the assembler knows that it is on 32-bits
            CALL [fread]                ; Read the file
            ADD ESP, 4*4                ; Free the stack (move the pointer)
        
            ; On finishing work with the file   
            CMP EAX, 0
            JE clean_prog
            
            ; Init buffer & counter used to parse the file
            MOV ESI, buffer
            MOV ECX, EAX
            CLD
            
            loop2:
                LODSB   ; AL = curr_char
                
                ; if >= 0 && <= 9 then count in EBX, else skip curr_char
                CMP AL, '0'
                JL next
                CMP AL, '9'
                JG next
                INC EBX
                
                next:
            LOOP loop2
        JMP loop1
        
        ; Close files
        clean_prog:
            ; fclose(fd)
            PUSH DWORD [fd]
            CALL [fclose]
            ADD ESP, 4
        
        end_prog:
        
        ; TODO: print result to console
        
        push    dword 0      
        call    [exit]       
