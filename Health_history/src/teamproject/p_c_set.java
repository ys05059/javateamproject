package teamproject;

import java.time.Duration;
import java.time.LocalTime;

import set단위class.c_set;

// 수행한 횟수단위 set 
public class p_c_set extends c_set implements performed {
	private LocalTime start_time;
	private LocalTime end_time;

	public Duration getperform_time() {
		Duration duration = Duration.between(start_time, end_time);
		return duration;
	}
	

}
