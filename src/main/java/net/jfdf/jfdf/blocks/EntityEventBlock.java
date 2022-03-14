package net.jfdf.jfdf.blocks;

import java.util.HashMap;
import java.util.Map;

public class EntityEventBlock implements CodeHeader {

	private String event;

	public EntityEventBlock(String event) {
		this.event = event;
	}
	 
	public String asJSON() {
		return "{\"blocks\":[{\"id\":\"block\",\"entity_event\":\"event\",\"args\":{\"items\":[]},\"action\":\"" + event + "\"}";
	}

	public String getTemplateName() {
		return "Event » " + event;
	}

	public String getTemplateNameWithColors() {
		return "\u00A7e\u00A7lEvent \u00A76» \u00A7e" + event + " Event";
	}
	
	public enum Event {
		ENTITY_DAMAGE_ENTITY("EntityDmgEntity"),
		ENTITY_KILL_ENTITY("EntityKillEntity"),
		ENTITY_TAKE_DAMAGE("EntityDmg"),
		PROJECTILE_DAMAGE_ENTITY("ProjDmgEntity"),
		PROJECTILE_KILL_ENTITY("ProjKillEntity"),
		ENTITY_DEATH("EntityDeath"),
		VECHILE_TAKE_DAMAGE("VechileDamage"),
		BLOCK_FALL("BlockFall"),
		FALLING_BLOCK_LAND("FallingBlockLand");

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
