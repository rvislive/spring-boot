package com.example.BookMyShow.repositories;

import com.example.BookMyShow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    Show findByIdEquals(Long Id);
    // We can also write our own function

    Show save(Show show);
}
