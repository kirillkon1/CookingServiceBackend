package ru.itmo.cookingservice.receipt

import org.springframework.data.domain.PageRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.cookingservice.auth.user.User
import ru.itmo.cookingservice.auth.user.UserRepository
import ru.itmo.cookingservice.exceptions.CustomException
import ru.itmo.cookingservice.receipt.category.CategoryDto
import ru.itmo.cookingservice.receipt.receiptDto.requestDto.ReceiptDto
import ru.itmo.cookingservice.exceptions.NotFoundException
import ru.itmo.cookingservice.receipt.composition.Composition
import ru.itmo.cookingservice.receipt.ingredient.IngredientRepository
import ru.itmo.cookingservice.receipt.metric.MetricRepository
import ru.itmo.cookingservice.receipt.category.ReceiptCategoryRepository
import ru.itmo.cookingservice.receipt.receiptDto.responseDto.ReceiptSimpleDto
import ru.itmo.cookingservice.utils.Logger
import java.util.Optional

@Service
class ReceiptService(
    private val receiptRepository: ReceiptRepository,
    private val categoryRepository: ReceiptCategoryRepository,
    private val ingredientRepository: IngredientRepository,
    private val metricRepository: MetricRepository,
    private val userRepository: UserRepository,
) {


    fun getReceiptById(id: Long): Receipt{
        return receiptRepository.findById(id).get()
    }

    fun getReceiptByPageAndSize(size: Int = 10, page: Int = 10): List<Receipt> {
        val pageable = PageRequest.of(size, page)
        return receiptRepository.findAll(pageable).content
    }

    fun getAllReceipts(): List<Receipt> {
        return receiptRepository.findAll()
    }

    fun getByStartsWith(str: String, total: Int): List<Receipt> {
        val strLowercase = str.lowercase()
        val receiptList = receiptRepository.getStartsWith(strLowercase).take(total)

        if (receiptList.isNotEmpty()) {
            return receiptList
        }

        return receiptRepository.getByNameContains(strLowercase).take(total)
    }

    fun getByCategoriesIn(dto: CategoryDto): List<Receipt> {
        val namesList: List<String?> = dto.categories?.map { it.name }!!.toList()

        return receiptRepository.getReceiptsByCategoriesNamesIn(namesList)
    }

    fun getSimpleById(id: Long): ReceiptSimpleDto {
        return receiptRepository.findById(id).get().convertToSimple()
    }

    fun getSimpleBySearch(name: String, total: Int): List<ReceiptSimpleDto> {

        val simpleList = getByStartsWith(name, total).map {
            ReceiptSimpleDto(
                id = it.id,
                name = it.name!!,
                rating = it.rating,
                imageUrl = it.imageUrl,
                categories = it.categories!!
            )
        }.toList()

        return simpleList
    }

    @Transactional
    fun create(dto: ReceiptDto): Receipt {
        val receipt = Receipt()

        receipt.name = dto.name!!.trim().lowercase()
        receipt.description = dto.description?.trim()
        receipt.amountOfPortions = dto.amountOfPortions ?: 1
        receipt.calories = dto.calories ?: 0
        receipt.rating = 0

        val currentUser: User =
            userRepository.findUserByName(SecurityContextHolder.getContext().authentication.name!!).get()

        receipt.user = currentUser

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
                    description = it.description,
                ),
            )
        }

        Logger.info("[CREATE] [User ${currentUser.name}#${currentUser.id}] - [Receipt ${dto.name}]")

        return receiptRepository.save(receipt)
    }

    fun getByFilter() {
        TODO("Доделать фильтр")
        return
    }

    @Transactional
    fun update(id: Long, dto: ReceiptDto) {
        val receiptOpt: Optional<Receipt> = receiptRepository.findById(id)

        if (receiptOpt.isEmpty) throw NotFoundException("Рецепт №$id не был найден!")

        val currentUser = userRepository.findUserByName(SecurityContextHolder.getContext().authentication.name!!).get()

        if (receiptOpt.get().user!! == currentUser) {
            throw CustomException(message = "Данный рецепт принадлежит другому пользователю!")
        }


        val receipt: Receipt = receiptOpt.get()

        receipt.name = dto.name?.trim()?.lowercase() ?: receipt.name
        receipt.description = dto.description?.trim() ?: receipt.description
        receipt.amountOfPortions = dto.amountOfPortions ?: receipt.amountOfPortions
        receipt.calories = dto.calories ?: receipt.calories

        if (dto.categories != null && dto.categories.size > 0) {
            receipt.categories = mutableListOf()
            dto.categories.forEach {
                val categoryOpt = categoryRepository.findByName(it.name!!.lowercase())

                if (categoryOpt.isEmpty) {
                    throw NotFoundException(message = "Категория '${it.name}' не была найдена")
                }

                receipt.categories?.add(categoryOpt.get())
            }
        }

        if (dto.compositions != null && dto.compositions.size > 0) {
            receipt.compositions = mutableListOf()
            dto.compositions.forEach {
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
        }

        receiptRepository.save(receipt)

        Logger.info("[UPDATE] [User ${currentUser.name}#${currentUser.id}] - [Receipt ${receipt.name}#${receipt.id}]")
    }

    fun deleteAll() {
        return receiptRepository.deleteAll()
    }

}
