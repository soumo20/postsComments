package fr.postscomments.comments.controllers;

import fr.postscomments.comments.dto.CommentDto;
import fr.postscomments.comments.dto.CommentUpdateDto;
import fr.postscomments.comments.models.Comment;
import fr.postscomments.comments.services.CommentServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentServices commentServices;

    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsOfSpecPost(@PathVariable() Long id) {
        return new ResponseEntity<>(commentServices.findAllCommentsOfOnePost(id), HttpStatus.FOUND);
    }

    @GetMapping("comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable() Long id) {
        return new ResponseEntity<>(commentServices.findCommentById(id), HttpStatus.OK);
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<Comment> addCommentToPost(@Valid @RequestBody CommentDto comment, @PathVariable Long id) {
        return new ResponseEntity<>(commentServices.addComment(comment, id), HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@Valid @RequestBody CommentUpdateDto commentUpdateDto) {
        return new ResponseEntity<>(commentServices.updateComment(commentUpdateDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable() Long id) {
        commentServices.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
