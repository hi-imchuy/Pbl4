import chess

from ai.search import best_move, best_move_stockfish


class ChessRoom:
    def __init__(self, player1_id, player2_id):
        self.player1_id = player1_id
        self.player2_id = player2_id
        self.board = self.initialize_board()
        self.game_history = []

    def initialize_board(self):
        # Khởi tạo bàn cờ mới sử dụng thư viện python-chess.
        return chess.Board()

    def make_move(self, move):
        """
        Cập nhật trạng thái bàn cờ dựa trên nước đi của một người chơi.

        Args:
        - player_id (str): ID của người chơi thực hiện nước đi.
        - move (str): Nước đi cần thực hiện dưới dạng chuỗi, ví dụ: "e2e4".
        """
        # Chuyển chuỗi move thành một đối tượng Move và thực hiện nước đi trên bàn cờ
        move_obj = chess.Move.from_uci(move)
        if move_obj in self.board.legal_moves:
            self.board.push(move_obj)
            self.game_history.append(move)
        else:
            # Nước đi không hợp lệ

            print(f"Invalid move {move} by player")

    def is_game_over(self):
        # Kiểm tra xem trò chơi đã kết thúc chưa
        return self.board.is_game_over()

    def __str__(self):
        return f"Room between {self.player1_id} and {self.player2_id}"

    # lấy danh sách các nước đi hợp lệ cho mỗi quân cờ còn lại của bên đi trên bàn cờ
    def get_legal_moves(self):
        """
        Lấy danh sách các bước đi hợp lệ cho mỗi quân cờ trên bàn.

        Returns:
        - dict: Một từ điển ánh xạ từ mỗi quân cờ đến danh sách các bước đi hợp lệ từ vị trí của nó.
        """
        legal_moves_dict = {}

        for square in chess.SQUARES:
            # Kiểm tra xem có quân cờ ở ô này không
            piece = self.board.piece_at(square)
            if piece:
                # Lấy tên của quân cờ và vị trí hiện tại của nó
                piece_name = piece.symbol()
                square_name = chess.square_name(square)

                legal_moves_dict[piece_name + "@" + square_name] = []

        for move in self.board.legal_moves:
            # Biến đổi đối tượng Move thành chuỗi (ví dụ: "e2e4")
            move_str = move.uci()

            # Lấy vị trí nguồn của nước đi (ví dụ: "e2")
            source_square = move_str[:2]
            piece_at_source = self.board.piece_at(chess.parse_square(source_square))
            key = piece_at_source.symbol() + "@" + source_square

            # Thêm nước đi vào danh sách nước đi cho vị trí nguồn tương ứng
            legal_moves_dict[key].append(move_str)

        # Xóa những quân cờ không có nước đi nào từ kết quả
        return {k: v for k, v in legal_moves_dict.items() if v}

    def promote_pawn(self, move_uci, promotion=chess.QUEEN):
        """
        Thực hiện việc phong quân.

        Args:
        - move_uci (str): Nước đi ở định dạng UCI.
        - promotion (chess.PieceType): Loại quân cờ bạn muốn phong.

        Returns:
        - bool: Trả về True nếu việc phong quân thành công, ngược lại trả về False.
        """
        move = chess.Move.from_uci(move_uci + promotion.upper())
        if move in self.board.legal_moves:
            self.board.push(move)
            return True
        return False

    def get_board_string(self):
        """
        Trả về bàn cờ dưới dạng chuỗi, mỗi hàng của bàn cờ được cách nhau bởi dấu '?'

        Returns:
            str: Chuỗi biểu diễn trạng thái bàn cờ.
        """
        board_str = str(self.board)
        # Chuyển đổi từ chuỗi đa dòng thành chuỗi đơn dòng với dấu '?' phân cách các hàng
        return board_str.replace('\n', '?')


class PlayerVsPlayerRoom(ChessRoom):
    def __init__(self, player1_id, player2_id):
        super().__init__(player1_id, player2_id)


class PlayerVsComputerRoom(ChessRoom):
    def __init__(self, player_id):
        super().__init__(player_id, "computer")

    def computer_move(self):
        best_move = self.search(self.board)
        # self.board.push(best_move)
        self.make_move(best_move.uci())
        print(self.board)
        return best_move.uci()

    def search(self, board):
        return best_move_stockfish(board, depth=8)
