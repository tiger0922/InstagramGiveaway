package edu.uci.cs.iggiveaway.instagramgiveaway.controller;

import edu.uci.cs.iggiveaway.instagramgiveaway.model.Post;
import edu.uci.cs.iggiveaway.instagramgiveaway.model.PostsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@RestController
public class PostsController {
    @Autowired
    DynamoDbEnhancedClient enhancedClient;

    @PostMapping("/posts")
    public void newPosts(@RequestBody Post post) {
        PostsDao.putItem(enhancedClient, post);
    }

    @GetMapping("/posts/{postid}")
    public Post getComment(@PathVariable String postid) {
        return PostsDao.getItem(enhancedClient, postid);
    }
}
