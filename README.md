# GameStore
An e-store, cross-platform, desktop application where the user can create an account and purchase digital games. Developed with the NetBeans IDE 8.2 using JavaFX. The application runs locally in a XAMPP enviroment. The administration part is handled by the phpMyAdmin tool for the project's database management.

Setup Instructions for running the application
-----------------------------------------------
1. Set up your <strong>XAMPP</strong> enviroment(Apache Server, phpMyAdmin).
2. Create the <strong>gamestore_fx_db</strong> schema on the database tool, and import the database from the <strong>SQL</strong> <a href="https://github.com/chrislaskos/GameStore/blob/master/gamestore_fx_db.sql">file</a>, or copy the <strong>SQL</strong> code.
3. Download the <a href="https://github.com/chrislaskos/GameStore/blob/master/GameStoreProject.zip">project</a> of the application and open it in <strong>NetBeans</strong>.
4. Connect with the database in the Services tab under <strong>MySQL Server(localhost)</strong>. The MySQL <strong>JDBC driver</strong> is embedded in the project.
5. Enter your server properties(Host Name, Port Number, Admin UserName/Password).
6. Modify the <strong>3 lines</strong> of code in the following files: <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/AccountController.java">AccountController.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/CheckoutController.java">CheckoutController.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/LibraryController.java">LibraryController.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/model/CustomerQueries.java">CustomerQueries.java</a> <br />
   <a href="https://github.com/chrislaskos/GameStore/blob/master/src/gamestore/model/ProductQueries.java">ProductQueries.java</a> <br /> 
   to match your <strong>server properties</strong> like this<br />
<hr />
   <strong>
      private static final String DATABASE_URL = "your_database_url"; <br />
      private static final String USERNAME = "your_username"; <br />
      private static final String PASSWORD = "your_password"; <br />
   </strong>   
<hr />
This project was also my thesis for the Informatics Engineering department.<br />
