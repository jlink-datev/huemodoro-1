package net.teamws.huemodoro.domain;

import java.time.*;

public interface HuemodoroSessionData {
	Duration getTimeLeft();

	SessionState getState();
}
