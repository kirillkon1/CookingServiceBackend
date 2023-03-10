package ru.itmo.cookingservice.services

import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.cookingservice.dto.receipt.ReceiptDto
import ru.itmo.cookingservice.exceptions.receiptExceptions.NotFoundException
import ru.itmo.cookingservice.models.*
import ru.itmo.cookingservice.repositories.IngredientRepository
import ru.itmo.cookingservice.repositories.MetricRepository
import ru.itmo.cookingservice.repositories.ReceiptCategoryRepository
import ru.itmo.cookingservice.repositories.ReceiptRepository
import java.util.Optional

@Service
class ReceiptService(
    private val receiptRepository: ReceiptRepository,
    private val categoryRepository: ReceiptCategoryRepository,
    private val ingredientRepository: IngredientRepository,
    private val metricRepository: MetricRepository,
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

    fun getByStartsWith(str: String): List<Receipt> {
        return receiptRepository.getStartsWith(str)
    }

    @Transactional
    fun create(dto: ReceiptDto): Receipt {
        val receipt = Receipt()

        receipt.name = dto.name!!.trim()
        receipt.description = dto.description?.trim()
        receipt.amountOfPortions = dto.amountOfPortions
        receipt.calories = dto.calories
        receipt.rating = 0
        receipt.createdDate = dto.createdDate

        val categories = dto.categories

        if (categories != null && categories.size > 0) {
            receipt.categories = mutableListOf()
            categories.forEach {
                val categoryOpt = categoryRepository.findByName(it.name!!.lowercase())

                if (categoryOpt.isEmpty) {
                    throw NotFoundException(message = "Категория '${it.name}' не была найдена")
                }

                receipt.categories?.add(categoryOpt.get())
            }
        }

        receipt.compositions = mutableListOf()
        dto.compositions?.forEach {
            val ingredientOpt = ingredientRepository.findByName(it.ingredient!!.name!!.lowercase().trim())

            if (ingredientOpt.isEmpty) {
                throw NotFoundException(message = "Ингредиент '${it.ingredient.name}' не был найден!")
            }

            val metricOpt = metricRepository.findByName(it.metric!!.name!!.lowercase().trim())

            if (metricOpt.isEmpty) {
                throw NotFoundException(message = "Метрика '${it.metric.name}' не была найдена!")
            }

            receipt.compositions!!.add(
                Composition(
                    amount = it.amount,
                    ingredient = ingredientOpt.get(),
                    metric = metricOpt.get(),
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
