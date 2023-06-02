# api endpoint desgin

## add customer:
### POST localhost:8080/webAPI/customer
requestBody:
```
{
    "firstName": "Minghao",
    "lastName": "Zhou"
}
```

## add transactions:
### POST localhost:8080/webAPI/transaction
requestBody:
```
{
    "customer": {
        "id": 1,
        "firstName": "Minghao",
        "lastName": "Zhou"
    },
    "date": "2023-07-29",
    "amount": 108
}
```

## get rewards of customer by id:
### GET localhost:8080/webAPI/rewards?id=1
requestParam:

    "id": 1

## get info of specific user:
### GET localhost:8080/webAPI/customer?id=1
requestParam:

    "id": 1


|Name|Email|Address|
|----|-----|-------|
|John|john@example.com|Address1|