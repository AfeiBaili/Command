package online.afeibaili.param

class Parameter(
    val name: String,
    val alias: String?,
    val validator: ((String, Map<String, Any>) -> Boolean)? = null,
    val isValue: Boolean = true,
    var value: String? = null,
) {

    constructor(name: String, alias: String?, isValue: Boolean = false) : this(name, alias, null, isValue)

    fun equalsParam(param: String): Boolean = name == param || alias == param

    fun equalsArray(array: Array<String>): Boolean {
        return array.contains(name) || array.contains(alias)
    }

    fun findByArray(args: Array<String>): Int =
        args.indexOf(name).let { if (it != -1) return it else return args.indexOf(alias) }

    override fun toString(): String {
        return "Parameter(name='$name', alias=$alias, validator=$validator, value=$value, isValue=$isValue)"
    }

}