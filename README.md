# job4j_url_shortcut



## Study project "job4j_url_shortcut".
### training course Job4j_MIDDLE

Link shortening app. The app allows the user to receive shortened links 
to their site. When using a shortened link, the app will redirect to the 
original URL. Interaction occurs through simple http requests.

### Technologies used:
- Java 17
- PostgreSQL
- Hibernate
- Spring Boot 2
- Spring Data JPA
- Spring Security
- JWT

### How to use the app:

- First you need to register your site by sending a POST request with your site name.
  Upon successful registration, you will be assigned a login (the name of your site) and 
  a password (generated automatically), necessary for authorization.
  ![alt text](https://github.com/BarmaleySPb/job4j_url_shortcut/blob/master/src/main/java/ru/job4j/url/shortcut/image/1.png)
  ![alt text](https://github.com/BarmaleySPb/job4j_url_shortcut/blob/master/src/main/java/ru/job4j/url/shortcut/image/2.png)

- Next, you need to get a token using a POST request /login, in the body of the request 
  you must specify your login and password. The token you can find in the headers.
  ![alt text](https://github.com/BarmaleySPb/job4j_url_shortcut/blob/master/src/main/java/ru/job4j/url/shortcut/image/3.png)

- To get a shortened link, you need to send POST request /convert, with a link, you 
  must also specify your token in Headers (For example: Bearer e25d31c5-db66-4cf2-85d4-8faa8c544ad6).
  The link code will be received in the body of the response.
  ![alt text](https://github.com/BarmaleySPb/job4j_url_shortcut/blob/master/src/main/java/ru/job4j/url/shortcut/image/4.png)
  ![alt text](https://github.com/BarmaleySPb/job4j_url_shortcut/blob/master/src/main/java/ru/job4j/url/shortcut/image/5.png)

- For redirect to the original url address, use the GET request /redirect/link code
  ![alt text](https://github.com/BarmaleySPb/job4j_url_shortcut/blob/master/src/main/java/ru/job4j/url/shortcut/image/6.png)

- The application also has statistics on queries. To get statistics, use the GET request 
  /statistics. You will receive a response with a list of your links and the number of requests 
  for each of them.
  ![alt text](https://github.com/BarmaleySPb/job4j_url_shortcut/blob/master/src/main/java/ru/job4j/url/shortcut/image/7.png)

### Contacts:
[![Telegram](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/Evgeny_Zakharov)&nbsp;
[![Email](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:e.g.zakharov@gmail.com)&nbsp;