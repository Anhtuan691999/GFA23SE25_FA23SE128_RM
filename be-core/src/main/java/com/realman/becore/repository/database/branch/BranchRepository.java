package com.realman.becore.repository.database.branch;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.realman.becore.dto.branch.BranchGroupByCityInfo;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Long> {

    @Query("""
            SELECT b FROM BranchEntity b
            WHERE (
                (:dateFrom IS NULL OR :dateTo IS NULL) OR (b.open BETWEEN :dateFrom AND :dateTo OR b.close BETWEEN :dateFrom AND :dateTo)
                    OR (:dateFrom BETWEEN b.open AND b.close OR :dateTo BETWEEN b.open AND b.close))
                AND (:#{#search.isEmpty()} = TRUE) OR b.branchName LIKE %:search% OR b.address LIKE %:search%
                """)
    List<BranchEntity> findAll(
            LocalTime dateFrom,
            LocalTime dateTo,
            String search);
    
    
    @Query("""
            SELECT 
                b.city AS city,
                COUNT(b) AS branch
            FROM BranchEntity b
            GROUP BY city
            """)
    List<BranchGroupByCityInfo> groupByCity();        


    @Query("""
            SELECT b FROM BranchEntity b WHERE b.city = :city
            """)
    Page<BranchEntity> findAll(String city, Pageable pageable);

    @Query("""
            SELECT b
            FROM BranchEntity b
            INNER JOIN BranchServiceEntity bs ON bs.branchId = b.branchId
            WHERE bs.serviceId = :serviceId
                    """)
    List<BranchEntity> findByServiceId(Long serviceId);
}
