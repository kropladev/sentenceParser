SentenceParser.
Application, based on standard in (text file), produce two files out.xml and out.csv containing sentences data with sorted words.

Building app:
mvn clean package

Runing in single thread mode (default mode).
java -Xmx32m -jar [path to jar]/SentenceParser-1.3.jar < [path to file]

example:
cd SentenceParser
java -Xmx32m -jar ./target/SentenceParser-1.3.jar < /home/kropla/dev/temp/sample-files/large.in

Time of generating files: about 9 seconds.



Runing on two threads mode (with parameter threadsMode):
java -Xmx32m -jar [path to jar]/SentenceParser-1.3.jar threadsMode < [path to file]

example:
cd SentenceParser
java -Xmx32m -jar ./target/SentenceParser-1.3.jar threadsMode < /home/kropla/dev/temp/sample-files/large.in

Time of generating files: about 12 seconds.


*Time measured on Ubuntu, Intel core i7 with 16GB RAM