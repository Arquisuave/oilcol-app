import os
import json
import requests
import random
import numpy as np

r=[]

user = {'username':'mmgomez10@uniandes.edu.co', 'password':"margara123", 'type':'JEFE_PRODUCCION'}
headers = {'Oilcol-Token':'asdkljaskldjsakldjsad'}
(requests.post('http://localhost:9000/users/register', headers=headers, json=user))


user = {'username':'jg.tamura@uniandes.edu.co', 'password':"lomaximo", 'type':'JEFE_CAMPO'}
headers = {'Oilcol-Token':'asdkljaskldjsakldjsad'}
requests.post('http://localhost:9000/users/register', headers=headers, json=user)

campo = {'idJefeCampo':{'username':user['username']}, 'region':'PACIFICA', 'pozos':[]}
r.append(requests.post('http://localhost:9000/campoSinSeg', headers=headers, json=campo))

user = {'username':'jg.tamura10@uniandes.edu.co', 'password':"lomaximo", 'type':'JEFE_CAMPO'}
headers = {'Oilcol-Token':'asdkljaskldjsakldjsad'}
requests.post('http://localhost:9000/users/register', headers=headers, json=user)

campo = {'idJefeCampo':{'username':user['username']}, 'region':'CARIBE', 'pozos':[]}
r.append(requests.post('http://localhost:9000/campoSinSeg', headers=headers, json=campo))


user = {'username':'example1@uniandes.edu.co', 'password':"lomaximo", 'type':'JEFE_CAMPO'}
headers = {'Oilcol-Token':'asdkljaskldjsakldjsad'}
requests.post('http://localhost:9000/users/register', headers=headers, json=user)

campo = {'idJefeCampo':{'username':user['username']}, 'region':'ANDINA', 'pozos':[]}
r.append(requests.post('http://localhost:9000/campoSinSeg', headers=headers, json=campo))

user = {'username':'c.garcia11@uniandes.edu.co', 'password':"lomaximo", 'type':'JEFE_CAMPO'}
headers = {'Oilcol-Token':'asdkljaskldjsakldjsad'}
requests.post('http://localhost:9000/users/register', headers=headers, json=user)

campo = {'idJefeCampo':{'username':user['username']}, 'region':'ORINOQUIA', 'pozos':[]}
r.append(requests.post('http://localhost:9000/campoSinSeg', headers=headers, json=campo))


user = {'username':'ea.margffoy10@uniandes.edu.co', 'password':"lomaximo", 'type':'JEFE_CAMPO'}
headers = {'Oilcol-Token':'asdkljaskldjsakldjsad'}
requests.post('http://localhost:9000/users/register', headers=headers, json=user)

campo = {'idJefeCampo':{'username':user['username']}, 'region':'AMAZONAS', 'pozos':[]}
r.append(requests.post('http://localhost:9000/campoSinSeg', headers=headers, json=campo))


lons=[[-77.5,-76.5],[-76.6,-73.2],[-74.98,-73.6],[-70.9,-68.12],[-73.70,-70.16]]
lats = [[2.33,5.8],[8.464,12.7],[5.15,6.3],[4.25,5.9],[-1.46,0.885]]

print r
estado = {1:'PARADO',2:'ABIERTO',4:'CLAUSURADO',3:'PRODUCCION'}

for i in range(0, len(r)):
    print(i)
    print(r[i].json())
    _id = r[i].json()['id']
    for j in range(0, 50):
        numeroT = np.around(random.triangular(1,4,2))
        estadoT = estado[numeroT]
        lat = np.random.uniform(lats[i][0],lats[i][1])
        lon = np.random.uniform(lons[i][0],lons[i][1])
        p = {'lon':lon,'lat':lat,'estado': estadoT ,'campo':{'id':_id}}
        requests.post('http://localhost:9000/pozoSinSeg', headers=headers, json=p)


        