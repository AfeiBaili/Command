package online.afeibaili.command

import online.afeibaili.text.Message
import online.afeibaili.text.Style

class CommandSet {
    private var set = HashSet<Command>()

    fun add(command: Command) {
        set.add(command)
    }

    fun parses(args: Array<String>): Message {
        var matchMessage = Message(0, 0)
            .append("没有可匹配的命令", Style.RED_COLOR)
        for (command in set) {
            var message: Message = command.parse(args)
            matchMessage = if (matchMessage.matchValue < message.matchValue) message else matchMessage
        }
        return matchMessage
    }
}