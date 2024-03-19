import re

# Dữ liệu đầu vào
data = "Add mode=0 1 0 127 []"

# Tách phần không phải nước đi
non_move_parts = data.split()[:5]

# Tách các nước đi sử dụng biểu thức chính quy
moves_str = re.search(r'\[(.*?)\]', data).group(1)
moves = moves_str.split(', ')

# In kết quả
if moves[-1] == '':
    print(non_move_parts, moves)