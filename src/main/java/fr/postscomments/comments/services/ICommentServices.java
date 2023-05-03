package fr.postscomments.comments.services;

import fr.postscomments.comments.models.Comment;

import java.util.List;

public interface ICommentServices {
    List<Comment> findAllComment();

    Comment findCommentById(Long id);

    Comment addComment(Comment commentToAdd);

    Comment updateComment(Comment commentUpdated);

    void deleteComment(Long id);

}