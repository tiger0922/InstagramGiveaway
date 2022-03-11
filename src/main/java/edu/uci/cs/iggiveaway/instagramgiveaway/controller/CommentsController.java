package edu.uci.cs.iggiveaway.instagramgiveaway.controller;

import edu.uci.cs.iggiveaway.instagramgiveaway.model.Comment;
import edu.uci.cs.iggiveaway.instagramgiveaway.model.CommentsDao;
import edu.uci.cs.iggiveaway.instagramgiveaway.model.Post;
import edu.uci.cs.iggiveaway.instagramgiveaway.model.PostsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
public class CommentsController {
    @Autowired
    DynamoDbEnhancedClient enhancedClient;

    @PostMapping("/comments")
    public void newComments(@RequestBody Comment comments) {
        CommentsDao.putItem(enhancedClient, comments);
    }

    @GetMapping("/comments")
    public Comment getComment(@RequestParam String account_id, @RequestParam Integer timestamp) {
        return CommentsDao.getItem(enhancedClient, account_id, timestamp);
    }

    @GetMapping("/comments/post-id/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable String postId){
        return CommentsDao.queryPostId(enhancedClient, postId);
    }

    @GetMapping("/comments/post-id/{postId}/pick")
    public Comment pickCommentsByPostId(@PathVariable String postId){
        List<Comment> result = CommentsDao.queryPostId(enhancedClient, postId);
        Random rand = new Random();
        int x = rand.nextInt(result.size());
        return result.get(x);
    }
}
