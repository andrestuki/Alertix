package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Model.Likes;
import com.scr.Alertix_DB.Services.LikesServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LikesController {
    @Autowired
    private LikesServices likesServices;

    @PostMapping("/Like")
    public ResponseEntity<?> darLike(@Valid @RequestBody Likes likes){
        try {
           Integer like= likesServices.darLike(likes);
            return new ResponseEntity<>(like, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Fallo a el dar like: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
