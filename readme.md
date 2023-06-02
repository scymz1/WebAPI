# api endpoint desgin

## add customer:
### POST localhost:8080/webAPI/customer
requestBody:
```json
{
    "firstName": "Minghao",
    "lastName": "Zhou"
}
```

## add transactions:
### POST localhost:8080/webAPI/transaction
requestBody:
```json
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

## delete specific transactions:
### DELETE localhost:8080/webAPI/transaction
requestBody:
```json
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

## delete customer:
### DELETE localhost:8080/webAPI/customer
requestBody:
```json
{
    "firstName": "Minghao",
    "lastName": "Zhou"
}
```

# Example dataset for demonstration

Customer table

| id |firstName|lastName|
|--|-----|-------|
| 1 |Leo|Zhou|
| 2 |Stephen|Curry|
| 3 |Jimmy|McGill|

Transaction table
| transactionId |date|amount|customer_id|
|--|-----|-------|-------|
| 1 |2023-07-29|150|1|
| 2 |2023-07-30|300|2|
| 3 |2023-08-01|250|2|
| 4 |2023-08-05|1000|3|
| 5 |2023-08-07|650|2|
| 6 |2023-08-22|5000|3|
| 7 |2023-11-20|2050|3|
| 8 |2023-12-29|150|1|
| 9 |2023-12-30|500|3|
| 10 |2024-01-01|150|2|

## get rewards of Jimmy McGill by customer id:
### GET localhost:8080/webAPI/rewards?id=3
requestParam:

    "id": 3

return response:
```json
{
    "firstName": "Jimmy",
    "lastName": "McGill",
    "total": 16500,
    "rewardsEachMonthList": [
        {
            "month": "NOVEMBER",
            "rewards": 3950
        },
        {
            "month": "AUGUST",
            "rewards": 11700
        },
        {
            "month": "DECEMBER",
            "rewards": 850
        }
    ]
}

```

