package net.jfdf.jfdf.values;

import java.util.HashMap;
import java.util.Map;

public class Projectile extends Item implements IProjectile {
    public Projectile(ProjectileType projectileType) {
        super(projectileType.getItemID());
    }

    public Projectile(PotionProjectileType potionProjectileType, IPotion potion) {
        super(potionProjectileType.getItemID());
    }

    public enum ProjectileType {
        ARROW("arrow"),
        EXPERIENCE_BOTTLE("experience_bottle"),
        EGG("egg"),
        ENDER_PEARL("ender_pearl"),
        EYE_OF_ENDER("ender_eye"),
        FIREBALL("fire_charge"),
        SNOWBALL("snowball"),
        TRIDENT("trident");
        
        private final static Map<Integer, ProjectileType> values = new HashMap<>();
        
        private int value;
        private final String itemId;
        
        ProjectileType(final String itemId) {this.itemId = itemId;}
        static {for (ProjectileType type : ProjectileType.values()) {type.value = values.size(); values.put(type.getValue(), type);}}
        
        public static ProjectileType valueOf(int type) {return values.get(type);}
        
        public int getValue() {return value;}
        
        public String getItemID() {return itemId;}
    }

    public enum PotionProjectileType {
        LINGERING_POTION("lingering_potion"),
        SPLASH_POTION("splash_potion");
        
        private final static Map<Integer, PotionProjectileType> values = new HashMap<>();
        
        private int value;
        private final String itemId;
        
        PotionProjectileType(final String itemId) {this.itemId = itemId;}
        static {for (PotionProjectileType type : PotionProjectileType.values()) {type.value = values.size(); values.put(type.getValue(), type);}}
        
        public static PotionProjectileType valueOf(int type) {return values.get(type);}
        
        public int getValue() {return value;}
        
        public String getItemID() {return itemId;}
    }
}