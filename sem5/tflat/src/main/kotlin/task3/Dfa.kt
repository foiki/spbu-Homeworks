package task3

import java.util.*
import kotlin.Pair
import kotlin.collections.HashSet

class Dfa {
    val states: HashSet<State> = HashSet()
    private lateinit var statesArray: Array<State>
    val symbols: HashSet<String> = HashSet()
    private val transitions: Dictionary<Pair<State, String>, State> = Hashtable()

    private lateinit var table: Array<BooleanArray>

    fun get(state: State, symbol: String): State? {
        return transitions.get(Pair(state, symbol))
    }

    fun set(state: State, symbol: String, value: State) {
        transitions.put(Pair(state, symbol), value)
        states.add(state)
        symbols.add(symbol)
    }

    fun addState(name: String, isFinal: Boolean): State {
        val newState = State(name, isFinal);
        states.add(newState);
        return newState;
    }

    private fun markStartDistinguishable() {
        for (i in states.indices) {
            for (j in 0 until i + 1) {
                if (statesArray[i].getIsFinal().xor(statesArray[j].getIsFinal())) {
                    table[i][j] = true
                    table[j][i] = true
                }
            }
        }
        println("Отмечены заведомо различимые пары состояний:");
        print(table, statesArray)
        println()
    }

    private fun markDistinguishable() {
        val indexes: Dictionary<State, Int> = Hashtable()
        for (i in statesArray.indices) {
            indexes.put(statesArray[i], i)
        }
        val distinguishable: MutableList<Pair<Int, Int>> = LinkedList()
        while (true) {
            var k = 0
            for (symbol in symbols) {
                for (i in states.indices) {
                    for (j in 0 until i + 1) {
                        val iTransition = get(statesArray[i], symbol)
                        val jTransition = get(statesArray[j], symbol)

                        if (iTransition == null && jTransition == null) continue

                        if (((iTransition == null).xor(jTransition == null) || table[indexes[iTransition]][indexes[jTransition]])
                            && !table[i][j] && !distinguishable.contains(Pair(i, j))) {
                            distinguishable.add(Pair(i, j))
                            ++k
                        }
                    }
                }
            }

            if (k == 0) break

            for (pair in distinguishable) {
                table[pair.first][pair.second] = true
                table[pair.second][pair.first] = true
            }
        }

        println("Отмечено ${distinguishable.size} различимых пар:")
        print(table, statesArray)
        println()
    }

    private fun combineStates(): Pair<Dfa, Boolean> {
        val combinedStates: MutableList<HashSet<State>> = LinkedList()

        for (i in states.indices) {
            for (j in 0 until i + 1) {
                if (!table[i][j]) {
                    val combined = combinedStates.find { set ->
                        set.contains(statesArray[i]) || set.contains(statesArray[j])
                    }

                    if (combined == null) {
                        val toAdd: HashSet<State> = HashSet()
                        toAdd.add(statesArray[i])
                        toAdd.add(statesArray[j])
                        combinedStates.add(toAdd)
                    } else {
                        combined.add(statesArray[i]);
                        combined.add(statesArray[j]);
                    }
                }
            }
        }

        val minimal = Dfa()
        val wasMinimal = combinedStates.size == statesArray.size

        val combinedTransformed: MutableList<Pair<State, HashSet<State>>> = LinkedList()
        combinedStates.forEachIndexed { index, hashSet ->
            val newState = minimal.addState("q$index", hashSet.any { it.getIsFinal() })
            combinedTransformed.add(Pair(newState, hashSet))
        }

        for (combined in combinedTransformed) {
            for (symbol in symbols) {
                val transition = combinedTransformed.find { it.second.contains(get(combined.second.first(), symbol)) }

                if (transition != null) {
                    minimal.set(combined.first, symbol, transition.first)
                }
            }
        }
        return Pair(minimal, wasMinimal)
    }

    fun minimize(): Pair<Dfa, Boolean> {

        statesArray = states.toTypedArray()
        table = Array(states.size) { BooleanArray(states.size) }

        //Помечаем заведомо различные
        markStartDistinguishable()

        //Помечаем различимые пары
        markDistinguishable()

        //Комбинируем неразличимые пары
        return combineStates()
    }
}
