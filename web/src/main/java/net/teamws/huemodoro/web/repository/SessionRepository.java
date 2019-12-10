package net.teamws.huemodoro.web.repository;

import java.time.*;

import net.teamws.huemodoro.domain.*;

public interface SessionRepository {
	HuemodoroSessionData getSession();

	HuemodoroSessionData runSession();

	HuemodoroSessionData stopSession();

	HuemodoroSessionData resetSession();

	void advanceTime(Duration advanceBy);
}
