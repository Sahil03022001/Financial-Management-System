package com.financial.transaction.system.repository;

import com.financial.transaction.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

      User findByMobNo(String mobNo);

      User findByEmail(String email);

      User findById(int id);

      User findByUserId(String userId);

      void deleteByUserId(String userId);
}
