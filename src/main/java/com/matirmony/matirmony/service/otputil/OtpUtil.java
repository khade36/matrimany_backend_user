package com.matirmony.matirmony.service.otputil;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {
  public String generateOtp(){
      Random random=new Random();
      int randomNumber=random.nextInt(999999);
      String output=Integer.toString(randomNumber);
      while(output.length()<6){
          output=output+"0";
      }
          return output;
  }
}
