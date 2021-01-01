### Advanced Object-Oriented Design Principles & Patterns Project

# Project Breakdown

## Requirements:
Create a Java application that uses reflection to analyse an arbitrary Java
Application Archive (JAR) and calculates one or more design and structural metrics for each
of the component classes in its object graph. Use any number of or combination of metrics...
* INCLUDING
  - LOCs
   - count the lines of code
  - SLOC
   - count the lines of code with out spaces & comments
  - CP
  - cyclomatic complexity
  - positional stability ('easiest one')
  - CK metrics
  - MOOD metrics


*  Application should process a JAR archive provided by a user
  - Compute one or more metrics for each class and present the results in a structured format, i.e. a JavaFX
GUI or a command line console user-interface. (GUI with Model-View-Controller Lab)


* The results should be stored in an instance of Micro Stream DB - (Object Persistence Lab)
  - Have one or more stream-based queries for displaying results (a single query will suffice).
  - Singleton pattern


* UML diagram of your design, README (design rationale) and Java Docs.

* Do NOT use <u>modules.</u>

## Further Breakdown
* Get an application up and running before implementing design patterns and etc...

*Do the following*:
* Through a GUI or UI (Java FX) - let a Jar file be able to be specified
* Use metrics LOC, SLOC & positional stability
 - Processes Jar File - then outputs the SLOC, LOC etc...
* Process a JAR archive provided by a user
* Store results in an instance of Micro Stream DB
  - stream-based queries for displaying results
* Package project into Jar file and run from cli



## Useful links:
[Project Workshop: @ 21:45](https://web.microsoftstream.com/video/92ca6d06-f35e-463b-b662-2a6bd12ffd4a)

#### GUI with Model-View-Controller Lab
[Lab Tutorial: GUI with Model-View-Controller](https://web.microsoftstream.com/video/2a8da55d-c5a0-4c24-8aa3-a426201a814f)

[Source Code: GUI with Model-View-Controller](https://learnonline.gmit.ie/mod/resource/view.php?id=129718)

#### Object Persistence Lab
[Lab Tutorial: Object Persistence with Streams](https://web.microsoftstream.com/video/6ede3e10-3e6f-4dad-9505-c19aef3acb1d)

[Source Code: Object Persistence with Streams ](https://learnonline.gmit.ie/mod/resource/view.php?id=64078)

[Source Code: MicroStream 4.0 Embedded Object Database](https://learnonline.gmit.ie/mod/resource/view.php?id=64081)
