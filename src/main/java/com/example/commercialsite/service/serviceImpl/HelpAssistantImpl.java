package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.CheckHelpDto;
import com.example.commercialsite.dto.response.HelpRequestDto;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class HelpAssistantImpl implements HelpAssistantService {
    @Autowired
    private HelpSupportRepo helpSupportRepo;

    @Autowired
    private FromDTO fromDTO;

    @Autowired
    private  ToDTO toDTO;

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
                    new StandardResponse(400,"request id not found",null ),
                    HttpStatus.BAD_REQUEST
            );


        }

    }

    @Override
    public ResponseEntity<StandardResponse> GetHelpRequestFromHelpRequestId(Long helpRequestId) {
        //get data to entity using help request id
        HelpSupport helpSupport = helpSupportRepo.getReferenceById(helpRequestId);
        //has a help request for help request id
        if(helpSupport != null){
            //convert the entity to dto
            HelpRequestDto helpRequestDto = toDTO.getHelpRequest(helpSupport);

            return new ResponseEntity<>(
                    new StandardResponse(400,"request success.",helpRequestDto ),
                    HttpStatus.OK);

        }else { //haven't a help request for relevant help request id
            return new ResponseEntity<>(
                    new StandardResponse(400,"request id not found.",null ),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<StandardResponse> GetAllHelpRequestFromStatus(boolean status) {
        List<HelpSupport> helpSupports = helpSupportRepo.findAllByStatusEquals(status); //get list of help request details
        List<HelpRequestDto> dtoList = new ArrayList<>(); // create a empty dto list
        if(!helpSupports.isEmpty()){ // help requests are not empty
            for(HelpSupport helpSupport: helpSupports){
                dtoList.add(toDTO.getHelpRequest(helpSupport));
            }
            return new ResponseEntity<>(
                    new StandardResponse(200,"success.",dtoList ),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>( //help requests are empty
                    new StandardResponse(400,"Help request list is Empty.",null ),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<StandardResponse> GetAllHelpRequests() {
        List<HelpSupport> helpSupports = helpSupportRepo.findAll(); //get list of help request details
        List<HelpRequestDto> dtoList = new ArrayList<>(); // create a empty dto list
        if(!helpSupports.isEmpty()){ // help requests are not empty
            for(HelpSupport helpSupport: helpSupports){
                dtoList.add(toDTO.getHelpRequest(helpSupport));
            }
            return new ResponseEntity<>(
                    new StandardResponse(200,"get help request successfully.",dtoList ),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>( //help requests are empty
                    new StandardResponse(400,"Help request list is Empty.",null ),
                    HttpStatus.BAD_REQUEST);
        }
    }
}

