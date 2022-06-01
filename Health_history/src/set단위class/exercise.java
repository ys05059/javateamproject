package set단위class;

//운동정보 class
public class exercise implements Cloneable {
	
	private String name;
	private String category;
	private String calmethod;
	
	public exercise() {
		this.name = "";
		this.category = "";
		this.calmethod = "";
	}
	public exercise (String other_name) {
		name = other_name;
		category = "";
		calmethod = "";
	}
	
	public exercise(String other_name, String other_category, String other_calmethod) {
		this.name = other_name;
		this.category = other_category;
		this.calmethod = other_calmethod;
	}
	public exercise(exercise other_ex) {
		name = other_ex.name;
		category = other_ex.getcategory();
		calmethod = other_ex.getcalmethod();
	}
	
	public String getname() {
		return this.name;
	}
	
	public String getcategory() {
		return this.category;
	}
	public String getcalmethod() {
		return this.calmethod;
	}
	public void setname(String other_name) {
		this.name = other_name;
	}
	public void setcategory(String other_category) {
		this.category = other_category;
	}
	public void setcalmethod(String other_calmethod) {
		this.calmethod = other_calmethod;
	}
	protected exercise clone() throws CloneNotSupportedException{
		return (exercise) super.clone();
	}
}
