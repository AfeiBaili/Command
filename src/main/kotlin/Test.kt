package online.afeibaili

fun main() {
    var i = 0;
    var j = 1;
    var k = 2;

    if (k.also { i = 2 } == 2) {
        println(i)
    }
}