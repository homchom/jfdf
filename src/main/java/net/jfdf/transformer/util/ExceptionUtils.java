package net.jfdf.transformer.util;

import net.jfdf.jfdf.mangement.Control;
import net.jfdf.jfdf.mangement.Player;
import net.jfdf.jfdf.values.IText;
import net.jfdf.jfdf.values.Tags;
import net.jfdf.jfdf.values.Text;

public class ExceptionUtils {
    public static void throwException(Exception exception) {
        Player.getAllPlayers().SendMessage(new IText[]{ new Text().Set("&c" + exception) }, Tags.AlignmentMode.REGULAR, Tags.TextValueMerging.NO_SPACES);
        Control.End();
    }
}
