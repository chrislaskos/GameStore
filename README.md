# GameStore
An e-store, cross-platform, desktop application where the user can create an account and purchase digital games. Developed with the NetBeans IDE 8.2 using JavaFX. The application runs locally in a XAMPP enviroment. The administration part is handled by the phpMyAdmin tool for the project's database management.

Setup Instructions for running the application
-----------------------------------------------
1. Set up your XAMPP enviroment(Apache Server, phpMyAdmin).
2. Create the gamestore_fx_db schema on the database tool, and import the database from the SQL <a href="#">file</a>.
3. Download the games <a href="#">folder</a> and move it in the htdocs directory of XAMPP.
4. Download the <a href="#">project</a> of the application and open it in NetBeans.
5. Connect with the database in the Services tab under MySQL Server(localhost). The MySQL JDBC driver is embedded in the project.
6. Enter your server properties(Host Name, Port Number, Admin UserName/Password).
7. Alter the code in the followning files where the Strings DATABASE_URL, USERNAME, PASSWORD are your server properties: <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/AccountController.java">AccountController.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/CheckoutController.java">CheckoutController.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/LibraryController.java">LibraryController.java</a> <br />
   <a href="#">CustomerQueries.java</a> <br />
   <a href="#">ProductQueries.java</a> <br /> 
