package net.jfdf.jfdf.blocks;

import java.util.HashMap;
import java.util.Map;

public class PlayerEventBlock implements CodeHeader {
	
	private final String event;
	
	public PlayerEventBlock(final String event) {
		this.event = event;
	}
	 
	public String asJSON() {
		return "{\"id\":\"block\",\"block\":\"event\",\"args\":{\"items\":[]},\"action\":\"" + event + "\"}";
	}

	public String getTemplateName() {
		return "Event » " + event;
	}

	public String getTemplateNameWithColors() {
		return "\u00A7e\u00A7lEvent » \u00A7e" + event + " Event";
	}
	
	public enum Event {
		JOIN("Join"),
		QUIT("Quit"),
		COMMAND("Command"),
		RIGHT_CLICK("RightClick"),
		LEFT_CLICK("LeftClick"),
		CLICK_ENTITY("ClickEntity"),
		CLICK_PLAYER("ClickPlayer"),
		PLACE_BLOCK("PlaceBlock"),
		BREAK_BLOCK("BreakBlock"),
		SWAP_HANDS("SwapHands"),
		CHANGE_SLOT("ChangeSlot"),
		CLOSE_INV("CloseInv"),
		WALK("Walk"),
		JUMP("Jump"),
		SNEAK("Sneak"),
		UNSNEAK("Unsneak"),
		START_SPRINT("StartSprint"),
		STOP_SPRINT("StopSprint"),
		START_FLY("StartFly"),
		STOP_FLY("StopFly"),
		RIPTIDE("Riptide"),
		DISMOUNT("Dismount"),
		CLICK_ITEM("ClickItem"),
		CLICK_OWN_INV("ClickOwnInv"),
		PICKUP_ITEM("PickupItem"),
		DROP_ITEM("DropItem"),
		CONSUME("Consume"),
		BREAK_ITEM("BreakItem"),
		PLAYER_TAKE_DMG("PlayerTakeDmg"),
		PLAYER_DMG_PLAYER("PlayerDmgPlayer"),
		ENTITY_DMG_PLAYER("EntityDmgPlayer"),
		DAMAGE_ENTITY("DamageEntity"),
		HEAL("PlayerHeal"),
		SHOOT_BOW("ShootBow"),
		PROJECTILE_HIT("ProjHit"),
		PROJECTILE_DMG_PLAYER("ProjDmgPlayer"),
		POTION_CLOUD_IMBUE_PLAYER("CloudImbuePlayer"),
		DEATH("Death"),
		KILL_PLAYER("KillPlayer"),
		KILL_MOB("KillMob"),
		MOB_KILL_PLAYER("MobKillPlayer"),
		RESPAWN("Respawn");

		private final static Map<Integer, Event> values = new HashMap<Integer, Event>();

		private int value;
		private final String name;
		private final String jsonValue;

		Event(final String jsonValue) {
			this.name = String.join(" ", jsonValue.split("(?=\\p{Upper})"));
			this.jsonValue = jsonValue;
		}

		static {
			for (Event type : Event.values()) {
				type.value = values.size();
				values.put(type.getValue(), type);
			}
		}

		public static Event valueOf(final int type) {
			return values.get(type);
		}
		
		public int getValue() {
			return value;
		}
		
		public String getName() {
			return name;
		}

		public String getJSONValue() {
			return jsonValue;
		}
	}
}
