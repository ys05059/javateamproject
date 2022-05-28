package health;

public class category implements Cloneable {
	private String workout;
	private String category;
	private String calmethod;
	
	
	public category() {
	}
	
	public category(String workout, String category,String calmethod) {
		super();
		this.workout = workout;
		this.category = category;
		this.calmethod = calmethod;
	}
	
	public String getworkout() {
		return this.workout;
	}
	public String getcategory() {
		return this.category;
	}
	public String getcalmethod() {
		return this.calmethod;
	}
	public void setworkout(String workout) {
		this.workout = workout;
	}
	public void setcategory(String category) {
		this.category = category;
	}
	public void setcalmethod(String calmethod) {
		this.calmethod = calmethod;
	}
	
	protected category clone() throws CloneNotSupportedException{
		return (category) super.clone();
	}
	
	
}
