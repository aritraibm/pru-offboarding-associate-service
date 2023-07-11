package com.pru.offboarding.associate.service.repo;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pru.offboarding.associate.service.entity.Associate;

@Repository
public interface AssociateRepo extends MongoRepository<Associate, Long> {

	Associate findByAssociateId(Long associateId);
	
//	@Query(value = "select * from tbl_associate_details where ibm_id like %:ibmId%", nativeQuery = true)
//	public List<Associate> getExcelDetailsByAssociateId(@Param(value = "ibmId") String ibmId);
//
//	@Query(value = "select * from tbl_associate_details where associate_name like %:associateName%", nativeQuery = true)
//	public List<Associate> searchAssociateDetailsSQL(@Param(value = "associateName") String associateName);
//	
//	@Query(value = "select * from tbl_associate_details where as_on_date = :date", nativeQuery = true)
//	public List<Associate> searchAssociateDetailsByDateSQL(Date date);
	
    List<Associate> findByIbmIdLike(String ibmId);
	
	List<Associate> findByFirstNameLike(String firstName);
	
	Optional<Associate> findByAssociateId(String associateId);
	
	List<Associate> findByAsOnDate(Date asOnDate);

	boolean existsByIbmId(String ibmId);

	Optional<Associate> findByIbmId(String ibmId);

}
