package set단위class;

import java.time.Duration;
import java.time.LocalTime;

// 수행 무게x횟수 set
public class p_wc_set extends wc_set implements performed {
	private LocalTime start_time;
	private LocalTime end_time;

	public Duration getperform_time() {
		Duration duration = Duration.between(start_time, end_time);
		return duration;
	}
}
