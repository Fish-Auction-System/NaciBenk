# API DOCUMANTATION

#### 1) URL => ```/api/customers``` _~~GET~~_:
```
- Shows all customers in db.
```


#### 2) URL => ```/api/fisherman``` _~~GET~~_:
```
- Shows all fishermans in db.
```

#### 3) URL => ```/api/admin``` _~~GET~~_:
```
- Shows all admins in db.
```
#### 3) URL => ```/api/members``` _~~GET~~_:
```
- Shows all members in db.
```
#### 4) URL => ```/api//loginget/{username}/{password}``` _~~GET~~_
```
- Getting user login function.
- username and password must be changed! this is for demo.
```
#### 5) URL => ```/api/signup/customer``` _~~POST~~_
```
- User signup function.
- This url requeires Customer object.
```
```
- customer Template:

{
    "username": "randomUser",
    "password": "random_psw_123",
    "surname": "iba"        
    "name": "cagatay",    
    "mail": "random@gmail.com",
    "address": "randomAddress"
}
```
#### 6) URL => ```/api/signup/member``` _~~POST~~_
```
- Member signup function.
- This url requeires Member object.
```
```
- member Template:

{
    "username": "Member_123",
    "mail": "member234@email.com",
    "password": "random_psw_234"

}
```
#### 7) URL => ```/api/register-fisherman``` _~~POST~~_
```
- Fisherman register function.
- This url requeires fisherman object.
```
```
- fisherman Template:

{
    "username": "Ali_Kaptan",
    "password": "random_psw_567",
    "mail": "randomFm@gmail.com",
    "iban": "11111111111111111111111111",
    "owner": "ali kuscu"                               
}
```
#### 8) URL => ```/api/cooperativeHead/getCode``` _~~GET~~_
```
- Getting code for aucion function.
- Returns String code
```
#### 9) URL => ```/api/auction/fetchAuctions``` _~~GET~~_
```
- Getting all auctions.
- Returns list of auction objects.
```
#### 10) URL => ```/api/auction/add``` _~~POST~~_
```
- Adding auctions to systems.
- This url requires auction object.
```
```
- auction Template:

{
    "name": "MEZGIT MEZATI",
    "date": 12312.1,
    "id": "asdasdasd",
    "quota": 12                        
}    
```
