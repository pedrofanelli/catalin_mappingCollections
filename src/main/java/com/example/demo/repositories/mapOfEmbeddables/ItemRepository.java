package com.example.demo.repositories.mapOfEmbeddables;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.mapOfEmbeddables.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i inner join fetch i.images where i.id = :id")
    Item findItemWithImages(@Param("id") Long id);

    @Query(value = "SELECT TITLE FROM IMAGE WHERE ITEM_ID = ?1",
            nativeQuery = true)
    Set<String> findImagesNative(Long id);
}