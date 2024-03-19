import chess
import chess.engine

from ai.evaluation import evaluate_board


def evaluate_move(board, next_move_uci):
    """
    Args:
        board (chess.Board): The current board state.
        next_move_uci (str): The move to analyze in UCI format.

    Returns:
        str: Verbal evaluation of the move.
        dict: Winning percentages for each side.
    """
    with chess.engine.SimpleEngine.popen_uci("C:\\stockfish\\stockfish-windows-x86-64-avx2.exe") as engine:
        # Make sure that the move is legal
        next_move = chess.Move.from_uci(next_move_uci)
        if next_move not in board.legal_moves:
            raise ValueError("The provided move is not legal.")

        # Analyzing the move
        board.push(next_move)
        # info = engine.analyse(board, chess.engine.Limit(time= 1.0))
        score = evaluate_board(board)
        board.pop()
        print(f'score = ', score)
        # Extracting score from the analysis

        # if "score" in info and info["score"].relative is not None:
        #     score = -info["score"].relative.score()

        # Mapping score to verbal evaluation
        if score is None:
            verbal_evaluation = "No evaluation"
        elif score > 300:
            verbal_evaluation = "Brilliant"
        elif score > 100:
            verbal_evaluation = "Great Move"
        elif score > 50:
            verbal_evaluation = "Good"
        elif score > 20:
            verbal_evaluation = "Inaccuracy"
        elif score > -50:
            verbal_evaluation = "Mistake"
        elif score > -100:
            verbal_evaluation = "Miss"
        else:
            verbal_evaluation = "Blunder"

        # Estimating winning chances based on the score
        if score is not None:
            win_chance_white = 1 / (1 + 10 ** (-score / 400))
            win_chance_black = 1 - win_chance_white
        else:
            win_chance_white = win_chance_black = 0.5  # Even chances if no score is available

        win_chances = {
            "white": win_chance_white,
            "black": win_chance_black
        }

        return verbal_evaluation, win_chances
