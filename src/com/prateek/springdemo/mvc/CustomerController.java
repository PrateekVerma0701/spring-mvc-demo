package com.prateek.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// add a InitBinder to convert trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation

	@InitBinder
	public void initBinder(WebDataBinder databinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		databinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/showForm")
	public String showForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-form";
	}

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {

		// Debugging trick to apply your own custom messages by overriding values
		
		// Step-1 - Check binding result messages
		
		// Step-2 - Override message value with your own custom message

		/*
		 * Binding Result : org.springframework.validation.BeanPropertyBindingResult: 1
		 * errors Field error in object 'customer' on field 'freePasses': rejected value
		 * [g]; codes
		 * [typeMismatch.customer.freePasses,typeMismatch.freePasses,typeMismatch.java.
		 * lang.Integer,typeMismatch]; arguments
		 * [org.springframework.context.support.DefaultMessageSourceResolvable: codes
		 * [customer.freePasses,freePasses]; arguments []; default message
		 * [freePasses]]; default message [Failed to convert property value of type
		 * 'java.lang.String' to required type 'java.lang.Integer' for property
		 * 'freePasses'; nested exception is java.lang.NumberFormatException: For input
		 * string: "g"]
		 */

		/*
		 * Step-3- In message.properties define the value of
		 * typeMismatch.customer.freePasses
		 * 
		 * typeMismatch.customer.freePasses= Invalid Number
		 */

		System.out.println("Binding Result : " + bindingResult);

		if (bindingResult.hasErrors()) {
			return "customer-form";
		} else {
			return "customer-confirmation";
		}
	}

}
