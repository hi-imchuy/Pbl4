from rooms.RoomManager import RoomManager

manager = RoomManager()
idrooms = manager.create_player_vs_player_room("id1", "id2")
legalmove = manager.get_pvp_room(idrooms).get_legal_moves()
print(str(legalmove))