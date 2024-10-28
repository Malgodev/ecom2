package com.example.ecom2.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom2.models.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment, String> {

}
