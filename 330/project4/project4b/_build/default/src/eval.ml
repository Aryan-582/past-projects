open MicroCamlTypes
open Utils

exception TypeError of string
exception DeclareError of string
exception DivByZeroError 

(* Provided functions - DO NOT MODIFY *)

(* Adds mapping [x:v] to environment [env] *)
let extend env x v = (x, ref v)::env

(* Returns [v] if [x:v] is a mapping in [env]; uses the
   most recent if multiple mappings for [x] are present *)
let rec lookup env x =
  match env with
  | [] -> raise (DeclareError ("Unbound variable " ^ x))
  | (var, value)::t -> if x = var then !value else lookup t x

(* Creates a placeholder mapping for [x] in [env]; needed
   for handling recursive definitions *)
let extend_tmp env x = (x, ref (Int 0))::env

(* Updates the (most recent) mapping in [env] for [x] to [v] *)
let rec update env x v =
  match env with
  | [] -> raise (DeclareError ("Unbound variable " ^ x))
  | (var, value)::t -> if x = var then (value := v) else update t x v
        
(* Part 1: Evaluating expressions *)
let compare value1 value2 = 
match value1, value2 with 
| Int i, Int l -> i == l
| String i, String l -> i == l
| Bool i, Bool l -> i == l 
| _, _ -> raise (TypeError("wrong type"))

let get_string value = 
match value with 
| String s -> s 
| _ -> raise (TypeError("wrong type"))

let get_int value = 
match value with 
| Int i -> i
| _ -> raise (TypeError("wrong type"))

let get_bool value = 
match value with 
| Bool b -> b
| _ -> raise (TypeError("wrong type"))
(* Evaluates MicroCaml expression [e] in environment [env],
   returning a value, or throwing an exception on error *)
let rec eval_expr env e = 
match e with 
| Value v -> v 
| ID var -> lookup env var
| Not expr -> (match eval_expr env expr with | Bool(true) -> Bool(false) | Bool(false) -> Bool(true) | _ -> raise (TypeError("wrong type"))) 
| Binop (op, expr1, expr2) -> (match op with | Add -> Int (get_int (eval_expr env expr1) + get_int (eval_expr env expr2)) 
                                             | Sub -> Int (get_int (eval_expr env expr1) - get_int (eval_expr env expr2)) 
                                             | Mult -> Int (get_int (eval_expr env expr1) * get_int (eval_expr env expr2))
                                             | Div -> if get_int (eval_expr env expr2) = 0 then raise (DivByZeroError) else Int (get_int (eval_expr env expr1) / get_int (eval_expr env expr2))
                                             | Greater -> Bool (get_int (eval_expr env expr1) > get_int (eval_expr env expr2))
                                             | Less -> Bool (get_int (eval_expr env expr1) < get_int (eval_expr env expr2))
                                             | GreaterEqual -> Bool (get_int (eval_expr env expr1) >= get_int (eval_expr env expr2))
                                             | LessEqual -> Bool (get_int (eval_expr env expr1) <= get_int (eval_expr env expr2))
                                             | Concat -> String (get_string (eval_expr env expr1) ^ get_string (eval_expr env expr2))
                                             | Equal -> Bool(compare (eval_expr env expr1) (eval_expr env expr2))
                                             | NotEqual -> (Bool (not (compare (eval_expr env expr1) (eval_expr env expr2))))
                                             | Or -> Bool (get_bool(eval_expr env expr1) || get_bool (eval_expr env expr2))
                                             | And -> Bool (get_bool(eval_expr env expr1) && get_bool (eval_expr env expr2)))
| If (expr1, expr2, expr3) -> (match eval_expr env expr1 with | Bool(true) -> eval_expr env expr2 | Bool(false) -> eval_expr env expr3 | _ -> raise (TypeError("wrong type")))
| Let (id, bool, expr1, expr2) -> (match bool with | false -> (eval_expr (extend env id (eval_expr env expr1)) expr2)  
                                                   | true -> let env_new = extend_tmp env id in
                                                             let v = (eval_expr env_new expr1) in
                                                             update env_new id v;
                                                            (eval_expr env_new expr2))  
| Fun (id, expr) -> Closure (env, id, expr)
| FunctionCall (expr1, expr2) -> (match (eval_expr env expr1) with | Closure (env2, id, expr3) -> eval_expr (extend env2 id (eval_expr env expr2)) expr3 
                                                                   | _ -> raise (TypeError("wrong type")))
                                                                    

(* Part 2: Evaluating mutop directive *)

(* Evaluates MicroCaml mutop directive [m] in environment [env],
   returning a possibly updated environment paired with
   a value option; throws an exception on error *)
let eval_mutop env m = 
match m with 
| Def (id, expr) ->  let env_new = extend_tmp env id in 
                     let v = (eval_expr env_new expr) in
                     update env_new id v;
                     (env_new, Some v)
| Expr expr -> (env, Some (eval_expr env expr))
| NoOp -> (env, None)