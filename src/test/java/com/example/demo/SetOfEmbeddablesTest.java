package com.example.demo;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.configuration.SpringDataConfiguration;
import com.example.demo.repositories.setOfEmbeddables.ItemRepository;
import com.example.demo.setOfEmbeddables.Image;
import com.example.demo.setOfEmbeddables.Item;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class SetOfEmbeddablesTest {

	@Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadEntities() {

        Item item = new Item("Foo");

        item.addImage(new Image("background.jpg", 640, 480));
        item.addImage(new Image("foreground.jpg", 640, 480));
        item.addImage(new Image("landscape.jpg", 640, 480));
        item.addImage(new Image("portrait.jpg", 480, 640));

        itemRepository.save(item);

        Item item2 = itemRepository.findItemWithImages(item.getId());

        List<Item> items2 = itemRepository.findAll();
        Set<String> images = itemRepository.findImagesNative(item.getId());

        assertAll(
                () -> assertEquals(4, item2.getImages().size()),
                () -> assertEquals(1, items2.size()),
                () -> assertEquals(4, images.size())
        );
        
        Item item16 = new Item("Cocaina");
        
        item16.addImage(new Image("background.jpg", 640, 480));
        
        itemRepository.save(item16);

    }
}
