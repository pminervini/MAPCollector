package com.neuralnoise.web;

import com.neuralnoise.service.PublishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MessageController {
	@Autowired
	private PublishService publishService;

	@RequestMapping(value = "/message", method = RequestMethod.POST)
	@ResponseBody
	public void postMessage(@RequestBody com.neuralnoise.model.Message message, HttpServletResponse response) {
		// Publish the message
		publishService.send(message);

		// Set the status to 201 because we created a new message
		response.setStatus(HttpStatus.CREATED.value());
	}

}
