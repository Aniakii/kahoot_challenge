class TrieNode {
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
    var isEndOfWord: Boolean = false
}

class Trie {
    private val root: TrieNode = TrieNode()

    fun insert(word: String) {
        var node = root
        for (char in word) {
            node = node.children.getOrPut(char) { TrieNode() }
        }
        node.isEndOfWord = true
    }

    private fun searchPrefix(prefix: String): TrieNode? {
        var node = root
        for (char in prefix) {
            node = node.children[char] ?: return null
        }
        return node
    }

    private fun getWords(node: TrieNode, prefix: String): List<String> {
        val results = mutableListOf<String>()
        if (node.isEndOfWord) {
            results.add(prefix)
        }

        for ((char, childNode) in node.children) {
            results.addAll(getWords(childNode, prefix + char))
        }

        return results
    }


    fun autocomplete(query: String): List<String> {
        val node = searchPrefix(query)
        return node?.let { getWords(it, query) } ?: emptyList()
    }
}
