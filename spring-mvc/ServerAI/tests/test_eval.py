import chess


from ai.evaluation import evaluate_board

board = chess.Board()

# Thực hiện một số nước đi để có một tình huống trên bàn cờ
board.push_uci("e2e4")
board.push_uci("e7e5")
board.push_uci("g1f3")
board.push_uci("b8c6")
board.push_uci("d2d4")
board.push_uci("f8b4")
board.push_uci("c2c3")
board.push_uci("b4d6")
board.push_uci("f1d3")
board.push_uci("g8e7")
board.push_uci("d4d5")


