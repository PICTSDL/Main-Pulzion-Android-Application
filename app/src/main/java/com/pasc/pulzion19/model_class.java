package com.pasc.pulzion19;

public class model_class {
    private String name;
    private String contact;
    private String id;
    private String amt_paid;

    public model_class(String name, String contact, String id, String amt_paid) {
        this.name = name;
        this.contact = contact;
        this.id = id;
        this.amt_paid = amt_paid;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getId() {
        return id;
    }

    public String getAmt_paid() {
        return amt_paid;
    }
}
