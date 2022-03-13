package repository;

import domain.NotFoundEx;
import domain.Ticket;

import java.util.Arrays;

public class Repository {
    private Ticket[] items = new Ticket[0];

    public void save(Ticket item) {
        int length = items.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Ticket[] findAll() {
        Arrays.sort(items);
        return items;
    }

    public Ticket findById(int id) {
        for (Ticket item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) throws NotFoundEx {
        if (findById(id) == null) {
            throw new NotFoundEx("Element with id: " + id + " not found");
        }

        int length = items.length - 1;

        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket item : items) {
            if (findById(id) != null) ;

            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;

    }

}

