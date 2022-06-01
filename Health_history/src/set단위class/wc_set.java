package set단위class;

// 무게x횟수 set
public class wc_set extends Set implements Cloneable{
	private int weight;
	private int count;
	private int p_weight; 						// 수행 무게
	private int p_count;						// 수행 횟수
	
	public wc_set(){
		super();
		weight = 0;
		count = 0;
		p_weight = 0;
		p_count = 0;
	}
	
	public wc_set(int goal_weight, int goal_count) {
		super();
		weight = goal_weight;
		count = goal_count;
		p_weight = 0;
		p_count = 0;
	}
	
	public void performed_update() {
		p_weight = weight;
		p_count = count;
	}
	
	
	@Override
	public wc_set clone() throws CloneNotSupportedException{
		return (wc_set) super.clone();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(!(o instanceof wc_set))
			return false;
		wc_set wcs = (wc_set) o;
		return super.equals(wcs) && weight==wcs.weight && count==wcs.count && p_weight == wcs.p_weight &&  p_count == wcs.p_count;
	}

	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int getP_weight() {
		return p_weight;
	}

	public void setP_weight(int p_weight) {
		this.p_weight = p_weight;
	}

	public int getP_count() {
		return p_count;
	}

	public void setP_count(int p_count) {
		this.p_count = p_count;
	}
	
}
