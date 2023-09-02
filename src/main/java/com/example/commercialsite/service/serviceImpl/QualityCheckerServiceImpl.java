package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RejectRequestDto;
import com.example.commercialsite.entity.Item;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.repository.ItemRepo;
import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.service.QualityCheckerService;
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

    public ResponseEntity<String> AcceptRequestToken(AcceptRequestDto acceptRequestDto) {
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

                 return new ResponseEntity<>("Data saved successfully.", HttpStatus.OK);
              }else{
                 return new ResponseEntity<>("Request Token Id Not Found",HttpStatus.NOT_FOUND);
             }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while processing the Accept Request Token: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<String> rejectRequestToken(RejectRequestDto rejectRequestDto) {
       try{
           if(requestTokenRepo.existsById(rejectRequestDto.getRequestTokenId())){
                RequestToken requestToken = new RequestToken();
                requestToken = requestTokenRepo.getRequestTokenByRequestTokenId( rejectRequestDto.getRequestTokenId());
                requestToken.setQualityCheckerId(rejectRequestDto.getQualityCheckerId());
                requestToken.setStatus(-1);

                requestTokenRepo.save(requestToken);
                return new ResponseEntity<>("Rejected Token Request.", HttpStatus.OK);
             }else {
                return new ResponseEntity<>("Request Token Id Not Found",HttpStatus.NOT_FOUND);
             }

       } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error while processing the Accept Request Token: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }


}
