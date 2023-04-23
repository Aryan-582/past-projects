require "minitest/autorun"
require_relative "../../src/controllers/input_controller.rb"
require_relative "../../src/controllers/game_controller.rb"
require_relative "../../src/models/game_board.rb"
require_relative "../../src/models/position.rb"
require_relative "../../src/models/ship.rb"

# The ship coordinates for p1, p2
SHIPS_P1 = "#{__dir__}/inputs/correct_ships_p1.txt"
SHIPS_P2 = "#{__dir__}/inputs/correct_ships_p2.txt"

# The attack coordinates against p1, p2
ATTACK_P1 = "#{__dir__}/inputs/correct_strat_p1.txt"
ATTACK_P2 = "#{__dir__}/inputs/correct_strat_p2.txt"

# The perfect attack coordinates against p1, p2
PERF_ATK_P1 = "#{__dir__}/inputs/perfect_strat_p1.txt"
PERF_ATK_P2 = "#{__dir__}/inputs/perfect_strat_p2.txt"

# A bad ships file
BAD_SHIPS = "#{__dir__}/inputs/bad_ships.txt"

class StudentTests 
    @p1_ships = []
        @p1_perf_atk = []
        @p2_ships = []
        @p2_perf_atk = []
        for i, size in [1,2,3,4].zip([4,5,3,2])
            pos0 = Position.new(i, i)
            pos1 = Position.new(i + 4, i + 4)
            @p1_ships << Ship.new(pos0, "Right", size)
            @p2_ships << Ship.new(pos1, "Right", size)
            for j in 0..(size - 1)
                @p2_perf_atk << Position.new(i, i + j)
                @p1_perf_atk << Position.new(i + 4, i + j + 4)
            end
        end

    
    test_board = GameBoard.new 10, 10
    for shp in @p1_ships[1..] 
        test_board.add_ship(shp)
    end
    test_board.attack_pos(Position.new(2, 2))
    puts test_board.add_ship(@p1_ships[1])
    test_board.to_s
    s = "(2,5), Right, 3"
    ss = "(3,2)"
    first, second, direction, third = s.scan(/\w+/)[-4,4] 
    list = s.scan(/\w+/) 
    puts list[0].to_i
    board_p1 = read_ships_file(SHIPS_P1)
    puts board_p1
    list2 = ss.scan(/\d+/)
    puts list2
    if 1.is_a?(Integer) == true
        puts "shake"
    end
end
