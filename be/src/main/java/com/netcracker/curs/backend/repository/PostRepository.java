package com.netcracker.curs.backend.repository;

import com.netcracker.curs.backend.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    //PostEntity[] FindPostByUserId(Integer userByIdUser);
}
