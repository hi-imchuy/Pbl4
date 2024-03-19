import hashlib
import uuid
from typing import Dict, Optional

from rooms.ChessRoom import PlayerVsPlayerRoom, PlayerVsComputerRoom


class RoomManager:
    def __init__(self):
        # Danh sách các phòng PvP (player vs player)
        self.pvp_rooms: Dict[str, PlayerVsPlayerRoom] = {}
        # Danh sách các phòng PvC (player vs computer)
        self.pvc_rooms: Dict[str, PlayerVsComputerRoom] = {}

    def create_unique_room_id(player1_id: str, player2_id: str) -> str:
        # Sắp xếp hai ID và kết hợp chúng
        combined_id = ''.join(sorted([player1_id, player2_id]))

        # Tạo một mã băm SHA256 dựa trên combined_id
        return hashlib.sha256(combined_id.encode()).hexdigest()

    def get_board_string_for_room(self, room_id: str) -> Optional[str]:
        # Kiểm tra phòng PvP
        if room_id in self.pvp_rooms:
            return self.pvp_rooms[room_id].get_board_string()

        # Kiểm tra phòng PvC
        if room_id in self.pvc_rooms:
            return self.pvc_rooms[room_id].get_board_string()

        # Trường hợp phòng không tồn tại
        return None

    def create_player_vs_player_room(self, player1_id: str, player2_id: str) -> str:
        room_id = str(uuid.uuid4())
        self.pvp_rooms[room_id] = PlayerVsPlayerRoom(player1_id, player2_id)
        return room_id

    def create_player_vs_computer_room(self, player_id, room_id):
        self.pvc_rooms[room_id] = PlayerVsComputerRoom(player_id)


    def get_pvp_room(self, room_id: str) -> Optional[PlayerVsPlayerRoom]:
        return self.pvp_rooms.get(room_id)

    def get_pvc_room(self, room_id: str) -> Optional[PlayerVsComputerRoom]:
        return self.pvc_rooms.get(room_id)

    def close_pvp_room(self, room_id: str) -> bool:
        if room_id in self.pvp_rooms:
            del self.pvp_rooms[room_id]
            return True
        return False

    def close_pvc_room(self, room_id: str) -> bool:
        if room_id in self.pvc_rooms:
            del self.pvc_rooms[room_id]
            return True
        return False

    def list_pvp_rooms(self):
        return list(self.pvp_rooms.keys())

    def list_pvc_rooms(self):
        return list(self.pvc_rooms.keys())
