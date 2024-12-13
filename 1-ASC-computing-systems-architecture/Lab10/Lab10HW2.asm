; Lab10 - Problem15
; A file name and a text (defined in the data segment) are given. 
; The text contains lowercase letters, uppercase letters, digits and special characters. 
; Replace all the special characters from the given text with the character 'X'. 
; Create a file with the given name and write the generated text to file.

bits 32 
global start        
extern exit, fopen, fread, fprintf, fwrite, fclose               
import exit msvcrt.dll    
import fopen msvcrt.dll  
import fread msvcrt.dll     
import fprintf msvcrt.dll    
import fwrite msvcrt.dll  
import fclose msvcrt.dll   
                          
segment data use32 class=data
    text DB "Hell() @! #$ 0@0", 0
    len_text EQU ($-text)
    outputfile DB "outputfile.txt", 0
    access_write DB "w", 0               
    fdw DD 0
    err_msg DB "Error!", 0
    special DB "!@#$%^&*()-=+.,><;:|][{}", 0
    curr_char DD 0
    ; Expected: H3llXXXXXXXXXwXrX

segment code use32 class=code
    start:               
        ; Create/Open outputfile - fopen(path, mode)
        PUSH DWORD access_write
        PUSH DWORD outputfile
        CALL [fopen]
        ADD ESP, 4*2
        ; Store & validate fdw
        MOV [fdw], EAX
        CMP EAX, 0
        JE end_prog
        
        
        ;MOV EDX, 0
        ;MOV DL, [text]
        MOV ESI, text
        MOV ECX, len_text
        CLD
        JECXZ close
        loop_cmp:
            MOV EAX, 0
            LODSB           ; AL = text[ECX]
            
            CMP AL, 122     ; 'z'
            JA isSpecial
            CMP AL, 97      ; 'a'
            JAE write_file
            CMP AL, 90      ; 'Z'
            JA isSpecial
            CMP AL, 65      ; 'A'
            JAE write_file
            CMP AL, 57      ; '9'
            JA isSpecial
            CMP AL, 48      ; '0'
            JAE write_file
            
            isSpecial:
            MOV AL, 'X'
            JMP write_file
            
            write_file:
            pusha
            MOV [curr_char], EAX
            ; Write into fdw 1 time 1 byte (curr_char) - fwrite(string ptr, integer size, integer n, FILE * handle) - writes n times size bytes from
            PUSH DWORD [fdw]        ; Write into fdw
            PUSH DWORD 1            ; Write 1 time
            PUSH DWORD 1            ; Write 1 byte
            PUSH DWORD curr_char    ; From curr_char
            CALL [fwrite]
            ADD ESP, 4*4
            ; Check for read end
            CMP EAX, 0
            JE close
            popa
            
        LOOP loop_cmp        
        
        close:
        ; Close outputfile - fclose(FILE* handle)
        PUSH DWORD [fdw]
        CALL [fclose]
        ADD ESP, 4
        
        end_prog:
        PUSH DWORD 0
        CALL [exit]        
