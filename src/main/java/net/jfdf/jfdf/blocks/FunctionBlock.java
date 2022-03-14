package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class FunctionBlock implements CodeHeader {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
	private final String name;

	public FunctionBlock(String name) {
		this.name = name;
	}

	public FunctionBlock SetTags(Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (Tag tag : tags) {
			tag.setAction("dynamic");
			tag.setBlock("func");
		}
		
		return this;
	}

	public FunctionBlock SetItems(CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}

	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"func\",\"args\":{\"items\":[";
		List<String> itemsJSON = new ArrayList<>();
		
		if(tags.size() > 9) tags = tags.subList(0, 8);
		if(items.size() > (27 - tags.size())) items = items.subList(0, 26 - tags.size());
		
		for (int i = 0; i < items.size(); i++) {
			CodeValue codeValue = items.get(i);
			itemsJSON.add("{\"item\":" + codeValue.asJSON() + ",\"slot\":" + i + "}");
		}

		for (int i = 26; i >= 27 - tags.size(); i--) {
			Tag tag = tags.get(26 - i);
			itemsJSON.add("{\"item\":" + tag.asJSON() + ",\"slot\":" + i + "}");
		}
		
		json += String.join(",", itemsJSON);
		json += "]},\"data\":\"" + name + "\"}";
		
		return json;
	}

	public String getName() {
		return name;
	}

	public String getTemplateName() {
		return "Function » " + name;
	}
	
	public String getTemplateNameWithColors() {
		return "\u00A7b\u00A7lFunction \u00A73» \u00A7b" + name;
	}
}
