#! /usr/bin/env python3

import sys
import random

def find_unique(nums_list):
    count = 0
    for i in nums_list :
        if nums_list.count(i) == 1 :
            return count


with open(sys.argv[1], 'rb') as in_file:
#with open('testaryan.in', 'rb') as in_file:
    #data = in_file.read(162)             # balance length
    #data2 = in_file.read(80)            # transfer length
    #data16 = in_file.read(64)            # invoice length
    data = in_file.read()
    length = len(data)
    hexdata = data.hex()              # hexdata is a string of hex digits
    bindata = bytes.fromhex(hexdata)  # bindata should be the same as data
fulldata = data    
balfound = 0
transfound = 0
invfound = 0
balancecipher = "" 
transcipher = ""
invcipher = ""
msglist = []
accounts = []
msgindex = 0
datalist = []
replacetrans = []
def initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans):
    balfound = 0
    transfound = 0
    invfound = 0
    balancecipher = "" 
    transcipher = ""
    invcipher = ""
    msglist.clear()
    accounts.clear()
    msgindex = 0
    datalist.clear()
    replacetrans.clear()
def parse_fooABC(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans):
    if len(data) == 0:
        return 1
    if balfound == 0:
        balancecipher = data[0:16]
        balfound = 1
        msglist.append("BALANCE")
        accounts.append((msgindex, data[16:32]))
        msgindex += 1
        datalist.append(data[0:32])
        return parse_fooABC(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif balfound == 1 and data[0:16] == balancecipher:
        msglist.append("BALANCE")
        accounts.append((msgindex, data[16:32]))
        msgindex += 1
        datalist.append(data[0:32])
        return parse_fooABC(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif balfound == 1 and data[0:16] != balancecipher:
        if transfound == 0:
            transcipher = data[0:16]
            transfound = 1
            msglist.append("TRANSFER")
            accounts.append((msgindex, data[16:32]))
            accounts.append((msgindex, data[48:64]))
            msgindex += 1
            replacetrans.append(data[0:32])
            replacetrans.append(data[64:80])
            datalist.append(data[0:80])
            return parse_fooABC(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif transfound == 1 and data[0:16] == transcipher:
            msglist.append("TRANSFER")
            accounts.append((msgindex, data[16:32]))
            accounts.append((msgindex, data[48:64]))
            msgindex += 1
            datalist.append(data[0:80])
            return parse_fooABC(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif transfound == 1 and data[0:16] != transcipher:
            if invfound == 0:
                invcipher = data[0:16]
                invfound = 1
                accounts.append((msgindex, data[16:32]))
                accounts.append((msgindex, data[32:48]))
                msgindex += 1
                datalist.append(data[0:64])
                msglist.append("INVOICE")
                return parse_fooABC(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            elif invfound == 1 and data[0:16] == invcipher:
                msglist.append("INVOICE")
                accounts.append((msgindex,data[16:32]))
                accounts.append((msgindex, data[32:48]))
                msgindex += 1
                datalist.append(data[0:64])
                return parse_fooABC(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            else:
                return 0
def parse_fooACB(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans):
    if len(data) == 0:
        return 1
    if balfound == 0:
        balancecipher = data[0:16]
        balfound = 1
        accounts.append((msgindex, data[16:32]))
        msglist.append("BALANCE")
        msgindex += 1
        datalist.append(data[0:32])
        return parse_fooACB(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif balfound == 1 and data[0:16] == balancecipher:
        msglist.append("BALANCE")
        accounts.append((msgindex, data[16:32]))
        msgindex += 1
        datalist.append(data[0:32])
        return parse_fooACB(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif balfound == 1 and data[0:16] != balancecipher:
        if invfound == 0:
            invcipher = data[0:16]
            invfound = 1
            msglist.append("INVOICE")
            accounts.append((msgindex, data[16:32]))
            accounts.append((msgindex, data[32:48]))
            msgindex += 1
            datalist.append(data[0:64])
            return parse_fooACB(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif invfound == 1 and data[0:16] == invcipher:
                msglist.append("INVOICE")
                accounts.append((msgindex, data[16:32]))
                accounts.append((msgindex, data[32:48]))
                msgindex += 1
                datalist.append(data[0:64])
                return parse_fooACB(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif invfound == 1 and data[0:16] != invcipher:
            if transfound == 0:
                transcipher = data[0:16]
                transfound = 1
                msglist.append("TRANSFER")
                accounts.append((msgindex, data[16:32]))
                accounts.append((msgindex, data[48:64]))
                msgindex += 1
                replacetrans.append(data[0:32])
                replacetrans.append(data[64:80])
                datalist.append(data[0:80])
                return parse_fooACB(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            elif transfound == 1 and data[0:16] == transcipher:
                msglist.append("TRANSFER")
                accounts.append((msgindex, data[16:32]))
                accounts.append((msgindex, data[48:64]))
                msgindex += 1
                datalist.append(data[0:80])
                return parse_fooACB(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            else:
                return 0
def parse_fooBAC(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans):
    if len(data) == 0:
        return 1
    if transfound == 0:
        transcipher = data[0:16]
        transfound = 1
        msglist.append("TRANSFER")
        accounts.append((msgindex, data[16:32]))
        accounts.append((msgindex, data[48:64]))
        msgindex += 1
        replacetrans.append(data[0:32])
        replacetrans.append(data[64:80])
        datalist.append(data[0:80])
        return parse_fooBAC(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif transfound == 1 and data[0:16] == transcipher:
        msglist.append("TRANSFER")
        accounts.append((msgindex, data[16:32]))
        accounts.append((msgindex, data[48:64]))
        msgindex += 1
        datalist.append(data[0:80])
        return parse_fooBAC(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif transfound == 1 and data[0:16] != transcipher:
        if balfound == 0:
            balancecipher = data[0:16]
            balfound = 1
            accounts.append((msgindex, data[16:32]))
            msglist.append("BALANCE")
            msgindex += 1
            datalist.append(data[0:32])
            return parse_fooBAC(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif balfound == 1 and data[0:16] == balancecipher:
            msglist.append("BALANCE")
            accounts.append((msgindex, data[16:32]))
            msgindex += 1
            datalist.append(data[0:32])
            return parse_fooBAC(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif balfound == 1 and data[0:16] != balancecipher:
            if invfound == 0:
                invcipher = data[0:16]
                invfound = 1
                msglist.append("INVOICE")
                accounts.append((msgindex, data[16:32]))
                accounts.append((msgindex, data[32:48]))
                msgindex += 1
                datalist.append(data[0:64])
                return parse_fooBAC(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            elif invfound == 1 and data[0:16] == invcipher:
                msglist.append("INVOICE")
                accounts.append((msgindex, data[16:32]))
                accounts.append((msgindex, data[32:48]))
                msgindex += 1
                datalist.append(data[0:64])
                return parse_fooBAC(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            else:
                return 0     
def parse_fooBCA(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans):
    if len(data) == 0:
        return 1
    if transfound == 0:
        transcipher = data[0:16]
        transfound = 1
        msglist.append("TRANSFER")
        accounts.append((msgindex, data[16:32]))
        accounts.append((msgindex, data[48:64]))
        msgindex += 1
        replacetrans.append(data[0:32])
        replacetrans.append(data[64:80])
        datalist.append(data[0:80])
        return parse_fooBCA(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif transfound == 1 and data[0:16] == transcipher:
        msglist.append("TRANSFER")
        accounts.append((msgindex, data[16:32]))
        accounts.append((msgindex, data[48:64]))
        msgindex += 1
        datalist.append(data[0:80])
        return parse_fooBCA(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif transfound == 1 and data[0:16] != transcipher:
        if invfound == 0:
            invcipher = data[0:16]
            invfound = 1
            msglist.append("INVOICE")
            accounts.append((msgindex, data[16:32]))
            accounts.append((msgindex, data[32:48]))
            msgindex += 1
            datalist.append(data[0:64])
            return parse_fooBCA(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif invfound == 1 and data[0:16] == invcipher:
                msglist.append("INVOICE")
                accounts.append((msgindex, data[16:32]))
                accounts.append((msgindex, data[32:48]))
                msgindex += 1
                datalist.append(data[0:64])
                return parse_fooBCA(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif invfound == 1 and data[0:16] != invcipher:
            if balfound == 0:
                balancecipher = data[0:16]
                balfound = 1
                accounts.append((msgindex, data[16:32]))
                msgindex += 1
                datalist.append(data[0:32])
                msglist.append("BALANCE")
                return parse_fooBCA(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            elif balfound == 1 and data[0:16] == balancecipher:
                msglist.append("BALANCE")
                accounts.append((msgindex, data[16:32]))
                msgindex += 1
                datalist.append(data[0:32])
                return parse_fooBCA(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            else:
                return 0   
def parse_fooCAB(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans):
    if len(data) == 0:
        return 1
    if invfound == 0:
        invcipher = data[0:16]
        invfound = 1
        msglist.append("INVOICE")
        accounts.append((msgindex, data[16:32]))
        accounts.append((msgindex, data[32:48]))
        msgindex += 1
        datalist.append(data[0:64])
        return parse_fooCAB(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif invfound == 1 and data[0:16] == invcipher:
        msglist.append("INVOICE")
        accounts.append((msgindex, data[16:32]))
        accounts.append((msgindex, data[32:48]))
        msgindex += 1
        datalist.append(data[0:64])
        return parse_fooCAB(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif invfound == 1 and data[0:16] != invcipher:
        if balfound == 0:
            balancecipher = data[0:16]
            balfound = 1
            accounts.append((msgindex, data[16:32]))
            msgindex += 1
            datalist.append(data[0:32])
            msglist.append("BALANCE")
            return parse_fooCAB(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif balfound == 1 and data[0:16] == balancecipher:
                msglist.append("BALANCE")
                accounts.append((msgindex, data[16:32]))
                msgindex += 1
                datalist.append(data[0:32])
                return parse_fooCAB(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif balfound == 1 and data[0:16] != balancecipher:
            if transfound == 0:
                transcipher = data[0:16]
                transfound = 1
                msglist.append("TRANSFER")
                accounts.append((msgindex, data[16:32]))
                accounts.append((msgindex, data[48:64]))
                msgindex += 1
                replacetrans.append(data[0:32])
                replacetrans.append(data[64:80])
                datalist.append(data[0:80])
                return parse_fooCAB(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            elif transfound == 1 and data[0:16] == transcipher:
                msglist.append("TRANSFER")
                accounts.append((msgindex, data[16:32]))
                accounts.append((msgindex, data[48:64]))
                msgindex += 1
                datalist.append(data[0:80])
                return parse_fooCAB(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            else:
                return 0
def parse_fooCBA(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans):
    if len(data) == 0:
        return 1
    if invfound == 0:
        invcipher = data[0:16]
        invfound = 1
        msglist.append("INVOICE")
        accounts.append((msgindex, data[16:32]))
        accounts.append((msgindex, data[32:48]))
        msgindex += 1
        datalist.append(data[0:64])
        return parse_fooCBA(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif invfound == 1 and data[0:16] == invcipher:
        msglist.append("INVOICE")
        accounts.append((msgindex, data[16:32]))
        accounts.append((msgindex, data[32:48]))
        msgindex += 1
        datalist.append(data[0:64])
        return parse_fooCBA(data[64:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
    elif invfound == 1 and data[0:16] != invcipher:
        if transfound == 0:
            transcipher = data[0:16]
            transfound = 1
            msglist.append("TRANSFER")
            accounts.append((msgindex, data[16:32]))
            accounts.append((msgindex, data[48:64]))
            msgindex += 1
            replacetrans.append(data[0:32])
            replacetrans.append(data[64:80])
            datalist.append(data[0:80])
            return parse_fooCBA(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif transfound == 1 and data[0:16] == transcipher:
            msglist.append("TRANSFER")
            accounts.append((msgindex, data[16:32]))
            accounts.append((msgindex, data[48:64]))
            msgindex += 1
            datalist.append(data[0:80])
            return parse_fooCBA(data[80:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
        elif transfound == 1 and data[0:16] != transcipher:
            if balfound == 0:
                balancecipher = data[0:16]
                balfound = 1
                msglist.append("BALANCE")
                accounts.append((msgindex, data[16:32]))
                msgindex += 1
                datalist.append(data[0:32])
                return parse_fooCBA(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            elif balfound == 1 and data[0:16] == balancecipher:
                msglist.append("BALANCE")
                accounts.append((msgindex, data[16:32]))
                msgindex += 1
                datalist.append(data[0:32])
                return parse_fooCBA(data[32:], balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
            else:
                return 0
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans) 
found = 0                                                                             
if (parse_fooABC(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans) == 1 and found == 0):
    finallist = msglist.copy()
    finalmsgs = accounts.copy()
    finaldata = datalist.copy()
    replacefin = replacetrans.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
if (parse_fooACB(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans) == 1 and found == 0):
    finallist = msglist.copy()
    finalmsgs = accounts.copy()
    finaldata = datalist.copy()
    replacefin = replacetrans.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
if (parse_fooBAC(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans) == 1 and found == 0):
    finallist = msglist.copy()
    finalmsgs = accounts.copy()
    finaldata = datalist.copy()
    replacefin = replacetrans.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
if (parse_fooBCA(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans) == 1 and found == 0):
    finallist = msglist.copy()
    finalmsgs = accounts.copy()
    finaldata = datalist.copy()
    replacefin = replacetrans.copy()
    found = 1  
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans) 
if (parse_fooCAB(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans) == 1 and found == 0):
    finallist = msglist.copy()
    finalmsgs = accounts.copy()
    finaldata = datalist.copy()
    replacefin = replacetrans.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)
if (parse_fooCBA(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans) == 1 and found == 0):
    finallist = msglist.copy()
    finalmsgs = accounts.copy()
    finaldata = datalist.copy()
    replacefin = replacetrans.copy()
    found = 1
initialize(data, balfound, transfound, invfound, balancecipher, transcipher, invcipher, msglist, accounts, msgindex, datalist, replacetrans)         
for elem in finallist:
    print(elem)
finalidx = 0
justmsgs = []
for idx, elem in finalmsgs:
    justmsgs.append(elem)
i = 0
for elem in justmsgs:
    if justmsgs.count(elem) == 1:
        break
    else:
        i += 1
try:
    finalidx, acc = finalmsgs[i]
    invoice = finaldata[finalidx]
    replacefin.append(invoice[48:64])
except Exception as e: print("no unique account num")
finalreplace = replacefin[0] + replacefin[2] + acc + replacefin[1]    
with open('task4.out', 'wb') as f:    
   for elem in finaldata:
    if len(elem) == 64 and elem.find(acc) >= 0:
        f.write(finalreplace)
    else:
        f.write(elem)
    

