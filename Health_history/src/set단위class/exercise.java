package set단위class;

// 운동정보 class
public class exercise {
	
	private String name;
	private String type;    //카테고리
	private String cal_type;
	
	public exercise() {
		name = "";
		type = "";
		cal_type = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCal_type() {
		return cal_type;
	}

	public void setCal_type(String cal_type) {
		this.cal_type = cal_type;
	}
	
	

}
