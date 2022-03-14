package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.blocks.EntityActionBlock.EntitySelection;
import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class IfEntityBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
    
    private EntitySelection entitySelection;
	private boolean inverseIf = false;
    private Type type;
	
	public IfEntityBlock(Type type, EntitySelection entitySelection) {
        this.type = type;
        this.entitySelection = entitySelection;
	}

	public IfEntityBlock(Type type, EntitySelection entitySelection, boolean inverseIf) {
        this.type = type;
		this.entitySelection = entitySelection;
		this.inverseIf = inverseIf;
	}

	public IfEntityBlock SetItems(final List<CodeValue> items) {
		this.items = items;

		return this;
	}
	
	public IfEntityBlock SetItems(final CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}

	public IfEntityBlock SetTags(final Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (final Tag tag : tags) {
            tag.setBlock("if_entity");
            tag.setAction(type.getJSONValue());
		}
		
		return this;
	}
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"if_entity\",\"args\":{\"items\":[";
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
            + entitySelection.getJSONValue() +"\"" + (inverseIf ? ",\"inverted\":\"NOT\"" : "") + "}";
		
		return json;
	}

	public enum Type {
		IS_TYPE("IsType"),
        NAME_EQUALS("NameEquals"),
        IS_STANDING_ON("StandingOn"),
        IS_GROUNDED("IsGrounded"),
        IS_NEAR("IsNear"),
        IS_RIDING("IsRiding"),
        IS_MOB("IsMob"),
        IS_PROJECTILE("IsProj"),
        IS_VECHILE("IsVechile"),
        IS_ITEM("IsItem"),
        EXISTS("Exists"),
        HAS_CUSTOM_TAG("HasCustomTag");

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