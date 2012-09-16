package blocks.computerInfo;

import javax.servlet.http.HttpServletRequest;

import blocks.ICategory;

public class ComputerInfo extends ICategory {

	public ComputerInfo() {}
	
	@Override
	public String getName() {
		return "Computer Info";
	}

	@Override
	public String getColor() {
		return "0x000000";
	}

	@Override
	public String getBlockColor() {
		return "0xFFFFFF";
	}
	
	public void getHeader(HttpServletRequest req, String header){
	
	}

}
