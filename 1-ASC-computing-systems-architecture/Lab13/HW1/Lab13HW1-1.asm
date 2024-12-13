bits 32
global read_and_compare
extern scanf, printf, exit
import scanf msvcrt.dll   
import printf msvcrt.dll  
import exit msvcrt.dll

segment data use32 class=data public
    msg_input_curr DB "Enter number: ", 0
    format_read DB "%d", 0
    curr dd 0
    
segment code use32 class=code public                          
    ; Takes a number as a parameter & reads another number from the keyboard.
    ; It compares the two and returns the maximum.
    ; param  - number that will be compared with what is read from the keyboard
    ;          (i.e. the current maximum)
    ; return - max(param, read number) in EAX
    read_and_compare:
        ; ret   - ESP+4
        ; param - ESP+8
        PUSH EBP
        MOV EBP, ESP
        
        ; EDX <- max  (passed as a parameter)
        MOV EDX, [EBP + 8]
        PUSH EDX
        
        ; Read one number
        PUSH DWORD msg_input_curr
        CALL [printf]
        ADD ESP, 4*1
        
        PUSH DWORD curr
        PUSH DWORD format_read
        CALL [scanf]
        ADD ESP, 4*2
        
        POP EDX
        ; EAX <- curr (read number) 
        MOV EAX, [curr]
        
        ; Update Maximum 
        CMP EAX, EDX
        ; if EAX >= EDX then skip               
        ; else if EAX < EDX then EAX <- EDX     
        JAE skip
        MOV EAX, EDX
        skip:
        
        MOV ESP, EBP
        POP EBP
        RET