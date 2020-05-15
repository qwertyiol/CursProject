package com.netcracker.curs.backend.repository;

import com.netcracker.curs.backend.entity.LikePostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<LikePostEntity, Integer> {
}
