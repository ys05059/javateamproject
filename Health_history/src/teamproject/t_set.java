package teamproject;

import java.time.LocalTime;

import set����class.Set;

// ��ǥ �ð� set
public class t_set extends Set {
	private LocalTime goal_time;
	
	public t_set() {
		goal_time = LocalTime.of(0, 0);
	}

	public LocalTime getGoal_time() {
		return goal_time;
	}

	public void setGoal_time(LocalTime goal_time) {
		this.goal_time = goal_time;
	}
	
	
}
