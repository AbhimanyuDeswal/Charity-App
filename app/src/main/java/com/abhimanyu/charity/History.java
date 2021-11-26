package com.abhimanyu.charity;

import java.util.Map;

public class History {
    String name, donate, amount;
    Map<String, Long> timestamp;
    public History() {
    }


    public History(String name, String donate, String amount, Map<String, Long> timestamp) {
        this.name = name;
        this.donate = donate;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDonate() {
        return donate;
    }

    public void setDonate(String donate) {
        this.donate = donate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Map<String, Long> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Map<String, Long> timestamp) {
        this.timestamp = timestamp;
    }
}
