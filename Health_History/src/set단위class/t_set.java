package set단위class;

import java.time.LocalTime;

// 목표 시간 set
public class t_set {
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
