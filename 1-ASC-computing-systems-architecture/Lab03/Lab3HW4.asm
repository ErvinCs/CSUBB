; (a*a+b/c-1)/(b+c)+d-x; 
; a,c : word; b : byte; d : doubleword; x : qword
; Signed
; Result: -10 (FFFFFFF6)
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DW 20
    b DB 100
    c DW 50
    d DD 500
    x DQ 512
    temp DD 0

segment code use32 class=code
    start:
        MOV AX, [a]         ; AX = a
        IMUL WORD [a]       ; DX:AX = a*a
        MOV [temp], AX
        MOV [temp+2], DX    ; temp = a*a
    
        MOV AL, [b]
        CBW
        CWD                 ; DX:AX = b
        IDIV WORD [c]       ; AX = b/c
        SUB AX, 1           ; AX = b/c - 1
        ADD [temp], AX
        ADC [temp+2], DX    ; temp = a*a+b/c-1 = 401
        
        
        MOV AL, [b]
        CBW
        ADD AX, [c]         ; AX = b+c = 150
        
        MOV BX, AX          ; BX = b+c
        MOV AX, [temp]
        MOV DX, [temp+2]
        IDIV WORD BX        ; AX = (a*a+b/c-1)/(b+c) = 2
        
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
        CDQ
        
        MOV EBX, [x]
        MOV ECX, [x+4]
        
        SUB EAX, EBX
        SBB ECX, EDX

        
        push    dword 0      
        call    [exit]       
