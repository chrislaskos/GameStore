# GameStore
An e-store, cross-platform, desktop application where the user can create an account and purchase digital games. Developed with the NetBeans IDE 8.2 using JavaFX. The application runs locally in a XAMPP enviroment. The administration part is handled by the phpMyAdmin tool for the project's database management.

Setup Instructions for running the application
-----------------------------------------------
1. Set up your XAMPP enviroment(Apache Server, phpMyAdmin).
2. Create the gamestore_fx_db schema on the database tool, and import the database from the SQL <a href="https://github.com/chrislaskos/GameStore/blob/master/gamestore_fx_db.sql">file</a>, or copy the SQL code.
3. Download the <a href="https://github.com/chrislaskos/GameStore/blob/master/GameStoreProject.zip">project</a> of the application and open it in NetBeans.
4. Connect with the database in the Services tab under MySQL Server(localhost). The MySQL JDBC driver is embedded in the project.
5. Enter your server properties(Host Name, Port Number, Admin UserName/Password).
6. Alter the code in the following files where the Strings DATABASE_URL, USERNAME, PASSWORD are your server properties: <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/AccountController.java">AccountController.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/CheckoutController.java">CheckoutController.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/LibraryController.java">LibraryController.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/model/CustomerQueries.java">CustomerQueries.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/model/ProductQueries.java">ProductQueries.java</a> <br /> 
