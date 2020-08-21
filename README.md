##Tech Stacks
*    Java
*    SpringBoot
*    JUnit
*    Rest Assured
*    Assertj
##Running app
1. If mvn works in console

    mvn clean install

    mvn spring-boot:run

2. From IDE - maven

    maven > hsbc-challenge > lifecycle > install

    maven > hsbc-challenge > plugins > spring-boot > spring-boot:run
    
##Rest endpoints description
Application runs with context /hsbc-challenge on port 7777 by default.

Available endpoints:
* http://localhost:7777/hsbc-challenge/rest/wall/messages POST {"text":"test1"} <- add message
* http://localhost:7777/hsbc-challenge/rest/wall/messages GET <- get wall message
* http://localhost:7777/hsbc-challenge/rest/user GET <- get all users
* http://localhost:7777/hsbc-challenge/rest/user/followed PUT body example {"followedUser":UUID} <- follow user
* http://localhost:7777/hsbc-challenge/rest/user/followed GET <- get followed users
* http://localhost:7777/hsbc-challenge/rest/wall/messages/followed GET <- get followed users messages


## Scenario
If you use Postman import pre-prepared collection hsbc-challenge.postman_collection. In Postman, you can create second session by deleting cookie (Cookies -> JSESSIONID -> X).
1. Post a few messages. User will be created after first message. Created user will have login like sessionId.
2. Get own messages from wall.
3. Get all users and save UUID (will be useful later).
4. Open second session and add a message to create second user.
5. Follow user by using saved UUID.
6. Get followed users (optionally).
7. Get followed users messages (it should be messages from 1 step).