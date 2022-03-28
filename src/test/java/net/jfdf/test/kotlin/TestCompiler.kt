package net.jfdf.test.kotlin

import net.jfdf.compiler.JFDFCompiler
import net.jfdf.jfdf.mangement.CodeManager

fun main() {
    JFDFCompiler.compileClass(Test::class.java)
    JFDFCompiler.compileClass(TestCompilerClass.Container::class.java)

    try {
        val codeOutputArray = CodeManager.instance.codeValues
        for (codeOutput in codeOutputArray) {
            println("-=-=-=- A Code Template -=-=-=-")
            println(codeOutput)
        }
    } catch (e : Exception) {
        e.printStackTrace()
    }
}