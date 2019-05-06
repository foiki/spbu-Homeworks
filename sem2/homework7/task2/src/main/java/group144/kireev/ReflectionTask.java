package group144.kireev;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/** Class to print some Class and to find differences between two classes */
public class ReflectionTask {

    /**
     * @param someClass class that declaration should be built
     * @return String representation of someClass
     */
    public static String printStructure(Class<?> someClass) {
        return "package group144.kireev;\n\n" + getClassStructure(someClass);
    }

    /**
     * Return string representation of class without package
     * @param someClass class that declaration should be built
     * @return String representation of someClass without package
     */
    private static String getClassStructure(Class<?> someClass) {
        return  getClassDeclaration(someClass) + " { \n \n"
                + getClassFields(someClass)
                + getConstructors(someClass)
                + getMethods(someClass)
                + getInnerClasses(someClass)
                + "\n}";
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of declaration of someClass
     */
    private static String getClassDeclaration(Class<?> someClass) {
        return getModifiers(someClass) + "class " + simpleNameWithParameters(someClass) + getSuperClass(someClass) + getInterfaces(someClass);
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of modifiers of someClass
     */
    private static String getModifiers(Class<?> someClass) {
        if (someClass.getModifiers() != 0) {
            return Modifier.toString(someClass.getModifiers()) + " ";
        }
        return "";
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of SuperClass of someClass
     */
    private static String getSuperClass(Class<?> someClass) {
        if (someClass.getSuperclass() != null) {
            return " extends " + simpleNameWithParameters(someClass.getSuperclass()) + " ";
        }
        return "";
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of interfaces of someClass
     */
    private static String getInterfaces(Class<?> someClass) {
        if (someClass.getInterfaces().length != 0) {
            return Arrays.stream(someClass.getInterfaces())
                    .map(ReflectionTask::simpleNameWithParameters)
                    .collect(Collectors.joining(", ", "implements ", ""));
        }
        return "";
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of simple name of the SomeClass and it's TypeParameters
     */
    private static String simpleNameWithParameters(Class<?> someClass) {
        return someClass.getSimpleName() + getTypeParameters(someClass);
    }

    /**
     * @param someClass class that declaration should be built
     * @return string as "<" + type parameters + ">".
     */
    private static String getTypeParameters(Class<?> someClass) {
        if (someClass != null && someClass.getTypeParameters().length != 0) {
            return Arrays.stream(someClass.getTypeParameters())
                    .map(TypeVariable::getName)
                    .collect(Collectors.joining(", ", "<", ">"));
        }
        return "";
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of fields of someClass
     */
    private static String getClassFields(Class<?> someClass) {
        if (someClass.getFields().length != 0) {
            return Arrays
                    .stream(someClass.getFields())
                    .map(ReflectionTask::fieldToString)
                    .collect(Collectors.joining(";\n", "", ";\n\n"));
        }
        return "";
    }

    /**
     * @param field class that declaration should be built
     * @return String representation of the field
     */
    private static String fieldToString(Field field) {
        return Modifier.toString(field.getModifiers()) + " " + simpleNameWithParameters(field.getType()) + " " + field.getName();
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of constructors of the someClass
     */
    private static String getConstructors(Class<?> someClass) {
        if (someClass.getConstructors().length != 0) {
            return Arrays
                    .stream(someClass.getDeclaredConstructors())
                    .map(constructor -> constructorToString(someClass, constructor))
                    .collect(Collectors.joining("\n", "", ""));
        }
        return "";
    }

    /**
     * @param someClass to get SimpleName for constructor
     * @param constructor that declaration should be built
     * @return String representation of constructor
     */
    private static String constructorToString(Class<?> someClass, Constructor constructor) {
        int[] counter = new int[] {0};
        return Modifier.toString(constructor.getModifiers()) + " " + someClass.getSimpleName()
                + Arrays.stream(constructor.getParameterTypes())
                .map(ReflectionTask::simpleNameWithParameters)
                .map(s ->  {counter[0] += 1; return s + " arg" + counter[0];})
                .collect(Collectors.joining(", ", "(", ") {\n}\n"));
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of methods of the someClass
     */
    private static String getMethods(Class<?> someClass) {
        if (someClass.getMethods().length != 0) {
            return Arrays.stream(someClass.getMethods())
                    .map(ReflectionTask::methodToString)
                    .collect(Collectors.joining("\n\n", "\n", "\n"));
        }
        return "";
    }

    /**
     * @param method method that declaration should be built
     * @return String representation of method
     */
    private static String methodToString(Method method) {
        int[] counter = new int[] {0};
        return Modifier.toString(method.getModifiers())
                + " " + simpleNameWithParameters(method.getReturnType())
                + " " + method.getName()
                + Arrays.stream(method.getParameterTypes())
                .map(ReflectionTask::simpleNameWithParameters)
                .map(s ->  {counter[0]+=1; return s + " arg" + counter[0];})
                .collect(Collectors.joining(", ", "(", ") {\n}"));
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of InnerClasses of the someClass
     */
    private static String getInnerClasses(Class<?> someClass) {
        if (someClass.getDeclaredClasses().length != 0) {
            StringBuilder result = new StringBuilder();
            for (Class<?> innerClass : someClass.getDeclaredClasses()) {
                result.append(getClassStructure(innerClass));
            }
            return "\n" + result.toString();
        }
        return "";
    }

    public boolean diffClasses(Class firstClass, Class secondClass) {
        StringBuilder differences = new StringBuilder();
        findDifferences(differences, firstClass, secondClass);
        if (differences.length() == 0) {
            System.out.println("Classes are equal!");
            return true;
        }
        System.out.println("Classes are not equal!");
        System.out.println(differences);
        return false;
    }

    private void findDifferences(StringBuilder differences, Class firstClass, Class secondClass) {
        findDifferencesInFields(differences, firstClass, secondClass);
    }

    private void findDifferencesInFields(StringBuilder differences, Class firstClass, Class secondClass) {
        Field[] fieldsFirst = firstClass.getDeclaredFields();
        Field[] fieldsSecond = secondClass.getDeclaredFields();

    }
}
