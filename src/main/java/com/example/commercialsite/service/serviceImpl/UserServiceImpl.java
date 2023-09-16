package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.UserRegisterRequest;
import com.example.commercialsite.dto.response.getAllUsersToAdmin;
import com.example.commercialsite.entity.Users;
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
            if (// userRegisterRequest.getRole().isEmpty() ||  redundant
                    userRegisterRequest.getRole().isEmpty() || // changed ==("") with .isEmpty()
                    userRegisterRequest.getRole().equals("CUSTOMER") // changed == "CUSTOMER" to .equals()
                )
            {
                Users users = modelMapper.map(userRegisterRequest, Users.class);
                users.setPassword(getEncodedPassword(userRegisterRequest.getPassword()));
                users.setActiveStatus(true);
                users.setRole("CUSTOMER");

                String randomCode = RandomString.make(64);
                users.setVerificationCode(randomCode);

                userRepo.save(users);
                String siteURL="http://localhost:3000";
                sendVerificationEmail(users, siteURL);
                return new ResponseEntity<>("please check your mail for verify your account",HttpStatus.OK);
            } else {
                Users users = modelMapper.map(userRegisterRequest, Users.class);
                users.setPassword(getEncodedPassword(userRegisterRequest.getPassword()));
                users.setActiveStatus(true);
                userRepo.save(users);
                return new ResponseEntity<>("saved " + users.getRole(),HttpStatus.OK);
            }
        }else{
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

    @Override
    public ResponseEntity<String> holdUser(Long userId) {

        Users users = userRepo.findById(userId).orElse(null);;
        if(users == null || !users.isActiveStatus()){
            return new ResponseEntity<>("This User already hold",HttpStatus.BAD_REQUEST);
        }else{
            users.setActiveStatus(false);
            userRepo.save(users);
            return new ResponseEntity<>("User put on hold.",HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<String> removeHoldFromUser(Long userId) {
        Users users = userRepo.findById(userId).orElse(null);;
        if(users == null || users.isActiveStatus()){
            return new ResponseEntity<>("this user already active.",HttpStatus.BAD_REQUEST);
        }else{
            users.setActiveStatus(true);
            userRepo.save(users);
            return new ResponseEntity<>("removed Hold From User.",HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<List<getAllUsersToAdmin>> getAllUsers() {
        List<Users> users = userRepo.findAll();
        if(users.size()>0) {
            List<getAllUsersToAdmin> getAllUsers = userMapper.entityListTogetAllUsersToAdminDtoList(users);

            return new ResponseEntity<>(getAllUsers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public Users getCustomer(String email) {
        if(userRepo.existsByEmailEquals(email)){
            Users users = userRepo.findByEmailEquals(email);
            return users;
        }else{
            return null;
        }
    }
}
