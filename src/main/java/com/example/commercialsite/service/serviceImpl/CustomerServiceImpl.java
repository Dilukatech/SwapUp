package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.CustomerRequestTokenDto;
import com.example.commercialsite.dto.response.CustomerRequestTokenResponseDto;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.repository.UserRepo;
import com.example.commercialsite.service.CustomerService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RequestTokenRepo requestTokenRepo;

    @Override
    public ResponseEntity<StandardResponse> CreateRequestToken(CustomerRequestTokenDto customerRequestTokenDto) {
        if (userRepo.existsById(customerRequestTokenDto.getCustomerId())) {
            if(userRepo.getReferenceById(customerRequestTokenDto.getCustomerId()).isActiveStatus()){
                if(userRepo.getReferenceById(customerRequestTokenDto.getCustomerId()).isVerified()){
                    RequestToken requestToken = new RequestToken();
                    requestToken.setCustomerId(customerRequestTokenDto.getCustomerId());
                    requestToken.setItemDescription(customerRequestTokenDto.getItemDescription());
                    requestToken.setItemImage(customerRequestTokenDto.getItemImage());
                    requestToken.setRequestDateTime(LocalDateTime.now());
                    requestToken.setStatus(0);

                    requestTokenRepo.save(requestToken);

                    CustomerRequestTokenResponseDto dto = new CustomerRequestTokenResponseDto(
                            requestToken.getRequestTokenId(),
                            requestToken.getCustomerId(),
                            requestToken.getStatus(),
                            requestToken.getItemDescription(),
                            requestToken.getItemImage(),
                            requestToken.getRequestDateTime()
                    );

                    return new ResponseEntity<StandardResponse>(
                            new StandardResponse(201,"saved Request.",dto),
                            HttpStatus.CREATED
                    );
                }else{
                    return new ResponseEntity<StandardResponse>(
                            new StandardResponse(400,"this user is not verified.",new CustomerRequestTokenDto()),
                            HttpStatus.BAD_REQUEST
                    );
                }

            }else{
                return new ResponseEntity<StandardResponse>(
                        new StandardResponse(400,"this user is hold",new CustomerRequestTokenDto()),
                        HttpStatus.BAD_REQUEST
                );
            }

        } else {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(400,"user not found for this user id.",new CustomerRequestTokenDto()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
