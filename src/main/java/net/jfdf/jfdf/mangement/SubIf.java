package net.jfdf.jfdf.mangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;
import net.jfdf.jfdf.values.Tags.*;

public class SubIf {
    private final SubIfType subIfType;
    private boolean inverseIf = false;
    private List<CodeValue> items;
    private List<Tag> tags;

    public SubIf(SubIfType subIfType) {
        this.subIfType = subIfType;
        this.items = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public SubIf(SubIfType subIfType, boolean inverseIf) {
        this.subIfType = subIfType;
        this.inverseIf = inverseIf;
        this.items = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public SubIf SetItems(List<CodeValue> items) {
        this.items = items;
        return this;
    }

    public SubIf SetItems(CodeValue... items) {
        this.items = Arrays.asList(items);
        return this;
    }

    public SubIf SetTags(Tag... tags) {
        this.tags = Arrays.asList(tags);
        return this;
    }

    public SubIf InverseIf() {
        this.inverseIf = !this.inverseIf;
        return this;
    }

    public SubIf SetInverted(boolean inverseIf) {
        this.inverseIf = inverseIf;
        return this;
    }
    
    public SubIfType GetType() {
        return subIfType;
    }

    public List<Tag> GetTags() {
        return tags;
    }

    public List<CodeValue> GetItems() {
        return items;
    }

    public boolean IsInversed() {
        return inverseIf;
    }

    public static class Player {
        private static List<CodeValue> toList(CodeValue[] array) {
            List<CodeValue> list = Arrays.asList(array);
            list.remove(null);
            return list;
        }

        /**
        * Checks if a player is sneaking.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsSneaking(boolean inverseIf) {
            return new SubIf(SubIfType.PLAYER_IS_SNEAKING);
        }

        /**
        * Checks if a player is sprinting
        * or using the sprint key to swim.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsSprinting(boolean inverseIf) {
            return new SubIf(SubIfType.PLAYER_IS_SPRINTING);
        }

        /**
        * Checks if a player is
        * gliding with elytra.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsGliding(boolean inverseIf) {
            return new SubIf(SubIfType.PLAYER_IS_GLIDING);
        }

        /**
        * Checks if a player is flying.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsFlying(boolean inverseIf) {
            return new SubIf(SubIfType.PLAYER_IS_FLYING);
        }

        /**
        * Checks if a player is
        * supported by a block.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsGrounded(boolean inverseIf) {
            return new SubIf(SubIfType.PLAYER_IS_GROUNDED);
        }

        /**
        * Checks if a player
        * is in water or lava.
        * 
        * Chest Parameters:
        * (None)
        * 
        * Additional Info:
        * > Use 'Is Sprinting'
        * to check if a player
        * is swimming with the
        * swimming animation.
        */
        public static SubIf IsSwimming(boolean inverseIf) {
            return new SubIf(SubIfType.PLAYER_IS_SWIMMING);
        }

        /**
        * Checks if a player is blocking
        * with a shield.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsBlocking(boolean inverseIf) {
            return new SubIf(SubIfType.PLAYER_IS_BLOCKING);
        }

        /**
        * Checks if a player is looking
        * at a block of a certain type
        * or at a certain location.
        * 
        * Chest Parameters:
        * Block(s)* - Block(s) to check for
        * OR
        * Location(s) - Location(s) to check for
        * Number* - Maximum distance
        * from target block
        * # 1 Tag
        * 
        * *Optional
        * 
        * Additional Info:
        * > The distance argument determines
        * how far away the target player's
        * target block can be from the
        * check locations.
        */
        public static SubIf IsLookingAt(Item[] blocksToCheckFor, FluidMode fluidMode,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(blocksToCheckFor != null) items.addAll(toList(blocksToCheckFor));
            return new SubIf(SubIfType.PLAYER_IS_LOOKING_AT).SetItems(items).SetTags(new Tag("Fluid Mode", fluidMode.getJSONValue()));
        }

        /**
        * Checks if a player is standing
        * on a block of a certain type
        * or at a certain location.
        * 
        * Chest Parameters:
        * Block(s)* - Block to check for
        * OR
        * Location(s) - Location to check for
        * 
        * *Optional
        */
        public static SubIf StandingOn(Item[] blockToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(blockToCheckFor != null) items.addAll(toList(blockToCheckFor));
            return new SubIf(SubIfType.PLAYER_STANDING_ON).SetItems(items);
        }

        /**
        * Checks if a player is within a
        * certain range of a location.
        * (default: 5 blocks)
        * 
        * Chest Parameters:
        * Location(s) - Center location
        * Number* - Range
        * # 1 Tag
        * 
        * *Optional
        */
        public static SubIf IsNear(Location[] centerLocation, Number range, IgnoreY_Axis ignoreYAxis,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(centerLocation));
            if(range != null) items.add(range);
            return new SubIf(SubIfType.PLAYER_IS_NEAR).SetItems(items).SetTags(new Tag("Ignore Y-Axis", ignoreYAxis.getJSONValue()));
        }

        /**
        * Checks if a player (or a location)
        * is within their world border.
        * 
        * Chest Parameters:
        * Location* - Location to check
        * 
        * *Optional
        * 
        * Emperor Exclusive
        */
        public static SubIf InWorldBorder(Location locationToCheck,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(locationToCheck != null) items.add(locationToCheck);
            return new SubIf(SubIfType.PLAYER_IS_IN_WORLD_BORDER).SetItems(items);
        }

        /**
        * Checks if a player is holding
        * an item in their hand.
        * 
        * Chest Parameters:
        * Item(s)* - Item(s) to check for
        * # 1 Tag
        * 
        * *Optional
        */
        public static SubIf IsHolding(Item[] itemsToCheckFor, HandSlot handSlot,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(itemsToCheckFor != null) items.addAll(toList(itemsToCheckFor));
            return new SubIf(SubIfType.PLAYER_IS_HOLDING).SetItems(items).SetTags(new Tag("Hand Slot", handSlot.getJSONValue()));
        }

        /**
        * Checks if a player has an item
        * in their inventory.
        * 
        * Chest Parameters:
        * Item(s) - Item(s) to check for
        * # 1 Tag
        */
        public static SubIf HasItem(Item[] itemsToCheckFor, CheckMode checkMode,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(itemsToCheckFor));
            return new SubIf(SubIfType.PLAYER_HAS_ITEM).SetItems(items).SetTags(new Tag("Check Mode", checkMode.getJSONValue()));
        }

        /**
        * Checks if a player is wearing
        * an item.
        * 
        * Chest Parameters:
        * Item(s) - Item(s) to check for
        * # 1 Tag
        */
        public static SubIf IsWearing(Item[] itemsToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(itemsToCheckFor));
            return new SubIf(SubIfType.PLAYER_IS_WEARING).SetItems(items);
        }

        /**
        * Checks if a player is currently
        * using an item such as
        * a bow, consumable, or shield.
        * 
        * Chest Parameters:
        * Item(s)* - Item(s) to check
        * 
        * *Optional
        * 
        * Additional Info:
        * > Only checks for material
        */
        public static SubIf IsUsingItem(Item[] itemsToCheck,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(itemsToCheck != null) items.addAll(toList(itemsToCheck));
            return new SubIf(SubIfType.PLAYER_IS_USING_ITEM).SetItems(items);
        }

        /**
        * Checks if a player does not have a
        * cooldown applied to an item type.
        * 
        * Chest Parameters:
        * Item(s) - Item type(s) to check
        * 
        * Additional Info:
        * > The check will succeed if any
        * of the given items are not on
        * cooldown.
        */
        public static SubIf NoItemCooldown(Item[] itemTypesToCheck,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(itemTypesToCheck));
            return new SubIf(SubIfType.PLAYER_IS_ITEM_HAS_NO_COOLDOWN).SetItems(items);
        }

        /**
        * Checks if a player has an item
        * in a specific inventory slot.
        * 
        * Chest Parameters:
        * Number(s) - Slot(s) to check
        * Item(s)* - Item(s) to check for
        * 
        * *Optional
        */
        public static SubIf HasSlotItem(Number[] slotsToCheck, Item[] itemsToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(slotsToCheck));
            if(itemsToCheckFor != null) items.addAll(toList(itemsToCheckFor));
            return new SubIf(SubIfType.PLAYER_HAS_ITEM_IN_SLOT).SetItems(items);
        }

        /**
        * Checks if a player's currently
        * open inventory menu contains
        * an item in a specific slot.
        * 
        * Chest Parameters:
        * Number(s) - Slot ID(s) to check
        * Item(s)* - Item(s) to check for
        * 
        * *Optional
        */
        public static SubIf MenuSlotEquals(Number[] slotIDsToCheck, Item[] itemsToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(slotIDsToCheck));
            if(itemsToCheckFor != null) items.addAll(toList(itemsToCheckFor));
            return new SubIf(SubIfType.PLAYER_INVENTORY_MENU_SLOT_EQUALS).SetItems(items);
        }

        /**
        * Checks if the item that is being moved
        * with a player's cursor is a certain item.
        * 
        * Chest Parameters:
        * Item(s)* - Items(s) to check for
        * 
        * *Optional
        * 
        * Additional Info:
        * > If multiple items are in the chest,
        * the target can have any of them on
        * their cursor.
        * > When used on the Player Click Item
        * in Own Inventory Event, 'Cursor Item
        * Equals' checks the previous cursor
        * item, not the item that was clicked.
        */
        public static SubIf CursorItem(Item[] itemssToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(itemssToCheckFor != null) items.addAll(toList(itemssToCheckFor));
            return new SubIf(SubIfType.PLAYER_CURSOR_ITEM_EQUALS).SetItems(items);
        }

        /**
        * Checks if a player's inventory
        * has enough room for one or
        * more items to be given.
        * 
        * Chest Parameters:
        * Item* - Item(s) to check
        * # 2 Tags
        * 
        * *Optional
        * 
        * Additional Info:
        * > Not specifying any items will
        * check if the target has an empty
        * slot in their inventory.
        */
        public static SubIf HasRoomForItem(Item itemsToCheck, CheckedSlots checkedSlots,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(itemsToCheck != null) items.add(itemsToCheck);
            return new SubIf(SubIfType.PLAYER_HAS_ROOM_FOR_ITEM).SetItems(items).SetTags(new Tag("Checked Slots", checkedSlots.getJSONValue()));
        }

        /**
        * Checks if a player's username is equal
        * to one of the usernames in the chest
        * (case insensitive).
        * 
        * Chest Parameters:
        * Text(s) - Name(s) to check for
        * 
        * Additional Info:
        * > Works with UUID's.
        */
        public static SubIf NameEquals(Text[] namesToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(namesToCheckFor));
            return new SubIf(SubIfType.PLAYER_NAME_EQUALS).SetItems(items);
        }

        /**
        * Checks if a player's currently
        * selected hotbar slot corresponds
        * with a slot ID between 1 and 9.
        * 
        * Chest Parameters:
        * Number - Slot ID to check
        * 
        * Additional Info:
        * > 1 = Leftmost slot
        * > 9 = Rightmost slot
        */
        public static SubIf SlotEquals(Number slotIDToCheck,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(slotIDToCheck);
            return new SubIf(SubIfType.PLAYER_HOTBAR_SLOT_EQUALS).SetItems(items);
        }

        /**
        * Checks if a player has
        * a potion effect.
        * 
        * Chest Parameters:
        * Potion(s) - Potion(s) to check for
        * # 1 Tag
        * 
        * Additional Info:
        * > Amplifiers and durations do not
        * have to be the same as the initial
        * potion effect(s).
        */
        public static SubIf HasEffect(Potion[] potionsToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(potionsToCheckFor));
            return new SubIf(SubIfType.PLAYER_HAS_POTION_EFFECT).SetItems(items);
        }

        /**
        * Checks if a player is riding an
        * entity (including vehicles).
        * 
        * Chest Parameters:
        * Text(s)* - Entity type (e.g. \"pig\"),
        * name, or UUID to compare ridden
        * entity to
        * # 1 Tag
        * 
        * *Optional
        */
        public static SubIf IsRiding(Text[] entityType, CompareTextTo compareTextTo,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(entityType != null) items.addAll(toList(entityType));
            return new SubIf(SubIfType.PLAYER_IS_RIDING_ENTITY).SetItems(items).SetTags(new Tag("Compare Text To", compareTextTo.getJSONValue()));
        }

        /**
        * Checks if a player has a
        * certain inventory type
        * open.
        * 
        * Chest Parameters:
        * # 1 Tag
        * 
        * Additional Info:
        * > Does not work with special
        * screens such as the death screen,
        * chat box, etc. This includes
        * the player's own inventory.
        */
        public static SubIf InvOpen(InventoryType inventoryType,boolean inverseIf) {
            return new SubIf(SubIfType.PLAYER_INVENTORY_TYPE_OPEN).SetTags(new Tag("Inventory Type", inventoryType.getJSONValue()));
        }

        /**
        * Checks if a player has a certain
        * level of access on this plot, such
        * as builder or owner.
        * 
        * Chest Parameters:
        * # 1 Tag
        */
        public static SubIf HasPermission(Permission permission,boolean inverseIf) {
            return new SubIf(SubIfType.PLAYER_HAS_PREMISSION).SetTags(new Tag("Permission", permission.getJSONValue()));
        }     
    }

    public static class Entity {
        private static List<CodeValue> toList(CodeValue[] array) {
            List<CodeValue> list = Arrays.asList(array);
            list.remove(null);
            return list;
        }

        /**
         * Checks if an entity
         * is a certain type.
         * 
         * Chest Parameters:
         * Entity Type(s) - Spawn egg,
         * projectile, or
         * vehicle
         */
        public static SubIf IsType(IEntityType entityType,boolean inverseIf) {
            return new SubIf(SubIfType.ENTITY_IS_TYPE).SetItems(entityType);
        }

        /**
         * Checks if an entity's name is
         * equal to the text in the chest.
         * 
         * Chest Parameters:
         * Text(s) - Name to check for
         * 
         * Additional Info:
         * > 'Name Equals' also works with
         * entities with custom names
         * > Works with UUID's
         */
        public static SubIf NameEquals(Text[] nameToCheckFor,boolean inverseIf) {
            return new SubIf(SubIfType.ENTITY_NAME_EQUALS ).SetItems(nameToCheckFor);
        }

        /**
        * Checks if an entity is
        * supported by a block.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsGrounded(boolean inverseIf) {
            return new SubIf(SubIfType.ENTITY_IS_GROUNDED);
        }

        /**
        * Checks if an entity is standing
        * on a block of a certain type
        * or at a certain location.
        * 
        * Chest Parameters:
        * Block(s)* - Block to check for
        * OR
        * Location(s) - Location to check for
        * 
        * *Optional
        */
        public static SubIf StandingOn(Item[] blockToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(blockToCheckFor != null) items.addAll(toList(blockToCheckFor));
            return new SubIf(SubIfType.ENTITY_IS_STANDING_ON).SetItems(items);
        }

        /**
        * Checks if an entity is within a
        * certain range of a location.
        * (default: 5 blocks)
        * 
        * Chest Parameters:
        * Location(s) - Center location
        * Number* - Range
        * # 1 Tag
        * 
        * *Optional
        */
        public static SubIf IsNear(Location[] centerLocation, Number range, IgnoreY_Axis ignoreYAxis,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(centerLocation));
            if(range != null) items.add(range);
            return new SubIf(SubIfType.ENTITY_IS_NEAR).SetItems(items).SetTags(new Tag("Ignore Y-Axis", ignoreYAxis.getJSONValue()));
        }

        /**
        * Checks if an entity is riding an
        * entity (including vehicles).
        * 
        * Chest Parameters:
        * Text(s)* - Entity type (e.g. \"pig\"),
        * name, or UUID to compare ridden
        * entity to
        * # 1 Tag
        * 
        * *Optional
        */
        public static SubIf IsRiding(Text[] entityType, CompareTextTo compareTextTo,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(entityType != null) items.addAll(toList(entityType));
            return new SubIf(SubIfType.ENTITY_IS_RIDING).SetItems(items).SetTags(new Tag("Compare Text To", compareTextTo.getJSONValue()));
        }

        /**
        * Checks if an entity is a mob.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsMob(boolean inverseIf) {
            return new SubIf(SubIfType.ENTITY_IS_MOB);
        }

        /**
        * Checks if an entity is a projectile.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsProjectile(boolean inverseIf) {
            return new SubIf(SubIfType.ENTITY_IS_PROJECTILE);
        }

        /**
        * Checks if an entity is a vechile.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsVechile(boolean inverseIf) {
            return new SubIf(SubIfType.ENTITY_IS_VECHILE);
        }

        /**
        * Checks if an entity is an item.
        * 
        * Chest Parameters:
        * (None)
        */
        public static SubIf IsItem(boolean inverseIf) {
            return new SubIf(SubIfType.ENTITY_IS_ITEM);
        }

        /**
        * Checks if an entity still
        * exists in the world.
        * 
        * Chest Parameters:
        * (None)
        *
        * Additional Info:
        * > Entities which have been removed
        * still remain in selections.
        */
        public static SubIf Exists(boolean inverseIf) {
            return new SubIf(SubIfType.ENTITY_EXISTS);
        }

        /**
        * Checks if an entity a
        * given custom tag, and (if 
        * provided) whether the tag
        * matches the given value.
        * 
        * Chest Parameters:
        * Text - Tag name
        * Text* - Tag value
        *
        * *Optional
        */
        public static SubIf HasCustomTag(Text tagName, Text tagValue,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(tagName);
            if(tagValue != null) items.add(tagValue);
            return new SubIf(SubIfType.ENTITY_HAS_CUSTOM_TAG).SetItems(items);
        }

        /**
        * Checks if an entity a
        * given custom tag, and (if 
        * provided) whether the tag
        * matches the given value.
        * 
        * Chest Parameters:
        * Text - Tag name
        * Number* - Tag value
        *
        * *Optional
        */
        public static SubIf HasCustomTag(Text tagName, Number tagValue,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(tagName);
            if(tagValue != null) items.add(tagValue);
            return new SubIf(SubIfType.ENTITY_HAS_CUSTOM_TAG).SetItems(items);
        }
    }

    public static class Variable {
        private static List<CodeValue> toList(CodeValue[] array) {
            List<CodeValue> list = Arrays.asList(array);
            list.remove(null);
            return list;
        }

        /**
         * Checks if a variable is equal
         * to one of the given variables.
         * 
         * Chest Parameters:
         * Any Value - Variable to check
         * Any Value(s) - Variables to compare to
         */
        public static SubIf Equals(net.jfdf.jfdf.values.CodeValue variableToCheck, CodeValue[] variablesToCompareTo) {
            List<CodeValue> items = new ArrayList<>();
            items.add(variableToCheck);
            items.addAll(toList(variablesToCompareTo));
            return new SubIf(SubIfType.VARIABLE_EQUALS).SetItems(items);
        }

        /**
         * Checks if a variable does not
         * equal another variable.
         * 
         * Chest Parameters:
         * Any Value - Variable to check
         * Any Value(s) - Variables to compare to
         */
        public static SubIf NotEquals(net.jfdf.jfdf.values.CodeValue variableToCheck, CodeValue[] variablesToCompareTo) {
            List<CodeValue> items = new ArrayList<>();
            items.add(variableToCheck);
            items.addAll(toList(variablesToCompareTo));
            return new SubIf(SubIfType.VARIABLE_NOT_EQUALS).SetItems(items);
        }

        /**
         * Checks if a number variable is
         * greater than another number
         * 
         * Chest Parameters:
         * Number - Number to check
         * Number - Number to compare to
         */
        public static SubIf GreaterThan(INumber numberToCheck, INumber numberToCompareTo) {
            return new SubIf(SubIfType.VARIABLE_GREATER_THAN).SetItems(numberToCheck, numberToCompareTo);
        }

        /**
         * Checks if a number variable
         * is greater than or equal to
         * another number.
         * 
         * Chest Parameters:
         * Number - Number to check
         * Number - Number to compare to
         */
        public static SubIf GreaterThanOrEqualTo(INumber numberToCheck, INumber numberToCompareTo) {
            return new SubIf(SubIfType.VARIABLE_GREATER_THAN_OR_EQUAL_TO).SetItems(numberToCheck, numberToCompareTo);
        }

        /**
         * Checks if a number variable is
         * less than another number
         * 
         * Chest Parameters:
         * Number - Number to check
         * Number - Number to compare to
         */
        public static SubIf LessThan(INumber numberToCheck, INumber numberToCompareTo) {
            return new SubIf(SubIfType.VARIABLE_LESS_THAN).SetItems(numberToCheck, numberToCompareTo);
        }

        /**
         * Checks if a number variable
         * is less than or equal to
         * another number.
         * 
         * Chest Parameters:
         * Number - Number to check
         * Number - Number to compare to
         */
        public static SubIf LessThanOrEqualTo(INumber numberToCheck, INumber numberToCompareTo) {
            return new SubIf(SubIfType.VARIABLE_LESS_THAN_OR_EQUAL_TO).SetItems(numberToCheck, numberToCompareTo);
        }

        /**
         * Checks if a number variable is
         * in between 2 other numbers.
         * 
         * Chest Parameters:
         * Number - Check value
         * Number - Minimum value
         * Number - Maximum value
         */
        public static SubIf IsWithinRange(INumber checkValue, INumber minimumValue, INumber maximumValue,boolean inverseIf) {
            return new SubIf(SubIfType.VARIABLE_IN_RANGE).SetItems(checkValue, minimumValue, maximumValue);
        }

        /**
         * Checks if a location variable 
         * is within the region of 2 other
         * locations
         * 
         * Chest Parameters:
         * Location - Check value
         * Location - Minimum value
         * Location - Maximum value
         */
        public static SubIf IsWithinRange(ILocation checkValue, Location minimumValue, Location maximumValue,boolean inverseIf) {
            return new SubIf(SubIfType.VARIABLE_IN_RANGE).SetItems(checkValue, minimumValue, maximumValue);
        }

        /**
         * Checks if a number is within
         * a cretain range of another
         * number.
         * 
         * Chest Parameters:
         * Number - Check value
         * Number - Center value
         * Number - Range value
         */
        public static SubIf IsNear(INumber checkValue, INumber centerValue, INumber rangeValue,boolean inverseIf) {
            return new SubIf(SubIfType.VARIABLE_IS_NEAR).SetItems(checkValue, centerValue, rangeValue);
        }

        /**
         * Checks if a location is
         * near another location.
         * 
         * Chest Parameters:
         * Location - Check value
         * Location - Center value
         * Number - Range value
         */
        public static SubIf IsNear(ILocation checkValue, Location centerValue, INumber rangeValue,boolean inverseIf) {
            return new SubIf(SubIfType.VARIABLE_IS_NEAR).SetItems(checkValue, centerValue, rangeValue);
        }

        /**
         * Checks if a text value
         * matches other values.
         * 
         * Chest Parameters:
         * Text - Text or expression to match
         * Text(s) - Text to compare
         */
        public static SubIf TextMatches(IText textOrExpressionToMatch, IText[] textToCompare, RegularExpressions regularExpressions, IgnoreCase ignoreCase,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(textOrExpressionToMatch);
            items.addAll(toList(textToCompare));
            return new SubIf(SubIfType.VARIABLE_TEXT_MATCHES).SetItems(items).SetTags(new Tag("Regular Expressions", regularExpressions.getJSONValue()), new Tag("Ignore Case", ignoreCase.getJSONValue()));
        }

        /**
         * Checks if a text variable
         * contains another text item.
         * 
         * Chest Parameters:
         * Text - Text to check
         * Text(s) - Text to check for
         */
        public static SubIf TextContains(IText textToCheck, IText[] textToCheckFor, IgnoreCase ignoreCase,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(textToCheck);
            items.addAll(toList(textToCheckFor));
            return new SubIf(SubIfType.VARIABLE_TEXT_CONTAINS).SetItems(items).SetTags(new Tag("Ignore Case", ignoreCase.getJSONValue()));
        }

        /**
         * Checks if the first part of
         * a text variable matches a
         * certain text.
         * 
         * Chest Parameters:
         * Text - Text to check
         * Text(s) - Text to start for
         */
        public static SubIf TextStartsWith(IText textToCheck, IText[] textToCheckFor, IgnoreCase ignoreCase,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(textToCheck);
            items.addAll(toList(textToCheckFor));
            return new SubIf(SubIfType.VARIABLE_TEXT_STARTS_WITH).SetItems(items).SetTags(new Tag("Ignore Case", ignoreCase.getJSONValue()));
        }

        /**
         * Checks if the last part of
         * a text variable matches a
         * certain text.
         * 
         * Chest Parameters:
         * Text - Text to check
         * Text(s) - Text to end for
         */
        public static SubIf TextEndsWith(IText textToCheck, IText[] textToCheckFor, IgnoreCase ignoreCase,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(textToCheck);
            items.addAll(toList(textToCheckFor));
            return new SubIf(SubIfType.VARIABLE_TEXT_ENDS_WITH).SetItems(items).SetTags(new Tag("Ignore Case", ignoreCase.getJSONValue()));
        }

        /**
         * Checks if a variable exists.
         * 
         * Chest Parameters:
         * Variable - Variable to check
         */
        public static SubIf Exists(net.jfdf.jfdf.values.Variable variableToCheck) {
            return new SubIf(SubIfType.VARIABLE_EXISTS).SetItems(variableToCheck);
        }

        /**
         * Checks if a variable ia a
         * certain type of variable.
         * 
         * Chest Parameters:
         * Any Value - Variable to check
         */
        public static SubIf IsType(CodeValue variableToCheck, VariableType variableType,boolean inverseIf) {
            return new SubIf(SubIfType.VARIABLE_VARIABLE_IS_TYPE).SetItems(variableToCheck).SetTags(new Tag("Variable Type", variableType.getJSONValue()));
        }

        /**
         * Works the same as Variable Equals
         * but has a few extra options
         * for item comparison.
         * 
         * Chest Parameters:
         * Item - Item to check
         * Item(s)* - Item(s) to compare to
         */
        public static SubIf ItemEquals(IItem itemToCheck, IItem[] itemsToCompareTo, ComparisonMode comparisonMode,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(itemToCheck);
            items.addAll(toList(itemsToCompareTo));
            return new SubIf(SubIfType.VARIABLE_ITEM_EQUALS).SetItems(items).SetTags(new Tag("Comparison Mode", comparisonMode.getJSONValue()));
        }

        /**
         * Checks if an item has a
         * given custom tag, and (if
         * provided) whether the tag
         * matches the given value.
         * 
         * Chest Parameters:
         * Item - Item to check
         * Text - Tag name
         * Text* - Tag value
         * 
         * *Optional
         */
        public static SubIf ItemHasCustomTag(IItem itemToCheck, IText tagName, IText tagValue,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(itemToCheck);
            items.add(tagName);
            if(tagValue != null) items.add(tagValue);
            return new SubIf(SubIfType.VARIABLE_ITEM_HAS_TAG).SetItems(items);
        }

        /**
         * Checks if an item has a
         * given custom tag, and (if
         * provided) whether the tag
         * matches the given value.
         * 
         * Chest Parameters:
         * Item - Item to check
         * Text - Custom name
         * Number* - Tag value
         * 
         * *Optional
         */
        public static SubIf ItemHasCustomTag(IItem itemToCheck, IText tagName, INumber tagValue,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(itemToCheck);
            items.add(tagName);
            if(tagValue != null) items.add(tagValue);
            return new SubIf(SubIfType.VARIABLE_ITEM_HAS_TAG).SetItems(items);
        }

        /**
         * Checks if any of a list's contents
         * match the given value.
         * 
         * Chest Parameters:
         * List - List to check in
         * Any Value(s) - Variable to find
         */
        public static SubIf ListContainsValue(net.jfdf.jfdf.values.Variable listToCheckIn, CodeValue[] variableToFind) {
            List<CodeValue> items = new ArrayList<>();
            items.add(listToCheckIn);
            items.addAll(toList(variableToFind));
            return new SubIf(SubIfType.VARIABLE_LIST_CONTAINS_VALUE).SetItems(items);
        }

        /**
         * Checks if a list's value at an
         * index is equal to a value.
         * 
         * Chest Parameters:
         * List - List to check in
         * Number - Index to check at
         * Any Value(s) - Variable to compare 
         * to
         */
        public static SubIf ListValueEquals(net.jfdf.jfdf.values.Variable listToCheckIn, INumber indexToCheckAt, CodeValue[] variableToCompareTo) {
            List<CodeValue> items = new ArrayList<>();
            items.add(listToCheckIn);
            items.add(indexToCheckAt);
            items.addAll(toList(variableToCompareTo));
            return new SubIf(SubIfType.VARIABLE_LIST_VALUE_EQUALS).SetItems(items);
        }
    }

    public static class Game {
        private static List<CodeValue> toList(CodeValue[] array) {
            List<CodeValue> list = Arrays.asList(array);
            list.remove(null);
            return list;
        }

        /**
         * Checks if a block at a cretain
         * location is a certain block.
         * 
         * Chest Parameters:
         * Location - Check location
         * Blocks(s)* - Blocks(s) to check for
         * Block Tag(s)* - Block data
         */
        public static SubIf BlockEquals(ILocation checkLocation, Item[] blocksToCheckFor, Text[] blockData,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(checkLocation);
            if(blocksToCheckFor != null) items.addAll(toList(blocksToCheckFor));
            if(blockData != null) items.addAll(toList(blockData));
            return new SubIf(SubIfType.GAME_BLOCK_EQUALS).SetItems(items);
        }

        /**
         * Checks if a block at a cretain
         * location is powered by
         * redstone.
         * 
         * Chest Parameters:
         * Location(s) - Check location(s)
         */
        public static SubIf BlockIsPowered(ILocation[] checkLocations, RedstonePowerMode redstonePowerMode,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(checkLocations));
            return new SubIf(SubIfType.GAME_BLOCK_POWERED).SetItems(items).SetTags(new Tag("Redstone Power Mode", redstonePowerMode.getJSONValue()));
        }

        /**
         * Checks if a container
         * an item in its inventory
         * 
         * Chest Parameters:
         * Location - Container location
         * Item(s) - Item(s) to check for
         */
        public static SubIf ContainerHasItem(ILocation containerLocation, IItem[] itemsToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(containerLocation);
            items.addAll(toList(itemsToCheckFor));
            return new SubIf(SubIfType.GAME_CONTAINER_HAS_ITEM).SetItems(items);
        }

        /**
         * Checks if the given container
         * has enough room for one or
         * more items to be given.
         * 
         * Chest Parameters:
         * Location - Container location
         * Item(s)* - Item(s) to check
         * 
         * *Optional
         * 
         * Additonal Info:
         * > Not specifying any items will
         * check if the container has an
         * empty slot in its inventory.
         */
        public static SubIf ContainerHasRoomForItem(ILocation containerLocation, IItem[] itemsToCheck,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(containerLocation);
            if(itemsToCheck != null) items.addAll(toList(itemsToCheck));
            return new SubIf(SubIfType.GAME_CONTAINER_HAS_ALL_ITEMS).SetItems(items);
        }

        /**
         * Checks if the text on a sign
         * at a cretain location contains
         * the text in the chest.
         * 
         * Chest Parameters:
         * Location - Sign location
         * Text(s) - Text to check for
         */
        public static SubIf SignContainsText(ILocation signLocation, IText[] textToCheckFor, SignLine signLine, CheckMode checkMode,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(signLocation);
            items.addAll(toList(textToCheckFor));
            return new SubIf(SubIfType.GAME_SIGN_CONTAINS_TEXT).SetItems(items).SetTags(new Tag("Sign Line", signLine.getJSONValue()), new Tag("Check Mode", checkMode.getJSONValue()));
        }

        /**
         * Checks if there is currently
         * a player in the game with a
         * certain name or UUID.
         * 
         * Chest Parameters:
         * Text(s) - Name or UUID
         */
        public static SubIf GameHasPlayer(IText nameOrUUID,boolean inverseIf) {
            return new SubIf(SubIfType.GAME_HAS_PLAYER).SetItems(nameOrUUID);
        }

        /**
         * Checks if the block in a block-
         * related event is a certain
         * block.
         * 
         * Chest Parameters:
         * Block(s) - Block(s) to check for
         * 
         * Works With:
         * > Block Events
         * > Click Events
         * 
         * Emperor Exclusive
         */
        public static SubIf EventBlockEquals(IItem[] blocksToCheckFor,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(blocksToCheckFor));
            return new SubIf(SubIfType.GAME_EVENT_BLOCK_EQUALS).SetItems(items);
        }

        /**
         * Checks if the item in a item-
         * related event is a certain
         * item.
         * 
         * Chest Parameters:
         * Item(s) - Item(s) to check for
         * 
         * Works With:
         * > Click Item Events
         * > Pickup Item Event
         * > Drop Item Event
         * > Consume Item Event
         */
        public static SubIf EventItemEquals(IItem[] itemsToCheckFor, ComparisonMode eventItemComparisonMode,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(itemsToCheckFor));
            return new SubIf(SubIfType.GAME_EVENT_ITEM_EQUALS).SetItems(items).SetTags(new Tag("Comparison Mode", eventItemComparisonMode.getJSONValue()));
        }

        /**
         * Checks if the command entered in
         * this Command Event is equal
         * to a certain text.
         * 
         * Chest Parameters:
         * Text(s) - Text(s) to check for
         * 
         * Works With:
         * > Command Event
         * 
         * Emperor Exclusive
         */
        public static SubIf CommandEquals(IText[] textsToCheckFor, CheckMode checkMode, IgnoreCase ignoreCase, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(textsToCheckFor));
            return new SubIf(SubIfType.GAME_COMMAND_EQUALS).SetItems(items).SetTags(new Tag("Check Mode", checkMode.getJSONValue()), new Tag("Ignore Case", ignoreCase.getJSONValue()));
        }

        /**
         * Checks if the part of the command 
         * entered in this Command Event 
         * is equal to a certain text.
         * 
         * Chest Parameters:
         * Text(s) - Text(s) to check for
         * Number - Argument number
         * 
         * Works With:
         * > Command Event
         * 
         * Emperor Exclusive
         */
        public static SubIf CommandArgumentEquals(IText[] textsToCheckFor, INumber argumentNumber, IgnoreCase ignoreCase,boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.addAll(toList(textsToCheckFor));
            items.add(argumentNumber);
            return new SubIf(SubIfType.GAME_COMMAND_ARGUMENT_EQUALS).SetItems(items).SetTags(new Tag("Ignore Case", ignoreCase.getJSONValue()));
        }

        /**
         * Checks if the current
         * event is cancelled.
         * 
         * Chest Parameters:
         * (None)
         */
        public static SubIf EventIsCancelled(boolean inverseIf) {
            return new SubIf(SubIfType.GAME_IS_EVENT_CANCELLED);
        }
    }

    public enum SubIfType {
        ENTITY_IS_TYPE("if_entity","IsType"),
        ENTITY_NAME_EQUALS("if_entity","ENameEquals", "NameEquals"),
        ENTITY_IS_STANDING_ON("if_entity","EStandingOn", "StandingOn"),
        ENTITY_IS_GROUNDED("if_entity","IsGrounded"),
        ENTITY_IS_NEAR("if_entity","EIsNear", "IsNear"),
        ENTITY_IS_RIDING("if_entity","IsRiding"),
        ENTITY_IS_MOB("if_entity","IsMob"),
        ENTITY_IS_PROJECTILE("if_entity","IsProj"),
        ENTITY_IS_VECHILE("if_entity","IsVechile"),
        ENTITY_IS_ITEM("if_entity","IsItem"),
        ENTITY_EXISTS("if_entity","Exists"),
        ENTITY_HAS_CUSTOM_TAG("if_entity","HasCustomTag"),
        GAME_BLOCK_EQUALS("if_game","GBlockEquals", "BlockEquals"),
        GAME_BLOCK_POWERED("if_game","BlockPowered"),
        GAME_CONTAINER_HAS_ITEM("if_game","ContainterHas"),
        GAME_CONTAINER_HAS_ALL_ITEMS("if_game","ContainterHasAll"),
        GAME_CONTAINER_HAS_ROOM_FOR_ITEM("if_game","HasRoomForItem"),
        GAME_SIGN_CONTAINS_TEXT("if_game","SignHasTxt"),
        GAME_HAS_PLAYER("if_game","HasPlayer"),
        GAME_EVENT_BLOCK_EQUALS("if_game","EventBlockEquals"),
        GAME_EVENT_ITEM_EQUALS("if_game","EventItemEquals"),
        GAME_COMMAND_EQUALS("if_game","CommandEquals"),
        GAME_COMMAND_ARGUMENT_EQUALS("if_game","CmdArgEquals"),
        GAME_IS_EVENT_CANCELLED("if_game","EventCancelled"),
        PLAYER_IS_SNEAKING("if_player","IsSneaking"),
        PLAYER_IS_SPRINTING("if_player","IsSprinting"),
        PLAYER_IS_GLIDING("if_player","IsGliding"),
        PLAYER_IS_FLYING("if_player","IsFlying"),
        PLAYER_IS_GROUNDED("if_player","IsGrounded"),
        PLAYER_IS_SWIMMING("if_player","IsSwimming"),
        PLAYER_IS_BLOCKING("if_player","IsBlocking"),
        PLAYER_IS_LOOKING_AT("if_player","IsLookingAt"),
        PLAYER_STANDING_ON("if_player","PStandingOn", "StandingOn"),
        PLAYER_IS_NEAR("if_player","PIsNear", "IsNear"),
        PLAYER_IS_IN_WORLD_BORDER("if_player","InWorldBorder"),
        PLAYER_IS_HOLDING("if_player","IsHolding"),
        PLAYER_HAS_ITEM("if_player","HasItem"),
        PLAYER_IS_WEARING("if_player","PIsWearing","IsWearing"),
        PLAYER_IS_USING_ITEM("if_player","IsUsingItem"),
        PLAYER_IS_ITEM_HAS_NO_COOLDOWN("if_player","NoItemCooldown"),
        PLAYER_HAS_ITEM_IN_SLOT("if_player","HasSlotItem"),
        PLAYER_INVENTORY_MENU_SLOT_EQUALS("if_player","MenuSlotEquals"),
        PLAYER_CURSOR_ITEM_EQUALS("if_player","CursorItem"),
        PLAYER_HAS_ROOM_FOR_ITEM("if_player","HasRoomForItem"),
        PLAYER_NAME_EQUALS("if_player","PNameEquals", "NameEquals"),
        PLAYER_HOTBAR_SLOT_EQUALS("if_player","SlotEquals"),
        PLAYER_HAS_POTION_EFFECT("if_player","HasEffect"),
        PLAYER_IS_RIDING_ENTITY("if_player","IsRiding"),
        PLAYER_INVENTORY_TYPE_OPEN("if_player","InvOpen"),
        PLAYER_HAS_PREMISSION("if_player","HasPremission"),
        VARIABLE_EQUALS("if_var","="),
        VARIABLE_NOT_EQUALS("if_var","!="),
        VARIABLE_GREATER_THAN("if_var",">"),
        VARIABLE_GREATER_THAN_OR_EQUAL_TO("if_var",">="),
        VARIABLE_LESS_THAN("if_var","<"),
        VARIABLE_LESS_THAN_OR_EQUAL_TO("if_var","<="),
        VARIABLE_IN_RANGE("if_var","InRange"),
        VARIABLE_IS_NEAR("if_var","VIsNear", "IsNear"),
        VARIABLE_TEXT_MATCHES("if_var","TextMatches"),
        VARIABLE_TEXT_CONTAINS("if_var","Contains"),
        VARIABLE_TEXT_STARTS_WITH("if_var", "StartsWith"),
        VARIABLE_TEXT_ENDS_WITH("if_var", "EndsWith"),
        VARIABLE_EXISTS("if_var", "VarExists"),
        VARIABLE_VARIABLE_IS_TYPE("if_var","VarIsType"),
        VARIABLE_ITEM_EQUALS("if_var","VItemEquals","ItemEquals"),
        VARIABLE_ITEM_HAS_TAG("if_var", "ItemHasTag"),
        VARIABLE_LIST_CONTAINS_VALUE("if_var","ListContains"),
        VARIABLE_LIST_VALUE_EQUALS("if_var","ListValueEq");

        private final static Map<Integer, SubIfType> values = new HashMap<>();

        private int value;
        private final String ifBlock;
        private final String normalJsonValue;
        private final String subJsonValue;

        SubIfType(final String ifBlock, final String jsonValue) {
            this.ifBlock = ifBlock;
            this.subJsonValue = jsonValue;
            this.normalJsonValue = jsonValue;
        }

        SubIfType(final String ifBlock, final String subJSONValue, final String normalJSONValue) {
            this.ifBlock = ifBlock;
            this.subJsonValue = subJSONValue;
            this.normalJsonValue = normalJSONValue;
        }

        static {
            for (SubIfType subIf : SubIfType.values()) {
                subIf.value = values.size();
                values.put(subIf.getValue(), subIf);
            }
        }

        public static SubIfType valueOf(int subIf) {
            return values.get(subIf);
        }

        public int getValue() {
            return value;
        }

        public String getIfBlock() {
            return ifBlock;
        }

        public String getNormalJSONValue() {
            return normalJsonValue;
        }

        public String getSubJSONValue() {
            return subJsonValue;
        }
    }
}