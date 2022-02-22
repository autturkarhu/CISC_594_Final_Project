import time;
import requests;



loginPayload = {'username': 'John’, 'password': 'admin'}
loginRequest = requests.post('http://localhost:8080/login', params=loginPayload)
	#print loginRequest.content;
assert(loginRequest.status_code == 200), 'not 200';

createBlogPayload = {'blogName': 'sample', 'blogDescription': 'sample description'};
createBlogRequest = requests.post('http://localhost:8080/blog', params=createBlogPayload)
print createBlogRequest.content;
assert(createBlogRequest.status_code == 200), 'not 200';

logoutRequest = requests.get('http://localhost:8080/logout');
	#print logoutRequest.content;
assert(logoutRequest.status_code == 200), 'not 200';