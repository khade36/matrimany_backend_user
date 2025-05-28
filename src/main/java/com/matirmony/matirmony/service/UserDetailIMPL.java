package com.matirmony.matirmony.service;

import com.matirmony.matirmony.repository.UserDetailRepository;
import com.matirmony.matirmony.service.otputil.EmailUtil;
import com.matirmony.matirmony.service.otputil.OtpUtil;
import com.matirmony.matirmony.userDetails.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailIMPL implements UserDetailsI {

    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private JWTService jwtService;

    public UserDetailIMPL(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    public UserDetailIMPL() {
    }

    @Override
    public String saveUser(PersonalDetail personalDetail) {
        System.out.println(personalDetail);
        String result = "";
        if (userDetailRepository.findByEmailId(personalDetail.getEmailId()) != null) {
            result = "duplicate email id";
        } else {
            //personalDetails
            PersonalDetail personalDetails = new PersonalDetail();
            personalDetails.setProfileFor(personalDetail.getProfileFor());
            personalDetails.setName(personalDetail.getName());
            personalDetails.setEmailId(personalDetail.getEmailId());
            personalDetails.setPassword(personalDetail.getPassword());
            //profileDetails
            ProfileDetail profileDetail = new ProfileDetail();
            profileDetail.setDob(personalDetail.getProfileDetail().getDob());
            profileDetail.setSubCaste(personalDetail.getProfileDetail().getSubCaste());
            profileDetail.setMotherTongue(personalDetail.getProfileDetail().getMotherTongue());
            profileDetail.setMaritalStatus(personalDetail.getProfileDetail().getMaritalStatus());
            profileDetail.setPhoneNumber(personalDetail.getProfileDetail().getPhoneNumber());
            personalDetails.setProfileDetail(profileDetail);
            //careerDetails
            CareerDetail careerDetail = new CareerDetail();
            System.out.println(personalDetail.getCareerDetail().getState());
            careerDetail.setCountryDetails(personalDetail.getCareerDetail().getCountryDetails());
            careerDetail.setState(personalDetail.getCareerDetail().getState());
            careerDetail.setCity(personalDetail.getCareerDetail().getCity());
            careerDetail.setHighestEducation(personalDetail.getCareerDetail().getHighestEducation());
            careerDetail.setEmployedIn(personalDetail.getCareerDetail().getEmployedIn());
            careerDetail.setAnnualIncome(personalDetail.getCareerDetail().getAnnualIncome());
            careerDetail.setAbout(personalDetail.getCareerDetail().getAbout());
            personalDetails.setCareerDetail(careerDetail);
            //HoroscopeDetails
            HoroscopeDetail horoscopeDetail = new HoroscopeDetail();
            horoscopeDetail.setStar(personalDetail.getHoroscopeDetail().getStar());
            horoscopeDetail.setRasi(personalDetail.getHoroscopeDetail().getRasi());
            horoscopeDetail.setGothra(personalDetail.getHoroscopeDetail().getGothra());
            horoscopeDetail.setManglik(personalDetail.getHoroscopeDetail().getManglik());
            horoscopeDetail.setBirthTime(personalDetail.getHoroscopeDetail().getBirthTime());
            horoscopeDetail.setCountryOfBirth(personalDetail.getHoroscopeDetail().getCountryOfBirth());
            horoscopeDetail.setStateOfBirth(personalDetail.getHoroscopeDetail().getStateOfBirth());
            horoscopeDetail.setCityOfBirth(personalDetail.getHoroscopeDetail().getCityOfBirth());
            personalDetails.setHoroscopeDetail(horoscopeDetail);

            //familyDetails
            FamilyDetail familyDetail = new FamilyDetail();
            familyDetail.setFamilyType(personalDetail.getFamilyDetail().getFamilyType());
            familyDetail.setFatherOccupation(personalDetail.getFamilyDetail().getFatherOccupation());
            familyDetail.setMotherOccupation(personalDetail.getFamilyDetail().getMotherOccupation());
            familyDetail.setSibling(personalDetail.getFamilyDetail().getSibling());
            familyDetail.setHomeTown(personalDetail.getFamilyDetail().getHomeTown());
            familyDetail.setAboutFamily(personalDetail.getFamilyDetail().getAboutFamily());
//            familyDetail.setFamilyType(personalDetail.getFamilyDetail().getFamilyType());
            personalDetails.setFamilyDetail(familyDetail);

            personalDetails.setIdActivated(false);
            personalDetails.setAcceptInvite(false);
            userDetailRepository.deleteById(personalDetail.getId());
            userDetailRepository.save(personalDetails);
            result = "email saved";
        }
        return result;
    }

    @Override
    @Transactional
    public String updateDetails(PersonalDetail personalDetail, String emailId ) {
        String result = "";
        if (userDetailRepository.findByEmailId(personalDetail.getEmailId()) != null) {
            PersonalDetail getDetails = userDetailRepository.findByEmailId(emailId);

            ProfileDetail profileDetail = new ProfileDetail();
            profileDetail.setDob(personalDetail.getProfileDetail().getDob());
            profileDetail.setSubCaste(personalDetail.getProfileDetail().getSubCaste());
            profileDetail.setMotherTongue(personalDetail.getProfileDetail().getMotherTongue());
            profileDetail.setMaritalStatus(personalDetail.getProfileDetail().getMaritalStatus());
            profileDetail.setPhoneNumber(personalDetail.getProfileDetail().getPhoneNumber());
            getDetails.setProfileDetail(profileDetail);

            CareerDetail careerDetail = new CareerDetail();
            careerDetail.setCountryDetails(personalDetail.getCareerDetail().getCountryDetails());
            careerDetail.setState(personalDetail.getCareerDetail().getState());
            careerDetail.setCity(personalDetail.getCareerDetail().getCity());
            careerDetail.setHighestEducation(personalDetail.getCareerDetail().getHighestEducation());
            careerDetail.setEmployedIn(personalDetail.getCareerDetail().getEmployedIn());
            careerDetail.setAnnualIncome(personalDetail.getCareerDetail().getAnnualIncome());
            careerDetail.setAbout(personalDetail.getCareerDetail().getAbout());
            getDetails.setCareerDetail(careerDetail);

            FamilyDetail familyDetail = new FamilyDetail();
            familyDetail.setFamilyType(personalDetail.getFamilyDetail().getFamilyType());
            familyDetail.setFatherOccupation(personalDetail.getFamilyDetail().getFatherOccupation());
            familyDetail.setMotherOccupation(personalDetail.getFamilyDetail().getMotherOccupation());
            familyDetail.setSibling(personalDetail.getFamilyDetail().getSibling());
            familyDetail.setHomeTown(personalDetail.getFamilyDetail().getHomeTown());
            familyDetail.setAboutFamily(personalDetail.getFamilyDetail().getAboutFamily());
            getDetails.setFamilyDetail(familyDetail);

        }
        return result = "details are updated";
    }

    @Override
    public String generateOtp(String email) {
        PersonalDetail getDetails = userDetailRepository.findByEmailId(email);
        String getEmail = getDetails.getEmailId();
        String result = "";
        if (getEmail == null || getEmail == "" || getEmail.equals("")) {
            result = "you are not registered with us !";
        } else {
            OtpUtil otpUtil = new OtpUtil();
            String otp = otpUtil.generateOtp();
            try {
                emailUtil.sendOtpEmail(getEmail, otp);
                getDetails.setOtp(otp);
                userDetailRepository.deleteById(getDetails.getId());
                userDetailRepository.save(getDetails);
                result = "OTP sent !";
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public String verifyOTP(String email, String emailOtp) {
        String result = "";
        if (userDetailRepository.findByEmailId(email) == null) {
            result = "Email is not registered";

        } else {
            PersonalDetail getDetails = userDetailRepository.findByEmailId(email);
            String getEmail = getDetails.getEmailId();
            boolean IdActivated = getDetails.isIdActivated();
            String idActivate = Boolean.toString(IdActivated);
//        OtpUtil otpUtil=new OtpUtil();

            if ((getEmail != null || getEmail != "") && !IdActivated) {
                if (emailOtp.equals(getDetails.getOtp())) {
                    getDetails.setIdActivated(true);
//                userDetailRepository.deleteById(getDetails.getId());
//                userDetailRepository.save(getDetails);
                    result = "OTP is verified!";
                    if (result.equals("OTP is verified!")) {
                        LoginDetail loginDetail = new LoginDetail();
                        loginDetail.setEmailId(getDetails.getEmailId());
                        loginDetail.setPassword(getDetails.getPassword());
                        getDetails.setLoginDetail(loginDetail);
                        userDetailRepository.deleteById(getDetails.getId());
                        userDetailRepository.save(getDetails);
                    }
                }
            } else {
                result = "OTP is already verified!";
            }
        }
        return result;
    }

    @Override
    public String addImage(String email, MultipartFile file) throws IOException {
        PersonalDetail getDetails = userDetailRepository.findByEmailId(email);

        // Compress image to maximum 500KB
        byte[] compressedImage = compressImage(file);

        Image image = new Image();
        image.setName(email);
        image.setType(file.getContentType());
        image.setImageData(compressedImage); // Set compressed image data
        getDetails.setImage(image);

        // Save the updated details (assuming delete+save is your intended approach)
        userDetailRepository.deleteById(getDetails.getId());
        userDetailRepository.save(getDetails);

        return "Image uploaded and compressed to 500KB";
    }

    private byte[] compressImage(MultipartFile file) throws IOException {
        byte[] originalBytes = file.getBytes();
        byte[] imageBytes;

        // Start at full quality (1.0) and lower gradually
        double quality = 1.0;

        // Try compressing in a loop; limit the number of attempts to avoid infinite loops
        for (int i = 0; i < 10; i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Each iteration creates a new ByteArrayInputStream over the original bytes
            Thumbnails.of(new ByteArrayInputStream(originalBytes))
                    .scale(1.0)  // No change in dimensions
                    .outputQuality(quality)
                    .outputFormat("jpg")
                    .toOutputStream(baos);
            imageBytes = baos.toByteArray();

            // If the compressed image size is under or equal to 500KB, break out of the loop
            if (imageBytes.length <= 500 * 1024) {
                return imageBytes;
            }

            // Reduce the quality by 10% for the next iteration
            quality -= 0.1;
            // Ensure quality does not drop below a minimum threshold (e.g. 10%)
            if (quality < 0.1) {
                quality = 0.1;
            }
        }

        // Return the best compressed result, even if it did not meet the size target exactly
        return originalBytes;
    }

    public Image retriveImage(String email) {
        return userDetailRepository.findByEmailId(email).getImage();
    }

    @Override
    public String loginUser(String email, String password) {
        PersonalDetail personalDetail = userDetailRepository.findByEmailId(email);
        String token = "";
        if (personalDetail.getEmailId().equals(email) && personalDetail.getPassword().equals(password)) {
                token = jwtService.generateToken(email);
        } else {
            return "NA";
        }
        return token;
    }

    @Override
    public List<PersonalDetail> findAll() {
        return userDetailRepository.findAllByOrderByIdDesc();
    }


    @Override
    public List<PersonalDetail> findAllByCity(String city, String emailId) {
        PersonalDetail currentUser = userDetailRepository.findByEmailId(emailId);
        List<PersonalDetail> usersInCity = userDetailRepository.findByCareerDetail_CityOrderByIdDesc(city);

        List<String> friendEmails = new ArrayList<>();

        if (currentUser.getFriends() != null) {
            Object allFriendsObj = currentUser.getFriends().getAllFriends();

            if (allFriendsObj instanceof String) {
                String allFriendsStr = (String) allFriendsObj;
                if (allFriendsStr != null && !allFriendsStr.isEmpty()) {
                    friendEmails = Arrays.stream(allFriendsStr.split(","))
                            .map(String::trim)
                            .filter(email -> !email.isEmpty())
                            .collect(Collectors.toList());
                }
            } else if (allFriendsObj instanceof List) {
                // Suppress unchecked warning since we trust data model here
                @SuppressWarnings("unchecked")
                List<String> allFriendsList = (List<String>) allFriendsObj;
                friendEmails = allFriendsList != null ? allFriendsList : new ArrayList<>();
            }
        }

        // Filter out current user and their friends from city users
        List<String> finalFriendEmails = friendEmails;
        return usersInCity.stream()
                .filter(user -> !finalFriendEmails.contains(user.getEmailId())
                        && !user.getEmailId().equals(currentUser.getEmailId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonalDetail> findAllByCity_City(String city) {
        List<PersonalDetail> personalDetailList=new ArrayList<>();
        personalDetailList=userDetailRepository.findByCareerDetail_CityOrderByIdDesc(city);
        return personalDetailList;
    }


    @Override
    @Transactional
    public String passChange(String email, String password) {
        PersonalDetail getDetails = userDetailRepository.findByEmailId(email);
        String getEmail = getDetails.getEmailId();
        String result = "";
        if (getEmail == null || getEmail == "" || getEmail.equals("")) {
            result = "you are not registered with us !";
        } else {
            getDetails.setPassword(password);
            getDetails.setIdActivated(false);
            result="done";
//            OtpUtil otpUtil = new OtpUtil();
//            String otp = otpUtil.generateOtp();
//            try {
//                emailUtil.sendOtpEmail(getEmail, otp);
//                getDetails.setOtp(otp);
//                result = "OTP sent !";
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
//            return result;
        }
        return result;
    }

    @Override
    @Transactional
    public String sendRequest(String firstEmail, String secondPersonEmail) {
        if (firstEmail == null || secondPersonEmail == null) {
            return "Error: email cannot be null";
        }

        if (firstEmail.equals(secondPersonEmail)) {
            return "Error: Cannot send request to yourself";
        }

        // Get sender
        PersonalDetail sender = userDetailRepository.findByEmailId(firstEmail);
        if (sender == null) {
            return "Error: Sender not found";
        }

        FriendList senderFriends = sender.getFriends();
        if (senderFriends == null) {
            senderFriends = new FriendList();
            sender.setFriends(senderFriends);
        }

        List<String> sentRequests = senderFriends.getSentRequest();
        if (sentRequests == null) {
            sentRequests = new ArrayList<>();
            senderFriends.setSentRequest((ArrayList<String>) sentRequests);
        }

        if (!sentRequests.contains(secondPersonEmail)) {
            sentRequests.add(secondPersonEmail);
        }

        // Get receiver
        PersonalDetail receiver = userDetailRepository.findByEmailId(secondPersonEmail);
        if (receiver == null) {
            return "Error: Receiver not found";
        }

        FriendList receiverFriends = receiver.getFriends();
        if (receiverFriends == null) {
            receiverFriends = new FriendList();
            receiver.setFriends(receiverFriends);
        }

        List<String> receiveRequests = receiverFriends.getReceiveRequest();
        if (receiveRequests == null) {
            receiveRequests = new ArrayList<>();
            receiverFriends.setReceiveRequest((ArrayList<String>) receiveRequests);
        }

        if (!receiveRequests.contains(firstEmail)) {
            receiveRequests.add(firstEmail);
        }

        return "Operation is successful!";
    }



    @Override
    @Transactional
    public String addToFriend(String ownEmail,String friendEmail) {
        //for 1st email
        PersonalDetail personalDetailOwn=userDetailRepository.findByEmailId(ownEmail);
        FriendList friendList=personalDetailOwn.getFriends();
        if(friendList==null){
            friendList=new FriendList();
            personalDetailOwn.setFriends(friendList);
        }
        ArrayList<String> allFriendListReceived=friendList.getReceiveRequest();
        if(allFriendListReceived==null){
            allFriendListReceived=new ArrayList<>();
            friendList.setReceiveRequest(allFriendListReceived);
        }

        ArrayList<String> friendListAll=personalDetailOwn.getFriends().getAllFriends();
        if(friendListAll==null){
            friendListAll=new ArrayList<>();
            friendList.setAllFriends(friendListAll);
        }

        Iterator<String> itr=allFriendListReceived.iterator();
        while(itr.hasNext()){
            String email=itr.next();
            if(email.equals(friendEmail)){
                friendListAll.add(email);
                itr.remove();
            }
        }
        //for second email
        PersonalDetail personalDetailOwnSecond=userDetailRepository.findByEmailId(friendEmail);
        FriendList friendListSecond=personalDetailOwnSecond.getFriends();
        if(friendListSecond==null){
            friendListSecond=new FriendList();
            personalDetailOwnSecond.setFriends(friendListSecond);
        }
        ArrayList<String> allFriendListSentSecond=friendListSecond.getSentRequest();
        if(allFriendListSentSecond==null){
            allFriendListSentSecond=new ArrayList<>();
            friendListSecond.setSentRequest(allFriendListSentSecond);
        }

        ArrayList<String> friendListAllSecond=personalDetailOwnSecond.getFriends().getAllFriends();
        if(friendListAllSecond==null){
            friendListAllSecond=new ArrayList<>();
            friendListSecond.setAllFriends(friendListAllSecond);
        }

        Iterator<String> itrSecond=allFriendListSentSecond.iterator();
        while(itrSecond.hasNext()){
            String email=itrSecond.next();
            if(email.equals(ownEmail)){
                friendListAllSecond.add(ownEmail);
                itrSecond.remove();
            }
        }


        System.out.println(friendListAll);
        return "done";

    }

    @Override
    public PersonalDetail findMyProfile(String email) {
        PersonalDetail myProfile = new PersonalDetail();
        if(email!=null){
            PersonalDetail personalDetail=userDetailRepository.findByEmailId(email);
            myProfile= personalDetail;
        }else{
            System.out.println("please log in!");
        }
        return myProfile;
    }

    @Override
    public boolean idActivationCheck(String email) {
        PersonalDetail personalDetail=userDetailRepository.findByEmailId(email);
        if(personalDetail.isIdActivated()==true){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<PersonalDetail> findFriendSentList(String email) {
        List<PersonalDetail> ArrayList=new ArrayList<>();
        PersonalDetail personalDetail=userDetailRepository.findByEmailId(email);
        FriendList friendList=personalDetail.getFriends();
        if(friendList==null){
            friendList=new FriendList();
            personalDetail.setFriends(friendList);
        }
//         int friendListSize=personalDetail.getFriends().getReceiveRequest().size();
        List<String> emails=personalDetail.getFriends().getSentRequest();
        System.out.println(emails);
        for(String email1:emails){
            PersonalDetail personalDetail1= userDetailRepository.findByEmailId(email1);
            ArrayList.add(personalDetail1);
        }
        return ArrayList;
    }


    @Override
    public List<PersonalDetail> findFriendReceivedList(String email) {
         List<PersonalDetail> ArrayList=new ArrayList<>();
         PersonalDetail personalDetail=userDetailRepository.findByEmailId(email);
         FriendList friendList=personalDetail.getFriends();
         if(friendList==null){
             friendList=new FriendList();
             personalDetail.setFriends(friendList);
         }
//         int friendListSize=personalDetail.getFriends().getReceiveRequest().size();
         List<String> emails=personalDetail.getFriends().getReceiveRequest();
        System.out.println(emails);
         for(String email1:emails){
             PersonalDetail personalDetail1= userDetailRepository.findByEmailId(email1);
             ArrayList.add(personalDetail1);
        }
        return ArrayList;
    }
}
