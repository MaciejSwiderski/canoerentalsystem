package com.canoerent.repository;

import com.canoerent.model.Rent;
import com.canoerent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findByUserEmailOrderByRentId(String email);

    List<Rent> deleteRentByUserEmail(String email);

    List<Rent> findAllByOrderByRentId();

    Optional<Rent> deleteRentByRentId(long id);

    Rent findRentByRentId(long id);

    Rent findRentByUser_Email(String email);

    List<Rent> deleteRentByCanoeId(long id);

    List<Rent> findDistinctFirstByUserEmailOrderByRentIdDesc(String email);

//     @Query("select  sum(c.canoe_amount) from rent c inner join canoe t On c.rent.canoe_id=t.id where c.canoe_type= ?1")
//     int findSum (String type);

}
