; (a+b*c + 2/c) / (2+a) + e + x
; a,b : byte
; c : word
; e : dWord
; x : qWord
; Signed
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DB 1
    b DB -1
    c DW 0xAB
    e DD 0x1234
    x DQ 17
    t DD 0

segment code use32 class=code
    start:
        MOV AL, [b]     ; AL = b
        CBW             ; AX = b
        IMUL WORD [c]   ; DX:AX = b*c
        
        MOV [t], AX
        MOV [t+2], DX   ; t = DX:AX = b*c
        
        MOV AX, 2       ; AX = 2
        CWD             ; DX:AX = 2
        IDIV WORD [c]   ; AX = 2/c
                        ; DX = 2%c
        CWDE            ; EAX = 2/c
        
        ADD EAX, [t]    ; EAX = 2/c + b*c
        MOV EBX, EAX    ; EBX = 2/c + b*c
        
        MOV AL, [a]
        CBW
        CWDE            ; EAX = a
        ADD EBX, EAX    ; EBX = (a + b*c + 2/c)
        
        MOV AL, [a]
        CBW
        ADD AX, 2       ; AX = (a+2)
        MOV CX, AX      ; CX = (a+2)
        
        MOV [t], EBX
        MOV AX, [t]
        MOV DX, [t+2]   ; DX:AX = (a + b*c + 2/c)
        
        IDIV CX         ; AX = (a + b*c + 2/c) / (a+2)
                        ; DX = (a + b*c + 2/c) % (a+2)
        CWDE            ; EAX = (a + b*c + 2/c) / (a+2)
        
        ADD EAX, [e]    ; EAX = (a + b*c + 2/c) / (a+2) + e
        
        CDQ             ; EDX:EAX = (a + b*c + 2/c) / (a+2) + e
        ADD EAX, [x]
        ADC EDX, [x+4]  ; EDX:EAX = (a + b*c + 2/c) / (a+2) + e + x
        
        push    dword 0      
        call    [exit]       
