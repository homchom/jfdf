package net.jfdf.jfdf.values;

public class Particle implements IParticle {
	
	private String type;
	
	public Particle(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Particle{" +
				"type='" + type + '\'' +
				'}';
	}

	public String asJSON() {
		return "{\"id\":\"part\",\"data\":{\"particle\":\"" + type +"\"}}";
	}
}
