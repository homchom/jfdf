package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.mangement.SubIf.SubIfType;
import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class SelectionBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
	
	private final String selection;
	private boolean inverseIf = false;
	private SubIfType ifBlock = null;

	@Deprecated
	public SelectionBlock(Selection selection) {
		this.selection = selection.getJSONValue();

		if(selection.getValue() >= 18 && selection.getValue() <= 21) {
			throw new IllegalArgumentException("You can't put if selection without if !");
		}
	}

	@Deprecated
	public SelectionBlock(Selection selection, SubIfType ifBlock) {
		this.selection = selection.getJSONValue();
		this.ifBlock = ifBlock;

		if(selection.getValue() < 18 || selection.getValue() > 21) {
			throw new IllegalArgumentException("You can't put normal selection with if !");
		}
	}

	public SelectionBlock(String selection) {
		this.selection = selection;
	}

	public SelectionBlock(String selection, SubIfType ifBlock) {
		this.selection = selection;
		this.ifBlock = ifBlock;
	}

	public SelectionBlock SetItems(List<CodeValue> items) {
		this.items = items;

		return this;
	}
	
	public SelectionBlock SetItems(final CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}

	public SelectionBlock SetTags(final Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (final Tag tag : tags) {
			if(ifBlock == null) {
				tag.setBlock("select_obj");
				tag.setAction(selection);
			} else {
				tag.setBlock(ifBlock.getIfBlock());
				tag.setAction(ifBlock.getNormalJSONValue());
			}
		}
		
		return this;
	}
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"select_obj\",\"args\":{\"items\":[";
		final List<String> itemsJSON = new ArrayList<>();
		
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
		json += "]},\"action\":\"" + selection + "\"";
		
		if(ifBlock != null) {
			json += ",\"subAction\":\"" + ifBlock.getSubJSONValue() +"\"" + (inverseIf ? ",\"inverted\":\"NOT\"" : "");
		}

		json += "}";
		
		return json;
	}

	@Deprecated
	public enum Selection {
		DEFAULT_PLAYER("DefaultPlayer"),
		DEFAULT_ENTITY("DefaultEntity"),
		RANDOM_PLAYER("RandomPlayer"),
		RANDOM_MOB("RandomMob"),
		RANDOM_ENTITY("RandomEntity"),
		ALL_PLAYERS("AllPlayers"),
		ALL_MOBS("AllMobs"),
		ALL_ENTITIES("AllEntities"),
		LAST_SPAWNED_MOB("LastMob"),
		LAST_SPAWNED_ENTITY("LastEntity"),
		KILLER("Killer"),
		DAMAGER("Damager"),
		SHOOTER("Shooter"),
		VICTIM("Victim"),
		PROJECTILE("Projectile"),
		PLAYER_NAME("PlayerName"),
		MOBS_NAME("MobName"),
		ENTITIES_NAME("EntityName"),
		PLAYERS_CONDITION("PlayersCond"),
		MOBS_CONDITION("MobsCond"),
		ENTITIES_CONDITION("EntitiesCond"),
		FILTER_SELECTION_CONDITION("FilterSelect"),
		FILTER_SELECTION_RANDOM("RandomSelected"),
		NONE("None");

		private final static Map<Integer, Selection> values = new HashMap<Integer, Selection>();

		private int value;
		private final String jsonValue;

		Selection(final String jsonValue) {
			this.jsonValue = jsonValue;
		}

		static {
			for (Selection selection : Selection.values()) {
				selection.value = values.size();
				values.put(selection.getValue(), selection);
			}
		}

		public static Selection valueOf(int Selection) {
			return values.get(Selection);
		}

		public int getValue() {
			return value;
		}

		public String getJSONValue() {
			return jsonValue;
		}
	}
}