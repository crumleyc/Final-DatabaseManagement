# Final-DatabaseManagement

Contents:
1. Summary of classes
2. Configuration instructions for [Eclipse](https://www.eclipse.org/downloads/)
3. Configuration instructions for Command Line
4. Use instructions
---------------------------------
1. Summary of classses

    The project is divided into three main Java classes:
      * MainDriver.java contains the main method and a simple console UI, with limited error handling

      * DBHelper.java contains all of the JDBC data access methods and queries.

      * DBTablePrinter.java is an open-source helper class for outputting result sets into a clean, readable table format.

2. Configuration Instructions for Eclipse.

   2.1: Table Alterations
   If you have not yet done so, connect to your database with work bench, open the 'alterations.sql' file, and execute the script in order to add the primary key to the 'people' table. This script file will contain and keep track of any additional table alterations that we may need to make.

   2.2: Importing into Eclipse
   To import the project into Eclipse,
     1. Download and unzip the project files
     2. Copy the directory to your Eclipse workspace and rename the project folder as you see fit.
     3. Within Eclipse: File -> Import -> General -> Existing Project Into Workspace -> next -> browse -> select the project folder -> Finish

     If the project is giving you errors, you may need to manually specify your JRE in the build path: Right click the project in the project explorer -> Build Path -> Configure Build Path -> Libraries -> Module path -> double click -> Workspace default JRE

     You may also need to add the mysql connector to the classpath if it is not already. The connector JAR is located in the project's lib directory.

   2.3: Specify MySQL credentials
    Open DBHelper.java and modify the class constructor to include your databasename, your MySQL user name, and your MySQL password.

3. Configuration Instrctions for Command Line

    3.1: Java 8 and JDK installed to test: `$javac --version` AND/OR `$java --version`

    3.2: You may also need to add the mysql connector to the classpath if it is not already. The connector JAR is located in the project's lib directory.

    3.3: Compiling:
      - `$ javac -cp .:lib/ -d bin/ src/*.java`

    3.4: Running:
      - `$ java -cp .:lib/*:bin/ MainDriver`
4. Use instructions.

   After configuring the project, run the project as a Java application. Currently, two analysis operations have been implemented, and the commands to run them are displayed in the menu.

   The first option will prompt you for a playerID (try 'pujolal01' as a test case). It will find the season for which that played earned the highest batting average, and it will compute how this stat differs from the historical average.

   The second option will display two tables. The first table shows the top 10 average homerun stats of above average weight players, and the second table shows the top 10 average homerun stats of below average players.
