package com.example.commercialsite.utill.utilimpl;


import com.example.commercialsite.dto.request.*;
import com.example.commercialsite.entity.*;
import com.example.commercialsite.repository.InventoryManagerSwapRepo;
import com.example.commercialsite.repository.ItemRepo;
import com.example.commercialsite.repository.TokenRepo;
import com.example.commercialsite.repository.UsersRepo;
import com.example.commercialsite.utill.FromDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
class FromDTOImpl implements FromDTO { // from DTO to relevant entity object

    @Autowired
    private PasswordEncoder passwordEncoder;
    public String getEncodedPassword(String passWord){
        return passwordEncoder.encode(passWord);
    }

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private InventoryManagerSwapRepo inventoryManagerSwapRepo;

    @Override
    public Users getUsers(UserRegisterRequestDTO dto) {
        Users entity = new Users();
        entity.setEmail(dto.getEmail());
        entity.setPassword(getEncodedPassword(dto.getPassword()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setNic(dto.getNic());
        entity.setTelephone(dto.getTelephone());
        entity.setProfilePicture(dto.getProfilePicture());
        entity.setAddress(dto.getAddress());
        entity.setRole(dto.getRole());
        entity.setActiveStatus(true); // user is enabled at profile creation

        return entity;
    }

    @Override
    public RequestToken getRequestToken(RequestTokenRequestDto dto) {
        RequestToken entity = new RequestToken();

//        entity.setCustomerId(dto.getCustomerId()); // previous code
        // cestCustomer accept a Users object  // possibility --> users table being also updated
        // getting users object by id
        entity.setCustomerId(usersRepo.getReferenceById(dto.getCustomerId()));
        entity.setItemImage(dto.getItemImage());
        entity.setItemDescription(dto.getItemDescription());

        return entity;
    }




    @Override
    public Item getItem(RequestToken requestToken, AcceptRequestDto acceptRequestDto) {
        Item item = new Item();

        item.setColor(acceptRequestDto.getColor());
        item.setImageUrl(acceptRequestDto.getImageURL());
        item.setGender(acceptRequestDto.getGender());
        item.setType(acceptRequestDto.getType());
        item.setPrice(acceptRequestDto.getPrice());
        item.setAvailableStatus(true);
        item.setSize(acceptRequestDto.getSize());

        return item;
    }

    @Override
    public HelpSupport setHelpRequestFromCustomer(HelpDto helpDto) {
      HelpSupport entity = new HelpSupport();

      entity.setCustomerId(helpDto.getCustomerId());
      entity.setSubject(helpDto.getSubject());
      entity.setMessage(helpDto.getMessage());
      entity.setReceivedTime(LocalDateTime.now());
      entity.setStatus(false);

      return entity;
    }

    @Override
    public HelpSupport checkHelpRequest(HelpSupport helpSupport, CheckHelpDto checkHelpDto) {
        helpSupport.setHelpAssistantId(checkHelpDto.getHelpAssistantId());
        helpSupport.setReply(checkHelpDto.getReply());
        helpSupport.setStatus(true);

        return helpSupport;
    }

    public Swap getSwapRequest(SwapRequestDto swapRequestDto){
        Swap swap = new Swap();
        // injecting data
        swap.setUsersId(usersRepo.getReferenceById(swapRequestDto.getCustomerId()));
        swap.setItemId(itemRepo.getReferenceById(swapRequestDto.getItemId()));
        swap.setTokenId(tokenRepo.getReferenceById(swapRequestDto.getTokenId()));
        swap.setRequestDateTime(LocalDateTime.now());

        return swap;
    }



}
