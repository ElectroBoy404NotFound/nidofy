
# Nidofy

The source code for the [frontend](https://github.com/ElectroBoy404NotFound/nidofy/tree/main/nidofy-frontend) and [backend](https://github.com/ElectroBoy404NotFound/nidofy/tree/main/nidofy-backend) of my Portfolio Website.




## Documentation

### Frontend

The frontend uses data from the backend to display data on the site...

... and yes I know that that's how basically every site on Earth works.

**Languages:** HTML, CSS, Javascript

**Libraries/Frameworks**: Bootstrap 5, Font Awesome

### Backend

Basically a database which grew roles and HTTP.

**Languages:** Java

**Libraries/Frameworks**: SpringBoot, MySQL

### Glue

Basically stuff that hold everything together from collapse.

**Software:** Nginx


## Environment Variables

To run this project, you will need to add the following environment variables to your environment

### Security

`SECURITY_ADMINEMAIL`

`SECURITY_JWT_SECRET-KEY`

### Database

`SPRING_DATASOURCE_URL`

`SPRING_DATASOURCE_USERNAME`

`SPRING_DATASOURCE_PASSWORD`

### SMTP

`SPRING_MAIL_HOST`

`SPRING_MAIL_PORT`

`SPRING_MAIL_USERNAME`

`SPRING_MAIL_PASSWORD`



## Demo

The live site can be access at https://nido.dino.icu/
## Contributing

Contributions are always welcome!

Make a change and send a pull request.
## API Reference

API reference for the backend

### Public Endpoints

#### Get all projects and poems as thumbnails

```http
  GET /public/home/getHome
```

#### Get all poem thumbnails

```http
  GET /public/poems/getAllPoems
```

#### Get specific poem by id

```http
  GET /public/poems/getPoemById
```

URL Fields:
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long`   | **Required**. Id of poem to fetch |

#### Get all project thumbnails

```http
  GET /public/projects/getAllProjects
```

#### Get specific project by id

```http
  GET /public/projects/getProjectById
```

URL Fields:
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long`   | **Required**. Id of project to fetch |

### Authentication

#### Login User

```http
  POST /userauth/login
```

Body Fields:
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email`      | `String`   | **Required**. email of user to login |
| `password`      | `String`   | **Required**. password of user to login |

#### Logout User

```http
  POST /userauth/logout
```

Body Fields:
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `jwtToken`      | `String`   | **Required**. JWT of the user |
| `refreshToken`      | `String`   | **Required**. RefreshToken of the user |

#### Refresh User JWT token

```http
  POST /userauth/refresh
```

Body Fields:
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `refreshToken`      | `String`   | **Required**. RefreshToken of the user |

#### Refresh User JWT token

```http
  POST /userauth/refresh
```

Body Fields:
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `refreshToken`      | `String`   | **Required**. RefreshToken of the user |

**Note:** Other implemented endpoints (such as signup) have not been mentioned here as they're not currently in use or tested.

