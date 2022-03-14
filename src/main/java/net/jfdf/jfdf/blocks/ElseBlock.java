package net.jfdf.jfdf.blocks;

public class ElseBlock implements CodeBlock {
	
	public String asJSON() {
		String json = "{\"id\":\"block\",\"block\":\"else\"}";
		
		return json;
	}
}