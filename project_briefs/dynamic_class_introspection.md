## Dynamic Class Introspection
The contents of a Java Application Archive can be read as follows using instances of the classes

```java
java.util.jar.JarInputStream and java.util.jar.JarEntry :

JarInputStream in = new JarInputStream(new FileInputStream(new File("mylib.jar")));
JarEntry next = in.getNextJarEntry();
while (next != null) {
   if (next.getName().endsWith(".class")) {
   String name = next.getName().replaceAll("/", "\\.");
   name = name.replaceAll(".class", "");
   if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
   System.out.println(name);
 }
 next = in.getNextJarEntry();
}
 ```

Classes can be dynamically loaded (by name) through invoking the Java class loader. Note that
if the class is not found, i.e. not on the CLASSPATH, a ClassNotFoundException will be
thrown:

```java
Class cls = Class.forName(“ie.gmit.sw.Stuff”);
```
Once an initial class is loaded (of type Class), every other class in an application can be
processed in a similar way as an object graph is a recursive structure. Consider the following
types of processing that can be performed on a Class:
```java
Package pack = cls.getPackage(); //Get the package
boolean iface = cls.isInterface(); //Is it an interface?
Class[] interfaces = cls.getInterfaces(); //Get the set of interface it implements
Constructor[] cons = cls.getConstructors(); //Get the set of constructors
Class[] params = cons[i].getParameterTypes(); //Get the parameters
Field[] fields = cls.getFields(); //Get the fields / attributes
Method[] methods = cls.getMethods(); //Get the set of methods
Class c = methods[i].getReturnType(); //Get a method return type
Class[] params = methods[i]
```
