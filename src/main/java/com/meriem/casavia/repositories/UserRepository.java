package com.meriem.casavia.repositories;

import com.meriem.casavia.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends  JpaRepository<User,Long>{
    User findUserByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByNomContaining(String nom);
    List<User> findByPrenomContaining(String prenom);
    List<User> findByNomContainingAndPrenomContaining(String nom, String prenom);
}
