package net.jfdf.jfdf.mangement;

import java.util.ArrayList;
import java.util.List;

import net.jfdf.jfdf.blocks.BracketBlock;
import net.jfdf.jfdf.blocks.RepeatBlock;
import net.jfdf.jfdf.blocks.RepeatBlock.Type;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;
import net.jfdf.jfdf.values.Tags.ChangeLocationRotation;
import net.jfdf.jfdf.values.Tags.IncludeOriginBlock;
import net.jfdf.jfdf.values.Tags.PointLocationsInwards;

public class Repeat {
	/**
	 * Repeats code indefinitely.
	 * 
	 * Chest Parameters:
	 * (None)
	 * 
	 * Additional Info:
	 * > A Control: Wait block must
	 * be activated at least once
	 * for every 10 iterations.
	 */
	public static void Forever() {
		CodeManager.instance.addCodeBlock(new RepeatBlock(Type.FOREVER));
		CodeManager.instance.addCodeBlock(new BracketBlock(false, true));
	}

	/**
	 * Repeats code as long as a
	 * condition is true.
	 * 
	 * Additional Info:
	 * > A Control: Wait block must
	 * be activated at least once
	 * for every 10 iterations.
	 */
	public static void While(SubIf condition) {
		CodeManager.instance.addCodeBlock(new RepeatBlock(condition.GetType(), condition.IsInversed()).SetItems(condition.GetItems()).SetTags(condition.GetTags()));
		CodeManager.instance.addCodeBlock(new BracketBlock(false, true));
	}

	/**
	 * Repeats code multiple times.
	 * 
	 * Chest Parameters:
	 * Variable* - Gets the
	 * current index each iteration
	 * Number - Amount
	 * 
	 * *Optional
	 * 
	 * Additional Info:
	 * > A Control: Wait block must
	 * be activated at least once
	 * for every 10 iterations.
	 */
	public static void MultipleTimes(Variable iterationIndex, INumber amount) {
		List<CodeValue> items = new ArrayList<>();
		if(iterationIndex != null) items.add(iterationIndex);
		items.add(amount);
		CodeManager.instance.addCodeBlock(new RepeatBlock(Type.MULTIPLE_TIMES).SetItems(items));
		CodeManager.instance.addCodeBlock(new BracketBlock(false, true));
	}

	/**
	 * Repeats code once for each
	 * number on a number line.
	 * 
	 * Chest Parameters:
	 * Variable* - Gets the
	 * current number each iteration
	 * Number - Start of range
	 * Number - End of range
	 * Number* - Interval
	 * > Default = 1
	 * 
	 * * Optional
	 * 
	 * Additional Info:
	 * > A Control: Wait block must
	 * be activated at least once
	 * for every 10 iterations.
	 */
	public static void OnRange(Variable iterationIndex, INumber startOfRange, INumber endOfRange, Number interval) {
		List<CodeValue> items = new ArrayList<>();
		if(iterationIndex != null) items.add(iterationIndex);
		items.add(startOfRange);
		items.add(endOfRange);
		if(interval != null) items.add(interval);
		CodeManager.instance.addCodeBlock(new RepeatBlock(Type.ON_RANGE).SetItems(items));
		CodeManager.instance.addCodeBlock(new BracketBlock(false, true));
	}

	/**
	 * Repeats code once for each
	 * index of a list.
	 * 
	 * Chest Parameters:
	 * Variable - Gets the
	 * current value each iteration
	 * List - List to repeat through
	 * 
	 * Additional Info:
	 * > A Control: Wait block must
	 * be activated at least once
	 * for every 10 iterations.
	 */
	public static void ForEach(Variable iterationValue, Variable listToRepeatThrough) {
		List<CodeValue> items = new ArrayList<>();
		items.add(iterationValue);
		items.add(listToRepeatThrough);
		CodeManager.instance.addCodeBlock(new RepeatBlock(Type.FOREACH).SetItems(items));
		CodeManager.instance.addCodeBlock(new BracketBlock(false, true));
	}

	/**
	 * Repeats code once for each
	 * block in a region
	 * 
	 * Chest Parameters:
	 * Variable - Gets the
	 * current block each iteration
	 * Location - Start of region
	 * Location - End of region
	 * 
	 * Additional Info:
	 * > A Control: Wait block must
	 * be activated at least once
	 * for every 10 iterations.
	 */
	public static void OnGrid(Variable iterationBlock, ILocation startOfRegion, ILocation endOfRegion) {
		List<CodeValue> items = new ArrayList<>();
		items.add(iterationBlock);
		items.add(startOfRegion);
		items.add(endOfRegion);
		CodeManager.instance.addCodeBlock(new RepeatBlock(Type.ON_GRID).SetItems(items));
		CodeManager.instance.addCodeBlock(new BracketBlock(false, true));
	}

	/**
	 * Repeats code once for each
	 * block adjancent to a location.
	 * 
	 * Chest Parameters:
	 * Variable - Gets the
	 * current location each iteration
	 * Location - Center block
	 * 
	 * Additional Info:
	 * > A Control: Wait block must
	 * be activated at least once
	 * for every 10 iterations.
	 */
	public static void Adjacently(Variable iterationBlock, ILocation startOfRegion, ILocation endOfRegion, Tags.Pattern pattern, IncludeOriginBlock includeOriginBlock, ChangeLocationRotation changeLocationRotation) {
		List<CodeValue> items = new ArrayList<>();
		items.add(iterationBlock);
		items.add(startOfRegion);
		items.add(endOfRegion);
		CodeManager.instance.addCodeBlock(new RepeatBlock(Type.ON_GRID).SetItems(items).SetTags(new Tag("Pattern", pattern.getJSONValue()), new Tag("Include Origin Block", includeOriginBlock.getJSONValue()), new Tag("Change Location Rotation", changeLocationRotation.getJSONValue())));
		CodeManager.instance.addCodeBlock(new BracketBlock(false, true));
	}

	/**
	 * Repeats code once for each
	 * evenly distributed sphere point.
	 * 
	 * Chest Parameters:
	 * Variable - Gets the
	 * current location each iteration
	 * Location - Sphere center
	 * Number - Sphere radius
	 * Number - Sphere points
	 * 
	 * Additional Info:
	 * > A Control: Wait block must
	 * be activated at least once
	 * for every 10 iterations.
	 */
	public static void OnSphere(Variable iterationBlock, ILocation sphereCenter, INumber sphereRadius, INumber spherePoints, PointLocationsInwards pointLocationsInwards) {
		List<CodeValue> items = new ArrayList<>();
		items.add(iterationBlock);
		items.add(sphereCenter);
		items.add(sphereRadius);
		items.add(spherePoints);
		CodeManager.instance.addCodeBlock(new RepeatBlock(Type.ON_SPHERE).SetItems(items).SetTags(new Tag("Point Locations Inwards", pointLocationsInwards.getJSONValue())));
		CodeManager.instance.addCodeBlock(new BracketBlock(false, true));
	}
	
	public static void End() {
		CodeManager.instance.addCodeBlock(new BracketBlock(true, true));
	}
}
