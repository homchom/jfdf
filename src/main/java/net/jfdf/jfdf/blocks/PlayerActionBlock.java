package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class PlayerActionBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
	private final String action;

	@Deprecated
	public PlayerActionBlock(Action action, PlayerSelection selection) {
		this.action = action.getJSONValue();
	}

	public PlayerActionBlock(String action, PlayerSelection selection) {
		this.action = action;
	}

	@Deprecated
	public static PlayerActionBlock New(Action action, PlayerSelection selection) {
		return new PlayerActionBlock(action, selection);
	}

	public static PlayerActionBlock New(String action, PlayerSelection selection) {
		return new PlayerActionBlock(action, selection);
	}

	public PlayerActionBlock SetItems(List<CodeValue> items) {
		this.items = items;

		return this;
	}

	public PlayerActionBlock SetItems(CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}
	
	public PlayerActionBlock SetTags(Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (Tag tag : tags) {
			tag.setAction(action);
			tag.setBlock("player_action");
		}
		
		return this;
	}
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"player_action\",\"args\":{\"items\":[";
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

	@Deprecated(forRemoval = true)
	public enum Action {
		GIVE_ITEMS("GiveItems"),
		SET_ITEMS("SetItems"),
		SET_SLOT_ITEM("SetSlotItem"),
		SET_EQUIPMENT("SetEquipment"),
		SET_ARMOR("SetArmor"),
		REPLACE_ITEMS("ReplaceItems"),
		REMOVE_ITEMS("RemoveItems"),
		CLEAR_ITEMS("ClearItems"),
		CLEAR_INV("ClearInv"),
		GIVE_RNG_ITEM("GiveRngItem"),
		SET_CURSOR_ITEM("SetCursorItem"),
		SAVE_INV("SaveInv"),
		LOAD_INV("LoadInv"),
		SET_ITEM_COOLDOWN("SetItemCooldown"),
		SEND_MESSAGE("SendMessage"),
		SEND_DIALOGUE("SendDialogue"),
		SEND_HOVER("SendHover"),
		CLEAR_CHAT("ClearChat"),
		SEND_TITLE("SendTitle"),
		ACTION_BAR("ActionBar"),
		OPEN_BOOK("OpenBook"),
		BOSS_BAR("BossBar"),
		REMOVE_BOSS_BAR("RemoveBossBar"),
		SEND_ADVANCEMENT("SendAdvancement"),
		SET_TAB_LIST_INFO("SetTabListInfo"),
		PLAY_SOUND("PlaySound"),
		STOP_SOUND("StopSound"),
		PLAY_SOUND_SEQ("PlaySoundSeq"),
		SHOW_INV("ShowInv"),
		EXPAND_INV("ExpandInv"),
		SET_MENU_ITEM("SetMenuItem"),
		SET_INV_NAME("SetInvName"),
		ADD_INV_ROW("AddInvRow"),
		REMOVE_INV_ROW("RemoveInvRow"),
		CLOSE_INV("CloseInv"),
		OPEN_BLOCK_INV("OpenBlockInv"),
		DAMAGE("Damage"),
		HEAL("Heal"),
		SET_HEALTH("SetHealth"),
		SET_MAX_HEALTH("SetMaxHealth"),
		SET_ABSORPTION("SetAbsorption"),
		SET_FOOD_LEVEL("SetFoodLevel"),
		SET_SATURATION("SetSaturation"),
		GIVE_EXP("GiveExp"),
		SET_EXP("SetExp"),
		GIVE_EFFECT("GiveEffect"),
		REMOVE_EFFECT("RemoveEffect"),
		CLEAR_EFFECTS("ClearEffects"),
		SET_SLOT("SetSlot"),
		SET_ATK_SPEED("SetAtkSpeed"),
		SET_FIRE_TICKS("SetFireTicks"),
		SET_AIR_TICKS("SetAirTicks"),
		SET_INVUL_TICKS("SetInvulTicks"),
		SET_FALL_DISTANCE("SetFallDistance"),
		SET_SPEED("SetSpeed"),
		SET_COLLIDABLE("SetCollidable"),
		SET_ALLOW_FLIGHT("SetAllowFlight"),
		SET_ALLOW_P_V_P("SetAllowPVP"),
		SET_INVENTORY_KEPT("SetInventoryKept"),
		SET_DROPS_ENABLED("SetDropsEnabled"),
		SET_GAMEMODE("SetGamemode"),
		ENABLE_BLOCKS("EnableBlocks"),
		DISABLE_BLOCKS("DisableBlocks"),
		TELEPORT("Teleport"),
		RNG_TELEPORT("RngTeleport"),
		TP_SEQUENCE("TpSequence"),
		LAUNCH_UP("LaunchUp"),
		LAUNCH_FWD("LaunchFwd"),
		LAUNCH_TOWARD("LaunchToward"),
		RIDE_ENTITY("RideEntity"),
		SET_FLYING("SetFlying"),
		SET_GLIDING("SetGliding"),
		LAUNCH_PROJ("LaunchProj"),
		PARTICLE_EFFECT("ParticleEffect"),
		SET_PLAYER_TIME("SetPlayerTime"),
		SET_PLAYER_WEATHER("SetPlayerWeather"),
		SET_COMPASS("SetCompass"),
		DISPLAY_LIGHTNING("DisplayLightning"),
		DISPLAY_BLOCK("DisplayBlock"),
		DISPLAY_FRACTURE("DisplayFracture"),
		DISPLAY_BLOCK_OPEN("DisplayBlockOpen"),
		DISPLAY_SIGN_TEXT("DisplaySignText"),
		SET_WORLD_BORDER("SetWorldBorder"),
		SHIFT_WORLD_BORDER("ShiftWorldBorder"),
		MOB_DISGUISE("MobDisguise"),
		PLAYER_DISGUISE("PlayerDisguise"),
		BLOCK_DISGUISE("BlockDisguise"),
		SET_DISGUISE_VISIBLE("SetDisguiseVisible"),
		UNDISGUISE("Undisguise"),
		SET_CHAT_TAG("SetChatTag"),
		CHAT_COLOR("ChatColor"),
		SET_NAME_COLOR("SetNameColor"),
		SET_ARROWS_STUCK("SetArrowsStuck"),
		ATTACK_ANIMATION("AttackAnimation"),
		SEND_ANIMATION("SendAnimation"),
		ROLLBACK_BLOCKS("RollbackBlocks"),
		RESPAWN("Respawn"),
		KICK("Kick");

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
	
	public enum PlayerSelection {
		CURRENT_SELECTION(0, "Selection"),
		DEFAULT_PLAYER(1, "Default"),
		KILLER(2, "Killer"),
		DAMAGER(3, "Damager"),
		SHOOTER(4, "Shooter"),
		VICTIM(5, "Victim"),
		ALL_PLAYERS(6, "AllPlayers");

		private final static Map<Integer, PlayerSelection> values = new HashMap<Integer, PlayerSelection>();

		private final int value;
		private final String jsonValue;

		PlayerSelection(final int value, final String jsonValue) {
			this.value = value;
			this.jsonValue = jsonValue;
		}

		static {
			for (PlayerSelection type : PlayerSelection.values()) {
				values.put(type.getValue(), type);
			}
		}

		public static PlayerSelection valueOf(int type) {
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
