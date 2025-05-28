package com.matirmony.matirmony.controller;

import com.matirmony.matirmony.service.UserDetailIMPL;
import com.matirmony.matirmony.userDetails.Image;
import com.matirmony.matirmony.userDetails.LoginDetail;
import com.matirmony.matirmony.userDetails.PersonalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserDetailIMPL userDetailIMPLService;

    public userController(UserDetailIMPL userDetailIMPLService) {
        this.userDetailIMPLService = userDetailIMPLService;
    }

    public userController() {
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody PersonalDetail personalDetail) {
        return new ResponseEntity<>(userDetailIMPLService.saveUser(personalDetail), HttpStatus.OK);
    }

    @PostMapping("/update/{emailId}")
    public ResponseEntity<?> updateDetails(@RequestBody PersonalDetail personalDetail,@PathVariable String emailId) {
        return new ResponseEntity<>(userDetailIMPLService.updateDetails(personalDetail,emailId), HttpStatus.OK);
    }

    @PostMapping("/login/{emailId}/{password}")
    public ResponseEntity<?> login(@PathVariable String emailId,@PathVariable String password) {
        return new ResponseEntity<>(userDetailIMPLService.loginUser(emailId, password), HttpStatus.OK);
    }

    @PostMapping("/generate-otp/{emailId}")
    public ResponseEntity<?> generateOTP(@PathVariable String emailId) {
        return new ResponseEntity<>(userDetailIMPLService.generateOtp(emailId), HttpStatus.OK);
    }

    @PostMapping("/verify-otp/{emailId}/{otp}")
    public ResponseEntity<?> verifyOTP(@PathVariable String emailId,@PathVariable String otp) {
        return new ResponseEntity<>(userDetailIMPLService.verifyOTP(emailId, otp), HttpStatus.OK);
    }

    @PostMapping("/upload-image/{emailId}")
    public ResponseEntity<String> uploadImage(@PathVariable String emailId, @RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity<>(userDetailIMPLService.addImage(emailId, file), HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        }
    }
    @GetMapping("/retrive/image/{emailId}")
    public ResponseEntity<?> retriveImage(@PathVariable String emailId) {
        Image image=userDetailIMPLService.retriveImage(emailId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .contentType(org.springframework.http.MediaType.parseMediaType(image.getType()))
                .body(image.getImageData());
    }
    @GetMapping("/findAll")
    public List<PersonalDetail> findAll() {
        return userDetailIMPLService.findAll();
    }
    @GetMapping("/findAllByCity/{city}/{emailId}")
    public List<PersonalDetail> findAllByCity(@PathVariable String city,@PathVariable String emailId) {
        return userDetailIMPLService.findAllByCity(city,emailId);
    }
    @GetMapping("/findAllByCity/{city}")
    public List<PersonalDetail> findAllByCity(@PathVariable String city) {
        return userDetailIMPLService.findAllByCity_City(city);
    }
    @PostMapping("/password-reset/{emailId}/{password}")
    public ResponseEntity<?> passwordReset(@PathVariable String emailId,@PathVariable String password){
        return new ResponseEntity<>(userDetailIMPLService.passChange(emailId, password),HttpStatus.OK);
    }
    @GetMapping("/myProfile/{emailId}")
    public ResponseEntity<?> getMyProfile(@PathVariable String emailId){
        return new ResponseEntity<>(userDetailIMPLService.findMyProfile(emailId),HttpStatus.OK);
    }
    @GetMapping("/checkProfileVerification/{emailId}")
    public ResponseEntity<?> checkIdActivation(@PathVariable String emailId){
        return new ResponseEntity<>(userDetailIMPLService.idActivationCheck(emailId),HttpStatus.OK);
    }
    @PostMapping("/sendRequest/{emailId}/{secondPersonEmail}")
    public ResponseEntity<?> checkIdActivation(@PathVariable String emailId,@PathVariable String secondPersonEmail){
        return new ResponseEntity<>(userDetailIMPLService.sendRequest(emailId, secondPersonEmail),HttpStatus.OK);
    }
    @GetMapping("/sendRequests/{emailId}")
    public ResponseEntity<?> friendRequestSent(@PathVariable String emailId){
        return new ResponseEntity<>(userDetailIMPLService.findFriendSentList(emailId),HttpStatus.OK);
    }
    @GetMapping("/receivedRequests/{emailId}")
    public ResponseEntity<?> friendRequestReceived(@PathVariable String emailId){
        return new ResponseEntity<>(userDetailIMPLService.findFriendReceivedList(emailId),HttpStatus.OK);
    }
    @PostMapping("/addFriend/{emailId}/{friendEmailId}")
    public  ResponseEntity<?> addFriend(@PathVariable String emailId,@PathVariable String friendEmailId){
        return new ResponseEntity<>(userDetailIMPLService.addToFriend(emailId, friendEmailId),HttpStatus.OK);
    }

}
