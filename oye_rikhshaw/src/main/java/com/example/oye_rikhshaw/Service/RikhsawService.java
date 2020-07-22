package com.example.oye_rikhshaw.Service;

import com.example.oye_rikhshaw.Dtos.RikhshawDto;
import com.example.oye_rikhshaw.Repositry.RikhshawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RikhsawService {
    @Autowired
    private RikhshawRepo rikhshawRepo;

    public ResponseEntity<String> addNewUser(String telelPhoneNumber){
        RikhshawDto rikhshawDto = new RikhshawDto();
        rikhshawDto.setTelephoneNumber(telelPhoneNumber);
        rikhshawDto.setTotalRatingsValue(0);
        rikhshawDto.setTotalUserGivenRatings(0);
        try {
            rikhshawRepo.save(rikhshawDto);
            return new ResponseEntity<String>("Thank you to register with us", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<String>("User is already Existing in database,Please Continue With Some other Phone Number ", HttpStatus.EXPECTATION_FAILED);
        }
    }
    public ResponseEntity<String> getRatingByTelephoneNumber(String telelPhoneNumber){
        try {


            RikhshawDto rikhshawDto = rikhshawRepo.findByTelephoneNumber(telelPhoneNumber);
            Integer totalValue = rikhshawDto.getTotalRatingsValue();
            Integer totalUserGivenRatings = rikhshawDto.getTotalUserGivenRatings();
            double rating = 0.0;
            if(totalUserGivenRatings !=0){

             rating = (double)totalValue/totalUserGivenRatings;
            }
            return new ResponseEntity<String>(Double.toString(rating), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>("Error in getting the Rating,Please try After Sometime", HttpStatus.EXPECTATION_FAILED);

        }
    }
    public void updateRatingByTelePhoneNumber(String telePhoneNumber,Integer rating) throws  Exception{
        try {
            RikhshawDto rikhshawDto = rikhshawRepo.findByTelephoneNumber(telePhoneNumber);
            Integer currentTotalValue = rikhshawDto.getTotalRatingsValue();
            Integer totalUserGivenRatings = rikhshawDto.getTotalUserGivenRatings();
            totalUserGivenRatings = totalUserGivenRatings + 1 ;
            currentTotalValue = (currentTotalValue+rating);
            rikhshawDto.setTotalRatingsValue(currentTotalValue);
            rikhshawDto.setTotalUserGivenRatings(totalUserGivenRatings);
            rikhshawRepo.save(rikhshawDto);
        }
        catch (Exception e){
            throw  new Exception("Error in updating the rating");
        }
    }
    public void updateTelePhoneNumber(String newTelePhoneNumber,String oldTelePhoneNumber) throws  Exception{
        try {
            RikhshawDto rikhshawDto = rikhshawRepo.findByTelephoneNumber(oldTelePhoneNumber);
            rikhshawDto.setTelephoneNumber(newTelePhoneNumber);
            rikhshawRepo.save(rikhshawDto);
        }
        catch (Exception e){
            throw  new Exception("Error in updating the TelePhoneNumber");
        }
    }
}
