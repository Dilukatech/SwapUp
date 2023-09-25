package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.CheckHelpDto;
import com.example.commercialsite.entity.HelpSupport;
import com.example.commercialsite.repository.HelpSupportRepo;
import com.example.commercialsite.service.HelpAssistantService;
import com.example.commercialsite.utill.FromDTO;
import com.example.commercialsite.utill.StandardResponse;
import com.example.commercialsite.utill.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelpAssistantImpl implements HelpAssistantService {
    @Autowired
    private HelpSupportRepo helpSupportRepo;

    @Autowired
    private FromDTO fromDTO;

    @Override
    public ResponseEntity<StandardResponse> CheckHelpRequest(CheckHelpDto checkHelpDto) {
        //help request cant null and check help request id is null
        if (checkHelpDto.getHelpRequestId() != null && helpSupportRepo.existsByHelpRequestIdEquals(checkHelpDto.getHelpRequestId())) {
           //get data from relevant help support id
            HelpSupport helpSupport = helpSupportRepo.findById(checkHelpDto.getHelpRequestId()).orElse(null);
            //convert the data from check dto to help support object
            HelpSupport helpSupportResponse = fromDTO.checkHelpRequest(helpSupport,checkHelpDto);
            //save the data from help support table
            helpSupportRepo.save(helpSupportResponse);

            return new ResponseEntity<>(
                    new StandardResponse(200,"saved Request.",helpSupportResponse),
                    HttpStatus.CREATED
            );
        } else {
            //the ID is null or the user does not exist.
            return new ResponseEntity<>(
                    new StandardResponse(404,"request id not found",null ),
                    HttpStatus.NOT_FOUND
            );


        }

    }
}
