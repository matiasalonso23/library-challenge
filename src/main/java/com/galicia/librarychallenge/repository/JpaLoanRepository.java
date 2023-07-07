package com.galicia.librarychallenge.repository;

import com.galicia.librarychallenge.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLoanRepository extends JpaRepository<Loan, Long> {
}
