CSCIX370 Term Project README

Authors:
Caleb Crumley
Max Strauss
Jeff Kubler

Database Import Insructions:

Database Import Using MySQL Workbench (minimum required tables are specified)
1. Connect to your local MySQL database using MySQL Workbench.

2. In the script editor, enter and execute the following command to create the database: 'CREATE DATABASE lahmansbaseballdb;'

3. In the script editor, enter and execute the following command: 'use lahmansbaseballdb;'

4. From the top menu, click File -> Open SQL Script -> Navigate to the projects 'SQL Scripts/Database dump' directory -> lahmansbaseballdb_awardsplayers.sql'

5. From the script editor top menu, click the Lightning Bolt Icon to execute the entire script file.

6. Repeat steps 4 and 5 for the following script files: 

lahmansbaseballdb_batting.sql, 
lahmansbaseballdb_fielding.sql, 
lahmansbaseballdb_pitching.sql,
lahmansbaseballdb_people.sql,
lahmansbaseballdb_teams.sql,
lahmansbaseballdb_teamsfranchies.sql

7. If deesired, repeat steps 4 and 5 for the remaining sql scripts. However, only the above scripts are required for the application.

Configuration Instructions:
1. Open DBHelper.java in a text editor. The file is located in the project's /src directory.

2. Edit lines 20 and 21 to include the username and login for your MySQL server that is hosting the lahmansbaseballdb database.

Compilation and Execution Instructions:
(Instructions assume that the JDK is in user's system PATH variable)

To compile and execute from Bash:

1. Unzip the project files

2. In Bash, navigate to the to unzipped project's parent directory 

3. To compile: enter "make clean", and then enter "make compile"

4. To run: Enter "make run"

To compile and execute from Windows Command Prompt:


1. Unzip project2_nullmansland.zip

2. In Command Prompt Navigate to the navigate to the to unzipped project's parent directory 

3. To compile: Enter "del bin\*.class" enter "javac -classpath lib -d bin src\*.java"

4. To run: Enter "java -classpath classes;lib\* MainDriver"


To compile and execute from Eclipse:

1. Unzip the project files, and copy them into you Eclipse workspace directory

2. Open Eclipse, and from the top menu enter File -> Import -> General -> Existing Projects into workspace -> 
   Next -> Browse -> Navigate to your workspace and select the project -> Finish


4. If project is throwing errors, right click the project from the Package Explorer -> Add External Archives -> 
   Navigate to project's lib directory and select mysql-connector-java-8.0.15.jar

5. Right click MainDriver.java file from the project folder in the Package Explorer -> Run As -> Java Application