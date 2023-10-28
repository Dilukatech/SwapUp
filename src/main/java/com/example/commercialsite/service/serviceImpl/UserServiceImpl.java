package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.Users;
import com.example.commercialsite.repository.HoldUserRepo;
import com.example.commercialsite.repository.UsersRepo;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepo usersRepo;

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
        if (!usersRepo.existsByEmailEquals(userRegisterRequestDTO.getEmail())) {
            // user does not exist      // removed previous redundant validations
            Users users = fromDTO.getUsers(userRegisterRequestDTO); // take userRegister request and spit a users object

            String randomCode = RandomString.make(64); // random code for verification
            users.setVerificationCode(randomCode);

            usersRepo.save(users);

            String siteURL="http://localhost:3000";
            sendVerificationEmail(users, siteURL);

            return new ResponseEntity<>("please check your mail for verify your account --> " +
                    users.getVerificationCode(),HttpStatus.OK);

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
    public ResponseEntity<?> verifyUsers(String code) {
        Users users = usersRepo.findByVerificationCodeEquals(code);
        if(users == null || users.isVerified()){ // no such user by that code or the user is already verified
            return new ResponseEntity<>("InValid Request \nuser is already verified",HttpStatus.BAD_REQUEST);
        }else{ // user is not null and user is not verified
            users.setVerificationCode(null);
            users.setVerified(true);
            usersRepo.save(users);
            return new ResponseEntity<>("Account Verified",HttpStatus.OK);

        }
    }

    @Override
    public Optional<Users> getUserById(Long userId) {
        return usersRepo.findById(userId);
    }

    @Override
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<Users> users = usersRepo.findAll();
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


}
