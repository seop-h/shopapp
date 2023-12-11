package com.hjs.shopapp.infra.member;

import com.hjs.shopapp.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    List<Member> findByNameLike(String name);

    List<Member> findByUserIdLike(String userId);

    @Query("select m from Member m where m.name like :name and m.userId like :userId")
    List<Member> findAllBy(@Param("name") String name, @Param("userId") String userId);
}
