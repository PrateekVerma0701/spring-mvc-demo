package com.prateek.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

	// need a controller method to show the initial form

	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";

	}

	// need a controller method to process a form

	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}

	// new controller method to read form data
	// add data to the model

	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {

		// read the request from the HTML form
		String name = request.getParameter("studentName");

		// convert the data to UpperCase

		String result = "YO !! " + name.toUpperCase();

		// create the message
		model.addAttribute("message", result);

		// add message to the model
		return "helloworld";
	}

	@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(@RequestParam("studentName") String name, Model model) {

		// convert the data to UpperCase

		String result = "YO !!! " + name.toUpperCase();

		// create the message
		model.addAttribute("message", result);

		// add message to the model
		return "helloworld";
	}

}
