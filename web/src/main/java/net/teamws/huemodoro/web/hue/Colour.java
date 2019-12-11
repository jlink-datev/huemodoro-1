package net.teamws.huemodoro.web.hue;

public enum Colour {
	RED(1),
	ORANGE(5000),
	GREEN(23000);

	private int colorCode;

	Colour(int colorCode) {
		this.colorCode = colorCode;
	}

	int value() {
		return colorCode;
	}
}