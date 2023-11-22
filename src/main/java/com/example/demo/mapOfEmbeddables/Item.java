package com.example.demo.mapOfEmbeddables;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.Constants;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item {

	@Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    private Map<Filename, Image> images = new HashMap<>();

    public Item(String name) {
        this.name = name;
    }

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setImages(Map<Filename, Image> images) {
        this.images = images;
    }

    public Map<Filename, Image> getImages() {
        return Collections.unmodifiableMap(images);
    }

    public void putImage(Filename key, Image value) {
        images.put(key, value);
    }
}
