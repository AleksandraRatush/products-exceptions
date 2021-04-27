package ru.netology.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.domain.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
  private ProductRepository repository = new ProductRepository();
  private Book coreJava = new Book();
  private TShirt tshirt = new TShirt();

  @BeforeEach
  public void clear(){
     repository = new ProductRepository();
  }

  @Test
  public void shouldSaveOneItem() {
    repository.save(coreJava);
    Product[] expected = new Product[]{coreJava};
    Product[] actual = repository.findAll();
    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldRemoveOne() {
    repository.save(coreJava);
    repository.save(tshirt);
    repository.removeById(1);
    Product[] actual = repository.findAll();
    Product[] expected = new Product[]{tshirt};
    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldFailOnRemoveNotFound() {
    repository.save(coreJava);
    repository.save(tshirt);
    try {
      repository.removeById(3);
    } catch (Throwable ex) {
       assertTrue(ex instanceof NotFoundException);
       assertEquals(new NotFoundException("Element with id: 3 not found").getMessage()
               , ex.getMessage());
    }
  }



}
