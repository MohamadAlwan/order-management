# Order Management REST APIs

## This api consist of 5 APIs


## You need to be authenticated to have access to the resources of this API

### First you need to sign up and create an account
```bash
To access the sign up: localhost:9090/auth/signup
```
```bash
You need to pass:
1. Name
2. User Name
3. Email
4. Password
```

### After creating an account you need to log in 
```bash
To access the sign in: localhost:9090/auth/signin
```
```bash
You need to pass:
1. the username or the email
2. the password
```
```bash
after that you will get a bearer token that you need to put it for accessing the order management APIs
```
### The first one is customer API
```bash
This API consist of:
1. ID as primary key
2. First Name
3. Last name
4. Born At 
5. List<Order>
```
```bash
The first function the Customer API have is create customer
The request path is: localhost:9090/customer with a POST method
```
```bash
The second function the Customer API have is get all customer
The request path is: localhost:9090/customer with a GET method
```
```bash
The third function the Customer API have is get customer by id
The request path is: localhost:9090/customer/{id} with a GET method
```
```bash
The fourth function the Customer API have is update customer
The request path is: localhost:9090/customer/{id} with a PUT method
```
```bash
The fifth function the Customer API have is delete customer
The request path is: localhost:9090/customer/{id} with a DELETE method
```

### The second one is product API
```bash
This API consist of:
1. ID as primary key
2. slug
3. name
4. price
5. vat
6. stockable
7. reference  
8. List<Product_order>
9. List<stock>
```
```bash
The first function the Product API have is create Product
The request path is: localhost:9090/product with a POST method
```
```bash
The second function the Product API have is get all Product
The request path is: localhost:9090/product with a GET method
```
```bash
The third function the Product API have is get Product by id
The request path is: localhost:9090/product/{id} with a GET method
```
```bash
The fourth function the Product API have is update Product
The request path is: localhost:9090/product/{id} with a PUT method
```
```bash
The fifth function the Product API have is delete Product
The request path is: localhost:9090/product/{id} with a DELETE method
```

### The third one is order API
```bash
This API consist of:
1. ID as primary key
2. orderAt  
3. customer id
4. List<Product_order>
```
```bash
The first function the Order API have is create Order
The request path is: localhost:9090/order/customer/{id} with a POST method
```
```bash
The second function the Order API have is get all Order
The request path is: localhost:9090/order with a GET method
```
```bash
The third function the Order API have is get Order by id
The request path is: localhost:9090/order/{id} with a GET method
```
```bash
The fourth function the Order API have is update Order
The request path is: localhost:9090/order/{id} with a PUT method
```
```bash
The fifth function the Order API have is delete Order
The request path is: localhost:9090/order/{id} with a DELETE method
```

### The fifth one is product_order API
```bash
This API consist of:
1. order id
2. product id
the product id and order id together form the primary key
3. quantity
4. price
5. vat
```
```bash
The first function the product_order API have is create product_order
The request path is: localhost:9090/product_order/product/{pid}/order/{oid} with a POST method
```
```bash
The second function the product_order API have is get all product_order
The request path is: localhost:9090/product_order with a GET method
```
```bash
The third function the product_order API have is get product_order by id
The request path is: localhost:9090/product_order/{id} with a GET method
```
```bash
The fourth function the product_order API have is update product_order
The request path is: localhost:9090/product_order/{id} with a PUT method
```
```bash
The fifth function the product_order API have is delete product_order
The request path is: localhost:9090/product_order/{id} with a DELETE method
```

### Build Docker Image from Dockerfile
```bash
docker build -t order_management .
```

### Run a docker image in a docker container
```bash
docker run --network springboot-mysql-net --name springboot-mysql-container -p 8080:8080 springboot-restful-webservices
```

