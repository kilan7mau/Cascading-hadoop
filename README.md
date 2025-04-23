This is a wordcount program using Cascading Api.

To run this program, follow the below steps,

Clone this repository

Build the jar by running mvn package and copy it into hadoop installed directory[/usr/local/hadoop/userLib]. Provide appropriate file permissions

Then login as Hadoop user and go to Hadoop directory[/usr/local/hadoop]

Create a input text file with some sentences[inputFile.txt]

Copy that file to hadoop by running the below command bin/hadoop fs -copyFromLocal inputFile.txt /wordcount/input/inputFile.txt

Run the jar bin/hadoop jar userLib/cascading-wordcount-1.0-jar-with-dependencices.jar /wordcount/input/inputFile.txt /wordcount/output
