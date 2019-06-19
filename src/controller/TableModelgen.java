package controller;

public class TableModelgen {

    String dates;
    String card;
    String staffs;
    String arrival;
    String departure;

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getStaffs() {
        return staffs;
    }

    public void setStaffs(String staffs) {
        this.staffs = staffs;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public TableModelgen(String dates, String card, String staffs, String arrival, String departure) {
        this.dates = dates;
        this.card = card;
        this.staffs = staffs;
        this.arrival = arrival;
        this.departure = departure;
    }
}