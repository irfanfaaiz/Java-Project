import java.util.Comparator;

public class PriorityComparator implements Comparator<Appointment> {
    @Override
    public int compare(Appointment a, Appointment b) {
        if (a.priority != b.priority)
            return a.priority - b.priority;
        int d = (a.date + " " + a.time).compareTo(b.date + " " + b.time);
        if (d != 0) return d;
        return a.appointmentId.compareTo(b.appointmentId);
    }
}
