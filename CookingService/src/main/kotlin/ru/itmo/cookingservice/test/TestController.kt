package ru.itmo.cookingservice.test

import org.springframework.web.bind.annotation.*
import ru.itmo.cookingservice.dto.receipt.ReceiptDto
import ru.itmo.cookingservice.models.Receipt
import ru.itmo.cookingservice.services.ReceiptService
import javax.validation.Valid

@RestController
@RequestMapping("/test")
class TestController(private val receiptService: ReceiptService) {

    @GetMapping("/1")
    fun doTestOne(): String {
        return "Hello, Test controller!"
    }

    @GetMapping()
    fun getByPageAndSize(@RequestBody dto: ReceiptDto): Receipt {
        println(dto)
        return receiptService.create(dto)
    }

    @GetMapping("/page/{page}")
    fun getByPage(@PathVariable page: Int): String {
        return "$page"
    }

    @PostMapping("1")
    fun doTestDTO(
        @RequestBody @Valid
        testDTO: TestDTO,
    ): TestDTO {
        println("dasd $testDTO")

        return testDTO
    }
}
