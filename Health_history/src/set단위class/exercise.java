package set����class;

// ����� class
public class exercise {
	
	private String name;
	private String type;    //ī�װ�
	private String cal_type;
	
	public exercise() {
		name = "";
		type = "";
		cal_type = "";
	}
	
	public exercise (String other_name) {
		name = other_name;
		type = "";
		cal_type = "";
	}
	
	public exercise(exercise other_ex) {
		name = other_ex.name;
		type = other_ex.type;
		cal_type = other_ex.cal_type;
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
