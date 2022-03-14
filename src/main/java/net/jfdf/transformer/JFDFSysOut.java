package net.jfdf.transformer;

import net.jfdf.jfdf.mangement.Player;
import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.IText;
import net.jfdf.jfdf.values.Tags;
import net.jfdf.jfdf.values.Text;

import java.io.PrintStream;

@Deprecated(forRemoval = true)
public class JFDFSysOut {
    public static void println(PrintStream printStream, Object object) {
        if (object instanceof CodeValue) {
            if(printStream == System.out) {
                Player.getCurrentSelection().SendMessage(new IText[]{(IText) object},
                        Tags.AlignmentMode.REGULAR,
                        Tags.TextValueMerging.ADD_SPACES);
            } else if(printStream == System.err) {
                Player.getCurrentSelection().SendMessage(new IText[]{new Text().Set("\u00A7c"), (IText) object},
                        Tags.AlignmentMode.REGULAR,
                        Tags.TextValueMerging.ADD_SPACES);
            } else {
                throw new IllegalStateException("Illegal PrintStream (Isn't System.out or System.err)");
            }
        } else {
            printStream.println(object);
        }
    }
}
