package net.teamws.huemodoro.web.hue.state;

public class HueBodyColorChange {

	private boolean on = true;
	private int sat = 254;
	private int bri = 120;
	private int hue = 5000;
	private String effect = "none";

	public boolean isOn() {
		return on;
	}

	public int getSat() {
		return sat;
	}

	public int getBri() {
		return bri;
	}

	public int getHue() {
		return hue;
	}

	public void setHue(int hue) {
		this.hue = hue;
	}

	public String getEffect() {
		return effect;
	}




}
