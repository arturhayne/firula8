# Firula8
An access client for Dribbble plataform

## Functionalities

### Autenticação

Authorization to use the services (login required)

Service:
```
GET https://dribbble.com/oauth/authorize
```

Acquire access code:
```
POST https://dribbble.com/oauth/token
```

The token information is stored in session.

### Shots List
Lists the first 30 shots 

Service:
```
GET /user/shots?page=1&per_page=30"
```

### Shot
Displays the selected shot in the listing

Service:
```
GET /shots/:id
```

## API Version Changes
Unfortunately api version 1 (v1) is deprecated, so there have been some changes with this change

### Authentication
In the previous version there was no need for the user to authenticate, however it is necessary for the user to have a login and password, including a web authentication.

### Fields Removed
The "created_at", "views_counts" and "comments_counts" fields have also been removed from both the listing and the single shot pickup service. The "created_at" field has been replaced by "published_at"

### Listing by user
The list of shots as it was formerly:
```
GET /shots
```

It allowed to show the listing of all shots, but today, according to the documentation, list only the shots of the authenticated user
```
GET /user/shots
```




