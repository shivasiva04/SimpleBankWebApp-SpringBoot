package com.springTime.bankingWebApplication.repository;

import com.springTime.bankingWebApplication.model.Account;
import com.springTime.bankingWebApplication.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId "
            +"AND (:type IS NULL OR t.type = :type) "
            +"AND t.date >= :startDate "
            +"AND t.date <= :endDate")
    List<Transaction> findByAccountIdAndOptionalFilters(@Param("accountId") Long accountId,
                                                        @Param("type") String type, @Param("startDate")LocalDateTime startDate,
                                                        @Param("endDate") LocalDateTime endDate);




}
