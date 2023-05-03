package com.tem.TransportApp.repository;

import com.tem.TransportApp.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {



//    Optional<AppUser> findByTransportEmail(String email);

    Optional<AppUser> findByWebmail(String email);

    AppUser findAppUsersByWebmail(String email);

    AppUser findAppUserByWebmail(String email);

    Optional<AppUser> findByName(String name);

}
