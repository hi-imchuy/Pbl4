import chess
import chess.engine


def analyze_game(moves, engine_path="C:\\stockfish\\stockfish-windows-x86-64-avx2.exe"):
    with chess.engine.SimpleEngine.popen_uci(engine_path) as engine:
        board = chess.Board()
        analysis_results = []

        for move_san in moves:
            move = chess.Move.from_uci(move_san)
            if move not in board.legal_moves:
                analysis_results.append({
                    "move": move_san,
                    "quality": "INVALID",
                    "optimal_move": None
                })
                continue

            # Lưu lại điểm số trước nước đi
            info_before = engine.analyse(board, chess.engine.Limit(time=0.1))
            score_before = info_before["score"].white().score() if board.turn == chess.WHITE else info_before[
                "score"].black().score()

            # Thực hiện nước đi
            board.push(move)

            # Đánh giá lại sau nước đi
            info_after = engine.analyse(board, chess.engine.Limit(time=0.1))
            score_after = info_after["score"].white().score() if board.turn == chess.BLACK else info_after[
                "score"].black().score()

            # So sánh điểm số để đánh giá chất lượng nước đi
            quality = "NEUTRAL"

            if score_before is not None and score_after is not None:
                score_after = abs(score_after)
                score_before = abs(score_before)
                # print(score_before, " and ", score_after)
                score_change = score_after - score_before
                if score_change > 5:
                    quality = "GOOD"
                elif score_change < -30:
                    quality = "BAD"

            # Tìm nước đi tối ưu tiếp theo
            optimal_move = None
            try:
                optimal_move = engine.play(board, chess.engine.Limit(time=0.1)).move
            except Exception as e:
                print(f"Không thể tìm nước đi tối ưu: {e}")
            analysis_results.append({
                "move": move_san,
                "quality": quality,
                "optimal_move": optimal_move.uci() if optimal_move else None
            })

        return analysis_results


# Phân tích các nước đi
# import re
#
# data = "Analysis [e2e4, c7c5, g1f3, d7d6, d2d4, c5d4, f3d4, g8f6, b1c3, a7a6, c1g5, e7e6, f2f4, b7b5, e4e5, d6e5,
# " \ "f4e5, d8c7, d1e2, f6d7, e1c1, c8b7, d4e6, f7e6, e2g4, c7b6, d1d6, f8d6, g4e6, e8f8, f1c4, b5c4, h1f1, d7f6,
# " \ "f1f6, g7f6, g5h6]" game_moves = re.findall(r'\b([a-h][1-8][a-h][1-8])\b', data) analysis_results =
# analyze_game(game_moves, "C:\\stockfish\\stockfish-windows-x86-64-avx2.exe") for result in analysis_results: print(
# result)
