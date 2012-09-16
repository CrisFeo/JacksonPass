package blocks.literature;

import blocks.ICategory;

public class Literature extends ICategory {
	
	public Literature() {}
	
	@Override
	public String getName() {
		return "Literature";
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
