package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.blocks.PlayerActionBlock.PlayerSelection;
import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class IfPlayerBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
    
    private final PlayerSelection playerSelection;
	private boolean inverseIf = false;
    private Type type;
	
	public IfPlayerBlock(Type type, PlayerSelection playerSelection) {
        this.type = type;
        this.playerSelection = playerSelection;
	}

	public IfPlayerBlock(Type type, PlayerSelection playerSelection, boolean inverseIf) {
        this.type = type;
		this.playerSelection = playerSelection;
		this.inverseIf = inverseIf;
	}

	public IfPlayerBlock SetItems(List<CodeValue> items) {
		this.items = items;

		return this;
	}
	
	public IfPlayerBlock SetItems(final CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}

	public IfPlayerBlock SetTags(final Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (final Tag tag : tags) {
            tag.setBlock("if_player");
            tag.setAction(type.getJSONValue());
		}
		
		return this;
	}
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"if_player\",\"args\":{\"items\":[";
		final List<String> itemsJSON = new ArrayList<String>();
		
		if(tags.size() > 9) tags = tags.subList(0, 8);
		if(items.size() > (27 - tags.size())) items = items.subList(0, 26 - tags.size());
		
		for (int i = 0; i < items.size(); i++) {
			final CodeValue codeValue = items.get(i);
			itemsJSON.add("{\"item\":" + codeValue.asJSON() + ",\"slot\":" + i + "}");
		}

		for (int i = 26; i >= 27 - tags.size(); i--) {
			Tag tag = tags.get(26 - i);
			itemsJSON.add("{\"item\":" + tag.asJSON() + ",\"slot\":" + i + "}");
		}
		
		json += String.join(",", itemsJSON);
        json += "]},\"action\":\"" + type.getJSONValue() + "\"" + ",\"selection\":\"" 
            + playerSelection.getJSONValue() +"\"" + (inverseIf ? ",\"inverted\":\"NOT\"" : "") + "}";
		
		return json;
	}

	public enum Type {
		IS_SNEAKING("IsSneaking"),
		IS_SPRINTING("IsSprinting"),
		IS_GLIDING("IsGliding"),
		IS_FLYING("IsFlying"),
		IS_GROUNDED("IsGrounded"),
		IS_SWIMMING("IsSwimming"),
		IS_BLOCKING("IsBlocking"),
		IS_LOOKING_AT("IsLookingAt"),
		STANDING_ON("StandingOn"),
		IS_NEAR("IsNear"),
		IN_WORLD_BORDER("InWorldBorder"),
		IS_HOLDING("IsHolding"),
		HAS_ITEM("HasItem"),
		IS_WEARING("IsWearing"),
		IS_USING_ITEM("IsUsingItem"),
		NO_ITEM_COOLDOWN("NoItemCooldown"),
		HAS_SLOT_ITEM("HasSlotItem"),
		MENU_SLOT_EQUALS("MenuSlotEquals"),
		CURSOR_ITEM("CursorItem"),
		HAS_ROOM_FOR_ITEM("HasRoomForItem"),
		NAME_EQUALS("NameEquals"),
		SLOT_EQUALS("SlotEquals"),
		HAS_EFFECT("HasEffect"),
		IS_RIDING("IsRiding"),
		INV_OPEN("InvOpen"),
		HAS_PERMISSION("HasPermission");

		private final static Map<Integer, Type> values = new HashMap<Integer, Type>();

		private int value;
		private final String jsonValue;

		Type(final String jsonValue) {
			this.jsonValue = jsonValue;
		}

		static {
			for (Type type : Type.values()) {
				type.value = values.size();
				values.put(type.getValue(), type);
			}
		}

		public static Type valueOf(int type) {
			return values.get(type);
		}

		public int getValue() {
			return value;
		}

		public String getJSONValue() {
			return jsonValue;
		}
	}
}