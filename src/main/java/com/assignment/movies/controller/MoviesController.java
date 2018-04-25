package com.assignment.movies.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class MoviesController {

	private final Logger logger = LoggerFactory.getLogger(MoviesController.class);
	
	List<String> movies = new ArrayList<String>();
	
	@PostMapping("/movies")
	public ResponseEntity<?> addMovie(@RequestParam("name") String movie) {
		logger.debug("add movie started");
		if (movie.isEmpty()) {
			return new ResponseEntity<String>("Provide movie name", HttpStatus.BAD_REQUEST);
		}
		movies.add(movie);
		logger.debug("add movie completed");
		return new ResponseEntity<String>("Movie added successfully",
				HttpStatus.OK);
	}
	
	@DeleteMapping("/movies")
	public ResponseEntity<?> deleteMovie(@RequestParam("name") String movie) {
		logger.debug("delete movie started");
		if (movie.isEmpty()) {
			return new ResponseEntity<String>("Provide movie name", HttpStatus.BAD_REQUEST);
		}
		movies.remove(movie);
		logger.debug("delete movie completed");
		return new ResponseEntity<String>("Movie deleted successfully",
				HttpStatus.OK);
	}
	
	@GetMapping("/movies")
	public ResponseEntity<?> getMovies() {
		logger.debug("get movies started");
		String moviesList = String.join(",", movies);
		logger.debug("get movies completed");
		return new ResponseEntity<String>(moviesList,
				HttpStatus.OK);
	}


}
