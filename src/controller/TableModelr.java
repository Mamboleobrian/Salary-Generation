package controller;

public class TableModelr {

    String job_juri;
    String Pay_rates;
    String overtime;

    public String getJob_juri() {
        return job_juri;
    }

    public void setJob_juri(String job_juri) {
        this.job_juri = job_juri;
    }

    public String getPay_rates() {
        return Pay_rates;
    }

    public void setPay_rates(String pay_rates) {
        Pay_rates = pay_rates;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public TableModelr(String job_juri, String pay_rates, String overtime) {
        this.job_juri = job_juri;
        Pay_rates = pay_rates;
        this.overtime = overtime;
    }
}
