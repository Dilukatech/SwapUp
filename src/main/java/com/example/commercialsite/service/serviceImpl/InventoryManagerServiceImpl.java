package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.response.HelpRequestDto;
import com.example.commercialsite.dto.response.Inv_Mng_TokenRequestDto;
import com.example.commercialsite.entity.HelpSupport;
import com.example.commercialsite.entity.InventoryManagerSwap;
import com.example.commercialsite.entity.InventoryManagerTokenRequest;
import com.example.commercialsite.repository.InventoryManagerSwapRepo;
import com.example.commercialsite.repository.InventoryManagerTokenRequestRepo;
import com.example.commercialsite.service.InventoryManagerService;
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
public class InventoryManagerServiceImpl implements InventoryManagerService {
    @Autowired
    private InventoryManagerTokenRequestRepo inv_managerTokenRequestRepo;

    @Autowired
    InventoryManagerSwapRepo inventoryManagerSwapRepo;

    @Autowired
    private ToDTO toDTO;


    @Override
    public ResponseEntity<StandardResponse> arrivedOrReturnItem(Long inventoryManagerId, Long requestId ,int shippingStatus) {
        if (inv_managerTokenRequestRepo.existsByRequestTokenId(requestId)){//check relevant request token id does have
            //select relevant object by request token id
            InventoryManagerTokenRequest inv_mng_tokenRequest = inv_managerTokenRequestRepo.getReferenceByRequestTokenIdEquals(requestId);

            // check if the Token request is already processed ( arrived / return back )
            if ( inv_mng_tokenRequest.getShipmentStatus() == 1 || // arrived
                    inv_mng_tokenRequest.getShipmentStatus() == -1 // return back
            ){
                return new ResponseEntity<>(
                        new StandardResponse(208,"request is already processed.", null),
                        HttpStatus.CREATED);
            }else {
                inv_mng_tokenRequest.setInventoryManagerId(inventoryManagerId);
                inv_mng_tokenRequest.setTimeCreated(LocalDateTime.now());
                inv_mng_tokenRequest.setShipmentStatus(shippingStatus);//set the status arrived or return
                inv_managerTokenRequestRepo.save(inv_mng_tokenRequest);


                return new ResponseEntity<>(
                        new StandardResponse(201,"saved Successfully.", inv_mng_tokenRequest),
                        HttpStatus.CREATED);
            }

        } else{ // request_id is not valid
           return new ResponseEntity<>(
                    new StandardResponse(400,"Request Id Not Found",null ),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override//selected swapping items shipping to the customer
    public ResponseEntity<StandardResponse> shippedSwappingItem(Long inventoryManagerId, Long swapId, boolean status) {
        if (inventoryManagerSwapRepo.existsBySwapIdEquals(swapId)){//check relevant swap id does have
            //select relevant object by swap id
            InventoryManagerSwap inventoryManagerSwap = inventoryManagerSwapRepo.getReferenceBySwapIdEquals(swapId);

            inventoryManagerSwap.setInventoryManagerId(inventoryManagerId);
            inventoryManagerSwap.setDateTime(LocalDateTime.now());
            inventoryManagerSwap.setSwappingStatus(status);//set the status shipped (1->shipped)
            inventoryManagerSwapRepo.save(inventoryManagerSwap);


            return new ResponseEntity<>(
                    new StandardResponse(201,"saved Successfully.", inventoryManagerSwap),
                    HttpStatus.CREATED);

        } else{ // swap-id is not valid
            return new ResponseEntity<>(
                    new StandardResponse(400,"swap Id Not Found",null ),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<StandardResponse> getAllUnprocessedSwapItems() {
        List<InventoryManagerTokenRequest> inventoryManagerTokenRequests = inv_managerTokenRequestRepo.findAllByShipmentStatusEquals(0); //get all unprocessed items
        List<Inv_Mng_TokenRequestDto> dtoList = new ArrayList<>(); // create a empty dto list
        if(!inventoryManagerTokenRequests.isEmpty()){ //list are not empty
            for(InventoryManagerTokenRequest inventoryManagerTokenRequest: inventoryManagerTokenRequests){
                dtoList.add(toDTO.getAllUnprocessedSwapItems(inventoryManagerTokenRequest));
            }
            return new ResponseEntity<>(
                    new StandardResponse(200,"get all unprocessed request successfully.",dtoList ),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>( //list are empty
                    new StandardResponse(400,"unprocessed inventory manager token request list is Empty.",null ),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
