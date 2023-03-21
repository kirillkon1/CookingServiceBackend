package ru.itmo.cookingservice.receipt.filter

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.cookingservice.receipt.Receipt

@Service
class FilterService {


    @Transactional
    fun doFilter(filterDto: FilterDto): List<Receipt>{
        val receiptsList = mutableListOf<Receipt>()




        return receiptsList
    }



}