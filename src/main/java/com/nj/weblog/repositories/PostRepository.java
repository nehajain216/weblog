package com.nj.weblog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nj.weblog.entities.Post;
import com.nj.weblog.models.Archival;

public interface PostRepository extends JpaRepository<Post, Integer>  {

	@Query(value="SELECT new com.nj.weblog.models.Archival(COUNT(p.id), MONTH(p.createdOn), YEAR(p.createdOn)) FROM Post p GROUP BY YEAR(p.createdOn), MONTH(p.createdOn)")
	public List<Archival> findByCreatedOn();
	
	@Query("select p from Post p where EXTRACT (month FROM p.createdOn) =?1 and EXTRACT (year FROM p.createdOn)=?2")
	public List<Post> findByArchival(int month, int year);
 }
