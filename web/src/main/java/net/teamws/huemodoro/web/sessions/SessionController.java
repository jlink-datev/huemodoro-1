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

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public SessionResponse getSession(@PathVariable String id) {
		ensureIdIs1(id);
		return new SessionResponse(repository.getSession());
	}

	@RequestMapping(path = "{id}/run", method = RequestMethod.PUT)
	public SessionResponse runSession(@PathVariable String id) {
		ensureIdIs1(id);
		return new SessionResponse(repository.runSession());
	}

	@RequestMapping(path = "{id}/reload", method = RequestMethod.PUT)
	public SessionResponse reloadSession(@PathVariable String id) {
		ensureIdIs1(id);
		return new SessionResponse(repository.reloadSession());
	}

	@RequestMapping(path = "{id}/stop", method = RequestMethod.PUT)
	public SessionResponse stopSession(@PathVariable String id) {
		ensureIdIs1(id);
		return new SessionResponse(repository.stopSession());
	}

	@RequestMapping(path = "{id}/reset", method = RequestMethod.PUT)
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
