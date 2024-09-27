# Crud
# Spring Security
# MySql
# JWT
# Authentication
# JPA
# build create update user set role and permission for user and admin

# postman
POST METHOD
--------
localhost:8080/Crud/user
-----
body 
-----
{
"email": "nghia181032@gmail.com",
"username":"nghiameowa",
"firstname": "nghia",
"lastname": "nguyen",
"password": "nghia6063@",
"birthday": "1990-10-10"
}

--------
result
------
{
"code": 0,
"results": {
"id": "1b58a2de-240f-4a1f-8efb-eca9964ab0f5",
"username": "nghiamedowa",
"email": "nghia18a1032@gmail.com",
"firstname": "nghia",
"lastname": "nguyen",
"birthday": "1990-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
}
}
--------
localhost:8080/Crud/auth/token
-----
{
"username": "admin",
"password": "admin123456"
}
--------
result
------
{
"code": 0,
"results": {
"token": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJOZ2hpYS1tZW93Iiwic3ViIjoiYWRtaW4iLCJleHAiOjE3Mjc3OTEyMjIsImlhdCI6MTcyNzQzMTIyMiwianRpIjoiODhlYTFhNDEtZGM2NC00ZWE5LWIyNmUtOTNhY2FiODAzNTE0Iiwic2NvcGUiOiJST0xFX0FETUlOIFJFSkVDVF9EQVRBIENSRUFURV9EQVRBIEFQUFJPVkVfUE9TVCJ9.4QiMPW-iqHnBGdnnbtp3oKMHrd0JeKnjUi4LuB9-mI4bodOeQPL-x24u9V1MJl-4oA566hjGIewk4FY609_AZg",
"authenticated": true
}
}
--------------
# GET METHOD

-------
localhost:8080/Crud/user/myInfo

Authorization

bearer Token
--------
result
------

{
"code": 0,
"results": {
"id": "755e81f4-934a-45fa-b8e9-4c3df9579bf3",
"username": "admin",
"email": "admin@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "1990-10-10",
"roles": [
{
"name": "ADMIN",
"description": "Admin role",
"permissions": [
{
"name": "REJECT_DATA",
"description": "3"
},
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
}
}

localhost:8080/Crud/user/users
--------
result
------
{
"code": 0,
"results": [
{
"id": "02c1b82b-9fbc-45f0-82c7-3d863bad20f8",
"username": "noerean3",
"email": "noerean3@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2015-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "05eeb221-970d-4b0a-b6a2-f9ac5b6172da",
"username": "noerean",
"email": "noerean@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2001-10-10",
"roles": []
},
{
"id": "12a58784-9951-445d-be1f-69c3d046fb00",
"username": "noerean12",
"email": "noerean12@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2024-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "1b58a2de-240f-4a1f-8efb-eca9964ab0f5",
"username": "nghiamedowa",
"email": "nghia18a1032@gmail.com",
"firstname": "nghia",
"lastname": "nguyen",
"birthday": "1990-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "40ad592f-e15a-4831-b160-6a269b8b03fa",
"username": "noerean1",
"email": "noerean1@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2001-10-10",
"roles": []
},
{
"id": "437a2bb9-d772-4693-bf3c-85221fbc4cd9",
"username": "nghiameow",
"email": "admin@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "1990-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "5650c694-fad4-4a4e-81ea-2d004c993632",
"username": "nghia123",
"email": "123as@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2023-10-10",
"roles": []
},
{
"id": "755e81f4-934a-45fa-b8e9-4c3df9579bf3",
"username": "admin",
"email": "admin@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "1990-10-10",
"roles": [
{
"name": "ADMIN",
"description": "Admin role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "REJECT_DATA",
"description": "3"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "8fe1431a-654f-4a04-9d45-fd80e624605c",
"username": "noerean13",
"email": "noerean13@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2014-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "9e056a34-ad76-4ef8-a6de-bc1230259c4d",
"username": "noerean8",
"email": "noerean8@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2020-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "a65d0d3d-764c-4ff3-84ba-fbaff18a04c2",
"username": "nghiameowa",
"email": "nghia181032@gmail.com",
"firstname": "nghia",
"lastname": "nguyen",
"birthday": "1990-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "acb9af23-284d-4ef0-ac6c-5112e04462eb",
"username": "noerean14",
"email": "noerean14@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2014-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "cb2f88d3-c1ac-4752-a5b2-4ec5a63149b5",
"username": "noerean6",
"email": "noerean6@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2020-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "cca8acbe-eace-499f-8ebc-1006573d9b43",
"username": "noerean10",
"email": "noerean10@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2020-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "d067b7a7-a151-4a00-a331-b0a2135a7bd7",
"username": "noerean15",
"email": "noerean15@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2001-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "d7c47b60-8570-48a1-9d60-effdb54de5f4",
"username": "noerean7",
"email": "noerean7@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2020-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "dc104c49-4e38-4d1d-b7f0-1c803678b882",
"username": "noerean4",
"email": "noerean5@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2020-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "f2dce8df-02b6-407a-a300-0e729689c1af",
"username": "noerean9",
"email": "noerean9@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2020-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "fa6a1b23-f057-40f0-8175-4aa6447ec985",
"username": "noerean11",
"email": "noerean11@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2024-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
},
{
"id": "fac965fd-a257-4644-aee2-fae7b761c718",
"username": "noerean2",
"email": "noerean2@gmail.com",
"firstname": null,
"lastname": null,
"birthday": "2001-10-10",
"roles": [
{
"name": "USER",
"description": "User role",
"permissions": [
{
"name": "CREATE_DATA",
"description": "2"
},
{
"name": "APPROVE_POST",
"description": "1"
}
]
}
]
}
]
}



# UPDATE METHOD

localhost:8080/Crud/user/668b8dd2-2413-46a9-b807-a24e10a216ea
--------
result
------
{
"code": 404,
"message": "User Not Found"
}
# DELETE METHOD

localhost:8080/Crud/user/668b8dd2-2413-46a9-b807-a24e10a216ea
--------
result
------
{
"code": 1004,
"message": "Unauthenticated"
}


