import java.io.Serializable;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    int patientId;
    String name;
    int age;
    String contact;

    public Patient(int patientId, String name, int age, String contact) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return patientId + "\t" + name + "\t" + age + "\t" + contact;
    }
}
