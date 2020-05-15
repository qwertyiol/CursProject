package com.netcracker.curs.fapi.controller;

import com.netcracker.curs.fapi.models.Post;
import com.netcracker.curs.fapi.models.User;
import com.netcracker.curs.fapi.service.PostService;
import com.netcracker.curs.fapi.steganography.SteganographyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    SteganographyImpl steganography=new SteganographyImpl();

    @Autowired
    private PostService postService;

    @RequestMapping
    public ResponseEntity<List<Post>> getAllPost() {
        return ResponseEntity.ok(postService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> savePost(@RequestBody Post post /*todo server validation*/) {
        if (post != null) {
            return ResponseEntity.ok(postService.savePost(post));
        }
        return null;
    }

    @RequestMapping(value = "/{id}/image", method = RequestMethod.POST)
    public ResponseEntity<Post> saveFile(@PathVariable String id, @RequestParam("file") MultipartFile file) throws IOException {
        int postId = Integer.valueOf(id);

        Post post= postService.getPostById(postId);


        String fileExtension = (file.getOriginalFilename()).split("\\.")[1];
        String fileName = "image_" + id + "." + fileExtension;
        String filePath = "fapi/image/" + fileName;

        File dest = new File(filePath);
        file.transferTo(dest);


//        byte[] userIdByte=steganography.intToBytes(Integer.valueOf(post.getIdUser()), )
//        byte[] message=steganography.showMessage(dest);
//        byte[] userIdByte=BigInteger.valueOf(post.getIdUser()).toByteArray();

//        if (Arrays.equals(message, userIdByte) ||
//                Arrays.equals(message, null))
//        {
//            steganography.hideMessage(BigInteger.valueOf(post.getIdUser()).toByteArray(), dest);
            //byte[] message=steganography.showMessage()
//        }

        post.setFilePath(fileName);

        Post savedPost = postService.savePost(post);

        return ResponseEntity.ok(savedPost);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable String id) {
        postService.deletePost(Integer.valueOf(id));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) throws InterruptedException {
        int postId = Integer.valueOf(id);
        return ResponseEntity.ok(postService.getPostById(postId));
    }
}