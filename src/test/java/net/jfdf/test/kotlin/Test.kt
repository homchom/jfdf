package net.jfdf.test.kotlin

import net.jfdf.compiler.annotation.NoConstructors
import net.jfdf.compiler.util.CodeValueArrayBuilder
import net.jfdf.jfdf.blocks.PlayerEventBlock
import net.jfdf.jfdf.mangement.Player
import net.jfdf.jfdf.mangement.PlayerEvent
import net.jfdf.jfdf.values.IText
import net.jfdf.jfdf.values.Tags
import net.jfdf.jfdf.values.Text

@NoConstructors
class Test {
    @PlayerEvent(eventType = PlayerEventBlock.Event.JOIN)
    fun onJoin() {
        println("Hi !")

        Player.getCurrentSelection().SendMessage(
                CodeValueArrayBuilder.start<IText>(1).next(Text().Set("Hi ! (x2)")).array,
                Tags.AlignmentMode.REGULAR,
                Tags.TextValueMerging.NO_SPACES
        )

        TestCompilerClass.printSmth("Hi ! (x3)")
    }
}