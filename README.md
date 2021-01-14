# Metrics Application 
#### John Shields - G00348436

## Advanced Object-Oriented Design Principles & Patterns Project
### *Measuring Software Design Quality Using the Reflection API*
Java application that uses reflection to analyse an arbitrary Java Application Archive (JAR) and calculates one or more design and structural metrics for each of the component classes in its object graph.

## The App
JavaFX App that processes Jar Files to read and print their Class Names, Package Names, and the Lines of Code on a GUI.
###### Make sure the Jar Files are in the Class Path.

## Development Environment 
* Java JDK Version: 15
* Java FX SDK Version: 15.0.1
* OS Name: Microsoft Windows 10
* Intellij IDE 2020.3.1 (Professional Edition)

## Run the App 
* From the 'metrics.jar' File
### Requirements
* [Git](https://git-scm.com/downloads)
* Java JDK Version: [15](https://jdk.java.net/15/)
* Java FX SDK Version: [15.0.1](https://gluonhq.com/products/javafx/)

### Open a directory of your choice in Command-Line and enter:
```cmd
$ git clone https://github.com/johnshields/Metrics-App.git
 ```
### Open the repository directory in Command-Line and enter:
```java
$ cd application 
$ java -cp ./metrics.jar;./your_javafx_directory/lib/* --enable-preview js.metrics.app.Runner
```

###### END OF README
