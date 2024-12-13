; Problem 16
; Read a string of unsigned numbers in base 10 from keyboard. 
; Determine the maximum value of the string and write it in the file max.txt (it will be created) in 16  base.

bits 32 
global start        
extern exit, fprintf, fwrite, fopen, fclose, scanf, printf    
import exit msvcrt.dll    
import fprintf msvcrt.dll   
import fwrite msvcrt.dll  
import fopen msvcrt.dll   
import fclose msvcrt.dll   
import scanf msvcrt.dll   
import printf msvcrt.dll   

extern read_and_compare                       
                          
segment data use32 class=data public
    fd DD 0
    access_write DB "w", 0 
    outputfile DB "outputfile.txt", 0
    len dd 0
    max dd 0
    format_read DB "%d", 0
    msg_input_len DB "Enter string length: ", 0
    msg_output DB "Maximum = %x", 0  
    
; nasm -f obj Lab13HW1-M.asm
; nasm -f obj Lab13HW1-1.asm
; alink Lab13HW1-M.obj Lab13HW1-1.obj -oPE -subsys console -entry start
; Lab13HW1-M.exe
    
segment code use32 class=code public
    start:
        ; Create/Open outputfile - fopen(path, mode)
        PUSH DWORD access_write
        PUSH DWORD outputfile
        CALL [fopen]
        ADD ESP, 4*2
        ; Store & validate fd
        MOV [fd], EAX
        CMP EAX, 0
        JE end_prog
    
        ; Call printf(format)
        PUSH DWORD msg_input_len
        CALL [printf]
        ADD ESP, 4*1
        
        ; Call scanf(format, integer target)
        PUSH DWORD len
        PUSH DWORD format_read
        CALL [scanf]
        ADD ESP, 4*2
        
        ; Find maximum
        MOV EAX, [max]
        MOV ECX, [len]
        JECXZ end_prog
        loop1:
            PUSH DWORD ECX
            PUSH DWORD EAX
            CALL read_and_compare
            ; Returns the maximum in EAX
            POP ECX
            POP ECX
        LOOP loop1
        
        ; Display maximum - Call printf(format, maximum)
        PUSH DWORD EAX
        PUSH DWORD msg_output
        CALL [printf]
        ADD ESP, 4*2
        
        ; Write maximum to file
        ; fprintf(file hanle, format [, params])
        PUSH DWORD EAX
        PUSH DWORD msg_output
        PUSH DWORD [fd]
        CALL [fprintf]
        ADD ESP, 4*3

        
        end_prog:
        ; Close outputfile - fclose(FILE* handle)
        PUSH DWORD [fd]
        CALL [fclose]
        ADD ESP, 4
        
        PUSH DWORD 0
        CALL [exit]     