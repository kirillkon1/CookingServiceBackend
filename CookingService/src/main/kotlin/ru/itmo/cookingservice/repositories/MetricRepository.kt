package ru.itmo.cookingservice.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itmo.cookingservice.models.Metric
import java.util.Optional

@Repository
interface MetricRepository : JpaRepository<Metric, Long> {

    fun findByName(name: String): Optional<Metric>
}
