package com.netcracker.curs.fapi.controller;

import com.netcracker.curs.fapi.models.Post;
import com.netcracker.curs.fapi.models.User;
import com.netcracker.curs.fapi.service.PostService;
import com.netcracker.curs.fapi.steganography.SteganographyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    SteganographyImpl steganography=new SteganographyImpl();

    @Autowired
    private PostService postService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping
    public ResponseEntity<List<Post>> getAllPost() {
        return ResponseEntity.ok(postService.getAll(request));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> savePost(@RequestBody Post post /*todo server validation*/) {
        if (post != null) {
            return ResponseEntity.ok(postService.savePost(post, request));
        }
        return null;
    }

    @RequestMapping(value = "/{id}/image", method = RequestMethod.POST)
    public Object saveFile(@PathVariable String id, @RequestParam("file") MultipartFile file) throws IOException {
        int postId = Integer.valueOf(id);
        Post post= postService.getPostById(postId, request);
        Path directoryPath = Paths.get("/uploads");
        String directoryPathString = "";

        if(Files.notExists(directoryPath)) {
            directoryPathString = directoryPath.toAbsolutePath().toString();
            File directoryFile = new File(directoryPathString);
            directoryFile.mkdir();
        }
        else {
            directoryPathString = directoryPath.toAbsolutePath().toString();
        }

        String fileName = "image_" + id + ".png";

        String filePath = directoryPathString + "\\" + fileName;
        File dest = new File(filePath);
        file.transferTo(dest);

        String hiddenMessage = steganography.showMessage(dest);

        if(hiddenMessage != null)
        {
            String[] spliterArray = hiddenMessage.split("_");
            if(spliterArray.length != 3)
            {
                postService.deletePost(postId, request);
                dest.delete();
                return 1;
            }

            if(Integer.valueOf(spliterArray[1]) != post.getUserByIdUser().getId())
            {
                postService.deletePost(postId, request);
                dest.delete();
                return 2;
            }

        }

        String messageToHide = "Stegomessage_" + post.getUserByIdUser().getId() + "_" + post.getId();
        steganography.hideMessage(messageToHide.getBytes(), dest);
        post.setFilePath(fileName);
        postService.savePost(post, request);

        return 0;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable String id) {
        int postId = Integer.valueOf(id);

        Post post = postService.getPostById(postId, request);
        String fileName = post.getFilePath();

        Path directoryPath = Paths.get("/uploads");
        String directoryPathString = "";

        if(Files.notExists(directoryPath)) {
            directoryPathString = directoryPath.toAbsolutePath().toString();
            File directoryFile = new File(directoryPathString);
            directoryFile.mkdir();
        }
        else {
            directoryPathString = directoryPath.toAbsolutePath().toString();
        }

        String filePath = directoryPathString + "\\" + fileName;
        File dest = new File(filePath);
        postService.deletePost(postId, request);

        if(dest != null)
        {
            dest.delete();
        }

    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) throws InterruptedException {
        int postId = Integer.valueOf(id);
        return ResponseEntity.ok(postService.getPostById(postId, request));
    }
}