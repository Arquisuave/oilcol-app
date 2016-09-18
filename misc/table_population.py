import os
import json
import requests
import localtime

for i in range(1, 91):
    user = {'username':'example%d@example.com' % (i), 'password':"1234567", 'type':'JEFE_CAMPO'}
    headers = {'Oilcol-Token':'asdkljaskldjsakldjsad'}
    r = requests.post('http://172.24.42.126:9000/users/register', headers=headers, json=user)
    campo = {'idJefeCampo':{'username':user['username']}, 'region':'CARIBE', 'pozos':[]}
    r = requests.post('http://172.24.42.126:9000/campo', headers=headers, json=campo)
    _id = r.json()['id']
    for j in range(0, 14):
        p = {'lon':34.66,'lat':-72.123123,'estado':'ABIERTO','campo':{'id':_id}}
        r = requests.post('http://172.24.42.126 :9000/pozo', headers=headers, json=p)

with open('poziyos.csv', 'wb') as fp:
    for i in range(1, 1201):
        fp.write(str(i)+'\n')
        