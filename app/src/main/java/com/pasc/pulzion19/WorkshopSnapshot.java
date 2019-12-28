package com.pasc.pulzion19;

import java.io.Serializable;

public class WorkshopSnapshot implements Serializable {

    String contact1;
    String contact2;
    private int imageView;
    private String event_name, event_name_quote, event_name_description, event_rules_description, event_team_distribution, event_fees, event_contact;

    WorkshopSnapshot(String event_name, String event_name_quote, String event_name_description, String event_fees, int imageView, String contact1, String contact2) {
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.event_name = event_name;
        this.event_name_quote = event_name_quote;
        this.event_name_description = event_name_description;
        this.event_fees = event_fees;
        this.imageView = imageView;
    }

    int getImageView() {
        return imageView;
    }

    void setImageView(int imageView) {
        this.imageView = imageView;
    }

    String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    String getEvent_name_quote() {
        return event_name_quote;
    }

    public void setEvent_name_quote(String event_name_quote) {
        this.event_name_quote = event_name_quote;
    }

    String getEvent_name_description() {
        return event_name_description;
    }

    public void setEvent_name_description(String event_name_description) {
        this.event_name_description = event_name_description;
    }

    String getEvent_rules_description() {
        return event_rules_description;
    }

    public void setEvent_rules_description(String event_rules_description) {
        this.event_rules_description = event_rules_description;
    }

    String getEvent_team_distribution() {
        return event_team_distribution;
    }

    public void setEvent_team_distribution(String event_team_distribution) {
        this.event_team_distribution = event_team_distribution;
    }

    String getEvent_fees() {
        return event_fees;
    }

    public void setEvent_fees(String event_fees) {
        this.event_fees = event_fees;
    }

    String getEvent_contact() {
        return event_contact;
    }

    public void setEvent_contact(String event_contact) {
        this.event_contact = event_contact;
    }


}
