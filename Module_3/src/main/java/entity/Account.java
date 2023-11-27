package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Column(name = "account_number", unique = true, nullable = false)
    private int account;

    @Column(nullable = false)
    private float balance;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "sender")
    private Set<Transaction> sender;

    @OneToMany(mappedBy = "recipient")
    private Set<Transaction> recipient;
    public Account() {
        super();
    }
    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Transaction> getSender() {
        return sender;
    }

    public void setSender(Set<Transaction> sender) {
        this.sender = sender;
    }

    public Set<Transaction> getRecipient() {
        return recipient;
    }

    public void setRecipient(Set<Transaction> recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account=" + account +
                ", balance=" + balance +
                '}';
    }
}
