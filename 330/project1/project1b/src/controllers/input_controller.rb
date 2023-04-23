require_relative '../models/game_board'
require_relative '../models/ship'
require_relative '../models/position'

# return a populated GameBoard or nil
# Return nil on any error (validation error or file opening error)
# If 5 valid ships added, return GameBoard; return nil otherwise
def read_ships_file(path)
    game = GameBoard.new 10, 10
    array = Array.new
    i = 0
    read_file_lines(path) { |x| array = x.scan(/\w+/); if i != 5 then if game.add_ship(Ship.new((Position.new(array[0].to_i, array[1].to_i)), array[2], array[3].to_i)) == true then i += 1 end end}
    if i == 5 
        return game
    else 
        return nil
    end
end


# return Array of Position or nil
# Returns nil on file open error
def read_attacks_file(path)
    s = [Position.new(1, 1)]
    array = Array.new
    positions = Array.new
    i = 0
    if (read_file_lines(path) { |x| array = x.scan(/\d+/); if array[0] != nil && array[1] != nil then positions[i] = Position.new(array[0].to_i, array[1].to_i); i += 1 end} == false) 
        return nil  
    end
    return positions
end


# ===========================================
# =====DON'T modify the following code=======
# ===========================================
# Use this code for reading files
# Pass a code block that would accept a file line
# and does something with it
# Returns True on successfully opening the file
# Returns False if file doesn't exist
def read_file_lines(path)
    return false unless File.exist? path
    if block_given?
        File.open(path).each do |line|
            yield line
        end
    end

    true
end
