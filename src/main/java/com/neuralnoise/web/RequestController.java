package com.neuralnoise.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.model.Request;
import com.neuralnoise.service.RequestService;

@Controller
public class RequestController {
	
	@Autowired
	private RequestService requestService;

	@RequestMapping(value = "/request", method = RequestMethod.POST)
	@ResponseBody
	public void postMessage(@RequestBody Request request, HttpServletResponse response) {
		System.out.println("Sending request: " + request + " ..");
		requestService.send(request);
		response.setStatus(HttpStatus.CREATED.value());
	}

}
