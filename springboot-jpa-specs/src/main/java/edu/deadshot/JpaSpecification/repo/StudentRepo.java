package edu.deadshot.JpaSpecification.repo;

import edu.deadshot.JpaSpecification.entity.ComplexCondition;
import edu.deadshot.JpaSpecification.entity.ComplexRequest;
import edu.deadshot.JpaSpecification.entity.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    default List<Student> doComplexSearch(ComplexRequest request) {
        if(CollectionUtils.isEmpty(request.getConditions())) {
            return new ArrayList<>();
        }

        Specification<Student> spec = ((root, query, criteriaBuilder) -> {
            String rule = request.getRule();
            List<ComplexCondition> conditions = request.getConditions();
            Predicate predicate = null;

            for (ComplexCondition a : conditions) {
                switch (a.getOperator()) {
                    case "equal":
                        predicate = (Optional.ofNullable(predicate).isPresent()) ?
                                setRule(criteriaBuilder, predicate, rule, criteriaBuilder.equal(root.get(a.getKey()), a.getValue()))
                                : criteriaBuilder.equal(root.get(a.getKey()), a.getValue());
                        break;
                    case "contains":
                        predicate = (Optional.ofNullable(predicate).isPresent()) ?
                                setRule(criteriaBuilder, predicate, rule, criteriaBuilder.like(root.get(a.getKey()), "%" + a.getValue() + "%"))
                                : criteriaBuilder.like(root.get(a.getKey()), "%" + a.getValue() + "%");
                        break;
                    case "starts":
                        predicate = (Optional.ofNullable(predicate).isPresent()) ?
                                setRule(criteriaBuilder, predicate, rule, criteriaBuilder.like(root.get(a.getKey()), a.getValue() + "%"))
                                : criteriaBuilder.like(root.get(a.getKey()), a.getValue() + "%");
                        break;
                    case "ends":
                        predicate = (Optional.ofNullable(predicate).isPresent()) ?
                                setRule(criteriaBuilder, predicate, rule, criteriaBuilder.like(root.get(a.getKey()), "%" + a.getValue()))
                                : criteriaBuilder.like(root.get(a.getKey()), "%" + a.getValue());

                        predicate = setRule(criteriaBuilder, predicate, rule, criteriaBuilder.like(root.get(a.getKey()), "%" + a.getValue()));
                        break;
                    case "lesser":
                        predicate = (Optional.ofNullable(predicate).isPresent()) ?
                                setRule(criteriaBuilder, predicate, rule, criteriaBuilder.lessThan(root.get(a.getKey()), a.getValue()))
                                : criteriaBuilder.lessThan(root.get(a.getKey()), a.getValue());
                        break;
                    case "greater":
                        predicate = (Optional.ofNullable(predicate).isPresent()) ?
                                setRule(criteriaBuilder, predicate, rule, criteriaBuilder.greaterThan(root.get(a.getKey()), a.getValue()))
                                : criteriaBuilder.greaterThan(root.get(a.getKey()), a.getValue());
                        break;
                }
            }
            return predicate;
        });
        return findAll(spec);
    }

    default Predicate setRule(CriteriaBuilder criteriaBuilder, Predicate predicate, String rule, Predicate condition) {
        if (rule.equals("AND")) {
            return criteriaBuilder.and(predicate, condition);
        } else {
            return criteriaBuilder.or(predicate, condition);
        }
    }

}
