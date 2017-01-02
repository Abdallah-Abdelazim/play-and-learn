package user;

import java.io.Serializable;

public class User implements Serializable , IUser {
    private Account account = new Account(); // The User contains Account.

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "account=" + account +
                '}';
    }
}
