package com.hjs.shopapp.infra.hello;

import com.hjs.shopapp.domain.model.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {

    Optional<Hello> findByUserId(String userId);

    List<Hello> findByNameLike(String name);

    List<Hello> findByUserIdLike(String userId);

    @Query("select h from Hello h where h.name like :name and h.userId like :userId")
    List<Hello> findAllBy(@Param("name") String name, @Param("userId") String userId);
}
