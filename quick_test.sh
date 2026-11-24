set -a
source /home/user/java_test_project/.env
set +a


sudo docker compose down
sudo docker compose up -d

sleep 15
cd /home/user/java_test_project/auth-api
mvn clean spring-boot:run &

cd /home/user/java_test_project/data-api
mvn clean spring-boot:run &

sleep 30

curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d "{\"email\":\"a@a.com\",\"password\":\"pass\"}"
token=$(curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"email\":\"a@a.com\",\"password\":\"pass\"}" | jq -r ".result")
echo "Token: $token"

sleep 10
curl -X POST http://localhost:8080/api/process -H "Authorization: Bearer $token" -H "Content-Type: application/json" -d "{\"text\":\"hello\"}"

pkill java
