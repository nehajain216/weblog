package com.nj.weblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nj.weblog.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

	Tag findByName(String tagName);
}
