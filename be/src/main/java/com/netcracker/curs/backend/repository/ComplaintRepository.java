package com.netcracker.curs.backend.repository;

import com.netcracker.curs.backend.entity.ComplaintEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends CrudRepository<ComplaintEntity, Integer>  {
    //ComplaintEntity[] FindComplaintByPostId(Integer idPost);
}
