package net.jfdf.jfdf.values;

public class Sound implements ISound {
	private String name;
	private float pitch = 1.0f;
	private float volume = 100.0f;
	
	public Sound(String name) {
		this.name = name;
	}
	
	public Sound setPitch(float pitch) {
		this.pitch = pitch;
		return this;
	}
	
	public Sound setVolume(float volume) {
		this.volume = volume;
		return this;
	}

	@Override
	public String toString() {
		return "Sound{" +
				"name='" + name + '\'' +
				", pitch=" + pitch +
				", volume=" + volume +
				'}';
	}

	public String asJSON() {
		return "{\"id\":\"snd\",\"data\":{\"sound\":\"" + name + "\",\"pitch\":" + pitch + ",\"vol\":" + volume +"}}";
	}

}
