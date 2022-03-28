package net.jfdf.test.kotlin

import net.jfdf.compiler.annotation.NoConstructors

class TestCompilerClass {
    @NoConstructors
    companion object Container {
        @JvmStatic
        fun printSmth(msg: String) {
            println(msg)
        }
    }
}