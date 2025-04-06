package online.afeibaili.command

import online.afeibaili.param.Parameter

class CommandBuilder {
    val command = Command()
    fun param(parameter: Parameter): CommandBuilder {
        command.add(parameter)
        return this
    }

    fun build(executor: (Command) -> Unit): Command {
        command.executor = executor
        return command
    }
}