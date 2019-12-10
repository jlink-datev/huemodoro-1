package net.teamws.huemodoro.web;

import java.time.*;

import org.junit.jupiter.api.*;

import net.teamws.huemodoro.domain.*;
import net.teamws.huemodoro.web.hue.*;
import net.teamws.huemodoro.web.repository.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static net.teamws.huemodoro.domain.SessionState.*;

class SessionRepositoryTests {

	private HueService hueService = mock(HueService.class);
	private SessionRepository repository = new InMemorySessionRepository(hueService);

	@Test
	void initiallySessionIsFresh() {
		HuemodoroSessionData session = repository.getSession();
		assertEquals(STOPPED, session.getState());
		assertEquals(Duration.ofMinutes(25), session.getTimeLeft());
	}

	@Test
	void startingSession() {
		HuemodoroSessionData session = repository.runSession();
		assertEquals(RUNNING, session.getState());
	}

	@Test
	void hueServiceIsRegisteredAsStateChangeObserver() {
		repository.runSession();
		verify(hueService).stateChanged(STOPPED, RUNNING);
	}

	@Test
	void stoppingSession() {
		repository.runSession();
		HuemodoroSessionData session = repository.stopSession();
		assertEquals(STOPPED, session.getState());
	}

	@Test
	void resettingSession() {
		repository.runSession();
		repository.advanceTime(Duration.ofMinutes(12));

		HuemodoroSessionData session = repository.resetSession();
		assertEquals(STOPPED, session.getState());
		assertEquals(Duration.ofMinutes(25), session.getTimeLeft());
	}

	@Test
	void advanceTimeAdvancesTimeOfSession() {
		repository.runSession();
		repository.advanceTime(Duration.ofHours(1));

		HuemodoroSessionData session = repository.getSession();
		assertEquals(SessionState.FINISHED, session.getState());
		assertEquals(Duration.ZERO, session.getTimeLeft());
	}
}
