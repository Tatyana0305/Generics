package manager;

import domain.NotFoundEx;
import domain.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    Repository repository = new Repository();
    Manager manager = new Manager(repository);
    private Ticket first = new Ticket(1, 1500, "VKO", "KZN", 50);
    private Ticket second = new Ticket(2, 1700, "VKO", "KGD", 50);
    private Ticket third = new Ticket(3, 2500, "VKO", "STV", 50);
    private Ticket forth = new Ticket(4, 2200, "VKO", "KRR", 50);
    private Ticket fifth = new Ticket(5, 3100, "VKO", "PKC", 50);


    @Test
    void removeBiId() throws NotFoundEx {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        repository.removeById(2);

        Ticket[] expected = {first, third, forth, fifth};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);


    }

    @Test
    public void add() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);

        Ticket[] expected = {first, second, third, forth};
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
    public void searchByName() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);


        Ticket[] expected = {second};
        Ticket[] actual = manager.searchBy("KGD");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void searchByNameIfMultipleValues() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);


        Ticket[] expected = {first, second, third};
        Ticket[] actual = manager.searchBy("alp");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void searchByNameIfNotFindValues() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);


        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("delta");

        assertArrayEquals(expected, actual);

    }
}