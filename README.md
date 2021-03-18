# Transaction service


## Functionality:
(url = "localhost:8080")
### Client ("/clients"):
* Create an account with or without relations;
  * POST
* Get all clients with pagination;
  * GET
* Update client;
  * PUT ( "/{id}" )
* Get by id.
  * GET ( "/{id}" )

### Account ("/accounts"):
* Create account with reference to client;
  * POST ( "/client-id/{id}" )
* Get account by client id.
  * GET ( "/client-id/{id}" )
   
### Transaction ("/transactions"):
* Create transactions;
  * POST
* Get transactions by accounts id.
  * GET

# To run the project locally:
1. Create schema in the PosgreSQL "transaction";
2. Update file "src/main/resources/application.properties":
   * spring.datasource.username=root  // type your role in the PosgreSQL
   * spring.datasource.password=1234  // type your password.
