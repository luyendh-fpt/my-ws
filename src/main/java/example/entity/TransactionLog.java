package example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class TransactionLog {

    @Id
    private long id;
    private long senderId;
    private long receiveId;
    private double balance;
    private int type; // 1. deposit || 2. withdraw || 3. transfer.
    private String content;
    private long createdAt;
    private long updatedAt;
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(long receiveId) {
        this.receiveId = receiveId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public static final class Builder {
        private long id;
        private long senderId;
        private long receiveId;
        private double balance;
        private int type; // 1. deposit || 2. withdraw || 3. transfer.
        private String content;
        private long createdAt;
        private long updatedAt;
        private int status;

        private Builder() {
            this.id = Calendar.getInstance().getTimeInMillis();
            this.createdAt = Calendar.getInstance().getTimeInMillis();
            this.updatedAt = Calendar.getInstance().getTimeInMillis();
        }

        public static Builder aTransactionLog() {
            return new Builder();
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withSenderId(long senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder withReceiveId(long receiveId) {
            this.receiveId = receiveId;
            return this;
        }

        public Builder withBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder withType(int type) {
            this.type = type;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withCreatedAt(long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public TransactionLog build() {
            TransactionLog transactionLog = new TransactionLog();
            transactionLog.setId(id);
            transactionLog.setSenderId(senderId);
            transactionLog.setReceiveId(receiveId);
            transactionLog.setBalance(balance);
            transactionLog.setType(type);
            transactionLog.setContent(content);
            transactionLog.setCreatedAt(createdAt);
            transactionLog.setUpdatedAt(updatedAt);
            transactionLog.setStatus(status);
            return transactionLog;
        }
    }
}
