package controller;

public class TableModelPaye_rates {

    String _tax;
    String _rate;

    public String get_tax() {
        return _tax;
    }

    public void set_tax(String _tax) {
        this._tax = _tax;
    }

    public String get_rate() {
        return _rate;
    }

    public void set_rate(String _rate) {
        this._rate = _rate;
    }

    public TableModelPaye_rates(String _tax, String _rate) {
        this._tax = _tax;
        this._rate = _rate;
    }
}
