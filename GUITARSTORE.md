# Guitar Store

Guitar Store is an API created to manage the product side of your guitar store. If your are looking for an "all included API" for your small shop, look no more, with this API you will get all you need to start your front end development.


## End-points



### `/`

- **This is a GET request only endpoint**

- This is the starting "home endpoint" which shows all the endpoints implemented by the API. *use that end point for a fast reference of the endpoints*

---

### `/userlogin`
- **This is a POST request only endpoint**

- Use this endpoint to log in users.

    - Need to provide the following key value pairs in the body of your request:

        - `username`
        - `password`
        
    - Body example

    ```
    {
        "username":"john.doe",
        "password":"password"
    }
    ```

    - password field is automatically converted to an MD5 encryption format to provide a piece of mind in security.

---

### `/userlogout` 

- **This is a POST request only endpoint**

- Use this endpoint to log out users

- No request-body necessary, if user was not logged in, the server will just re-new the session.

---

### `/user`
    
- **GET REQUEST**
    
    - Returns all active users

    - ***User must be logged in as Administrator in order to get access to this endpoint***.

- **POST REQUEST**

    - Creates a new Employee user

    - ***User must be logged in as Administrator in order to get access to this endpoint***.

    - Need the following key value pairs in the body of your request:

        - `uniqueID`
        - `username`
        - `email`
        - `password`
        - `userType_UID`

    - Body example

    ```
            {
                "uniqueID": 0,
                "username":"alexis.dominguez",
                "email":"alexis.dominguez@yahoo.comm"
                "password":"alexisdominguez",
                "userType_UID": 2
            }
            ```
---

### `/user/*`
    
- **GET REQUEST**

    - Returns user by id

    - ***User must be logged in as Administrator in order to get access to this endpoint***.

- **PUT REQUEST**

    - Updates the current user

    - ***User must be logged in as Administrator in order to get access to this endpoint***.

    - Need the following key value pairs in the body of your request:

        - `uniqueID`
        - `username`
        - `email`
        - `password`
        - `userType_UID`

    - Body example

    ```
    {
        "uniqueID": 0,
        "username":"alexis.dominguez",
        "email":"alexis.dominguez@yahoo.comm"
        "password":"alexisdominguez",
        "userType_UID": 2
    }
    ```          

- **DELETE REQUEST**  

    - Deletes an user by id

    - ***User must be logged in as Administrator in order to get access to this endpoint***.

---

### `/sign/up`

- Use this endpoint to create customers. These users have no access to any administrative endpoint.

- **This is a POST request only endpoint**

- Need the following key value pairs in the body of your request:

    - `username`
    - `email`
    - `password`

- Body example

```
{
    "username":"alexis.dominguez",
    "email":"alexis.dominguez@yahoo.comm"
    "password":"alexisdominguez",
}
```  

---

### `/sign/out`

- Use this enpoint to allow customers to opt-out of the system.

- No request-body necessary.

---

### `/sign/recover` 

- Use this enpoint to 'recover' access to the system for a previously opted-out customer.

- Need the following key value pairs in the body of your request:

    - `username`
    - `password`

- Body example

```
{
    "email":"alexis.dominguez@yahoo.comm"
    "password":"alexisdominguez",
}
``` 

--- 


### `/product`

- Retrieves all products from database.

---

### `/product/*`

- Regrieves on specific product by id

---

### `/product/brand/*`

- Retrieves products with matching Brand Id

---

### `/product/department/*`

- Retrieves products with matching Department Id


# Actually working on 
For our next version we've planned to fully implement the following:

- *Review Section* with average
- *CRUD* functionalities for *Brand, Category, Condition, Department, Features, PremiumGear, Specifications and Style*

# Contributors
Revature 2011 Java/React team


