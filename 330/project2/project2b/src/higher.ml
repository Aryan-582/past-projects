open Funs

(********************************)
(* Part 1: High Order Functions *)
(********************************)

let contains_elem lst e = 
    fold (fun x y -> if y = e then true else x) false lst 
;;

let is_present lst x = map (fun a -> if a = x then 1 else 0) lst;;

let count_occ lst target = fold (fun x y -> if y = target then x + 1 else x + 0) 0 lst ;;

let uniq lst = fold (fun x y -> if contains_elem x y = false then y :: x else x) [] lst;;

let assoc_list lst = 
    map (fun x -> (x, count_occ lst x)) (uniq lst) 
    
;;

let rec combine lst1 lst2 = 
    match lst1 with 
    | [] -> lst2
    | h :: t -> h :: combine t lst2 
;;

let ap fns args = fold (fun x y -> combine x (map y args)) [] fns;;
