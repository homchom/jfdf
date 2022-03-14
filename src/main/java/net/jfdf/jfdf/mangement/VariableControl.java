package net.jfdf.jfdf.mangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.jfdf.jfdf.blocks.SetVariableBlock;
import net.jfdf.jfdf.values.*;

public class VariableControl {
    /**
     * Sets a particle effect's type.
     *
     * @param variableToSet Variable to set
     * @param effectTo Effect to change (Optional)
     * @param type Type
     */
    public static void SetParticleType(Variable variableToSet, IParticle effectTo, IText type) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(effectTo != null) items.add(effectTo);
        items.add(type);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetParticleType").SetItems(items));
    }

    /**
     * Sets an item's enchantment list.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param enchantments Enchantments
     */
    public static void SetItemEnchants(Variable variableToSet, IItem itemToChange, Variable enchantments) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(enchantments);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemEnchants").SetItems(items));
    }

    /**
     * Clears all variables with names<br>
     * that match the given text.
     *
     * @param nameToMatch Name to match
     */
    public static void PurgeVars(IText[] nameToMatch, Tags.MatchRequirement matchRequirementTag, Tags.IgnoreCase ignoreCaseTag) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(nameToMatch));
        CodeManager.instance.addCodeBlock(new SetVariableBlock("PurgeVars").SetItems(items).SetTags(new Tag("Match Requirement", matchRequirementTag.getJSONValue()), new Tag("Ignore Case", ignoreCaseTag.getJSONValue())));
    }

    /**
     * Shifts a location's coordinates<br>
     * on the X, Y, and Z axes.
     *
     * @param variableToSet Variable to set
     * @param locationToChange Location to change (Optional)
     * @param xChange X Change
     * @param yChange Y Change (Optional)
     * @param zChange Z Change (Optional)
     */
    public static void ShiftAllAxes(Variable variableToSet, ILocation locationToChange, INumber xChange, INumber yChange, INumber zChange) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToChange != null) items.add(locationToChange);
        items.add(xChange);
        if(yChange != null) items.add(yChange);
        if(zChange != null) items.add(zChange);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ShiftAllAxes").SetItems(items));
    }

    /**
     * Gets a particle effect's particle<br>
     * display material.
     *
     * @param variableToSet Variable to set
     * @param effectToGet Effect to get material of
     */
    public static void GetParticleMat(Variable variableToSet, IParticle effectToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(effectToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetParticleMat").SetItems(items));
    }

    /**
     * Sets a particle effect's horizontal<br>
     * and vertical spread.
     *
     * @param variableToSet Variable to set
     * @param effectToChange Effect to change (Optional)
     * @param horizontalSpread Horizontal spread
     * @param verticalSpread Vertical spread
     */
    public static void SetParticleSprd(Variable variableToSet, IParticle effectToChange, INumber horizontalSpread, INumber verticalSpread) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(effectToChange != null) items.add(effectToChange);
        items.add(horizontalSpread);
        items.add(verticalSpread);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetParticleSprd").SetItems(items));
    }

    /**
     * Makes a number positive<br>
     * if it is negative.
     *
     * @param variableToSet Variable to set
     * @param numberInput Number input (Optional)
     */
    public static void AbsoluteValue(Variable variableToSet, INumber numberInput) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(numberInput != null) items.add(numberInput);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("AbsoluteValue").SetItems(items));
    }

    /**
     * Adds a value to the end of a list<br>
     * variable.
     *
     * @param listToAppendTo List to append to
     * @param value Value(s) to append
     */
    public static void AppendValue(Variable listToAppendTo, CodeValue... value) {
        List<CodeValue> items = new ArrayList<>();

        items.add(listToAppendTo);
        items.addAll(Arrays.asList(value));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("AppendValue").SetItems(items));
    }

    /**
     * Sets a variable to the remainder<br>
     * after dividing two numbers with<br>
     * a whole quotient.
     *
     * @param variableToSet Variable to set
     * @param dividend Dividend
     * @param divisor Divisor
     */
    public static void SetToRemainder(Variable variableToSet, INumber dividend, INumber divisor) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(dividend);
        items.add(divisor);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("%").SetItems(items));
    }

    /**
     * Shifts a location along a<br>
     * vector.
     *
     * @param variableToSet Variable to set
     * @param locationToShift Location to shift (Optional)
     * @param shiftVector Shift vector
     * @param shiftDistance Shift distance
     */
    public static void ShiftOnVector(Variable variableToSet, ILocation locationToShift, IVector shiftVector, INumber shiftDistance, Tags.AddLocationRotation addLocationRotationTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToShift != null) items.add(locationToShift);
        items.add(shiftVector);
        items.add(shiftDistance);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ShiftOnVector").SetItems(items).SetTags(new Tag("Add Location Rotation", addLocationRotationTag.getJSONValue())));
    }

    /**
     * Get an attribute's<br>
     * multiplier for a specific slot.
     *
     * @param variableToSet Variable to set
     * @param item Item
     */
    public static void GetItemAttribute(Variable variableToSet, IItem item, Tags.Attribute attributeTag, Tags.ActiveEquipmentSlot activeEquipmentSlotTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(item);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemAttribute").SetItems(items).SetTags(new Tag("Attribute", attributeTag.getJSONValue()), new Tag("Active Equipment Slot", activeEquipmentSlotTag.getJSONValue())));
    }

    /**
     * Removes all entries from<br>
     * a dictionary.
     *
     * @param dictionaryToClear Dictionary to clear
     */
    public static void ClearDict(Variable dictionaryToClear) {
        List<CodeValue> items = new ArrayList<>();

        items.add(dictionaryToClear);
        CodeManager.instance.addCodeBlock(new SetVariableBlock("ClearDict").SetItems(items));
    }

    /**
     * Sets a variable to the sum of<br>
     * the given numbers.
     *
     * @param variableToSet Variable to set
     * @param valuesToAdd Values to add
     */
    public static void SetToSum(Variable variableToSet, CodeValue... valuesToAdd) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.addAll(Arrays.asList(valuesToAdd));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("+").SetItems(items));
    }

    /**
     * Rotates a location by shifting its pitch<br>
     * (up/down) or yaw (left/right) value.
     *
     * @param variableToSet Variable to set
     * @param locationToShift Location to shift (Optional)
     * @param rotationAmount Rotation amount
     */
    public static void ShiftRotation(Variable variableToSet, ILocation locationToShift, INumber rotationAmount, Tags.RotationAxis rotationAxisTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToShift != null) items.add(locationToShift);
        items.add(rotationAmount);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ShiftRotation").SetItems(items).SetTags(new Tag("Rotation Axis", rotationAxisTag.getJSONValue())));
    }

    /**
     * Sets a variable to the difference<br>
     * between the given numbers.
     *
     * @param variableToSet Variable to set
     * @param numbersToSubtract Numbers to subtract
     */
    public static void SetToDifference(Variable variableToSet, INumber... numbersToSubtract) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.addAll(Arrays.asList(numbersToSubtract));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("-").SetItems(items));
    }

    /**
     * Gets an item's rarity.
     *
     * @param variableToSet Variable to set
     * @param item Item
     */
    public static void GetItemRarity(Variable variableToSet, IItem item) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(item);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemRarity").SetItems(items));
    }

    /**
     * Multiplies a vector's length<br>
     * by a number.
     *
     * @param variableToSet Variable to set
     * @param vectorToMultiply Vector to multiply (Optional)
     * @param multiplier Multiplier
     */
    public static void MultiplyVector(Variable variableToSet, IVector vectorToMultiply, INumber multiplier) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(vectorToMultiply != null) items.add(vectorToMultiply);
        items.add(multiplier);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("MultiplyVector").SetItems(items));
    }

    /**
     * Sets a variable to the quotient<br>
     * of the given numbers.
     *
     * @param variableToSet Variable to set
     * @param numbersToDivide Numbers to divide
     */
    public static void SetToQuotient(Variable variableToSet, INumber[] numbersToDivide, Tags.DivisionMode divisionModeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.addAll(Arrays.asList(numbersToDivide));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("/").SetItems(items).SetTags(new Tag("Division Mode", divisionModeTag.getJSONValue())));
    }

    /**
     * Gets a sign's text at a location.
     *
     * @param variableToSet Variable to set
     * @param location Location
     */
    public static void GetSignText(Variable variableToSet, ILocation location, Tags.SignLine signLineTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(location);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetSignText").SetItems(items).SetTags(new Tag("Sign Line", signLineTag.getJSONValue())));
    }

    /**
     * Sets a variable to the result<br>
     * of a bitwise operation.
     *
     * @param variableToSet Variable to set
     * @param operand1 Operand 1
     * @param operand2 Operand 2 (Optional)
     */
    public static void Bitwise(Variable variableToSet, INumber operand1, INumber operand2, Tags.Operator operatorTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(operand1);
        if(operand2 != null) items.add(operand2);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Bitwise").SetItems(items).SetTags(new Tag("Operator", operatorTag.getJSONValue())));
    }

    /**
     * Shifts the X, Y, or Z coordinate<br>
     * of a location on its axis.
     *
     * @param variableToSet Variable to set
     * @param locationToShift Location to shift (Optional)
     * @param shiftDistance Shift distance
     */
    public static void ShiftOnAxis(Variable variableToSet, ILocation locationToShift, INumber shiftDistance, Tags.Coordinate coordinateTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToShift != null) items.add(locationToShift);
        items.add(shiftDistance);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ShiftOnAxis").SetItems(items).SetTags(new Tag("Coordinate", coordinateTag.getJSONValue())));
    }

    /**
     * Sets a variable to the vector<br>
     * between two locations.
     *
     * @param variableToSet Variable to set
     * @param startLocation Start location
     * @param endLocation End location
     */
    public static void VectorBetween(Variable variableToSet, ILocation startLocation, ILocation endLocation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(startLocation);
        items.add(endLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("VectorBetween").SetItems(items));
    }

    /**
     * Gets a vector's X, Y, or Z<br>
     * component.
     *
     * @param variableToSet Variable to set
     * @param vectorToGet Vector to get component of
     */
    public static void GetVectorComp(Variable variableToSet, IVector vectorToGet, Tags.Component componentTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(vectorToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetVectorComp").SetItems(items).SetTags(new Tag("Component", componentTag.getJSONValue())));
    }

    /**
     * Sets a variable to a value.
     *
     * @param variableToSet Variable to set
     * @param value Value
     */
    public static void Set(Variable variableToSet, CodeValue value) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(value);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("=").SetItems(items));
    }

    /**
     * Adds an attribute modifier to the<br>
     * item, which is active in a certain<br>
     * equipment slot.
     *
     * @param variableToSet Variable to set
     * @param item Item (Optional)
     * @param modifierAmount Modifier Amount
     */
    public static void AddItemAttribute(Variable variableToSet, IItem item, INumber modifierAmount, Tags.Attribute attributeTag, Tags.Operation operationTag, Tags.ActiveEquipmentSlot activeEquipmentSlotTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(item != null) items.add(item);
        items.add(modifierAmount);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("AddItemAttribute").SetItems(items).SetTags(new Tag("Attribute", attributeTag.getJSONValue()), new Tag("Operation", operationTag.getJSONValue()), new Tag("Active Equipment Slot", activeEquipmentSlotTag.getJSONValue())));
    }

    /**
     * Finds an average position (center)<br>
     * of the given locations.
     *
     * @param variableToSet Variable to set
     * @param locationsToCenter Locations to center
     */
    public static void GetCenterLoc(Variable variableToSet, ILocation... locationsToCenter) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.addAll(Arrays.asList(locationsToCenter));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetCenterLoc").SetItems(items));
    }

    /**
     * Aligns a location to the center<br>
     * or corner of the block it is in.
     *
     * @param variableToSet Variable to set
     * @param locationToAlign Location to align (Optional)
     */
    public static void AlignLoc(Variable variableToSet, ILocation locationToAlign, Tags.LocationAlignmentMode alignmentModeTag, Tags.Coordinates coordinatesTag, Tags.Rotation rotationTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToAlign != null) items.add(locationToAlign);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("AlignLoc").SetItems(items).SetTags(new Tag("Alignment Mode", alignmentModeTag.getJSONValue()), new Tag("Coordinates", coordinatesTag.getJSONValue()), new Tag("Rotation", rotationTag.getJSONValue())));
    }

    /**
     * Gets a sound's volume.
     *
     * @param variableToSet Variable to set
     * @param soundToGetVolumeOf Sound to get volume of
     */
    public static void GetSoundVolume(Variable variableToSet, ISound soundToGetVolumeOf) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(soundToGetVolumeOf);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetSoundVolume").SetItems(items));
    }

    /**
     * Sets a variable to a random<br>
     * number between two other<br>
     * numbers.
     *
     * @param variableToSet Variable to set
     * @param minimumNumber Minimum number
     * @param maximumNumber Maximum number
     */
    public static void RandomNumber(Variable variableToSet, INumber minimumNumber, INumber maximumNumber, Tags.RoundingMode roundingModeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(minimumNumber);
        items.add(maximumNumber);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RandomNumber").SetItems(items).SetTags(new Tag("Rounding Mode", roundingModeTag.getJSONValue())));
    }

    /**
     * Raycasts from a location<br>
     * to the first intersection.
     *
     * @param variableToSet Variable to set
     * @param rayOrigin Ray origin
     * @param rayDistance Ray distance
     */
    public static void Raycast(Variable variableToSet, ILocation rayOrigin, INumber rayDistance, Tags.EntityCollision entityCollisionTag, Tags.BlockCollision blockCollisionTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(rayOrigin);
        items.add(rayDistance);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Raycast").SetItems(items).SetTags(new Tag("Entity Collision", entityCollisionTag.getJSONValue()), new Tag("Block Collision", blockCollisionTag.getJSONValue())));
    }

    /**
     * Rotates a vector around<br>
     * another vector by an angle.
     *
     * @param variableToSet Variable to set
     * @param vectorToRotate Vector to rotate (Optional)
     * @param axisVector Axis vector
     * @param angle Angle
     */
    public static void RotateAroundVec(Variable variableToSet, IVector vectorToRotate, IVector axisVector, INumber angle, Tags.AngleUnits angleUnitsTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(vectorToRotate != null) items.add(vectorToRotate);
        items.add(axisVector);
        items.add(angle);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RotateAroundVec").SetItems(items).SetTags(new Tag("Angle Units", angleUnitsTag.getJSONValue())));
    }

    /**
     * Gets a particle effect's particle<br>
     * motion.
     *
     * @param variableToSet Variable to set
     * @param effectToGet Effect to get motion of
     */
    public static void GetParticleMotion(Variable variableToSet, IParticle effectToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(effectToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetParticleMotion").SetItems(items));
    }

    /**
     * Sets a particle effect's particle<br>
     * motion and motion variation.
     *
     * @param variableToSet Variable to set
     * @param effectTo Effect to change (Optional)
     * @param particleMotion Particle motion
     * @param motionVariation Motion variation (%) (Optional)
     */
    public static void SetParticleMotion(Variable variableToSet, IParticle effectTo, IVector particleMotion, INumber motionVariation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(effectTo != null) items.add(effectTo);
        items.add(particleMotion);
        if(motionVariation != null) items.add(motionVariation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetParticleMotion").SetItems(items));
    }

    /**
     * Repeats a text the given number<br>
     * of times.
     *
     * @param variableToSet Variable to set
     * @param textToRepeat Text to repeat
     * @param timesToRepeat Times to repeat
     */
    public static void RepeatText(Variable variableToSet, IText textToRepeat, INumber timesToRepeat) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(textToRepeat);
        items.add(timesToRepeat);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RepeatText").SetItems(items));
    }

    /**
     * Sets a variable to the average<br>
     * of the given numbers.
     *
     * @param variableToSet Variable to set
     * @param numbersToAverage Numbers to average
     */
    public static void Average(Variable variableToSet, INumber... numbersToAverage) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.addAll(Arrays.asList(numbersToAverage));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Average").SetItems(items));
    }

    /**
     * Checks if a number is between<br>
     * two bounds and if not, wraps<br>
     * it around the farthest bound.
     *
     * @param variableToSet Variable to set
     * @param numberToWrap Number to wrap (Optional)
     * @param lowerBound Lower bound
     * @param upperBound Upper bound
     */
    public static void WrapNumber(Variable variableToSet, INumber numberToWrap, INumber lowerBound, INumber upperBound) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(numberToWrap != null) items.add(numberToWrap);
        items.add(lowerBound);
        items.add(upperBound);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("WrapNumber").SetItems(items));
    }

    /**
     * Sets a map item's texture to the<br>
     * image at the given URL.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param imageUrl Image URL
     */
    public static void SetMapTexture(Variable variableToSet, IItem itemToChange, IText imageUrl) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(imageUrl);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetMapTexture").SetItems(items));
    }

    /**
     * Gets a block state tag<br>
     * value at a location.
     *
     * @param variableToSet Variable to set
     * @param blockLocation Block location
     * @param tagName Tag name
     */
    public static void GetBlockData(Variable variableToSet, ILocation blockLocation, IText tagName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(blockLocation);
        items.add(tagName);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetBlockData").SetItems(items));
    }

    /**
     * Sets a variable to the cross<br>
     * product of two vectors.
     *
     * @param variableToSet Variable to set
     * @param vector1 Vector 1
     * @param vector2 Vector 2
     */
    public static void CrossProduct(Variable variableToSet, IVector vector1, IVector vector2) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(vector1);
        items.add(vector2);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("CrossProduct").SetItems(items));
    }

    /**
     * Sets a variable to the product<br>
     * of the given numbers.
     *
     * @param variableToSet Variable to set
     * @param numbersToMultiply Numbers to multiply
     */
    public static void SetToProduct(Variable variableToSet, INumber... numbersToMultiply) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.addAll(Arrays.asList(numbersToMultiply));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("x").SetItems(items));
    }

    /**
     * Sets a variable to the dot<br>
     * product of two vectors.
     *
     * @param variableToSet Variable to set
     * @param vector1 Vector 1
     * @param vector2 Vector 2
     */
    public static void DotProduct(Variable variableToSet, IVector vector1, IVector vector2) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(vector1);
        items.add(vector2);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("DotProduct").SetItems(items));
    }

    /**
     * Gets a potion effect's type.
     *
     * @param variableToSet Variable to set
     * @param potionToGet Potion to get type of
     */
    public static void GetPotionType(Variable variableToSet, IPotion potionToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(potionToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetPotionType").SetItems(items));
    }

    /**
     * Sets an item's name.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param name Name
     */
    public static void SetItemName(Variable variableToSet, IItem itemToChange, IText... name) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.addAll(Arrays.asList(name));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemName").SetItems(items));
    }

    /**
     * Gets the number of values<br>
     * a list has.
     *
     * @param variableToSet Variable to set
     * @param listToMeasure List to measure
     */
    public static void ListLength(Variable variableToSet, Variable listToMeasure) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(listToMeasure);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ListLength").SetItems(items));
    }

    /**
     * Sets a variable to the trigonometric<br>
     * sine function of a number.
     *
     * @param variableToSet Variable to set
     * @param numberInput Number input
     */
    public static void Sine(Variable variableToSet, INumber numberInput, Tags.SineVariant sineVariantTag, Tags.Input inputTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(numberInput);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Sine").SetItems(items).SetTags(new Tag("Sine Variant", sineVariantTag.getJSONValue()), new Tag("Input", inputTag.getJSONValue())));
    }

    /**
     * Sets a variable to the name<br>
     * of the direction of a vector.
     *
     * @param variableToSet Variable to set
     * @param direction Direction
     */
    public static void DirectionName(Variable variableToSet, IVector direction) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(direction);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("DirectionName").SetItems(items));
    }

    /**
     * Gets an item's lore list.
     *
     * @param variableToSet Variable to set
     * @param itemToGetLoreOf Item to get lore of
     */
    public static void GetItemLore(Variable variableToSet, IItem itemToGetLoreOf) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(itemToGetLoreOf);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemLore").SetItems(items));
    }

    /**
     * Reverses the order of a<br>
     * list variable's values.
     *
     * @param variableToSet Variable to set
     * @param listToReverse List to reverse (Optional)
     */
    public static void ReverseList(Variable variableToSet, Variable listToReverse) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(listToReverse != null) items.add(listToReverse);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ReverseList").SetItems(items));
    }

    /**
     * Creates a dictionary with the<br>
     * given keys and values.
     *
     * @param variableToSet Variable to set
     * @param keyList Key list (Optional)
     * @param valueList Value list (Optional)
     */
    public static void CreateDict(Variable variableToSet, Variable keyList, Variable valueList) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(keyList != null) items.add(keyList);
        if(valueList != null) items.add(valueList);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("CreateDict").SetItems(items));
    }

    /**
     * Sets a location's rotation to<br>
     * face another location.
     *
     * @param variableToSet Variable to set
     * @param locationToChange Location to change (Optional)
     * @param targetLocation Target location
     */
    public static void FaceLocation(Variable variableToSet, ILocation locationToChange, ILocation targetLocation, Tags.FaceDirection faceDirectionTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToChange != null) items.add(locationToChange);
        items.add(targetLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("FaceLocation").SetItems(items).SetTags(new Tag("Face Direction", faceDirectionTag.getJSONValue())));
    }

    /**
     * Sets a vector's length. This<br>
     * affects all components.
     *
     * @param variableToSet Variable to set
     * @param vectorToChange Vector to change (Optional)
     * @param length Length (Optional)
     */
    public static void SetVectorLength(Variable variableToSet, IVector vectorToChange, INumber length) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(vectorToChange != null) items.add(vectorToChange);
        if(length != null) items.add(length);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetVectorLength").SetItems(items));
    }

    /**
     * Sets a potion effect's duration.
     *
     * @param variableToSet Variable to set
     * @param potionToChange Potion to change (Optional)
     * @param duration Duration (ticks)
     */
    public static void SetPotionDur(Variable variableToSet, IPotion potionToChange, INumber duration) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(potionToChange != null) items.add(potionToChange);
        items.add(duration);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetPotionDur").SetItems(items));
    }

    /**
     * Sets a variable to a normally distributed<br>
     * random number. Values closer to ? are<br>
     * more likely to be chosen.
     *
     * @param variableToSet Variable to set
     * @param meanMidpoint ? (Mean midpoint)
     * @param standardDeviation ? (Standard deviation)
     */
    public static void NormalRandom(Variable variableToSet, INumber meanMidpoint , INumber standardDeviation , Tags.Distribution distributionTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(meanMidpoint);
        items.add(standardDeviation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("NormalRandom").SetItems(items).SetTags(new Tag("Distribution", distributionTag.getJSONValue())));
    }

    /**
     * Sets a potion effect's type.
     *
     * @param variableToSet Variable to set
     * @param potionToChange Potion to change (Optional)
     * @param type Type
     */
    public static void SetPotionType(Variable variableToSet, IPotion potionToChange, IText type) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(potionToChange != null) items.add(potionToChange);
        items.add(type);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetPotionType").SetItems(items));
    }

    /**
     * Aligns a vector to the<br>
     * nearest axis.
     *
     * @param variableToSet Variable to set
     * @param vectorToAlign Vector to align (Optional)
     */
    public static void AlignVector(Variable variableToSet, IVector vectorToAlign) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(vectorToAlign != null) items.add(vectorToAlign);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("AlignVector").SetItems(items));
    }

    /**
     * Sets the given item's durability.
     *
     * @param variableToSet Variable to set
     * @param item Item (Optional)
     * @param itemDurability Item durability
     */
    public static void SetItemDura(Variable variableToSet, IItem item, INumber itemDurability, Tags.DurabilityType durabilityTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(item != null) items.add(item);
        items.add(itemDurability);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemDura").SetItems(items).SetTags(new Tag("Durability Type", durabilityTypeTag.getJSONValue())));
    }

    /**
     * Sets whether an item is<br>
     * unbreakable.
     *
     * @param variableToSet Variable to set
     * @param item Item (Optional)
     */
    public static void SetBreakability(Variable variableToSet, IItem item, Tags.Breakability breakabilityTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(item != null) items.add(item);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetBreakability").SetItems(items).SetTags(new Tag("Breakability", breakabilityTag.getJSONValue())));
    }

    /**
     * Sets the given key to the value.
     *
     * @param dictionaryToAddTo Dictionary to add to
     * @param key key
     * @param value Value
     */
    public static void SetDictValue(Variable dictionaryToAddTo, IText key, CodeValue value) {
        List<CodeValue> items = new ArrayList<>();

        items.add(dictionaryToAddTo);
        items.add(key);
        items.add(value);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetDictValue").SetItems(items));
    }

    /**
     * Sets a location's coordinates or<br>
     * creates a new location.
     *
     * @param variableToSet Variable to set
     * @param locationToChange Location to change (Optional)
     * @param newX New X
     * @param newY New Y (Optional)
     * @param newZ New Z (Optional)
     * @param newPitch New Pitch (Optional)
     * @param newYaw New Yaw (Optional)
     */
    public static void SetAllCoords(Variable variableToSet, ILocation locationToChange, INumber newX, INumber newY, INumber newZ, INumber newPitch, INumber newYaw, Tags.CoordinateType coordinateTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToChange != null) items.add(locationToChange);
        items.add(newX);
        if(newY != null) items.add(newY);
        if(newZ != null) items.add(newZ);
        if(newPitch != null) items.add(newPitch);
        if(newYaw != null) items.add(newYaw);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetAllCoords").SetItems(items).SetTags(new Tag("Coordinate Type", coordinateTypeTag.getJSONValue())));
    }

    /**
     * Creates a color hex based on red,<br>
     * green, and blue channels.
     *
     * @param variableToSet Variable to set
     * @param red Red (0-255)
     * @param green Green (0-255)
     * @param blue Blue (0-255)
     */
    public static void RGBColor(Variable variableToSet, INumber red, INumber green, INumber blue) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(red);
        items.add(green);
        items.add(blue);
        CodeManager.instance.addCodeBlock(new SetVariableBlock("RGBColor").SetItems(items));
    }

    /**
     * Sets which blocks an item<br>
     * can break in Adventure Mode.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param breakableBlocks Breakable blocks
     */
    public static void SetCanDestroy(Variable variableToSet, IItem itemToChange, CodeValue... breakableBlocks) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.addAll(Arrays.asList(breakableBlocks));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetCanDestroy").SetItems(items));
    }

    /**
     * Splits a text into a list of texts.
     *
     * @param variableToSet Variable to set
     * @param textToSplit Text to split
     * @param splitterText Splitter text (Optional)
     */
    public static void SplitText(Variable variableToSet, IText textToSplit, IText splitterText) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(textToSplit);
        if(splitterText != null) items.add(splitterText);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SplitText").SetItems(items));
    }

    /**
     * Creates a color based on hue,<br>
     * saturation, and lightness.
     *
     * @param variableToSet Variable to set
     * @param hue Hue (Color circle, 0-360)
     * @param saturation Saturation (0-100) (Optional)
     * @param lightness Lightness (0-100) (Optional)
     */
    public static void HSLColor(Variable variableToSet, INumber hue, INumber saturation, INumber lightness) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(hue);
        if(saturation != null) items.add(saturation);
        if(lightness != null) items.add(lightness);
        CodeManager.instance.addCodeBlock(new SetVariableBlock("HSLColor").SetItems(items));
    }

    /**
     * Removes a list variable's value<br>
     * at an index and shifts all values<br>
     * after it to the left.
     *
     * @param listToChange List to change
     * @param indexToRemove Index to remove
     */
    public static void RemoveListIndex(Variable listToChange, INumber... indexToRemove) {
        List<CodeValue> items = new ArrayList<>();

        items.add(listToChange);
        items.addAll(Arrays.asList(indexToRemove));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RemoveListIndex").SetItems(items));
    }

    /**
     * Finds the logarithm of a number.<br>
     * A logarithm is the power the<br>
     * base must be raised to to get<br>
     * the given input.
     *
     * @param variableToSet Variable to set
     * @param numberInput Number input (Optional)
     * @param base Base
     */
    public static void Logarithm(Variable variableToSet, INumber numberInput, INumber base) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(numberInput != null) items.add(numberInput);
        items.add(base);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Logarithm").SetItems(items));
    }

    /**
     * Sets the value of or creates<br>
     * a custom stored tag value.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param tagName Tag name
     * @param tagValue Tag value
     */
    public static void SetItemTag(Variable variableToSet, IItem itemToChange, IText tagName, INumber tagValue) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(tagName);
        items.add(tagValue);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemTag").SetItems(items));
    }

    /**
     * Gets an item's stack size.
     *
     * @param variableToSet Variable to set
     * @param itemToGetStack Item to get stack size of
     */
    public static void GetItemAmount(Variable variableToSet, IItem itemToGetStack) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(itemToGetStack);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemAmount").SetItems(items));
    }

    /**
     * Sets a potion effect's amplifier.
     *
     * @param variableToSet Variable to set
     * @param potionToChange Potion to change (Optional)
     * @param amplifier Amplifier
     */
    public static void SetPotionAmp(Variable variableToSet, IPotion potionToChange, INumber amplifier) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(potionToChange != null) items.add(potionToChange);
        items.add(amplifier);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetPotionAmp").SetItems(items));
    }

    /**
     * Rotates a vector around an<br>
     * axis by an angle.
     *
     * @param variableToSet Variable to set
     * @param vectorToRotate Vector to rotate (Optional)
     * @param angle Angle
     */
    public static void RotateAroundAxis(Variable variableToSet, IVector vectorToRotate, INumber angle, Tags.Axis axisTag, Tags.AngleUnits angleUnitsTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(vectorToRotate != null) items.add(vectorToRotate);
        items.add(angle);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RotateAroundAxis").SetItems(items).SetTags(new Tag("Axis", axisTag.getJSONValue()), new Tag("Angle Units", angleUnitsTag.getJSONValue())));
    }

    /**
     * Gets an item's name.
     *
     * @param variableToSet Variable to set
     * @param itemToGetNameOf Item to get name of
     */
    public static void GetItemName(Variable variableToSet, IItem itemToGetNameOf) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(itemToGetNameOf);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemName").SetItems(items));
    }

    /**
     * Gets the given item's current or<br>
     * maximum durability.
     *
     * @param variableToSet Variable to set
     * @param item Item
     */
    public static void GetItemDura(Variable variableToSet, IItem item, Tags.DurabilityType durabilityTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(item);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemDura").SetItems(items).SetTags(new Tag("Durability Type", durabilityTypeTag.getJSONValue())));
    }

    /**
     * Shifts a location forward, upward,<br>
     * or sideways based on its rotation.
     *
     * @param variableToSet Variable to set
     * @param locationToShift Location to shift (Optional)
     * @param shiftDistance Shift distance (Optional)
     */
    public static void ShiftInDirection(Variable variableToSet, ILocation locationToShift, INumber shiftDistance, Tags.Direction directionTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToShift != null) items.add(locationToShift);
        if(shiftDistance != null) items.add(shiftDistance);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ShiftInDirection").SetItems(items).SetTags(new Tag("Direction", directionTag.getJSONValue())));
    }

    /**
     * Searches for part of a text<br>
     * and replaces it.
     *
     * @param variable Variable to set
     * @param textToChange Text to change
     * @param textPartToReplace Text part to replace
     * @param replacement Replacement
     */
    public static void ReplaceText(Variable variable, IText textToChange, IText textPartToReplace, IText replacement, Tags.RegularExpressions regularExpressionsTag, Tags.ReplacementType replacementTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variable);
        items.add(textToChange);
        items.add(textPartToReplace);
        items.add(replacement);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ReplaceText").SetItems(items).SetTags(new Tag("Regular Expressions", regularExpressionsTag.getJSONValue()), new Tag("Replacement Type", replacementTypeTag.getJSONValue())));
    }

    /**
     * Gets the number of characters<br>
     * a text has.
     *
     * @param variableToSet Variable to set
     * @param textToMeasure Text to measure
     */
    public static void TextLength(Variable variableToSet, IText textToMeasure) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(textToMeasure);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("TextLength").SetItems(items));
    }

    /**
     * Sets a compass's lodestone location.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param lodestoneLocation Lodestone location
     */
    public static void SetLodestoneLoc(Variable variableToSet, IItem itemToChange, ILocation lodestoneLocation, Tags.RequireLodestoneatLocation requireLodestoneatLocationTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(lodestoneLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetLodestoneLoc").SetItems(items).SetTags(new Tag("Require Lodestone at Location", requireLodestoneatLocationTag.getJSONValue())));
    }

    /**
     * Gets a potion effect's amplifier.
     *
     * @param variableToSet Variable to set
     * @param potionToGet Potion to get amplifier of
     */
    public static void GetPotionAmp(Variable variableToSet, IPotion potionToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(potionToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetPotionAmp").SetItems(items));
    }

    /**
     * Gets a particle effect's particle<br>
     * amount.
     *
     * @param variableToSet Variable to set
     * @param effectToGet Effect to get amount of
     */
    public static void GetParticleAmount(Variable variableToSet, IParticle effectToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(effectToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetParticleAmount").SetItems(items));
    }

    /**
     * Gets the number of entries<br>
     * in a dictionary.
     *
     * @param variableToSet Variable to set
     * @param dictionaryTo Dictionary to measure
     */
    public static void GetDictSize(Variable variableToSet, Variable dictionaryTo) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(dictionaryTo);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetDictSize").SetItems(items));
    }

    /**
     * Sets an item's stack size.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param stackSize Stack size
     */
    public static void SetItemAmount(Variable variableToSet, IItem itemToChange, INumber stackSize) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(stackSize);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemAmount").SetItems(items));
    }

    /**
     * Sets a variable to the difference<br>
     * between the given vectors.
     *
     * @param variableToSet Variable to set
     * @param vectorsToSubtract Vectors to subtract
     */
    public static void SubtractVectors(Variable variableToSet, IVector... vectorsToSubtract) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.addAll(Arrays.asList(vectorsToSubtract));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SubtractVectors").SetItems(items));
    }

    /**
     * Sets a text's capitalization<br>
     * (eg. to uppercase).
     *
     * @param variableToSet Variable to set
     * @param textToChange Text to change (Optional)
     */
    public static void SetCase(Variable variableToSet, IText textToChange, Tags.CapitalizationType capitalizationTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(textToChange != null) items.add(textToChange);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetCase").SetItems(items).SetTags(new Tag("Capitalization Type", capitalizationTypeTag.getJSONValue())));
    }

    /**
     * Sets a particle effect's particle<br>
     * color and color variation.
     *
     * @param variableToSet Variable to set
     * @param effectTo Effect to change (Optional)
     * @param colorHexadecimal Color hexadecimal
     * @param colorVariation Color variation (%) (Optional)
     */
    public static void SetParticleColor(Variable variableToSet, IParticle effectTo, IText colorHexadecimal, INumber colorVariation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(effectTo != null) items.add(effectTo);
        items.add(colorHexadecimal);
        if(colorVariation != null) items.add(colorVariation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetParticleColor").SetItems(items));
    }

    /**
     * Gets the light level at<br>
     * a location.
     *
     * @param variableToSet Variable to set
     * @param lightLocation Light location
     */
    public static void GetLight(Variable variableToSet, ILocation lightLocation, Tags.LightType lightTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(lightLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetLight").SetItems(items).SetTags(new Tag("Light Type", lightTypeTag.getJSONValue())));
    }

    /**
     * Gets the list of values<br>
     * in this dictionary.
     *
     * @param variableToSet Variable to set
     * @param dictionaryTo Dictionary to pull from
     */
    public static void GetDictValues(Variable variableToSet, Variable dictionaryTo) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(dictionaryTo);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetDictValues").SetItems(items));
    }

    /**
     * Sets a variable to a vector.
     *
     * @param variableToSet Variable to set
     * @param xComponent X component
     * @param yComponent Y component
     * @param zComponent Z component
     */
    public static void Vector(Variable variableToSet, INumber xComponent, INumber yComponent, INumber zComponent) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(xComponent);
        items.add(yComponent);
        items.add(zComponent);
        CodeManager.instance.addCodeBlock(new SetVariableBlock("Vector").SetItems(items));
    }

    /**
     * Sets a variable to the distance<br>
     * between two locations.
     *
     * @param variableToSet Variable to set
     * @param location1 Location 1
     * @param location2 Location 2
     */
    public static void Distance(Variable variableToSet, ILocation location1, ILocation location2, Tags.DistanceType distanceTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(location1);
        items.add(location2);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Distance").SetItems(items).SetTags(new Tag("Distance Type", distanceTypeTag.getJSONValue())));
    }

    /**
     * Sets an item's lore list.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param lore Lore (Optional)
     */
    public static void SetItemLore(Variable variableToSet, IItem itemToChange, IText[] lore) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        if(lore != null) items.addAll(Arrays.asList(lore));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemLore").SetItems(items));
    }

    /**
     * Takes the root of a number.
     *
     * @param variableToSet Variable to set
     * @param numberInput Number input (Optional)
     * @param rootIndex Root index (Optional)
     */
    public static void Root(Variable variableToSet, INumber numberInput, INumber rootIndex) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(numberInput != null) items.add(numberInput);
        if(rootIndex != null) items.add(rootIndex);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Root").SetItems(items));
    }

    /**
     * Sets a particle effect's particle<br>
     * amount.
     *
     * @param variableToSet Variable to set
     * @param effectTo Effect to change (Optional)
     * @param particleAmount Particle amount
     */
    public static void SetParticleAmount(Variable variableToSet, IParticle effectTo, INumber particleAmount) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(effectTo != null) items.add(effectTo);
        items.add(particleAmount);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetParticleAmount").SetItems(items));
    }

    /**
     * Adds an enchantment to an item.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param enchantmentName Enchantment name
     * @param enchantmentLevel Enchantment level
     */
    public static void AddItemEnchant(Variable variableToSet, IItem itemToChange, IText enchantmentName, INumber enchantmentLevel) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(enchantmentName);
        items.add(enchantmentLevel);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("AddItemEnchant").SetItems(items));
    }

    /**
     * Gets an item's material.
     *
     * @param variableToSet Variable to set
     * @param itemToGetMaterialOf Item to get material of
     */
    public static void GetItemType(Variable variableToSet, IItem itemToGetMaterialOf, Tags.ReturnValueType returnValueTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(itemToGetMaterialOf);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemType").SetItems(items).SetTags(new Tag("Return Value Type", returnValueTypeTag.getJSONValue())));
    }

    /**
     * Gets a location's rotation<br>
     * (pitch and yaw) as a direction.
     *
     * @param variableToSet Variable to set
     * @param locationToGet Location to get direction of
     */
    public static void GetDirection(Variable variableToSet, ILocation locationToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(locationToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetDirection").SetItems(items));
    }

    /**
     * Trims a text, starting and ending<br>
     * at the given positions.
     *
     * @param variableToSet Variable to set
     * @param textToTrim Text to trim (Optional)
     * @param startCharacterPosition Start character position
     * @param endCharacterPosition End character position (Optional)
     */
    public static void TrimText(Variable variableToSet, IText textToTrim, INumber startCharacterPosition, INumber endCharacterPosition) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(textToTrim != null) items.add(textToTrim);
        items.add(startCharacterPosition);
        if(endCharacterPosition != null) items.add(endCharacterPosition);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("TrimText").SetItems(items));
    }

    /**
     * Gets a particle effect's type.
     *
     * @param variableToSet Variable to set
     * @param effectToGet Effect to get type of
     */
    public static void GetParticleType(Variable variableToSet, IParticle effectToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(effectToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetParticleType").SetItems(items));
    }

    /**
     * Gets the block state tags<br>
     * at a location.
     *
     * @param variableToSet Variable to set
     * @param blockLocation Block location
     */
    public static void GetAllBlockData(Variable variableToSet, ILocation blockLocation, Tags.HideDefault hideDefaultTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(blockLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetAllBlockData").SetItems(items).SetTags(new Tag("Hide Default", hideDefaultTag.getJSONValue())));
    }

    /**
     * Gets the list of keys<br>
     * in this dictionary.
     *
     * @param variableToSet Variable to set
     * @param dictionaryTo Dictionary to pull from
     */
    public static void GetDictKeys(Variable variableToSet, Variable dictionaryTo) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(dictionaryTo);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetDictKeys").SetItems(items));
    }

    /**
     * Sets a particle effect's particle<br>
     * display material.
     *
     * @param variableToSet Variable to set
     * @param effectTo Effect to change (Optional)
     * @param particleMaterial Particle material
     */
    public static void SetParticleMat(Variable variableToSet, IParticle effectTo, IItem particleMaterial) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(effectTo != null) items.add(effectTo);
        items.add(particleMaterial);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetParticleMat").SetItems(items));
    }

    /**
     * Gets a location's X, Y, Z, pitch,<br>
     * or yaw coordinate.
     *
     * @param variableToSet Variable to set
     * @param locationToGet Location to get coordinate of
     */
    public static void GetCoord(Variable variableToSet, ILocation locationToGet, Tags.CoordinateType coordinateTypeTag, Tags.Coordinate coordinateTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(locationToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetCoord").SetItems(items).SetTags(new Tag("Coordinate Type", coordinateTypeTag.getJSONValue()), new Tag("Coordinate", coordinateTag.getJSONValue())));
    }

    /**
     * Removes a custom item tag.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param tagName Tag name
     */
    public static void RemoveItemTag(Variable variableToSet, IItem itemToChange, IText tagName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(tagName);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RemoveItemTag").SetItems(items));
    }

    /**
     * Sets a particle effect's particle<br>
     * size and size variation.
     *
     * @param variableToSet Variable to set
     * @param effectTo Effect to change (Optional)
     * @param particleSize Particle size
     * @param sizeVariation Size variation (%) (Optional)
     */
    public static void SetParticleSize(Variable variableToSet, IParticle effectTo, INumber particleSize, INumber sizeVariation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(effectTo != null) items.add(effectTo);
        items.add(particleSize);
        if(sizeVariation != null) items.add(sizeVariation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetParticleSize").SetItems(items));
    }

    /**
     * Gets a potion effect's duration.
     *
     * @param variableToSet Variable to set
     * @param potionToGet Potion to get duration of
     */
    public static void GetPotionDur(Variable variableToSet, IPotion potionToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(potionToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetPotionDur").SetItems(items));
    }

    /**
     * Sets the variable to a random<br>
     * location between two locations.
     *
     * @param variableToSet Variable to set
     * @param location1 Location 1
     * @param location2 Location 2
     */
    public static void RandomLoc(Variable variableToSet, ILocation location1, ILocation location2) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(location1);
        items.add(location2);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RandomLoc").SetItems(items));
    }

    /**
     * Sets a sound's type.
     *
     * @param variableToSet Variable to set
     * @param soundToChange Sound to change (Optional)
     * @param soundName Sound name (e.g. "rabbit eat")
     */
    public static void SetSoundType(Variable variableToSet, ISound soundToChange, IText soundName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(soundToChange != null) items.add(soundToChange);
        items.add(soundName);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetSoundType").SetItems(items));
    }

    /**
     * Gets a compass's lodestone location.
     *
     * @param variableToSet Variable to set
     * @param compassToGetLodestone Compass to get lodestone location of
     */
    public static void GetLodestoneLoc(Variable variableToSet, IItem compassToGetLodestone) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(compassToGetLodestone);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetLodestoneLoc").SetItems(items));
    }

    /**
     * Gets a container's name at<br>
     * a location.
     *
     * @param variableToSet Variable to set
     * @param containerLocation Container location
     */
    public static void GetContainerName(Variable variableToSet, ILocation containerLocation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(containerLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetContainerName").SetItems(items));
    }

    /**
     * Gets a particle effect's horizontal<br>
     * or vertical spread.
     *
     * @param variableToSet Variable to set
     * @param effectToGet Effect to get spread of
     */
    public static void GetParticleSprd(Variable variableToSet, IParticle effectToGet, Tags.Spread spreadTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(effectToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetParticleSprd").SetItems(items).SetTags(new Tag("Spread", spreadTag.getJSONValue())));
    }

    /**
     * Reflects a vector off a<br>
     * surface defined by another<br>
     * vector.
     *
     * @param variableToSet Variable to set
     * @param vectorToReflect Vector to reflect (Optional)
     * @param surfaceVector Surface vector
     */
    public static void ReflectVector(Variable variableToSet, IVector vectorToReflect, IVector surfaceVector) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(vectorToReflect != null) items.add(vectorToReflect);
        items.add(surfaceVector);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ReflectVector").SetItems(items));
    }

    /**
     * Gets a player head's owner<br>
     * name or UUID.
     *
     * @param variableToSet Variable to set
     * @param headToGetOwnerOf Head to get owner of
     */
    public static void GetHeadOwner(Variable variableToSet, IItem headToGetOwnerOf, Tags.TextValue textValueTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(headToGetOwnerOf);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetHeadOwner").SetItems(items).SetTags(new Tag("Text Value", textValueTag.getJSONValue())));
    }

    /**
     * Adds all entries from one<br>
     * dictionary into the other.
     *
     * @param dictionaryTo Dictionary to add to
     * @param dictionary Dictionary to append
     */
    public static void AppendDict(Variable dictionaryTo, Variable dictionary) {
        List<CodeValue> items = new ArrayList<>();

        items.add(dictionaryTo);
        items.add(dictionary);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("AppendDict").SetItems(items));
    }

    /**
     * Gets an item's maximum stack size.
     *
     * @param variableToSet Variable to set
     * @param itemToGetMaximumStack Item to get maximum stack size of
     */
    public static void GetMaxItemAmount(Variable variableToSet, IItem itemToGetMaximumStack) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(itemToGetMaximumStack);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetMaxItemAmount").SetItems(items));
    }

    /**
     * Gets a color's RGB/HSB/HSL<br>
     * number values as a list.
     *
     * @param variableToSet Variable to set
     * @param color Color
     */
    public static void GetColorChannels(Variable variableToSet, IText color, Tags.ColorChannels colorChannelsTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(color);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetColorChannels").SetItems(items).SetTags(new Tag("Color Channels", colorChannelsTag.getJSONValue())));
    }

    /**
     * Sets a location's rotation<br>
     * (pitch and yaw) to a direction.
     *
     * @param variableToSet Variable to set
     * @param locationToChange Location to change (Optional)
     * @param direction Direction
     */
    public static void SetDirection(Variable variableToSet, ILocation locationToChange, IVector direction) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToChange != null) items.add(locationToChange);
        items.add(direction);

        CodeManager.instance.addCodeBlock(new SetVariableBlock(" SetDirection ").SetItems(items));
    }

    /**
     * Sets a list variable's value at<br>
     * an index.
     *
     * @param listToChange List to change
     * @param index Index
     * @param valueToSet Value to set
     */
    public static void SetListValue(Variable listToChange, INumber index, CodeValue valueToSet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(listToChange);
        items.add(index);
        items.add(valueToSet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetListValue").SetItems(items));
    }

    /**
     * Sets a book's text.
     *
     * @param variableToSet Variable to set
     * @param book Book (Optional)
     * @param pages Pages
     */
    public static void SetBookText(Variable variableToSet, IItem book, IText[] pages) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(book != null) items.add(book);
        items.addAll(Arrays.asList(pages));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetBookText").SetItems(items));
    }

    /**
     * Sets a variable to a random<br>
     * value from a set.
     *
     * @param variableToSet Variable to set
     * @param valueSet Value set to choose from
     */
    public static void RandomValue(Variable variableToSet, CodeValue... valueSet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.addAll(Arrays.asList(valueSet));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RandomValue").SetItems(items));
    }

    /**
     * Sets an item's material.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param material Material
     */
    public static void SetItemType(Variable variableToSet, IItem itemToChange, IText material) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(material);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemType").SetItems(items));
    }

    /**
     * Gets the given sound's type.
     *
     * @param variableToSet Variable to set
     * @param soundToGetTypeOf Sound to get type of
     */
    public static void GetSoundType(Variable variableToSet, ISound soundToGetTypeOf) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(soundToGetTypeOf);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetSoundType").SetItems(items));
    }

    /**
     * Gets a list variable's value at<br>
     * an index.
     *
     * @param variableToSet Variable to set
     * @param listToGetValueOf List to get value of
     * @param index Index
     */
    public static void GetListValue(Variable variableToSet, Variable listToGetValueOf, INumber index) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(listToGetValueOf);
        items.add(index);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetListValue").SetItems(items));
    }

    /**
     * Sets a variable to the trigonometric<br>
     * tangent function of a number.
     *
     * @param variableToSet Variable to set
     * @param numberInput Number input
     */
    public static void Tangent(Variable variableToSet, INumber numberInput, Tags.TangentVariant tangentVariantTag, Tags.Input inputTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(numberInput);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Tangent").SetItems(items).SetTags(new Tag("Tangent Variant", tangentVariantTag.getJSONValue()), new Tag("Input", inputTag.getJSONValue())));
    }

    /**
     * Sets a variable to a text, or combines<br>
     * multiple values into one text.
     *
     * @param variableToSet Variable to set
     * @param textToSetTo Text to set to (Optional)
     */
    public static void Text(Variable variableToSet, CodeValue[] textToSetTo, Tags.TextValueMerging textValueMergingTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(textToSetTo != null) items.addAll(Arrays.asList(textToSetTo));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Text").SetItems(items).SetTags(new Tag("Text Value Merging", textValueMergingTag.getJSONValue())));
    }

    /**
     * Gets a Voronoi noise value: A cellular<br>
     * noise in which the value of an entire<br>
     * cell is calculated.
     *
     * @param variableToSet Variable to set
     * @param noiseLocation Noise location
     * @param cellFrequency Cell frequency (Optional)
     * @param cellScatter Cell scatter (Optional)
     * @param generationSeed Generation seed (Optional)
     */
    public static void VoronoiNoise(Variable variableToSet, ILocation noiseLocation, INumber cellFrequency, INumber cellScatter, INumber generationSeed, Tags.CellEdgeType cellEdgeTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(noiseLocation);
        if(cellFrequency != null) items.add(cellFrequency);
        if(cellScatter != null) items.add(cellScatter);
        if(generationSeed != null) items.add(generationSeed);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("VoronoiNoise").SetItems(items).SetTags(new Tag("Cell Edge Type", cellEdgeTypeTag.getJSONValue())));
    }

    /**
     * Creates a color based on hue,<br>
     * saturation, and brightness.
     *
     * @param variableToSet Variable to set
     * @param hue Hue (Color circle, 0-360)
     * @param saturation Saturation (0-100) (Optional)
     * @param brightness Brightness (0-100) (Optional)
     */
    public static void HSBColor(Variable variableToSet, INumber hue, INumber saturation, INumber brightness) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(hue);
        if(saturation != null) items.add(saturation);
        if(brightness != null) items.add(brightness);
        CodeManager.instance.addCodeBlock(new SetVariableBlock("HSBColor").SetItems(items));
    }

    /**
     * Increments a number variable<br>
     * by 1 or more other numbers.
     *
     * @param variable Variable to increment
     * @param number Number(s) to increment by (Optional)
     */
    public static void Increment(Variable variable, INumber... number) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variable);
        if(number != null) items.addAll(Arrays.asList(number));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("+=").SetItems(items));
    }

    /**
     * Gets a colorable item's color.
     *
     * @param variable Variable to set
     * @param itemToGetColorOf Item to get color of
     */
    public static void GetItemColor(Variable variable, IItem itemToGetColorOf) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variable);
        items.add(itemToGetColorOf);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemColor").SetItems(items));
    }

    /**
     * Inserts a value into a list<br>
     * variable at an index, shifting<br>
     * all values at and after it to<br>
     * the right.
     *
     * @param listToChange List to change
     * @param index Index
     * @param valueToInsert Value to insert
     */
    public static void InsertListValue(Variable listToChange, INumber index, CodeValue valueToInsert) {
        List<CodeValue> items = new ArrayList<>();

        items.add(listToChange);
        items.add(index);
        items.add(valueToInsert);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("InsertListValue").SetItems(items));
    }

    /**
     * Sets a sound's volume.
     *
     * @param variableToSet Variable to set
     * @param soundToChange Sound to change (Optional)
     * @param volume Volume
     */
    public static void SetSoundVolume(Variable variableToSet, ISound soundToChange, INumber volume) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(soundToChange != null) items.add(soundToChange);
        items.add(volume);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetSoundVolume").SetItems(items));
    }

    /**
     * Sets a location's X, Y, Z, pitch,<br>
     * or yaw coordinate.
     *
     * @param variableToSet Variable to set
     * @param locationToChange Location to change (Optional)
     * @param coordinate Coordinate
     */
    public static void SetCoord(Variable variableToSet, ILocation locationToChange, INumber coordinate, Tags.CoordinateType coordinateTypeTag, Tags.Coordinate coordinateTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToChange != null) items.add(locationToChange);
        items.add(coordinate);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetCoord").SetItems(items).SetTags(new Tag("Coordinate Type", coordinateTypeTag.getJSONValue()), new Tag("Coordinate", coordinateTag.getJSONValue())));
    }

    /**
     * Sets a variable to the sum<br>
     * of the given vectors.
     *
     * @param variableToSet Variable to set
     * @param vectorsToAdd Vectors to add
     */
    public static void AddVectors(Variable variableToSet, IVector... vectorsToAdd) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.addAll(Arrays.asList(vectorsToAdd));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("AddVectors").SetItems(items));
    }

    /**
     * Gets an item's enchantments.
     *
     * @param variableToSet Variable to set
     * @param itemToGetEnchantmentsFrom Item to get enchantments from
     */
    public static void GetItemEnchants(Variable variableToSet, IItem itemToGetEnchantmentsFrom) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(itemToGetEnchantmentsFrom);

        CodeManager.instance.addCodeBlock(new SetVariableBlock(" GetItemEnchants ").SetItems(items));
    }

    /**
     * Sets a player head's texture<br>
     * using an owning player<br>
     * or custom texture.
     *
     * @param variableToSet Variable to set
     * @param playerHead Player head (Optional)
     * @param ownerName Owner name, UUID or texture value
     */
    public static void SetHeadTexture(Variable variableToSet, IItem playerHead, IText ownerName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(playerHead != null) items.add(playerHead);
        items.add(ownerName);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetHeadTexture").SetItems(items));
    }

    /**
     * Gets a Perlin noise value: A type<br>
     * of fractal gradient noise.
     *
     * @param variableToSet Variable to set
     * @param noiseLocation Noise location
     * @param frequency Frequency (Scale) (Optional)
     * @param octaves Octaves (Perlin layers) (Optional)
     * @param octaveFrequencyGain Octave frequency gain (Optional)
     * @param octaveAmplitudeGain Octave amplitude gain (Optional)
     * @param generationSeed Generation seed (Optional)
     */
    public static void PerlinNoise(Variable variableToSet, ILocation noiseLocation, INumber frequency, INumber octaves, INumber octaveFrequencyGain, INumber octaveAmplitudeGain, INumber generationSeed, Tags.FractalType fractalTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(noiseLocation);
        if(frequency != null) items.add(frequency);
        if(octaves != null) items.add(octaves);
        if(octaveFrequencyGain != null) items.add(octaveFrequencyGain);
        if(octaveAmplitudeGain != null) items.add(octaveAmplitudeGain);
        if(generationSeed != null) items.add(generationSeed);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("PerlinNoise").SetItems(items).SetTags(new Tag("Fractal Type", fractalTypeTag.getJSONValue())));
    }

    /**
     * Gets a Worley noise value: A cellular<br>
     * noise in which the distance between<br>
     * two cells' nuclei is calculated.
     *
     * @param variableToSet Variable to set
     * @param noiseLocation Noise location
     * @param cellFrequency Cell frequency (Optional)
     * @param cellScatter Cell scatter (Optional)
     * @param generationSeed Generation seed (Optional)
     */
    public static void WorleyNoise(Variable variableToSet, ILocation noiseLocation, INumber cellFrequency, INumber cellScatter, INumber generationSeed, Tags.CellEdgeType cellEdgeTypeTag, Tags.DistanceCalculation distanceCalculationTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(noiseLocation);
        if(cellFrequency != null) items.add(cellFrequency);
        if(cellScatter != null) items.add(cellScatter);
        if(generationSeed != null) items.add(generationSeed);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("WorleyNoise").SetItems(items).SetTags(new Tag("Cell Edge Type", cellEdgeTypeTag.getJSONValue()), new Tag("Distance Calculation", distanceCalculationTag.getJSONValue())));
    }

    /**
     * Sets a colorable item's color.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param colorHexadecimal Color hexadecimal
     */
    public static void SetItemColor(Variable variableToSet, IItem itemToChange, IText colorHexadecimal) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(colorHexadecimal);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemColor").SetItems(items));
    }

    /**
     * Gets a particle effect's particle<br>
     * color.
     *
     * @param variableToSet Variable to set
     * @param effectToGet Effect to get color of
     */
    public static void GetParticleColor(Variable variableToSet, IParticle effectToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(effectToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetParticleColor").SetItems(items));
    }

    /**
     * Sets a sound's pitch or note
     *
     * @param variableToSet Variable to set
     * @param soundToChange Sound to change (Optional)
     * @param pitch Pitch
     */
    public static void SetSoundPitch(Variable variableToSet, ISound soundToChange, INumber pitch) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(soundToChange != null) items.add(soundToChange);
        items.add(pitch);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetSoundPitch").SetItems(items));
    }

    /**
     * Sorts a list variable's values.
     *
     * @param variableToSet Variable to set
     * @param listToSort List to sort (Optional)
     */
    public static void SortList(Variable variableToSet, Variable listToSort, Tags.SortOrder sortOrderTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(listToSort != null) items.add(listToSort);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SortList").SetItems(items).SetTags(new Tag("Sort Order", sortOrderTag.getJSONValue())));
    }

    /**
     * Removes the dictionary entry<br>
     * with the given key.
     *
     * @param dictionaryToChange Dictionary to change
     * @param keyToRemove Key to remove
     * @param expectedValue Expected value(s) (Optional)
     */
    public static void RemoveDictEntry(Variable dictionaryToChange, IText keyToRemove, CodeValue... expectedValue) {
        List<CodeValue> items = new ArrayList<>();

        items.add(dictionaryToChange);
        items.add(keyToRemove);
        if(expectedValue != null) items.addAll(Arrays.asList(expectedValue));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RemoveDictEntry").SetItems(items));
    }

    /**
     * Converts a numerical timestamp to<br>
     * a human-readable time/date text<br>
     * using a date format.
     *
     * @param variableToSet Variable to set
     * @param timeToFormat Time to format
     * @param customFormat Custom Format (Optional)
     */
    public static void FormatTime(Variable variableToSet, INumber timeToFormat, IText customFormat, Tags.Format formatTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(timeToFormat);
        if(customFormat != null) items.add(customFormat);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("FormatTime").SetItems(items).SetTags(new Tag("Format", formatTag.getJSONValue())));
    }

    /**
     * Sets which components of an item<br>
     * are visible, similar to /hideflags.
     *
     * @param variableToSet Variable to set
     * @param item Item (Optional)
     */
    public static void SetItemFlags(Variable variableToSet, IItem item, Tags.HideColor hideColorTag, Tags.HideEnchantments hideEnchantmentsTag, Tags.HideAttributes hideAttributesTag, Tags.HideUnbreakable hideUnbreakableTag, Tags.HideCanDestroy hideCanDestroyTag, Tags.HideCanPlaceOn hideCanPlaceOnTag, Tags.HidePotionEffects hidePotionEffectsTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(item != null) items.add(item);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemFlags").SetItems(items).SetTags(new Tag("Hide Color", hideColorTag.getJSONValue()), new Tag("Hide Enchantments", hideEnchantmentsTag.getJSONValue()), new Tag("Hide Attributes", hideAttributesTag.getJSONValue()), new Tag("Hide Unbreakable", hideUnbreakableTag.getJSONValue()), new Tag("Hide Can Destroy", hideCanDestroyTag.getJSONValue()), new Tag("Hide Can Place On", hideCanPlaceOnTag.getJSONValue()), new Tag("Hide Potion Effects", hidePotionEffectsTag.getJSONValue())));
    }

    /**
     * Gets the potion effects applied by<br>
     * an item.
     *
     * @param variableToSet Variable to set
     * @param itemToGetEffectsFrom Item to get effects from
     */
    public static void GetItemEffects(Variable variableToSet, IItem itemToGetEffectsFrom) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(itemToGetEffectsFrom);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemEffects").SetItems(items));
    }

    /**
     * Sets the potion effects applied by<br>
     * an item.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param itemEffects Item effects
     */
    public static void SetItemEffects(Variable variableToSet, IItem itemToChange, IPotion... itemEffects) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.addAll(Arrays.asList(itemEffects));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetItemEffects").SetItems(items));
    }

    /**
     * Decrements a number variable<br>
     * by 1 or more other numbers.
     *
     * @param variable Variable to decrement
     * @param number Number(s) to decrement by (Optional)
     */
    public static void Decrement(Variable variable, INumber... number) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variable);
        if(number != null) items.addAll(Arrays.asList(number));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("-=").SetItems(items));
    }

    /**
     * Gets the value of a custom<br>
     * item tag.
     *
     * @param variableToSet Variable to set
     * @param itemToGetTagOf Item to get tag of
     * @param tagName Tag name
     */
    public static void GetItemTag(Variable variableToSet, IItem itemToGetTagOf, IText tagName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(itemToGetTagOf);
        items.add(tagName);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetItemTag").SetItems(items));
    }

    /**
     * Creates a list from the given<br>
     * values.
     *
     * @param variableToSet Variable to set
     * @param valueList Value list (Optional)
     */
    public static void CreateList(Variable variableToSet, CodeValue... valueList) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(valueList != null) items.addAll(Arrays.asList(valueList));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("CreateList").SetItems(items));
    }

    /**
     * Adds a list to the end of<br>
     * another list variable.
     *
     * @param listToAppendTo List to append to
     * @param list List(s) to append
     */
    public static void AppendList(Variable listToAppendTo, Variable... list) {
        List<CodeValue> items = new ArrayList<>();

        items.add(listToAppendTo);
        items.addAll(Arrays.asList(list));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("AppendList").SetItems(items));
    }

    /**
     * Gets a container's contents<br>
     * at a location.
     *
     * @param variableToSet Variable to set
     * @param containerLocation Container location
     */
    public static void GetContainerItems(Variable variableToSet, ILocation containerLocation, Tags.IgnoreEmptySlots ignoreEmptySlotsTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(containerLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetContainerItems").SetItems(items).SetTags(new Tag("Ignore Empty Slots", ignoreEmptySlotsTag.getJSONValue())));
    }

    /**
     * Shifts a location toward another<br>
     * location by the given distance.
     *
     * @param variableToSet Variable to set
     * @param locationToShift Location to shift (Optional)
     * @param targetLocation Target location
     * @param shiftDistance Shift distance (Optional)
     */
    public static void ShiftToward(Variable variableToSet, ILocation locationToShift, ILocation targetLocation, INumber shiftDistance) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToShift != null) items.add(locationToShift);
        items.add(targetLocation);
        if(shiftDistance != null) items.add(shiftDistance);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ShiftToward").SetItems(items));
    }

    /**
     * Trims a list, starting and ending<br>
     * at the given indices.
     *
     * @param variableToSet Variable to set
     * @param listToTrim List to trim (Optional)
     * @param startIndex Start index
     * @param endIndex End index (Optional)
     */
    public static void TrimList(Variable variableToSet, Variable listToTrim, INumber startIndex, INumber endIndex) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(listToTrim != null) items.add(listToTrim);
        items.add(startIndex);
        if(endIndex != null) items.add(endIndex);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("TrimList").SetItems(items));
    }

    /**
     * Searches for part of a text and<br>
     * removes all instances of it.
     *
     * @param variable Variable to set
     * @param textToChange Text to change (Optional)
     * @param textToRemove Text to remove
     */
    public static void RemoveText(Variable variable, IText textToChange, IText[] textToRemove, Tags.RegularExpressions regularExpressionsTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variable);
        if(textToChange != null) items.add(textToChange);
        items.addAll(Arrays.asList(textToRemove));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RemoveText").SetItems(items).SetTags(new Tag("Regular Expressions", regularExpressionsTag.getJSONValue())));
    }

    /**
     * Sets a variable to the trigonometric<br>
     * cosine function of a number.
     *
     * @param variableToSet Variable to set
     * @param numberInput Number input
     */
    public static void Cosine(Variable variableToSet, INumber numberInput, Tags.CosineVariant cosineVariantTag, Tags.Input inputTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(numberInput);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Cosine").SetItems(items).SetTags(new Tag("Cosine Variant", cosineVariantTag.getJSONValue()), new Tag("Input", inputTag.getJSONValue())));
    }

    /**
     * Sets a vector's X, Y, or Z<br>
     * component.
     *
     * @param variableToSet Variable to set
     * @param vectorToChange Vector to change (Optional)
     * @param component Component
     */
    public static void SetVectorComp(Variable variableToSet, IVector vectorToChange, INumber component, Tags.Component componentTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(vectorToChange != null) items.add(vectorToChange);
        items.add(component);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetVectorComp").SetItems(items).SetTags(new Tag("Component", componentTag.getJSONValue())));
    }

    /**
     * Converts a text to a number.
     *
     * @param variableToSet Variable to set
     * @param textToConvert Text to convert (Optional)
     */
    public static void ParseNumber(Variable variableToSet, IText textToConvert) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(textToConvert != null) items.add(textToConvert);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ParseNumber").SetItems(items));
    }

    /**
     * Raises a number to the power<br>
     * of an exponent.
     *
     * @param variableToSet Variable to set
     * @param numberInput Number input (Optional)
     * @param exponent Exponent (Optional)
     */
    public static void Exponent(Variable variableToSet, INumber numberInput, INumber exponent) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(numberInput != null) items.add(numberInput);
        if(exponent != null) items.add(exponent);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Exponent").SetItems(items));
    }

    /**
     * Searches for a value in a list<br>
     * variable and gets the index if<br>
     * found.
     *
     * @param variableToSet Variable to set
     * @param listToSearchIn List to search in
     * @param valueToSearch Value to search
     */
    public static void GetValueIndex(Variable variableToSet, Variable listToSearchIn, CodeValue valueToSearch, Tags.SearchOrder searchOrderTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(listToSearchIn);
        items.add(valueToSearch);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetValueIndex").SetItems(items).SetTags(new Tag("Search Order", searchOrderTag.getJSONValue())));
    }

    /**
     * Removes an enchantment from an item.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param enchantmentName Enchantment name
     */
    public static void RemItemEnchant(Variable variableToSet, IItem itemToChange, IText enchantmentName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(enchantmentName);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RemItemEnchant").SetItems(items));
    }

    /**
     * Gets a book's text.
     *
     * @param variableToSet Variable to set
     * @param book Book
     * @param pageNumber Page number (Optional)
     */
    public static void GetBookText(Variable variableToSet, IItem book, INumber pageNumber) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(book);
        if(pageNumber != null) items.add(pageNumber);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetBookText").SetItems(items));
    }

    /**
     * Randomizes the order of a<br>
     * list variable's values.
     *
     * @param variableToSet Variable to set
     * @param listToRandomize List to randomize (Optional)
     */
    public static void RandomizeList(Variable variableToSet, Variable listToRandomize) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(listToRandomize != null) items.add(listToRandomize);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RandomizeList").SetItems(items));
    }

    /**
     * Checks if a number is between<br>
     * a minimum and maximum value, and<br>
     * if not, sets it to the nearest.
     *
     * @param variableToSet Variable to set
     * @param numberToClamp Number to clamp (Optional)
     * @param minimum Minimum
     * @param maximum Maximum
     */
    public static void ClampNumber(Variable variableToSet, INumber numberToClamp, INumber minimum, INumber maximum) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(numberToClamp != null) items.add(numberToClamp);
        items.add(minimum);
        items.add(maximum);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ClampNumber").SetItems(items));
    }

    /**
     * Rounds a number to a whole<br>
     * number or multiple.
     *
     * @param variableToSet Variable to set
     * @param numberToRound Number to round (Optional)
     * @param roundMultiple Round multiple (Optional)
     */
    public static void Round(Variable variableToSet, INumber numberToRound, INumber roundMultiple, Tags.RoundMode roundModeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(numberToRound != null) items.add(numberToRound);
        if(roundMultiple != null) items.add(roundMultiple);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("Round").SetItems(items).SetTags(new Tag("Round Mode", roundModeTag.getJSONValue())));
    }

    /**
     * Gets a sound's pitch or<br>
     * note.
     *
     * @param variableToSet Variable to set
     * @param soundToGetPitchOr Sound to get pitch or note of
     */
    public static void GetSoundPitch(Variable variableToSet, ISound soundToGetPitchOr, Tags.ReturnValueType returnValueTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(soundToGetPitchOr);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetSoundPitch").SetItems(items).SetTags(new Tag("Return Value Type", returnValueTypeTag.getJSONValue())));
    }

    /**
     * Combines a list of texts.
     *
     * @param variableToSet Variable to set
     * @param textsToJoin Texts to join
     * @param joiningText Joining text (Optional)
     * @param finalJoiningText Final joining text (Optional)
     */
    public static void JoinText(Variable variableToSet, Variable textsToJoin, IText joiningText, IText finalJoiningText) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(textsToJoin);
        if(joiningText != null) items.add(joiningText);
        if(finalJoiningText != null) items.add(finalJoiningText);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("JoinText").SetItems(items));
    }

    /**
     * Converts color codes written in<br>
     * "&" or hex format to functional<br>
     * color codes, or vice versa.
     *
     * @param variableToSet Variable to set
     * @param textToTranslate Text to translate (Optional)
     */
    public static void TranslateColors(Variable variableToSet, IText textToTranslate, Tags.TranslationType translationTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(textToTranslate != null) items.add(textToTranslate);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("TranslateColors").SetItems(items).SetTags(new Tag("Translation Type", translationTypeTag.getJSONValue())));
    }

    /**
     * Gets a block's current<br>
     * growth at a location.
     *
     * @param variableToSet Variable to set
     * @param blockLocation Block location
     */
    public static void GetBlockGrowth(Variable variableToSet, ILocation blockLocation, Tags.GrowthUnit growthUnitTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(blockLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetBlockGrowth").SetItems(items).SetTags(new Tag("Growth Unit", growthUnitTag.getJSONValue())));
    }

    /**
     * Gets all tags registered<br>
     * on an item.
     *
     * @param variableToSet Variable to set
     * @param itemToGetTagsFrom Item to get tags from
     */
    public static void GetAllItemTags(Variable variableToSet, IItem itemToGetTagsFrom) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(itemToGetTagsFrom);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetAllItemTags").SetItems(items));
    }

    /**
     * Removes all matching values<br>
     * from a list variable.
     *
     * @param listToChange List to change
     * @param value Value(s) to remove
     */
    public static void RemoveListValue(Variable listToChange, CodeValue... value) {
        List<CodeValue> items = new ArrayList<>();

        items.add(listToChange);
        items.addAll(Arrays.asList(value));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("RemoveListValue").SetItems(items));
    }

    /**
     * Shifts a location in multiple directions<br>
     * based on its rotation.
     *
     * @param variableToSet Variable to set
     * @param locationToShift Location to shift (Optional)
     * @param forwardsChange Forwards change
     * @param upwardsChange Upwards change (Optional)
     * @param sidewaysChange Sideways change (-L / +R) (Optional)
     */
    public static void ShiftAllDirections(Variable variableToSet, ILocation locationToShift, INumber forwardsChange, INumber upwardsChange, INumber sidewaysChange) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(locationToShift != null) items.add(locationToShift);
        items.add(forwardsChange);
        if(upwardsChange != null) items.add(upwardsChange);
        if(sidewaysChange != null) items.add(sidewaysChange);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ShiftAllDirections").SetItems(items));
    }

    /**
     * Sets which blocks an item<br>
     * can be placed on in Adventure Mode.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param placeableBlocks Placeable blocks
     */
    public static void SetCanPlaceOn(Variable variableToSet, IItem itemToChange, CodeValue... placeableBlocks) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.addAll(Arrays.asList(placeableBlocks));

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetCanPlaceOn").SetItems(items));
    }

    /**
     * Gets a block's material<br>
     * at a location.
     *
     * @param variableToSet Variable to set
     * @param blockLocation Block location
     */
    public static void GetBlockType(Variable variableToSet, ILocation blockLocation, Tags.ReturnValueType returnValueTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(blockLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetBlockType").SetItems(items).SetTags(new Tag("Return Value Type", returnValueTypeTag.getJSONValue())));
    }

    /**
     * Get a dictionary variable's<br>
     * value for a key.
     *
     * @param variableToSet Variable to set
     * @param dictionaryTo Dictionary to pull from
     * @param key Key
     */
    public static void GetDictValue(Variable variableToSet, Variable dictionaryTo, IText key) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(dictionaryTo);
        items.add(key);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetDictValue").SetItems(items));
    }

    /**
     * Gets a container's lock key at a<br>
     * location.
     *
     * @param variableToSet Variable to set
     * @param containerLocation Container location
     */
    public static void ContainerLock(Variable variableToSet, ILocation containerLocation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(containerLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("ContainerLock").SetItems(items));
    }

    /**
     * Gets a block's redstone<br>
     * power level.
     *
     * @param variableToSet Variable to set
     * @param blockLocation Block location
     */
    public static void GetBlockPower(Variable variableToSet, ILocation blockLocation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(blockLocation);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetBlockPower").SetItems(items));
    }

    /**
     * Gets a vector's length.
     *
     * @param variableToSet Variable to set
     * @param vectorToGet Vector to get length of
     */
    public static void GetVectorLength(Variable variableToSet, IVector vectorToGet, Tags.LengthType lengthTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(vectorToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetVectorLength").SetItems(items).SetTags(new Tag("Length Type", lengthTypeTag.getJSONValue())));
    }

    /**
     * Sets the model value<br>
     * used in resource packs.
     *
     * @param variableToSet Variable to set
     * @param itemToChange Item to change (Optional)
     * @param modelValue Model value
     */
    public static void SetModelData(Variable variableToSet, IItem itemToChange, INumber modelValue) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(itemToChange != null) items.add(itemToChange);
        items.add(modelValue);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("SetModelData").SetItems(items));
    }

    /**
     * Gets a particle effect's particle<br>
     * size.
     *
     * @param variableToSet Variable to set
     * @param effectToGet Effect to get size of (Optional)
     */
    public static void GetParticleSize(Variable variableToSet, IParticle effectToGet) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        if(effectToGet != null) items.add(effectToGet);

        CodeManager.instance.addCodeBlock(new SetVariableBlock("GetParticleSize").SetItems(items));
    }
}