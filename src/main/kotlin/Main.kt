package online.afeibaili

import online.afeibaili.command.CommandBuilder
import online.afeibaili.command.CommandSet
import online.afeibaili.param.Parameter

fun main() {

    var commandSet: CommandSet = CommandSet()
    commandSet.add(
        CommandBuilder()
            .param(Parameter("add", "a", { c, m ->
                println(c)
                true
            }))
            .param(Parameter("list", "l", false))
            .param(Parameter("alias", "l", { c, m ->
                println(c)
                true
            }))
            .build { c, m -> println("成功") }
    )
    commandSet.add(
        CommandBuilder()
            .param(Parameter("list", "l", isValue = false))
            .build { c, m -> println("第二个命令执行成功") })


    var array: Array<String> =

        "alias 888 list add path"

            .trim().split("\\s+".toRegex()).toTypedArray()

    println(commandSet.parses(array))
}