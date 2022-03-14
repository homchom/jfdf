package net.jfdf.jfdf.mangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.jfdf.jfdf.blocks.PlayerActionBlock;
import net.jfdf.jfdf.blocks.PlayerActionBlock.PlayerSelection;
import net.jfdf.jfdf.values.*;

public class Player {
	private final PlayerSelection selection;
	
	public Player(PlayerSelection selection) {
		this.selection = selection;
	}
	
	public static Player getCurrentSelection() {return new Player(PlayerSelection.CURRENT_SELECTION);}
	
	public static Player getDefaultPlayer() {return new Player(PlayerSelection.DEFAULT_PLAYER);}
	
	public static Player getKiller() {return new Player(PlayerSelection.KILLER);}
	
	public static Player getDamager() {return new Player(PlayerSelection.DAMAGER);}
	
	public static Player getShooter() {return new Player(PlayerSelection.SHOOTER);}
	
	public static Player getVictim() {return new Player(PlayerSelection.VICTIM);}
	
	public static Player getAllPlayers() {return new Player(PlayerSelection.ALL_PLAYERS);}

	private List<CodeValue> toList(CodeValue[] array) {
		List<CodeValue> list = Arrays.asList(array);
		list.remove(null);
		return list;
	}

	/**
	 * Sets items in a player's<br>
	 * hotbar.
	 *
	 * @param item Item(s) to set
	 */
	public void SetHotbar(IItem... item) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(item));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetHotbar", selection).SetItems(items));
	}

	/**
	 * When enabled, a player won't be<br>
	 * able to see their coordinates,<br>
	 * block info, or other info.
	 */
	public void SetReducedDebug(Tags.ReducedDebugInfoEnabled reducedDebugInfoEnabledTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetReducedDebug", selection).SetItems(items).SetTags(new Tag("Reduced Debug Info Enabled", reducedDebugInfoEnabledTag.getJSONValue())));
	}

	/**
	 * Closes a player's inventory.
	 */
	public void CloseInv() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("CloseInv", selection).SetItems(items));
	}

	/**
	 * Gives a player all of the<br>
	 * items in the chest.
	 *
	 * @param item Item(s) to give
	 * @param amountToGive Amount to give (Optional)
	 */
	public void GiveItems(IItem[] item, INumber amountToGive) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(item));
		if(amountToGive != null) items.add(amountToGive);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("GiveItems", selection).SetItems(items));
	}

	/**
	 * Creates or modifies a custom<br>
	 * boss health bar at the top<br>
	 * of a player's screen.
	 *
	 * @param title Title (Optional)
	 * @param currentHealth Current health (Optional)
	 * @param maximumHealth Maximum health (Optional)
	 */
	public void BossBar(IText title, INumber currentHealth, INumber maximumHealth, Tags.BarSlot barSlotTag, Tags.BarStyle barStyleTag, Tags.SkyEffect skyEffectTag, Tags.BarColor barColorTag) {
		List<CodeValue> items = new ArrayList<>();

		if(title != null) items.add(title);
		if(currentHealth != null) items.add(currentHealth);
		if(maximumHealth != null) items.add(maximumHealth);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("BossBar", selection).SetItems(items).SetTags(new Tag("Bar Slot", barSlotTag.getJSONValue()), new Tag("Bar Style", barStyleTag.getJSONValue()), new Tag("Sky Effect", skyEffectTag.getJSONValue()), new Tag("Bar Color", barColorTag.getJSONValue())));
	}

	/**
	 * Displays a sphere of particles<br>
	 * at a location to a player.
	 *
	 * @param effect Effect
	 * @param centerLocation Center location
	 * @param diameter Diameter (Optional)
	 */
	public void ParticleSphere(IParticle effect, ILocation centerLocation, INumber diameter) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(centerLocation);
		if(diameter != null) items.add(diameter);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleSphere", selection).SetItems(items));
	}

	/**
	 * Sets a player's attack speed.
	 *
	 * @param attackSpeed Attack speed (Optional)
	 */
	public void SetAtkSpeed(INumber attackSpeed) {
		List<CodeValue> items = new ArrayList<>();

		if(attackSpeed != null) items.add(attackSpeed);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetAtkSpeed", selection).SetItems(items));
	}

	/**
	 * Sets a player's movement<br>
	 * velocity.
	 *
	 * @param newVelocity New velocity
	 */
	public void SetVelocity(IVector newVelocity, Tags.AddtoCurrentVelocity addtoCurrentVelocityTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(newVelocity);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetVelocity", selection).SetItems(items).SetTags(new Tag("Add to Current Velocity", addtoCurrentVelocityTag.getJSONValue())));
	}

	/**
	 * Displays a particle effect<br>
	 * to a player.
	 *
	 * @param effect Effect
	 * @param effectLocation Effect location
	 */
	public void Particle(IParticle[] effect, ILocation effectLocation) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(effect));
		items.add(effectLocation);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("Particle", selection).SetItems(items));
	}

	/**
	 * Adds a row to the bottom of<br>
	 * a player's current inventory<br>
	 * menu.
	 *
	 * @param itemsToDisplay Items to display (Optional)
	 */
	public void AddInvRow(IItem[] itemsToDisplay, Tags.NewRowPosition newRowPositionTag) {
		List<CodeValue> items = new ArrayList<>();

		if(itemsToDisplay != null) items.addAll(Arrays.asList(itemsToDisplay));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("AddInvRow", selection).SetItems(items).SetTags(new Tag("New Row Position", newRowPositionTag.getJSONValue())));
	}

	/**
	 * Displays a silent lightning<br>
	 * strike effect to a player.
	 *
	 * @param strikeLocation Strike location
	 */
	public void DisplayLightning(ILocation strikeLocation) {
		List<CodeValue> items = new ArrayList<>();

		items.add(strikeLocation);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("DisplayLightning", selection).SetItems(items));
	}

	/**
	 * Damages a player.
	 *
	 * @param damageToInflict Damage to inflict
	 * @param damageSource Damage source (Optional)
	 */
	public void Damage(INumber damageToInflict, IText damageSource) {
		List<CodeValue> items = new ArrayList<>();

		items.add(damageToInflict);
		if(damageSource != null) items.add(damageSource);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("Damage", selection).SetItems(items));
	}

	/**
	 * Sets a player's experience<br>
	 * level, points or progress.
	 *
	 * @param experienceToSet Experience to set
	 */
	public void SetExp(INumber experienceToSet, Tags.SetExperience setExperienceTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(experienceToSet);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetExp", selection).SetItems(items).SetTags(new Tag("Set Experience", setExperienceTag.getJSONValue())));
	}

	/**
	 * Makes a player perform<br>
	 * an animation.
	 */
	public void SendAnimation(Tags.AnimationType animationTypeTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SendAnimation", selection).SetItems(items).SetTags(new Tag("Animation Type", animationTypeTag.getJSONValue())));
	}

	/**
	 * Sets the XP progress bar<br>
	 * to a certain percentage.
	 *
	 * @param progress Progress % (0-100)
	 */
	public void SetXPProg(INumber progress) {
		List<CodeValue> items = new ArrayList<>();

		items.add(progress);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetXPProg", selection).SetItems(items));
	}

	/**
	 * Sets items in a player's<br>
	 * upper inventory.
	 *
	 * @param item Item(s) to set
	 */
	public void SetInventory(IItem... item) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(item));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetInventory", selection).SetItems(items));
	}

	/**
	 * Teleports a player to multiple<br>
	 * locations, with a delay between<br>
	 * each teleport.
	 *
	 * @param locationsTo Locations to teleport to
	 * @param teleportDelay Teleport delay (ticks, default = 60) (Optional)
	 */
	public void TpSequence(ILocation[] locationsTo, INumber teleportDelay) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(locationsTo));
		if(teleportDelay != null) items.add(teleportDelay);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("TpSequence", selection).SetItems(items));
	}

	/**
	 * Restores a player's health.
	 *
	 * @param amountToHeal Amount to heal
	 */
	public void Heal(INumber amountToHeal) {
		List<CodeValue> items = new ArrayList<>();

		items.add(amountToHeal);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("Heal", selection).SetItems(items));
	}

	/**
	 * Sets the location a player will<br>
	 * spawn when they die and respawn.
	 *
	 * @param theNewSpawnLocation The new spawn location
	 */
	public void SetSpawnPoint(ILocation theNewSpawnLocation) {
		List<CodeValue> items = new ArrayList<>();

		items.add(theNewSpawnLocation);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetSpawnPoint", selection).SetItems(items));
	}

	/**
	 * Displays a spiral of particles<br>
	 * at a location to a player.
	 *
	 * @param effect Effect
	 * @param baseLocation Base location
	 * @param length Length (Optional)
	 * @param diameter Diameter (Optional)
	 * @param effectCount Effect count (Optional)
	 * @param rotations Rotations (Optional)
	 */
	public void ParticleSpiral(IParticle effect, ILocation baseLocation, INumber length, INumber diameter, INumber effectCount, INumber rotations) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(baseLocation);
		if(length != null) items.add(length);
		if(diameter != null) items.add(diameter);
		if(effectCount != null) items.add(effectCount);
		if(rotations != null) items.add(rotations);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleSpiral", selection).SetItems(items));
	}

	/**
	 * Sets whether a player's inventory<br>
	 * is kept after death.
	 */
	public void SetInventoryKept(Tags.InventoryKept inventoryKeptTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetInventoryKept", selection).SetItems(items).SetTags(new Tag("Inventory Kept", inventoryKeptTag.getJSONValue())));
	}

	/**
	 * Sets whether a player<br>
	 * is able to enter and exit<br>
	 * flight mode by double<br>
	 * tapping jump.
	 */
	public void SetAllowFlight(Tags.AllowFlight allowFlightTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetAllowFlight", selection).SetItems(items).SetTags(new Tag("Allow Flight", allowFlightTag.getJSONValue())));
	}

	/**
	 * Launches a player up or down.
	 *
	 * @param launchPower Launch power
	 */
	public void LaunchUp(INumber launchPower, Tags.AddtoCurrentVelocity addtoCurrentVelocityTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(launchPower);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("LaunchUp", selection).SetItems(items).SetTags(new Tag("Add to Current Velocity", addtoCurrentVelocityTag.getJSONValue())));
	}

	/**
	 * Sets a player's maximum<br>
	 * health.
	 *
	 * @param maximumHealth Maximum health
	 */
	public void SetMaxHealth(INumber maximumHealth, Tags.HealPlayertoMaxHealth healPlayertoMaxHealthTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(maximumHealth);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetMaxHealth", selection).SetItems(items).SetTags(new Tag("Heal Player to Max Health", healPlayertoMaxHealthTag.getJSONValue())));
	}

	/**
	 * Removes a boss health bar<br>
	 * from a player's screen.
	 *
	 * @param bossBarPosition Boss bar position
	 */
	public void RemoveBossBar(INumber bossBarPosition) {
		List<CodeValue> items = new ArrayList<>();

		items.add(bossBarPosition);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock(" RemoveBossBar ", selection).SetItems(items));
	}

	/**
	 * Sets a player's game<br>
	 * mode to Adventure.
	 */
	public void AdventureMode() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("AdventureMode", selection).SetItems(items));
	}

	/**
	 * Forces a player to start<br>
	 * or stop flying.
	 */
	public void ForceFlight(Tags.FlightMode flightModeTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ForceFlight", selection).SetItems(items).SetTags(new Tag("Flight Mode", flightModeTag.getJSONValue())));
	}

	/**
	 * Loads a player's inventory.
	 */
	public void LoadInv() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("LoadInv", selection).SetItems(items));
	}

	/**
	 * Sets a player's game<br>
	 * mode to Spectator.
	 */
	public void SpectatorMode() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SpectatorMode", selection).SetItems(items));
	}

	/**
	 * Removes all active potion<br>
	 * effects from a player.
	 */
	public void ClearPotions() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ClearPotions", selection).SetItems(items));
	}

	/**
	 * Kicks a player from<br>
	 * the plot.
	 */
	public void Kick() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("Kick", selection).SetItems(items));
	}

	/**
	 * Sets the text to be displayed<br>
	 * above or below a player's player<br>
	 * list shown when pressing Tab.
	 *
	 * @param headerfooterText Header/footer text (Optional)
	 */
	public void SetTabListInfo(IText[] headerfooterText, Tags.PlayerListField playerListFieldTag) {
		List<CodeValue> items = new ArrayList<>();

		if(headerfooterText != null) items.addAll(Arrays.asList(headerfooterText));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetTabListInfo", selection).SetItems(items).SetTags(new Tag("Player List Field", playerListFieldTag.getJSONValue())));
	}

	/**
	 * Makes a player spectate<br>
	 * another player or entity.
	 *
	 * @param targetUuid Target UUID
	 */
	public void SpectateTarget(IText targetUuid) {
		List<CodeValue> items = new ArrayList<>();

		items.add(targetUuid);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SpectateTarget", selection).SetItems(items));
	}

	/**
	 * Sets a player's game<br>
	 * mode to Survival.
	 */
	public void SurvivalMode() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SurvivalMode", selection).SetItems(items));
	}

	/**
	 * Creates or modifies a custom boss<br>
	 * health bar at the top of a player's<br>
	 * screen.
	 *
	 * @param title Title (Optional)
	 * @param currentHealth Current health (Optional)
	 * @param maximumHealth Maximum health (Optional)
	 * @param bossBarPosition Boss bar position (Optional)
	 */
	public void SetBossBar(IText title, INumber currentHealth, INumber maximumHealth, INumber bossBarPosition, Tags.BarStyle barStyleTag, Tags.SkyEffect skyEffectTag, Tags.BarColor barColorTag) {
		List<CodeValue> items = new ArrayList<>();

		if(title != null) items.add(title);
		if(currentHealth != null) items.add(currentHealth);
		if(maximumHealth != null) items.add(maximumHealth);
		if(bossBarPosition != null) items.add(bossBarPosition);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock(" SetBossBar ", selection).SetItems(items).SetTags(new Tag("Bar Style", barStyleTag.getJSONValue()), new Tag("Sky Effect", skyEffectTag.getJSONValue()), new Tag("Bar Color", barColorTag.getJSONValue())));
	}

	/**
	 * Sets the player's skin.
	 *
	 * @param playerHead Player head
	 */
	public void SetSkin(IItem playerHead) {
		List<CodeValue> items = new ArrayList<>();

		items.add(playerHead);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetSkin", selection).SetItems(items));
	}

	/**
	 * Sets the player's game status,<br>
	 * which is used to display information<br>
	 * about what the player is doing<br>
	 * in the game.
	 *
	 * @param gameStatus Game Status
	 */
	public void SetStatus(IText gameStatus) {
		List<CodeValue> items = new ArrayList<>();

		items.add(gameStatus);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetStatus", selection).SetItems(items));
	}

	/**
	 * Sets the currently<br>
	 * remaining ticks until a<br>
	 * player can next be hurt.
	 *
	 * @param ticks Ticks
	 */
	public void SetInvulTicks(INumber ticks) {
		List<CodeValue> items = new ArrayList<>();

		items.add(ticks);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetInvulTicks", selection).SetItems(items));
	}

	/**
	 * Sets the item on a<br>
	 * player's cursor.
	 *
	 * @param itemToSet Item to set (Optional)
	 */
	public void SetCursorItem(IItem itemToSet) {
		List<CodeValue> items = new ArrayList<>();

		if(itemToSet != null) items.add(itemToSet);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetCursorItem", selection).SetItems(items));
	}

	/**
	 * Sets a player's absorption<br>
	 * health (golden hearts).
	 *
	 * @param absorptionHealth Absorption health
	 */
	public void SetAbsorption(INumber absorptionHealth) {
		List<CodeValue> items = new ArrayList<>();

		items.add(absorptionHealth);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetAbsorption", selection).SetItems(items));
	}

	/**
	 * Sets the remaining time a<br>
	 * player is on fire for.
	 *
	 * @param ticks Ticks
	 */
	public void SetFireTicks(INumber ticks) {
		List<CodeValue> items = new ArrayList<>();

		items.add(ticks);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetFireTicks", selection).SetItems(items));
	}

	/**
	 * Removes the given number of<br>
	 * rows from the bottom of a player's<br>
	 * current inventory menu.
	 *
	 * @param rowsToRemove Rows to remove (Optional)
	 */
	public void RemoveInvRow(INumber rowsToRemove, Tags.RowtoRemove rowtoRemoveTag) {
		List<CodeValue> items = new ArrayList<>();

		if(rowsToRemove != null) items.add(rowsToRemove);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("RemoveInvRow", selection).SetItems(items).SetTags(new Tag("Row to Remove", rowtoRemoveTag.getJSONValue())));
	}

	/**
	 * Prevents a player from placing<br>
	 * and breaking certain blocks.
	 *
	 * @param blocksToDisallow Blocks to disallow (Optional)
	 */
	public void DisableBlocks(CodeValue... blocksToDisallow) {
		List<CodeValue> items = new ArrayList<>();

		if(blocksToDisallow != null) items.addAll(Arrays.asList(blocksToDisallow));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("DisableBlocks", selection).SetItems(items));
	}

	/**
	 * Empties a player's inventory.
	 */
	public void ClearInv(Tags.ClearMode clearModeTag, Tags.ClearCraftingAndCursor clearCraftingAndCursorTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ClearInv", selection).SetItems(items).SetTags(new Tag("Clear Mode", clearModeTag.getJSONValue()), new Tag("Clear Crafting and Cursor", clearCraftingAndCursorTag.getJSONValue())));
	}

	/**
	 * Sets the player's current freeze ticks
	 *
	 * @param ticks Ticks
	 */
	public void SetFreezeTicks(INumber ticks) {
		List<CodeValue> items = new ArrayList<>();

		items.add(ticks);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetFreezeTicks", selection).SetItems(items));
	}

	/**
	 * Sets whether a player<br>
	 * is gliding with elytra.
	 */
	public void SetGliding(Tags.Gliding glidingTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetGliding", selection).SetItems(items).SetTags(new Tag("Gliding", glidingTag.getJSONValue())));
	}

	/**
	 * Changes a player's pitch and<br>
	 * yaw.
	 *
	 * @param pitch Pitch (-90 to 90)
	 * @param yaw Yaw (-180 to 180)
	 */
	public void SetRotation(INumber pitch, INumber yaw) {
		List<CodeValue> items = new ArrayList<>();

		items.add(pitch);
		items.add(yaw);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetRotation", selection).SetItems(items));
	}

	/**
	 * Displays a circle of particles<br>
	 * to a player.
	 *
	 * @param effect Effect
	 * @param centerLocation Center location
	 * @param diameter Diameter (Optional)
	 */
	public void ParticleCircle(IParticle effect, ILocation centerLocation, INumber diameter) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(centerLocation);
		if(diameter != null) items.add(diameter);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleCircle", selection).SetItems(items));
	}

	/**
	 * Removes all of an item from<br>
	 * a player.
	 *
	 * @param item Item(s) to clear
	 */
	public void ClearItems(IItem... item) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(item));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ClearItems", selection).SetItems(items));
	}

	/**
	 * Displays a block at a location to<br>
	 * a player.
	 *
	 * @param blockToDisplay Block to display
	 * @param blockLocation Block location, or start of region
	 * @param endOfRegion End of region (Optional)
	 * @param blockData Block data (Optional)
	 */
	public void DisplayBlock(CodeValue blockToDisplay, ILocation blockLocation, ILocation endOfRegion, IText... blockData) {
		List<CodeValue> items = new ArrayList<>();

		items.add(blockToDisplay);
		items.add(blockLocation);
		if(endOfRegion != null) items.add(endOfRegion);
		if(blockData != null) items.addAll(Arrays.asList(blockData));

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("DisplayBlock", selection).SetItems(items));
	}

	/**
	 * Mounts a player on top of<br>
	 * another player or entity.
	 *
	 * @param nameOrUuidOf Name or UUID of target to ride
	 */
	public void RideEntity(IText nameOrUuidOf) {
		List<CodeValue> items = new ArrayList<>();

		items.add(nameOrUuidOf);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("RideEntity", selection).SetItems(items));
	}

	/**
	 * Sets whether a player<br>
	 * is flying.
	 */
	public void SetFlying(Tags.Flying flyingTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetFlying", selection).SetItems(items).SetTags(new Tag("Flying", flyingTag.getJSONValue())));
	}

	/**
	 * Removes a player's world border.
	 */
	public void RmWorldBorder() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("RmWorldBorder", selection).SetItems(items));
	}

	/**
	 * Displays a container block<br>
	 * at a location as being open<br>
	 * or closed to a player.
	 *
	 * @param blockLocation Block location
	 */
	public void DisplayBlockOpen(ILocation blockLocation, Tags.ContainerState containerStateTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(blockLocation);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("DisplayBlockOpen", selection).SetItems(items).SetTags(new Tag("Container State", containerStateTag.getJSONValue())));
	}

	/**
	 * Displays a custom advancement<br>
	 * popup to a player.
	 *
	 * @param advancementName Advancement name
	 * @param advancementIcon Advancement icon
	 */
	public void SendAdvancement(IText advancementName, IItem advancementIcon, Tags.ToastType toastTypeTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(advancementName);
		items.add(advancementIcon);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SendAdvancement", selection).SetItems(items).SetTags(new Tag("Toast Type", toastTypeTag.getJSONValue())));
	}

	/**
	 * Teleports a player to<br>
	 * a location.
	 *
	 * @param newPosition New position
	 */
	public void Teleport(ILocation newPosition, Tags.KeepCurrentRotation keepCurrentRotationTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(newPosition);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("Teleport", selection).SetItems(items).SetTags(new Tag("Keep Current Rotation", keepCurrentRotationTag.getJSONValue())));
	}

	/**
	 * Sets whether a player can<br>
	 * hurt or be hurt by other<br>
	 * players.
	 */
	public void SetAllowPVP(Tags.PVP pVPTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetAllowPVP", selection).SetItems(items).SetTags(new Tag("PVP", pVPTag.getJSONValue())));
	}

	/**
	 * Sets the item in a slot<br>
	 * of a player's current<br>
	 * inventory menu.
	 *
	 * @param slot Slot
	 * @param itemToSet Item to set (Optional)
	 */
	public void SetMenuItem(INumber slot, IItem itemToSet) {
		List<CodeValue> items = new ArrayList<>();

		items.add(slot);
		if(itemToSet != null) items.add(itemToSet);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetMenuItem", selection).SetItems(items));
	}

	/**
	 * Launches a player toward or away<br>
	 * from a location.
	 *
	 * @param launchDestination Launch destination
	 * @param launchPower Launch power (Optional)
	 */
	public void LaunchToward(ILocation launchDestination, INumber launchPower, Tags.AddtoCurrentVelocity addtoCurrentVelocityTag, Tags.IgnoreDistance ignoreDistanceTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(launchDestination);
		if(launchPower != null) items.add(launchPower);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("LaunchToward", selection).SetItems(items).SetTags(new Tag("Add to Current Velocity", addtoCurrentVelocityTag.getJSONValue()), new Tag("Ignore Distance", ignoreDistanceTag.getJSONValue())));
	}

	/**
	 * Sets a player's armor items.<br>
	 * Place the armor in slots 1-4<br>
	 * of the chest, with 1 being the<br>
	 * helmet and 4 being the boots.
	 *
	 * @param armorToSet Armor to set
	 */
	public void SetArmor(IItem... armorToSet) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(armorToSet));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetArmor", selection).SetItems(items));
	}

	/**
	 * Displays a vertical beam on<br>
	 * an end gateway to a player.
	 *
	 * @param gatewayLocation Gateway location
	 */
	public void DisplayGateway(ILocation gatewayLocation, Tags.AnimationType animationTypeTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(gatewayLocation);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("DisplayGateway", selection).SetItems(items).SetTags(new Tag("Animation Type", animationTypeTag.getJSONValue())));
	}

	/**
	 * Sets a player's ability to<br>
	 * see their own disguise. It<br>
	 * is recommended that it is<br>
	 * almost always hidden.
	 */
	public void SetDisguiseVisible(Tags.DisguiseVisible disguiseVisibleTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetDisguiseVisible", selection).SetItems(items).SetTags(new Tag("Disguise Visible", disguiseVisibleTag.getJSONValue())));
	}

	/**
	 * Sets the amount of arrows<br>
	 * sticking out of a player's<br>
	 * character model.
	 *
	 * @param arrowCount Arrow Count (Optional)
	 */
	public void SetArrowsStuck(INumber arrowCount) {
		List<CodeValue> items = new ArrayList<>();

		if(arrowCount != null) items.add(arrowCount);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetArrowsStuck", selection).SetItems(items));
	}

	/**
	 * Adds experience points or<br>
	 * levels to a player.
	 *
	 * @param experienceToGive Experience to give
	 */
	public void GiveExp(INumber experienceToGive, Tags.GiveExperience giveExperienceTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(experienceToGive);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("GiveExp", selection).SetItems(items).SetTags(new Tag("Give Experience", giveExperienceTag.getJSONValue())));
	}

	/**
	 * Rotates a player to look<br>
	 * toward a location without<br>
	 * teleporting them.
	 *
	 * @param locationToFace Location to face
	 */
	public void FaceLocation(ILocation locationToFace) {
		List<CodeValue> items = new ArrayList<>();

		items.add(locationToFace);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("FaceLocation", selection).SetItems(items));
	}

	/**
	 * Replaces items in a player's<br>
	 * inventory with the given item.
	 *
	 * @param item Item(s) to replace (Optional)
	 * @param itemToReplaceWith Item to replace with
	 * @param amountOfItemsTo Amount of items to replace (Optional)
	 */
	public void ReplaceItems(IItem[] item, IItem itemToReplaceWith, INumber amountOfItemsTo) {
		List<CodeValue> items = new ArrayList<>();

		if(item != null) items.addAll(Arrays.asList(item));
		items.add(itemToReplaceWith);
		if(amountOfItemsTo != null) items.add(amountOfItemsTo);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ReplaceItems", selection).SetItems(items));
	}

	/**
	 * Displays text directly above<br>
	 * a player's hotbar.
	 *
	 * @param messageToSend Message to send
	 */
	public void ActionBar(IText[] messageToSend, Tags.TextValueMerging textValueMergingTag) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(messageToSend));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ActionBar", selection).SetItems(items).SetTags(new Tag("Text Value Merging", textValueMergingTag.getJSONValue())));
	}

	/**
	 * Sets a player's chat tag.
	 *
	 * @param chatTag Chat tag
	 */
	public void SetChatTag(IText[] chatTag) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(chatTag));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetChatTag", selection).SetItems(items));
	}

	/**
	 * Sends a chat message to a<br>
	 * player.
	 *
	 * @param messageToSend Message to send (Optional)
	 */
	public void SendMessage(CodeValue[] messageToSend, Tags.AlignmentMode alignmentModeTag, Tags.TextValueMerging textValueMergingTag) {
		List<CodeValue> items = new ArrayList<>();

		if(messageToSend != null) items.addAll(Arrays.asList(messageToSend));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SendMessage", selection).SetItems(items).SetTags(new Tag("Alignment Mode", alignmentModeTag.getJSONValue()), new Tag("Text Value Merging", textValueMergingTag.getJSONValue())));
	}

	/**
	 * Changes a player's world border<br>
	 * size if they have one active.
	 *
	 * @param newRadius New radius
	 * @param blocksPerSecond Blocks per second (Optional)
	 */
	public void ShiftWorldBorder(INumber newRadius, INumber blocksPerSecond) {
		List<CodeValue> items = new ArrayList<>();

		items.add(newRadius);
		if(blocksPerSecond != null) items.add(blocksPerSecond);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ShiftWorldBorder", selection).SetItems(items));
	}

	/**
	 * Sets the item in a slot<br>
	 * of a player's inventory.
	 *
	 * @param itemToSet Item to set (Optional)
	 * @param slotToSet Slot to set
	 */
	public void SetSlotItem(IItem itemToSet, INumber slotToSet) {
		List<CodeValue> items = new ArrayList<>();

		if(itemToSet != null) items.add(itemToSet);
		items.add(slotToSet);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetSlotItem", selection).SetItems(items));
	}

	/**
	 * Plays a sequence of sounds<br>
	 * to a player, with a delay<br>
	 * between each sound.
	 *
	 * @param soundsToPlay Sounds to play
	 * @param soundDelay Sound delay (ticks, default = 60) (Optional)
	 * @param playbackLocation Playback location (Optional)
	 */
	public void PlaySoundSeq(ISound[] soundsToPlay, INumber soundDelay, ILocation playbackLocation) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(soundsToPlay));
		if(soundDelay != null) items.add(soundDelay);
		if(playbackLocation != null) items.add(playbackLocation);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("PlaySoundSeq", selection).SetItems(items));
	}

	/**
	 * Displays an animated line of<br>
	 * particles between two locations<br>
	 * to a player.
	 *
	 * @param effect Effect
	 * @param startLocation Start location
	 * @param endLocation End location
	 * @param effectSpacing Effect spacing (Optional)
	 * @param animationDuration Animation duration (Optional)
	 */
	public void ParticleLineA(IParticle effect, ILocation startLocation, ILocation endLocation, INumber effectSpacing, INumber animationDuration) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(startLocation);
		items.add(endLocation);
		if(effectSpacing != null) items.add(effectSpacing);
		if(animationDuration != null) items.add(animationDuration);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleLineA", selection).SetItems(items));
	}

	/**
	 * Displays text on a sign<br>
	 * to a player.
	 *
	 * @param signLocation Sign location
	 * @param textLine Text line(s) (Optional)
	 */
	public void DisplaySignText(ILocation signLocation, IText[] textLine, Tags.TextColor textColorTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(signLocation);
		if(textLine != null) items.addAll(Arrays.asList(textLine));

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("DisplaySignText", selection).SetItems(items).SetTags(new Tag("Text Color", textColorTag.getJSONValue())));
	}

	/**
	 * Sets a player's walking<br>
	 * and/or flight speed.
	 *
	 * @param movementSpeed Movement speed percentage (0% to 1000%)
	 */
	public void SetSpeed(INumber movementSpeed, Tags.SpeedType speedTypeTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(movementSpeed);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetSpeed", selection).SetItems(items).SetTags(new Tag("Speed Type", speedTypeTag.getJSONValue())));
	}

	/**
	 * Adds 3 more rows to a player's<br>
	 * current inventory menu using the<br>
	 * contents of the chest.
	 *
	 * @param itemsToDisplay Items to display (Optional)
	 */
	public void ExpandInv(IItem... itemsToDisplay) {
		List<CodeValue> items = new ArrayList<>();

		if(itemsToDisplay != null) items.addAll(Arrays.asList(itemsToDisplay));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ExpandInv", selection).SetItems(items));
	}

	/**
	 * Renames a player's current<br>
	 * inventory menu.
	 *
	 * @param inventoryName Inventory name
	 */
	public void SetInvName(IText inventoryName) {
		List<CodeValue> items = new ArrayList<>();

		items.add(inventoryName);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetInvName", selection).SetItems(items));
	}

	/**
	 * Launches a projectile from<br>
	 * a player.
	 *
	 * @param projectileTo Projectile to launch
	 * @param launchPoint Launch point (Optional)
	 * @param projectileName Projectile name (Optional)
	 * @param speed Speed (Optional)
	 * @param inaccuracy Inaccuracy (Optional)
	 */
	public void LaunchProj(IItem projectileTo, ILocation launchPoint, IText projectileName, INumber speed, INumber inaccuracy) {
		List<CodeValue> items = new ArrayList<>();

		items.add(projectileTo);
		if(launchPoint != null) items.add(launchPoint);
		if(projectileName != null) items.add(projectileName);
		if(speed != null) items.add(speed);
		if(inaccuracy != null) items.add(inaccuracy);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("LaunchProj", selection).SetItems(items));
	}

	/**
	 * Applies a cooldown visual effect<br>
	 * to an item type.
	 *
	 * @param itemTypeToAffect Item type to affect
	 * @param cooldownInTicks Cooldown in ticks
	 */
	public void SetItemCooldown(IItem itemTypeToAffect, INumber cooldownInTicks) {
		List<CodeValue> items = new ArrayList<>();

		items.add(itemTypeToAffect);
		items.add(cooldownInTicks);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetItemCooldown", selection).SetItems(items));
	}

	/**
	 * Sets the type of weather<br>
	 * visible to a player.
	 */
	public void SetPlayerWeather(Tags.Weather weatherTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetPlayerWeather", selection).SetItems(items).SetTags(new Tag("Weather", weatherTag.getJSONValue())));
	}

	/**
	 * Sends a message to a player.<br>
	 * When the player moves over<br>
	 * it with their cursor, a second<br>
	 * message appears.
	 *
	 * @param messageToSendInChat Message to send in chat
	 * @param messageToSeeOnHover Message to see on hover
	 */
	public void SendHover(IText messageToSendInChat, IText messageToSeeOnHover) {
		List<CodeValue> items = new ArrayList<>();

		items.add(messageToSendInChat);
		items.add(messageToSeeOnHover);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SendHover", selection).SetItems(items));
	}

	/**
	 * Sets a player's remaining<br>
	 * breath ticks.
	 *
	 * @param breathTicks Breath ticks
	 */
	public void SetAirTicks(INumber breathTicks) {
		List<CodeValue> items = new ArrayList<>();

		items.add(breathTicks);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetAirTicks", selection).SetItems(items));
	}

	/**
	 * Displays a pickup animation<br>
	 * of one entity being collected<br>
	 * by another entity.
	 *
	 * @param entityUuid Entity UUID
	 * @param collectorUuid Collector UUID
	 */
	public void DisplayPickup(IText entityUuid, IText collectorUuid) {
		List<CodeValue> items = new ArrayList<>();

		items.add(entityUuid);
		items.add(collectorUuid);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("DisplayPickup", selection).SetItems(items));
	}

	/**
	 * Displays an animated particle<br>
	 * cuboid to a player.
	 *
	 * @param effect Effect
	 * @param corner1 Corner 1
	 * @param corner2 Corner 2
	 * @param effectSpacing Effect spacing (Optional)
	 * @param animationDuration Animation duration (Optional)
	 */
	public void ParticleCuboidA(IParticle effect, ILocation corner1, ILocation corner2, INumber effectSpacing, INumber animationDuration, Tags.FillType fillTypeTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(corner1);
		items.add(corner2);
		if(effectSpacing != null) items.add(effectSpacing);
		if(animationDuration != null) items.add(animationDuration);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleCuboidA", selection).SetItems(items).SetTags(new Tag("Fill Type", fillTypeTag.getJSONValue())));
	}

	/**
	 * Sets a player's chat color.
	 *
	 * @param newChatColor New chat color
	 */
	public void ChatColor(IText newChatColor) {
		List<CodeValue> items = new ArrayList<>();

		items.add(newChatColor);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ChatColor", selection).SetItems(items));
	}

	/**
	 * Creates a world border only<br>
	 * visible to a player.
	 *
	 * @param centerPosition Center position
	 * @param radiusInBlocks Radius in blocks
	 * @param warningDistance Warning distance (Optional)
	 */
	public void SetWorldBorder(ILocation centerPosition, INumber radiusInBlocks, INumber warningDistance) {
		List<CodeValue> items = new ArrayList<>();

		items.add(centerPosition);
		items.add(radiusInBlocks);
		if(warningDistance != null) items.add(warningDistance);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetWorldBorder", selection).SetItems(items));
	}

	/**
	 * Sets the time of day visible<br>
	 * to a player.
	 *
	 * @param daylightTicks Daylight ticks)
	 */
	public void SetPlayerTime(INumber daylightTicks) {
		List<CodeValue> items = new ArrayList<>();

		items.add(daylightTicks);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetPlayerTime", selection).SetItems(items));
	}

	/**
	 * Gives one or more potion<br>
	 * effects to a player.
	 *
	 * @param effect Effect(s) to give
	 */
	public void GivePotion(IPotion[] effect, Tags.ShowIcon showIconTag, Tags.OverwriteEffect overwriteEffectTag, Tags.EffectParticles effectParticlesTag) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(effect));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("GivePotion", selection).SetItems(items).SetTags(new Tag("Show Icon", showIconTag.getJSONValue()), new Tag("Overwrite Effect", overwriteEffectTag.getJSONValue()), new Tag("Effect Particles", effectParticlesTag.getJSONValue())));
	}

	/**
	 * Plays a sound for a player.
	 *
	 * @param soundToPlay Sound to play
	 * @param playbackLocation Playback location (Optional)
	 */
	public void PlaySound(ISound[] soundToPlay, ILocation playbackLocation, Tags.SoundSource soundSourceTag) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(soundToPlay));
		if(playbackLocation != null) items.add(playbackLocation);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("PlaySound", selection).SetItems(items).SetTags(new Tag("Sound Source", soundSourceTag.getJSONValue())));
	}

	/**
	 * Sets the location compasses<br>
	 * point to for a player.
	 *
	 * @param newTarget New Target
	 */
	public void SetCompass(ILocation newTarget) {
		List<CodeValue> items = new ArrayList<>();

		items.add(newTarget);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetCompass", selection).SetItems(items));
	}

	/**
	 * Removes items from a player.
	 *
	 * @param item Item(s) to remove
	 */
	public void RemoveItems(IItem... item) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(item));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("RemoveItems", selection).SetItems(items));
	}

	/**
	 * Teleports a player to a random<br>
	 * location in the chest.
	 *
	 * @param locationsTo Locations to choose from
	 */
	public void RngTeleport(ILocation[] locationsTo, Tags.KeepCurrentRotation keepCurrentRotationTag) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(locationsTo));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("RngTeleport", selection).SetItems(items).SetTags(new Tag("Keep Current Rotation", keepCurrentRotationTag.getJSONValue())));
	}

	/**
	 * Boosts a player's elytra<br>
	 * using a firework rocket.
	 *
	 * @param firework Firework
	 */
	public void BoostElytra(IItem firework) {
		List<CodeValue> items = new ArrayList<>();

		items.add(firework);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("BoostElytra", selection).SetItems(items));
	}

	/**
	 * Saves a player's inventory.<br>
	 * It can be loaded later with<br>
	 * 'Load Saved Inventory'.
	 */
	public void SaveInv() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SaveInv", selection).SetItems(items));
	}

	/**
	 * Opens a written book<br>
	 * menu for a player.
	 *
	 * @param bookItem Book item
	 */
	public void OpenBook(IItem bookItem) {
		List<CodeValue> items = new ArrayList<>();

		items.add(bookItem);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("OpenBook", selection).SetItems(items));
	}

	/**
	 * Sets a player's current<br>
	 * health.
	 *
	 * @param currentHealth Current health
	 */
	public void SetHealth(INumber currentHealth) {
		List<CodeValue> items = new ArrayList<>();

		items.add(currentHealth);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetHealth", selection).SetItems(items));
	}

	/**
	 * Disguises a player as a mob.
	 *
	 * @param mobDisguise Mob disguise
	 * @param disguiseName Disguise name (Optional)
	 */
	public void MobDisguise(IItem mobDisguise, IText disguiseName) {
		List<CodeValue> items = new ArrayList<>();

		items.add(mobDisguise);
		if(disguiseName != null) items.add(disguiseName);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("MobDisguise", selection).SetItems(items));
	}

	/**
	 * Disguises a player as a block.
	 *
	 * @param blockDisguise Block disguise
	 * @param nameOfDisguise Name of disguise (Optional)
	 */
	public void BlockDisguise(CodeValue blockDisguise, IText nameOfDisguise) {
		List<CodeValue> items = new ArrayList<>();

		items.add(blockDisguise);
		if(nameOfDisguise != null) items.add(nameOfDisguise);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("BlockDisguise", selection).SetItems(items));
	}

	/**
	 * Allows a player to place<br>
	 * and break certain blocks.
	 *
	 * @param blocksToAllow Blocks to allow (Optional)
	 */
	public void EnableBlocks(CodeValue... blocksToAllow) {
		List<CodeValue> items = new ArrayList<>();

		if(blocksToAllow != null) items.addAll(Arrays.asList(blocksToAllow));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("EnableBlocks", selection).SetItems(items));
	}

	/**
	 * Sets a player's walk<br>
	 * speed.
	 *
	 * @param ofNormal % of normal walk speed (0 to 500)
	 */
	public void WalkSpeed(INumber ofNormal) {
		List<CodeValue> items = new ArrayList<>();

		items.add(ofNormal);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("WalkSpeed", selection).SetItems(items));
	}

	/**
	 * Opens a container's inventory.<br>
	 * Also works with crafting tables.
	 *
	 * @param containerLocation Container location
	 */
	public void OpenBlockInv(ILocation containerLocation) {
		List<CodeValue> items = new ArrayList<>();

		items.add(containerLocation);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("OpenBlockInv", selection).SetItems(items));
	}

	/**
	 * Sets whether a player is<br>
	 * able to collide with other<br>
	 * entities.
	 */
	public void SetCollidable(Tags.Collision collisionTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetCollidable", selection).SetItems(items).SetTags(new Tag("Collision", collisionTag.getJSONValue())));
	}

	/**
	 * Launches a player forward<br>
	 * or backward.
	 *
	 * @param launchPower Launch power
	 */
	public void LaunchFwd(INumber launchPower, Tags.AddtoCurrentVelocity addtoCurrentVelocityTag, Tags.LaunchAxis launchAxisTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(launchPower);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("LaunchFwd", selection).SetItems(items).SetTags(new Tag("Add to Current Velocity", addtoCurrentVelocityTag.getJSONValue()), new Tag("Launch Axis", launchAxisTag.getJSONValue())));
	}

	/**
	 * Displays an animated circle<br>
	 * of particles to a player.
	 *
	 * @param effect Effect
	 * @param centerLocation Center location
	 * @param diameter Diameter (Optional)
	 * @param animationDuration Animation duration (Optional)
	 */
	public void ParticleCircleA(IParticle effect, ILocation centerLocation, INumber diameter, INumber animationDuration) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(centerLocation);
		if(diameter != null) items.add(diameter);
		if(animationDuration != null) items.add(animationDuration);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleCircleA", selection).SetItems(items));
	}

	/**
	 * Sets a player's fall distance,<br>
	 * affecting fall damage upon<br>
	 * landing.
	 *
	 * @param fallDistance Fall distance (blocks)
	 */
	public void SetFallDistance(INumber fallDistance) {
		List<CodeValue> items = new ArrayList<>();

		items.add(fallDistance);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetFallDistance", selection).SetItems(items));
	}

	/**
	 * Sets a player's game<br>
	 * mode to Creative.
	 */
	public void CreativeMode() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("CreativeMode", selection).SetItems(items));
	}

	/**
	 * Sets the item in one of the<br>
	 * equipment slots (armor and<br>
	 * held items) of a player.
	 *
	 * @param itemToSet Item to set (Optional)
	 */
	public void SetEquipment(IItem itemToSet, Tags.EquipmentSlot equipmentSlotTag) {
		List<CodeValue> items = new ArrayList<>();

		if(itemToSet != null) items.add(itemToSet);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetEquipment", selection).SetItems(items).SetTags(new Tag("Equipment Slot", equipmentSlotTag.getJSONValue())));
	}

	/**
	 * Makes a player perform<br>
	 * an attack animation.
	 */
	public void AttackAnimation(Tags.AnimationArm animationArmTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("AttackAnimation", selection).SetItems(items).SetTags(new Tag("Animation Arm", animationArmTag.getJSONValue())));
	}

	/**
	 * Sets whether a player drops<br>
	 * their items when dead.
	 */
	public void SetDropsEnabled(Tags.SpawnDeathDrops spawnDeathDropsTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetDropsEnabled", selection).SetItems(items).SetTags(new Tag("Spawn Death Drops", spawnDeathDropsTag.getJSONValue())));
	}

	/**
	 * Removes one or more potion<br>
	 * effects from a player.
	 *
	 * @param effect Effect(s) to remove
	 */
	public void RemovePotion(IPotion... effect) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(effect));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("RemovePotion", selection).SetItems(items));
	}

	/**
	 * Displays a floating name tag<br>
	 * at a location to a player.
	 *
	 * @param displayLocation Display location
	 * @param textToDisplay Text to display
	 */
	public void DisplayHologram(ILocation displayLocation, IText textToDisplay) {
		List<CodeValue> items = new ArrayList<>();

		items.add(displayLocation);
		items.add(textToDisplay);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("DisplayHologram", selection).SetItems(items));
	}

	/**
	 * Displays a block fracture<br>
	 * effect at a location to a<br>
	 * player.
	 *
	 * @param block Block(s) to fracture
	 * @param fractureLevel Fracture level (Optional)
	 */
	public void DisplayFracture(ILocation[] block, INumber fractureLevel, Tags.OverwritePreviousFracture overwritePreviousFractureTag) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(block));
		if(fractureLevel != null) items.add(fractureLevel);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("DisplayFracture", selection).SetItems(items).SetTags(new Tag("Overwrite Previous Fracture", overwritePreviousFractureTag.getJSONValue())));
	}

	/**
	 * Sets if an entity is hidden<br>
	 * to a target.
	 *
	 * @param entityUuid Entity UUID
	 */
	public void SetEntityHidden(IText entityUuid, Tags.Hidden hiddenTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(entityUuid);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetEntityHidden", selection).SetItems(items).SetTags(new Tag("Hidden", hiddenTag.getJSONValue())));
	}

	/**
	 * Opens a custom inventory<br>
	 * for a player.
	 *
	 * @param itemsToDisplay Items to display (Optional)
	 */
	public void ShowInv(IItem... itemsToDisplay) {
		List<CodeValue> items = new ArrayList<>();

		if(itemsToDisplay != null) items.addAll(Arrays.asList(itemsToDisplay));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ShowInv", selection).SetItems(items));
	}

	/**
	 * Sets a player's selected<br>
	 * hotbar slot.
	 *
	 * @param newSlot New slot
	 */
	public void SetSlot(INumber newSlot) {
		List<CodeValue> items = new ArrayList<>();

		items.add(newSlot);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetSlot", selection).SetItems(items));
	}

	/**
	 * Displays a ray of particles<br>
	 * to a player.
	 *
	 * @param effect Effect
	 * @param rayLocation Ray location
	 * @param rayVector Ray vector
	 * @param effectSpacing Effect spacing (Optional)
	 */
	public void ParticleRay(IParticle effect, ILocation rayLocation, IVector rayVector, INumber effectSpacing) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(rayLocation);
		items.add(rayVector);
		if(effectSpacing != null) items.add(effectSpacing);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleRay", selection).SetItems(items));
	}

	/**
	 * Sets a player's food hunger level.
	 *
	 * @param foodLevel Food level (0-20)
	 */
	public void SetFoodLevel(INumber foodLevel) {
		List<CodeValue> items = new ArrayList<>();

		items.add(foodLevel);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetFoodLevel", selection).SetItems(items));
	}

	/**
	 * Disguises a player as another<br>
	 * player.
	 *
	 * @param disguisePlayerName Disguise player name
	 * @param disguiseSkin Disguise skin (Optional)
	 */
	public void PlayerDisguise(IText disguisePlayerName, IItem disguiseSkin) {
		List<CodeValue> items = new ArrayList<>();

		items.add(disguisePlayerName);
		if(disguiseSkin != null) items.add(disguiseSkin);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("PlayerDisguise", selection).SetItems(items));
	}

	/**
	 * Displays a particle cuboid as a<br>
	 * solid, hollow or wireframe<br>
	 * shape to a player.
	 *
	 * @param effect Effect
	 * @param corner1 Corner 1
	 * @param corner2 Corner 2
	 * @param effectSpacing Effect spacing (Optional)
	 */
	public void ParticleCuboid(IParticle effect, ILocation corner1, ILocation corner2, INumber effectSpacing, Tags.FillType fillTypeTag) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(corner1);
		items.add(corner2);
		if(effectSpacing != null) items.add(effectSpacing);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleCuboid", selection).SetItems(items).SetTags(new Tag("Fill Type", fillTypeTag.getJSONValue())));
	}

	/**
	 * Sends a series of messages<br>
	 * in chat to a player, with a<br>
	 * delay after each message.
	 *
	 * @param messagesToSend Messages to send
	 * @param messageDelayTicks Message delay ticks (Optional)
	 */
	public void SendMessageSeq(IText[] messagesToSend, INumber messageDelayTicks) {
		List<CodeValue> items = new ArrayList<>();

		items.addAll(Arrays.asList(messagesToSend));
		if(messageDelayTicks != null) items.add(messageDelayTicks);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SendMessageSeq", selection).SetItems(items));
	}

	/**
	 * Sets a player's saturation<br>
	 * level.
	 *
	 * @param saturationLevel Saturation level (1-20)
	 */
	public void SetSaturation(INumber saturationLevel) {
		List<CodeValue> items = new ArrayList<>();

		items.add(saturationLevel);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetSaturation", selection).SetItems(items));
	}

	/**
	 * Removes a player's disguise.
	 */
	public void Undisguise() {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("Undisguise", selection).SetItems(items));
	}

	/**
	 * Displays an animated spiral of<br>
	 * particles to a player.
	 *
	 * @param effect Effect
	 * @param baseLocation Base location
	 * @param length Length (Optional)
	 * @param diameter Diameter (Optional)
	 * @param particleCount Particle count (Optional)
	 * @param rotations Rotations (Optional)
	 * @param animationDuration Animation duration (Optional)
	 */
	public void ParticleSpiralA(IParticle effect, ILocation baseLocation, INumber length, INumber diameter, INumber particleCount, INumber rotations, INumber animationDuration) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(baseLocation);
		if(length != null) items.add(length);
		if(diameter != null) items.add(diameter);
		if(particleCount != null) items.add(particleCount);
		if(rotations != null) items.add(rotations);
		if(animationDuration != null) items.add(animationDuration);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleSpiralA", selection).SetItems(items));
	}

	/**
	 * Displays text in the center<br>
	 * of a player's screen.
	 *
	 * @param titleText Title text
	 * @param subtitleText Subtitle text (Optional)
	 * @param titleDuration Title duration (Optional)
	 * @param fadeInLength Fade in length (Optional)
	 * @param fadeOutLength Fade out length (Optional)
	 */
	public void SendTitle(IText titleText, IText subtitleText, INumber titleDuration, INumber fadeInLength, INumber fadeOutLength) {
		List<CodeValue> items = new ArrayList<>();

		items.add(titleText);
		if(subtitleText != null) items.add(subtitleText);
		if(titleDuration != null) items.add(titleDuration);
		if(fadeInLength != null) items.add(fadeInLength);
		if(fadeOutLength != null) items.add(fadeOutLength);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SendTitle", selection).SetItems(items));
	}

	/**
	 * Sets if a player is instantly <br>
	 * respawned upon dying.
	 */
	public void InstantRespawn(Tags.InstantRespawn instantRespawnTag) {
		List<CodeValue> items = new ArrayList<>();

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("InstantRespawn", selection).SetItems(items).SetTags(new Tag("Instant Respawn", instantRespawnTag.getJSONValue())));
	}

	/**
	 * Stops all or specific sounds<br>
	 * for a player.
	 *
	 * @param soundsToStop Sounds to stop (Optional)
	 */
	public void StopSound(ISound[] soundsToStop, Tags.SoundSource soundSourceTag) {
		List<CodeValue> items = new ArrayList<>();

		if(soundsToStop != null) items.addAll(Arrays.asList(soundsToStop));
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("StopSound", selection).SetItems(items).SetTags(new Tag("Sound Source", soundSourceTag.getJSONValue())));
	}

	/**
	 * Sets the color a player's<br>
	 * name tag appears in.
	 *
	 * @param nameColor Name color
	 */
	public void SetNameColor(IText nameColor) {
		List<CodeValue> items = new ArrayList<>();

		items.add(nameColor);
		CodeManager.instance.addCodeBlock(new PlayerActionBlock("SetNameColor", selection).SetItems(items));
	}

	/**
	 * Displays a line of particles<br>
	 * between two locations to<br>
	 * a player.
	 *
	 * @param effect Effect
	 * @param startLocation Start location
	 * @param endLocation End location
	 * @param effectSpacing Effect spacing (Optional)
	 */
	public void ParticleLine(IParticle effect, ILocation startLocation, ILocation endLocation, INumber effectSpacing) {
		List<CodeValue> items = new ArrayList<>();

		items.add(effect);
		items.add(startLocation);
		items.add(endLocation);
		if(effectSpacing != null) items.add(effectSpacing);

		CodeManager.instance.addCodeBlock(new PlayerActionBlock("ParticleLine", selection).SetItems(items));
	}
}