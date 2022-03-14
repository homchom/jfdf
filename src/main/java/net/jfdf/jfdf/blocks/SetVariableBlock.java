package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class SetVariableBlock implements CodeBlock {
	public List<CodeValue> items = new ArrayList<>();
	public List<Tag> tags = new ArrayList<>();
	public String type;

	public SetVariableBlock(String type) {
		this.type = type;
	}

	public static SetVariableBlock New(String type) {
		return new SetVariableBlock(type);
	}

	public SetVariableBlock SetItems(List<CodeValue> items) {
		this.items = items;

		return this;
	}

	public SetVariableBlock SetItems(final CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}

	public SetVariableBlock SetTags(final Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (final Tag tag : tags) {
			tag.setBlock("set_var");
			tag.setAction(type);
		}
		
		return this;
	}

	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"set_var\",\"args\":{\"items\":[";
		List<String> itemsJSON = new ArrayList<>();
		
		if(tags.size() > 9) tags = tags.subList(0, 8);
		if(items.size() > (27 - tags.size())) items = items.subList(0, 26 - tags.size());
		
		for (int i = 0; i < items.size(); i++) {
			final CodeValue codeValue = items.get(i);
			itemsJSON.add("{\"item\":" + codeValue.asJSON() + ",\"slot\":" + i + "}");
		}

		for (int i = 0; i < tags.size(); i++) {
			final Tag tag = tags.get(i);
			itemsJSON.add("{\"item\":" + tag.asJSON() + ",\"slot\":" + (26 - i) + "}");
		}
		
		json += String.join(",", itemsJSON);
		json += "]},\"action\":\"" + type + "\"}";

		return json;
	}
}
