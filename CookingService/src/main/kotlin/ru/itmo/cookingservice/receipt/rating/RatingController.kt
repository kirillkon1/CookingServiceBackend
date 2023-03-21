package ru.itmo.cookingservice.receipt.rating

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/receipt/rating")
class RatingController(private val ratingService: ReceiptRatingService) {


    @PutMapping
    fun doRating(@RequestBody(required = true) ratingDto: RatingDto){
        ratingService.updateRating(ratingDto)
    }
}