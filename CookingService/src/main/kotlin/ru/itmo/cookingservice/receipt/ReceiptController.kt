package ru.itmo.cookingservice.receipt

import jakarta.validation.constraints.Min
import org.springframework.web.bind.annotation.*
import ru.itmo.cookingservice.receipt.category.CategoryDto
import ru.itmo.cookingservice.receipt.receiptDto.requestDto.ReceiptDto
import ru.itmo.cookingservice.receipt.receiptDto.responseDto.ReceiptSimpleDto
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
    fun getBySearch(
        @RequestParam(required = false, name = "name") name: String,
        @Valid @Min(1) @RequestParam(required = false, name = "total") total: Int?
    ): List<Receipt> {
        return receiptService.getByStartsWith(name, total ?: 10)
    }

    @GetMapping("/search/categories/in")
    fun getByCategoriesIn(@RequestBody categoryDto: CategoryDto): List<Receipt> {
        return receiptService.getByCategoriesIn(categoryDto)
    }

    @GetMapping("{id}")
    fun getReceiptById(@PathVariable id: Long): Receipt {
        return receiptService.getReceiptById(id)
    }

    @GetMapping("{id}/simple")
    fun getSimpleReceiptById(@PathVariable id: Long): ReceiptSimpleDto {
        return receiptService.getSimpleById(id)
    }

    @GetMapping("/simple/search")
    fun getSimpleBySearch(
        @RequestParam(required = true, name = "name") name: String,
        @Valid @Min(1) @RequestParam(required = false, name = "total") total: Int?
    ): List<ReceiptSimpleDto> {
        return receiptService.getSimpleBySearch(name, total = total ?: 10)
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
