/**
    Returns the sum 1 + 2 + ... + n
    If n is less than 0, return -1
**/
pub fn gauss(n: i32) -> i32 {
    let mut result = 0i32;
    if n > 0 {
        for i in 1..=n {
            result += i 
        }
        return result
    } else if n < 0 {
        return -1i32
    } else {
        return 0i32
    }
}

/**
    Returns the number of elements in the list that 
    are in the range [s,e]
**/
pub fn in_range(ls: &[i32], s: i32, e: i32) -> i32 {
    let mut result = 0i32;
    for &element in ls.iter() {
        if element >= s && element <= e {
            result += 1
        }
    }
    return result
}

/**
    Returns true if target is a subset of set, false otherwise

    Ex: [1,3,2] is a subset of [1,2,3,4,5]
**/
pub fn subset<T: PartialEq>(set: &[T], target: &[T]) -> bool {
    let mut result = 0;
    for element in set.iter() {
        if target.contains(&element) {
            result += 1
        }
    }
    if result == target.len() {
        return true;
    } else {
        return false;
    }
}

/**
    Returns the mean of elements in ls. If the list is empty, return None
    It might be helpful to use the fold method of the Iterator trait
**/
pub fn mean(ls: &[f64]) -> Option<f64> {
    if ls.len() == 0{
        return None;
    }
    let mut result = 0.0f64;
    let mut length = 0.0f64;
    for &element in ls.iter() {
        result += element;
        length += 1.0;
    }
    return Some (result/length);
}

/**
    Converts a binary number to decimal, where each bit is stored in order in the array
    
    Ex: to_decimal of [1,0,1,0] returns 10
**/
pub fn to_decimal(ls: &[i32]) -> i32 {
    let mut result = 0;
    let mut length = 0;
    let mut i = 1;
    for &element in ls.iter() {
        length += 1;
    }
    for &element in ls.iter() {
        if element == 1 {
            result += 2i32.pow(length - i) ;
        }
        i += 1;
    }
    return result;
}

/**
    Decomposes an integer into its prime factors and returns them in a vector
    You can assume factorize will never be passed anything less than 2

    Ex: factorize of 36 should return [2,2,3,3] since 36 = 2 * 2 * 3 * 3
**/
pub fn factorize(n: u32) -> Vec<u32> {
    let mut vector = Vec::new();
    let mut x = n;
    while (x % 2 == 0) {
        vector.push(2);
        x /= 2;
    }
    let y = x as f64;
    for i in 3..=(y).sqrt() as u32 + 1 {
        while (x % i == 0) {
            vector.push(i);
            x /= i;
        }
    }
    if vector.len() == 0 {
        vector.push(n);
    }
    return vector;
}

/** 
    Takes all of the elements of the given slice and creates a new vector.
    The new vector takes all the elements of the original and rotates them, 
    so the first becomes the last, the second becomes first, and so on.
    
    EX: rotate [1,2,3,4] returns [2,3,4,1]
**/
pub fn rotate(lst: &[i32]) -> Vec<i32> {
    let mut vector = Vec::new();
    if lst.len() == 0 {
        return vector;
    }
    if lst.len() == 1 {
        vector.push(lst[0]);
        return vector;
    } else {
        for &element in lst[1..].iter() {
            vector.push(element);
        }
        vector.push(lst[0]);
        return vector;
    }
}

/**
    Returns true if target is a subtring of s, false otherwise
    You should not use the contains function of the string library in your implementation
    
    Ex: "ace" is a substring of "rustacean"
**/
pub fn substr(s: &String, target: &str) -> bool {
    let mut vector = Vec::new();
    let mut vector2 = Vec::new();
    if s == target {
        return true;
    }
    if s.len() == 0 {
        return false;
    }
    for c in s.chars() {
        vector.push(c);
    }
    for c in target.chars() {
        vector2.push(c);
    }
    for i in 0..vector.len() {
        if ((i + vector2.len()) > vector.len()) {
            return false;
        } 
        if vector2 == vector[i..(vector2.len() + i)] {
              return true;
        }
    }
    return false;
}

/**
    Takes a string and returns the first longest substring of consecutive equal characters

    EX: longest_sequence of "ababbba" is Some("bbb")
    EX: longest_sequence of "aaabbb" is Some("aaa")
    EX: longest_sequence of "xyz" is Some("x")
    EX: longest_sequence of "" is None
**/
pub fn longest_sequence(s: &str) -> Option<&str> {
    if s.len() == 0 {
        return None;
    } else {
        let mut i = 0;
        let mut vector = Vec::new();
        for c in s.chars() {
            vector.push(c);
        }
        let mut curr = vector[0];
        let mut length = 0;
        let mut maxlength = 0;
        let mut start = 0;
        let mut maxstart = 0;
        while i < vector.len() {
            if vector[i] == curr {
                length += 1;
                if length > maxlength {
                    maxlength = length;
                    maxstart = start;
                }
            } else {
                curr = vector[i];
                start = i;
                length = 1;
            }
            i += 1;
        }
        return Some(&s[maxstart..maxstart + maxlength]);
    }
}
