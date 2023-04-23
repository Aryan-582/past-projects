open Funs

(*************************************)
(* Part 2: Three-Way Search Tree *)
(*************************************)

type int_tree =
  | IntLeaf
  | IntNode of int * int option * int_tree * int_tree * int_tree 

let empty_int_tree = IntLeaf

let rec int_insert x t =
  match t with
  | IntLeaf -> IntNode (x, None, IntLeaf, IntLeaf, IntLeaf)
  | IntNode (a, b, c, d, e) -> 
    if x < a then 
      if b = None then
        IntNode (x, Some a, c, d, e) 
      else 
        IntNode (a, b, int_insert x c, d, e)   
    else if x > a then 
      if b = None then 
        IntNode (a, Some x, c, d, e)
      else
        if Some x < b then 
          IntNode (a, b, c, int_insert x d, e) 
        else if Some x > b then
          IntNode (a, b, c, d, int_insert x e)
        else t  
    else t
;;
let rec int_mem x t =
  match t with 
  | IntLeaf -> false 
  | IntNode (a, b, c, d, e) -> 
    if x = a then true 
    else if Some x = b then true 
    else if x < a then int_mem x c 
    else if Some x < b then int_mem x d
    else int_mem x e
;;
let rec int_size t =
  match t with 
  | IntLeaf -> 0
  | IntNode (a, b, c, d, e) -> 
    if b = None then 1
    else 2 + (int_size c) + (int_size d) + (int_size e)
;;
let rec int_max t =
  match t with 
  | IntLeaf -> raise (Invalid_argument("int_max"))
  | IntNode (a, b, c, d, e) ->
    if b = None then a
    else if e = IntLeaf then match b with |None -> failwith "it should never get here" |Some b -> b 
    else int_max e
;;
(*******************************)
(* Part 3: Three-Way Search Tree-Based Map *)
(*******************************)

type 'a tree_map =
  | MapLeaf
  | MapNode of (int * 'a) * (int * 'a) option * 'a tree_map * 'a tree_map * 'a tree_map

let empty_tree_map = MapLeaf

let rec map_put k v t = 
  match t with
  | MapLeaf -> MapNode ((k, v), None, MapLeaf, MapLeaf, MapLeaf)
  | MapNode ((x, y), None, c, d, e) -> 
    if k < x then MapNode ((k, v), Some (x, y), c, d, e) else if k > x then MapNode ((x, y), Some (k, v), c, d, e) else raise (Invalid_argument("map_put"))
  | MapNode ((x, y), Some (l, p), c, d, e) -> 
    if k < x then 
      if Some (l, p) = None then
        MapNode ((k, v), Some (x, y), c, d, e) 
      else 
        MapNode ((x, y), Some (l, p), map_put k v c, d, e)   
    else if k > x then 
      if Some (l, p) = None then 
        MapNode ((x, y), Some (k, v), c, d, e)
      else
        if Some k < Some l then 
          MapNode ((x, y), Some (l, p), c, map_put k v d, e) 
        else if Some k > Some l then
          MapNode ((x, y), Some (l, p), c, d, map_put k v e)
        else raise (Invalid_argument("map_put"))  
    else raise (Invalid_argument("map_put")) 
;;

let rec map_contains k t = 
  match t with 
  | MapLeaf -> false 
  | MapNode ((x, y), None, c, d, e) -> if k = x then true else false
  | MapNode ((x, y), Some (l, p), c, d, e) -> 
    if k = x then true 
    else if Some k = Some l then true 
    else if k < x then map_contains k c 
    else if Some k < Some l then map_contains k d
    else map_contains k e
;;
let rec map_get k t =
  match t with 
  | MapLeaf -> raise (Invalid_argument("map_get"))
  | MapNode ((x, y), None, c, d, e) -> if k = x then y else raise (Invalid_argument("map_get"))
  | MapNode ((x, y), Some (l, p), c, d, e) ->
    if k = x then y 
    else if Some k = Some l then p 
    else if k < x then map_get k c 
    else if Some k < Some l then map_get k d
    else map_get k e
;;
(***************************)
(* Part 4: Variable Lookup *)
(***************************)

(* Modify the next line to your intended type *)
type lookup_table = 
| NoScope
| Scope of (string * int) list * lookup_table

let empty_table : lookup_table = NoScope

let rec push_scope (table : lookup_table) : lookup_table = 
  match table with 
  |NoScope -> Scope ([], NoScope)
  |Scope (a, b) -> Scope (a, push_scope b)
;;

let rec pop_scope (table : lookup_table) : lookup_table =
  match table with 
  | NoScope -> failwith "No scopes remain!"
  | Scope (a, NoScope) -> NoScope
  | Scope (a, b) -> Scope (a, pop_scope b)
;;

let rec find name list = 
  match list with 
  | [] -> -1 
  | (x, y) :: t -> if x = name then y else find name t 
;;

let rec add_var name value (table : lookup_table) : lookup_table =
  match table with 
  | NoScope -> failwith "There are no scopes to add a variable to!"
  | Scope (a, NoScope) -> if find name a = -1 then Scope ((name, value) :: a, NoScope) else failwith "Duplicate variable binding in scope!"
  | Scope (a, b) -> Scope (a, add_var name value b)
;;


let rec lookup name (table : lookup_table) =
  match table with 
  | NoScope -> failwith "Variable not found!"
  | Scope (a, NoScope) -> if find name a != -1 then find name a else lookup name (pop_scope table)
  | Scope (a, b) -> lookup name b
;;