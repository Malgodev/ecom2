package com.example.ecom2.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom2.models.Comment;
import com.example.ecom2.models.Item;

import java.util.List;


@Repository
public interface CommentDAO extends JpaRepository<Comment, String> {
    List<Comment> findByItem(Item item);
}
