package controller;

public class TableModel {

   String date;
   String card_id;
   String staff_id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    String arrival_time;

    public TableModel(String date, String card_id, String staff_id, String arrival_time, String departure_time) {
        this.date = date;
        this.card_id = card_id;
        this.staff_id = staff_id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
    }

    String departure_time;

}
