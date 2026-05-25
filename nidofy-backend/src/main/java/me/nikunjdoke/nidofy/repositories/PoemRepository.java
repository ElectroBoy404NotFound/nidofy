package me.nikunjdoke.nidofy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nikunjdoke.nidofy.models.Poem;

@Repository
public interface PoemRepository extends JpaRepository<Poem, Long> {
    Optional<Poem> findById(long id);
}