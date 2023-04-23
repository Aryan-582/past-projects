class GameBoard
    # @max_row is an `Integer`
    # @max_column is an `Integer`
    attr_reader :max_row, :max_column

    def initialize(max_row, max_column)
        @max_row = max_row
        @max_column = max_column
        @board = Array.new(max_row){Array.new(max_column){Array.new(2, "-")}}
    end

    # adds a Ship object to the GameBoard
    # returns Boolean
    # Returns true on successfully added the ship, false otherwise
    # Note that Position pair starts from 1 to max_row/max_column
    def add_ship(ship)
        if ship.start_position.row > max_row || ship.start_position.row < 1 || ship.start_position.column > max_column || ship.start_position.column < 1
            return false
        else
            i = 0
            while i < ship.size
                if ship.orientation == "Up"
                    if ship.start_position.row - i < 1 || @board[ship.start_position.row - i - 1][ship.start_position.column - 1][0] == "B"
                        return false
                    end 
                elsif ship.orientation == "Down"
                    if ship.start_position.row + i > max_row || @board[ship.start_position.row + i - 1][ship.start_position.column - 1][0] == "B"
                        return false
                    end
                elsif ship.orientation == "Right"
                    if ship.start_position.column + i > max_column || @board[ship.start_position.row - 1][ship.start_position.column + i - 1][0] == "B"
                        return false
                    end
                else
                    if ship.start_position.column - i < 1 || @board[ship.start_position.row - 1][ship.start_position.column - i - 1][0] == "B"
                        return false
                    end
                end
                i += 1
            end   
            i = 0
            while i < ship.size
                if ship.orientation == "Up"
                    @board[ship.start_position.row - i - 1][ship.start_position.column - 1][0] = "B"
                elsif ship.orientation == "Down"
                    @board[ship.start_position.row + i - 1][ship.start_position.column - 1][0] = "B"
                elsif ship.orientation == "Right"
                    @board[ship.start_position.row - 1][ship.start_position.column + i - 1][0] = "B"
                else 
                    @board[ship.start_position.row - 1][ship.start_position.column - i - 1][0] = "B"
                end
                i += 1
            end
            return true
        end
    end

    # return Boolean on whether attack was successful or not (hit a ship?)
    # return nil if Position is invalid (out of the boundary defined)
    def attack_pos(position)
        # check position
        if position.row > max_row || position.row < 1 || position.column > max_column || position.column < 1
            return nil
        end
        # update your grid
        check = 0
        if @board[position.row - 1][position.column - 1][0] == "B"
            check = 1
        end
        @board[position.row - 1][position.column - 1][1] = "A"
        # return whether the attack was successful or not
        if check == 1
            return true
        else 
            return false
        end
    end

    # Number of successful attacks made by the "opponent" on this player GameBoard
    def num_successful_attacks
        i = 0
        j = 0
        attacks = 0
        for i in 0..max_row - 1
            for j in 0..max_column - 1
                if @board[i][j][0] == "B"
                    if @board[i][j][1] == "A"
                        attacks += 1 
                    end
                end
            end
        end
        return attacks
    end 
    # returns Boolean
    # returns True if all the ships are sunk.
    # Return false if at least one ship hasn't sunk.
    def all_sunk?
        for i in 0..max_row - 1
            for j in 0..max_column - 1
                if @board[i][j][0] == "B"
                    if @board[i][j][1] != "A"
                        return false
                    end
                end
            end
        end
        return true
    end


    # String representation of GameBoard (optional but recommended)
    def to_s
        for i in 0..max_row
            p @board[i]
        end
    end
end
