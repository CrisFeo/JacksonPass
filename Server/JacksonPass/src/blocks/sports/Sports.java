package blocks.sports;

import blocks.ICategory;

public class Sports extends ICategory{
	

	public Sports() {}
	
	@Override
	public String getName() {
		return "Sports";
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
