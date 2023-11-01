package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.HelpDto;
import com.example.commercialsite.dto.request.RequestTokenRequestDto;
import com.example.commercialsite.dto.request.SwapRequestDto;
import com.example.commercialsite.dto.response.RequestTokenResponseDto;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.*;
import com.example.commercialsite.repository.*;
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
    private UsersRepo usersRepo;

    @Autowired
    private RequestTokenRepo requestTokenRepo;

    @Autowired
    private HelpSupportRepo helpSupportRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private TokenRepo tokenRepo;

    //@Autowired
    //private CustomerSubscriptionRepo customerSubscriptionRepo;

    @Autowired
    private SwapRepo swapRepo;

    @Autowired
    private InventoryManagerSwapRepo inventoryManagerSwapRepo;

    @Autowired
    private PaymentTableRepository paymentTableRepository;

    //private PaymentTable paymentTable;

    @Autowired
    private ToDTO toDTO;

    @Autowired
    private FromDTO fromDTO;


    @Override
    public ResponseEntity<StandardResponse> CreateRequestToken(RequestTokenRequestDto requestTokenRequestDto) {
        if (usersRepo.existsById(requestTokenRequestDto.getCustomerId())) { // user exist
            if (usersRepo.getReferenceById(requestTokenRequestDto.getCustomerId()).isActiveStatus()) { // get user object if the user is active
                if (usersRepo.getReferenceById(requestTokenRequestDto.getCustomerId()).isVerified()) { // user is verified

                    RequestToken requestToken = fromDTO.getRequestToken(requestTokenRequestDto); // mapping dto to entity
                    requestToken.setRequestDateTime(LocalDateTime.now());
                    requestToken.setStatus(0);

                    requestTokenRepo.save(requestToken);

                    RequestTokenResponseDto requestTokenResponseDto = toDTO.getRequestTokenResponseDto(requestToken);

                    return new ResponseEntity<>(
                            new StandardResponse(201, "saved Request.", requestTokenResponseDto),
                            HttpStatus.CREATED
                    );
                } else {
                    return new ResponseEntity<>(
                            new StandardResponse(400, "this user is not verified.", new RequestTokenRequestDto()),
                            HttpStatus.BAD_REQUEST
                    );
                }

            } else {
                return new ResponseEntity<>(
                        new StandardResponse(400, "this user is hold", new RequestTokenRequestDto()),
                        HttpStatus.BAD_REQUEST
                );
            }

        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "user not found for this user id.", new RequestTokenRequestDto()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public ResponseEntity<StandardResponse> AllRequestTokenFromCustomer(UsersDTO usersDTO) { // return all request_token requests from customer
        if (usersRepo.existsByEmailEquals(usersDTO.getEmail())) { // user exist
            Users users = usersRepo.getReferenceById(usersDTO.getUserId()); // getting the user as an object

            if (users.isVerified() && // user is verified?
                    users.isActiveStatus() // user is active?
            ) {// user is verified and active
//                List<RequestToken> list = requestTokenRepo.getAllByCustomerId(usersDTO.getUserId()); // previous code does not work any more after jhibernate relationships
                List<RequestToken> list = requestTokenRepo.getAllByCustomerId(users);
                List<RequestTokenResponseDto> dtoList = new ArrayList<>(list.size()); //  dto list of size of list

                list.forEach(requestToken -> dtoList.add(toDTO.getRequestTokenResponseDto(requestToken)));

                return new ResponseEntity<>(
                        new StandardResponse(201, "all requestToken from the user", dtoList),
                        HttpStatus.OK
                );

            } else { // user is not active or not verified
                return new ResponseEntity<>(
                        new StandardResponse(201, "user is not active of verified", null),
                        HttpStatus.CONFLICT
                );
            }
        } else { // user does not exist
            return new ResponseEntity<>(
                    new StandardResponse(201, "user is not found", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public ResponseEntity<StandardResponse> HelpRequestFromCustomer(HelpDto helpDto) {
        //get user data from customer id
        Users users = usersRepo.findByUserId(helpDto.getCustomerId());
        if (users != null) { //have a user for relevant id
            //convert the help dto to helpSupport entity
            HelpSupport helpSupport = fromDTO.setHelpRequestFromCustomer(helpDto);
            //save the data in Help_Support table
            helpSupportRepo.save(helpSupport);

            return new ResponseEntity<>(
                    new StandardResponse(200, "saved Request.", helpSupport),
                    HttpStatus.CREATED
            );
        } else { // doesn't have  a user from relevant id
            return new ResponseEntity<>(
                    new StandardResponse(400, "user is not found", null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<StandardResponse> requestSwap(SwapRequestDto swapRequestDto) {
        // existence of user
        if (usersRepo.existsByEmailEquals(swapRequestDto.getEmail())) { // user exist
            Users users = usersRepo.getReferenceById(swapRequestDto.getUserId()); // getting the user as an object

            // is user active and verified
            if (    !users.isVerified() || // user is verified?
                    !users.isActiveStatus() // user is active?
            ) {// user is not verified or not active
                return new ResponseEntity<>(
                        new StandardResponse(201, "user is not active or not verified", null),
                        HttpStatus.CONFLICT
                );
            }

            // check subscription validity
            //boolean subscriptionValidity = false;
            if( !paymentTableRepository.existsById(swapRequestDto.getCustomerId()) ){ // some sort of subscription  not available
                return new ResponseEntity<>(
                        new StandardResponse(201, "subscription not available", null),
                        HttpStatus.CONFLICT
                );
            }
            //CustomerSubscription customerSubscription = customerSubscriptionRepo.getReferenceById(swapRequestDto.getCustomerId());
            PaymentTable paymentTable = paymentTableRepository.getReferenceById(swapRequestDto.getUserId());

            // check customerSubscription state
            if( !paymentTable.isPayment() ){ // state is false
                return new ResponseEntity<>(
                        new StandardResponse(201, "subscription is not valid", null),
                        HttpStatus.CONFLICT
                );
            }


//            // is swaps available
//            if (customerSubscription.getCount() == 0) { // swaps not available
//                // setting subscription state invalid
//                customerSubscription.setState(false);
//                return new ResponseEntity<>(
//                        new StandardResponse(201, "swaps not available", null),
//                        HttpStatus.CONFLICT
//                );
//            } // swaps available
//            // reducing one from count //
//            customerSubscription.setCount( customerSubscription.getCount() - 1 ); //
//
//            LocalDateTime start = customerSubscription.getDateTime(); // time of subscription purchase
//            LocalDateTime end = LocalDateTime.now(); // current time
//            long duration = Duration.between(start, end).getSeconds();
//
//            // is subscription time available
//            if (duration < 60 * 10) { // 10 minutes or more is not  available
//                // setting subscription state invalid
//                customerSubscription.setState(false);
//                return new ResponseEntity<>(
//                        new StandardResponse(201, "end of subscription", null),
//                        HttpStatus.CONFLICT
//                );
//
//            } // 10 minutes or more is available  // subscription validity ends 10 minutes before the exact end time


            // item availability
            if( !itemRepo.existsById(swapRequestDto.getItemId()) ){ // item does not exist
                return new ResponseEntity<>(
                        new StandardResponse(201, "item does not exist", null),
                        HttpStatus.OK
                );
            }
            Item item = itemRepo.getReferenceById(swapRequestDto.getItemId());

            // is item available
            if( !item.isAvailableStatus() ){ // item is not available
                return new ResponseEntity<>(
                        new StandardResponse(201, "item is not available", null),
                        HttpStatus.OK
                );
            } // item is available
            // setting item to false
            item.setAvailableStatus(false);

            // token validity and price check
            if( !tokenRepo.existsById(swapRequestDto.getTokenId()) ){ // token does not exist
                return new ResponseEntity<>(
                        new StandardResponse(201, "token does not exist", null),
                        HttpStatus.OK
                );
            } // token exists
            Token token = tokenRepo.getReferenceById(swapRequestDto.getTokenId()); // getting token

            // is token available
            if(!token.isState()){ // token is not available
                return new ResponseEntity<>(
                        new StandardResponse(201, "token is not valid", null),
                        HttpStatus.OK
                );
            } // token is available

            // checking token price
            if( token.getPrice() < token.getPrice() ){ // token price is less than item price
                // token price is less than item price
                return new ResponseEntity<>(
                        new StandardResponse(201, "token price is less than the item", null),
                        HttpStatus.OK
                );
            } // token is eligible
            // setting token false
            token.setState(false);

            /// ready to request swapping
            Swap swap = fromDTO.getSwapRequest(swapRequestDto);
            // writing to database
            //customerSubscriptionRepo.save(customerSubscription);
            Swap swapSaved = swapRepo.save(swap);

            // connecting inventoryManagerSwap to swap object
            InventoryManagerSwap inventoryManagerSwap = new InventoryManagerSwap() ;
            inventoryManagerSwap.setSwapId(swapSaved.getSwapId());
            inventoryManagerSwap.setSwappingStatus(false);

            // saving inventoryManagerSwap reference
            inventoryManagerSwapRepo.save(inventoryManagerSwap);

            /* success */
            return new ResponseEntity<>(
                    new StandardResponse(201, "swap request was created", null),
                    HttpStatus.OK
            );


        } else { // user does not exist
            return new ResponseEntity<>(
                    new StandardResponse(201, "user is not found", null),
                    HttpStatus.BAD_REQUEST
            );
        }

    } // end of class requestSwap


} // end of class CustomerServiceImpl



