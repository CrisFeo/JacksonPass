package blocks.financial;

import blocks.ICategory;

public class Financial extends ICategory {

	public Financial() {}
	
	@Override
	public String getName() {
		return "Financial";
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
