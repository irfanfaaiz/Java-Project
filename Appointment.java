import java.io.Serializable;

public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    String appointmentId;
    int patientId;
    String date;
    String time;
    int priority; // 1 Emergency, 2 High, 3 Normal
    String status;
    String notes;

    public Appointment(String appointmentId, int patientId, String date, String time, int priority) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.status = "Scheduled";
        this.notes = "";
    }

    public String priorityName() {
        if (priority == 1) return "Emergency";
        if (priority == 2) return "High";
        return "Normal";
    }

    @Override
    public String toString() {
        return appointmentId + "\t" + patientId + "\t" + date + "\t" + time + "\t" + priorityName() + "\t" + status;
    }
}
