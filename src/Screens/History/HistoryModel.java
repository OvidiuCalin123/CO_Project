package Screens.History;

public class HistoryModel {

    public String selectedOption;
    public String score;
    public int rowNumber;
    public double run_time;
    public String hdd_ssd;
    public String test_time;

    public HistoryModel(int rowNumber, String score, double run_time, String hdd_ssd, String test_time) {

        this.rowNumber = rowNumber;
        this.score = score;
        this.run_time = run_time;
        this.hdd_ssd = hdd_ssd;
        this.test_time = test_time;
    }

    public HistoryModel(int rowNumber, String selectedOption, String score, double run_time, String hdd_ssd, String test_time) {

        this.selectedOption = selectedOption;
        this.rowNumber = rowNumber;
        this.score = score;
        this.run_time = run_time;
        this.hdd_ssd = hdd_ssd;
        this.test_time = test_time;
    }

    public int getRowNumber() {

        return this.rowNumber;
    }

    public void setRowNumber(int rowNumber) {

        this.rowNumber = rowNumber;
    }

    public String getSelectedOption() {
        return this.selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public double getRun_time() {
        return run_time;
    }

    public void setRun_time(double run_time) {
        this.run_time = run_time;
    }

    public String getHdd_ssd() {
        return hdd_ssd;
    }

    public void setHdd_ssd(String hdd_ssd) {
        this.hdd_ssd = hdd_ssd;
    }

    public String getTest_time() {
        return test_time;
    }

    public void setTest_time(String test_time) {
        this.test_time = test_time;
    }
}