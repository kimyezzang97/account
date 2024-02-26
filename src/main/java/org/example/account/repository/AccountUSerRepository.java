package org.example.account.repository;

import org.example.account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUSerRepository extends JpaRepository<AccountUser, Long> {


}
