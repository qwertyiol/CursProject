package com.netcracker.curs.backend.repository;

import com.netcracker.curs.backend.entity.RoleUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserRepository extends CrudRepository<RoleUserEntity, Integer>{
}
