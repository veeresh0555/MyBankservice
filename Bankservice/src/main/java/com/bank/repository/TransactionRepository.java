package com.bank.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.model.TransactionHistory;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionHistory, Long> {

	@Query("select transact from TransactionHistory transact where transact.userid =:userid ")
	List<TransactionHistory> findstmtById(@Param("userid") long userid, Pageable top10);
}

