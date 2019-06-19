package controller;

public class TableModeld {

   String card;
   String names;
   String id_staff;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getId_staff() {
        return id_staff;
    }

    public void setId_staff(String id_staff) {
        this.id_staff = id_staff;
    }

    public String getJurisdictions() {
        return Jurisdictions;
    }

    public void setJurisdictions(String jurisdictions) {
        Jurisdictions = jurisdictions;
    }

    String Jurisdictions;


    public TableModeld(String card, String names, String id_staff, String jurisdictions) {
        this.card = card;
        this.names = names;
        this.id_staff = id_staff;
        Jurisdictions = jurisdictions;
    }
}
