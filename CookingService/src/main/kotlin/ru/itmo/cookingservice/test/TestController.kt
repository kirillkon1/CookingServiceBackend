package ru.itmo.cookingservice.test

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.itmo.cookingservice.auth.user.User
import ru.itmo.cookingservice.auth.user.UserService
import ru.itmo.cookingservice.receipt.receiptDto.ReceiptDto
import ru.itmo.cookingservice.receipt.Receipt
import ru.itmo.cookingservice.receipt.filter.ReceiptFilterRepository
import ru.itmo.cookingservice.security.jwt.JwtTokenUtil

@RestController
@RequestMapping("/test")
class TestController(
    private val userService: UserService,
    private val filterRepository: ReceiptFilterRepository
) {


    val jwtTokenUtil = JwtTokenUtil()

    @GetMapping("/1")
    fun doTestOne(): String {
        return jwtTokenUtil.generateToken("Hello, Test controller!")
    }

    @GetMapping()
    fun getByPageAndSize(@RequestBody dto: ReceiptDto): Iterable<User> {
        println(dto)
        return userService.getAll()
    }

    @GetMapping("/2")
    fun getByPage(): List<Receipt> {

        val names = listOf("лук", "картофель", "сливочное масло")



        return filterRepository.findByReceiptsByIngredientsIdsIn(names, names.size)
    }

    @GetMapping("3")
    fun doTestDTO(

    ): Any? {

        return SecurityContextHolder.getContext().authentication
    }


//    @GetMapping("/2")
//    fun getsdfkgd(@RequestBody dto: CategoryDto): {
//        val list = dto.categories.map { it.name }.toList()
//
//        return filterRepository.findByAllIngredientsIn(list)
//    }
}
