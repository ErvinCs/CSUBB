;(e+g)*2 / (a*c) + (h–f) + b*3
; a,b,c : byte 
; e,f,g,h : word
bits 32 
global start        
extern exit               
import exit msvcrt.dll    
                          
segment data use32 class=data
    a DB 10
    b DB 200
    c DB 12
    const2 DW 2
    e DW 100
    f DW 6
    g DW 50
    h DW 6  ; 5
    ; Expected result: 300 / 120 + 0 + 200 * 3 = 2 + 600 = 602 = 0x025A

segment code use32 class=code
    start:
        MOV AL, [a]     ; AL = a
        MUL BYTE [c]    ; AX = a*c
        
        MOV BX, AX      ; BX = a*c = 120
        
        MOV AX, [e]           ; AX = e
        ADD AX, [g]           ; AX = e+g
        MUL WORD [const2]     ; DX:AX = (e+g)*2
        
        DIV WORD BX     ; AX = DX:AX / BX = (e+g)*2 / (a*c) = 300 / 120 = 2
                        ; DX = DX:AX % BX = (e+g)*2 % (a*c) = 300 % 120 = 60
        MOV CX, AX      ; CX = AX = (e+g)*2 / (a*c) = 2
        
        MOV BX, [h]     ; BX = h
        SUB BX, [f]     ; BX = h-f = 0
        
        MOV AL, 3       ; AL = b
        MUL BYTE [b]    ; AX = b*3 = 600 = 0x258
        
        ADD CX, BX      ; CX = (e+g)*2 / (a*c) + (h-f)
        ADD CX, AX      ; CX = (e+g)*2 / (a*c) + (h-f) + b*3
        
        MOV AX, CX
        
        push    dword 0      
        call    [exit]       
