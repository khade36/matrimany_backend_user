package com.matirmony.matirmony.service;

import com.matirmony.matirmony.userDetails.PersonalDetail;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserDetailsI {
    String saveUser(PersonalDetail personalDetail);
    String updateDetails(PersonalDetail personalDetail,String emailId);
    String generateOtp(String email);
    String verifyOTP(String email,String emailOtp);
    String addImage(String email,MultipartFile file) throws IOException;
    String loginUser(String email,String password);
    List<PersonalDetail> findAll();
    List<PersonalDetail> findAllByCity(String city, String currentUser);
    List<PersonalDetail> findAllByCity_City(String city);
    String passChange(String email,String password);
    String sendRequest(String firstEmail, String SecondPersonEmail);
    String addToFriend(String ownEmail,String friendEmail);
    PersonalDetail findMyProfile(String email);
    boolean idActivationCheck(String email);
    List<PersonalDetail> findFriendSentList(String email);
    List<PersonalDetail> findFriendReceivedList(String email);
}
