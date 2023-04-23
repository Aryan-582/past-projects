
import sys
with open('test.in', 'rb') as in_file:
    data = in_file.read()
with open('testaryan.in', 'wb') as f:    
    f.write(data[0:208])
    