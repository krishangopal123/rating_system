package com.example.oye_rikhshaw.Controller;

import com.example.oye_rikhshaw.Service.RikhsawService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RikhsawController {
    @Autowired
    private RikhsawService rikhsawService;
    private static Logger logger = LoggerFactory.getLogger(RikhsawController.class);

    @RequestMapping(value="/ping")
    String test(){
        return "pong";
    }
    @RequestMapping(value="addNewUser/{telePhoneNumber}", method = RequestMethod.POST)
    ResponseEntity<String>signUpNewUser(@PathVariable String telePhoneNumber){
        return rikhsawService.addNewUser(telePhoneNumber);

    }

    @RequestMapping(value = "getrating/{telePhoneNumber}")
    ResponseEntity<String> getUserRatingByTelephoneNumber(@PathVariable String telePhoneNumber){
        return rikhsawService.getRatingByTelephoneNumber(telePhoneNumber);
    }
    @RequestMapping(value ="updateRating/{telePhoneNumber}/{rating}",method = RequestMethod.PUT)
    ResponseEntity<String>updateRatingByTelePhoneNumber(@PathVariable String telePhoneNumber,@PathVariable Integer rating) throws Exception{
        try {

        rikhsawService.updateRatingByTelePhoneNumber(telePhoneNumber,rating);
        return new ResponseEntity<String>("Successful update the rating", HttpStatus.OK);

        }
        catch (Exception e){
            logger.info("Error in updating the rating");
            return new ResponseEntity<String>("Error in updating the rating,Please try after Sometime", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "updateMobileNumber/{newtelePhoneNumber}/{oldTelePhoneNumber}",method = RequestMethod.PUT)
    ResponseEntity<String>updateTelePhoneNumber(@PathVariable String newtelePhoneNumber,@PathVariable String oldTelePhoneNumber) throws Exception{
        try {

            rikhsawService.updateTelePhoneNumber(newtelePhoneNumber,oldTelePhoneNumber);
            return new ResponseEntity<String>("Successful update the TelePhoneNumber", HttpStatus.OK);

        }
        catch (Exception e){
            logger.info("Error in updating the rating");
            return new ResponseEntity<String>("Error in updating the TelePhoneNumber,Please try after Sometime", HttpStatus.EXPECTATION_FAILED);
        }
    }



}
