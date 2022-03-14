package net.jfdf.jfdf.values;

import java.util.HashMap;
import java.util.Map;

public class Variable implements IItem, ILocation, INumber, IParticle, IPotion, IProjectile, ISound, IText, ISpawnEgg {

	private String name;
	private Scope scope;
	
	public Variable(final Variable variable) {
		this.name = variable.getName();
		this.scope = variable.getScope();
	}
	
	public Variable(final String name, final Scope scope) {
		this.name = name;
		this.scope = scope;
	}

	public String getName() {
		return name;
	}
	
	public Scope getScope() {
		return scope;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "Variable{" +
				"name='" + name + '\'' +
				", scope=" + scope +
				'}';
	}

	public String asJSON() {
		return "{\"id\":\"var\",\"data\":{\"name\":\"" + name + "\",\"scope\":\"" + scope.getJSONValue() + "\"}}";
	}
	
	public enum Scope {
		UNSAVED(0, "unsaved"),
		NORMAL(0, "unsaved"),
		GLOBAL(0, "unsaved"),
		GAME(0, "unsaved"),
		SAVED(1, "saved"),
		LOCAL(2, "local");

		private final static Map<Integer, Scope> values = new HashMap<Integer, Scope>();

		private final int value;
		private final String jsonValue;

		Scope(final int value, final String jsonValue) {
			this.value = value;
			this.jsonValue = jsonValue;
		}

		static {
			for (final Scope type : Scope.values()) {
				values.put(type.getValue(), type);
			}
		}

		public static Scope valueOf(final int type) {
			return values.get(type);
		}

		public int getValue() {
			return value;
		}

		public String getJSONValue() {
			return jsonValue;
		}

		public boolean equals(Scope scope) {
			return this.getValue() == scope.getValue();
		}
	}
}
