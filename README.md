
# Nidofy

The source code for the [frontend](https://github.com/ElectroBoy404NotFound/nidofy/tree/main/nidofy-frontend) and [backend](https://github.com/ElectroBoy404NotFound/nidofy/tree/main/nidofy-backend) of my Portfolio Website.


## Deploying

Once the repo is cloned, copy the contents of `nidofy-frontend` to the root of your webserver.

Then go into nidofy-backend and run `docker compose build` and `docker compose up`. This will start the backend.

Now, set all requests to the `/api` from the web root to proxy to the backend (without the `/api` being included) and all requests to `/uploads` from the web root to be fetched from the `uploads` folder created in the backend's directory after first run.

## Documentation

### Frontend

The frontend uses data from the backend to display data on the site...

... and yes I know that that's how basically every site on Earth works.

**Languages:** HTML, CSS, Javascript

**Libraries/Frameworks**: Bootstrap 5, Font Awesome, Swiper.JS, EasyMDE, Marked

### Backend

Basically a database which grew roles and HTTP.

**Languages:** Java

**Libraries/Frameworks**: SpringBoot, MySQL

**Extra Docs**: `nidofy-backend/README.md`, `nidofy-backend/API_REFERENCE.md`

### Glue

Basically stuff that holds everything together from collapse.

**Software:** Nginx, Docker


## Environment Variables

To run this project, you will need to add the following environment variables to your environment

### Security

`SECURITY_ADMINEMAIL` - Email of the admin

`SECURITY_JWT_SECRET-KEY` - A SHA256 hash to be used as the secret key for the JWT (never leak this!)

### Database

`SPRING_DATASOURCE_URL` - URL of the database

`SPRING_DATASOURCE_USERNAME` - Database username

`SPRING_DATASOURCE_PASSWORD` - Database password

### SMTP

`SPRING_MAIL_HOST` - Email SMTP server host

`SPRING_MAIL_PORT` - Email SMTP server port

`SPRING_MAIL_USERNAME` - Email SMTP server username

`SPRING_MAIL_PASSWORD` - Email SMTP server password

### Uploads

`UPLOADS_TARGETDIRECTORY` - Directory name of where to upload the image files to

## Demo

The live site can be accessed at https://nido.dino.icu/

## Contributing

Contributions are always welcome!

Make a change and send a pull request.
