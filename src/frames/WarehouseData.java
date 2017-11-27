package frames;

public class WarehouseData {

	private String location;
	private String technican;
	private String partnumber;
	private boolean isPrinted = true; 
	
	public WarehouseData(String location, String technican, String partnumber) {
		super();
		this.location = location;
		this.technican = technican;
		this.partnumber = partnumber;
	}
	
	public WarehouseData() {
		super();
	}
	
	
	public boolean getIsPrinted() {
		return isPrinted;
	}

	public void setIsPrinted(boolean isPrinted) {
		this.isPrinted = isPrinted;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTechnican() {
		return technican;
	}
	public void setTechnican(String technican) {
		this.technican = technican;
	}
	public String getPartnumber() {
		return partnumber;
	}
	public void setPartnumber(String partnumber) {
		this.partnumber = partnumber;
	}
	
	public Object[] getObjectArr() {
		Object[] objs = {location,technican,partnumber,isPrinted} ;
		return objs;
	}
	
}
