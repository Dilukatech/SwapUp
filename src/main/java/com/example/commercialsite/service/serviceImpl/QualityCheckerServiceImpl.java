package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.controller.CustomerController;
import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RejectRequestDto;
import com.example.commercialsite.entity.Item;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Token;
import com.example.commercialsite.repository.ItemRepo;
import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.repository.TokenRepo;
import com.example.commercialsite.service.QualityCheckerService;
import com.example.commercialsite.service.TokenService;
import com.example.commercialsite.utill.FromDTO;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class QualityCheckerServiceImpl implements QualityCheckerService {
    @Autowired
    private RequestTokenRepo requestTokenRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private FromDTO fromDTO;

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName()); // logger is the recommended way to handel exceptions


    public ResponseEntity<StandardResponse> AcceptRequestToken(AcceptRequestDto acceptRequestDto) {
        logger.info("Logging begins... AcceptRequestToken");   // log INFO-level message
        ResponseEntity<StandardResponse> result;

        try {
             if( requestTokenRepo.existsById( acceptRequestDto.getRequestTokenId() ) ){ // check if the token_request_id is present(valid)
                 // get requestToken object by id
                 RequestToken requestToken = requestTokenRepo.getRequestTokenByRequestTokenId(acceptRequestDto.getRequestTokenId());
                 //updating the requestToken object with arrived details
                 requestToken.setQualityCheckerId(acceptRequestDto.getQualityCheckerId());
                 requestToken.setStatus(1);

                 // creating a item object and adding arrived details
                 Item item = fromDTO.getItem(requestToken, acceptRequestDto); // map data

                 // generating token
                 Token token = tokenService.GenerateToken(acceptRequestDto.getRequestTokenId(), acceptRequestDto.getPrice());

                 // setting data in the requestToken
                 requestToken.setToken(token); // suppose to save token in the db wen the requestToken save is called
                 requestToken.setItem(item); // suppose to save item in the db wen the requestToken save is called


                 // writing to the database
                 requestTokenRepo.save(requestToken);


                 result = new ResponseEntity<>(
                         new StandardResponse(201,"Data saved successfully.", null),
                         HttpStatus.CREATED);
              }else{ // request_id is not valid
                 result = new ResponseEntity<>(
                         new StandardResponse(400,"Request Id Not Found",null ),
                         HttpStatus.BAD_REQUEST);
             }
        } catch (Exception ex) {
            //ex.printStackTrace();
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            result = new ResponseEntity<>(
                    new StandardResponse(500,"Error while processing the Accept Request Token: " + ex.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Done... AcceptRequestToken");
        return result;
    } // end of AcceptRequestToken

    @Override
    public ResponseEntity<StandardResponse> rejectRequestToken(RejectRequestDto rejectRequestDto) {
        logger.info("Logging begins... rejectRequestToken");   // log INFO-level message
        ResponseEntity<StandardResponse> result;

       try{
           if(requestTokenRepo.existsById(rejectRequestDto.getRequestTokenId())){
                //RequestToken requestToken = new RequestToken();
               RequestToken requestToken = requestTokenRepo.getRequestTokenByRequestTokenId( rejectRequestDto.getRequestTokenId());
                requestToken.setQualityCheckerId(rejectRequestDto.getQualityCheckerId());
                requestToken.setStatus(-1);

                requestTokenRepo.save(requestToken);

                result = new ResponseEntity<>(
                       new StandardResponse(201,"Token Request Successfully Rejected.", null),
                       HttpStatus.CREATED);
             }else {
                result = new ResponseEntity<>(
                       new StandardResponse(400,"Request Token Id Not Found.", null),
                       HttpStatus.BAD_REQUEST);
             }

       } catch (Exception ex) {
        //e.printStackTrace();
           logger.log(Level.SEVERE, ex.getMessage(), ex);
           result = new ResponseEntity<>(
                new StandardResponse(500,"Error while processing the Accept Request Token: " + ex.getMessage(),null),
           HttpStatus.INTERNAL_SERVER_ERROR);
       }

        logger.info("Done... rejectRequestToken");
        return result;
    } // end of rejectRequestToken


}
