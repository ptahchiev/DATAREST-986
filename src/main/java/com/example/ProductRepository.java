package com.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Repository
@RepositoryRestResource
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

}
