package com.nj.weblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nj.weblog.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>  {

}
