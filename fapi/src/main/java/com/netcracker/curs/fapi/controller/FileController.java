package com.netcracker.curs.fapi.controller;

import com.netcracker.curs.fapi.service.LikeService;
import com.netcracker.curs.fapi.models.Like;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {


    @RequestMapping(value = "/{imageName}", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response, @PathVariable String imageName) throws IOException {
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

        String filePath = directoryPathString + "\\"+ imageName + ".png";
        InputStream in =  Files.newInputStream(Paths.get(filePath));
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
