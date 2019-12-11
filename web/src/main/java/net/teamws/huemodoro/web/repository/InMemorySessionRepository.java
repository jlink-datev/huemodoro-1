package net.teamws.huemodoro.web.repository;

import java.time.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import net.teamws.huemodoro.domain.*;
import net.teamws.huemodoro.web.hue.*;

@Component
public class InMemorySessionRepository implements SessionRepository {

	private final HuemodoroSession session;

	public InMemorySessionRepository(
			@Autowired HueService hueService,
			@Value("${huemodoro.durationInMinutes:10}") int durationInMinutes
	) {
		session = new HuemodoroSession(durationInMinutes);
		session.addStateObserver(hueService);
	}

	@Override
	public HuemodoroSessionData getSession() {
		return session;
	}

	@Override
	public HuemodoroSessionData runSession() {
		session.run();
		return session;
	}

	@Override
	public HuemodoroSessionData stopSession() {
		session.stop();
		return session;
	}

	@Override
	public HuemodoroSessionData resetSession() {
		session.reset();
		return session;
	}

	@Override
	public HuemodoroSessionData reloadSession() {
		session.reload();
		return session;
	}

	@Override
	public void advanceTime(Duration advanceBy) {
		session.advanceTime(advanceBy);
	}
}
