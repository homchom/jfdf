package net.jfdf.jfdf.blocks;

public class BracketBlock implements CodeBlock {
	
	public boolean isClose;
	public boolean isRepeat;
	
	public BracketBlock(boolean isClose, boolean isRepeat) {
		this.isClose = isClose;
		this.isRepeat = isRepeat;
	}

	public String asJSON() {
		return "{\"id\":\"bracket\",\"direct\":\"" + (isClose ? "close" : "open") +"\",\"type\":\"" + (isRepeat ? "repeat" : "norm") +"\"}";
	}
	
}
