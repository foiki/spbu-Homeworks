package group144.kireev;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static group144.kireev.ReflectionTask.printStructure;


public class Main {

    public static void main(String[] args) throws IOException {
        List string = new LinkedList();
        FileWriter fileWriter = new FileWriter("SomeClass.java");
        fileWriter.write(printStructure(string.getClass()));
        fileWriter.close();
        printStructure(string.getClass());
    }
}
