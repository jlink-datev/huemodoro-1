package net.teamws.huemodoro.web.sessions;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import net.teamws.huemodoro.web.repository.*;

@RestController
@RequestMapping(path = SessionController.SESSIONS_PATH)
public class SessionController {

	public static final String SESSIONS_PATH = "/api/sessions/";

	private SessionRepository repository;

	public SessionController(@Autowired SessionRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "{id}")
	public SessionResponse getSession(@PathVariable String id) {
		ensureIdIs1(id);
		return new SessionResponse(repository.getSession());
	}

	@PutMapping(path = "{id}/run")
	public SessionResponse runSession(@PathVariable String id) {
		ensureIdIs1(id);
		return new SessionResponse(repository.runSession());
	}

	@PutMapping(path = "{id}/stop")
	public SessionResponse stopSession(@PathVariable String id) {
		ensureIdIs1(id);
		return new SessionResponse(repository.stopSession());
	}

	@PutMapping(path = "{id}/reset")
	public SessionResponse resetSession(@PathVariable String id) {
		ensureIdIs1(id);
		return new SessionResponse(repository.resetSession());
	}

	private void ensureIdIs1(@PathVariable String id) {
		if (!id.equals("1")) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, String.format("no session with id [%s]", id)
			);
		}
	}
}
