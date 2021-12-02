package now.planit.Domain.Models;

public class Subtask {
    private String subtaskName;
    private String startDate;
    private String finishDate;
    private int cost;


    public Subtask(String subtaskName, String startDate, String finishDate, int cost) {
        this.subtaskName = subtaskName;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.cost = cost;
    }

    public String getSubtaskName() {
        return subtaskName;
    }

    public void setSubtaskName(String subtaskName) {
        this.subtaskName = subtaskName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}