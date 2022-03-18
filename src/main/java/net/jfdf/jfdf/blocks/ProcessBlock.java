package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class ProcessBlock implements CodeHeader {
	private List<CodeValue> items = new ArrayList<>();
	private List<Tag> tags = new ArrayList<>();
	private final String name;

	public ProcessBlock(String name) {
		this.name = name;
	}

	public ProcessBlock SetTags(Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (Tag tag : tags) {
			tag.setAction("dynamic");
			tag.setBlock("process");
		}
		
		return this;
	}

	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"process\",\"args\":{\"items\":[";
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
		return "Process » " + name;
	}
	
	public String getTemplateNameWithColors() {
		return "\u00A7a\u00A7lProcess \u00A73» \u00A7a" + name;
	}
}