package concurrent.thread_local;

import java.util.Random;

/**
 * User class used for ThreadLocal.
 */
public class User {
    private int id;

    public User(int id) {
        this.id = id;
    }

    public User() {
        Random random = new Random();
        this.id = random.nextInt(1000);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
