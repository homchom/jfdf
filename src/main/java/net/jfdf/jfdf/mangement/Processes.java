package net.jfdf.jfdf.mangement;

import net.jfdf.jfdf.blocks.CallProcessBlock;
import net.jfdf.jfdf.values.*;

public class Processes {
    public static void Start(String processName, Tags.TargetMode targetMode, Tags.LocalVariables localVariablesMode) {
        CodeManager.instance.addCodeBlock(
                new CallProcessBlock(processName)
                .SetTags(
                        new Tag("Target Mode", targetMode.getJSONValue()),
                        new Tag("Local Variables", localVariablesMode.getJSONValue())
                )
        );
    }
}