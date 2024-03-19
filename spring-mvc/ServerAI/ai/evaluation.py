import chess.engine


def evaluate_board(board, engine_path="C:\\stockfish\\stockfish-windows-x86-64-avx2.exe", time_limit=0.01):
    engine = chess.engine.SimpleEngine.popen_uci(engine_path)
    info = engine.analyse(board, chess.engine.Limit(time=time_limit))
    engine.quit()
    score = info["score"].relative.score()
    return score









# import chess
#
# PIECE_VALUES = {
#     chess.PAWN: 10,
#     chess.KNIGHT: 30,
#     chess.BISHOP: 30,
#     chess.ROOK: 50,
#     chess.QUEEN: 90,
#     chess.KING: 900,
# }
#
#
# PAWN_POSITIONS = [
#     [0, 0, 0, 0, 0, 0, 0, 0],
#     [5, 10, 10, -20, -20, 10, 10, 5],
#     [5, -5, -10, 0, 0, -10, -5, 5],
#     [0, 0, 0, 20, 20, 0, 0, 0],
#     [5, 5, 10, 25, 25, 10, 5, 5],
#     [10, 10, 20, 30, 30, 20, 10, 10],
#     [50, 50, 50, 50, 50, 50, 50, 50],
#     [0, 0, 0, 0, 0, 0, 0, 0]
# ]
#
#
#
#
# def evaluate_board(board):
#     """
#     Đánh giá bàn cờ dựa trên giá trị của các quân cờ và vị trí của chúng.
#     """
#     evaluation = 0
#
#     # Thêm giá trị của từng quân cờ và giá trị vị trí của nó vào đánh giá tổng cộng
#     for square in chess.SQUARES:
#         piece = board.piece_at(square)
#         if piece is not None:
#             # Thêm giá trị của quân cờ
#             piece_value = PIECE_VALUES[piece.piece_type]
#             if piece.color == chess.WHITE:
#                 evaluation += piece_value
#             else:
#                 evaluation -= piece_value
#
#             # Thêm giá trị vị trí của quân cờ
#             # Ví dụ: Với pawn, chúng ta sử dụng bảng PAWN_POSITIONS
#             if piece.piece_type == chess.PAWN:
#                 if piece.color == chess.WHITE:
#                     evaluation += PAWN_POSITIONS[square // 8][square % 8]
#                 else:  # Đảo ngược bảng cho quân đen
#                     evaluation -= PAWN_POSITIONS[7 - square // 8][square % 8]
#             # Tương tự cho các quân cờ khác, sử dụng bảng vị trí tương ứng
#
#     return evaluation