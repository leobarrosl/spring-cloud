package com.example.rating_service;

import com.example.rating_service.models.Rating;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EnableDiscoveryClient
@RestController
@RequestMapping("/ratings")
@SpringBootApplication
public class RatingServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}

	private List<Rating> ratingList = Arrays.asList(
			new Rating(1L, 1L, 2),
			new Rating(2L, 1L, 3),
			new Rating(3L, 2L, 4),
			new Rating(4L, 2L, 5)
	);

	@GetMapping("")
	public List<Rating> findRatingsByBookId(@RequestParam Long bookId) {
		return bookId == null || bookId.equals(0L) ? Collections.EMPTY_LIST : ratingList.stream().filter(r -> r.getBookId().equals(bookId)).collect(Collectors.toList());
	}

	@GetMapping("/all")
	public List<Rating> findAllRatings() {
		return ratingList;
	}
}
