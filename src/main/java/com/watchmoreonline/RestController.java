package com.watchmoreonline;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@org.springframework.web.bind.annotation.RestController
public class RestController {

	
	@RequestMapping("/")
    @ResponseBody
    String hello() {
        return "Backend is running.";
    }	
}
