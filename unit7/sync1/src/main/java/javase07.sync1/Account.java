package javase07.sync1;

public class Account {

    private final int id;

    public Account(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Account a = (Account) o;
        return id == a.getId();
    }

    @Override
    public int hashCode() {
        return id * 31;
    }
}
