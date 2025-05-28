package com.matirmony.matirmony.repository;

import com.matirmony.matirmony.userDetails.PersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailRepository extends JpaRepository<PersonalDetail,Integer> {
  public PersonalDetail findByEmailId(String email);
  List<PersonalDetail> findAllByOrderByIdDesc();
  List<PersonalDetail> findByCareerDetail_CityOrderByIdDesc(String city);
}
