package group144.kireev;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;
import static group144.kireev.ReflectionTask.printStructure;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> sample = classLoader.loadClass("group144.kireev.test.SomeClass");
        List string = new LinkedList();
        FileWriter fileWriter = new FileWriter("SomeClass.java");
        fileWriter.write(printStructure(sample));
        fileWriter.close();
        printStructure(string.getClass());
    }
}
