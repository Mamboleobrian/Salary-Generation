package controller;

public class TableModelAllowance {

    String juries;
    String home;
    String transport;

    public String getJuries() {
        return juries;
    }

    public void setJuries(String juries) {
        this.juries = juries;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public TableModelAllowance(String juries, String home, String transport) {
        this.juries = juries;
        this.home = home;
        this.transport = transport;
    }
}
