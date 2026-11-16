import java.util.Comparator;

public class AppointmentTimeComparator implements Comparator<Appointment> {
    @Override
    public int compare(Appointment a, Appointment b) {
        int d = (a.date + " " + a.time).compareTo(b.date + " " + b.time);
        if (d != 0) return d;
        return a.appointmentId.compareTo(b.appointmentId);
    }
}
