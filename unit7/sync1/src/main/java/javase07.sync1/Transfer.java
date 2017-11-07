package javase07.sync1;

public class Transfer {

    private Account accountFrom;
    private Account accountTo;
    private int sum;

    public Transfer(Account accountFrom, Account accountTo, int sum) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return String.format("%d from %d to %d", sum, accountFrom.getId(), accountTo.getId());
    }
}
