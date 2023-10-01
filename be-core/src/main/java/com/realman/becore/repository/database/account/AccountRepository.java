package com.realman.becore.repository.database.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUsername(String username);

    Optional<AccountEntity> findByPhone(String phone);

}
