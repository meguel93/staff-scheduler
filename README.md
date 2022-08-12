# Getting Started

### How to run the app

1. Run the [up.sh](up.sh) script in the main project folder. 
   * This will build the application
   * Create the docker containers
   * Deploy the application.

2. Run the [create_roles.sql](create_roles.sql) script
      * This will insert 2 roles, namely 'ADMIN' and 'STAFF'
      * The database name is staff-schedule-db
      * It is running on port 3307
      * The username is **_root_** and password is _**P@ssw0rd**_

3. To bring down the containers run the [down.sh](down.sh)

### Rest Api Documentation
* [Open API Documantation](http://localhost:8080/staff-schedule/swagger-ui/index.html#/)
