package ru.itmo.cookingservice.services

import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.cookingservice.dto.receipt.ReceiptDto
import ru.itmo.cookingservice.models.*
import ru.itmo.cookingservice.repositories.ReceiptRepository
import java.util.Optional

@Service
class ReceiptService(
    private val receiptRepository: ReceiptRepository,
) {

    private val logger = KotlinLogging.logger {}

    fun getReceiptByPageAndSize(size: Int = 10, page: Int = 10): List<Receipt> {
        print("$size, $page")
        val pageable = PageRequest.of(size, page)
        return receiptRepository.findAll(pageable).content
    }

    fun getAllReceipts(): List<Receipt> {
        return receiptRepository.findAll()
    }

    @Transactional
    fun create(dto: ReceiptDto): Receipt {
        val receipt = Receipt()

        receipt.name = dto.name
        receipt.description = dto.description
        receipt.amountOfPortions = dto.amountOfPortions
        receipt.calories = dto.calories
        receipt.rating = 0
        receipt.createdDate = dto.createdDate

        val categories = dto.categories

        if (categories != null && categories.size > 0) {
            receipt.categories = mutableListOf()
            categories.forEach {
                receipt.categories?.add(
                    ReceiptCategory(name = it.name),
                )
            }
        }

        dto.compositions?.forEach {
            receipt.compositions?.add(
                Composition(
                    amount = it.amount,
                    ingredient = Ingredient(name = it.ingredient?.name),
                    metric = Metric(name = it.metric?.name),
                ),
            )
        }

        logger.debug { "Receipt ${dto.name}" }

        return receiptRepository.save(receipt)
    }

    fun update(id: Long) {
        val receipt: Optional<Receipt> = receiptRepository.findById(id)

        if (receipt.isEmpty) throw Exception()
    }

    fun deleteAll() {
        return receiptRepository.deleteAll()
    }
}
