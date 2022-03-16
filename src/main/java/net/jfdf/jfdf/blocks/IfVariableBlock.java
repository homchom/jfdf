package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class IfVariableBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
    
	private boolean inverseIf = false;
    private final String type;
	
	public IfVariableBlock(Type type) {
        this.type = type.getJSONValue();
	}

	public IfVariableBlock(Type type, boolean inverseIf) {
		this.type = type.getJSONValue();
		this.inverseIf = inverseIf;
	}

	public IfVariableBlock(String type, boolean inverseIf) {
		this.type = type;
		this.inverseIf = inverseIf;
	}

	public IfVariableBlock SetItems(final List<CodeValue> items) {
		this.items = items;

		return this;
	}
	
	public IfVariableBlock SetItems(final CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}

	public IfVariableBlock SetTags(final Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (final Tag tag : tags) {
            tag.setBlock("if_var");
            tag.setAction(type);
		}
		
		return this;
	}
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"if_var\",\"args\":{\"items\":[";
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
        json += "]},\"action\":\"" + type + (inverseIf ? "\",\"inverted\":\"NOT\"" : "\"") + "}";
		
		return json;
	}

	public enum Type {
		EQUALS("="),
        NOT_EQUALS("!="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL_TO(">="),
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL_TO("<="),
        IN_RANGE("InRange"),
        IS_NEAR("IsNear"),
        LOC_IS_NEAR("LocIsNear"),
        TEXT_MATCHES("TextMatches"),
        TEXT_CONTAINS("Contains"),
        TEXT_STARTS_WITH( "StartsWith"),
        TEXT_ENDS_WITH( "EndsWith"),
        EXISTS( "VarExists"),
        VARIABLE_IS_TYPE("VarIsType"),
        ITEM_EQUALS("ItemEquals"),
        ITEM_HAS_TAG( "ItemHasTag"),
        LIST_CONTAINS_VALUE("ListContains"),
        LIST_VALUE_EQUALS("ListValueEq");

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