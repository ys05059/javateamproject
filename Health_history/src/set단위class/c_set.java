package set´ÜÀ§class;

import java.io.Serializable;
import java.time.LocalTime;

// È½¼ö set
public class c_set extends Set implements Cloneable, Serializable{
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
	
	public c_set(int goal_count, LocalTime resttime) {
		super(resttime);
		count = goal_count;
	}
	@Override
	public void performed_update() {
		p_count = count;
		super.setPerform_check(true);
	}
	
	public void performed_update(int tmp_count) {
		if(tmp_count >=count) {
			p_count = tmp_count;
		}else {
			p_count = count;
		}
		super.setPerform_check(true);
	}
	
	public void performed_reset() {
		p_count = 0;
		super.setPerform_check(false);
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

	public void setP_count(int p_count) {
		this.p_count = p_count;
		if (p_count >0) {
			super.setPerform_check(true);
		}else {
			super.setPerform_check(false);
		}
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


	@Override
	public c_set clone() throws CloneNotSupportedException{
		return (c_set) super.clone();
	}
}
