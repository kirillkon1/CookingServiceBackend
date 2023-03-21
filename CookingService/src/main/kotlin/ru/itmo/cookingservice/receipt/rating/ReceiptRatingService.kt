package ru.itmo.cookingservice.receipt.rating

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.cookingservice.auth.user.UserRepository
import ru.itmo.cookingservice.exceptions.NotFoundException
import ru.itmo.cookingservice.receipt.ReceiptRepository

@Service
class ReceiptRatingService(
    private val ratingRepository: ReceiptRatingRepository,
    private val userRepository: UserRepository,
    private val receiptRepository: ReceiptRepository
) {

    @Transactional
    fun updateRating(dto: RatingDto) {

        val receiptOpt = receiptRepository.findById(dto.receiptId!!)

        if (receiptOpt.isEmpty) throw NotFoundException(message = "Рецепт #${dto.receiptId} не был найден")

        val currentUserOpt = userRepository.findUserByName(SecurityContextHolder.getContext().authentication.name)

        val ratingOpt = ratingRepository.getByUserAndReceipt(currentUserOpt.get(), receiptOpt.get())

        val rating: ReceiptRating = if (ratingOpt.isPresent) {
            ratingOpt.get()
        } else {
            ReceiptRating(user = currentUserOpt.get(), receipt = receiptOpt.get(), rating = dto.rating)
        }

        ratingRepository.save(rating)
        ratingRepository.updateRatingByReceiptAndRating(receiptOpt.get().id)
    }
}