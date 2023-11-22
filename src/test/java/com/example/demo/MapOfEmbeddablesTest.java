package com.example.demo;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.configuration.SpringDataConfiguration;
import com.example.demo.mapOfEmbeddables.Filename;
import com.example.demo.mapOfEmbeddables.Image;
import com.example.demo.mapOfEmbeddables.Item;
import com.example.demo.repositories.mapOfEmbeddables.ItemRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MapOfEmbeddablesTest {

	@Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadEntities() {

        Item item = new Item("Foo");

        item.putImage(new Filename("background.jpg"), new Image("Background", 640, 480));
        item.putImage(new Filename("foreground.jpg"), new Image("Foreground", 640, 480));
        item.putImage(new Filename("landscape.jpg"), new Image("Landscape", 640, 480));
        item.putImage(new Filename("portrait.jpg"), new Image("Portrait", 480, 640));

        itemRepository.save(item);

        Item item2 = itemRepository.findItemWithImages(item.getId());

        List<Item> items2 = itemRepository.findAll();
        Set<String> images = itemRepository.findImagesNative(item.getId());

        assertAll(
                () -> assertEquals(4, item2.getImages().size()),
                () -> assertEquals(1, items2.size()),
                () -> assertEquals(4, images.size())
        );

    }
}
