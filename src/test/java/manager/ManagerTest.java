package manager;

import domain.NotFoundEx;
import domain.Ticket;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.Repository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    Repository repository = new Repository();
    Manager manager = new Manager(repository);
    private Ticket first = new Ticket(1, 7500, "VKO1", "KZN", 50);
    private Ticket second = new Ticket(2, 1700, "VKO2", "KGD", 150);
    private Ticket third = new Ticket(3, 2500, "VKO3", "STV", 250);
    private Ticket forth = new Ticket(4, 2200, "VKO4", "KRR", 350);
    private Ticket fifth = new Ticket(5, 3100, "LED", "KGD2", 450);


    @Test
    void removeBiId() throws NotFoundEx {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        repository.removeById(2);

        Ticket[] expected = {forth, third, fifth, first};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);


    }

    @Test
    public void add() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);

        Ticket[] expected = {second, forth, third, first};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldRemoveByIdNotFound() {

        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        Assertions.assertThrows(NotFoundEx.class, () -> repository.removeById(66));

    }


    @Test
    public void searchByFrom() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);


        Ticket[] expected = {first};
        Ticket[] actual = manager.searchByFrom("VKO1");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void searchByFromIfMultipleValues() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);


        Ticket[] expected = {second, forth, third, first};
        Ticket[] actual = manager.searchByFrom("VKO");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void searchByFromIfNotFindValues() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);


        Ticket[] expected = {};
        Ticket[] actual = manager.searchByFrom("MRV");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void searchByTo() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);


        Ticket[] expected = {first};
        Ticket[] actual = manager.searchByTo("KZN");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void searchByToIfMultipleValues() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);


        Ticket[] expected = {second, fifth};
        Ticket[] actual = manager.searchByTo("KGD");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void searchByToIfNotFindValues() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);


        Ticket[] expected = {};
        Ticket[] actual = manager.searchByTo("MRV");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSortByPrice() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        Ticket[] expected = new Ticket[]{second, forth, third, fifth, first,};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

}