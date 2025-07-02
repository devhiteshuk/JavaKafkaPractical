package com.dhl.virtual.repository;

import com.dhl.virtual.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {}