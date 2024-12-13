; (a*a+b/c-1)/(b+c)+d-x; 
; a,c : word; b : byte; d : doubleword; x : qword
; Unsigned
; Result: 1
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DW 20
    b DB 100
    c DW 50
    d DD 500
    x DQ 501
    temp DD 0
    

segment code use32 class=code
    start:    
        MOV AX, [a]         ; AX = a
        MUL WORD [a]        ; DX:AX = a*a
        MOV [temp], AX
        MOV [temp+2], DX    ; temp = a*a
    
        MOV AX, 0
        MOV AL, [b]
        MOV DX, 0           ; DX:AX = b
        DIV WORD [c]        ; AX = b/c
        SUB AX, 1           ; AX = b/c - 1
        ADD AX, [temp]
        ADC DX, [temp+2]    ; DX:AX = a*a+b/c-1 = 401
        
        MOV BL, [b]
        MOV BH, 0
        ADD BX, [c]         ; BX = b+c = 150
        
        DIV WORD BX         ; AX = (a*a+b/c-1)/(b+c) = 2
        
        MOV DX, AX          ; DX = (a*a+b/c-1)/(b+c)
        MOV BX, [d]
        MOV CX, [d+2]       ; CX:BX = d = 500
        ADD BX, DX
        ADC CX, 0           ; CX:BX = (a*a+b/c-1)/(b+c)+d
        
        ;SUB BX, [x]
        ;SBB CX, [x+2]       ; CX:BX = (a*a+b/c-1)/(b+c)+d-x 
        
        MOV [temp], BX
        MOV [temp+2], CX    
        
        MOV EAX, [temp]
        MOV EDX, 0
        
        MOV EBX, [x]
        MOV ECX, [x+4]
        
        SUB EAX, EBX
        SBB ECX, EDX
        
        push    dword 0      
        call    [exit]       
