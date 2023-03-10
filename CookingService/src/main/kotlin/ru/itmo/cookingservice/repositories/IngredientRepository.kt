package ru.itmo.cookingservice.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.itmo.cookingservice.models.Ingredient
import java.util.Optional

@Repository
interface IngredientRepository : JpaRepository<Ingredient, Long> {

    @Query(
        value = "select * from ingredients where (select starts_with(name, ?1));",
        nativeQuery = true,
    )
    fun getStartsWith(@Param("str") str: String): List<Ingredient>

    fun findByName(name: String): Optional<Ingredient>
}
