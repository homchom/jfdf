package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class GameActionBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
	private String action;

	@Deprecated
	public GameActionBlock(Action action) {
		this.action = action.getJSONValue();
	}

	public GameActionBlock(String action) {
		this.action = action;
	}

	@Deprecated
	public static GameActionBlock New(Action action) {
		return new GameActionBlock(action);
	}

	public static GameActionBlock New(String action) {
		return new GameActionBlock(action);
	}

	public GameActionBlock SetItems(List<CodeValue> items) {
		this.items = items;

		return this;
	}

	public GameActionBlock SetItems(CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}
	
	public GameActionBlock SetTags(Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (Tag tag : tags) {
			tag.setAction(action);
			tag.setBlock("game_action");
		}
		
		return this;
	}
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"game_action\",\"args\":{\"items\":[";
		List<String> itemsJSON = new ArrayList<String>();
		
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
		json += "]},\"action\":\"" + action + "\"}";
		
		return json;
	}

	@Deprecated
	public enum Action {
		SPAWN_MOB("SpawnMob"),
		SPAWN_ITEM("SpawnItem"),
		SPAWN_RNG_ITEM("SpawnRngItem"),
		SPAWN_POTION_CLOUD("SpawnPotionCloud"),
		SPAWN_VEHICLE("SpawnVehicle"),
		SPAWN_EXP_ORB("SpawnExpOrb"),
		EXPLOSION("Explosion"),
		SPAWN_T_N_T("SpawnTNT"),
		SPAWN_FANGS("SpawnFangs"),
		FIREWORK("Firework"),
		LAUNCH_PROJ("LaunchProj"),
		LIGHTNING("Lightning"),
		FALLING_BLOCK("FallingBlock"),
		SPAWN_ARMOR_STAND("SpawnArmorStand"),
		SPAWN_CRYSTAL("SpawnCrystal"),
		SPAWN_ENDER_EYE("SpawnEnderEye"),
		SET_BLOCK("SetBlock"),
		BREAK_BLOCK("BreakBlock"),
		COPY_BLOCKS("CopyBlocks"),
		SET_BLOCK_DATA("SetBlockData"),
		TICK_BLOCK("TickBlock"),
		BONE_MEAL("BoneMeal"),
		GENERATE_TREE("GenerateTree"),
		SET_BLOCK_GROWTH("SetBlockGrowth"),
		FILL_CONTAINER("FillContainer"),
		SET_CONTAINER("SetContainer"),
		SET_ITEM_IN_SLOT("SetItemInSlot"),
		REPLACE_ITEMS("ReplaceItems"),
		REMOVE_ITEMS("RemoveItems"),
		CLEAR_ITEMS("ClearItems"),
		CLEAR_CONTAINER("ClearContainer"),
		SET_CONTAINER_NAME("SetContainerName"),
		LOCK_CONTAINER("LockContainer"),
		CHANGE_SIGN("ChangeSign"),
		SET_HEAD("SetHead"),
		CANCEL_EVENT("CancelEvent"),
		UNCANCEL_EVENT("UncancelEvent"),
		SET_EVENT_DAMAGE("SetEventDamage"),
		SET_EVENT_HEAL("SetEventHeal"),
		SET_EVENT_X_P("SetEventXP"),
		SET_EVENT_PROJ("SetEventProj"),
		SET_EVENT_SOUND("SetEventSound"),
		BLOCK_DROPS_ON("BlockDropsOn"),
		BLOCK_DROPS_OFF("BlockDropsOff"),
		SET_SC_OBJ("SetScObj"),
		CREATE_HOLOGRAM("CreateHologram"),
		REMOVE_HOLOGRAM("RemoveHologram"),
		WEB_REQUEST("WebRequest"),
		DISCORD_WEBHOOK("DiscordWebhook");

		private final static Map<Integer, Action> values = new HashMap<Integer, Action>();

		private int value;
		private final String jsonValue;

		Action(final String jsonValue) {
			this.jsonValue = jsonValue;
		}

		static {
			for (Action type : Action.values()) {
				type.value = values.size();
				values.put(type.getValue(), type);
			}
		}

		public static Action valueOf(int type) {
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
