package net.jfdf.jfdf.values;

public class Tags {
    public enum ReducedDebugInfoEnabled {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        ReducedDebugInfoEnabled(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum BarSlot {
        SLOT_1("Slot 1"),
        SLOT_2("Slot 2"),
        SLOT_3("Slot 3"),
        SLOT_4("Slot 4"),
        SLOT_5("Slot 5"),
        SLOT_6("Slot 6"),
        SLOT_7("Slot 7"),
        SLOT_8("Slot 8"),
        SLOT_9("Slot 9");

        private final String jsonValue;

        BarSlot(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum BarStyle {
        SOLID("Solid"),
        VALUE_6_SEGMENTS("6 segments"),
        VALUE_10_SEGMENTS("10 segments"),
        VALUE_12_SEGMENTS("12 segments"),
        VALUE_20_SEGMENTS("20 segments");

        private final String jsonValue;

        BarStyle(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SkyEffect {
        NONE("None"),
        CREATE_FOG("Create fog"),
        DARKEN_SKY("Darken sky"),
        BOTH("Both");

        private final String jsonValue;

        SkyEffect(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum BarColor {
        RED("Red"),
        PURPLE("Purple"),
        PINK("Pink"),
        BLUE("Blue"),
        GREEN("Green"),
        YELLOW("Yellow"),
        WHITE("White");

        private final String jsonValue;

        BarColor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AddtoCurrentVelocity {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        AddtoCurrentVelocity(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum NewRowPosition {
        TOP_ROW("Top row"),
        BOTTOM_ROW("Bottom row");

        private final String jsonValue;

        NewRowPosition(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SetExperience {
        POINTS("Points"),
        LEVEL("Level"),
        LEVEL_PERCENTAGE("Level Percentage");

        private final String jsonValue;

        SetExperience(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AnimationType {
        HURT_ANIMATION("Hurt animation"),
        WAKE_UP__FADE_EFFECT_("Wake up (fade effect)");

        private final String jsonValue;

        AnimationType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum InventoryKept {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        InventoryKept(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AllowFlight {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        AllowFlight(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HealPlayertoMaxHealth {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        HealPlayertoMaxHealth(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IgnoreBlocks {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IgnoreBlocks(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum FlightMode {
        START_FLIGHT("Start Flight"),
        STOP_FLIGHT("Stop Flight");

        private final String jsonValue;

        FlightMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum PlayerListField {
        HEADER("Header"),
        FOOTER("Footer");

        private final String jsonValue;

        PlayerListField(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Gamemode {
        SURVIVAL("Survival"),
        CREATIVE("Creative"),
        ADVENTURE("Adventure");

        private final String jsonValue;

        Gamemode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum RowtoRemove {
        TOP_ROW("Top row"),
        BOTTOM_ROW("Bottom row");

        private final String jsonValue;

        RowtoRemove(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HealType {
        REGULAR_HEALTH("Regular Health"),
        ABSORPTION_HEALTH("Absorption Health"),
        COMBINED_HEALTH("Combined Health");

        private final String jsonValue;

        HealType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ClearMode {
        ENTIRE_INVENTORY("Entire inventory"),
        MAIN_INVENTORY("Main inventory"),
        UPPER_INVENTORY("Upper inventory"),
        HOTBAR("Hotbar"),
        ARMOR("Armor");

        private final String jsonValue;

        ClearMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ClearCraftingAndCursor {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        ClearCraftingAndCursor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Gliding {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Gliding(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Flying {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Flying(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ContainerState {
        OPEN("Open"),
        CLOSED("Closed");

        private final String jsonValue;

        ContainerState(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HandSlot {
        MAIN_HAND("Main Hand"),
        OFF_HAND("Off Hand");

        private final String jsonValue;

        HandSlot(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ToastType {
        ADVANCEMENT("Advancement"),
        GOAL("Goal"),
        CHALLENGE("Challenge");

        private final String jsonValue;

        ToastType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum KeepCurrentRotation {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        KeepCurrentRotation(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum PVP {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        PVP(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IgnoreDistance {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IgnoreDistance(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum DisguiseVisible {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        DisguiseVisible(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum GiveExperience {
        POINTS("Points"),
        LEVELS("Levels"),
        LEVEL_PERCENTAGE("Level percentage");

        private final String jsonValue;

        GiveExperience(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum TextValueMerging {
        ADD_SPACES("Add spaces"),
        NO_SPACES("No spaces");

        private final String jsonValue;

        TextValueMerging(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AlignmentMode {
        REGULAR("Regular"),
        CENTERED("Centered");

        private final String jsonValue;

        AlignmentMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum LocationAlignmentMode {
        BLOCK_CENTER("Block center"),
        LOWER_BLOCK_CORNER("Lower block corner");

        private final String jsonValue;

        LocationAlignmentMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum TextColor {
        WHITE("White"),
        ORANGE("Orange"),
        MAGENTA("Magenta"),
        LIGHT_BLUE("Light blue"),
        YELLOW("Yellow"),
        LIME("Lime"),
        PINK("Pink"),
        GRAY("Gray"),
        LIGHT_GRAY("Light gray"),
        CYAN("Cyan"),
        PURPLE("Purple"),
        BLUE("Blue"),
        BROWN("Brown"),
        GREEN("Green"),
        RED("Red"),
        BLACK("Black");

        private final String jsonValue;

        TextColor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SpeedType {
        GROUND_SPEED("Ground speed"),
        FLIGHT_SPEED("Flight speed"),
        BOTH("Both");

        private final String jsonValue;

        SpeedType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Weather {
        CLEAR("Clear"),
        DOWNFALL("Downfall"),
        RESET("Reset");

        private final String jsonValue;

        Weather(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum FillType {
        WIREFRAME("Wireframe"),
        HOLLOW("Hollow"),
        SOLID("Solid");

        private final String jsonValue;

        FillType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ShowIcon {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        ShowIcon(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum OverwriteEffect {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        OverwriteEffect(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum EffectParticles {
        REGULAR("Regular"),
        AMBIENT("Ambient"),
        NONE("None");

        private final String jsonValue;

        EffectParticles(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SoundSource {
        MASTER("Master"),
        MUSIC("Music"),
        JUKEBOX_NOTE_BLOCKS("Jukebox/Note Blocks"),
        WEATHER("Weather"),
        BLOCKS("Blocks"),
        HOSTILE_CREATURES("Hostile Creatures"),
        FRIENDLY_CREATURES("Friendly Creatures"),
        PLAYERS("Players"),
        AMBIENT_ENVIRONMENT("Ambient/Environment"),
        VOICE_SPEECH("Voice/Speech");

        private final String jsonValue;

        SoundSource(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Collision {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Collision(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum LaunchAxis {
        PITCH_AND_YAW("Pitch and Yaw"),
        YAW_ONLY("Yaw Only");

        private final String jsonValue;

        LaunchAxis(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum BossBarSlot {
        ALL_BOSS_BARS("All boss bars"),
        VALUE_1("1"),
        VALUE_2("2"),
        VALUE_3("3"),
        VALUE_4("4"),
        VALUE_5("5"),
        VALUE_6("6"),
        VALUE_7("7"),
        VALUE_8("8"),
        VALUE_9("9");

        private final String jsonValue;

        BossBarSlot(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum EquipmentSlot {
        MAIN_HAND("Main hand"),
        OFF_HAND("Off hand"),
        HEAD("Head"),
        CHEST("Chest"),
        LEGS("Legs"),
        FEET("Feet");

        private final String jsonValue;

        EquipmentSlot(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AnimationArm {
        SWING_MAIN_ARM("Swing main arm"),
        SWING_OFF_ARM("Swing off arm");

        private final String jsonValue;

        AnimationArm(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SpawnDeathDrops {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        SpawnDeathDrops(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum OverwritePreviousFracture {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        OverwritePreviousFracture(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Hidden {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Hidden(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum InstantRespawn {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        InstantRespawn(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum FluidMode {
        IGNORE_FLUIDS("Ignore fluids"),
        DETECT_FLUIDS("Detect fluids");

        private final String jsonValue;

        FluidMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CheckMode {
        HAS_ROOM_FOR_ANY_ITEM("Has Room for Any Item"),
        HAS_ROOM_FOR_ALL_ITEMS("Has Room for All Items");

        private final String jsonValue;

        CheckMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CheckedSlots {
        ENTIRE_INVENTORY("Entire inventory"),
        MAIN_INVENTORY("Main inventory"),
        UPPER_INVENTORY("Upper inventory"),
        HOTBAR("Hotbar"),
        ARMOR("Armor");

        private final String jsonValue;

        CheckedSlots(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Shape {
        SPHERE("Sphere"),
        CIRCLE("Circle"),
        CUBE("Cube"),
        SQUARE("Square");

        private final String jsonValue;

        Shape(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CompareTextTo {
        ENTITY_TYPE("Entity type"),
        NAME_OR_UUID("Name or UUID");

        private final String jsonValue;

        CompareTextTo(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IgnoreCase {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IgnoreCase(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Permission {
        OWNER("Owner"),
        DEVELOPER("Developer"),
        BUILDER("Builder"),
        DEVELOPER_OR_BUILDER("Developer or builder"),
        WHITELISTED("Whitelisted");

        private final String jsonValue;

        Permission(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CheckProperties {
        NONE("None"),
        AMPLIFIER("Amplifier"),
        DURATION("Duration"),
        AMPLIFIER_AND_DURATION("Amplifier and duration");

        private final String jsonValue;

        CheckProperties(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum InventoryType {
        ANY_INVENTORY("Any Inventory"),
        PLOT_MENU("Plot Menu"),
        CRAFTING_TABLE("Crafting Table"),
        CHEST("Chest"),
        DOUBLE_CHEST("Double Chest"),
        ENDER_CHEST("Ender Chest"),
        SHULKER_BOX("Shulker Box"),
        BARREL("Barrel"),
        FURNACE__ANY_("Furnace (any)"),
        FURNACE("Furnace"),
        BLAST_FURNACE("Blast Furnace"),
        SMOKER("Smoker"),
        DROPPER("Dropper"),
        DISPENSER("Dispenser"),
        BEACON("Beacon"),
        HOPPER("Hopper"),
        ANVIL("Anvil"),
        BREWING_STAND("Brewing Stand"),
        CARTOGRAPHY_TABLE("Cartography Table"),
        LOOM("Loom"),
        GRINDSTONE("Grindstone"),
        STONECUTTER("Stonecutter"),
        ENCHANTING_TABLE("Enchanting Table"),
        TRADER_MENU__ANY_("Trader Menu (any)"),
        VILLAGER_MENU("Villager Menu"),
        WANDERING_TRADER_MENU("Wandering Trader Menu"),
        HORSE_INVENTORY("Horse Inventory"),
        LLAMA_INVENTORY("Llama Inventory");

        private final String jsonValue;

        InventoryType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum TargetMode {
        WITH_CURRENT_TARGETS("With current targets"),
        WITH_CURRENT_SELECTION("With current selection"),
        WITH_NO_TARGETS("With no targets"),
        FOR_EACH_IN_SELECTION("For each in selection");

        private final String jsonValue;

        TargetMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum LocalVariables {
        DON_T_COPY("Don't copy"),
        COPY("Copy"),
        SHARE("Share");

        private final String jsonValue;

        LocalVariables(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum TimeUnit {
        TICKS("Ticks"),
        SECONDS("Seconds"),
        MINUTES("Minutes");

        private final String jsonValue;

        TimeUnit(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum MatchRequirement {
        ENTIRE_NAME("Entire name"),
        FULL_WORD_S__IN_NAME("Full word(s) in name"),
        ANY_PART_OF_NAME("Any part of name");

        private final String jsonValue;

        MatchRequirement(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AddLocationRotation {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        AddLocationRotation(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Attribute {
        ARMOR("Armor"),
        ARMOR_TOUGHNESS("Armor toughness"),
        ATTACK_DAMAGE("Attack damage"),
        ATTACK_SPEED("Attack speed"),
        MAXIMUM_HEALTH("Maximum health"),
        KNOCKBACK_RESISTANCE("Knockback resistance"),
        MOVEMENT_SPEED("Movement speed"),
        FOLLOW_RANGE("Follow range");

        private final String jsonValue;

        Attribute(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ActiveEquipmentSlot {
        ANY("Any"),
        MAIN_HAND("Main hand"),
        OFF_HAND("Off hand"),
        HEAD("Head"),
        BODY("Body"),
        LEGS("Legs"),
        FEET("Feet");

        private final String jsonValue;

        ActiveEquipmentSlot(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum RotationAxis {
        PITCH("Pitch"),
        YAW("Yaw");

        private final String jsonValue;

        RotationAxis(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum DivisionMode {
        DEFAULT("Default"),
        FLOOR_RESULT("Floor result");

        private final String jsonValue;

        DivisionMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SignLine {
        VALUE_1("1"),
        VALUE_2("2"),
        VALUE_3("3"),
        VALUE_4("4"),
        ALL_LINES("All lines");

        private final String jsonValue;

        SignLine(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Operator {
        OR("|"),
        AND("&"),
        NOT("~"),
        XOR("^"),
        LEFT_SHIFT("<<"),
        RIGHT_SHIFT(">>"),
        UNSIGNED_RIGHT_SHIFT(">>>");

        private final String jsonValue;

        Operator(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Coordinate {
        X("X"),
        Y("Y"),
        Z("Z");

        private final String jsonValue;

        Coordinate(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Component {
        X("X"),
        Y("Y"),
        Z("Z");

        private final String jsonValue;

        Component(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum RegularExpressions {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        RegularExpressions(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Operation {
        ADD_NUMBER("Add number"),
        ADD_PERCENTAGE_TO_BASE("Add percentage to base"),
        MULTIPLY_MODIFIER_BY_PERCENTAGE("Multiply modifier by percentage");

        private final String jsonValue;

        Operation(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Coordinates {
        ALL_COORDINATES("All coordinates"),
        X_AND_Z("X and Z"),
        ONLY_Y("Only Y");

        private final String jsonValue;

        Coordinates(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Rotation {
        KEEP_ROTATION("Keep rotation"),
        REMOVE_ROTATION("Remove rotation");

        private final String jsonValue;

        Rotation(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum RoundingMode {
        WHOLE_NUMBER("Whole number"),
        DECIMAL_NUMBER("Decimal number");

        private final String jsonValue;

        RoundingMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum EntityCollision {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        EntityCollision(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum BlockCollision {
        ALL_BLOCKS("All blocks"),
        NON_FLUID_BLOCKS("Non-fluid blocks"),
        SOLID_BLOCKS("Solid blocks"),
        NONE("None");

        private final String jsonValue;

        BlockCollision(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AngleUnits {
        DEGREES("Degrees"),
        RADIANS("Radians");

        private final String jsonValue;

        AngleUnits(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SineVariant {
        SINE("Sine"),
        INVERSE_SINE__ARCSINE_("Inverse sine (arcsine)"),
        HYPERBOLIC_SINE("Hyperbolic sine");

        private final String jsonValue;

        SineVariant(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Input {
        DEGREES("Degrees"),
        RADIANS("Radians");

        private final String jsonValue;

        Input(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum FaceDirection {
        TOWARD_LOCATION("Toward location"),
        AWAY_FROM_LOCATION("Away from location");

        private final String jsonValue;

        FaceDirection(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Distribution {
        NORMAL("Normal"),
        FOLDED_NORMAL("Folded normal");

        private final String jsonValue;

        Distribution(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum DurabilityType {
        SET_DAMAGE("Set Damage"),
        SET_DAMAGE_PERCENTAGE("Set Damage Percentage"),
        SET_REMAINING("Set Remaining"),
        SET_REMAINING_PERCENTAGE("Set Remaining Percentage");

        private final String jsonValue;

        DurabilityType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Breakability {
        BREAKABLE("Breakable"),
        UNBREAKABLE("Unbreakable");

        private final String jsonValue;

        Breakability(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CoordinateType {
        PLOT_COORDINATE("Plot coordinate"),
        WORLD_COORDINATE("World coordinate");

        private final String jsonValue;

        CoordinateType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ReturnType {
        TEXT__3D_("Text (3D)"),
        TEXT__2D_("Text (2D)"),
        VECTOR("Vector");

        private final String jsonValue;

        ReturnType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Axis {
        X("X"),
        Y("Y"),
        Z("Z");

        private final String jsonValue;

        Axis(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Direction {
        FORWARD("Forward"),
        UPWARD("Upward"),
        SIDEWAYS("Sideways");

        private final String jsonValue;

        Direction(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ReplacementType {
        FIRST_OCCURRENCE("First occurrence"),
        ALL_OCCURRENCES("All occurrences");

        private final String jsonValue;

        ReplacementType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum RequireLodestoneatLocation {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        RequireLodestoneatLocation(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CapitalizationType {
        UPPERCASE("UPPERCASE"),
        LOWERCASE("lowercase"),
        PROPER_CASE("Proper Case"),
        INVERT_CASE("iNVERT CASE"),
        RANDOM_CASE("RAnDoM cASe");

        private final String jsonValue;

        CapitalizationType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum LightType {
        COMBINED_LIGHT("Combined light"),
        SKY_LIGHT("Sky light"),
        BLOCK_LIGHT("Block light");

        private final String jsonValue;

        LightType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum DistanceType {
        DISTANCE_2D__X_Z_("Distance 2D (X/Z)"),
        DISTANCE_3D__X_Y_Z_("Distance 3D (X/Y/Z)"),
        ALTITUDE__Y_("Altitude (Y)");

        private final String jsonValue;

        DistanceType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ReturnValueType {
        ITEM_ID__GOLDEN_APPLE_("Item ID (golden_apple)"),
        ITEM_NAME__GOLDEN_APPLE_("Item Name (Golden Apple)"),
        ITEM("Item");

        private final String jsonValue;

        ReturnValueType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HideDefault {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        HideDefault(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Spread {
        HORIZONTAL("Horizontal"),
        VERTICAL("Vertical");

        private final String jsonValue;

        Spread(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum TextValue {
        OWNER_NAME("Owner Name"),
        OWNER_UUID("Owner UUID");

        private final String jsonValue;

        TextValue(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ColorChannels {
        RGB("RGB"),
        HSB("HSB"),
        HSL("HSL");

        private final String jsonValue;

        ColorChannels(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum TangentVariant {
        TANGENT("Tangent"),
        INVERSE_TANGENT__ARCTANGENT_("Inverse tangent (arctangent)"),
        HYPERBOLIC_TANGENT("Hyperbolic tangent");

        private final String jsonValue;

        TangentVariant(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CellEdgeType {
        EUCLIDEAN("Euclidean"),
        MANHATTAN("Manhattan"),
        NATURAL("Natural");

        private final String jsonValue;

        CellEdgeType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IgnorePassableBlocks {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IgnorePassableBlocks(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum FluidCollision {
        IGNORE_FLUIDS("Ignore fluids"),
        DETECT_FLUIDS("Detect fluids"),
        SOURCE_BLOCKS_ONLY("Source blocks only");

        private final String jsonValue;

        FluidCollision(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum FractalType {
        BROWNIAN("Brownian"),
        BILLOW__DARK_EDGES_("Billow (Dark edges)"),
        RIGID__LIGHT_EDGES_("Rigid (Light edges)");

        private final String jsonValue;

        FractalType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum DistanceCalculation {
        PRIMARY("Primary"),
        SECONDARY("Secondary"),
        ADDITIVE("Additive"),
        SUBTRACTIVE("Subtractive"),
        MULTIPLICATIVE("Multiplicative"),
        DIVISIVE("Divisive");

        private final String jsonValue;

        DistanceCalculation(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SortOrder {
        ASCENDING("Ascending"),
        DESCENDING("Descending");

        private final String jsonValue;

        SortOrder(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Format {
        CUSTOM("Custom"),
        VALUE_2020_08_17_17_20_54("2020/08/17 17:20:54"),
        VALUE_2020_08_17("2020/08/17"),
        MON__AUGUST_17("Mon, August 17"),
        MONDAY("Monday"),
        VALUE_17_20_54("17:20:54"),
        VALUE_5_20_PM("5:20 PM"),
        VALUE_17H20M54S("17h20m54s"),
        VALUE_54_229_SECONDS("54.229 seconds");

        private final String jsonValue;

        Format(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HideColor {
        TRUE("True"),
        FALSE("False"),
        NO_CHANGE("No Change");

        private final String jsonValue;

        HideColor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HideEnchantments {
        TRUE("True"),
        FALSE("False"),
        NO_CHANGE("No Change");

        private final String jsonValue;

        HideEnchantments(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HideAttributes {
        TRUE("True"),
        FALSE("False"),
        NO_CHANGE("No Change");

        private final String jsonValue;

        HideAttributes(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HideUnbreakable {
        TRUE("True"),
        FALSE("False"),
        NO_CHANGE("No Change");

        private final String jsonValue;

        HideUnbreakable(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HideCanDestroy {
        TRUE("True"),
        FALSE("False"),
        NO_CHANGE("No Change");

        private final String jsonValue;

        HideCanDestroy(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HideCanPlaceOn {
        TRUE("True"),
        FALSE("False"),
        NO_CHANGE("No Change");

        private final String jsonValue;

        HideCanPlaceOn(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HidePotionEffects {
        TRUE("True"),
        FALSE("False"),
        NO_CHANGE("No Change");

        private final String jsonValue;

        HidePotionEffects(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IgnoreEmptySlots {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IgnoreEmptySlots(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CosineVariant {
        COSINE("Cosine"),
        INVERSE_COSINE__ARCCOSINE_("Inverse cosine (arccosine)"),
        HYPERBOLIC_COSINE("Hyperbolic cosine");

        private final String jsonValue;

        CosineVariant(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IgnorePitch {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IgnorePitch(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SearchOrder {
        ASCENDING__FIRST_INDEX_("Ascending (first index)"),
        DESCENDING__LAST_INDEX_("Descending (last index)");

        private final String jsonValue;

        SearchOrder(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ShiftDirection {
        ____UPWARDS_______DOWNWARDS("(+) Upwards / (-) Downwards"),
        ____FORWARDS_______BACKWARDS("(+) Forwards / (-) Backwards"),
        ____RIGHT_______LEFT("(+) Right / (-) Left");

        private final String jsonValue;

        ShiftDirection(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum RoundMode {
        FLOOR("Floor"),
        NEAREST("Nearest"),
        CEILING("Ceiling");

        private final String jsonValue;

        RoundMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum TranslationType {
        FROM_HEX_TO_COLOR("From hex to color"),
        FROM___TO_COLOR("From & to color"),
        FROM_COLOR_TO__("From color to &"),
        STRIP_COLOR("Strip color");

        private final String jsonValue;

        TranslationType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum GrowthUnit {
        GROWTH_STAGE_NUMBER("Growth stage number"),
        GROWTH_PERCENTAGE("Growth percentage");

        private final String jsonValue;

        GrowthUnit(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum LengthType {
        LENGTH("Length"),
        LENGTH_SQUARED("Length Squared");

        private final String jsonValue;

        LengthType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IsHidden {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IsHidden(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ParrotColor {
        RED("Red"),
        BLUE("Blue"),
        GREEN("Green"),
        CYAN("Cyan"),
        GRAY("Gray");

        private final String jsonValue;

        ParrotColor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Sheared {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Sheared(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IsSitting {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        IsSitting(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AxolotlColor {
        PINK("Pink"),
        BROWN("Brown"),
        YELLOW("Yellow"),
        CYAN("Cyan"),
        BLUE("Blue");

        private final String jsonValue;

        AxolotlColor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HorseColor {
        WHITE("White"),
        BUCKSKIN("Buckskin"),
        FLAXEN_CHESTNUT("Flaxen chestnut"),
        BAY("Bay"),
        BLACK("Black"),
        DAPPLE_GRAY("Dapple gray"),
        DARK_BAY("Dark bay"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        HorseColor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HorseMarkings {
        NO_MARKINGS("No markings"),
        STOCKINGS_AND_BLAZE("Stockings and blaze"),
        PAINT("Paint"),
        SNOWFLAKE_APPALOOSA("Snowflake appaloosa"),
        SOOTY("Sooty"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        HorseMarkings(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AI {
        SENTIENT("Sentient"),
        INSENTIENT("Insentient"),
        NONE("None");

        private final String jsonValue;

        AI(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Riptiding {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Riptiding(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Leaping {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Leaping(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SetGene {
        MAIN_GENE("Main gene"),
        HIDDEN_GENE("Hidden gene"),
        BOTH("Both");

        private final String jsonValue;

        SetGene(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum GeneType {
        AGGRESSIVE("Aggressive"),
        LAZY("Lazy"),
        WEAK("Weak"),
        WORRIED("Worried"),
        PLAYFUL("Playful"),
        NORMAL("Normal"),
        BROWN("Brown");

        private final String jsonValue;

        GeneType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Dye {
        WHITE("White"),
        ORANGE("Orange"),
        MAGENTA("Magenta"),
        LIGHT_BLUE("Light blue"),
        YELLOW("Yellow"),
        LIME("Lime"),
        PINK("Pink"),
        GRAY("Gray"),
        LIGHT_GRAY("Light gray"),
        CYAN("Cyan"),
        PURPLE("Purple"),
        BLUE("Blue"),
        BROWN("Brown"),
        GREEN("Green"),
        RED("Red"),
        BLACK("Black");

        private final String jsonValue;

        Dye(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HealMobtoMaxHealth {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        HealMobtoMaxHealth(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AgeLock {
        ENABLE("Enable"),
        DISABLE("Disable"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        AgeLock(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Profession {
        UNEMPLOYED("Unemployed"),
        ARMORER("Armorer"),
        BUTCHER("Butcher"),
        CARTOGRAPHER("Cartographer"),
        CLERIC("Cleric"),
        FARMER("Farmer"),
        FISHERMAN("Fisherman"),
        FLETCHER("Fletcher"),
        LEATHERWORKER("Leatherworker"),
        LIBRARIAN("Librarian"),
        MASON("Mason"),
        NITWIT("Nitwit"),
        SHEPHERD("Shepherd"),
        TOOLSMITH("Toolsmith"),
        WEAPONSMITH("Weaponsmith");

        private final String jsonValue;

        Profession(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ArmsRaised {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        ArmsRaised(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Arms {
        ENABLE("Enable"),
        DISABLE("Disable"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        Arms(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum BasePlate {
        ENABLE("Enable"),
        DISABLE("Disable"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        BasePlate(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Invulnerable {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Invulnerable(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IsVisible {
        TRUE("True"),
        FALSE("False"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        IsVisible(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IsMarker_NoHitbox {
        TRUE("True"),
        FALSE("False"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        IsMarker_NoHitbox(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AllowItemTaking_Adding {
        TRUE("True"),
        FALSE("False"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        AllowItemTaking_Adding(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HasPhysics_Updates {
        TRUE("True"),
        FALSE("False"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        HasPhysics_Updates(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IsSmall {
        TRUE("True"),
        FALSE("False"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        IsSmall(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HasArms {
        TRUE("True"),
        FALSE("False"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        HasArms(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HasBasePlate {
        TRUE("True"),
        FALSE("False"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        HasBasePlate(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Marker {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Marker(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum NameTagVisible {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        NameTagVisible(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Pose {
        STANDING("Standing"),
        SLEEPING("Sleeping"),
        SWIMMING("Swimming"),
        SNEAKING("Sneaking");

        private final String jsonValue;

        Pose(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Rearing {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Rearing(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Charged {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Charged(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Gravity {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Gravity(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HideNameTag {
        TRUE("True"),
        FALSE("False"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        HideNameTag(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum PatternColor {
        WHITE("White"),
        ORANGE("Orange"),
        MAGENTA("Magenta"),
        LIGHT_BLUE("Light blue"),
        YELLOW("Yellow"),
        LIME("Lime"),
        PINK("Pink"),
        GRAY("Gray"),
        LIGHT_GRAY("Light gray"),
        CYAN("Cyan"),
        PURPLE("Purple"),
        BLUE("Blue"),
        BROWN("Brown"),
        GREEN("Green"),
        RED("Red"),
        BLACK("Black"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        PatternColor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum BodyColor {
        WHITE("White"),
        ORANGE("Orange"),
        MAGENTA("Magenta"),
        LIGHT_BLUE("Light blue"),
        YELLOW("Yellow"),
        LIME("Lime"),
        PINK("Pink"),
        GRAY("Gray"),
        LIGHT_GRAY("Light gray"),
        CYAN("Cyan"),
        PURPLE("Purple"),
        BLUE("Blue"),
        BROWN("Brown"),
        GREEN("Green"),
        RED("Red"),
        BLACK("Black"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        BodyColor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Pattern {
        KOB("Kob"),
        SUNSTREAK("Sunstreak"),
        SNOOPER("Snooper"),
        DASHER("Dasher"),
        BRINELY("Brinely"),
        SPOTTY("Spotty"),
        FLOPPER("Flopper"),
        STRIPEY("Stripey"),
        GLITTER("Glitter"),
        BLOCKFISH("Blockfish"),
        BETTY("Betty"),
        CLAYFISH("Clayfish"),
        DON_T_CHANGE("Don't change");

        private final String jsonValue;

        Pattern(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum SkinType {
        TABBY("Tabby"),
        TUXEDO("Tuxedo"),
        RED("Red"),
        SIAMESE("Siamese"),
        BRITISH_SHORTHAIR("British Shorthair"),
        CALICO("Calico"),
        PERSIAN("Persian"),
        RAGDOLL("Ragdoll"),
        WHITE("White"),
        JELLIE("Jellie"),
        BLACK("Black");

        private final String jsonValue;

        SkinType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Saddle {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Saddle(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Phase {
        FLYING("Flying"),
        HOVERING("Hovering"),
        BREATH_ATTACK("Breath attack"),
        DYING("Dying");

        private final String jsonValue;

        Phase(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum LlamaColor {
        BROWN("Brown"),
        CREAMY("Creamy"),
        WHITE("White"),
        GRAY("Gray");

        private final String jsonValue;

        LlamaColor(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Biome {
        DESERT("Desert"),
        JUNGLE("Jungle"),
        PLAINS("Plains"),
        SAVANNA("Savanna"),
        SNOW("Snow"),
        SWAMP("Swamp"),
        TAIGA("Taiga");

        private final String jsonValue;

        Biome(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Baby {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Baby(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum MooshroomVariant {
        RED("Red"),
        BROWN("Brown");

        private final String jsonValue;

        MooshroomVariant(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Invisible {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Invisible(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Resting {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Resting(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Glowing {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Glowing(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Screams {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Screams(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Sleeping {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Sleeping(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ArmorStandPart {
        HEAD("Head"),
        BODY("Body"),
        LEFT_ARM("Left Arm"),
        RIGHT_ARM("Right Arm"),
        LEFT_LEG("Left Leg"),
        RIGHT_LEG("Right Leg");

        private final String jsonValue;

        ArmorStandPart(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Silenced {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Silenced(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HasNectar {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        HasNectar(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Pumpkin {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Pumpkin(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Interactions {
        TAKE__SWAP_OR_PLACE_ITEM("Take, swap or place item"),
        TAKE_OR_SWAP_ITEM("Take or swap item"),
        TAKE_ITEM("Take item"),
        PLACE_ITEM("Place item"),
        NONE("None");

        private final String jsonValue;

        Interactions(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Angry {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Angry(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HasDeathDrops {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        HasDeathDrops(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Persistent {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Persistent(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Celebrate {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        Celebrate(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CarryingChest {
        ENABLE("Enable"),
        DISABLE("Disable");

        private final String jsonValue;

        CarryingChest(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum FoxType {
        RED("Red"),
        SNOW("Snow");

        private final String jsonValue;

        FoxType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum VariableType {
        NUMBER("Number"),
        TEXT("Text"),
        LOCATION("Location"),
        ITEM("Item"),
        LIST("List"),
        POTION_EFFECT("Potion effect"),
        SOUND("Sound"),
        PARTICLE("Particle"),
        VECTOR("Vector");

        private final String jsonValue;

        VariableType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ComparisonMode {
        EXACTLY_EQUALS("Exactly equals"),
        IGNORE_STACK_SIZE("Ignore stack size"),
        IGNORE_DURABILITY_AND_STACK_SIZE("Ignore durability and stack size"),
        MATERIAL_ONLY("Material only");

        private final String jsonValue;

        ComparisonMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IgnoreY_Axis {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IgnoreY_Axis(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CompareMode {
        NEAREST("Nearest"),
        FARTHEST("Farthest");

        private final String jsonValue;

        CompareMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum EventTarget {
        DEFAULT("Default"),
        KILLER("Killer"),
        DAMAGER("Damager"),
        VICTIM("Victim"),
        SHOOTER("Shooter"),
        PROJECTILE("Projectile");

        private final String jsonValue;

        EventTarget(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ShowParticles {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        ShowParticles(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum HurtHitEntities {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        HurtHitEntities(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ReformonImpact {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        ReformonImpact(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum RequestMethod {
        POST("Post"),
        GET("Get"),
        PUT("Put"),
        DELETE("Delete");

        private final String jsonValue;

        RequestMethod(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ContentType {
        TEXT_PLAIN("text/plain"),
        APPLICATION_JSON("application/json");

        private final String jsonValue;

        ContentType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum EndofLifespan {
        DROP_ITEM("Drop item"),
        SHATTER("Shatter"),
        RANDOM("Random");

        private final String jsonValue;

        EndofLifespan(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Instant {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        Instant(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Movement {
        UPWARDS("Upwards"),
        DIRECTIONAL("Directional");

        private final String jsonValue;

        Movement(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum DelayUnit {
        TICKS("Ticks"),
        SECONDS("Seconds"),
        MINUTES("Minutes");

        private final String jsonValue;

        DelayUnit(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ApplyItemMotion {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        ApplyItemMotion(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IgnoreAir {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IgnoreAir(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CloneBlockEntities {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        CloneBlockEntities(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Visibility {
        VISIBLE("Visible"),
        VISIBLE__NO_HITBOX_("Visible (No hitbox)"),
        INVISIBLE("Invisible"),
        INVISIBLE__NO_HITBOX_("Invisible (No hitbox)");

        private final String jsonValue;

        Visibility(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum TreeType {
        OAK_TREE("Oak Tree"),
        BIG_OAK_TREE("Big Oak Tree"),
        SWAMP_TREE("Swamp Tree"),
        SPRUCE_TREE("Spruce Tree"),
        SLIGHTLY_TALLER_SPRUCE_TREE("Slightly Taller Spruce Tree"),
        BIG_SPRUCE_TREE("Big Spruce Tree"),
        BIRCH_TREE("Birch Tree"),
        TALL_BIRCH_TREE("Tall Birch Tree"),
        JUNGLE_TREE("Jungle Tree"),
        BIG_JUNGLE_TREE("Big Jungle Tree"),
        JUNGLE_BUSH("Jungle Bush"),
        ACACIA_TREE("Acacia Tree"),
        DARK_OAK_TREE("Dark Oak Tree"),
        RED_MUSHROOM("Red Mushroom"),
        BROWN_MUSHROOM("Brown Mushroom"),
        CRIMSON_FUNGUS("Crimson Fungus"),
        WARPED_FUNGUS("Warped Fungus"),
        CHORUS_PLANT("Chorus Plant");

        private final String jsonValue;

        TreeType(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ShowBottom {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        ShowBottom(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum CampfireSlot {
        VALUE_1("1"),
        VALUE_2("2"),
        VALUE_3("3"),
        VALUE_4("4");

        private final String jsonValue;

        CampfireSlot(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum ChangeLocationRotation {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        ChangeLocationRotation(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum IncludeOriginBlock {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        IncludeOriginBlock(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum RotateLocation {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        RotateLocation(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum PointLocationsInwards {
        TRUE("True"),
        FALSE("False");

        private final String jsonValue;

        PointLocationsInwards(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum AllowListChanges {
        TRUE("True"),
        FALSE__COPY_LIST_("False (copy list)");

        private final String jsonValue;

        AllowListChanges(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }

    public enum RedstonePowerMode {
        DIRECT_POWER("Direct power"),
        INDIRECT_POWER("Indirect power");

        private final String jsonValue;

        RedstonePowerMode(final String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJSONValue() {
            return this.jsonValue;
        }
    }
}