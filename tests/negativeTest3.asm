


data segment   
    ident db 0
data ends

code segment 
    
begin:
    and al,ident  ;; error  ; Segment override operator is mandatory. 
    and al,ds:ident ;; ok 
    
    and eax,ds:undefIdent ;; error undefIdent is not defined

    div es:[ebx + ecx]  ;; error operand type is not defined 1 | 2 | 4
    
    test ds:[eax + esi],al   ;; ok
    test [eax + esi],al     ;; error

    test ds:ident[eax + esi],al    ;; error  - unsupported  
    
    ; jmp undefLabel ;; error undefLabel is not defined

code ends 
    end begin
    ;; error  
    ;; end directive is mandatory