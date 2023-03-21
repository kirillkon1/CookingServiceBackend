package ru.itmo.cookingservice.receipt.ingredient

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IngredientRepository : JpaRepository<Ingredient, Long> {

    @Query(
        value = "select * from ingredients where (select starts_with(name, ?1) order by name);",
        nativeQuery = true,
    )
    fun findByStartsWith(@Param("name") name: String): MutableList<Ingredient> // custom query

    @Query(
        value = "select * from ingredients where (ingredients.name ilike concat('%', :str, '%')) order by name",
        nativeQuery = true,
    )
    fun findByNameContains(@Param("str") str: String): MutableList<Ingredient> // custom query

    fun findByName(name: String): Optional<Ingredient>
}
