package net.teamws.huemodoro.web.hue;

import java.time.*;

import com.google.gson.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.web.client.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import net.teamws.huemodoro.web.*;

import static java.util.Collections.*;

@Service
public class HueBridge {
	private static Logger logger = LoggerFactory.getLogger(HueBridge.class);

	private RestTemplate restTemplate = new RestTemplateBuilder()
												.setConnectTimeout(Duration.ofSeconds(3))
												.build();

	private HueConfiguration hueConfiguration;

	public HueBridge(@Autowired HueConfiguration hueConfiguration) {
		this.hueConfiguration = hueConfiguration;
		logConfiguration();
	}

	private void logConfiguration() {
		logger.info("Hue host:   {}", hueConfiguration.getHost());
		logger.info("Hue client: {}", hueConfiguration.getClient());
		logger.info("Hue lamp:   {}", hueConfiguration.getLamp());
	}

	void lightOn(Colour colour) {
		logger.info("light on {}", colour.name());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(singletonList(MediaType.ALL));

		HueBody hueBody = new HueBody();
		hueBody.hue = colour.value();

		HttpEntity<String> requestUpdate =
				new HttpEntity<>(new Gson().toJson(hueBody), httpHeaders);

		executePut(url(), requestUpdate);
	}

	void lightOff() {
		logger.debug("light off");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(singletonList(MediaType.ALL));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestUpdate = new HttpEntity<>(new Gson().toJson(new HueLightsOffBody()), httpHeaders);

		executePut(url(), requestUpdate);
	}

	private void executePut(String url, HttpEntity<String> request) {
		logger.debug(url);
		try {
			restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
		} catch (RestClientException restClientException) {
			logger.error("error during put", restClientException);
		}
		//TODO: do not call anymore, when previous call failed
		//TODO: Handle other IOExceptions
	}

	private String url() {
		return "http://" + hueConfiguration.getHost()
					   + "/api/"
					   + hueConfiguration.getClient()
					   + "/lights/"
					   + hueConfiguration.getLamp()
					   + "/state";
	}

	void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	static class HueBody {
		public boolean on = true;
		public int sat = 254;
		public int bri = 120;
		public int hue = 5000;
		public String effect = "none";
	}

	static class HueLightsOffBody {
		public boolean on = false;
	}

}