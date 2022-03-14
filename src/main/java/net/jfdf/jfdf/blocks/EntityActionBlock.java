package net.jfdf.jfdf.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

public class EntityActionBlock implements CodeBlock {
	private List<CodeValue> items = new ArrayList<CodeValue>();
	private List<Tag> tags = new ArrayList<Tag>();
	private String action;

	@Deprecated
	public EntityActionBlock(Action action, EntitySelection selection) {
		this.action = action.getJSONValue();
	}

	public EntityActionBlock(String action, EntitySelection selection) {
		this.action = action;
	}
	
	public static EntityActionBlock New(Action action, EntitySelection selection) {
		return new EntityActionBlock(action, selection);
	}

	public EntityActionBlock SetItems(List<CodeValue> items) {
		this.items = items;

		return this;
	}

	public EntityActionBlock SetItems(CodeValue... items) {
		this.items = Arrays.asList(items);

		return this;
	}
	
	public EntityActionBlock SetTags(Tag... tags) {
		this.tags = Arrays.asList(tags);
		
		for (Tag tag : tags) {
			tag.setAction(action);
			tag.setBlock("entity_action");
		}
		
		return this;
	}
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"entity_action\",\"args\":{\"items\":[";
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
		HEAL("Heal"),
		SET_HEALTH("SetHealth"),
		SET_ABSORPTION("SetAbsorption"),
		SET_MAX_HEALTH("SetMaxHealth"),
		DAMAGE("Damage"),
		SET_FIRE_TICKS("SetFireTicks"),
		SET_INVUL_TICKS("SetInvulTicks"),
		GIVE_EFFECT("GiveEffect"),
		REMOVE_EFFECT("RemoveEffect"),
		CLEAR_EFFECTS("ClearEffects"),
		SET_AGE("SetAge"),
		SET_FALL_DISTANCE("SetFallDistance"),
		SET_ANGER_TICKS("SetAngerTicks"),
		SET_CREEPER_FUSE("SetCreeperFuse"),
		SET_CREEPER_POWER("SetCreeperPower"),
		SET_CLOUD_RADIUS("SetCloudRadius"),
		SET_VILLAGER_EXP("SetVillagerExp"),
		SET_WITHER_SHIELD("SetWitherShield"),
		SET_HORSE_JUMP("SetHorseJump"),
		SET_PICKUP_DELAY("SetPickupDelay"),
		SET_NAME("SetName"),
		SET_NAME_VISIBLE("SetNameVisible"),
		SET_A_I("SetAI"),
		SET_SILENCED("SetSilenced"),
		SET_GRAVITY("SetGravity"),
		SET_DEATH_DROPS("SetDeathDrops"),
		SET_COLLIDABLE("SetCollidable"),
		SET_INVULNERABLE("SetInvulnerable"),
		SET_MOB_SITTING("SetMobSitting"),
		SET_BABY("SetBaby"),
		SET_SIZE("SetSize"),
		SET_SHEEP_SHEARED("SetSheepSheared"),
		SET_SADDLE("SetSaddle"),
		SET_CARRYING_CHEST("SetCarryingChest"),
		CREEPER_CHARGED("CreeperCharged"),
		IGNITE_CREEPER("IgniteCreeper"),
		TAME("Tame"),
		END_CRYSTAL_BEAM("EndCrystalBeam"),
		SET_PANDA_GENE("SetPandaGene"),
		SET_PROFESSION("SetProfession"),
		SET_ITEM_OWNER("SetItemOwner"),
		SET_PROJ_SOURCE("SetProjSource"),
		TELEPORT("Teleport"),
		TP_SEQUENCE("TpSequence"),
		LAUNCH_UP("LaunchUp"),
		LAUNCH_FWD("LaunchFwd"),
		LAUNCH_TOWARD("LaunchToward"),
		RIDE_ENTITY("RideEntity"),
		ATTACH_LEAD("AttachLead"),
		MOVE_TO("MoveTo"),
		SET_TARGET("SetTarget"),
		SET_ROTATION("SetRotation"),
		MOB_DISGUISE("MobDisguise"),
		PLAYER_DISGUISE("PlayerDisguise"),
		BLOCK_DISGUISE("BlockDisguise"),
		UNDISGUISE("Undisguise"),
		SET_GLOWING("SetGlowing"),
		SET_DYE_COLOR("SetDyeColor"),
		SET_FISH_PATTERN("SetFishPattern"),
		SET_RABBIT_TYPE("SetRabbitType"),
		SET_CAT_TYPE("SetCatType"),
		MOOSHROOM_TYPE("MooshroomType"),
		SET_FOX_TYPE("SetFoxType"),
		SET_PARROT_COLOR("SetParrotColor"),
		SET_HORSE_PATTERN("SetHorsePattern"),
		SET_LLAMA_COLOR("SetLlamaColor"),
		SET_VILLAGER_BIOME("SetVillagerBiome"),
		SNOWMAN_PUMPKIN("SnowmanPumpkin"),
		SET_ENDERMAN_BLOCK("SetEndermanBlock"),
		SET_MINECART_BLOCK("SetMinecartBlock"),
		PROJECTILE_ITEM("ProjectileItem"),
		SEND_ANIMATION("SendAnimation"),
		ATTACK_ANIMATION("AttackAnimation"),
		SET_POSE("SetPose"),
		ARMOR_STAND_TAGS("ArmorStandTags"),
		REMOVE("Remove"),
		SET_EQUIPMENT("SetEquipment"),
		SET_ARMOR("SetArmor"),
		LAUNCH_PROJ("LaunchProj"),
		SHEEP_EAT("SheepEat"),
		SHEAR_SHEEP("ShearSheep"),
		EXPLODE_CREEPER("ExplodeCreeper"),
		SET_CUSTOM_TAG("SetCustomTag"),
		GET_CUSTOM_TAG("GetCustomTag"),
		REMOVE_CUSTOM_TAG("RemoveCustomTag");	

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
	
	public enum EntitySelection {
		CURRENT_SELECTION(0, "Selection"),
		DEFAULT_ENTITY(1, "Default"),
		KILLER(2, "Killer"),
		DAMAGER(3, "Damager"),
		SHOOTER(4, "Shooter"),
		VICTIM(5, "Victim"),
		PROJECTILE(6, "Projectile"),
		ALL_ENTITIES(7, "AllEntities"),
		ALL_MOBS(7, "AllMobs"),
		LAST_MOB_SPAWNED(8, "LastMob"),
		LAST_ENTITY_SPAWNED(8, "LastEntity");

		private final static Map<Integer, EntitySelection> values = new HashMap<Integer, EntitySelection>();

		private final int value;
		private final String jsonValue;

		EntitySelection(final int value, final String jsonValue) {
			this.value = value;
			this.jsonValue = jsonValue;
		}

		static {
			for (EntitySelection type : EntitySelection.values()) {
				values.put(type.getValue(), type);
			}
		}

		public static EntitySelection valueOf(int type) {
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
