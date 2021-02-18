package task3

fun main() {

    val automaton = Dfa()

    val q0 = automaton.addState("q0", true)
    val q1 = automaton.addState("q1", false)
    val q2 = automaton.addState("q2", false)
    val q3 = automaton.addState("q3", false)
    val q4 = automaton.addState("q4", false)
    val q5 = automaton.addState("q5", false)
    val q6 = automaton.addState("q6", false)

    automaton.set(q0, "d", q1)
    automaton.set(q1, "d", q1)
    automaton.set(q1, ".", q2)
    automaton.set(q1, "e", q3)
    automaton.set(q1, "eps", q0)
    automaton.set(q2, "d", q4)
    automaton.set(q3, "d", q5)
    automaton.set(q3, "+-", q6)
    automaton.set(q4, "d", q4)
    automaton.set(q4, "e", q3)
    automaton.set(q4, "eps", q0)
    automaton.set(q5, "d", q5)
    automaton.set(q5, "eps", q0)
    automaton.set(q6, "d", q5)

    //var minimal = automaton.Minimize();

    val automaton2 = Dfa()

    val a = automaton2.addState("q1", false);
    val b = automaton2.addState("q2", false);
    val c = automaton2.addState("q3", true);
    val d = automaton2.addState("q4", false);
    val e = automaton2.addState("q5", true);

    automaton2.set(a, "0", b)
    automaton2.set(a, "1", d)
    automaton2.set(b, "0", b)
    automaton2.set(b, "1", c)
    automaton2.set(c, "0", d)
    automaton2.set(c, "1", e)
    automaton2.set(d, "0", d)
    automaton2.set(d, "1", e)
    automaton2.set(e, "0", b)
    automaton2.set(e, "1", c)

    println("Исходный автомат:")
    print(automaton)
    val minimal = automaton.minimize()

    if (minimal.second) {
        println("Автомат уже был минимальным, минимизация невозможна.")
    }
    else {
        println("Минимальный автомат:")
        print(minimal.first)
    }
}