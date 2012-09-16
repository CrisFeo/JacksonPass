package blocks.zodiac;

import blocks.ICategory;

public class Zodiac extends ICategory {
	
	public Zodiac() {}
	
	@Override
	public String getName() {
		return "Zodiac";
	}

	@Override
	public String getColor() {
		return "0x000000";
	}

	@Override
	public String getBlockColor() {
		return "0xFFFFFF";
	}
}
