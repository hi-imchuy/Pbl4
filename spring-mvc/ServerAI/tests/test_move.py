import chess

# Tạo một bàn cờ mới
board = chess.Board()

# Lấy danh sách các nước đi hợp lệ
legal_moves = list(board.legal_moves)
print(board)
# In ra danh sách các nước đi hợp lệ
for move in legal_moves:
    print(move)