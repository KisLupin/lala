package com.appchat.pub.repository;

import com.appchat.pub.model.database.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {


    @Query(nativeQuery = true,
            value = "SELECT * FROM user_profile WHERE id not in :ids")
    List<UserProfile> findAllNotFriend(
            @Param(value = "ids") List<Integer> ids
    );

    @Query(nativeQuery = true,
            value = "SELECT * FROM user_profile " +
                    "WHERE username = :username LIMIT 1")
    UserProfile findOneUserLogin(
            @Param(value = "username") String username
    );

}
