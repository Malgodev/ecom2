package com.example.ecom2.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecom2.daos.CommentDAO;
import com.example.ecom2.models.Comment;
import com.example.ecom2.models.Item;

@Service
public class CommentService {
    private final CommentDAO commentRepository;

    @Autowired
    public CommentService(CommentDAO commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllCommentsByItem(Item item){
        return commentRepository.findByItem(item);
    }
}
