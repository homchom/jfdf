package net.jfdf.jfdf.values;

public class Tag implements CodeValue {
	
	private final String name;
	private final String value;
	private String block;
	private String action;
 
	public Tag(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Tag{" +
				"name='" + name + '\'' +
				", value='" + value + '\'' +
				", block='" + block + '\'' +
				", action='" + action + '\'' +
				'}';
	}

	public String asJSON() {
		if(block == null || action == null) throw new RuntimeException("No Such Block/Action !");
		return "{\"id\":\"bl_tag\",\"data\":{\"option\":\"" + value + "\",\"tag\":\"" + name + "\",\"action\":\"" + action + "\",\"block\":\"" + block + "\"}}";
	}
	
	public void setBlock(String block) {
		this.block = block;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
