package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.entity.InventoryManagerTokenRequest;
import com.example.commercialsite.repository.InventoryManagerTokenRequestRepo;
import com.example.commercialsite.service.InventoryManagerService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InventoryManagerServiceImpl implements InventoryManagerService {
    @Autowired
    private InventoryManagerTokenRequestRepo inv_managerTokenRequestRepo;

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
                inv_mng_tokenRequest.setShippedOrArrivedTime(LocalDateTime.now());
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
}
