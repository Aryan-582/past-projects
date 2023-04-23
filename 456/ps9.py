import sys
import random
import numpy as np
import math

i=10000
zerocount = 0
onecount = 0
while i != 0:
    q = 6277
    n = 10
    b = 0
    posA = 22
    negativeA = -22
    A = np.random.randint(q, size=(n,n))
    e = np.random.randint(negativeA, posA + 1, size=(n))
    f = np.random.randint(negativeA, posA + 1, size=(n))
    r = np.random.randint(negativeA, posA + 1, size=(n))
    s = np.random.randint(negativeA, posA + 1, size=(n))
    x = random.randint(negativeA, posA)
    k1 = b * math.floor(q/2)
    k2 = np.subtract(np.add(x, (np.matmul(r,e))), np.matmul(f,s))
    k = np.add(k1,k2)
    k = k % q
    if k < math.floor(q/4): 
        print(0) 
        zerocount = zerocount + 1
    elif math.floor(q/4) <= k and k <= math.floor(3*q/4):
        print(1)
        onecount = onecount + 1
    else:
        print(0)
        zerocount = zerocount + 1
    i = i - 1
print("Ones:", onecount)
print("Zeros:", zerocount)

