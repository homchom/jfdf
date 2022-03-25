package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class ControlBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<>();
	private List<Tag> tags = new ArrayList<>();
	private final String action;

	@Deprecated
	public ControlBlock(final Action action) {
		this.action = action.getJSONValue();
	}

	public ControlBlock(String action) {
		this.action = action;
	}

	@Deprecated
	public static ControlBlock New(final Action action) {
		return new ControlBlock(action);
	}

	public static ControlBlock New(String action) {
		return new ControlBlock(action);
	}

	public ControlBlock SetItems(final CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}

	public ControlBlock SetItems(List<CodeValue> items) {
		this.items = items;

		return this;
	}
	
	public ControlBlock SetTags(final Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (final Tag tag : tags) {
			tag.setAction(action);
			tag.setBlock("control");
		}
		
		return this;
	}
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"control\",\"args\":{\"items\":[";
		
		if(items.size() >= 1 || tags.size() >= 1) {
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
		}
		json += "]},\"action\":\"" + action + "\"}";
		
		return json;
	}

	public String getAction() {
		return action;
	}

	@Deprecated
	public enum Action {
		WAIT(0, "Wait"),
		RETURN(1, "Return"),
		END_SEQUENCE(2, "End"),
		SKIP_ITREATION(3, "Skip"),
		STOP_REPEATING(4, "StopRepeat");

		private final static Map<Integer, Action> values = new HashMap<Integer, Action>();

		private final int value;
		private final String jsonValue;

		Action(final int value, final String jsonValue) {
			this.value = value;
			this.jsonValue = jsonValue;
		}

		static {
			for (final Action type : Action.values()) {
				values.put(type.getValue(), type);
			}
		}

		public static Action valueOf(final int type) {
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