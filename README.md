# Java BE test task 
Tech stack: Spring boot, PostgreSQL, Docker

## Project "rules"
1) Keep it simple—no need for advanced patterns. Focus on working auth, basic transform, and Docker setup.
2) Passwords must be hashed; do not log secrets or tokens.

## TODO (with tests)
- [*] Register
```
curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d "{\"email\":\"a@a.com\",\"password\":\"pass\"}"
```
- [*] Login
```
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"email\":\"a@a.com\",\"password\":\"pass\"}"
```
- [ ] Data processor
```
curl -X POST http://localhost:8080/api/process -H "Authorization: Bearer <token>" -H "Content-Type: application/json" -d "{\"text\":\"hello\"}"
```
Expected result: <code>{ "result": "transformed text" }</code>


## How to:
- Run the project (test version) linux only:
```
./start_native.sh
```
- Run the project (release version):
```
docker compose up -d
```


