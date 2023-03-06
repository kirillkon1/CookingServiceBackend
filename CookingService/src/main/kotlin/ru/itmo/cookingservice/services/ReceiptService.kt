package ru.itmo.cookingservice.services

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.cookingservice.dto.ReceiptDto
import ru.itmo.cookingservice.models.*
import ru.itmo.cookingservice.repositories.ReceiptRepository
import java.util.Optional

@Service
class ReceiptService(
    private val receiptRepository: ReceiptRepository,
) {

    fun getReceiptByPageAndSize(size: Int = 10, page: Int = 1): List<Receipt> {
        print("$size, $page")
        val pageable = PageRequest.of(size, page)
        return receiptRepository.findAll(pageable).content
    }

    fun getAllReceipts(): List<Receipt> {
        return receiptRepository.findAll()
    }

    @Transactional
    fun create(receiptDto: ReceiptDto): Receipt {
        val receipt = Receipt(receiptDto)
        val categories = receiptDto.categories

        if (categories != null && categories.size > 0) {
            receipt.categories = mutableListOf()
            categories.forEach {
                receipt.categories?.add(
                    ReceiptCategory(name = it.name),
                )
            }
        }

        receiptDto.composition?.forEach {
            receipt.compositions?.add(
                Composition(
                    amount = it.amount,
                    ingredient = Ingredient(name = it.ingredient?.name),
                    metric = Metric(name = it.metric?.name),
                ),
            )
        }

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
