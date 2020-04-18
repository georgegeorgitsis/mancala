# The Mancala Game
---
by: George Georgitsis (georgegeorgitsis@gmail.com)

## Getting started

### Introduction
This application is a terminal board game using `Java`. 


### Installation

#### How to run

```
$ git clone https://github.com/georgegeorgitsis/mancala
$ cd georgegeorgitsis
$ mvn compile
$ mvn exec:java -Dexec -Dspring.main.banner-mode=off
```

#### How to test
```
$ mvn test
```

### Improvements 
1. Connect with DB to store boards, players and pits for asynchronous games
2. Create REST API endpoints for joining game, moving stones etc.
3. Create a fancy UI and connect with the API 
4. Improve testing  
