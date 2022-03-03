package repository;

import domain.NotFoundEx;
import domain.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Repository repository = new Repository();
    private Ticket first = new Ticket(1, 7500, "VKO1", "KZN", 50);
    private Ticket second = new Ticket(2, 1700, "VKO2", "KGD", 150);
    private Ticket third = new Ticket(3, 2500, "VKO3", "STV", 250);
    private Ticket forth = new Ticket(4, 2200, "VKO4", "KRR", 350);
    private Ticket fifth = new Ticket(5, 3100, "LED", "KGD2", 450);


    @Test
    public void shouldSaveAllItem() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        Ticket[] expected = new Ticket[]{first, second, third, forth, fifth};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSaveOneItem() {
        repository.save(fifth);

        Ticket[] expected = new Ticket[]{fifth};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindAll() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        Ticket[] expected = new Ticket[]{first, second, third, forth, fifth};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void findById() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        repository.findById(2);


        Ticket expected = second;
        Ticket actual = repository.findById(2);

        assertEquals(expected, actual);
    }

    @Test
    public void findByIdIfNotFind() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        repository.findById(7);


        Ticket expected = null;
        Ticket actual = repository.findById(7);

        assertEquals(expected, actual);
    }

    @Test
    public void removeById() throws NotFoundEx {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        repository.removeById(1);


        Ticket[] expected = {second, third, forth, fifth};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void removeByIdIfNotFound() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        Assertions.assertThrows(NotFoundEx.class, () -> repository.removeById(6));

    }
}