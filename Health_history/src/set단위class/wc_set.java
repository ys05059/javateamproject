package set����class;

// ��ǥ ����xȽ�� set
public class wc_set extends Set{
	private int weight;
	private int count;
	
	public wc_set() {
		weight = 0;
		count = 0;
	}
	
	public wc_set(int other_weight, int other_count) {
		weight = other_weight;
		count = other_count;
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
	
	
}
