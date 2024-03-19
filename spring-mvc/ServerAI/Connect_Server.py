import socket
import struct

# Thông số kết nối
HOST = 'localhost'  # Địa chỉ IP của máy chủ
PORT = 8888  # Cổng kết nối giống như trên server Java


def send_utf8_string(s, message):
    """Gửi chuỗi UTF-8 tới server theo định dạng của DataOutputStream.writeUTF()."""
    encoded_message = message.encode('utf-8')
    length = len(encoded_message)
    s.sendall(struct.pack('!H', length))
    s.sendall(encoded_message)


def recv_utf8_string(s):
    """Nhận chuỗi UTF-8 từ server theo định dạng của DataInputStream.readUTF()."""
    data_length_bytes = s.recv(2)  # Nhận hai byte đầu tiên biểu diễn độ dài của chuỗi
    data_length = struct.unpack('!H', data_length_bytes)[0]
    data = s.recv(data_length)
    return data.decode('utf-8')


# Tạo socket và kết nối tới server
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))

    while True:
        # Nhập và gửi dữ lieu tới server
        message = input("Enter your message to the server: ")
        if message.lower() == "exit":
            break

        send_utf8_string(s, message)

        # Nhận phản hồi từ server
        response = recv_utf8_string(s).strip("'")  # Dùng .strip() để loại bỏ dấu nháy đơn ở đầu và cuối
        print('Server:', response)
