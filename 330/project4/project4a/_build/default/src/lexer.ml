open TokenTypes
open Str
(* Part 1: Lexer - IMPLEMENT YOUR CODE BELOW *)

let tokenize input = 
    let regex_bool = Str.regexp "true\\|false" in 
    let pos_int = Str.regexp "[0-9]+" in
    let neg_int = Str.regexp "(-[0-9]+)" in 
    let regex_id = Str.regexp "\"[^\"]*\"" in
    let rec tok pos s = 
        if pos >= String.length s then []
        else
            if (Str.string_match (regex_bool) s pos) then
                let token = Str.matched_string s in  
                (Tok_Bool (bool_of_string token)) :: (tok (pos + 5) s)
            else if (Str.string_match (regex_id) s pos) then 
                let token = Str.matched_string s in 
                (Tok_String (String.sub token 1 ((String.length token) - 2))) :: (tok (pos + (String.length token)) s)
            else if (Str.string_match (pos_int) s pos) then 
                let token = Str.matched_string s in 
                (Tok_Int (int_of_string token)) ::  (tok (pos + (String.length token)) s)
             else if (Str.string_match (neg_int) s pos) then 
                let token = Str.matched_string s in 
                (Tok_Int (int_of_string (String.sub token 1 2))) ::  (tok (pos + (String.length token)) s)
            else if (Str.string_match (regexp ")") s pos) then   
                (Tok_RParen) :: (tok (pos + 1) s)
            else if (Str.string_match (regexp "(") s pos) then
                (Tok_LParen) :: (tok (pos + 1) s)
            else if (Str.string_match (regexp "=") s pos) then               
                (Tok_Equal) :: (tok (pos + 1) s)    
            else if (Str.string_match (regexp "<") s pos) && (Str.string_match (regexp ">") s (pos+1)) then              
                (Tok_NotEqual) :: (tok (pos + 2) s)
            else if (Str.string_match (regexp ">") s pos) && (Str.string_match (regexp "=") s (pos+1)) then               
                (Tok_GreaterEqual) :: (tok (pos + 2) s)
            else if (Str.string_match (regexp "<") s pos) && (Str.string_match (regexp "=") s (pos+1)) then             
                (Tok_LessEqual) :: (tok (pos + 2) s)
            else if (Str.string_match (regexp ">") s pos) then              
                (Tok_Greater) :: (tok (pos + 1) s)
            else if (Str.string_match (regexp "<") s pos) then            
                (Tok_Less) :: (tok (pos + 1) s)
            else if (Str.string_match (regexp "|") s pos) && (Str.string_match (regexp "|") s (pos+1)) then             
                (Tok_Or) :: (tok (pos + 2) s) 
            else if (Str.string_match (regexp "&") s pos) && (Str.string_match (regexp "&") s (pos+1)) then         
                (Tok_And) :: (tok (pos + 2) s)    
            else if (Str.string_match (regexp "not") s pos) && ((Str.string_match (regexp " ") s (pos + 3)) || ((pos + 3) >= (String.length s))) then            
                    (Tok_Not) :: (tok (pos + 3) s)
            else if (Str.string_match (regexp "if") s pos) && ((Str.string_match (regexp " ") s (pos + 2)) || ((pos + 2) >= (String.length s))) then           
                    (Tok_If) :: (tok (pos + 2) s)
            else if (Str.string_match (regexp "then") s pos) && ((Str.string_match (regexp " ") s (pos + 4)) || ((pos + 4) >= (String.length s))) then            
                    (Tok_Then) :: (tok (pos + 4) s)
            else if (Str.string_match (regexp "else") s pos) && ((Str.string_match (regexp " ") s (pos + 4)) || ((pos + 4) >= (String.length s))) then           
                (Tok_Else) :: (tok (pos + 4) s)
            else if (Str.string_match (regexp "let") s pos) && ((Str.string_match (regexp " ") s (pos + 3)) || ((pos + 3) >= (String.length s))) then     
                (Tok_Let) :: (tok (pos + 3) s)
            else if (Str.string_match (regexp "def") s pos) && ((Str.string_match (regexp " ") s (pos + 3)) || ((pos + 3) >= (String.length s))) then      
                (Tok_Def) :: (tok (pos + 3) s)
            else if (Str.string_match (regexp "in") s pos) && ((Str.string_match (regexp " ") s (pos + 2)) || ((pos + 2) >= (String.length s))) then       
                (Tok_In) :: (tok (pos + 2) s)
            else if (Str.string_match (regexp "rec") s pos) && ((Str.string_match (regexp " ") s (pos + 3)) || ((pos + 3) >= (String.length s))) then      
                (Tok_Rec) :: (tok (pos + 3) s)
            else if (Str.string_match (regexp "fun") s pos) && ((Str.string_match (regexp " ") s (pos + 3)) || ((pos + 3) >= (String.length s))) then     
                (Tok_Fun) :: (tok (pos + 3) s)
            else if (Str.string_match (regexp "[a-zA-Z][a-zA-Z0-9]*") s pos) then 
                let token = Str.matched_string s in
                (Tok_ID token) :: (tok (pos + (String.length token)) s)
            else if (Str.string_match (regexp "-") s pos) && (Str.string_match (regexp ">") s (pos+1)) then    
                (Tok_Arrow) :: (tok (pos + 2) s)
            else if (Str.string_match (regexp ";") s pos) && (Str.string_match (regexp ";") s (pos+1)) then 
                (Tok_DoubleSemi) :: (tok (pos + 2) s)
            else if (Str.string_match (regexp "+") s pos) then        
                (Tok_Add) :: (tok (pos + 1) s)
            else if (Str.string_match (regexp "-") s pos) then     
                (Tok_Sub) :: (tok (pos + 1) s)
            else if (Str.string_match (regexp "*") s pos) then         
                (Tok_Mult) :: (tok (pos + 1) s)
            else if (Str.string_match (regexp "/") s pos) then      
                (Tok_Div) :: (tok (pos + 1) s)
            else if (Str.string_match (regexp "\\^") s pos) then     
                (Tok_Concat) :: (tok (pos + 1) s)
            else if (Str.string_match (regexp " ") s pos) then 
                (tok (pos + 1) s)
            else raise (InvalidInputException "tokenize failed")
    in 
    tok 0 input