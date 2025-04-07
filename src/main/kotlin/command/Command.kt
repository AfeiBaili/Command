package online.afeibaili.command

import online.afeibaili.param.Parameter
import online.afeibaili.text.Message
import online.afeibaili.text.Style

class Command : Iterable<Parameter> {
    var count = 0
    var executor: ((Command, MutableMap<String, Any>) -> Unit)? = null
    var context: MutableMap<String, Any> = HashMap()
    private var first: Fragment? = null
    private var last: Fragment? = null

    private inner class Fragment(val value: Parameter, var next: Fragment? = null)

    fun add(value: Parameter) {
        val newValue: Fragment = Fragment(value)
        if (last == null) {
            first = newValue
            last = newValue
        } else {
            last!!.next = newValue
            last = newValue
        }
        count++
    }

    fun parse(args: Array<String>): Message {
        var iterator: Iterator<Parameter> = iterator()
        var command: String = args.joinToString(" ")

        while (iterator.hasNext()) {
            val parameter = iterator.next()

            if (!parameter.equalsArray(args)) {
                return Message(Message.ERROR, 0)
                    .append("找不到参数：", style = Style.RED_COLOR)
                    .append("\n\t-> 需要：${parameter.name}")
                    .append("\n\t-> 提供：$command")
            }

            var paramIndex: Int = parameter.findByArray(args)
            var paramCount = 0
            var valueCount = 0
            forEach { it ->
                if (it.isValue) {
                    paramCount++
                    valueCount++
                } else paramCount++
            }
            val errorPoint = if (paramCount + valueCount < args.size) "多余" else "不足"
            if (args.size != paramCount + valueCount) return Message(Message.ERROR, 1)
                .append("参数$errorPoint：$command", style = Style.RED_COLOR)
                .append("\n\t-> 需要参数: ${paramCount}个")
                .append("\n\t-> 需要值: ${valueCount}个")
                .append("\n\t-> 总共提供：${args.size}个")

            var paramValue = ""
            if (parameter.isValue
                && parameter.validator?.let { it ->
                    paramValue = args[paramIndex + 1]
                    it.invoke(paramValue, context)
                } == false
            ) {
                parameter.value = paramValue
                var paramValueIndex: Int = command.lastIndexOf(paramValue)
                var subCommand: String = command.substring(0, paramValueIndex)
                return Message(Message.ERROR, 2)
                    .append("不可用的值：", style = Style.RED_COLOR)
                    .append(subCommand)
                    .append(
                        command.substring(paramValueIndex, subCommand.length + paramValue.length),
                        style = Style.UNDER_LINE
                    )
                    .reset(Style.RED_COLOR)
                    .append(command.substring(subCommand.length + paramValue.length))
                    .append("\n\t-> $paramValue")
            }
        }
        this.executor!!.invoke(this, context)
        return Message(Message.SUCCESS, 3)
    }

    override fun iterator(): Iterator<Parameter> {
        return object : Iterator<Parameter> {
            private var current = first

            override fun next(): Parameter {
                val value = current?.value
                current = current?.next
                value?.let { return it }
                throw NoSuchElementException("找不到定义参数")
            }

            override fun hasNext(): Boolean = current != null
        }
    }
}