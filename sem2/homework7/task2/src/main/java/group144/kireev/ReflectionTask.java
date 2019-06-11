package group144.kireev;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/** Class to print some Class and to find differences between two classes */
public class ReflectionTask {

    /**
     * @param someClass class that declaration should be built
     */
    public static void printStructureToFile(Class<?> someClass) throws IOException {
        FileWriter fileWriter = new FileWriter(someClass.getSimpleName() + ".java");
        fileWriter.write(printStructure(someClass));
        fileWriter.close();
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of someClass
     */
    public static String printStructure(Class<?> someClass) {
        if (someClass.getPackageName().length() != 0) {
            return "package group144.kireev.test;\n\n" + getStructure(someClass);
        }
        return getStructure(someClass);
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of someClass without package
     */
    private static String getStructure(Class<?> someClass) {
        return  getClassDeclaration(someClass) + "{\n\n"
                + getClassFields(someClass)
                + getConstructors(someClass)
                + getMethods(someClass)
                + getInnerClasses(someClass)
                + "}\n";
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
        if (someClass.getDeclaredFields().length != 0) {
            return Arrays
                    .stream(someClass.getDeclaredFields())
                    .map(ReflectionTask::fieldToString)
                    .collect(Collectors.joining(";\n\n", "", ";\n\n"));
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
                    .map(constructor -> constructorToString(someClass.getSimpleName(), constructor))
                    .collect(Collectors.joining("\n", "", ""));
        }
        return "";
    }

    /**
     * @param className to get SimpleName for constructor
     * @param constructor that declaration should be built
     * @return String representation of constructor
     */
    private static String constructorToString(String className, Constructor constructor) {
        int[] counter = new int[] {0};
        return Modifier.toString(constructor.getModifiers()) + " " + className
                + Arrays.stream(constructor.getParameterTypes())
                .map(ReflectionTask::simpleNameWithParameters)
                .map(s ->  {counter[0] += 1; return s + " arg" + counter[0];})
                .collect(Collectors.joining(", ", "(", ")"))
                + getExceptions(constructor) + " {\n}\n";

    }

    /**
     * @param constructor to get Exceptions
     * @return String representation of constructor exceptions
     */
    private static String getExceptions(Constructor constructor) {
        if (constructor.getGenericExceptionTypes().length != 0) {
            return " throws " + Arrays.stream(constructor.getExceptionTypes())
                    .map(Class::getSimpleName)
                    .collect(Collectors.joining(", ", "", ""));
        }
        return "";
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of methods of the someClass
     */
    private static String getMethods(Class<?> someClass) {
        if (someClass.getMethods().length != 0) {
            return Arrays.stream(someClass.getDeclaredMethods())
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
                .collect(Collectors.joining(", ", "(", ")"))
                + getExceptions(method) + " {\n"
                + "return" + getReturnValue(method.getReturnType().getSimpleName()) + ";\n}";
    }

    /**
     * @param method to get Exceptions
     * @return String representation of constructor exceptions
     */
    private static String getExceptions(Method method) {
        if (method.getGenericExceptionTypes().length != 0) {
            return " throws " + Arrays.stream(method.getExceptionTypes())
                    .map(Class::getSimpleName)
                    .collect(Collectors.joining(", ", "", ""));
        }
        return "";
    }

    /**
     * @param returnType to find return value for it
     * @return String with possible return value for this return type
     */
    private static String getReturnValue(String returnType) {
        switch (returnType) {
            case "void":
                return "";
            case "Integer":
                return " 41";
            case "Boolean":
                return  " true";
            case "String":
                return " a";
            case "Double":
                return " 0.0";
            case "Character":
                return " b";
            case "Byte":
                return " 42";
            case "Long":
                return " 43";
            case "Short":
                return " 44";
            case "Float":
                return " 45";
            case "int":
                return " 49";
            case "boolean":
                return  " false";
            case "double":
                return " 1.0";
            case "char":
                return " c";
            case "byte":
                return " 2";
            case "long":
                return " 3";
            case "short":
                return " 4";
            case "float":
                return " 5.0";
            default:
                return " null";
        }
    }

    /**
     * @param someClass class that declaration should be built
     * @return String representation of InnerClasses of the someClass
     */
    private static String getInnerClasses(Class<?> someClass) {
        if (someClass.getDeclaredClasses().length != 0) {
            StringBuilder result = new StringBuilder();
            for (Class<?> innerClass : someClass.getDeclaredClasses()) {
                result.append(getStructure(innerClass));
            }
            return "\n" + result.toString();
        }
        return "";
    }

    /**
     * @param firstClass first class to compare
     * @param secondClass second class to compare
     * @return if classes are equal
     */
    public static boolean diffClasses(Class firstClass, Class secondClass) {
        String differences = findDifferences(firstClass, secondClass);;
        if (differences.length() == 0) {
            System.out.println("Classes are equal!");
            return true;
        }
        System.out.println("Classes are not equal!");
        System.out.println(differences);
        return false;
    }

    /**
     * @return String representation of different of classes
     */
    private static String findDifferences(Class firstClass, Class secondClass) {
        return findDifferencesInFields(firstClass, secondClass)
                + findDifferencesInMethods(firstClass, secondClass)
                + findDifferencesInInnerClasses(firstClass, secondClass);
    }

    /**
     * @return String representation of different fields of classes
     */
    private static String findDifferencesInFields(Class firstClass, Class secondClass) {
        Field[] fieldsFirst = firstClass.getDeclaredFields();
        Field[] fieldsSecond = secondClass.getDeclaredFields();
        if (Arrays.equals(fieldsFirst, fieldsSecond)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Field field : fieldsFirst) {
            if (!isContain(fieldsSecond, field)) {
                result.append(fieldToString(field));
                result.append("\n");
            }
        }
        for (Field field : fieldsSecond) {
            if (!isContain(fieldsFirst, field)) {
                result.append(fieldToString(field));
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * @param fields where to find
     * @param field to find
     * @return if fields not contain field
     */
    private static boolean isContain(Field[] fields, Field field) {
        if (Modifier.isFinal(field.getModifiers()) && field.getName().equals("this$0$")) {
            return true;
        }
        String stringOfField = fieldToString(field);
        for (Field current : fields) {
            if (fieldToString(current).equals(stringOfField)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return String representation of different methods of classes
     */
    private static String findDifferencesInMethods(Class firstClass, Class secondClass) {
        Method[] methodsFirst = firstClass.getDeclaredMethods();
        Method[] methodsSecond = secondClass.getDeclaredMethods();
        if (Arrays.equals(methodsFirst, methodsSecond)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Method method : methodsFirst) {
            if (!isContain(methodsSecond, method)) {
                result.append(methodToString(method));
                result.append("\n");
            }
        }
        for (Method method : methodsSecond) {
            if (!isContain(methodsFirst, method)) {
                result.append(methodToString(method));
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * @param methods where to find
     * @param method to find
     * @return if methods not contain method
     */
    private static boolean isContain(Method[] methods, Method method) {
        String stringOfMethod = methodToString(method);
        for (Method current : methods) {
            if (methodToString(current).equals(stringOfMethod)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return String representation of different innerClasses of classes
     */
    private static String findDifferencesInInnerClasses(Class firstClass, Class secondClass) {
        Class<?>[] firstInnerClasses = firstClass.getDeclaredClasses();
        Class<?>[] secondInnerClasses = secondClass.getDeclaredClasses();
        StringBuilder result = new StringBuilder();
        if (firstInnerClasses.length != 0 && secondInnerClasses.length != 0) {
            if (Arrays.equals(firstInnerClasses, secondInnerClasses)) {
                return "";
            }
            for (Class<?> currentFirstClass : firstInnerClasses) {
                for (Class<?> currentSecondClass : secondInnerClasses) {
                    result.append(diffClasses(currentFirstClass, currentSecondClass));
                }
            }
        } else {
            for (Class<?> currentFirstClass : firstInnerClasses) {
                result.append(getStructure(currentFirstClass));
            }
            for (Class<?> currentSecondClass : secondInnerClasses) {
                result.append(getStructure(currentSecondClass));
            }
        }
        return result.toString();
    }
}
