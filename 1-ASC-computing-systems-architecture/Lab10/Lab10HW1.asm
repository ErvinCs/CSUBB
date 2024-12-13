; Lab10 - Problem7
; Two natural numbers a and b (a: dword, b: dword, defined in the data segment) are given. 
; Calculate a/b and display the remainder in the following format: "<a> mod <b> = <remainder>". 
; Example: for a = 23, b = 5 it will display: "23 mod 5 = 3".
; The values will be displayed in decimal format (base 10) with sign.

bits 32 
global start        
extern exit, scanf, printf               
import exit msvcrt.dll    
import printf msvcrt.dll
import scanf msvcrt.dll
                          
segment data use32 class=data
    a DD 23
    b DD 5
    remainder DD 0
    format DB "<%d> mod <%d> = <%d>", 0

segment code use32 class=code
    modulo:  
        ; Return EAX % ECX in EAX
        PUSH EBP
        MOV EBP, ESP
        
        ; +4  ret
        ; +8  a
        ; +12 b
        
        MOV EAX, [EBP + 8]
        MOV ECX, [EBP + 12]
        
        MOV EDX, 0         
        DIV DWORD ECX 
        
        ; EAX:EDX div ECX : EAX 
        ; EAX     mod ECX : EDX        
        MOV EAX, EDX     
        
        POP EBP
        RET
        
    start:
        ; Call modulo
        ; EAX = remainder
        PUSH DWORD [b]
        PUSH DWORD [a]
        CALL modulo
        ADD ESP, 4*2
        
        ; Call printf(format, a, b, remainder)
        PUSH EAX
        PUSH DWORD [b]
        PUSH DWORD [a]
        PUSH format
        CALL [printf]
        ADD ESP, 4*4
        
        push    dword 0      
        call    [exit]       
