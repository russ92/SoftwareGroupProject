Sprint 8
Team: Hash Flag
Russell Ward
Owen Corrigan
Nicholas Hammm

To Run:
To run the JAR compiled by Intellij, enter the directory where the swe-project folder is located.
Then run;
java -jar swe-project/out/artifacts/swe.jar
If that fails try running the maven JAR from the same directory;
java -jar swe-project/target/swe-sprint8-jar-with-dependencies.jar
If that fails, the program can be run through by opening it in Intellij and building from the main.java file
- located in SoftwareGroupProject/swe-project/src/main/java/sweproject.

Output should be stored in swe-project/VaxData/Sprint8 directory

Note: The JARS will only work if you run them from outside the 'swe-project' directory/folder as the file paths
in application.properties unfortunately aren't working correctly.

Owen worked on fixing issues from sprint 7 and carry over those implimentations to sprint 8 for the probabilities.
Nicholas worked at fixing a number of bugs and rewriting large sections of previous sprints.
Russell worked on writing the classes to convert the text files to dfg files.