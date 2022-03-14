package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class IfGameBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
    
	private boolean inverseIf = false;
    private Type type;
	
	public IfGameBlock(Type type) {
        this.type = type;
	}

	public IfGameBlock(Type type, boolean inverseIf) {
		this.type = type;
		this.inverseIf = inverseIf;
	}

	public IfGameBlock SetItems(final List<CodeValue> items) {
		this.items = items;

		return this;
	}
	
	public IfGameBlock SetItems(final CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}

	public IfGameBlock SetTags(final Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (final Tag tag : tags) {
            tag.setBlock("if_game");
            tag.setAction(type.getJSONValue());
		}
		
		return this;
	}
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"if_game\",\"args\":{\"items\":[";
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
        json += "]},\"action\":\"" + type.getJSONValue() + (inverseIf ? "\",\"inverted\":\"NOT\"" : "\"") + "}";
		
		return json;
	}

	public enum Type {
		BLOCK_EQUALS("BlockEquals"),
        BLOCK_POWERED("BlockPowered"),
        CONTAINER_HAS_ITEM("ContainterHas"),
        CONTAINER_HAS_ALL_ITEMS("ContainterHasAll"),
        CONTAINER_HAS_ROOM_FOR_ITEM("HasRoomForItem"),
        SIGN_CONTAINS_TEXT("SignHasTxt"),
        HAS_PLAYER("HasPlayer"),
        EVENT_BLOCK_EQUALS("EventBlockEquals"),
        EVENT_ITEM_EQUALS("EventItemEquals"),
        COMMAND_EQUALS("CommandEquals"),
        COMMAND_ARGUMENT_EQUALS("CmdArgEquals"),
        IS_EVENT_CANCELLED("EventCancelled");

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