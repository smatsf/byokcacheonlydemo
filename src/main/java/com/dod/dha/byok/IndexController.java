package com.dod.dha.byok;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class IndexController {
 private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @GetMapping(value = {"/", ""})
    public String index() {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        return "index";
    }

    @GetMapping(value = {"/server_error"})
    public String triggerServerError() {
        "ser".charAt(30);
        return "index";
    }

    @PostMapping(value = {"/general_error"})
    public String triggerGeneralError() {
        return "index";
    }

}
