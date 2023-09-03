package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RejectRequestDto;
import com.example.commercialsite.entity.Item;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.repository.ItemRepo;
import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.service.QualityCheckerService;
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

    public ResponseEntity<StandardResponse> AcceptRequestToken(AcceptRequestDto acceptRequestDto) {
        try {

             if(requestTokenRepo.existsById(acceptRequestDto.getRequestTokenId())){
                 RequestToken requestToken = new RequestToken();
                 requestToken = requestTokenRepo.getRequestTokenByRequestTokenId(acceptRequestDto.getRequestTokenId());

                 requestToken.setQualityCheckerId(acceptRequestDto.getQualityCheckerId());
                 requestToken.setStatus(1);

                 Item item = new Item(
                         acceptRequestDto.getColor(),
                         acceptRequestDto.getImageURL(),
                         acceptRequestDto.getGender(),
                         acceptRequestDto.getType(),
                         acceptRequestDto.getPriceRange(),
                         acceptRequestDto.isActiveState(),
                         acceptRequestDto.getSize(),
                         requestToken
                 );

                 requestTokenRepo.save(requestToken);
                 itemRepo.save(item);

                 return new ResponseEntity<StandardResponse>(
                         new StandardResponse(201,"Data saved successfully.", requestToken),
                         HttpStatus.CREATED);
              }else{
                 return new ResponseEntity<StandardResponse>(
                         new StandardResponse(400,"Request Id Not Found",new RequestToken() ),
                         HttpStatus.BAD_REQUEST);
             }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"Error while processing the Accept Request Token: " + e.getMessage(), new RequestToken()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<StandardResponse> rejectRequestToken(RejectRequestDto rejectRequestDto) {
       try{
           if(requestTokenRepo.existsById(rejectRequestDto.getRequestTokenId())){
                RequestToken requestToken = new RequestToken();
                requestToken = requestTokenRepo.getRequestTokenByRequestTokenId( rejectRequestDto.getRequestTokenId());
                requestToken.setQualityCheckerId(rejectRequestDto.getQualityCheckerId());
                requestToken.setStatus(-1);

                requestTokenRepo.save(requestToken);

                return new ResponseEntity<StandardResponse>(
                       new StandardResponse(201,"Token Request Successfully Rejected.", requestToken),
                       HttpStatus.CREATED);
             }else {
                return new ResponseEntity<StandardResponse>(
                       new StandardResponse(400,"Request Token Id Not Found.", new RequestToken()),
                       HttpStatus.BAD_REQUEST);
             }

       } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(500,"Error while processing the Accept Request Token: " + e.getMessage(),new RequestToken()),
           HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }


}
