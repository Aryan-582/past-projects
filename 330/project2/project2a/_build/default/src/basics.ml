(***********************************)
(* Part 1: Non-Recursive Functions *)
(***********************************)

let rev_tup tup = match tup with (a, b, c) -> (c, b, a);;

let is_odd x = 
    if x mod 2 == 0 then false else true;;

let area x y = 
    let (a, b) = x in
    let (c, d) = y in
    if c >= a then
        if d >= b then
            (d-b) * (c-a)
        else 
            (b-d) * (c-a)
    else 
        if d >= b then 
            (d-b) * (a-c)
        else 
            (b-d) * (a-c)
;;

let volume x y = 
    let (a, b, c) = x in
    let (d, e, f) = y in
    if d >= a then
        if e >= b then
            if f >= c then
                (f-c) * (e-b) * (d-a)
            else 
                (c-f) * (e-b) * (d-a)
        else 
            if f >= c then
                (f-c) * (b-e) * (d-a)
            else 
                (c-f) * (b-e) * (d-a)
    else 
        if e >= b then
            if f >= c then
                (f-c) * (e-b) * (a-d)
            else 
                (c-f) * (e-b) * (a-d)
        else 
            if f >= c then
                (f-c) * (b-e) * (a-d)
            else 
                (c-f) * (b-e) * (a-d)
;;

(*******************************)
(* Part 2: Recursive Functions *)
(*******************************)

let rec fibonacci n = 
    if n = 0 then 
        0
    else if n = 1 then 
        1
    else 
        fibonacci(n-1) + fibonacci(n-2)
;;

let rec pow x y = 
    if y != 0 then
        x * (pow x (y-1))
    else 
        1
;;

let rec log x y = 
    if x = y then
        1 
    else if x > y then
        0
    else 
        1 + log x (y/x) 
;;


let rec gcf x y = 
    if y = 0 then
        x
    else 
        gcf y (x mod y)
;;
    
let rec is_prime_aux x y = 
    if x = 1 then 
        1
    else if x = y then
        0
    else if x mod y == 0 then
        1
    else 
        is_prime_aux x (y + 1) 
;;    

let rec is_prime x = 
    if (is_prime_aux x 2) == 0 then
        true
    else 
        false
;;


(*****************)
(* Part 3: Lists *)
(*****************)


    
let rec get idx lst =  
    match lst with 
    | [] -> failwith "Out of bounds"
    | h :: t -> if idx = 0 then h else get (idx - 1) t
;;

let rec get_length lst = 
    match lst with 
    | [] -> 0
    | _ :: t -> 1 + get_length t
;;

let larger lst1 lst2 = 
    if get_length lst1 = get_length lst2 then
        []
    else if get_length lst1 > get_length lst2 then 
        lst1
    else 
        lst2
;;


let rec combine lst1 lst2 = 
    match lst1 with 
    | [] -> lst2
    | h :: t -> h :: combine t lst2 
;;

let rec reverse_aux lst = 
    match lst with 
    | [] -> []
    | h :: t -> combine (reverse_aux t) (h :: [])
;;

let reverse lst = reverse_aux lst;;

let rec merge lst1 lst2 = 
    match lst1, lst2 with  
    | [], [] -> []
    | x, [] -> x
    | [], y -> y
    | (h1 :: t1), (h2 :: t2) -> if h1 > h2 then h2 :: merge lst1 t2 else h1 :: merge t1 lst2
;;

let rec rotate shift lst = 
    match lst with 
    | [] -> []
    | h :: t -> if shift = 0 then lst else rotate (shift - 1) (combine t (h::[]))
;;

let rec is_palindrome lst = 
    if lst = reverse lst then 
        true
    else 
        false 
;;