package health;

public class work implements Cloneable {
	private String workout;
	private String category;
	private String calmethod;
	
	
	public work() {
	}
	
	public work(String workout, String category, String calmethod) {
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
	
	protected work clone() throws CloneNotSupportedException{
		return (work) super.clone();
	}
	
	
}
