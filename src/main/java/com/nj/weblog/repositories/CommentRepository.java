package com.nj.weblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nj.weblog.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
