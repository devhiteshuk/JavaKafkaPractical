package com.dhl.virtusa.repository;

import com.dhl.virtusa.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {}