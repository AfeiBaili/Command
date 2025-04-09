package online.afeibaili.command

import online.afeibaili.param.Parameter

class CommandBuilder {
    val command = Command()
    fun param(parameter: Parameter): CommandBuilder {
        command.add(parameter)
        return this
    }

    fun build(executor: (Command, MutableMap<String, Any>) -> Unit, otherParams: ArrayList<String>? = null): Command {
        command.executor = executor
        command.otherParam = otherParams
        return command
    }

    fun build(executor: (Command, MutableMap<String, Any>) -> Unit): Command {
        command.executor = executor
        return command
    }
}