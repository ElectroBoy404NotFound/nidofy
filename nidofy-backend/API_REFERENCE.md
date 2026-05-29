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
GET /public/poems/getPoemById?id={id}
```

URL Fields:
| Parameter | Type   | Description                          |
| :-------- | :----- | :----------------------------------- |
| `id`      | `long` | **Required**. Id of poem to fetch    |

#### Get all project thumbnails

```http
GET /public/projects/getAllProjects
```

#### Get specific project by id

```http
GET /public/projects/getProjectById?id={id}
```

URL Fields:
| Parameter | Type   | Description                             |
| :-------- | :----- | :-------------------------------------- |
| `id`      | `long` | **Required**. Id of project to fetch    |

---

### Authentication

#### Register a new user

```http
POST /userauth/signup
```

Body Fields:
| Parameter  | Type     | Description                              |
| :--------- | :------- | :--------------------------------------- |
| `email`    | `String` | **Required**. Email of the new user      |
| `password` | `String` | **Required**. Password for the new user  |
| `fullname` | `String` | **Required**. Full name of the new user  |
| `username` | `String` | **Required**. Username of the new user   |

#### Login user

```http
POST /userauth/login
```

Body Fields:
| Parameter  | Type     | Description                           |
| :--------- | :------- | :------------------------------------ |
| `email`    | `String` | **Required**. Email of user to login  |
| `password` | `String` | **Required**. Password of user to login |

#### Logout user

```http
POST /userauth/logout
```

Body Fields:
| Parameter      | Type     | Description                          |
| :------------- | :------- | :----------------------------------- |
| `jwtToken`     | `String` | **Required**. JWT of the user        |
| `refreshToken` | `String` | **Required**. Refresh token of the user |

#### Refresh JWT token

```http
POST /userauth/refresh
```

Body Fields:
| Parameter      | Type     | Description                             |
| :------------- | :------- | :-------------------------------------- |
| `refreshToken` | `String` | **Required**. Refresh token of the user |

#### Get JWT token info

```http
POST /userauth/jwtInfo
```

Body Fields:
| Parameter | Type     | Description                     |
| :-------- | :------- | :------------------------------ |
| `token`   | `String` | **Required**. JWT to inspect    |

#### Request password reset OTP

```http
GET /userauth/resetPassword?email={email}
```

URL Fields:
| Parameter | Type     | Description                                    |
| :-------- | :------- | :--------------------------------------------- |
| `email`   | `String` | **Required**. Email to send the OTP to         |

#### Submit password reset

```http
POST /userauth/resetPassword
```

Body Fields:
| Parameter     | Type     | Description                              |
| :------------ | :------- | :--------------------------------------- |
| `email`       | `String` | **Required**. Email of the user          |
| `otp`         | `String` | **Required**. OTP received via email     |
| `newpassword` | `String` | **Required**. New password to set        |

---

### User Info
> Requires authentication.

#### Get current user info

```http
GET /info/users/me
```

#### Get current user privilege level

```http
GET /info/users/me/privilage_level
```

#### Get user by id
> Requires `ADMIN` privilege.

```http
GET /info/users/getById/{id}
```

Path Fields:
| Parameter | Type   | Description                        |
| :-------- | :----- | :--------------------------------- |
| `id`      | `long` | **Required**. Id of user to fetch  |

---

### Admin — Poems
> All endpoints require `ADMIN` privilege.

#### Add a poem

```http
POST /admin/poems/put
```

Body Fields:
| Parameter         | Type                     | Description                              |
| :---------------- | :----------------------- | :--------------------------------------- |
| `title`           | `String`                 | **Required**. Title of the poem          |
| `poem`            | `List<List<String>>`     | **Required**. Poem content (nested list) |
| `date`            | `String`                 | **Required**. Date of the poem           |
| `signature`       | `String`                 | **Required**. Signature text             |
| `signatureLength` | `int`                    | **Required**. Length of the signature    |

#### Edit a poem

```http
POST /admin/poems/edit
```

Body Fields:
| Parameter         | Type                     | Description                                 |
| :---------------- | :----------------------- | :------------------------------------------ |
| `id`              | `long`                   | **Required**. Id of the poem to edit        |
| `title`           | `String`                 | **Required**. Updated title                 |
| `poem`            | `List<List<String>>`     | **Required**. Updated poem content          |
| `date`            | `String`                 | **Required**. Updated date                  |
| `signature`       | `String`                 | **Required**. Updated signature             |
| `signatureLength` | `int`                    | **Required**. Updated signature length      |

#### Delete a poem

```http
DELETE /admin/poems/delete/{id}
```

Path Fields:
| Parameter | Type   | Description                          |
| :-------- | :----- | :----------------------------------- |
| `id`      | `long` | **Required**. Id of poem to delete   |

---

### Admin — Projects
> All endpoints require `ADMIN` privilege.

#### Add a project

```http
POST /admin/projects/put
```

Body Fields:
| Parameter     | Type           | Description                               |
| :------------ | :------------- | :---------------------------------------- |
| `title`       | `String`       | **Required**. Title of the project        |
| `description` | `String`       | **Required**. Short description           |
| `explanation` | `String`       | **Required**. Full explanation            |
| `date`        | `String`       | **Required**. Date of the project         |
| `timeperiod`  | `String`       | **Required**. Time period worked on       |
| `languages`   | `List<String>` | **Required**. Languages/technologies used |
| `thumbnail`   | `String`       | **Required**. Thumbnail URL               |
| `github`      | `String`       | GitHub repository URL                     |
| `liveDemo`    | `String`       | Live demo URL                             |
| `youtube`     | `String`       | YouTube video URL                         |

#### Upload a project image

```http
POST /admin/projects/uploadImage
```

Form Fields:
| Parameter | Type   | Description                           |
| :-------- | :----- | :------------------------------------ |
| `file`    | `File` | **Required**. Image file to upload    |

Returns the URL path to the uploaded image.

#### Delete a project

```http
DELETE /admin/projects/delete/{id}
```

Path Fields:
| Parameter | Type   | Description                             |
| :-------- | :----- | :-------------------------------------- |
| `id`      | `long` | **Required**. Id of project to delete   |

---

### Admin — Users
> All endpoints require `ADMIN` privilege.

#### List all users

```http
GET /admin/users/list
```

#### Update a user

```http
POST /admin/users/update
```

Body Fields:
| Parameter        | Type     | Description                                           |
| :--------------- | :------- | :---------------------------------------------------- |
| `userid`         | `long`   | **Required**. Id of the user to update               |
| `email`          | `String` | Updated email                                         |
| `password`       | `String` | Updated password (will be encoded)                    |
| `fullname`       | `String` | Updated full name                                     |
| `username`       | `String` | Updated username                                      |
| `privilegeLevel` | `String` | Updated privilege level                               |
| `enabled`        | `boolean`| Set to `true` to enable the user account             |

#### Delete a user

```http
DELETE /admin/users/delete/{id}
```

Path Fields:
| Parameter | Type   | Description                           |
| :-------- | :----- | :------------------------------------ |
| `id`      | `long` | **Required**. Id of user to delete    |