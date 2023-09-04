package com.example.commercialsite.service.serviceImpl;

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
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<StandardResponse> AcceptRequestToken(AcceptRequestDto acceptRequestDto) {
        try {

             if( requestTokenRepo.existsById( acceptRequestDto.getRequestTokenId() ) ){ // check if the token_request_id is present(valid)
                 // get requesttoken object by id
                 RequestToken requestToken = requestTokenRepo.getRequestTokenByRequestTokenId(acceptRequestDto.getRequestTokenId());
                 //updating the requesttoken object with arrived details
                 requestToken.setQualityCheckerId(acceptRequestDto.getQualityCheckerId());
                 requestToken.setStatus(1);

                 // creating a item object and adding arrived details
                 Item item = new Item();
                 item.setColor(acceptRequestDto.getColor());
                 item.setImageURL(acceptRequestDto.getImageURL());
                 item.setGender(acceptRequestDto.getGender());
                 item.setType(acceptRequestDto.getType());
                 item.setPrice(acceptRequestDto.getPrice());
                 item.setAvailableStatus(true);
                 item.setSize(acceptRequestDto.getSize());

                 // generating token
                 Token token = tokenService.GenerateToken(acceptRequestDto.getRequestTokenId(), acceptRequestDto.getPrice());

                 // writing to the database
                 requestTokenRepo.save(requestToken);
                 itemRepo.save(item);
                 tokenRepo.save(token);


                 return new ResponseEntity<>(
                         new StandardResponse(201,"Data saved successfully.", null),
                         HttpStatus.CREATED);
              }else{
                 return new ResponseEntity<>(
                         new StandardResponse(400,"Request Id Not Found",null ),
                         HttpStatus.BAD_REQUEST);
             }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new StandardResponse(500,"Error while processing the Accept Request Token: " + e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<StandardResponse> rejectRequestToken(RejectRequestDto rejectRequestDto) {
       try{
           if(requestTokenRepo.existsById(rejectRequestDto.getRequestTokenId())){
                //RequestToken requestToken = new RequestToken();
               RequestToken requestToken = requestTokenRepo.getRequestTokenByRequestTokenId( rejectRequestDto.getRequestTokenId());
                requestToken.setQualityCheckerId(rejectRequestDto.getQualityCheckerId());
                requestToken.setStatus(-1);

                requestTokenRepo.save(requestToken);

                return new ResponseEntity<>(
                       new StandardResponse(201,"Token Request Successfully Rejected.", null),
                       HttpStatus.CREATED);
             }else {
                return new ResponseEntity<>(
                       new StandardResponse(400,"Request Token Id Not Found.", null),
                       HttpStatus.BAD_REQUEST);
             }

       } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(
                new StandardResponse(500,"Error while processing the Accept Request Token: " + e.getMessage(),null),
           HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }


}
