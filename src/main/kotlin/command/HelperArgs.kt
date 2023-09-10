package command

class HelperArgs {
    private val matches: MutableList<String> = mutableListOf()

    fun addMatch(match: String) {
        this.matches.add(match)
    }

    fun removeMatch() {
        this.matches.removeAt(0)
    }

    fun getMatch(): String {
        return matches[0]
    }
}
