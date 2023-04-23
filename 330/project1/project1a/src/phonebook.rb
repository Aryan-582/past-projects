class PhoneBook
    def initialize
        @contact = Hash.new
    end

    def add(name, number, is_listed)
        if @contact.keys.include?(name) == true
            return false
        end 
        if number.length != 12
            return false
        end
        chars = number.chars
        if chars[3] != "-" || chars[7] != "-"
            return false
        end
        x = 0
        while x < @contact.length
            if @contact.values[x].first() == number && @contact.values[x].last() == true
                if is_listed == true
                    return false
                end
            end
            x += 1
        end
        @contact.store(name, [number, is_listed])
        return true
    end

    def lookup(name)
        x = 0
        while x < @contact.length
            if @contact.keys[x] == name
                if @contact.values[x].last() == true
                    return @contact.values[x].first()
                end
            end
            x += 1
        end
        return nil
    end

    def lookupByNum(number)
        x = 0
        while x < @contact.length
            if @contact.values[x].first() == number && @contact.values[x].last() == true
                return @contact.keys[x]
            end
            x += 1
        end
        return nil
    end

    def namesByAc(areacode)
        x = 0
        array = Array.new
        areaChar = areacode.chars
        while x < @contact.length
            y = 0
            z = 0
            while y < 3
                if @contact.values[x].first().chars[y] != areaChar[y]
                    break
                end
                y += 1
                z += 1
            end
            if z == 3
                array.push(@contact.keys[x])
            end
            x += 1
        end
        return array
    end
end
