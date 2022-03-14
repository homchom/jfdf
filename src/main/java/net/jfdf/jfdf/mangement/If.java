package net.jfdf.jfdf.mangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.jfdf.jfdf.blocks.*;
import net.jfdf.jfdf.blocks.IfVariableBlock;
import net.jfdf.jfdf.blocks.EntityActionBlock.EntitySelection;
import net.jfdf.jfdf.blocks.IfPlayerBlock.Type;
import net.jfdf.jfdf.blocks.PlayerActionBlock.PlayerSelection;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Tags.*;

public class If {
    public static class Player {
        private final PlayerSelection selection;
        
        public Player(PlayerSelection selection) {
            this.selection = selection;
        }
        
        public static Player getCurrentSelection() {return new Player(PlayerSelection.CURRENT_SELECTION);}
        
        public static Player getDefaultPlayer() {return new Player(PlayerSelection.DEFAULT_PLAYER);}
        
        public static Player getKiller() {return new Player(PlayerSelection.KILLER);}
        
        public static Player getDamager() {return new Player(PlayerSelection.DAMAGER);}
        
        public static Player getShooter() {return new Player(PlayerSelection.SHOOTER);}
        
        public static Player getVictim() {return new Player(PlayerSelection.VICTIM);}
        
        public static Player getAllPlayers() {return new Player(PlayerSelection.ALL_PLAYERS);}

        private List<CodeValue> toList(CodeValue[] array) {
            List<CodeValue> list = new ArrayList<>(Arrays.asList(array));
            list.remove(null);
            return list;
        }

        /**
        * Checks if a player is sneaking.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsSneaking(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_SNEAKING, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if a player is sprinting
        * or using the sprint key to swim.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsSprinting(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_SPRINTING, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if a player is
        * gliding with elytra.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsGliding(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_GLIDING, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if a player is flying.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsFlying(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_FLYING, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if a player is
        * supported by a block.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsGrounded(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_GROUNDED, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void IsSwimming(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_SWIMMING, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if a player is blocking
        * with a shield.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsBlocking(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_BLOCKING, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void IsLookingAt(IItem[] blocksToCheckFor, FluidMode fluidMode, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(blocksToCheckFor != null) items.addAll(toList(blocksToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_LOOKING_AT, selection, inverseIf).SetItems(items).SetTags(new Tag("Fluid Mode", fluidMode.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void StandingOn(IItem[] blockToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(blockToCheckFor != null) items.addAll(toList(blockToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.STANDING_ON, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void IsNear(ILocation[] centerLocation, INumber range, IgnoreY_Axis ignoreYAxis, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(centerLocation));
            if(range != null) items.add(range);
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_NEAR, selection, inverseIf).SetItems(items).SetTags(new Tag("Ignore Y-Axis", ignoreYAxis.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void InWorldBorder(ILocation locationToCheck, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(locationToCheck != null) items.add(locationToCheck);
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IN_WORLD_BORDER, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void IsHolding(IItem[] itemsToCheckFor, HandSlot handSlot, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(itemsToCheckFor != null) items.addAll(toList(itemsToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_HOLDING, selection, inverseIf).SetItems(items).SetTags(new Tag("Hand Slot", handSlot.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if a player has an item
        * in their inventory.
        * 
        * Chest Parameters:
        * Item(s) - Item(s) to check for
        * # 1 Tag
        */
        public void HasItem(IItem[] itemsToCheckFor, Tags.CheckMode checkMode, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(itemsToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.HAS_ITEM, selection, inverseIf).SetItems(items).SetTags(new Tag("Check Mode", checkMode.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if a player is wearing
        * an item.
        * 
        * Chest Parameters:
        * Item(s) - Item(s) to check for
        * # 1 Tag
        */
        public void IsWearing(IItem[] itemsToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(itemsToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_WEARING, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void IsUsingItem(IItem[] itemsToCheck, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(itemsToCheck != null) items.addAll(toList(itemsToCheck));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_USING_ITEM, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void NoItemCooldown(IItem[] itemTypesToCheck, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(itemTypesToCheck));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.NO_ITEM_COOLDOWN, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void HasSlotItem(INumber[] slotsToCheck, IItem[] itemsToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(slotsToCheck));
            if(itemsToCheckFor != null) items.addAll(toList(itemsToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.HAS_SLOT_ITEM, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void MenuSlotEquals(INumber[] slotIDsToCheck, IItem[] itemsToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(slotIDsToCheck));
            if(itemsToCheckFor != null) items.addAll(toList(itemsToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.MENU_SLOT_EQUALS, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void CursorItem(IItem[] itemssToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(itemssToCheckFor != null) items.addAll(toList(itemssToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.CURSOR_ITEM, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void HasRoomForItem(IItem itemsToCheck, CheckedSlots checkedSlots, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(itemsToCheck != null) items.add(itemsToCheck);
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.HAS_ROOM_FOR_ITEM, selection, inverseIf).SetItems(items).SetTags(new Tag("Checked Slots", checkedSlots.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void NameEquals(IText[] namesToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(namesToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.NAME_EQUALS, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void SlotEquals(INumber slotIDToCheck, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(slotIDToCheck);
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.SLOT_EQUALS, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void HasEffect(IPotion[] potionsToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(potionsToCheckFor));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.HAS_EFFECT, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void IsRiding(IText[] entityType, CompareTextTo compareTextTo, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(entityType != null) items.addAll(toList(entityType));
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.IS_RIDING, selection, inverseIf).SetItems(items).SetTags(new Tag("Compare Text To", compareTextTo.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void InvOpen(InventoryType inventoryType, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.INV_OPEN, selection, inverseIf).SetTags(new Tag("Inventory Type", inventoryType.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if a player has a certain
        * level of access on this plot, such
        * as builder or owner.
        * 
        * Chest Parameters:
        * # 1 Tag
        */
        public void HasPermission(Permission permission, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfPlayerBlock(Type.HAS_PERMISSION , selection, inverseIf).SetTags(new Tag("Permission", permission.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }       
    }

    public static class Entity {
        private final EntitySelection selection;
        
        public Entity(EntitySelection selection) {
            this.selection = selection;
        }
        
        public static Entity getCurrentSelection() {return new Entity(EntitySelection.CURRENT_SELECTION);}
        
        public static Entity getDefaultEntity() {return new Entity(EntitySelection.DEFAULT_ENTITY);}
        
        public static Entity getKiller() {return new Entity(EntitySelection.KILLER);}
        
        public static Entity getDamager() {return new Entity(EntitySelection.DAMAGER);}
        
        public static Entity getShooter() {return new Entity(EntitySelection.SHOOTER);}
        
        public static Entity getVictim() {return new Entity(EntitySelection.VICTIM);}
        
        public static Entity getProjectile() {return new Entity(EntitySelection.PROJECTILE);}
        
        public static Entity getAllEntities() {return new Entity(EntitySelection.ALL_ENTITIES);}
        
        public static Entity getAllMobs() {return new Entity(EntitySelection.ALL_MOBS);}

        public static Entity getLastEntitySpawned() {return new Entity(EntitySelection.LAST_ENTITY_SPAWNED);}

        public static Entity getLastMobSpawned() {return new Entity(EntitySelection.LAST_MOB_SPAWNED);}

        private List<CodeValue> toList(CodeValue[] array) {
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
        public void IsType(IEntityType entityType, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.IS_TYPE , selection, inverseIf).SetItems(entityType));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void NameEquals(IText[] nameToCheckFor, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.NAME_EQUALS , selection, inverseIf).SetItems(nameToCheckFor));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if an entity is
        * supported by a block.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsGrounded(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.IS_GROUNDED, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void StandingOn(IItem[] blockToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(blockToCheckFor != null) items.addAll(toList(blockToCheckFor));
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.IS_STANDING_ON, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void IsNear(ILocation[] centerLocation, INumber range, IgnoreY_Axis ignoreYAxis, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(centerLocation));
            if(range != null) items.add(range);
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.IS_NEAR, selection, inverseIf).SetItems(items).SetTags(new Tag("Ignore Y-Axis", ignoreYAxis.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void IsRiding(IText[] entityType, CompareTextTo compareTextTo, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            if(entityType != null) items.addAll(toList(entityType));
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.IS_RIDING, selection, inverseIf).SetItems(items).SetTags(new Tag("Compare Text To", compareTextTo.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if an entity is a mob.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsMob(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.IS_MOB, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if an entity is a projectile.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsProjectile(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.IS_PROJECTILE, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if an entity is a vechile.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsVechile(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.IS_VECHILE, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
        * Checks if an entity is an item.
        * 
        * Chest Parameters:
        * (None)
        */
        public void IsItem(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.IS_ITEM, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void Exists(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.EXISTS, selection, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void HasCustomTag(IText tagName, IText tagValue, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(tagName);
            if(tagValue != null) items.add(tagValue);
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.HAS_CUSTOM_TAG, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public void HasCustomTag(IText tagName, INumber tagValue, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(tagName);
            if(tagValue != null) items.add(tagValue);
            CodeManager.instance.addCodeBlock(new IfEntityBlock(IfEntityBlock.Type.HAS_CUSTOM_TAG, selection, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void Equals(net.jfdf.jfdf.values.CodeValue variableToCheck, CodeValue[] variablesToCompareTo, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(variableToCheck);
            items.addAll(toList(variablesToCompareTo));
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.EQUALS, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if a variable does not
         * equal another variable.
         * 
         * Chest Parameters:
         * Any Value - Variable to check
         * Any Value(s) - Variables to compare to
         */
        public static void NotEquals(CodeValue variableToCheck, CodeValue[] variablesToCompareTo, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(variableToCheck);
            items.addAll(toList(variablesToCompareTo));
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.NOT_EQUALS, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if a number variable is
         * greater than another number
         * 
         * Chest Parameters:
         * Number - Number to check
         * Number - Number to compare to
         */
        public static void GreaterThan(INumber numberToCheck, INumber numberToCompareTo, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.GREATER_THAN, inverseIf).SetItems(numberToCheck, numberToCompareTo));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void GreaterThanOrEqualTo(INumber numberToCheck, INumber numberToCompareTo, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.GREATER_THAN_OR_EQUAL_TO, inverseIf).SetItems(numberToCheck, numberToCompareTo));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if a number variable is
         * less than another number
         * 
         * Chest Parameters:
         * Number - Number to check
         * Number - Number to compare to
         */
        public static void LessThan(INumber numberToCheck, INumber numberToCompareTo, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.LESS_THAN, inverseIf).SetItems(numberToCheck, numberToCompareTo));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void LessThanOrEqualTo(INumber numberToCheck, INumber numberToCompareTo, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.LESS_THAN_OR_EQUAL_TO, inverseIf).SetItems(numberToCheck, numberToCompareTo));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void IsWithinRange(INumber checkValue, INumber minimumValue, INumber maximumValue, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.IN_RANGE, inverseIf).SetItems(checkValue, minimumValue, maximumValue));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void IsWithinRange(ILocation checkValue, Location minimumValue, Location maximumValue, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.IN_RANGE, inverseIf).SetItems(checkValue, minimumValue, maximumValue));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void IsNear(INumber checkValue, INumber centerValue, INumber rangeValue, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.IS_NEAR, inverseIf).SetItems(checkValue, centerValue, rangeValue));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void LocIsNear(ILocation locationToCheck, ILocation[] locationsToCompareTo, INumber rangeValue, Tags.Shape shape, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();

            items.add(locationToCheck);
            items.addAll(Arrays.asList(locationsToCompareTo));
            items.add(rangeValue);

            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.LOC_IS_NEAR, inverseIf).SetItems(items).SetTags(new Tag("Shape", shape.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if a text value
         * matches other values.
         * 
         * Chest Parameters:
         * Text - Text or expression to match
         * Text(s) - Text to compare
         */
        public static void TextMatches(IText textOrExpressionToMatch, IText[] textToCompare, RegularExpressions regularExpressions, IgnoreCase ignoreCase, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(textOrExpressionToMatch);
            items.addAll(toList(textToCompare));
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.TEXT_MATCHES, inverseIf).SetItems(items).SetTags(new Tag("Regular Expressions", regularExpressions.getJSONValue()), new Tag("Ignore Case", ignoreCase.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if a text variable
         * contains another text item.
         * 
         * Chest Parameters:
         * Text - Text to check
         * Text(s) - Text to check for
         */
        public static void TextContains(IText textToCheck, IText[] textToCheckFor, IgnoreCase ignoreCase, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(textToCheck);
            items.addAll(toList(textToCheckFor));
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.TEXT_CONTAINS, inverseIf).SetItems(items).SetTags(new Tag("Ignore Case", ignoreCase.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void TextStartsWith(IText textToCheck, IText[] textToCheckFor, IgnoreCase ignoreCase, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(textToCheck);
            items.addAll(toList(textToCheckFor));
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.TEXT_STARTS_WITH, inverseIf).SetItems(items).SetTags(new Tag("Ignore Case", ignoreCase.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void TextEndsWith(IText textToCheck, IText[] textToCheckFor, IgnoreCase ignoreCase, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(textToCheck);
            items.addAll(toList(textToCheckFor));
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.TEXT_ENDS_WITH, inverseIf).SetItems(items).SetTags(new Tag("Ignore Case", ignoreCase.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if a variable exists.
         * 
         * Chest Parameters:
         * Variable - Variable to check
         */
        public static void Exists(net.jfdf.jfdf.values.Variable variableToCheck, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.EXISTS, inverseIf).SetItems(variableToCheck));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if a variable ia a
         * certain type of variable.
         * 
         * Chest Parameters:
         * Any Value - Variable to check
         */
        public static void IsType(CodeValue variableToCheck, VariableType variableType, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.VARIABLE_IS_TYPE, inverseIf).SetItems(variableToCheck).SetTags(new Tag("Variable Type", variableType.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void ItemEquals(IItem itemToCheck, IItem[] itemsToCompareTo, ComparisonMode comparisonMode, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(itemToCheck);
            items.addAll(toList(itemsToCompareTo));
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.ITEM_EQUALS, inverseIf).SetItems(items).SetTags(new Tag("Comparison Mode", comparisonMode.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void ItemHasCustomTag(IItem itemToCheck, IText tagName, IText tagValue, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(itemToCheck);
            items.add(tagName);
            if(tagValue != null) items.add(tagValue);
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.ITEM_HAS_TAG, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void ItemHasCustomTag(IItem itemToCheck, IText tagName, INumber tagValue, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(itemToCheck);
            items.add(tagName);
            if(tagValue != null) items.add(tagValue);
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.ITEM_HAS_TAG, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if any of a list's contents
         * match the given value.
         * 
         * Chest Parameters:
         * List - List to check in
         * Any Value(s) - Variable to find
         */
        public static void ListContainsValue(net.jfdf.jfdf.values.Variable listToCheckIn, CodeValue[] variableToFind, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(listToCheckIn);
            items.addAll(toList(variableToFind));
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.LIST_CONTAINS_VALUE, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void ListValueEquals(net.jfdf.jfdf.values.Variable listToCheckIn, INumber indexToCheckAt, CodeValue[] variableToCompareTo, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(listToCheckIn);
            items.add(indexToCheckAt);
            items.addAll(toList(variableToCompareTo));
            CodeManager.instance.addCodeBlock(new IfVariableBlock(IfVariableBlock.Type.LIST_VALUE_EQUALS, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void BlockEquals(ILocation checkLocation, Item[] blocksToCheckFor, Text[] blockData, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(checkLocation);
            if(blocksToCheckFor != null) items.addAll(toList(blocksToCheckFor));
            if(blockData != null) items.addAll(toList(blockData));
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.BLOCK_EQUALS, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if a block at a cretain
         * location is powered by
         * redstone.
         * 
         * Chest Parameters:
         * Location(s) - Check location(s)
         */
        public static void BlockIsPowered(ILocation[] checkLocations, RedstonePowerMode redstonePowerMode, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(checkLocations));
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.BLOCK_POWERED, inverseIf).SetItems(items).SetTags(new Tag("Redstone Power Mode", redstonePowerMode.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if a container
         * an item in its inventory
         * 
         * Chest Parameters:
         * Location - Container location
         * Item(s) - Item(s) to check for
         */
        public static void ContainerHasItem(ILocation containerLocation, IItem[] itemsToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(containerLocation);
            items.addAll(toList(itemsToCheckFor));
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.CONTAINER_HAS_ITEM, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void ContainerHasRoomForItem(ILocation containerLocation, IItem[] itemsToCheck, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(containerLocation);
            if(itemsToCheck != null) items.addAll(toList(itemsToCheck));
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.CONTAINER_HAS_ALL_ITEMS, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void SignContainsText(ILocation signLocation, IText[] textToCheckFor, SignLine signLine, CheckMode checkMode, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>();
            items.add(signLocation);
            items.addAll(toList(textToCheckFor));
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.SIGN_CONTAINS_TEXT, inverseIf).SetItems(items).SetTags(new Tag("Sign Line", signLine.getJSONValue()), new Tag("Check Mode", checkMode.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if there is currently
         * a player in the game with a
         * certain name or UUID.
         * 
         * Chest Parameters:
         * Text(s) - Name or UUID
         */
        public static void GameHasPlayer(IText nameOrUUID, boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.HAS_PLAYER, inverseIf).SetItems(nameOrUUID));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void EventBlockEquals(IItem[] blocksToCheckFor, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(blocksToCheckFor));
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.EVENT_BLOCK_EQUALS, inverseIf).SetItems(items));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void EventItemEquals(IItem[] itemsToCheckFor, Tags.ComparisonMode eventItemComparisonMode, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(itemsToCheckFor));
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.EVENT_ITEM_EQUALS, inverseIf).SetItems(items).SetTags(new Tag("Comparison Mode", eventItemComparisonMode.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void CommandEquals(IText[] textsToCheckFor, CheckMode checkMode, IgnoreCase ignoreCase, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(textsToCheckFor));
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.COMMAND_EQUALS, inverseIf).SetItems(items).SetTags(new Tag("Check Mode", checkMode.getJSONValue()), new Tag("Ignore Case", ignoreCase.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
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
        public static void CommandArgumentEquals(IText[] textsToCheckFor, INumber argumentNumber, IgnoreCase ignoreCase, boolean inverseIf) {
            List<CodeValue> items = new ArrayList<>(toList(textsToCheckFor));
            items.add(argumentNumber);
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.COMMAND_ARGUMENT_EQUALS, inverseIf).SetItems(items).SetTags(new Tag("Ignore Case", ignoreCase.getJSONValue())));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }

        /**
         * Checks if the current
         * event is cancelled.
         * 
         * Chest Parameters:
         * (None)
         */
        public static void EventIsCancelled(boolean inverseIf) {
            CodeManager.instance.addCodeBlock(new IfGameBlock(IfGameBlock.Type.IS_EVENT_CANCELLED, inverseIf));
            CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
        }
    }

    public static void Else() {
        CodeManager.instance.addCodeBlock(new BracketBlock(true, false));
        CodeManager.instance.addCodeBlock(new ElseBlock());
        CodeManager.instance.addCodeBlock(new BracketBlock(false, false));
    }

    public static void End() {
        CodeManager.instance.addCodeBlock(new BracketBlock(true, false));
    }
}