//package ru.itmo.cookingservice.services.Filter;
//
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import org.springframework.data.jpa.domain.Specification;
//import ru.itmo.cookingservice.receipt.filter.FilterDto;
//
//public class SearchSpecificationJava<T> implements Specification<T> {
//    @Override
//    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);
//
//        for (FilterDto dto: this.request)
//        return null;
//    }
//}
