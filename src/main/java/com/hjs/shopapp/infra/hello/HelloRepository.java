package com.hjs.shopapp.infra.hello;

import com.hjs.shopapp.domain.model.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {

    Optional<Hello> findByUserId(String userId);
}
