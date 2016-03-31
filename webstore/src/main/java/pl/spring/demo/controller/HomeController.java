package pl.spring.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;

@Controller
public class HomeController {

	private static final String INFO_TEXT = "Welcome in my library. Feel free to check out all imaginary books I got here. Dive in my delusion and discover kinky world of stuff that shares common property - does not exist!.";
	private static final String WELCOME = "Awesome library";

	@RequestMapping("/")
	public String welcome(Principal user, Model model) {
		model.addAttribute(ModelConstants.GREETING, WELCOME);
		model.addAttribute(ModelConstants.INFO, INFO_TEXT);
		return ViewNames.WELCOME;
	}
}
