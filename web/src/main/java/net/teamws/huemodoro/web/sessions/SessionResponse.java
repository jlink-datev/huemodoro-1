package net.teamws.huemodoro.web.sessions;

import java.time.*;

import net.teamws.huemodoro.domain.*;

public class SessionResponse {

	public static final String WHOHASDEVELOPEDTHIS = "1";

	private final SessionState state;

	private final Duration timeLeft;

	public SessionResponse(HuemodoroSessionData session) {
		this.state = session.getState();
		this.timeLeft = session.getTimeLeft();
	}

	public String getId() {
		return WHOHASDEVELOPEDTHIS;
	}

	public SessionState getState() {
		return state;
	}

	public Duration getTimeLeft() {
		return timeLeft;
	}

}
