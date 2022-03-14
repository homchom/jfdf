package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.mangement.SubIf.SubIfType;
import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class RepeatBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
	
	private final Type repeatType;
	private boolean inverseIf = false;
	private SubIfType ifBlock = null;
	
	public RepeatBlock(final Type repeatType) {
		this.repeatType = repeatType;
	}
	
	public RepeatBlock(final SubIfType ifBlock) {
		this.inverseIf = true;
		this.ifBlock = ifBlock;
		this.repeatType = Type.WHILE;
	}

	public RepeatBlock(final SubIfType ifBlock, final boolean inverseIf) {
		this.ifBlock = ifBlock;
		this.repeatType = Type.WHILE;
		this.inverseIf = inverseIf;
	}

	public RepeatBlock SetItems(final List<CodeValue> items) {
		this.items = items;

		return this;
	}
	
	public RepeatBlock SetItems(final CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}

	public RepeatBlock SetTags(final List<Tag> tags) {
		this.tags = tags;
		
		String actionValue = repeatType.getJSONValue();

		for (final Tag tag : tags) {
			if(ifBlock == null) {
				tag.setBlock("repeat");
				tag.setAction(actionValue);
			}
		}
		
		return this;
	}

	public RepeatBlock SetTags(final Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		String actionValue = repeatType.getJSONValue();

		for (final Tag tag : tags) {
			if(ifBlock == null) {
				tag.setBlock("repeat");
				tag.setAction(actionValue);
			}
		}
		
		return this;
	}
	
	public String asJSON() {
		String actionValue = repeatType.getJSONValue();
		
		String json = "{\"id\":\"block\",\"block\":\"repeat\",\"args\":{\"items\":[";
		final List<String> itemsJSON = new ArrayList<String>();
		
		if(tags.size() > 9) tags = tags.subList(0, 8);
		if(items.size() > (27 - tags.size())) items = items.subList(0, 26 - tags.size());
		
		for (int i = 0; i < items.size(); i++) {
			final CodeValue codeValue = items.get(i);
			itemsJSON.add("{\"item\":" + codeValue.asJSON() + ",\"slot\":" + i + "}");
		}

		for (int i = 26; i < tags.size(); i--) {
			final Tag tag = tags.get(i);
			itemsJSON.add("{\"item\":" + tag.asJSON() + ",\"slot\":" + i + "}");
		}
		
		json += String.join(",", itemsJSON);
		json += "]},\"action\":\"" + actionValue + "\"";
		
		if(actionValue.equals("While")) {
			json += ",\"subAction\":\"" + ifBlock.getSubJSONValue() +"\"" + (inverseIf ? ",\"inverted\":\"NOT\"" : "");
		}

		json += "}";
		
		return json;
	}

	public enum Type {
		FOREVER("Forever"),
		WHILE("While"),
		MULTIPLE_TIMES("Multiple"),
		ON_RANGE("Range"),
		FOREACH("ForEach"),
		ON_GRID("Grid"),
		ADJACENTLY("Adjacent"),
		ON_SPHERE("SPHERE");

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