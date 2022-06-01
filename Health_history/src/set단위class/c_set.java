package set´ÜÀ§class;

// È½¼ö set
public class c_set extends Set implements Cloneable{
	private int count;
	private int p_count;
	
	public c_set() {
		super();
		count = 0;
		p_count = 0;
	}
	
	public c_set(int goal_count) {
		super();
		count = goal_count;
	}

	@Override
	public void performed_update() {
		p_count = count;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(!(o instanceof c_set))
			return false;
		c_set cs = (c_set) o;
		return super.equals(cs) && count==cs.count &&  p_count == cs.p_count;
	}

	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getP_count() {
		return p_count;
	}

	public void setP_count(int p_count) {
		this.p_count = p_count;
	}

	@Override
	public c_set clone() throws CloneNotSupportedException{
		return (c_set) super.clone();
	}
}
