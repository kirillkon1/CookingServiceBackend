package ru.itmo.cookingservice.receipt.metric

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MetricRepository : JpaRepository<Metric, Long> {

    fun findByName(name: String): Optional<Metric>
}
