package group144.kireev;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import static org.junit.jupiter.api.Assertions.*;

class ReflectionTaskTest {
    @Test
    void printStructureTest() throws ClassNotFoundException, IOException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> sample = classLoader.loadClass("group144.kireev.test.SomeClass");
        String result = ReflectionTask.printStructure(sample);
        String expected = "package group144.kireev.test;\n\n"
                + "public class SomeClass<E> extends Object {\n\n"
                + "private final Node node;\n\n"
                + "public SomeClass(Integer arg1, String arg2) throws NullPointerException {\n}\n\n"
                + "public String printer(char arg1) {\nreturn a;\n}\n\n"
                + "public static class Node extends Object {\n\n"
                + "private int number;\n\n"
                + "public Node(int arg1) {\n}\n\n"
                + "private void add(char arg1) {\nreturn;\n}\n}\n}\n";
        assertEquals(result, expected);
    }

    @Test
    void equalsClassTest() throws ClassNotFoundException, IOException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> sample = classLoader.loadClass("group144.kireev.List");
        ReflectionTask.printStructureToFile(sample);

        ClassLoader result = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> resultClass = result.loadClass("group144.kireev.test.List");
        assertTrue(ReflectionTask.diffClasses(resultClass, sample));
    }

    @Test
    void differentFieldsTest() throws ClassNotFoundException, IOException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> sample = classLoader.loadClass("group144.kireev.ListField");
        ReflectionTask.printStructureToFile(sample);

        ClassLoader result = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> resultClass = result.loadClass("group144.kireev.test.List");
        assertFalse(ReflectionTask.diffClasses(resultClass, sample));
    }

    @Test
    void differentConstructorTest() throws ClassNotFoundException, IOException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> sample = classLoader.loadClass("group144.kireev.ListMethod");
        ReflectionTask.printStructureToFile(sample);

        ClassLoader result = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> resultClass = result.loadClass("group144.kireev.test.List");
        assertFalse(ReflectionTask.diffClasses(resultClass, sample));
    }
}