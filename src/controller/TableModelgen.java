package controller;

public class TableModelgen {

    String dates;
    String card;
    String arrival;
    String departure;
    String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TableModelgen(String dates, String card, String arrival, String departure, String time) {
        this.dates = dates;
        this.card = card;
        this.arrival = arrival;
        this.departure = departure;
        this.time = time;
    }
}