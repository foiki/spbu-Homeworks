package task6;

import static task6.Util.initGrammar;
import static task6.Util.initTestGrammar;

public class Main {
    public static void main(String[] args) {
        char[][] rules = initGrammar();
        printGrammar(rules);
        FunctionFirst functionFirst = new FunctionFirst(rules);
        System.out.println("Результат функции FIRST:");
        char[][] firstResult = functionFirst.findFirst();

        FunctionFollow functionFollow = new FunctionFollow(rules, firstResult);
        System.out.println("Результат Функции FOLLOW:");
        functionFollow.findFollow();
    }
    public static void printGrammar(char[][] grammar) {
        System.out.println("Исходная грамматика: ");
        for (int i = 0; i < grammar.length; i++) {
            if (grammar[i][0] == ' ') {
                System.out.println(""); return;
            }
            System.out.println(grammar[i]);
        }
    }
}
