package manager;

import domain.Ticket;
import repository.Repository;

import java.util.Arrays;

public class Manager {
    private Repository repository;


    public Manager(Repository repository) {
        this.repository = repository;
    }

    public void add(Ticket item) {
        repository.save(item);
    }

    public Ticket[] getAll() {
        Ticket[] items = repository.findAll();
        Ticket[] result = new Ticket[items.length];
        for (int i = 0; i < result.length; i++) {
            int index = items.length - 1;
            result[i] = items[index];
        }
        return result;
    }

    public Ticket[] searchByFrom(String text) {
        Ticket[] result = new Ticket[0];

        for (Ticket product : repository.findAll()) {
            if (matchesFrom(product, text)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = product;
                result = tmp;
            }
            Arrays.sort(result);
        }

        return result;
    }


    public boolean matchesFrom(Ticket product, String search) {
        if (product.getFrom().contains(search)) {
            return true;
        } else {
            return false;
        }
        // или в одну строку:
        // return product.getFrom().contains(search);
    }

    public Ticket[] searchByTo(String text) {
        Ticket[] result = new Ticket[0];

        for (Ticket product : repository.findAll()) {
            if (matchesTo(product, text)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = product;
                result = tmp;
            }
            Arrays.sort(result);
        }

        return result;

    }


    public boolean matchesTo(Ticket product, String search) {
        if (product.getTo().contains(search)) {
            return true;
        } else {
            return false;
        }
        // или в одну строку:
        // return product.getTo().contains(search);
    }

}

