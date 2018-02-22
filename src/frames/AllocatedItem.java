package frames;

public class AllocatedItem {
	
	private String applicant;
	private String isKeyPart;
	private String location;
	private String newPartDesc;
	private String newPartNo;
	private String orgPartNo;
	private String rmaNo;
	private String realLocation;
	
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getIsKeyPart() {
		return isKeyPart;
	}
	public void setIsKeyPart(String isKeyPart) {
		this.isKeyPart = isKeyPart;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNewPartDesc() {
		return newPartDesc;
	}
	public void setNewPartDesc(String newPartDesc) {
		this.newPartDesc = newPartDesc;
	}
	public String getNewPartNo() {
		return newPartNo;
	}
	public void setNewPartNo(String newPartNo) {
		this.newPartNo = newPartNo;
	}
	public String getRmaNo() {
		return rmaNo;
	}
	public void setRmaNo(String rmaNo) {
		this.rmaNo = rmaNo;
	}
	public String getRealLocation() {
		return realLocation;
	}
	public void setRealLocation(String realLocation) {
		this.realLocation = realLocation;
	}
	
	
	public String getOrgPartNo() {
		return orgPartNo;
	}
	
	public void setOrgPartNo(String value) {
		this.orgPartNo = value;
	}
	
	
	public Object[] getObjectArr() {
		Object[] objs = {applicant,isKeyPart,location,newPartDesc,newPartNo,rmaNo,realLocation,orgPartNo} ;
		return objs;
		
//		CELL col=8 VALUE=STRING value=APPLICANT
//		CELL col=32 VALUE=STRING value=IS_KEY_PART
//		CELL col=43 VALUE=STRING value=LOCATION
//		CELL col=47 VALUE=STRING value=NEW_PART_DESC
//		CELL col=48 VALUE=STRING value=NEW_PART_NO
//		CELL col=86 VALUE=STRING value=RMA_NO
//		CELL col=89 VALUE=STRING value=REAL_LOCATION
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((newPartNo == null) ? 0 : newPartNo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllocatedItem other = (AllocatedItem) obj;
		if (newPartNo == null) {
			if (other.newPartNo != null)
				return false;
		} else if (!newPartNo.equals(other.newPartNo))
			return false;
		return true;
	}
	
	


	
}