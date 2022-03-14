package net.jfdf.jfdf.mangement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.blocks.ControlBlock;
import net.jfdf.jfdf.blocks.ControlBlock.Action;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;

public class Control {
	/**
	 * Stops a Repeat sequence and<br>
	 * continues to the next code block.
	 */
	public static void StopRepeat() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new ControlBlock("StopRepeat").SetItems(items));
	}

	/**
	 * Skips the rest of a Function<br>
	 * sequence and returns to the<br>
	 * block it was called from.
	 */
	public static void Return() {
		CodeManager.instance.addCodeBlock(new ControlBlock("Return"));
	}

	/**
	 * @param returnTimes Return times
	 */
	public static void ReturnNTimes(INumber returnTimes) {
		CodeManager.instance.addCodeBlock(new ControlBlock("ReturnNTimes").SetItems(returnTimes));
	}

	/**
	 * Skips the rest of this repeat<br>
	 * statement's code and continues<br>
	 * to the next repetition.
	 */
	public static void Skip() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new ControlBlock("Skip").SetItems(items));
	}

	/**
	 * Stops the current event<br>
	 * thread. Any code after this<br>
	 * block will not be executed.
	 */
	public static void End() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new ControlBlock("End").SetItems(items));
	}

	/**
	 * Pauses the current code<br>
	 * sequence for a duration of<br>
	 * ticks, seconds, or minutes.
	 *
	 * @param waitDuration Wait duration (Optional)
	 */
	public static void Wait(INumber waitDuration, Tags.TimeUnit timeUnitTag) {
		List<CodeValue> items = new ArrayList<>();

		if(waitDuration != null) items.add(waitDuration);
		CodeManager.instance.addCodeBlock(new ControlBlock("Wait").SetItems(items).SetTags(new Tag("Time Unit", timeUnitTag.getJSONValue())));
	}
}
