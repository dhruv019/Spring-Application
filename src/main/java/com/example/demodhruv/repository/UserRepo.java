package com.example.demodhruv.repository;

import com.example.demodhruv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepo extends JpaRepository<User,Integer>{
    public User findByNameAndPass(String name, String pass);
    public User deleteById(int id);

    @Transactional
    @Modifying
    @Query("update User u set u.pass=:tpa where u.id=:tid")
    public void updatePassword(@Param("tid")int tid, @Param("tpa")String tpa);
}
