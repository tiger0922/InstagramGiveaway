package edu.uci.cs.iggiveaway.instagramgiveaway.controller;

import edu.uci.cs.iggiveaway.instagramgiveaway.model.Followers;
import edu.uci.cs.iggiveaway.instagramgiveaway.model.FollowersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import java.util.List;

@RestController
public class FollowersController {

    @Autowired
    DynamoDbEnhancedClient enhancedClient;

    @GetMapping("/followers")
    public List<Followers> all() {
        return FollowersDao.scan(enhancedClient);
    }

    @GetMapping("/followers/{account}")
    public Followers getComment(@PathVariable String account) {
        return FollowersDao.getItem(enhancedClient, account);
    }

    @PostMapping("/followers")
    public void newFollowers(@RequestBody Followers followers) {
        FollowersDao.putItem(enhancedClient, followers);
    }
}
