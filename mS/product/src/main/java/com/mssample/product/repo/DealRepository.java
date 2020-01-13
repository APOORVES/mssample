package com.mssample.product.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mssample.product.model.Deal;

@Repository
public interface DealRepository extends JpaRepository<Deal, String> {
	List<Deal> findByStartDateLessThanAndEndDateGreaterThanOrderByDiscountDesc(Timestamp currTSSD, Timestamp currTSED);

}
