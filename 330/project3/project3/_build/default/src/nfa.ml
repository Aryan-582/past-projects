open List
open Sets

(*********)
(* Types *)
(*********)

type ('q, 's) transition = 'q * 's option * 'q

type ('q, 's) nfa_t = {
  sigma: 's list;
  qs: 'q list;
  q0: 'q;
  fs: 'q list;
  delta: ('q, 's) transition list;
}

(***********)
(* Utility *)
(***********)

(* explode converts a string to a character list *)
let explode (s: string) : char list =
  let rec exp i l =
    if i < 0 then l else exp (i - 1) (s.[i] :: l)
  in
  exp (String.length s - 1) []



(****************)
(* Part 1: NFAs *)
(****************)

let move (nfa: ('q,'s) nfa_t) (qs: 'q list) (s: 's option) : 'q list =
  List.sort_uniq Stdlib.compare (List.fold_right (fun m l -> match m with |(x, y, z) -> if (List.exists (fun n -> if n = x then true else false) qs) = true && y = s then z :: l else l) nfa.delta [])
  

let rec e_closure (nfa: ('q,'s) nfa_t) (qs: 'q list) : 'q list =
  let check = qs in
  let qs' = List.sort_uniq Stdlib.compare (List.fold_right (fun m l -> match m with |(x, y, z) -> if (List.exists (fun n -> if n = x then true else false) qs) = true && y = None 
  then z :: l else l) nfa.delta qs) in
  if (Sets.eq check (qs')) = true then qs' else e_closure nfa qs'

let rec accept_aux (nfa: ('q,char) nfa_t) (states: 'q list) (chars: char list) : bool = 
  match chars with 
  |[] -> if (List.exists (fun x -> if (List.exists (fun y -> if y = x then true else false) nfa.fs) = true then true else false) states) = true then true else false
  |h :: t -> accept_aux nfa (e_closure nfa (move nfa (e_closure nfa states) (Some h))) t  

let accept (nfa: ('q,char) nfa_t) (s: string) : bool =
  accept_aux nfa (e_closure nfa [nfa.q0]) (explode s)

(*******************************)
(* Part 2: Subset Construction *)
(*******************************)

let new_states (nfa: ('q,'s) nfa_t) (qs: 'q list) : 'q list list =
  (List.fold_right (fun x y -> e_closure nfa (move nfa qs (Some x)) :: y ) nfa.sigma [])

let new_trans (nfa: ('q,'s) nfa_t) (qs: 'q list) : ('q list, 's) transition list =
  (List.fold_right (fun x y -> (qs, Some x, e_closure nfa (move nfa qs (Some x))) :: y) nfa.sigma [])

let new_finals (nfa: ('q,'s) nfa_t) (qs: 'q list) : 'q list list =
  (List.fold_right (fun x y -> if (List.exists (fun n -> if n = x then true else false) qs) = true then [qs] @ y else y) nfa.fs [])

let rec get_dfa_states (nfa: ('q,'s) nfa_t) (work: 'q list list) : 'q list list =
  match List.sort_uniq Stdlib.compare(work) with 
  | [[]] -> []
  | lst -> (get_dfa_states nfa (List.fold_right (fun x y -> new_states nfa x @ y) lst [])) @ work 

let rec nfa_to_dfa_step (nfa: ('q,'s) nfa_t) (dfa: ('q list, 's) nfa_t)
    (work: 'q list list) : ('q list, 's) nfa_t =
  match work with 
  | [] -> dfa
  | h :: t -> 
  let new_dfa = { 
  qs = dfa.qs;
  sigma = nfa.sigma;
  delta = List.sort_uniq Stdlib.compare(new_trans nfa h @ dfa.delta);
  q0 = dfa.q0;
  fs = List.sort_uniq Stdlib.compare(if new_finals nfa h != [] then new_finals nfa h @ dfa.fs else dfa.fs)
  } in 
  nfa_to_dfa_step nfa new_dfa t 

let nfa_to_dfa (nfa: ('q,'s) nfa_t) : ('q list, 's) nfa_t =
  let dfa =
    {qs= List.sort_uniq Stdlib.compare(get_dfa_states nfa ([e_closure nfa [nfa.q0]])); sigma= nfa.sigma; delta= []; q0 = (e_closure nfa [nfa.q0]); fs= []}
  in
  nfa_to_dfa_step nfa dfa dfa.qs
