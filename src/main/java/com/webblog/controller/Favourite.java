package com.webblog.controller;

import com.webblog.POJO.ResponseHandler;
import com.webblog.model.Person;
import com.webblog.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/favourites")
public class Favourite {

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping("/{id}")
    public ResponseEntity<?> saveFavourite(@PathVariable Long postId, HttpSession httpSession){
        ResponseHandler res = new ResponseHandler();

        Person person = (Person) httpSession.getAttribute("person");

        if(person == null) {
            res.setMessage("User not login");
            res.setStatusCode(401);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }

        if(favouriteService.saveFavourite(person, postId)){
            res.setMessage("successfully added to favourites");
            res.setStatusCode(201);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }else {
            res.setMessage("Couldn't add to favourites");
            res.setStatusCode(400);

            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserFavourites(@PathVariable Long userId, HttpSession httpSession){

        ResponseHandler res = new ResponseHandler();

        Person person = (Person) httpSession.getAttribute("person");

        if(person == null) {
            res.setMessage("User not login");
            res.setStatusCode(401);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }

       return new ResponseEntity<>(favouriteService.getFavouritesByUser(person), HttpStatus.OK);
    }
}
