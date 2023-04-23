#! /usr/bin/env python3

import sys
import random


with open(sys.argv[1], 'rb') as in_file:
#with open('other3.in', 'rb') as in_file:
    #data = in_file.read(162)             # balance length
    #data2 = in_file.read(80)            # transfer length
    #data16 = in_file.read(64)            # invoice length
    data = in_file.read()
    length = len(data)
    hexdata = data.hex()              # hexdata is a string of hex digits
    bindata = bytes.fromhex(hexdata)  # bindata should be the same as data

balfound = 0
transfound = 0
invfound = 0
balancecipher = "" 
transcipher = ""
invcipher = ""
msglist = []
def initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist):
    balfound = 0
    transfound = 0
    invfound = 0
    balancecipher = "" 
    transcipher = ""
    invcipher = ""
    msglist.clear()
def parse_fooABC(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist):
    if len(data) == 0:
        return 1
    if balfound == 0:
        balancecipher = data[0:16]
        balfound = 1
        msglist.append("BALANCE")
        return parse_fooABC(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif balfound == 1 and data[0:16] == balancecipher:
        msglist.append("BALANCE")
        return parse_fooABC(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif balfound == 1 and data[0:16] != balancecipher:
        if transfound == 0:
            transcipher = data[0:16]
            transfound = 1
            msglist.append("TRANSFER")
            return parse_fooABC(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif transfound == 1 and data[0:16] == transcipher:
            msglist.append("TRANSFER")
            return parse_fooABC(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif transfound == 1 and data[0:16] != transcipher:
            if invfound == 0:
                invcipher = data[0:16]
                invfound = 1
                msglist.append("INVOICE")
                return parse_fooABC(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            elif invfound == 1 and data[0:16] == invcipher:
                msglist.append("INVOICE")
                return parse_fooABC(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            else:
                return 0
def parse_fooACB(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist):
    if len(data) == 0:
        return 1
    if balfound == 0:
        balancecipher = data[0:16]
        balfound = 1
        msglist.append("BALANCE")
        return parse_fooACB(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif balfound == 1 and data[0:16] == balancecipher:
        msglist.append("BALANCE")
        return parse_fooACB(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif balfound == 1 and data[0:16] != balancecipher:
        if invfound == 0:
            invcipher = data[0:16]
            invfound = 1
            msglist.append("INVOICE")
            return parse_fooACB(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif invfound == 1 and data[0:16] == invcipher:
                msglist.append("INVOICE")
                return parse_fooACB(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif invfound == 1 and data[0:16] != invcipher:
            if transfound == 0:
                transcipher = data[0:16]
                transfound = 1
                msglist.append("TRANSFER")
                return parse_fooACB(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            elif transfound == 1 and data[0:16] == transcipher:
                msglist.append("TRANSFER")
                return parse_fooACB(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            else:
                return 0
def parse_fooBAC(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist):
    if len(data) == 0:
        return 1
    if transfound == 0:
        transcipher = data[0:16]
        transfound = 1
        msglist.append("TRANSFER")
        return parse_fooBAC(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif transfound == 1 and data[0:16] == transcipher:
        msglist.append("TRANSFER")
        return parse_fooBAC(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif transfound == 1 and data[0:16] != transcipher:
        if balfound == 0:
            balancecipher = data[0:16]
            balfound = 1
            msglist.append("BALANCE")
            return parse_fooBAC(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif balfound == 1 and data[0:16] == balancecipher:
                msglist.append("BALANCE")
                return parse_fooBAC(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif balfound == 1 and data[0:16] != balancecipher:
            if invfound == 0:
                invcipher = data[0:16]
                invfound = 1
                msglist.append("INVOICE")
                return parse_fooBAC(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            elif invfound == 1 and data[0:16] == invcipher:
                msglist.append("INVOICE")
                return parse_fooBAC(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            else:
                return 0     
def parse_fooBCA(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist):
    if len(data) == 0:
        return 1
    if transfound == 0:
        transcipher = data[0:16]
        transfound = 1
        msglist.append("TRANSFER")
        return parse_fooBCA(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif transfound == 1 and data[0:16] == transcipher:
        msglist.append("TRANSFER")
        return parse_fooBCA(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif transfound == 1 and data[0:16] != transcipher:
        if invfound == 0:
            invcipher = data[0:16]
            invfound = 1
            msglist.append("INVOICE")
            return parse_fooBCA(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif invfound == 1 and data[0:16] == invcipher:
                msglist.append("INVOICE")
                return parse_fooBCA(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif invfound == 1 and data[0:16] != invcipher:
            if balfound == 0:
                balancecipher = data[0:16]
                balfound = 1
                msglist.append("BALANCE")
                return parse_fooBCA(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            elif balfound == 1 and data[0:16] == balancecipher:
                msglist.append("BALANCE")
                return parse_fooBCA(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            else:
                return 0   
def parse_fooCAB(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist):
    if len(data) == 0:
        return 1
    if invfound == 0:
        invcipher = data[0:16]
        invfound = 1
        msglist.append("INVOICE")
        return parse_fooCAB(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif invfound == 1 and data[0:16] == invcipher:
        msglist.append("INVOICE")
        return parse_fooCAB(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif invfound == 1 and data[0:16] != invcipher:
        if balfound == 0:
            balancecipher = data[0:16]
            balfound = 1
            msglist.append("BALANCE")
            return parse_fooCAB(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif balfound == 1 and data[0:16] == balancecipher:
                msglist.append("BALANCE")
                return parse_fooCAB(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif balfound == 1 and data[0:16] != balancecipher:
            if transfound == 0:
                transcipher = data[0:16]
                transfound = 1
                msglist.append("TRANSFER")
                return parse_fooCAB(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            elif transfound == 1 and data[0:16] == transcipher:
                msglist.append("TRANSFER")
                return parse_fooCAB(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            else:
                return 0
def parse_fooCBA(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist):
    if len(data) == 0:
        return 1
    if invfound == 0:
        invcipher = data[0:16]
        invfound = 1
        msglist.append("INVOICE")
        return parse_fooCBA(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif invfound == 1 and data[0:16] == invcipher:
        msglist.append("INVOICE")
        return parse_fooCBA(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
    elif invfound == 1 and data[0:16] != invcipher:
        if transfound == 0:
            transcipher = data[0:16]
            transfound = 1
            msglist.append("TRANSFER")
            return parse_fooCBA(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif transfound == 1 and data[0:16] == transcipher:
                msglist.append("TRANSFER")
                return parse_fooCBA(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
        elif transfound == 1 and data[0:16] != transcipher:
            if balfound == 0:
                balancecipher = data[0:16]
                balfound = 1
                msglist.append("BALANCE")
                return parse_fooCBA(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            elif balfound == 1 and data[0:16] == balancecipher:
                msglist.append("BALANCE")
                return parse_fooCBA(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
            else:
                return 0
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist) 
found = 0                                                                             
if (parse_fooABC(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist) == 1 and found == 0):
    finallist = msglist.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
if (parse_fooACB(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist) == 1 and found == 0):
    finallist = msglist.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
if (parse_fooBAC(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist) == 1 and found == 0):
    finallist = msglist.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
if (parse_fooBCA(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist) == 1 and found == 0):
    finallist = msglist.copy()
    found = 1  
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist) 
if (parse_fooCAB(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist) == 1 and found == 0):
    finallist = msglist.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)
if (parse_fooCBA(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist) == 1 and found == 0):
    finallist = msglist.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist)         
for elem in finallist:
    print(elem)
