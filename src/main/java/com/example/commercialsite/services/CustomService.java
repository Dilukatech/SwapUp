package com.example.commercialsite.services;

import com.example.commercialsite.dto.ItemDTO;
import com.example.commercialsite.dto.request.ProfilePictureRequest;
import com.example.commercialsite.dto.response.*;
import com.example.commercialsite.entity.HelpSupport;
import com.example.commercialsite.entity.Item;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Users;
import com.example.commercialsite.repository.HelpSupportRepo;
import com.example.commercialsite.repository.ItemRepo;
import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomService {

    @Autowired
    HelpSupportRepo helpSupportRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RequestTokenRepo requestTokenRepo;

    @Autowired
    ItemRepo itemRepo;

    public HelpRequestArrayResponse getHelpRequest(Long id) {
        Users user=userRepo.findByUserId(id);
        if(user==null){
            return HelpRequestArrayResponse.builder()
                    .message("No user found")
                    .data(null)
                    .build();
        }
        System.out.println(user);
        Optional<List<HelpSupport>> optionalHelpSupportList=helpSupportRepo.findAllByCustomerId(id);
        if(optionalHelpSupportList.isPresent()){
            List<HelpSupport> helpSupportList=optionalHelpSupportList.get();
            return HelpRequestArrayResponse.builder()
                    .message("Data fetch successfully")
                    .data(helpSupportList)
                    .build();
        }
            return HelpRequestArrayResponse.builder()
                    .message("No data found")
                    .data(null)
                    .build();

    }

    public MessageResponse updateProfilePic(Long id, ProfilePictureRequest profilePictureRequest) {
        Users user=userRepo.findByUserId(id);
        System.out.println(user);
        if(user==null){
            return MessageResponse.builder()
                    .message("User not found")
                    .build();
        }
        user.setProfilePicture(profilePictureRequest.getProfilePic());
        userRepo.save(user);

        Users updatedUser=userRepo.findByUserId(id);
        if(Objects.equals(updatedUser.getProfilePicture(), profilePictureRequest.getProfilePic())){
            return MessageResponse.builder()
                    .message("Updated successfully")
                    .build();
        }
        return MessageResponse.builder()
                .message("Server error")
                .build();

    }

    public AuthResponse getMe(Long id) {
        Users user=userRepo.findByUserId(id);
        if(user==null){
            return AuthResponse.builder()
                    .userId(null)
                    .build();
        }
        return AuthResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .email(user.getEmail())
                .telephone(user.getTelephone())
                .nic(user.getNic())
                .role(user.getRole())
                .profilePicture(user.getProfilePicture())
                .userId(user.getUserId())
                .token(null)
                .build();

    }

//    public RequestTokenResponse getAllRequestToken() {
//        List<RequestToken> requestTokenList=requestTokenRepo.findAll();
//        if(!requestTokenList.isEmpty()){
//            return RequestTokenResponse.builder()
//                    .data(requestTokenList)
//                    .message("Fetch successfully")
//                    .build();
//        }
//        return RequestTokenResponse.builder()
//                .message("Server error")
//                .build();
//
//    }

    public RequestTokenResponse getAllRequestToken() {
        List<RequestToken> requestTokenList = requestTokenRepo.findAll();
        List<RequestTokenResponseDto> dataArray=new ArrayList<>();

        System.out.println(requestTokenList);
        if (!requestTokenList.isEmpty()) {
            for(RequestToken requestToken:requestTokenList){
                RequestTokenResponseDto requestTokenResponseDto=new RequestTokenResponseDto();

                requestTokenResponseDto.setRequestTokenId(requestToken.getRequestTokenId());
                requestTokenResponseDto.setCustomerId(requestToken.getCustomerId());
                requestTokenResponseDto.setRequestDateTime(requestToken.getRequestDateTime());
                requestTokenResponseDto.setItemId(requestTokenResponseDto.getItemId());
                requestTokenResponseDto.setItemDescription(requestToken.getItemDescription());
                requestTokenResponseDto.setStatus(requestToken.getStatus());
                requestTokenResponseDto.setItemImage(requestToken.getItemImage());

                dataArray.add(requestTokenResponseDto);

            }
            return RequestTokenResponse.builder()
                    .message("Fetch successfully")
                    .data(dataArray)
                    .build();
        } else {
            return RequestTokenResponse.builder()
                    .message("Server error")
                    .build();
        }


    }

    public ItemResponse getAllItems() {
        List<ItemDTO> dataArray=new ArrayList<>();
        List<Item> itemList=itemRepo.findAll();
        if(!itemList.isEmpty()){
            for(Item item:itemList){
                ItemDTO itemDTO=new ItemDTO();

                itemDTO.setItemId(item.getItemId());
                itemDTO.setColor(item.getColor());
                itemDTO.setGender(item.getGender());
                itemDTO.setSize(item.getSize());
                itemDTO.setType(item.getType());
                itemDTO.setActiveState(item.isAvailableStatus());
                itemDTO.setPrice(item.getPrice());
                itemDTO.setImageURL(item.getImageURL());

                dataArray.add(itemDTO);
            }
            return ItemResponse.builder()
                    .message("Fetch successfully")
                    .data(dataArray)
                    .build();
        }else{
            return ItemResponse.builder()
                    .message("No data found")
                    .data(Collections.emptyList())
                    .build();
        }
    }
}
