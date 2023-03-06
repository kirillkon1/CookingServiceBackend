package ru.itmo.cookingservice.controllers

import org.springframework.web.bind.annotation.*
import ru.itmo.cookingservice.dto.ReceiptDto
import ru.itmo.cookingservice.models.Receipt
import ru.itmo.cookingservice.services.ReceiptService
import javax.validation.Valid

@RestController
@RequestMapping("receipts")
class ReceiptController(private val receiptService: ReceiptService) {

    @GetMapping()
    fun getByPageAndSize(@RequestParam(required = false, name = "size") size: Int?, @RequestParam(required = false, name = "page") page: Int?, ): List<Receipt> {
        if (size == null && page == null) return receiptService.getAllReceipts()
        return receiptService.getReceiptByPageAndSize(size = size ?: 10, page = page ?: 10)
    }

    @GetMapping("page/{page}")
    fun getByPage(@RequestParam page: Int): List<Receipt> {
        return receiptService.getReceiptByPageAndSize(page = page)
    }

    @PostMapping("/")
    fun create( @Valid @RequestBody receiptDto: ReceiptDto): Receipt {
        return receiptService.create(receiptDto)
    }

//    @GetMapping()
//    fun getByAll() {
//
//    }

    @DeleteMapping()
    fun deleteAll() {
        return receiptService.deleteAll()
    }
}
