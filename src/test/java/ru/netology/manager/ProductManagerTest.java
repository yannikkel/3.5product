package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book first = new Book(1, "Таинственный остров", 500, "Жюль Верн");
    private Book second = new Book(2, "Властелин колец", 600, "Дж. Р. Р. Толкиен");
    private Book third = new Book(3, "Властелин колец: Две крепости", 700, "Дж. Р. Р. Толкиен");
    private Smartphone smartphone1 = new Smartphone(1, "Xiaomi", 7500, "Китай");
    private Smartphone smartphone2 = new Smartphone(2, "Apple", 20000, "Тайвань");

    @BeforeEach
    public void setUp() {
        manager = new ProductManager(repository);
        manager.productAdd(first);
        manager.productAdd(second);
        manager.productAdd(smartphone1);
        manager.productAdd(smartphone2);
    }

    @Test
    void shouldSearchBookByNameIfExists() {
        String text = "Таинственный остров";

        Product[] expected = new Product[]{first};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotSearchBookByNameIfNotExists() {
        String text = "Одиссея капитана Блада";

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookByAuthorIfExists() {
        String text = "Дж. Р. Р. Толкиен";

        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotSearchBookByAuthorIfNotExists() {
        String text = "Рафаэль Сабатини";

        Book[] expected = new Book[]{};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneByNameIfExists() {
        String text = "Apple";

        Product[] expected = new Product[]{smartphone2};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotSearchSmartphoneByNameIfNotExists() {
        String text = "Lenovo";

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneByManufacturerIfExists() {
        String text = "Тайвань";

        Product[] expected = new Product[]{smartphone2};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotSearchSmartphoneByManufacturerIfExists() {
        String text = "Россия";

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchProductsWithSameAuthor() {
        manager.productAdd(third);
        String text = "Дж. Р. Р. Толкиен";

        Product[] expected = new Product[]{second, third};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }
}
