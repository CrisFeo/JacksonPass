package blocks.electedOfficials;

import blocks.ICategory;

public class ElectedOfficials extends ICategory{

	public ElectedOfficials() {}
	
	@Override
	public String getName() {
		return "Elected Officials";
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
