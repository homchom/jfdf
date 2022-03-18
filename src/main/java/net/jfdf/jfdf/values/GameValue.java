package net.jfdf.jfdf.values;

import java.util.HashMap;
import java.util.Map;

import net.jfdf.jfdf.blocks.EntityActionBlock.EntitySelection;

public class GameValue implements CodeValue, IText, INumber, ILocation, IItem {
    
    private final Value value;
    private EntitySelection target = EntitySelection.DEFAULT_ENTITY;

    public GameValue(Value value) {
        this.value = value;
    }

    public GameValue(Value value, EntitySelection target) {
        this.value = value;
        this.target = target;
    }

    public String asJSON() {
        return "{\"id\":\"g_val\",\"data\":{\"type\":\"" + value.getJSONValue() + "\",\"target\":\"" + target.getJSONValue() + "\"}}";
    }

    public enum Value {
        TOTAL_PLAYER_COUNT("Player Count"),
        CPU_USAGE_PERCENT("CPU Usage"),
        SERVER_TPS("Server TPS"),
        SELECTION_SIZE("Selection Size"),
        SELECTION_TARGET_NAMES("Selection Target Names"),
        CURRENT_HEALTH("Current Health"),
        MAX_HEALTH("Maximum Health"),
        CURRENT_FOOD_LEVEL("Current Food Level"),
        CURRENT_SATURATION_LEVEL("Current Saturation Level"),
        CURRENT_XP_LEVEL("Current XP Level"),
        CURRENT_XP_PROGRESS("Current XP Level Progress"),
        CURRENT_ARMOR_POINTS("Current Armor Points"),
        CURRENT_FIRE_TICKS("Current Fire Ticks"),
        CURRENT_AIR_REMAINING("Current Air Remaining"),
        CURRENT_FALL_DISTANCE("Current Fall Distance"),
        CURRENT_HELD_SLOT("Current Held Slot"),
        INVENTORY_NAME("Inventory Name"),
        ACTIVE_POTION_EFFECTS("Active Potion Effects"),
        ENTITY_TYPE("Entity Type"),
        CURRENT_LOCATION("Location"),
        TARGET_BLOCK_LOCATION("Target Block Location"),
        TARGET_FLUID_LOCATION("Target Fluid Location"),
        TARGET_BLOCK_FACE("Target Block Face"),
        CURRENT_EYE_LOCATION("Current Eye Location"),
        X_Coordinate("X-Coordinate"),
        Y_Coordinate("Y-Coordinate"),
        Z_Coordinate("Z-Coordinate"),
        PITCH("Pitch"),
        YAW("Yaw"),
        FACING_DIRECTION("Facing Direction"),
        SPAWN_LOCATION("Spawn Location"),
        MAIN_HAND_ITEM("Main Hand Item"),
        OFF_HAND_ITEM("Off Hand Item"),
        ARMOR_ITEMS("Armor Items"),
        HOTBAR_ITEMS("Hotbar Items"),
        INVENTORY_ITEMS("Inventory Items"),
        CURSOR_ITEM("Cursor Item"),
        INVENTORY_MENU_ITEMS("Inventory Menu Items"),
        SADDLE_ITEM("Saddle Item"),
        EVENT_BLOCK_LOCATION("Event Block Location"),
        EVENT_BLOCK_FACE("Event Block Face"),
        EVENT_DAMAGE("Event Damage"),
        DAMAGE_CAUSE("Damage Cause"),
        BOW_POWER("Bow Power"),
        COMMNAD("Command"),
        COMMAND_ARGS("Command Arguments"),
        EVENT_ITEM("Event Item"),
        NEW_SLOT("New Slot"),
        OLD_SLOT("Old Slot"),
        CLICKED_SLOT_INDEX("Clicked Slot Index"),
        CLICKED_SLOT_ITEM("Clicked Slot Item"),
        NEW_CLICKED_SLOT_ITEM("New Clicked Slot Item"),
        INVENTORY_CLOSE_CAUSE("Inventory Close Cause"),
        TIMESTAMP("Timestamp");

		private final static Map<Integer, Value> values = new HashMap<>();

		private int value;
		private final String jsonValue;

		Value(final String jsonValue) {
			this.jsonValue = jsonValue;
		}

		static {
			for (Value value : Value.values()) {
				value.value = values.size();
				values.put(value.getValue(), value);
			}
		}

		public static Value valueOf(int Value) {
			return values.get(Value);
		}

		public int getValue() {
			return value;
		}

		public String getJSONValue() {
			return jsonValue;
		}
    }
}