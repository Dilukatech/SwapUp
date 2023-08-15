package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.UserRegisterRequest;
import com.example.commercialsite.dto.response.getAllUsersToAdmin;
import com.example.commercialsite.entity.User;
import com.example.commercialsite.repository.CustomerRepo;
import com.example.commercialsite.repository.UserRepo;
import com.example.commercialsite.service.UserService;
import com.example.commercialsite.utill.UserMapper;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserMapper userMapper;

    public String getEncodedPassword(String passWord){
        return passwordEncoder.encode(passWord);
    }

    @Override
    public ResponseEntity<String> registerUser(UserRegisterRequest userRegisterRequest) throws MessagingException, UnsupportedEncodingException {
        if (!userRepo.existsByEmailEquals(userRegisterRequest.getEmail())) {
            if (userRegisterRequest.getRole() == null || userRegisterRequest.getRole() == ""||userRegisterRequest.getRole() =="CUSTOMER") {
                User user = modelMapper.map(userRegisterRequest, User.class);
                user.setPassword(getEncodedPassword(userRegisterRequest.getPassword()));
                user.setActiveStatus(true);
                user.setRole("CUSTOMER");

                String randomCode = RandomString.make(64);
                user.setVerificationCode(randomCode);

                userRepo.save(user);
                String siteURL="http://localhost:3000";
                sendVerificationEmail( user, siteURL);
                return new ResponseEntity<>("please check your mail for verify your account",HttpStatus.OK);
            } else {
                User user = modelMapper.map(userRegisterRequest, User.class);
                user.setPassword(getEncodedPassword(userRegisterRequest.getPassword()));
                user.setActiveStatus(true);
                userRepo.save(user);
                return new ResponseEntity<>("saved " + user.getRole(),HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>("this user name already exist",HttpStatus.CONFLICT);
        }

    }

    private void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
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

        content = content.replace("[[name]]", user.getFirstName()+" "+user.getLastName());
        String verifyURL = siteURL + "/verifycode/" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    @Override
    public ResponseEntity<?>  verifyCustomer(String code) {
        User user=userRepo.findByVerificationCodeEquals(code);
        if(user == null || user.isVerified()){
            return new ResponseEntity<>("InValid Request",HttpStatus.BAD_REQUEST);
        }else{
            user.setVerificationCode(null);
            user.setVerified(true);
            userRepo.save(user);
            return new ResponseEntity<>("Account Verified",HttpStatus.OK);

        }
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public ResponseEntity<String> holdUser(Long userId) {

        User user= userRepo.findById(userId).orElse(null);;
        if(user == null || !user.isActiveStatus()){
            return new ResponseEntity<>("This User already hold",HttpStatus.BAD_REQUEST);
        }else{
            user.setActiveStatus(false);
            userRepo.save(user);
            return new ResponseEntity<>("User put on hold.",HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<String> removeHoldFromUser(Long userId) {
        User user= userRepo.findById(userId).orElse(null);;
        if(user == null || user.isActiveStatus()){
            return new ResponseEntity<>("this user already active.",HttpStatus.BAD_REQUEST);
        }else{
            user.setActiveStatus(true);
            userRepo.save(user);
            return new ResponseEntity<>("removed Hold From User.",HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<List<getAllUsersToAdmin>> getAllUsers() {
        List<User> users = userRepo.findAll();
        if(users.size()>0) {
            List<getAllUsersToAdmin> getAllUsers = userMapper.entityListTogetAllUsersToAdminDtoList(users);

            return new ResponseEntity<>(getAllUsers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
