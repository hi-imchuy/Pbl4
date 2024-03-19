import chess

from ai.analyzer import evaluate_move  # Sử dụng hàm mới đã chỉnh sửa


# Tạo một bàn cờ mới
board = chess.Board()

# Thực hiện một số nước đi để có một tình huống trên bàn cờ
board.push_uci("e2e4")
board.push_uci("e7e5")
board.push_uci("g1f3")
board.push_uci("b8c6")
board.push_uci("d2d4")
board.push_uci("f8b4")

# In bàn cờ hiện tại
print(board)

# Phân tích nước đi "d2d4" từ bàn cờ hiện tại
move_to_analyze = "c2c3"
verbal_evaluation, win_chances = evaluate_move(board.copy(), move_to_analyze)

# In thông tin phân tích
print(f"Nước đi: {move_to_analyze}")
print(f"Đánh giá: {verbal_evaluation}")
print(f"Tỉ lệ thắng của quân trắng: {win_chances['white'] * 100:.2f}%")
print(f"Tỉ lệ thắng của quân đen: {win_chances['black'] * 100:.2f}%")
