package entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float amount;

    @Column(name = "time")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "recipient", referencedColumnName = "id")
    private Account recipient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }

    @PrePersist
    public void prePersist() {
        this.date = LocalDateTime.now();
    }

    private String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "№ " + id +
                ", amount=" + amount +
                ", localDateTime=" + formatDate(date) +
                ", firstAccount=" + sender.getAccount() +
                ", secondAccount=" + recipient.getAccount() +
                '}';
    }
}
