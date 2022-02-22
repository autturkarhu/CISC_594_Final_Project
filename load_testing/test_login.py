import time;
import requests;


def doSendRequests():
	payload = {'username': 'john', 'password': 'admin'}
	loginRequest = requests.post('http://localhost:8080/login', params=payload)
	print loginRequest.content;
	logoutRequest = requests.get('http://localhost:8080/logout')
	print logoutRequest.content;

while True:
    doSendRequests()