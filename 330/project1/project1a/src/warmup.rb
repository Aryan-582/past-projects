def fib(n)
    array = Array.new
    x = 0
    if n == 0
        return []
    end
    while x <= n
        if x != 0
            if x == 1
                array.push(0)
            elsif x == 2
                array.push(1)
            else 
                y = array[x - 2]
                z = array[x - 3]
                z += y
                array.push(z)
            end
        end
        x += 1
    end
    return array
end

def isPalindrome(n)
    string = n.to_s
    if string.length == 1
        return true
    end
    letters = string.chars
    x = 0
    y = string.length
    while x < y/2
        if letters[x] != letters[y - x - 1]
            return false
        end
        x += 1
    end
    return true
end

def nthmax(n, a)
    if n >= a.length
        return nil
    else 
        x = a.sort
        return x[x.length - n - 1]
    end    
end

def freq(s)
    if s.length == 0
        return ""
    else 
        counter = Hash.new
        letters = s.chars
        index = 0
        while index < letters.length
            if counter.key?(letters[index]) == false
                counter.store(letters[index], 1)
            else 
                counter[letters[index]] += 1
            end
            index += 1
        end    
        values = counter.values
        return counter.key(nthmax(0, values))
    end        
end

def zipHash(arr1, arr2)
    if arr1.length != arr2.length 
        return nil
    elsif arr1.length == 0 
        return {}
    else 
        zip = Hash.new
        x = 0
        while arr1[x] != nil
            zip.store(arr1[x], arr2[x])
            x += 1
        end
        return zip
    end
end

def hashToArray(hash)
    if hash.length == 0 
        return []
    else 
        keys = hash.keys
        values = hash.values
        pair = Array.new
        x = 0
        while keys[x] != nil
            pair[x] = [keys[x], values[x]] 
            x += 1
        end
        return pair
    end 
end
