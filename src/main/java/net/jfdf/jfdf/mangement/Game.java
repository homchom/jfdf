package net.jfdf.jfdf.mangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.jfdf.jfdf.blocks.GameActionBlock;
import net.jfdf.jfdf.blocks.GameActionBlock.Action;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Tags.*;

public class Game {
    /**
     * Sets the amount of ticks it<br>
     * takes for a furnace block<br>
     * to cook an item.
     *
     * @param furnaceLocation Furnace location
     * @param ticks Ticks
     */
    public static void SetFurnaceSpeed(ILocation furnaceLocation, INumber ticks) {
        List<CodeValue> items = new ArrayList<>();

        items.add(furnaceLocation);
        items.add(ticks);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SetFurnaceSpeed").SetItems(items));
    }

    /**
     * Enables blocks dropping<br>
     * as items when broken.
     */
    public static void BlockDropsOn() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new GameActionBlock("BlockDropsOn").SetItems(items));
    }

    /**
     * Fills the container at a location<br>
     * with items.
     *
     * @param containerLocation Container location
     * @param item Item(s) to fill with
     */
    public static void FillContainer(ILocation containerLocation, IItem... item) {
        List<CodeValue> items = new ArrayList<>();

        items.add(containerLocation);
        items.addAll(Arrays.asList(item));

        CodeManager.instance.addCodeBlock(new GameActionBlock("FillContainer").SetItems(items));
    }

    /**
     * Breaks the block at a location<br>
     * as if it was broken by a player.
     *
     * @param block Block(s) to break
     */
    public static void BreakBlock(ILocation... block) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(block));
        CodeManager.instance.addCodeBlock(new GameActionBlock("BreakBlock").SetItems(items));
    }

    /**
     * Applies bone meal to a block.
     *
     * @param block Block(s) to bone meal
     * @param numberOfUses Number of uses (Optional)
     */
    public static void BoneMeal(ILocation[] block, INumber numberOfUses, Tags.ShowParticles showParticlesTag) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(block));
        if(numberOfUses != null) items.add(numberOfUses);

        CodeManager.instance.addCodeBlock(new GameActionBlock("BoneMeal").SetItems(items).SetTags(new Tag("Show Particles", showParticlesTag.getJSONValue())));
    }

    /**
     */
    public static void DebugStackTrace() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new GameActionBlock("DebugStackTrace").SetItems(items));
    }

    /**
     * Spawns a falling block at a<br>
     * location.
     *
     * @param blockLocation Block location
     * @param blockMaterial Block material
     * @param blockData Block data (Optional)
     */
    public static void FallingBlock(ILocation blockLocation, CodeValue blockMaterial, IText[] blockData, Tags.HurtHitEntities hurtHitEntitiesTag, Tags.ReformonImpact reformonImpactTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(blockLocation);
        items.add(blockMaterial);
        if(blockData != null) items.addAll(Arrays.asList(blockData));
        CodeManager.instance.addCodeBlock(new GameActionBlock("FallingBlock").SetItems(items).SetTags(new Tag("Hurt Hit Entities", hurtHitEntitiesTag.getJSONValue()), new Tag("Reform on Impact", reformonImpactTag.getJSONValue())));
    }

    /**
     * Sends a message to a Discord<br>
     * webhook.
     *
     * @param webhookUrl Webhook URL
     * @param messageContent Message content
     */
    public static void DiscordWebhook(IText webhookUrl, IText messageContent) {
        List<CodeValue> items = new ArrayList<>();

        items.add(webhookUrl);
        items.add(messageContent);

        CodeManager.instance.addCodeBlock(new GameActionBlock("DiscordWebhook").SetItems(items));
    }

    /**
     * Changes a line of text<br>
     * on a sign.
     *
     * @param signLocation Sign location
     * @param lineNumber Line number
     * @param newText New text (Optional)
     */
    public static void ChangeSign(ILocation signLocation, INumber lineNumber, IText newText) {
        List<CodeValue> items = new ArrayList<>();

        items.add(signLocation);
        items.add(lineNumber);
        if(newText != null) items.add(newText);

        CodeManager.instance.addCodeBlock(new GameActionBlock("ChangeSign").SetItems(items));
    }

    /**
     * Causes a block to get "random<br>
     * ticked", which could cause a<br>
     * block update.
     *
     * @param block Block(s) to tick
     * @param numberOfTicks Number of ticks (Optional)
     */
    public static void TickBlock(ILocation[] block, INumber numberOfTicks) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(block));
        if(numberOfTicks != null) items.add(numberOfTicks);

        CodeManager.instance.addCodeBlock(new GameActionBlock("TickBlock").SetItems(items));
    }

    /**
     * Sends a web request to a URL.
     *
     * @param urlToRequest URL to request
     * @param contentBody Content body (Optional)
     */
    public static void WebRequest(IText urlToRequest, IText contentBody, Tags.RequestMethod requestMethodTag, Tags.ContentType contentTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(urlToRequest);
        if(contentBody != null) items.add(contentBody);

        CodeManager.instance.addCodeBlock(new GameActionBlock("WebRequest").SetItems(items).SetTags(new Tag("Request Method", requestMethodTag.getJSONValue()), new Tag("Content Type", contentTypeTag.getJSONValue())));
    }

    /**
     * Removes all scores from<br>
     * the scoreboard.
     */
    public static void ClearScBoard() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new GameActionBlock("ClearScBoard").SetItems(items));
    }

    /**
     * Disables the scoreboard<br>
     * sidebar on the plot.
     */
    public static void HideSidebar() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new GameActionBlock("HideSidebar").SetItems(items));
    }

    /**
     * Replaces items in the container<br>
     * at a location with the given item.
     *
     * @param containerLocation Container location
     * @param item Item(s) to replace (Optional)
     * @param itemToReplaceWith Item to replace with
     * @param amountOfItemsTo Amount of items to replace (Optional)
     */
    public static void ReplaceItems(ILocation containerLocation, IItem[] item, IItem itemToReplaceWith, INumber amountOfItemsTo) {
        List<CodeValue> items = new ArrayList<>();

        items.add(containerLocation);
        if(item != null) items.addAll(Arrays.asList(item));
        items.add(itemToReplaceWith);
        if(amountOfItemsTo != null) items.add(amountOfItemsTo);

        CodeManager.instance.addCodeBlock(new GameActionBlock("ReplaceItems").SetItems(items));
    }

    /**
     * Replaces the projectile fired in<br>
     * the Shoot Bow Event.
     *
     * @param projectileToLaunch Projectile to launch (Optional)
     */
    public static void SetEventProj(IItem projectileToLaunch) {
        List<CodeValue> items = new ArrayList<>();

        if(projectileToLaunch != null) items.add(projectileToLaunch);
        CodeManager.instance.addCodeBlock(new GameActionBlock("SetEventProj").SetItems(items));
    }

    /**
     * Creates an explosion at a location.
     *
     * @param explosion Explosion location
     * @param explosionPower Explosion power (0-4) (Optional)
     */
    public static void Explosion(ILocation explosion, INumber explosionPower) {
        List<CodeValue> items = new ArrayList<>();

        items.add(explosion);
        if(explosionPower != null) items.add(explosionPower);

        CodeManager.instance.addCodeBlock(new GameActionBlock("Explosion").SetItems(items));
    }

    /**
     * Spawns a mob at a location.
     *
     * @param mobType Mob type
     * @param spawnLocation Spawn location
     * @param health Health (Optional)
     * @param customName Custom name (Optional)
     * @param effect Effect(s) (Optional)
     * @param equipment Equipment (Optional)
     */
    public static void SpawnMob(IItem mobType, ILocation spawnLocation, INumber health, IText customName, IPotion[] effect, IItem... equipment) {
        List<CodeValue> items = new ArrayList<>();

        items.add(mobType);
        items.add(spawnLocation);
        if(health != null) items.add(health);
        if(customName != null) items.add(customName);
        if(effect != null) items.addAll(Arrays.asList(effect));
        if(equipment != null) items.addAll(Arrays.asList(equipment));

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnMob").SetItems(items));
    }

    /**
     * Spawns an eye of ender at a<br>
     * location, which (if specified) will<br>
     * float towards its destination.
     *
     * @param locationToSpawnAt Location to spawn at
     * @param destination Destination (Optional)
     * @param lifespan Lifespan (ticks) (Optional)
     * @param customName Custom name (Optional)
     */
    public static void SpawnEnderEye(ILocation locationToSpawnAt, ILocation destination, INumber lifespan, IText customName, Tags.EndofLifespan endofLifespanTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(locationToSpawnAt);
        if(destination != null) items.add(destination);
        if(lifespan != null) items.add(lifespan);
        if(customName != null) items.add(customName);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnEnderEye").SetItems(items).SetTags(new Tag("End of Lifespan", endofLifespanTag.getJSONValue())));
    }

    /**
     * Enables the scoreboard<br>
     * sidebar on the plot.
     */
    public static void ShowSidebar() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new GameActionBlock("ShowSidebar").SetItems(items));
    }

    /**
     * Spawns a lingering potion cloud<br>
     * at a location that imbues effects<br>
     * onto entities who enter it.
     *
     * @param spawnLocation Spawn location
     * @param effectToApply Effect to apply (Optional)
     * @param radius Radius (blocks) (Optional)
     * @param duration Duration (ticks) (Optional)
     */
    public static void SpawnPotionCloud(ILocation spawnLocation, IPotion[] effectToApply, INumber radius, INumber duration) {
        List<CodeValue> items = new ArrayList<>();

        items.add(spawnLocation);
        if(effectToApply != null) items.addAll(Arrays.asList(effectToApply));
        if(radius != null) items.add(radius);
        if(duration != null) items.add(duration);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnPotionCloud").SetItems(items));
    }

    /**
     * Sets a data tag value of<br>
     * the block at a location.
     *
     * @param location Location
     * @param blockData Block data
     */
    public static void SetBlockData(ILocation[] location, IText... blockData) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(location));
        items.addAll(Arrays.asList(blockData));

        CodeManager.instance.addCodeBlock(new GameActionBlock("SetBlockData").SetItems(items));
    }

    /**
     * Launches a projectile.
     *
     * @param projectileToLaunch Projectile to launch
     * @param launchPoint Launch point
     * @param customName Custom name (Optional)
     * @param speed Speed (Optional)
     * @param inaccuracy Inaccuracy (Optional)
     */
    public static void LaunchProj(IItem projectileToLaunch, ILocation launchPoint, IText customName, INumber speed, INumber inaccuracy) {
        List<CodeValue> items = new ArrayList<>();

        items.add(projectileToLaunch);
        items.add(launchPoint);
        if(customName != null) items.add(customName);
        if(speed != null) items.add(speed);
        if(inaccuracy != null) items.add(inaccuracy);

        CodeManager.instance.addCodeBlock(new GameActionBlock("LaunchProj").SetItems(items));
    }

    /**
     * Launches a firework<br>
     * rocket at a location.
     *
     * @param fireworkRocket Firework rocket
     * @param spawnLocation Spawn location
     */
    public static void Firework(IItem fireworkRocket, ILocation spawnLocation, Tags.Instant instantTag, Tags.Movement movementTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(fireworkRocket);
        items.add(spawnLocation);

        CodeManager.instance.addCodeBlock(new GameActionBlock("Firework").SetItems(items).SetTags(new Tag("Instant", instantTag.getJSONValue()), new Tag("Movement", movementTag.getJSONValue())));
    }

    /**
     * Sets the block at a location.
     *
     * @param blockToSet Block to set
     * @param blockLocation Block location(s)
     * @param blockData Block data (Optional)
     */
    public static void SetBlock(CodeValue blockToSet, ILocation[] blockLocation, IText... blockData) {
        List<CodeValue> items = new ArrayList<>();

        items.add(blockToSet);
        items.addAll(Arrays.asList(blockLocation));
        if(blockData != null) items.addAll(Arrays.asList(blockData));

        CodeManager.instance.addCodeBlock(new GameActionBlock(" SetBlock ").SetItems(items));
    }

    /**
     * Sets the damage dealt in<br>
     * this event.
     *
     * @param newDamageAmount New damage amount
     */
    public static void SetEventDamage(INumber newDamageAmount) {
        List<CodeValue> items = new ArrayList<>();

        items.add(newDamageAmount);
        CodeManager.instance.addCodeBlock(new GameActionBlock("SetEventDamage").SetItems(items));
    }

    /**
     * Sets the growth stage of the block<br>
     * (eg. carrots) at a location.
     *
     * @param blockLocation Block location
     * @param growthStage Growth stage (Optional)
     */
    public static void SetBlockGrowth(ILocation blockLocation, INumber growthStage, Tags.GrowthUnit growthUnitTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(blockLocation);
        if(growthStage != null) items.add(growthStage);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SetBlockGrowth").SetItems(items).SetTags(new Tag("Growth Unit", growthUnitTag.getJSONValue())));
    }

    /**
     * Sets the name of the container<br>
     * at a location.
     *
     * @param containerLocation Container location
     * @param name Name
     */
    public static void SetContainerName(ILocation containerLocation, IText name) {
        List<CodeValue> items = new ArrayList<>();

        items.add(containerLocation);
        items.add(name);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SetContainerName").SetItems(items));
    }

    /**
     * Spawns an item at a location.
     *
     * @param item Item(s) to spawn
     * @param spawnLocation Spawn location
     * @param customName Custom name (Optional)
     */
    public static void SpawnItem(IItem[] item, ILocation spawnLocation, IText customName, Tags.ApplyItemMotion applyItemMotionTag) {
        List<CodeValue> items = new ArrayList<>();

        items.addAll(Arrays.asList(item));
        items.add(spawnLocation);
        if(customName != null) items.add(customName);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnItem").SetItems(items).SetTags(new Tag("Apply Item Motion", applyItemMotionTag.getJSONValue())));
    }

    /**
     * Sets the block at a location<br>
     * to a player head.
     *
     * @param headLocation Head location
     * @param head Head
     */
    public static void SetHead(ILocation headLocation, IItem head) {
        List<CodeValue> items = new ArrayList<>();

        items.add(headLocation);
        items.add(head);
        CodeManager.instance.addCodeBlock(new GameActionBlock("SetHead").SetItems(items));
    }

    /**
     * Removes items from the container<br>
     * at a location.
     *
     * @param containerLocation Container location
     * @param item Item(s) to remove
     */
    public static void RemoveItems(ILocation containerLocation, IItem... item) {
        List<CodeValue> items = new ArrayList<>();

        items.add(containerLocation);
        items.addAll(Arrays.asList(item));

        CodeManager.instance.addCodeBlock(new GameActionBlock("RemoveItems").SetItems(items));
    }

    /**
     * Spawns a shulker bullet at a location.
     *
     * @param spawnLocation Spawn Location
     */
    public static void ShulkerBullet(ILocation spawnLocation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(spawnLocation);
        CodeManager.instance.addCodeBlock(new GameActionBlock("ShulkerBullet").SetItems(items));
    }

    /**
     * Fills a region with a type of block.
     *
     * @param blockToSet Block to set
     * @param corner1 Corner 1
     * @param corner2 Corner 2
     * @param blockData Block data (comma separated) (Optional)
     */
    public static void SetRegion(CodeValue blockToSet, ILocation corner1, ILocation corner2, IText blockData) {
        List<CodeValue> items = new ArrayList<>();

        items.add(blockToSet);
        items.add(corner1);
        items.add(corner2);
        if(blockData != null) items.add(blockData);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SetRegion").SetItems(items));
    }

    /**
     * Sets the contents of the container<br>
     * at a location.
     *
     * @param containerLocation Container location
     * @param item Item(s) to set
     */
    public static void SetContainer(ILocation containerLocation, IItem... item) {
        List<CodeValue> items = new ArrayList<>();

        items.add(containerLocation);
        items.addAll(Arrays.asList(item));

        CodeManager.instance.addCodeBlock(new GameActionBlock("SetContainer").SetItems(items));
    }

    /**
     * Sets the item in a slot of the<br>
     * container at a location.
     *
     * @param containerLocation Container location
     * @param itemToSet Item to set (Optional)
     * @param slot Slot
     */
    public static void SetItemInSlot(ILocation containerLocation, IItem itemToSet, INumber slot) {
        List<CodeValue> items = new ArrayList<>();

        items.add(containerLocation);
        if(itemToSet != null) items.add(itemToSet);
        items.add(slot);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SetItemInSlot").SetItems(items));
    }

    /**
     * Copies a region of blocks to another<br>
     * region, including air.
     *
     * @param corner1 Corner 1
     * @param corner2 Corner 2
     * @param positionToCopyFrom Position to copy from
     * @param positionToPasteTo Position to paste to
     */
    public static void CloneRegion(ILocation corner1, ILocation corner2, ILocation positionToCopyFrom, ILocation positionToPasteTo, Tags.IgnoreAir ignoreAirTag, Tags.CloneBlockEntities cloneBlockEntitiesTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(corner1);
        items.add(corner2);
        items.add(positionToCopyFrom);
        items.add(positionToPasteTo);

        CodeManager.instance.addCodeBlock(new GameActionBlock("CloneRegion").SetItems(items).SetTags(new Tag("Ignore Air", ignoreAirTag.getJSONValue()), new Tag("Clone Block Entities", cloneBlockEntitiesTag.getJSONValue())));
    }

    /**
     * Uncancels the initial event that<br>
     * triggered this line of code.
     */
    public static void UncancelEvent() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new GameActionBlock("UncancelEvent").SetItems(items));
    }

    /**
     * Spawns primed TNT at a location.
     *
     * @param spawnLocation Spawn location
     * @param tntPower TNT power (0-4) (Optional)
     * @param fuseDuration Fuse duration (Optional)
     * @param customName Custom name (Optional)
     */
    public static void SpawnTNT(ILocation spawnLocation, INumber tntPower, INumber fuseDuration, IText customName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(spawnLocation);
        if(tntPower != null) items.add(tntPower);
        if(fuseDuration != null) items.add(fuseDuration);
        if(customName != null) items.add(customName);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnTNT").SetItems(items));
    }

    /**
     * Spawns an armor stand at a<br>
     * location.
     *
     * @param spawnLocation Spawn location
     * @param customName Custom name (Optional)
     * @param equipment Equipment (Optional)
     */
    public static void SpawnArmorStand(ILocation spawnLocation, IText customName, IItem[] equipment, Tags.Visibility visibilityTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(spawnLocation);
        if(customName != null) items.add(customName);
        if(equipment != null) items.addAll(Arrays.asList(equipment));

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnArmorStand").SetItems(items).SetTags(new Tag("Visibility", visibilityTag.getJSONValue())));
    }

    /**
     * Spawns an experience orb at<br>
     * a location.
     *
     * @param spawnLocation Spawn location
     * @param experienceAmount Experience amount (Optional)
     * @param customName Custom name (Optional)
     */
    public static void SpawnExpOrb(ILocation spawnLocation, INumber experienceAmount, IText customName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(spawnLocation);
        if(experienceAmount != null) items.add(experienceAmount);
        if(customName != null) items.add(customName);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnExpOrb").SetItems(items));
    }

    /**
     * Sets the amount of health<br>
     * regained in this event.
     *
     * @param newHealingAmount New healing amount
     */
    public static void SetEventHeal(INumber newHealingAmount) {
        List<CodeValue> items = new ArrayList<>();

        items.add(newHealingAmount);
        CodeManager.instance.addCodeBlock(new GameActionBlock("SetEventHeal").SetItems(items));
    }

    /**
     * Generates a tree at a location.
     *
     * @param treeLocation Tree location (bottom log block)
     */
    public static void GenerateTree(ILocation treeLocation, Tags.TreeType treeTypeTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(treeLocation);
        CodeManager.instance.addCodeBlock(new GameActionBlock("GenerateTree").SetItems(items).SetTags(new Tag("Tree Type", treeTypeTag.getJSONValue())));
    }

    /**
     * Empties a container at a location.
     *
     * @param containerLocation Container location
     */
    public static void ClearContainer(ILocation containerLocation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(containerLocation);
        CodeManager.instance.addCodeBlock(new GameActionBlock("ClearContainer").SetItems(items));
    }

    /**
     * Sets the objective name of the<br>
     * scoreboard on your plot.
     *
     * @param objectiveName Objective name
     */
    public static void SetScObj(IText objectiveName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(objectiveName);
        CodeManager.instance.addCodeBlock(new GameActionBlock("SetScObj").SetItems(items));
    }

    /**
     * Cancels the initial event that<br>
     * triggered this line of code.
     */
    public static void CancelEvent() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new GameActionBlock("CancelEvent").SetItems(items));
    }

    /**
     * Spawns an end crystal at a<br>
     * location.
     *
     * @param spawnLocation Spawn location
     * @param customName Custom name (Optional)
     */
    public static void SpawnCrystal(ILocation spawnLocation, IText customName, Tags.ShowBottom showBottomTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(spawnLocation);
        if(customName != null) items.add(customName);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnCrystal").SetItems(items).SetTags(new Tag("Show Bottom", showBottomTag.getJSONValue())));
    }

    /**
     * Spawns evoker fangs at a<br>
     * location.
     *
     * @param spawnLocation Spawn location
     * @param customName Custom name (Optional)
     */
    public static void SpawnFangs(ILocation spawnLocation, IText customName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(spawnLocation);
        if(customName != null) items.add(customName);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnFangs").SetItems(items));
    }

    /**
     * Sets the sound to play for<br>
     * this event, replacing the<br>
     * original sound.
     *
     * @param newSound New sound
     */
    public static void SetEventSound(ISound newSound) {
        List<CodeValue> items = new ArrayList<>();

        items.add(newSound);
        CodeManager.instance.addCodeBlock(new GameActionBlock("SetEventSound").SetItems(items));
    }

    /**
     * Sets the item being cooked in<br>
     * one of a campfire's slots.
     *
     * @param campfireLocation Campfire location
     * @param campfireItem Campfire item
     * @param cookingTime Cooking time (ticks) (Optional)
     */
    public static void SetCampfireItem(ILocation campfireLocation, IItem campfireItem, INumber cookingTime, Tags.CampfireSlot campfireSlotTag) {
        List<CodeValue> items = new ArrayList<>();

        items.add(campfireLocation);
        items.add(campfireItem);
        if(cookingTime != null) items.add(cookingTime);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SetCampfireItem").SetItems(items).SetTags(new Tag("Campfire Slot", campfireSlotTag.getJSONValue())));
    }

    /**
     * Sets the amount of experience<br>
     * this event should drop.
     *
     * @param experience Experience
     */
    public static void SetEventXP(INumber experience) {
        List<CodeValue> items = new ArrayList<>();

        items.add(experience);
        CodeManager.instance.addCodeBlock(new GameActionBlock("SetEventXP").SetItems(items));
    }

    /**
     * Sets the lock key of the container<br>
     * at a location.
     *
     * @param containerLocation Container location
     * @param lockKey Lock key (Optional)
     */
    public static void LockContainer(ILocation containerLocation, IText lockKey) {
        List<CodeValue> items = new ArrayList<>();

        items.add(containerLocation);
        if(lockKey != null) items.add(lockKey);
        CodeManager.instance.addCodeBlock(new GameActionBlock("LockContainer").SetItems(items));
    }

    /**
     * Spawns a vehicle at a location.
     *
     * @param vehicleType Vehicle type
     * @param spawnLocation Spawn location
     * @param customName Custom name (Optional)
     */
    public static void SpawnVehicle(IItem vehicleType, ILocation spawnLocation, IText customName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(vehicleType);
        items.add(spawnLocation);
        if(customName != null) items.add(customName);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SpawnVehicle").SetItems(items));
    }

    /**
     * Strikes lightning at a location.
     *
     * @param impactLocation Impact location
     */
    public static void Lightning(ILocation impactLocation) {
        List<CodeValue> items = new ArrayList<>();

        items.add(impactLocation);
        CodeManager.instance.addCodeBlock(new GameActionBlock("Lightning").SetItems(items));
    }

    /**
     * Removes a score from<br>
     * the scoreboard.
     *
     * @param scoreName Score name
     */
    public static void RemoveScore(IText scoreName) {
        List<CodeValue> items = new ArrayList<>();

        items.add(scoreName);
        CodeManager.instance.addCodeBlock(new GameActionBlock("RemoveScore").SetItems(items));
    }

    /**
     * Sets a score on the<br>
     * scoreboard.
     *
     * @param scoreName Score name
     * @param scoreValue Score value (Optional)
     */
    public static void SetScore(IText scoreName, INumber scoreValue) {
        List<CodeValue> items = new ArrayList<>();

        items.add(scoreName);
        if(scoreValue != null) items.add(scoreValue);

        CodeManager.instance.addCodeBlock(new GameActionBlock("SetScore").SetItems(items));
    }

    /**
     * Disables blocks dropping<br>
     * as items when broken.
     */
    public static void BlockDropsOff() {
        List<CodeValue> items = new ArrayList<>();

        CodeManager.instance.addCodeBlock(new GameActionBlock("BlockDropsOff").SetItems(items));
    }

    /**
     * Removes all of an item from<br>
     * the container at a location.
     *
     * @param containerLocation Container location
     * @param item Item(s) to clear
     */
    public static void ClearItems(ILocation containerLocation, IItem... item) {
        List<CodeValue> items = new ArrayList<>();

        items.add(containerLocation);
        items.addAll(Arrays.asList(item));

        CodeManager.instance.addCodeBlock(new GameActionBlock("ClearItems").SetItems(items));
    }

    public static void SignColor(ILocation signLocation, Tags.Glowing glowing, Tags.TextColor textColor) {
        CodeManager.instance.addCodeBlock(new GameActionBlock("SignColor").SetItems(signLocation).SetTags(new Tag("Glowing", glowing.getJSONValue()), new Tag("Text Color", textColor.getJSONValue())));
    }
}