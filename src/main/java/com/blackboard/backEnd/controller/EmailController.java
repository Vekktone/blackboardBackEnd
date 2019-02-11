package com.blackboard.backEnd.controller;


import com.blackboard.backEnd.model.LogInfo;
import com.blackboard.backEnd.model.MailReturn;
import com.blackboard.backEnd.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

/**
 * This controller handles the email functionality of the web app.
 */
@RequestMapping("/email")
@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    private MailReturn mailObj;

    /**
     * The sendMail endpoint is called when the user hit the send button on the front end.
     * @param object This is the front end return piece containing an email recipient and a list of
     *               customers for the email output file
     * @param bindingResult this bindingResult checks for errors on request
     */
    @PutMapping("/sendMail")
    @CrossOrigin(origins = "http://localhost:4200")
    public void sendMail(@RequestBody @Valid MailReturn object, BindingResult bindingResult) throws MessagingException {

        if(bindingResult.hasErrors()){
            // handle error
            LogInfo.sendError("sendMail error!");
        }
        else
        {
            EmailService.sendMail(object);
        }

    }
}