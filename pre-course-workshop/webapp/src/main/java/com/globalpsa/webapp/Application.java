package com.globalpsa.webapp;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

class RunnableTask implements Runnable{
    private String message;
    
    public RunnableTask(String message){
        this.message = message;
    }
    
    @Override
    public void run() {
        System.out.println(new Date()+" Runnable Task with "+message
          +" on thread "+Thread.currentThread().getName());
    }
}

@RestController
class WebServiceController {

    private final static Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LocalCache cache;

    //http://localhost:8899/webapp/svc/number/v1.0
    @GetMapping("/svc/number/v1.0")
    public ResponseEntity<Map<String,String>> number() {
        Map output = new HashMap<String,String>();
        output.put("number", String.valueOf(cache.getNumber()));
        
        logger.debug("OUTPUT: {}", output.toString());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    //http://localhost:8899/webapp/svc/ping/v1.0?input=helloworld
    @GetMapping("/svc/ping/v1.0")
    public ResponseEntity<Map<String,String>> ping(@RequestParam(value = "input", defaultValue = "nil") String input) {
        Map output = new HashMap<String,String>();
        output.put("input", input);
        Enumeration<String> e = request.getHeaderNames();
        while(e.hasMoreElements()){
            String header = e.nextElement();
            output.put(header, request.getHeader(header));
        }        
        
        logger.debug("OUTPUT: {}", output.toString());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}

@Controller
class MainController {
    
    private final static Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    private TextRepo textRepo;
    
    //http://localhost:8899/webapp/main
    @GetMapping("/main")
    public String main(Model model){   
        String dynamicText = textRepo.getRandomLine();
        model.addAttribute("dynamicText", dynamicText);
        
        logger.info("Random Text: {}", dynamicText);
        return "index";
    }
}
