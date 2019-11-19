/**
 * 
 */
package com.vks.sb.app.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vivek
 *
 */
@RestController
public class GreetingRestController {

	@GetMapping("/greeting")
	public String greeting(Principal principal) {
		return "Hello Mr. " + principal.getName() + "!";
	}
}
