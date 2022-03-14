package net.jfdf.jfdf.values;

public class Item implements IItem {

	private String itemID = "stone";
    private String itemNBT = "";
    private int itemCount = 1;

    public Item() {}

    public Item(String itemID) {
        this.itemID = itemID;
    }

    public Item(String itemID, int itemCount) {
        this.itemID = itemID;
        this.itemCount = itemCount;
    }

    public Item(String itemID, int itemCount, String itemNBT) {
        this.itemID = itemID;
        this.itemCount = itemCount;
        this.itemNBT = itemNBT;
    }

	public static Item New() {
		return new Item();
	}

    public static Item New(String itemID) {
		return new Item(itemID);
	}

    public static Item New(String itemID, int itemCount) {
		return new Item(itemID, itemCount);
	}

    public static Item New(String itemID, int itemCount, String itemNBT) {
		return new Item(itemID, itemCount, itemNBT);
	}
	
	public String asJSON() {
		return "{\"id\":\"item\",\"data\":{\"item\":\"{DF_NBT:2230,id:\\\"" + itemID + "\\\"" + (!itemNBT.equals("") ? ",tag:{" + itemNBT.replace("\"", "\\\"")  + "}" : "") + ",Count:" + itemCount + "b}\"}}";
	}
}
