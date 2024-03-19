import chess
import chess.polyglot
import chess.engine
from ai.evaluation import evaluate_board


def order_moves(board, legal_moves):
    # [Optional] Add your move ordering logic here to rearrange `legal_moves`
    pass


def quiescence_search(board, alpha, beta):
    stand_pat = evaluate_board(board)
    if stand_pat >= beta:
        return beta
    if alpha < stand_pat:
        alpha = stand_pat

    for move in board.legal_moves:
        if board.is_capture(move):
            board.push(move)
            score = -quiescence_search(board, -beta, -alpha)
            board.pop()

            if score >= beta:
                return beta
            if score > alpha:
                alpha = score
    return alpha


def minimax(board, depth, alpha, beta, maximizing_player, transposition_table):
    if depth == 0 or board.is_game_over():
        return quiescence_search(board, alpha, beta), None  # Use quiescence_search here

    zobrist_hash = chess.polyglot.zobrist_hash(board)
    if zobrist_hash in transposition_table:
        return transposition_table[zobrist_hash], None

    legal_moves = list(board.legal_moves)
    order_moves(board, legal_moves)  # Order moves

    best_move = None

    if maximizing_player:
        max_eval = float('-inf')
        for move in legal_moves:
            board.push(move)
            evaluate, _ = minimax(board, depth - 1, alpha, beta, False, transposition_table)
            board.pop()

            if evaluate > max_eval:
                max_eval = evaluate
                best_move = move

            alpha = max(alpha, evaluate)
            if beta <= alpha:
                break

        transposition_table[zobrist_hash] = max_eval
        return max_eval, best_move

    else:
        min_eval = float('inf')
        for move in legal_moves:
            board.push(move)
            evaluate, _ = minimax(board, depth - 1, alpha, beta, True, transposition_table)
            board.pop()

            if evaluate < min_eval:
                min_eval = evaluate
                best_move = move

            beta = min(beta, evaluate)
            if beta <= alpha:
                break

        transposition_table[zobrist_hash] = min_eval
        return min_eval, best_move


def best_move(board, depth):
    transposition_table = {}
    _, move = minimax(board, depth, float('-inf'), float('inf'), board.turn, transposition_table)
    return move


def best_move_stockfish(board, depth):
    engine_path = "C:\\stockfish\\stockfish-windows-x86-64-avx2.exe"

    with chess.engine.SimpleEngine.popen_uci(engine_path) as engine:
        result = engine.play(board, chess.engine.Limit(depth=depth))
        print(result)
        best_move = result.move

    return best_move
