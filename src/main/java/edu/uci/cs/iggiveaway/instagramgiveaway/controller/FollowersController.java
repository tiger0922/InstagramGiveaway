package edu.uci.cs.iggiveaway.instagramgiveaway.controller;

import edu.uci.cs.iggiveaway.instagramgiveaway.model.Comment;
import edu.uci.cs.iggiveaway.instagramgiveaway.model.CommentsDao;
import edu.uci.cs.iggiveaway.instagramgiveaway.model.Followers;
import edu.uci.cs.iggiveaway.instagramgiveaway.model.FollowersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import java.util.List;
import java.util.Random;

@RestController
public class FollowersController {

    @Autowired
    DynamoDbEnhancedClient enhancedClient;

    @GetMapping("/followers")
    public List<Followers> all() {
        return FollowersDao.scan(enhancedClient);
    }

    @GetMapping("/followers/{accountId}")
    public Followers getFollowers(@PathVariable String accountId) {
        return FollowersDao.getItem(enhancedClient, accountId);
    }

    @GetMapping("/followers/{accountId}/pick")
    public String pickAccountsByFollowers(@PathVariable String accountId){
        List<String> result = FollowersDao.getItem(enhancedClient, accountId).getFollowers();
        Random rand = new Random();
        int x = rand.nextInt(result.size());
        return result.get(x);
    }

    @PostMapping("/followers")
    public void newFollowers(@RequestBody Followers followers) {
    FollowersDao.putItem(enhancedClient, followers);
    }
}
