package ru.itmo.cookingservice.receipt

import org.springframework.web.bind.annotation.*
import ru.itmo.cookingservice.receipt.category.CategoryDto
import ru.itmo.cookingservice.receipt.receiptDto.ReceiptDto
import javax.validation.Valid

@RestController
@RequestMapping("api/receipts")
class ReceiptController(private val receiptService: ReceiptService) {

    @GetMapping()
    fun getByPageAndSize(
        @RequestParam(required = false, name = "size") size: Int?,
        @RequestParam(required = false, name = "page") page: Int?,
    ): List<Receipt> {
        if (size == null && page == null) return receiptService.getAllReceipts()
        return receiptService.getReceiptByPageAndSize(size = size ?: 10, page = page ?: 1)
    }

    @GetMapping("/search")
    fun getByStartsWith(@RequestParam(required = false, name = "name") name: String): List<Receipt> {
        return receiptService.getByStartsWith(name)
    }

    @GetMapping("/search/categories/in")
    fun getByCategoriesIn(@RequestBody categoryDto: CategoryDto): List<Receipt> {
        return receiptService.getByCategoriesIn(categoryDto)
    }

    @PostMapping()
    fun create(@RequestBody @Valid receiptDto: ReceiptDto): Receipt {
        return receiptService.create(receiptDto)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody @Valid receiptDto: ReceiptDto, @PathVariable id: Long) {
        receiptService.update(id, receiptDto)
    }

//    @DeleteMapping()
//    fun deleteAll() {
//        return receiptService.deleteAll()
//    }
}
