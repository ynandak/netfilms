# NetFilms REST API Module
This README contains information regarding tables, URLs and features related to the REST API module.

### 1. RESTful URL List
This table contains all possible URL/method combinations possible in the API
 
| Method  | URL | Details |
| ------------- | ------------- | ------------- |
| GET  | /videoreleases  | Get all video releases. Accepts Parameters to sort/filter  |
| GET  | /videoreleases/{id}  | Get info related to a particular video  |
| POST | /videoreleases | Insert a new video release |
| PUT | /videoreleases/{id} | Update an existing video release |
| DELETE | /videoreleases/{id} | Delete an existing video release |
| GET  | /videoreleases/{vidID}/reviews  | Get all reviews for a particular video  |
| POST  | /videoreleases/{vidID}/reviews/{userID}  | Submit a review for a particular video/user combination  |
| GET  | /users  | Get a list of all users  |
| GET  | /users/{id}  | Get info related to a particular user  |
| POST | /users | Insert a new user account |
| PUT | /users/{id} | Update an existing user account |
| DELETE | /users/{id} | Delete an existing user account |
| POST | /users/login | Submit an email and password to login |

### 2. Tables
This is a list of all database tables required by the REST API in database netfilms-db. The tables will be created automatically if they do not exist.

| Table Name | Foreign Key(s) | Sample input JSON |
| ------------- | ------------- | ------------- |
| VIDEORELEASE | - | Provided in movielist.json |
| USER | - | {"email" : "testuser@gmail.com", "password" : "password123", "name" : "Test Name"} |
| REVIEW | VIDEORELEASE, USER | {"rating" : 4, "review" : "I liked the movie."} |
| VIDEORELEASE_ACTORS | VIDEORELEASE | - |
| VIDEORELEASE_COUNTRY | VIDEORELEASE | - |
| VIDEORELEASE_GENRE | VIDEORELEASE | - |
| VIDEORELEASE_LANGUAGE | VIDEORELEASE | - |

### 3. Features
A list of some of the notable features implemented, along with links to their location in code.

| Feature | Link(s) |
| ------------- | ------------- |
| Customised sorting/filtering of video releases on any field using input parameters. For example, **http://localhost:8080/netfilms-api/videoreleases?language=English&country=USA&rated=PG-13&sort=metascore&sortOrder=desc** is valid | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/entity/VideoRelease.java#L80) |
| Normalization of VideoRelease JSON input for certain multi-valued fields | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/entity/raw/RawVideoRelease.java#L61) |
| Password hashing using jbCrypt before storage | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/service/UserServiceImp.java#L56) [[2]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/service/HashService.java#L5) |
| Implementation of case-insensitive JSON mapping by overriding Spring's default mapping | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/AppConfig.java#L26) |
| On Delete Cascade for Reviews when a User or VideoRelease is deleted | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/repository/ReviewRepository.java#L42) |
| Maintaining average user ratings for video releases  | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/entity/VideoRelease.java#L71) |
| Server-side validations for user input | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/entity/User.java#L37) |
| @JsonIgnore and @JsonProperty(access) to limit what is sent back to client | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/entity/User.java#L46) |
| Use of @Enumerated to limit the types of users to USER, ADMIN  | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/entity/User.java#L55) |
| Database connection details using properties file | [[1]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/resources/netfilms-api.properties) [[2]](https://github.com/ynandak/netfilms/blob/module-api/server/src/main/java/xyz/yogesh/app/JPAConfig.java#L23) |

### 4. To-Do

* Implementing RESTful session control.
* Eliminating RawVideoRelease and implementing efficient direct mapping to VideoRelease.
