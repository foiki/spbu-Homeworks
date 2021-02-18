package task3

class State(private val name: String, private var isFinal: Boolean) {

    override fun equals(other: Any?): Boolean {
        if (other is State) {
            return other.name == name;
        }
        return false;
    }

    override fun toString(): String {
        return name;
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    fun getName(): String {
        return name
    }

    fun getIsFinal(): Boolean {
        return isFinal
    }

}