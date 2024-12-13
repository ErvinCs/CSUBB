; f(a,b) -> a+b

bits 32 
global start        
extern exit               
extern printf
import exit msvcrt.dll    
import printf msvcrt.dll
                          
segment data use32 class=data
    a DD 20
    b DD 12
    s_format DB "my_func(a,b) = %d  // Sum", 0


segment code use32 class=code
    my_func:
        ; ! Daca la exam sunt functii si nu folosim stack-frame se scade !
        ; Create a stack frame: save the old EBP to the top of the stack (with ESP & EBP  pointing at it)
        PUSH EBP
        MOV EBP, ESP
        
        ;       old_ebp     <- ESP, EBP
        ;   +4  ret_addr
        ;   +8  a
        ;   +12 b
        
        ; EAX = a
        MOV EAX, [EBP + 8]
        ADD EAX, [EBP + 12]
        
        ; Restore the original stack
        POP EBP
        RET
        
    start:
        push dword [b]
        push dword [a]
        call my_func
        ADD ESP, 4*2
        
        ; printf(format, EAX)
        PUSH EAX
        PUSH s_format
        CALL [printf]
        ADD ESP, 4*2
        
        push    dword 0      
        call    [exit]       
