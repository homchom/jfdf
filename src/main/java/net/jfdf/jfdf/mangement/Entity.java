package net.jfdf.jfdf.mangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.jfdf.jfdf.blocks.EntityActionBlock;
import net.jfdf.jfdf.blocks.EntityActionBlock.EntitySelection;
import net.jfdf.jfdf.values.*;

public class Entity {

    private final EntitySelection selection;
	
	public Entity(EntitySelection selection) {
		this.selection = selection;
	}
	
	public static Entity getCurrentSelection() {return new Entity(EntitySelection.CURRENT_SELECTION);}
	
	public static Entity getDefaultEntity() {return new Entity(EntitySelection.DEFAULT_ENTITY);}
	
	public static Entity getKiller() {return new Entity(EntitySelection.KILLER);}
	
	public static Entity getDamager() {return new Entity(EntitySelection.DAMAGER);}
	
	public static Entity getShooter() {return new Entity(EntitySelection.SHOOTER);}
	
    public static Entity getVictim() {return new Entity(EntitySelection.VICTIM);}
    
    public static Entity getProjectile() {return new Entity(EntitySelection.PROJECTILE);}
	
    public static Entity getAllEntities() {return new Entity(EntitySelection.ALL_ENTITIES);}
    
    public static Entity getAllMobs() {return new Entity(EntitySelection.ALL_MOBS);}

    public static Entity getLastEntitySpawned() {return new Entity(EntitySelection.LAST_ENTITY_SPAWNED);}

    public static Entity getLastMobSpawned() {return new Entity(EntitySelection.LAST_MOB_SPAWNED);}

    /**
     * Sets a parrot's color.
     */
    public void SetParrotColor(Tags.ParrotColor parrotColorTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetParrotColor", selection).SetItems(items).SetTags(new Tag("ParrotColor", parrotColorTag.getJSONValue())));
    }

    /**
     * Deletes an entity.
     */
    public void Remove() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("Remove", selection).SetItems(items));
    }

    /**
     * Sets an entity's movement<br>
     * velocity.
     *
     * @param newVelocity New velocity
     */
    public void SetVelocity(IVector newVelocity, Tags.AddtoCurrentVelocity addtoCurrentVelocityTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(newVelocity);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetVelocity", selection).SetItems(items).SetTags(new Tag("AddtoCurrentVelocity", addtoCurrentVelocityTag.getJSONValue())));
    }

    /**
     * Sets the number of ticks a<br>
     * glow squid will stop glowing for.
     *
     * @param ticks Ticks
     */
    public void SetGlowSquidDark(INumber ticks) {
        List<CodeValue> items = new ArrayList<>();

        items.add(ticks);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetGlowSquidDark", selection).SetItems(items));
    }

    /**
     * Damages a mob.
     *
     * @param damageToInflict Damage to inflict
     */
    public void Damage(INumber damageToInflict) {
        List<CodeValue> items = new ArrayList<>();

        items.add(damageToInflict);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("Damage", selection).SetItems(items));
    }

    /**
     * Sets whether a sheep<br>
     * has its wool.
     */
    public void SetSheepSheared(Tags.Sheared shearedTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetSheepSheared", selection).SetItems(items).SetTags(new Tag("Sheared", shearedTag.getJSONValue())));
    }

    /**
     * Sets whether an entity<br>
     * is sitting.
     */
    public void SetMobSitting(Tags.IsSitting isSittingTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetMobSitting", selection).SetItems(items).SetTags(new Tag("IsSitting", isSittingTag.getJSONValue())));
    }

    /**
     * Sets an axolotl's color.
     */
    public void SetAxolotlColor(Tags.AxolotlColor axolotlColorTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetAxolotlColor", selection).SetItems(items).SetTags(new Tag("AxolotlColor", axolotlColorTag.getJSONValue())));
    }

    /**
     * Makes a mob perform<br>
     * an animation.
     */
    public void SendAnimation(Tags.AnimationType animationTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SendAnimation", selection).SetItems(items).SetTags(new Tag("AnimationType", animationTypeTag.getJSONValue())));
    }

    /**
     * Sets a horse's color and pattern.
     */
    public void SetHorsePattern(Tags.HorseColor horseColorTag, Tags.HorseMarkings horseMarkingsTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetHorsePattern", selection).SetItems(items).SetTags(new Tag("HorseColor", horseColorTag.getJSONValue()), new Tag("HorseMarkings", horseMarkingsTag.getJSONValue())));
    }

    /**
     * Restores a mob's health.
     *
     * @param amountToHeal Amount to heal
     */
    public void Heal(INumber amountToHeal) {
        List<CodeValue> items = new ArrayList<>();

        items.add(amountToHeal);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("Heal", selection).SetItems(items));
    }

    /**
     * Sets whether an entity is<br>
     * sentient and/or affected<br>
     * by physics.
     */
    public void SetAI(Tags.AI aITag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetAI", selection).SetItems(items).SetTags(new Tag("AI", aITag.getJSONValue())));
    }

    /**
     * Sets whether an entity<br>
     * is riptiding.
     */
    public void SetRiptiding(Tags.Riptiding riptidingTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetRiptiding", selection).SetItems(items).SetTags(new Tag("Riptiding", riptidingTag.getJSONValue())));
    }

    /**
     * Sets the projectile source of<br>
     * a projectile (or removes it).
     *
     * @param uuidOfShooter UUID of shooter
     */
    public void SetProjSource(IText uuidOfShooter) {
        List<CodeValue> items = new ArrayList<>();

        items.add(uuidOfShooter);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetProjSource", selection).SetItems(items));
    }

    /**
     * Sets whether a fox appears<br>
     * to be leaping.
     */
    public void SetFoxLeaping(Tags.Leaping leapingTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetFoxLeaping", selection).SetItems(items).SetTags(new Tag("Leaping", leapingTag.getJSONValue())));
    }

    /**
     * Sets the gene of a panda.<br>
     * This affects their behavior<br>
     * and appearance.
     */
    public void SetPandaGene(Tags.SetGene setGeneTag, Tags.GeneType geneTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetPandaGene", selection).SetItems(items).SetTags(new Tag("SetGene", setGeneTag.getJSONValue()), new Tag("GeneType", geneTypeTag.getJSONValue())));
    }

    /**
     * Sets a mob's dye color.
     */
    public void SetDyeColor(Tags.Dye dyeTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetDyeColor", selection).SetItems(items).SetTags(new Tag("Dye", dyeTag.getJSONValue())));
    }

    /**
     * Launches an entity up or down.
     *
     * @param launchPower Launch power
     */
    public void LaunchUp(INumber launchPower, Tags.AddtoCurrentVelocity addtoCurrentVelocityTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(launchPower);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("LaunchUp", selection).SetItems(items).SetTags(new Tag("AddtoCurrentVelocity", addtoCurrentVelocityTag.getJSONValue())));
    }

    /**
     * Sets an entity's maximum<br>
     * health.
     *
     * @param maximumHealth Maximum health
     */
    public void SetMaxHealth(INumber maximumHealth, Tags.HealMobtoMaxHealth healMobtoMaxHealthTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(maximumHealth);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetMaxHealth", selection).SetItems(items).SetTags(new Tag("HealMobtoMaxHealth", healMobtoMaxHealthTag.getJSONValue())));
    }

    /**
     * Sets an animal's age.
     *
     * @param age Age
     */
    public void SetAge(INumber age, Tags.AgeLock ageLockTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(age);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetAge", selection).SetItems(items).SetTags(new Tag("AgeLock", ageLockTag.getJSONValue())));
    }

    /**
     * Sets the time until a fish<br>
     * starts to approach a<br>
     * fishing hook.
     *
     * @param waitTime Wait time (ticks)
     */
    public void SetFishingTime(INumber waitTime) {
        List<CodeValue> items = new ArrayList<>();

        items.add(waitTime);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetFishingTime", selection).SetItems(items));
    }

    /**
     * Sets the location an end<br>
     * crystal points its beam at.
     *
     * @param target Target (Optional)
     */
    public void EndCrystalBeam(ILocation target) {
        List<CodeValue> items = new ArrayList<>();

        if(target != null) items.add(target);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("EndCrystalBeam", selection).SetItems(items));
    }

    /**
     * Sets a villager's profession.
     */
    public void SetProfession(Tags.Profession professionTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetProfession", selection).SetItems(items).SetTags(new Tag("Profession", professionTag.getJSONValue())));
    }

    /**
     * Sets whether a mob has its<br>
     * arms raised.
     */
    public void SetArmsRaised(Tags.ArmsRaised armsRaisedTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetArmsRaised", selection).SetItems(items).SetTags(new Tag("ArmsRaised", armsRaisedTag.getJSONValue())));
    }

    /**
     * Removes all active potion<br>
     * effects from an entity.
     */
    public void ClearPotions() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("ClearPotions", selection).SetItems(items));
    }

    /**
     * Sets whether an armor stand has<br>
     * arms and a base plate.
     */
    public void ArmorStandParts(Tags.Arms armsTag, Tags.BasePlate basePlateTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("ArmorStandParts", selection).SetItems(items).SetTags(new Tag("Arms", armsTag.getJSONValue()), new Tag("BasePlate", basePlateTag.getJSONValue())));
    }

    /**
     * Sets whether an entity is<br>
     * invulnerable to damage.
     */
    public void SetInvulnerable(Tags.Invulnerable invulnerableTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetInvulnerable", selection).SetItems(items).SetTags(new Tag("Invulnerable", invulnerableTag.getJSONValue())));
    }

    /**
     * Sets the number of ticks a<br>
     * dropped item cannot be<br>
     * picked up for.
     *
     * @param delay Delay
     */
    public void SetPickupDelay(INumber delay) {
        List<CodeValue> items = new ArrayList<>();

        items.add(delay);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetPickupDelay", selection).SetItems(items));
    }

    /**
     * Instructs a mob's AI to target<br>
     * a specific mob or player.
     *
     * @param targetNameOrUuid Target name or UUID
     */
    public void SetTarget(IText targetNameOrUuid) {
        List<CodeValue> items = new ArrayList<>();

        items.add(targetNameOrUuid);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetTarget", selection).SetItems(items));
    }

    /**
     * Sets a creeper's explosion power.<br>
     * This affects the damage and area<br>
     * of effect.
     *
     * @param power Power (0-25)
     */
    public void SetCreeperPower(INumber power) {
        List<CodeValue> items = new ArrayList<>();

        items.add(power);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetCreeperPower", selection).SetItems(items));
    }

    /**
     * Sets whether an armor stand<br>
     * is a marker.
     */
    public void SetMarker(Tags.Marker markerTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetMarker", selection).SetItems(items).SetTags(new Tag("Marker", markerTag.getJSONValue())));
    }

    /**
     * Removes a custom tag<br>
     * from an entity.
     *
     * @param tagName Tag name
     */
    public void RemoveCustomTag(IText tagName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(tagName);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("RemoveCustomTag", selection).SetItems(items));
    }

    /**
     * Sets whether an entity's<br>
     * custom name is always<br>
     * displayed above them.
     */
    public void SetNameVisible(Tags.NameTagVisible nameTagVisibleTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetNameVisible", selection).SetItems(items).SetTags(new Tag("NameTagVisible", nameTagVisibleTag.getJSONValue())));
    }

    /**
     * Sets the currently<br>
     * remaining ticks until an<br>
     * entity can next be hurt.
     *
     * @param ticks Ticks
     */
    public void SetInvulTicks(INumber ticks) {
        List<CodeValue> items = new ArrayList<>();

        items.add(ticks);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetInvulTicks", selection).SetItems(items));
    }

    /**
     * Sets an entity's absorption<br>
     * health (golden hearts).
     *
     * @param absorptionHealth Absorption health
     */
    public void SetAbsorption(INumber absorptionHealth) {
        List<CodeValue> items = new ArrayList<>();

        items.add(absorptionHealth);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetAbsorption", selection).SetItems(items));
    }

    /**
     * Changes the pose of an entity.<br>
     * This affects their animations<br>
     * and/or hitbox, depending on<br>
     * the pose and entity type.
     */
    public void SetPose(Tags.Pose poseTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock(" SetPose ", selection).SetItems(items).SetTags(new Tag("Pose", poseTag.getJSONValue())));
    }

    /**
     * Sets whether a horse is<br>
     * standing on its hind legs.
     */
    public void SetRearing(Tags.Rearing rearingTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetRearing", selection).SetItems(items).SetTags(new Tag("Rearing", rearingTag.getJSONValue())));
    }

    /**
     * Sets whether a creeper<br>
     * has the charged effect.
     */
    public void CreeperCharged(Tags.Charged chargedTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("CreeperCharged", selection).SetItems(items).SetTags(new Tag("Charged", chargedTag.getJSONValue())));
    }

    /**
     * Sets the remaining time an entity is on<br>
     * fire for.
     *
     * @param ticks Ticks
     */
    public void SetFireTicks(INumber ticks) {
        List<CodeValue> items = new ArrayList<>();

        items.add(ticks);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetFireTicks", selection).SetItems(items));
    }

    /**
     * Sets an area of effect cloud's<br>
     * radius and shrinking speed.
     *
     * @param radius Radius
     * @param shrinkingSpeed Shrinking speed (blocks per second) (Optional)
     */
    public void SetCloudRadius(INumber radius, INumber shrinkingSpeed) {
        List<CodeValue> items = new ArrayList<>();

        items.add(radius);
        if(shrinkingSpeed != null) items.add(shrinkingSpeed);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetCloudRadius", selection).SetItems(items));
    }

    /**
     * Sets whether an entity<br>
     * is affected by gravity.
     */
    public void SetGravity(Tags.Gravity gravityTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetGravity", selection).SetItems(items).SetTags(new Tag("Gravity", gravityTag.getJSONValue())));
    }

    /**
     * Sets an entity's custom name.
     *
     * @param customName Custom name (Optional)
     */
    public void SetName(IText customName, Tags.HideNameTag hideNameTagTag) {
        List<CodeValue> items = new ArrayList<>();

        if(customName != null) items.add(customName);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetName", selection).SetItems(items).SetTags(new Tag("HideNameTag", hideNameTagTag.getJSONValue())));
    }

    /**
     * Causes a mob to jump.
     */
    public void Jump() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("Jump", selection).SetItems(items));
    }

    /**
     * Sets the remaining ticks of<br>
     * invulnerability a wither has.
     *
     * @param ticks Ticks
     */
    public void SetWitherInvul(INumber ticks) {
        List<CodeValue> items = new ArrayList<>();

        items.add(ticks);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetWitherInvul", selection).SetItems(items));
    }

    /**
     * Sets whether an entity<br>
     * is gliding.
     */
    public void SetGliding(Tags.Gliding glidingTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetGliding", selection).SetItems(items).SetTags(new Tag("Gliding", glidingTag.getJSONValue())));
    }

    /**
     * Changes an entity's pitch and<br>
     * yaw without teleporting it.
     *
     * @param pitch Pitch (-90 to 90)
     * @param yaw Yaw (-180 to 180)
     */
    public void SetRotation(INumber pitch, INumber yaw) {
        List<CodeValue> items = new ArrayList<>();

        items.add(pitch);
        items.add(yaw);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetRotation", selection).SetItems(items));
    }

    /**
     * Sets a tropical fish's<br>
     * color and pattern.
     */
    public void SetFishPattern(Tags.PatternColor patternColorTag, Tags.BodyColor bodyColorTag, Tags.Pattern patternTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetFishPattern", selection).SetItems(items).SetTags(new Tag("PatternColor", patternColorTag.getJSONValue()), new Tag("BodyColor", bodyColorTag.getJSONValue()), new Tag("Pattern", patternTag.getJSONValue())));
    }

    /**
     * Mounts an entity on top of<br>
     * another entity or player.
     *
     * @param nameOrUuidOf Name or UUID of target to ride
     */
    public void RideEntity(IText nameOrUuidOf) {
        List<CodeValue> items = new ArrayList<>();

        items.add(nameOrUuidOf);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("RideEntity", selection).SetItems(items));
    }

    /**
     * Set an enderman's held block.
     *
     * @param blockToHold Block to hold
     */
    public void SetEndermanBlock(CodeValue blockToHold) {
        List<CodeValue> items = new ArrayList<>();

        items.add(blockToHold);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetEndermanBlock", selection).SetItems(items));
    }

    /**
     * Teleports an entity to a<br>
     * specified location.
     *
     * @param newPosition New position
     */
    public void Teleport(ILocation newPosition, Tags.KeepCurrentRotation keepCurrentRotationTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(newPosition);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("Teleport", selection).SetItems(items).SetTags(new Tag("KeepCurrentRotation", keepCurrentRotationTag.getJSONValue())));
    }

    /**
     * Launches an entity toward or away<br>
     * from a location.
     *
     * @param launchDestination Launch destination
     * @param launchPower Launch power (Optional)
     */
    public void LaunchToward(ILocation launchDestination, INumber launchPower, Tags.AddtoCurrentVelocity addtoCurrentVelocityTag, Tags.IgnoreDistance ignoreDistanceTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(launchDestination);
        if(launchPower != null) items.add(launchPower);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("LaunchToward", selection).SetItems(items).SetTags(new Tag("AddtoCurrentVelocity", addtoCurrentVelocityTag.getJSONValue()), new Tag("IgnoreDistance", ignoreDistanceTag.getJSONValue())));
    }

    /**
     * Sets a mob's armor items.<br>
     * Place the armor in slots 1-4<br>
     * of the chest, with 1 being the<br>
     * helmet and 4 being the boots.
     *
     * @param armorToSet Armor to set
     */
    public void SetArmor(IItem... armorToSet) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(armorToSet));
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetArmor", selection).SetItems(items));
    }

    /**
     * Gets the value of a custom<br>
     * entity tag.
     *
     * @param variableToSet Variable to set
     * @param tagName Tag name
     */
    public void GetCustomTag(Variable variableToSet, IText tagName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(variableToSet);
        items.add(tagName);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("GetCustomTag", selection).SetItems(items));
    }

    /**
     * Sets a cat's skin type.
     */
    public void SetCatType(Tags.SkinType skinTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetCatType", selection).SetItems(items).SetTags(new Tag("SkinType", skinTypeTag.getJSONValue())));
    }

    /**
     * Sets whether a mob wears<br>
     * a saddle.
     */
    public void SetSaddle(Tags.Saddle saddleTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetSaddle", selection).SetItems(items).SetTags(new Tag("Saddle", saddleTag.getJSONValue())));
    }

    /**
     * Causes a shulker bullet to start<br>
     * targeting the provided entity.
     *
     * @param targetUuid Target UUID (Optional)
     */
    public void SetBulletTarget(IText targetUuid) {
        List<CodeValue> items = new ArrayList<>();

        if(targetUuid != null) items.add(targetUuid);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetBulletTarget", selection).SetItems(items));
    }

    /**
     * Launches a projectile from a mob.
     *
     * @param projectileToLaunch Projectile to launch
     * @param launchPoint Launch point (Optional)
     * @param projectileName Projectile name (Optional)
     * @param speed Speed (Optional)
     * @param inaccuracy Inaccuracy (Optional)
     */
    public void LaunchProj(IItem projectileToLaunch, ILocation launchPoint, IText projectileName, INumber speed, INumber inaccuracy) {
        List<CodeValue> items = new ArrayList<>();

        items.add(projectileToLaunch);
        if(launchPoint != null) items.add(launchPoint);
        if(projectileName != null) items.add(projectileName);
        if(speed != null) items.add(speed);
        if(inaccuracy != null) items.add(inaccuracy);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("LaunchProj", selection).SetItems(items));
    }

    /**
     * Sets the behavior phase<br>
     * of an Ender Dragon.
     */
    public void SetDragonPhase(Tags.Phase phaseTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetDragonPhase", selection).SetItems(items).SetTags(new Tag("Phase", phaseTag.getJSONValue())));
    }

    /**
     * Sets a llama's fur color.
     */
    public void SetLlamaColor(Tags.LlamaColor llamaColorTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetLlamaColor", selection).SetItems(items).SetTags(new Tag("LlamaColor", llamaColorTag.getJSONValue())));
    }

    /**
     * Sets the biome type of a<br>
     * villager. This affects their<br>
     * appearance only.
     */
    public void SetVillagerBiome(Tags.Biome biomeTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetVillagerBiome", selection).SetItems(items).SetTags(new Tag("Biome", biomeTag.getJSONValue())));
    }

    /**
     * Sets the starting amount<br>
     * of ticks it takes for a<br>
     * creeper to explode.
     *
     * @param fuseTicks Fuse ticks
     */
    public void SetCreeperFuse(INumber fuseTicks) {
        List<CodeValue> items = new ArrayList<>();

        items.add(fuseTicks);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetCreeperFuse", selection).SetItems(items));
    }

    /**
     * Sets whether an entity<br>
     * is a baby (permanently).
     */
    public void SetBaby(Tags.Baby babyTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetBaby", selection).SetItems(items).SetTags(new Tag("Baby", babyTag.getJSONValue())));
    }

    /**
     * Sets a mooshroom's skin<br>
     * type.
     */
    public void MooshroomType(Tags.MooshroomVariant mooshroomVariantTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("MooshroomType", selection).SetItems(items).SetTags(new Tag("MooshroomVariant", mooshroomVariantTag.getJSONValue())));
    }

    /**
     * Sets whether an entity<br>
     * is invisible.
     */
    public void SetInvisible(Tags.Invisible invisibleTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetInvisible", selection).SetItems(items).SetTags(new Tag("Invisible", invisibleTag.getJSONValue())));
    }

    /**
     * Causes a sheep to<br>
     * eat grass.
     */
    public void SheepEat() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SheepEat", selection).SetItems(items));
    }

    /**
     * Sets whether a cat appears<br>
     * to be lying down.
     */
    public void SetCatResting(Tags.Resting restingTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetCatResting", selection).SetItems(items).SetTags(new Tag("Resting", restingTag.getJSONValue())));
    }

    /**
     * Gives one or more potion<br>
     * effects to an entity.
     *
     * @param effect Effect(s) to give
     */
    public void GivePotion(IPotion[] effect, Tags.OverwriteEffect overwriteEffectTag, Tags.EffectParticles effectParticlesTag) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(effect));
        CodeManager.instance.addCodeBlock(new EntityActionBlock("GivePotion", selection).SetItems(items).SetTags(new Tag("OverwriteEffect", overwriteEffectTag.getJSONValue()), new Tag("EffectParticles", effectParticlesTag.getJSONValue())));
    }

    /**
     * Tames and sets the owner<br>
     * of a tameable mob.
     *
     * @param ownerNameOrUuid Owner name or UUID
     */
    public void Tame(IText ownerNameOrUuid) {
        List<CodeValue> items = new ArrayList<>();

        items.add(ownerNameOrUuid);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("Tame", selection).SetItems(items));
    }

    /**
     * Sets whether this entity has<br>
     * a glowing outline that can<br>
     * be seen through blocks.
     */
    public void SetGlowing(Tags.Glowing glowingTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetGlowing", selection).SetItems(items).SetTags(new Tag("Glowing", glowingTag.getJSONValue())));
    }

    /**
     * Sets whether a goat<br>
     * screams or not.
     */
    public void SetGoatScreaming(Tags.Screams screamsTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetGoatScreaming", selection).SetItems(items).SetTags(new Tag("Screams", screamsTag.getJSONValue())));
    }

    /**
     * Sets an entity's current<br>
     * health.
     *
     * @param currentHealth Current health
     */
    public void SetHealth(INumber currentHealth) {
        List<CodeValue> items = new ArrayList<>();

        items.add(currentHealth);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetHealth", selection).SetItems(items));
    }

    /**
     * Disguises an entity as a mob.
     *
     * @param mobDisguise Mob disguise
     * @param disguiseName Disguise name (Optional)
     */
    public void MobDisguise(IItem mobDisguise, IText disguiseName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(mobDisguise);
        if(disguiseName != null) items.add(disguiseName);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("MobDisguise", selection).SetItems(items));
    }

    /**
     * Disguises an entity as a block.
     *
     * @param blockDisguise Block disguise
     * @param nameOfDisguise Name of disguise (Optional)
     */
    public void BlockDisguise(CodeValue blockDisguise, IText nameOfDisguise) {
        List<CodeValue> items = new ArrayList<>();

        items.add(blockDisguise);
        if(nameOfDisguise != null) items.add(nameOfDisguise);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("BlockDisguise", selection).SetItems(items));
    }

    /**
     * Sets the block shown inside<br>
     * a minecart. This does not<br>
     * affect its functionality.
     *
     * @param blockToShow Block to show
     * @param blockOffset Block offset (Optional)
     */
    public void SetMinecartBlock(CodeValue blockToShow, INumber blockOffset) {
        List<CodeValue> items = new ArrayList<>();

        items.add(blockToShow);
        if(blockOffset != null) items.add(blockOffset);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetMinecartBlock", selection).SetItems(items));
    }

    /**
     * Causes a fox to start<br>
     * or stop sleeping.
     */
    public void FoxSleeping(Tags.Sleeping sleepingTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("FoxSleeping", selection).SetItems(items).SetTags(new Tag("Sleeping", sleepingTag.getJSONValue())));
    }

    /**
     * Sets whether a mob is able<br>
     * to collide with other entities.
     */
    public void SetCollidable(Tags.Collision collisionTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetCollidable", selection).SetItems(items).SetTags(new Tag("Collision", collisionTag.getJSONValue())));
    }

    /**
     * Sets the rotation of an armor<br>
     * stand part.
     *
     * @param direction Direction
     */
    public void ArmorStandPose(IVector direction, Tags.ArmorStandPart armorStandPartTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(direction);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("ArmorStandPose", selection).SetItems(items).SetTags(new Tag("ArmorStandPart", armorStandPartTag.getJSONValue())));
    }

    /**
     * Launches an entity forward<br>
     * or backward.
     *
     * @param launchPower Launch power
     */
    public void LaunchFwd(INumber launchPower, Tags.AddtoCurrentVelocity addtoCurrentVelocityTag, Tags.LaunchAxis launchAxisTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(launchPower);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("LaunchFwd", selection).SetItems(items).SetTags(new Tag("AddtoCurrentVelocity", addtoCurrentVelocityTag.getJSONValue()), new Tag("LaunchAxis", launchAxisTag.getJSONValue())));
    }

    /**
     * Sets an entity's fall distance,<br>
     * affecting fall damage upon<br>
     * landing.
     *
     * @param fallDistance Fall distance (blocks)
     */
    public void SetFallDistance(INumber fallDistance) {
        List<CodeValue> items = new ArrayList<>();

        items.add(fallDistance);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetFallDistance", selection).SetItems(items));
    }

    /**
     * Instructs a mob's AI to always<br>
     * pathfind to a certain location<br>
     * at a certain speed.
     *
     * @param targetLocation Target location
     * @param walkSpeed Walk speed (Optional)
     */
    public void MoveToLoc(ILocation targetLocation, INumber walkSpeed) {
        List<CodeValue> items = new ArrayList<>();

        items.add(targetLocation);
        if(walkSpeed != null) items.add(walkSpeed);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("MoveToLoc", selection).SetItems(items));
    }

    /**
     * Sets the item in one of the<br>
     * equipment slots (including<br>
     * horse items) of an entity.
     *
     * @param itemToSet Item to set (Optional)
     */
    public void SetEquipment(IItem itemToSet, Tags.EquipmentSlot equipmentSlotTag) {
        List<CodeValue> items = new ArrayList<>();

        if(itemToSet != null) items.add(itemToSet);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetEquipment", selection).SetItems(items).SetTags(new Tag("EquipmentSlot", equipmentSlotTag.getJSONValue())));
    }

    /**
     * Makes a mob perform<br>
     * an attack animation.
     */
    public void AttackAnimation(Tags.AnimationArm animationArmTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("AttackAnimation", selection).SetItems(items).SetTags(new Tag("AnimationArm", animationArmTag.getJSONValue())));
    }

    /**
     * Sets whether an entity will<br>
     * produce sound effects.
     */
    public void SetSilenced(Tags.Silenced silencedTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetSilenced", selection).SetItems(items).SetTags(new Tag("Silenced", silencedTag.getJSONValue())));
    }

    /**
     * Sets if a bee has nectar<br>
     * on its body.
     */
    public void SetBeeNectar(Tags.HasNectar hasNectarTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetBeeNectar", selection).SetItems(items).SetTags(new Tag("HasNectar", hasNectarTag.getJSONValue())));
    }

    /**
     * Attaches a lead to the target,<br>
     * held by an entity or lead knot.
     *
     * @param leadHolderUuid Lead holder UUID
     */
    public void AttachLead(IText leadHolderUuid) {
        List<CodeValue> items = new ArrayList<>();

        items.add(leadHolderUuid);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("AttachLead", selection).SetItems(items));
    }

    /**
     * Sets whether a snow golem<br>
     * is wearing a pumpkin.
     */
    public void SnowmanPumpkin(Tags.Pumpkin pumpkinTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SnowmanPumpkin", selection).SetItems(items).SetTags(new Tag("Pumpkin", pumpkinTag.getJSONValue())));
    }

    /**
     * Sets the value of or creates<br>
     * a custom tag value.
     *
     * @param tagName Tag name
     * @param tagValue Tag value
     */
    public void SetCustomTag(IText tagName, INumber tagValue) {
        List<CodeValue> items = new ArrayList<>();

        items.add(tagName);
        items.add(tagValue);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetCustomTag", selection).SetItems(items));
    }

    /**
     * Removes one or more potion<br>
     * effects from an entity.
     *
     * @param effect Effect(s) to remove
     */
    public void RemovePotion(IPotion... effect) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(effect));
        CodeManager.instance.addCodeBlock(new EntityActionBlock("RemovePotion", selection).SetItems(items));
    }

    /**
     * Causes a sheep to<br>
     * be sheared.
     */
    public void ShearSheep() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("ShearSheep", selection).SetItems(items));
    }

    /**
     * Sets the possible interactions, such<br>
     * as adding or removing items, of an<br>
     * armor stand's slot(s).
     */
    public void ArmorStandSlots(Tags.Interactions interactionsTag, Tags.EquipmentSlot equipmentSlotTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("ArmorStandSlots", selection).SetItems(items).SetTags(new Tag("Interactions", interactionsTag.getJSONValue()), new Tag("EquipmentSlot", equipmentSlotTag.getJSONValue())));
    }

    /**
     * Sets a rabbit's skin type.
     */
    public void SetRabbitType(Tags.SkinType skinTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetRabbitType", selection).SetItems(items).SetTags(new Tag("SkinType", skinTypeTag.getJSONValue())));
    }

    /**
     * Sets the size of an entity.<br>
     * This may also affect its<br>
     * health and strength.
     *
     * @param size Size
     */
    public void SetSize(INumber size) {
        List<CodeValue> items = new ArrayList<>();

        items.add(size);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetSize", selection).SetItems(items));
    }

    /**
     * Disguises an entity as a player.
     *
     * @param disguisePlayerName Disguise player name
     * @param disguiseSkin Disguise skin (Optional)
     */
    public void PlayerDisguise(IText disguisePlayerName, IItem disguiseSkin) {
        List<CodeValue> items = new ArrayList<>();

        items.add(disguisePlayerName);
        if(disguiseSkin != null) items.add(disguiseSkin);

        CodeManager.instance.addCodeBlock(new EntityActionBlock("PlayerDisguise", selection).SetItems(items));
    }

    /**
     * Sets whether a mob is<br>
     * angry at players.
     */
    public void SetAngry(Tags.Angry angryTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetAngry", selection).SetItems(items).SetTags(new Tag("Angry", angryTag.getJSONValue())));
    }

    /**
     * Sets the item of an item entity<br>
     * or inside an item frame.
     *
     * @param newItem New item
     */
    public void SetItem(IItem newItem) {
        List<CodeValue> items = new ArrayList<>();

        items.add(newItem);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetItem", selection).SetItems(items));
    }

    /**
     * Causes an entity<br>
     * to explode.
     */
    public void Explode() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("Explode", selection).SetItems(items));
    }

    /**
     * Removes an entity's disguise.
     */
    public void Undisguise() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("Undisguise", selection).SetItems(items));
    }

    /**
     * Sets whether a mob drops<br>
     * their items when dead.
     */
    public void SetDeathDrops(Tags.HasDeathDrops hasDeathDropsTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetDeathDrops", selection).SetItems(items).SetTags(new Tag("HasDeathDrops", hasDeathDropsTag.getJSONValue())));
    }

    /**
     * Sets whether an item will<br>
     * never despawn.
     */
    public void SetPersistent(Tags.Persistent persistentTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetPersistent", selection).SetItems(items).SetTags(new Tag("Persistent", persistentTag.getJSONValue())));
    }

    /**
     * Sets a villager's experience<br>
     * points, which affects their level.
     *
     * @param experience Experience
     */
    public void SetVillagerExp(INumber experience) {
        List<CodeValue> items = new ArrayList<>();

        items.add(experience);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetVillagerExp", selection).SetItems(items));
    }

    /**
     * Ignites a creeper, causing<br>
     * it to explode after a fuse<br>
     * period.
     */
    public void IgniteCreeper() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("IgniteCreeper", selection).SetItems(items));
    }

    /**
     * Causes a mob to start<br>
     * or stop celebrating.
     */
    public void SetCelebrating(Tags.Celebrate celebrateTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetCelebrating", selection).SetItems(items).SetTags(new Tag("Celebrate", celebrateTag.getJSONValue())));
    }

    /**
     * Sets the item a projectile<br>
     * displays as.
     *
     * @param displayItem Display item
     */
    public void ProjectileItem(IItem displayItem) {
        List<CodeValue> items = new ArrayList<>();

        items.add(displayItem);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("ProjectileItem", selection).SetItems(items));
    }

    /**
     * Sets a horse's jump strength.
     *
     * @param strength Strength
     */
    public void SetHorseJump(INumber strength) {
        List<CodeValue> items = new ArrayList<>();

        items.add(strength);
        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetHorseJump", selection).SetItems(items));
    }

    /**
     * Sets whether a mob carries<br>
     * a chest, which allows its<br>
     * inventory to be accessed.
     */
    public void SetCarryingChest(Tags.CarryingChest carryingChestTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetCarryingChest", selection).SetItems(items).SetTags(new Tag("CarryingChest", carryingChestTag.getJSONValue())));
    }

    /**
     * Sets a fox's fur type.
     */
    public void SetFoxType(Tags.FoxType foxTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new EntityActionBlock("SetFoxType", selection).SetItems(items).SetTags(new Tag("FoxType", foxTypeTag.getJSONValue())));
    }
}