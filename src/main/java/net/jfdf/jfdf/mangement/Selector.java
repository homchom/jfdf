package net.jfdf.jfdf.mangement;

import net.jfdf.jfdf.blocks.SelectionBlock;
import net.jfdf.jfdf.values.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Selector {
    /**
     * Creates a selection using<br>
     * one or more random<br>
     * players in the game.
     *
     * @param selectionSize Selection size (Optional)
     */
    public static void RandomPlayer(INumber selectionSize) {
        List<CodeValue> items = new ArrayList<>();

        if(selectionSize != null) items.add(selectionSize);
        CodeManager.instance.addCodeBlock(new SelectionBlock("RandomPlayer").SetItems(items));
    }

    /**
     * Creates a selection using<br>
     * the most recently spawned<br>
     * entity.
     */
    public static void LastEntity() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new SelectionBlock("LastEntity").SetItems(items));
    }

    /**
     * Creates a selection using all<br>
     * entities in the game whose<br>
     * name or UUID matches.
     *
     * @param nameOrUuid Name or UUID
     */
    public static void EntityName(IText... nameOrUuid) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(nameOrUuid));
        CodeManager.instance.addCodeBlock(new SelectionBlock("EntityName").SetItems(items));
    }

    /**
     * Filters the selection by<br>
     * randomly picking one or<br>
     * more objects from it.
     *
     * @param selectionSize Selection size (Optional)
     */
    public static void FilterRandom(INumber selectionSize) {
        List<CodeValue> items = new ArrayList<>();

        if(selectionSize != null) items.add(selectionSize);
        CodeManager.instance.addCodeBlock(new SelectionBlock("FilterRandom").SetItems(items));
    }

    /**
     * Creates a selection using all<br>
     * players in the game whose<br>
     * name or UUID matches.
     *
     * @param nameOrUuid Name or UUID
     */
    public static void PlayerName(IText... nameOrUuid) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(nameOrUuid));
        CodeManager.instance.addCodeBlock(new SelectionBlock("PlayerName").SetItems(items));
    }

    /**
     * Creates a selection of<br>
     * all entities in the game.
     */
    public static void AllEntities() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new SelectionBlock("AllEntities").SetItems(items));
    }

    /**
     * Filters the selection to the<br>
     * objects that are nearest<br>
     * or farthest to a location.
     *
     * @param locationTo Location to compare to
     * @param selectionSize Selection size (Optional)
     */
    public static void FilterDistance(ILocation locationTo, INumber selectionSize, Tags.IgnoreY_Axis ignoreY_AxisTag, Tags.CompareMode compareModeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(locationTo);
        if(selectionSize != null) items.add(selectionSize);

        CodeManager.instance.addCodeBlock(new SelectionBlock("FilterDistance").SetItems(items).SetTags(new Tag("Ignore Y-Axis", ignoreY_AxisTag.getJSONValue()), new Tag("Compare Mode", compareModeTag.getJSONValue())));
    }

    /**
     * Filters the selected objects<br>
     * to the objects that intersect<br>
     * a ray.
     *
     * @param getsTheEndOr Gets the end or final hit location (Optional)
     * @param rayOrigin Ray origin
     * @param rayDistance Ray distance
     * @param rayWidth Ray width (Optional)
     * @param selectionSize Selection size (Optional)
     */
    public static void FilterRay(Variable getsTheEndOr, ILocation rayOrigin, INumber rayDistance, INumber rayWidth, INumber selectionSize, Tags.BlockCollision blockCollisionTag) {
        List<CodeValue> items = new ArrayList<>();

        if(getsTheEndOr != null) items.add(getsTheEndOr);
        items.add(rayOrigin);
        items.add(rayDistance);
        if(rayWidth != null) items.add(rayWidth);
        if(selectionSize != null) items.add(selectionSize);

        CodeManager.instance.addCodeBlock(new SelectionBlock("FilterRay").SetItems(items).SetTags(new Tag("Block Collision", blockCollisionTag.getJSONValue())));
    }

    /**
     * Deactivates the selection.<br>
     * Code that follows will run<br>
     * normally with event targets.
     */
    public static void Reset() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new SelectionBlock("Reset").SetItems(items));
    }

    /**
     * Creates a selection using<br>
     * a target involved in this<br>
     * Event.
     */
    public static void EventTarget(Tags.EventTarget eventTargetTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new SelectionBlock("EventTarget").SetItems(items).SetTags(new Tag("Event Target", eventTargetTag.getJSONValue())));
    }

    /**
     * Creates a selection of<br>
     * all entities in the game<br>
     * that meet a condition.
     */
    public static void EntitiesCond(SubIf.SubIfType condition) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new SelectionBlock("EntitiesCond", condition).SetItems(items));
    }

    /**
     * Creates a selection of<br>
     * all players in the game.
     */
    public static void AllPlayers() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new SelectionBlock("AllPlayers").SetItems(items));
    }

    /**
     * Creates a new selection by<br>
     * inverting the selection that<br>
     * is currently active.
     */
    public static void Invert() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new SelectionBlock("Invert").SetItems(items));
    }

    /**
     * Filters the selection to the<br>
     * objects that meet a certain<br>
     * condition.
     */
    public static void FilterCondition(SubIf.SubIfType condition) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new SelectionBlock("FilterCondition", condition).SetItems(items));
    }

    /**
     * Filters the selection by<br>
     * sorting the value of each<br>
     * object in order.
     *
     * @param valueTo Value to compare
     * @param selectionSize Selection size (Optional)
     */
    public static void FilterSort(CodeValue valueTo, INumber selectionSize, Tags.SortOrder sortOrderTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(valueTo);
        if(selectionSize != null) items.add(selectionSize);

        CodeManager.instance.addCodeBlock(new SelectionBlock("FilterSort").SetItems(items).SetTags(new Tag("Sort Order", sortOrderTag.getJSONValue())));
    }

    /**
     * Creates a selection of<br>
     * all players in the game<br>
     * that meet a condition.
     */
    public static void PlayersCond(SubIf.SubIfType condition) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new SelectionBlock("PlayersCond", condition).SetItems(items));
    }
}
