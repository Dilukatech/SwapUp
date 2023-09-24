package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.HoldDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.HoldUser;
import com.example.commercialsite.entity.Users;
import com.example.commercialsite.repository.HoldUserRepo;
import com.example.commercialsite.repository.UserRepo;
import com.example.commercialsite.service.UserService;
import com.example.commercialsite.utill.FromDTO;
import com.example.commercialsite.utill.ToDTO;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.Column;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ToDTO toDTO;

    @Autowired
    private FromDTO fromDTO;

    @Autowired
    private HoldUserRepo holdUserRepo;


    @Override
    public ResponseEntity<String> registerUser(UserRegisterRequestDTO userRegisterRequestDTO) throws MessagingException, UnsupportedEncodingException {
        /* if user does not exist */
        if (!userRepo.existsByEmailEquals(userRegisterRequestDTO.getEmail())) {
            // user does not exist      // removed previous redundant validations
            Users users = fromDTO.getUsers(userRegisterRequestDTO); // take userRegister request and spit a users object

            String randomCode = RandomString.make(64); // random code for verification
            users.setVerificationCode(randomCode);

            userRepo.save(users);

            String siteURL="http://localhost:3000";
            sendVerificationEmail(users, siteURL);

            return new ResponseEntity<>("please check your mail for verify your account",HttpStatus.OK);

        } else { // user does exist
            return new ResponseEntity<>("this user name already exist",HttpStatus.CONFLICT);
        }
    }

    private void sendVerificationEmail(Users users, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = users.getEmail();
        String fromAddress = "swapup41@gmail.com";
        String senderName = "SwapUp";
        String subject = "Please verify your request";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your request:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", users.getFirstName()+" "+ users.getLastName());
        String verifyURL = siteURL + "/verifycode/" + users.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    @Override
    public ResponseEntity<?>  verifyCustomer(String code) {
        Users users =userRepo.findByVerificationCodeEquals(code);
        if(users == null || users.isVerified()){
            return new ResponseEntity<>("InValid Request",HttpStatus.BAD_REQUEST);
        }else{
            users.setVerificationCode(null);
            users.setVerified(true);
            userRepo.save(users);
            return new ResponseEntity<>("Account Verified",HttpStatus.OK);

        }
    }

    @Override
    public Optional<Users> getUserById(Long userId) {
        return userRepo.findById(userId);
    }

//    @Override
//    public ResponseEntity<String> holdUser(Long userId) {
//
//        Users users = userRepo.findById(userId).orElse(null);
//        if(users == null || !users.isActiveStatus()){
//            return new ResponseEntity<>("This User already hold",HttpStatus.BAD_REQUEST);
//        }else{
//            users.setActiveStatus(false);
//            userRepo.save(users);
//            return new ResponseEntity<>("User put on hold.",HttpStatus.OK);
//
//        }
//    }
//
//    @Override
//    public ResponseEntity<String> removeHoldFromUser(Long userId) {
//        Users users = userRepo.findById(userId).orElse(null);
//        if(users == null || users.isActiveStatus()){
//            return new ResponseEntity<>("this user already active.",HttpStatus.BAD_REQUEST);
//        }else{
//            users.setActiveStatus(true);
//            userRepo.save(users);
//            return new ResponseEntity<>("removed Hold From User.",HttpStatus.OK);
//
//        }
//    }

    @Override
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<Users> users = userRepo.findAll();
        List<UsersDTO> usersDTOList = new ArrayList<>();  // an empty list to store all userDTO objects
        if (!users.isEmpty()) { //users list is not empty
            for (Users user : users) {
                usersDTOList.add(toDTO.getUsersDTO(user));
            }
            return new ResponseEntity<>(usersDTOList, HttpStatus.OK);
        } else { // users list is empty
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> holdAccount(HoldDto holdDto) {
        Users users = userRepo.findByUserId(holdDto.getCustomerId());

        if (users != null) { //customer is not empty
            users.setActiveStatus(holdDto.getAction());  //set the action of customer as hold or remove hold
            userRepo.save(users);

            HoldUser holdUser = new HoldUser();
            holdUser.setAdminId(holdDto.getAdminId());
            holdUser.setCustomerId(holdUser.getCustomerId());
            holdUser.setHoldTime(LocalDateTime.now());
            holdUser.setReason(holdUser.getReason());
            holdUserRepo.save(holdUser);    //saved request detail of hold user table

            return new ResponseEntity<>("request Success.", HttpStatus.OK);
        } else { // user is empty
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
