package de.llggiessen.mke.repository;


import de.llggiessen.mke.schema.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;



public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Repository
    @RepositoryRestResource(exported = false)
    public interface CustomerRepository extends CrudRepository<Customer, Long> {

        @Modifying

        @Query(value = "SELECT * FROM customer WHERE customer.email LIKE %:email%", nativeQuery = true)
        Iterable<Customer> findAllByEmail(@Param("email") String email);

        @Query(value = "SELECT * FROM customer WHERE customer.first_name LIKE %:firstName%", nativeQuery = true)
        Iterable<Customer> findAllByFirstName(@Param("firstName") String firstName);

        @Query(value = "SELECT * FROM customer WHERE customer.last_name LIKE %:lastName%", nativeQuery = true)
        Iterable<Customer> findAllByLastName(@Param("lastName") String lastName);

        @Query(value = "SELECT * FROM customer WHERE customer.email LIKE %:email% AND customer.first_name LIKE %:firstName% AND customer.last_name LIKE %:lastName%", nativeQuery = true)
        Iterable<Customer> findAllByAttributes(@Param("email") String email, @Param("firstName") String firstName,
                                           @Param("lastName") String lastName);

        @Query(value = "SELECT * FROM customer WHERE customer.mobile_Phone LIKE %:mobile_Phone%", nativeQuery = true)
        Iterable<Customer> findAllByMobilePhone(@Param("mobilePhone") String mobilePhone);

        @Query(value = "SELECT * FROM customer WHERE customer.business_Phone LIKE %:business_Phone%", nativeQuery = true)
        Iterable<Customer> findAllByBusinessPhone(@Param("businessPhone") String businessPhone);
}