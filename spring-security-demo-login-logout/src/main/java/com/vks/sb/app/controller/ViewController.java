/**
 * 
 */
package com.vks.sb.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author vivkumarsingh
 *
 */
@Controller
public class ViewController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logout-success")
	public String logoutSuccess() {
		return "logout";
	}
}
