package net.jfdf.jfdf.values;

import java.text.NumberFormat;
import java.util.Locale;

public class Number implements INumber {

	private static final NumberFormat usNumberFormat = NumberFormat.getInstance(Locale.US);
	private String value;

	public static Number New() {
		return new Number();
	}

	public Number Set(int value) {
		this.value = usNumberFormat.format(Math.abs(value)).replace(",", "");

		return value < 0 ? Negative(this) : this;
	}

	public Number Set(long value) {
		this.value = usNumberFormat.format(Math.abs(value)).replace(",", "");

		return value < 0L ? Negative(this) : this;
	}

	public Number Set(float value) {
		this.value = String.format(Locale.US, "%.0f", Math.abs(value));;

		return value < 0.0f ? Negative(this) : this;
	}

	public Number Set(double value) {
		this.value = String.format(Locale.US, "%.0f", Math.abs(value));

		return value < 0.0d ? Negative(this) : this;
	}

	public Number SetToString(String value) {
		this.value = value;

		return this;
	}
	
	public static Number Negative(Number number) {
		return new Number().setValue("%math(0 - " + number.getValue() + ")");
	}
	
	public static Number Add(Number number1, Number number2) {
		return new Number().setValue("%math(" + number1.getValue() + " + " + number2.getValue() + ")");
	}
	
	public static Number Subtract(Number number1, Number number2) {
		return new Number().setValue("%math(" + number1.getValue() + " - " + number2.getValue() + ")");
	}
	
	public static Number Multiplication(Number number1, Number number2) {
		return new Number().setValue("%math(" + number1.getValue() + " * " + number2.getValue() + ")");
	}
	
	public static Number Division(Number number1, Number number2) {
		return new Number().setValue("%math(" + number1.getValue() + " / " + number2.getValue() + ")");
	}

	public static Number Remainder(Number number1, Number number2) {
		return new Number().setValue("%math(" + number1.getValue() + " % " + number2.getValue() + ")");
	}
	
	public static Number Random(Number number1, Number number2) {
		return new Number().setValue("%random(" + number1.getValue() + ", " + number2.getValue() + ")");
	}

	public static Number Round(Number number) {
		return new Number().setValue("%round(" + number.getValue() + ")");
	}
	
	public static Number AsVariable(Variable variable) {
		return new Number().setValue("%var(" + variable.getName() + ")");
	}

	public static Number AsListValue(Variable list, Number index) {
		return new Number().setValue("%index(" + list.getName() + ", " + index.getValue() + ")");
	}

	private Number setValue(String value) {
		this.value = value;

		return this;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Number{" +
				"value='" + value + '\'' +
				'}';
	}

	public String asJSON() {
		return "{\"id\":\"num\",\"data\":{\"name\":\"" + value + "\"}}";
	}
}
