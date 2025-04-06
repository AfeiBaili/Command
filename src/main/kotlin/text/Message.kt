package online.afeibaili.text

class Message(val level: Int, var matchValue: Int) : Iterable<String> {
    private var first: Text? = null
    private var last: Text? = null
    var count: Int = 0

    private inner class Text(var content: String, style: Style, var next: Text? = null) {
        init {
            content = "${style.code}$content"
        }
    }

    fun append(content: String, style: Style = Style.DEFAULT): Message {
        var text: Text = Text(content, style)
        if (last == null) {
            first = text
            last = text
        } else {
            last!!.next = text
            last = text
        }
        count++
        return this
    }

    fun styles(vararg style: Style): Message {
        for (style in style) append("", style)
        return this
    }

    fun reset(style: Style): Message {
        return styles(Style.RESET, style)
    }

    override fun toString(): String {
        var sb: StringBuilder = StringBuilder()
        forEach { sb.append(it) }
        return sb.append(Style.RESET).toString()
    }

    override fun iterator(): Iterator<String> {
        return object : Iterator<String> {
            private var current = first

            override fun next(): String {
                var value: String? = current?.content
                current = current?.next
                return value ?: throw NoSuchElementException()
            }

            override fun hasNext(): Boolean = current != null
        }
    }

    companion object {
        const val SUCCESS = 0
        const val INFO = 1
        const val WARN = 2
        const val ERROR = 3
    }
}
