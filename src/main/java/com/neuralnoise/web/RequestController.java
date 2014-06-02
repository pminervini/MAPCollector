package com.neuralnoise.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.integration.util.CAnswer;
import com.neuralnoise.integration.util.CRequest;
import com.neuralnoise.service.RequestService;

@Controller
public class RequestController {
	
	@Autowired
	private RequestService requestService;

	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public @ResponseBody
	CAnswer postMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		final String resource = request.getParameter("resource");
		final String adapter = request.getParameter("adapter");
		
		CRequest crequest = new CRequest();
		crequest.setResource(resource);
		crequest.setAdapterName(adapter);
		
		CAnswer canswer = requestService.process(crequest);
		
		response.setStatus(HttpStatus.FOUND.value());
		
		return canswer;
	}

}
