package online.afeibaili

import online.afeibaili.command.CommandBuilder
import online.afeibaili.command.CommandSet
import online.afeibaili.param.Parameter

fun main() {

    var commandSet: CommandSet = CommandSet()
    commandSet.add(
        CommandBuilder().param(Parameter("run", "r", { s, m ->
            s == "9"
        })).build({ command, context ->
            command.otherParam!!.forEach { println(it) }
        }, ArrayList<String>())
    )

    var array: Array<String> =

        "run 9 -jar xxx.jar"

            .trim().split("\\s+".toRegex()).toTypedArray()

    println(commandSet.parses(array))
}