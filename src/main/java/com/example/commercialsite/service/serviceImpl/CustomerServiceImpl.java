package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.HelpDto;
import com.example.commercialsite.dto.request.RequestTokenRequestDto;
import com.example.commercialsite.dto.response.RequestTokenResponseDto;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.HelpSupport;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Users;
import com.example.commercialsite.repository.HelpSupportRepo;
import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.repository.UserRepo;
import com.example.commercialsite.service.CustomerService;
import com.example.commercialsite.utill.FromDTO;
import com.example.commercialsite.utill.StandardResponse;
import com.example.commercialsite.utill.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RequestTokenRepo requestTokenRepo;

    @Autowired
    private ToDTO toDTO;

    @Autowired
    private FromDTO fromDTO;

    @Autowired
    private HelpSupportRepo helpSupportRepo;

    @Override
    public ResponseEntity<StandardResponse> CreateRequestToken(RequestTokenRequestDto requestTokenRequestDto) {
        if (userRepo.existsById(requestTokenRequestDto.getCustomerId())) { // user exist
            if(userRepo.getReferenceById(requestTokenRequestDto.getCustomerId()).isActiveStatus()){ // get user object if the user is active
                if(userRepo.getReferenceById(requestTokenRequestDto.getCustomerId()).isVerified()){ // user is verified

                    RequestToken requestToken = fromDTO.getRequestToken(requestTokenRequestDto); // mapping dto to entity
                    requestToken.setRequestDateTime(LocalDateTime.now());
                    requestToken.setStatus(0);

                    requestTokenRepo.save(requestToken);

                    RequestTokenResponseDto requestTokenResponseDto = toDTO.getRequestTokenResponseDto(requestToken);

                    return new ResponseEntity<>(
                            new StandardResponse(201,"saved Request.",requestTokenResponseDto),
                            HttpStatus.CREATED
                    );
                }else{
                    return new ResponseEntity<>(
                            new StandardResponse(400,"this user is not verified.",new RequestTokenRequestDto()),
                            HttpStatus.BAD_REQUEST
                    );
                }

            }else{
                return new ResponseEntity<>(
                        new StandardResponse(400,"this user is hold",new RequestTokenRequestDto()),
                        HttpStatus.BAD_REQUEST
                );
            }

        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400,"user not found for this user id.",new RequestTokenRequestDto()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public ResponseEntity<StandardResponse> AllRequestTokenFromCustomer(UsersDTO usersDTO) { // return all request_token requests from customer
        if ( userRepo.existsByEmailEquals( usersDTO.getEmail() ) ) { // user exist
            Users users = userRepo.getReferenceById( usersDTO.getUserId() ); // getting the user as an object
            if ( users.isVerified() || // user is verified?
                 users.isActiveStatus() // user is active?
            ){// user is verified and active
               List<RequestToken> list = requestTokenRepo.getAllByCustomerId(usersDTO.getUserId());
               List<RequestTokenResponseDto> dtoList = new ArrayList<>(list.size()); //  dto list of size of list
                list.forEach(requestToken -> dtoList.add(toDTO.getRequestTokenResponseDto(requestToken)));

                return new ResponseEntity<>(
                        new StandardResponse(201,"user is not active of verified", dtoList ),
                        HttpStatus.OK
                );

            }else{ // user is not active or not verified
                return new ResponseEntity<>(
                        new StandardResponse(201,"user is not active of verified",null ),
                        HttpStatus.CONFLICT
                );
            }
        }
        else{ // user does not exist
            return new ResponseEntity<>(
                    new StandardResponse(201,"user is not found",null ),
                    HttpStatus.BAD_REQUEST
            );
        }
    }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @Override
    public ResponseEntity<StandardResponse> HelpRequestFromCustomer(HelpDto helpDto) {
        Users users = userRepo.findByUserId(helpDto.getCustomerId());
        if(users != null){
//        if(userRepo.existsByUserIdEquals(helpDto.getCustomerId())){
            HelpSupport helpSupport = fromDTO.setHelpRequestFromCustomer(helpDto);
            helpSupportRepo.save(helpSupport);

            return new ResponseEntity<>(
                    new StandardResponse(200,"saved Request.",helpSupport),
                    HttpStatus.CREATED
            );
        }else{
            return new ResponseEntity<>(
                    new StandardResponse(404,"user is not found",null ),
                    HttpStatus.BAD_REQUEST);
        }
    }


} // end of class CustomerServiceImpl
