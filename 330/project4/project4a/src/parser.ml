open MicroCamlTypes
open Utils
open TokenTypes

(* Provided functions - DO NOT MODIFY *)

(* Matches the next token in the list, throwing an error if it doesn't match the given token *)
let match_token (toks: token list) (tok: token) =
  match toks with
  | [] -> raise (InvalidInputException(string_of_token tok))
  | h::t when h = tok -> t
  | h::_ -> raise (InvalidInputException(
      Printf.sprintf "Expected %s from input %s, got %s"
        (string_of_token tok)
        (string_of_list string_of_token toks)
        (string_of_token h)))

(* Matches a sequence of tokens given as the second list in the order in which they appear, throwing an error if they don't match *)
let match_many (toks: token list) (to_match: token list) =
  List.fold_left match_token toks to_match

(* Return the next token in the token list as an option *)
let lookahead (toks: token list) = 
  match toks with
  | [] -> None
  | h::t -> Some h

(* Return the token at the nth index in the token list as an option*)
let rec lookahead_many (toks: token list) (n: int) = 
  match toks, n with
  | h::_, 0 -> Some h
  | _::t, n when n > 0 -> lookahead_many t (n-1)
  | _ -> None

(* Part 2: Parsing expressions *)

let rec parse_expr toks = 
  match lookahead toks with 
  | Some Tok_Let -> parse_Let toks 
  | Some Tok_If -> parse_If toks 
  | Some Tok_Fun -> parse_Fun toks 
  | _ -> parse_Or toks
and parse_Let toks = 
  let t = match_token toks Tok_Let in 
  match lookahead t with 
  | Some Tok_Rec -> let t' = match_token t Tok_Rec in
              let id = match lookahead t' with 
              | Some Tok_ID a -> a 
              | _ -> raise (InvalidInputException "bad")
              in 
              let t'' = match_token t' (Tok_ID id) in
              let t''' = match_token t'' Tok_Equal in 
              let (t'''', expr1) = parse_expr t''' in 
              let t''''' = match_token t'''' Tok_In in 
              let (t'''''', expr2) = parse_expr t''''' in   
              (t'''''', Let (id, true, expr1, expr2)) 
  |_ -> let id = match lookahead t with 
        | Some Tok_ID a -> a
        | _ -> raise (InvalidInputException "bad")
        in
        let t' = match_token t (Tok_ID id) in
        let t'' = match_token t' Tok_Equal in 
        let (t''', expr1) = parse_expr t'' in 
        let t'''' = match_token t''' Tok_In in 
        let (t''''', expr2) = parse_expr t'''' in   
        (t''''', Let (id, false, expr1, expr2)) 
and parse_Fun toks = 
  let t = match_token toks Tok_Fun in 
  let id = match lookahead t with 
  | Some Tok_ID a -> a
  | _ -> raise (InvalidInputException "bad")
  in 
  let t' = match_token t (Tok_ID id) in 
  let t'' = match_token t' Tok_Arrow in 
  let (t''', expr) = parse_expr t'' in  
  (t''', Fun (id, expr))
and parse_If toks = 
  let t = match_token toks Tok_If in
  let (t', expr1) = parse_expr t in  
  let t'' = match_token t' Tok_Then in 
  let (t''', expr2) = parse_expr t'' in 
  let t'''' = match_token t''' Tok_Else in
  let (t''''', expr3) = parse_expr t'''' in  
  (t''''', If (expr1, expr2, expr3))
and parse_Or toks = 
  let (t, expr1) = parse_And toks in 
  match lookahead t with 
  | Some Tok_Or -> let t' = match_token t Tok_Or in 
                  let (t'', expr2) = parse_Or t' in
                  (t'', Binop (Or, expr1, expr2))
  | _ -> (t, expr1) 
and parse_And toks = 
  let (t, expr1) = parse_Equality toks in 
  match lookahead t with 
  | Some Tok_And -> let t' = match_token t Tok_And in 
                   let (t'', expr2) = parse_And t' in 
                   (t'', Binop (And, expr1, expr2))
  | _ -> (t, expr1)
and parse_Equality toks = 
  let (t, expr1) = parse_Relational toks in 
  match lookahead t with 
  | Some Tok_Equal -> let t' = match_token t Tok_Equal in 
                     let (t'', expr2) = parse_Equality t' in 
                     (t'', Binop (Equal, expr1, expr2))
  | Some Tok_NotEqual -> let t' = match_token t Tok_NotEqual in
                        let (t'', expr2) = parse_Equality t' in 
                        (t'', Binop (NotEqual, expr1, expr2))
  |_ -> (t, expr1) 
and parse_Relational toks = 
  let (t, expr1) = parse_Additive toks in 
  match lookahead t with 
  | Some Tok_Less -> let t' = match_token t Tok_Less in 
                    let (t'', expr2) = parse_Relational t' in 
                    (t'', Binop(Less, expr1, expr2))
  | Some Tok_Greater -> let t' = match_token t Tok_Greater in 
                       let (t'', expr2) = parse_Relational t' in 
                       (t'', Binop(Greater, expr1, expr2))
  | Some Tok_LessEqual -> let t' = match_token t Tok_LessEqual in
                         let (t'', expr2) = parse_Relational t' in 
                         (t'', Binop(LessEqual, expr1, expr2))
  | Some Tok_GreaterEqual -> let t' = match_token t Tok_GreaterEqual in 
                            let (t'', expr2) = parse_Relational t' in 
                            (t'', Binop(GreaterEqual, expr1, expr2))
  |_ -> (t, expr1)
and parse_Additive toks = 
  let (t, expr1) = parse_Multiplicative toks in
  match lookahead t with 
  | Some Tok_Add -> let t' = match_token t Tok_Add in 
                   let (t'', expr2) = parse_Additive t' in
                   (t'', Binop(Add, expr1, expr2))
  | Some Tok_Sub -> let t' = match_token t Tok_Sub in 
                   let (t'', expr2) = parse_Additive t' in 
                   (t'', Binop(Sub, expr1, expr2))
  |_ -> (t, expr1)
and parse_Multiplicative toks = 
  let (t, expr1) = parse_Concat toks in
  match lookahead t with 
  | Some Tok_Mult -> let t' = match_token t Tok_Mult in 
                    let (t'', expr2) = parse_Multiplicative t' in
                    (t'', Binop(Mult, expr1, expr2))
  | Some Tok_Div -> let t' = match_token t Tok_Div in 
                   let (t'', expr2) = parse_Multiplicative t' in 
                   (t'', Binop(Div, expr1, expr2))
  |_ -> (t, expr1)
and parse_Concat toks = 
  let (t, expr1) = parse_Unary toks in
  match lookahead t with 
  | Some Tok_Concat -> let t' = match_token t Tok_Concat in 
                      let (t'', expr2) = parse_Concat t' in
                      (t'', Binop(Concat, expr1, expr2))
  |_ -> (t, expr1)
and parse_Unary toks =
  match lookahead toks with 
  | Some Tok_Not -> let t = match_token toks Tok_Not in 
                   let (t', expr1) = parse_Unary t in 
                   (t', Not (expr1))
  | _ -> let (t, expr1) = parse_FunctionCall toks in
        (t, expr1) 
and parse_FunctionCall toks =
  let (t, expr1) = parse_Primary toks in
  match lookahead t with 
  | Some Tok_Int a -> let (t', expr2) = parse_Primary t in 
                   (t', FunctionCall(expr1, expr2))
  | Some Tok_Bool a -> let (t', expr2) = parse_Primary t in 
                   (t', FunctionCall(expr1, expr2))
  | Some Tok_String a -> let (t', expr2) = parse_Primary t in 
                   (t', FunctionCall(expr1, expr2))
  | Some Tok_ID a -> let (t', expr2) = parse_Primary t in 
                   (t', FunctionCall(expr1, expr2))
  | Some Tok_LParen -> let (t', expr2) = parse_Primary t in 
                   (t', FunctionCall(expr1, expr2))
  | _ -> (t, expr1)
and parse_Primary toks = 
  match lookahead toks with 
  | Some Tok_Int a -> let int = match lookahead toks with 
                      | Some Tok_Int a -> a
                      | _ -> raise (InvalidInputException "bad")
                      in 
                   let t = match_token toks (Tok_Int int) in
                   (t, Value (Int int))
  | Some Tok_Bool a -> let bool = match lookahead toks with 
                      | Some Tok_Bool a -> a
                      | _ -> raise (InvalidInputException "bad")
                      in 
                   let t = match_token toks (Tok_Bool bool) in
                   (t, Value (Bool bool))
  | Some Tok_String a -> let string = match lookahead toks with 
                      | Some Tok_String a -> a
                      | _ -> raise (InvalidInputException "bad")
                      in
                    let t = match_token toks (Tok_String string) in
                   (t, Value (String string))
  | Some Tok_ID a ->  let id = match lookahead toks with 
                      | Some Tok_ID a -> a
                      | _ -> raise (InvalidInputException "bad")
                      in
                   let t = match_token toks (Tok_ID id) in
                   (t, ID (id))
  | _ -> let t = match_token toks Tok_LParen in
        let (t', expr1) = parse_expr t in 
        let t'' = match_token t' Tok_RParen in
        (t'', expr1)
(* Part 3: Parsing mutop *)

let rec parse_mutop toks = 
  match lookahead toks with 
  | Some Tok_Def -> let t = match_token toks Tok_Def in
                    let id = match lookahead t with 
                      | Some Tok_ID a -> a
                      | _ -> raise (InvalidInputException "bad")
                    in
                    let t' = match_token t (Tok_ID id) in 
                    let t'' = match_token t' Tok_Equal in 
                    let (t''', expr1) = parse_expr t'' in
                    let t'''' = match_token t''' Tok_DoubleSemi in
                    (t'''', Def(id, expr1))
  | Some Tok_Let -> let (t, expr1) = parse_expr toks in
                    let t'' = match_token t Tok_DoubleSemi in 
                    (t'', Expr(expr1))
  | Some Tok_If -> let (t, expr1) = parse_expr toks in
                   let t'' = match_token t Tok_DoubleSemi in 
                   (t'', Expr(expr1))
  | Some Tok_Fun -> let (t, expr1) = parse_expr toks in
                    let t'' = match_token t Tok_DoubleSemi in 
                    (t'', Expr(expr1))
  | Some Tok_Or -> let (t, expr1) = parse_expr toks in
                   let t'' = match_token t Tok_DoubleSemi in 
                   (t'', Expr(expr1))
  | _ -> let t = match_token toks Tok_DoubleSemi in 
         (t, NoOp)