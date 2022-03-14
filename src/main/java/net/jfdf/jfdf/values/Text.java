package net.jfdf.jfdf.values;

public class Text implements IText {

	private String value = "";

	public static Text New() {
		return new Text();
	}

	public Text Set(String value) {
		this.value = value;
		return this;
	}

	public String Get() {
		return new String(value);
	}

	@Override
	public String toString() {
		return "Text{" + "value=\"" + value + "\"}";
	}

	public String asJSON() {
		return "{\"id\":\"txt\",\"data\":{\"name\":\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\"}}";
	}

	public static Text AsVariable(Variable variable) {
		return new Text().Set("%var(" + variable.getName() + ")");
	}

	public static Text AsListValue(Variable list, Number index) {
		return new Text().Set("%index(" + list.getName() + ", " + index.getValue() + ")");
	}
}
