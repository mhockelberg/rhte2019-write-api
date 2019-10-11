package org.mycompany;

import org.springframework.data.repository.CrudRepository;

import org.mycompany.Customers;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CustomerRepository extends CrudRepository<Customers, Integer> {

}