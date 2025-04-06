package online.afeibaili.text

enum class Style {
    RESET(0, "重置"),
    BOLD(1, "粗体或增加强度"),
    ITALIC(3, "斜体"),
    UNDER_LINE(4, "下划线"),
    BLINK(5, "闪烁"),
    REVERSAL(7, "前景色反色"),
    DELETE_LINE(9, "删除线"),
    DEFAULT(10, "默认"),
    DOUBLE_UNDERLINE(21, "双下划线"),
    ENCIRCLEMENT_LINE(51, "包围线"),
    UP_LINE(51, "上划线"),
    SUPERSCRIPT(73, "上标"),
    SUBSCRIPT(74, "下标"),

    BLACK_COLOR(30, "黑色"),
    DARK_RED_COLOR(31, "深红"),
    DARK_GREEN_COLOR(32, "深绿色"),
    DARK_YELLOW_COLOR(33, "深黄色"),
    BLUE_COLOR(34, "蓝色"),
    PURPLE_COLOR(35, "紫色"),
    DARK_CYAN_COLOR(36, "深青色"),
    GRAY(37, "灰色"),

    BLACK_COLOR_BACKGROUND(30, "黑色背景"),
    DARK_RED_COLOR_BACKGROUND(31, "深红背景"),
    DARK_GREEN_COLOR_BACKGROUND(32, "深绿色背景"),
    DARK_YELLOW_COLOR_BACKGROUND(33, "深黄色背景"),
    BLUE_COLOR_BACKGROUND(34, "蓝色背景"),
    PURPLE_COLOR_BACKGROUND(35, "紫色背景"),
    DARK_CYAN_COLOR_BACKGROUND(36, "青色背景"),
    GRAY_BACKGROUND(37, "灰色背景"),

    DARK_GRAY(90, "深灰"),
    RED_COLOR(91, "红色"),
    GREEN_COLOR(92, "绿色"),
    YELLOW_COLOR(93, "黄色"),
    SKY_BLUE_COLOR(94, "天蓝色"),
    PINK_COLOR(95, "粉色"),
    CYAN_COLOR(96, "青色"),
    WHITE_COLOR(97, "白色"),

    DARK_GRAY_BACKGROUND(100, "深灰背景"),
    RED_COLOR_BACKGROUND(101, "红色背景"),
    GREEN_COLOR_BACKGROUND(102, "绿色背景"),
    YELLOW_COLOR_BACKGROUND(103, "黄色背景"),
    SKY_BLUE_COLOR_BACKGROUND(104, "天蓝色背景"),
    PINK_COLOR_BACKGROUND(105, "粉色背景"),
    CYAN_COLOR_BACKGROUND(106, "青色背景"),
    WHITE_COLOR_BACKGROUND(107, "白色背景"),
    ;

    var code: String
    var style: String

    constructor(code: Int, style: String) {
        this.code = "\u001B[${code}m"
        this.style = style
    }

    companion object {

        fun byName(name: String): Style {
            return Style.entries.find { it.style == name } ?: DEFAULT
        }
    }

    override fun toString(): String {
        return code
    }
}