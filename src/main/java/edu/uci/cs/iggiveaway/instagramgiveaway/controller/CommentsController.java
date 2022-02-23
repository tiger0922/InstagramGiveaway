package edu.uci.cs.iggiveaway.instagramgiveaway.controller;

import edu.uci.cs.iggiveaway.instagramgiveaway.model.Comment;
import edu.uci.cs.iggiveaway.instagramgiveaway.model.CommentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import java.util.UUID;

@RestController
public class CommentsController {
    @Autowired
    DynamoDbEnhancedClient enhancedClient;

    @PostMapping("/comments")
    public void newComments(@RequestBody Comment comments) {
        UUID uuid = UUID.randomUUID();
        comments.setUuid(uuid.toString());
        CommentsDao.putItem(enhancedClient, comments);
    }

    @GetMapping("/comments/{uuid}")
    public Comment getComment(@PathVariable String uuid) {
        return CommentsDao.getItem(enhancedClient, uuid);
    }
}
