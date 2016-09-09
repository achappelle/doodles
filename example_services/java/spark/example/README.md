./gradlew [run|build|test]

curl -v -X GET http://localhost:9090/example/rest/v1/message/myId
curl -v -X POST -H "Content-Type:application/json" -H "Accept:application/json" -d '{"content":"your message content"}' http://localhost:9090/example/rest/v1/message/myId