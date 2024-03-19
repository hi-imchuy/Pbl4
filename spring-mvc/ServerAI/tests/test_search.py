import chess

from ai.search import best_move, best_move_stockfish

# Tạo một bàn cờ mới
board = chess.Board()

# Thực hiện một số nước đi để có một tình huống trên bàn cờ
board.push_uci("e2e4")
board.push_uci("e7e5")
board.push_uci("g1f3")
board.push_uci("b8c6")
board.push_uci("d2d4")
board.push_uci("f8b4")
board.push_uci("c2c3")

# In bàn cờ hiện tại
print(board)

# Phân tích nước đi "d2d4" từ bàn cờ hiện tại

best = best_move_stockfish(board, 10)
print(f"Best move: {best}")
