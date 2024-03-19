import socket

HOST = 'localhost'
PORT = 8888

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
    client_socket.connect((HOST, PORT))

    # Gửi tin nhắn đến máy chủ Java
    message = "Hello from Python client!\n"
    client_socket.sendall(message.encode())
    print(f"Sent: {message}")

    # Nhận phản hồi từ máy chủ Java
    response = client_socket.recv(1024).decode()
    print(f"Received: {response}")

print("Client finished")
