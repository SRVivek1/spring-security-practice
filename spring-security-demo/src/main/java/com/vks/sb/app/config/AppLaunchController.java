/**
 * 
 */
package com.vks.sb.app.config;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vivek
 *
 */
@RestController
public class AppLaunchController {

	@RequestMapping(value="/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public String homePage() {
		return "{message: Hello Mr X.}";
		
	}
}
