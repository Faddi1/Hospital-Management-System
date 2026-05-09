import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

abstract class Person {
    private int id;
    private String name;
    private int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void displayInfo();
}

class Patient extends Person {

    private String disease;
    private String contactNumber;

    public Patient(int id, String name, int age,
                   String disease, String contactNumber) {

        super(id, name, age);
        this.disease = disease;
        this.contactNumber = contactNumber;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public void displayInfo() {

        System.out.println("Patient ID: " + getId() +
                "\nName: " + getName() +
                "\nAge: " + getAge() +
                "\nDisease: " + disease +
                "\nContact: " + contactNumber);
    }
}

class Doctor extends Person {

    private String specialization;
    private String contactNumber;

    public Doctor(int id, String name, int age,
                  String specialization, String contactNumber) {

        super(id, name, age);
        this.specialization = specialization;
        this.contactNumber = contactNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public void displayInfo() {

        System.out.println("Doctor ID: " + getId() +
                "\nName: " + getName() +
                "\nAge: " + getAge() +
                "\nSpecialization: " + specialization +
                "\nContact: " + contactNumber);
    }
}

class Appointment {

    private int appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;

    public Appointment(int appointmentId, Patient patient,
                       Doctor doctor, String date, String time) {

        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public void displayInfo() {

        System.out.println("Appointment ID: " + appointmentId +
                "\nPatient: " + patient.getName() +
                "\nDoctor: " + doctor.getName() +
                "\nDate: " + date +
                "\nTime: " + time);
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}

class HospitalManagement {

    private ArrayList<Patient> patientList = new ArrayList<>();
    private ArrayList<Doctor> doctorList = new ArrayList<>();
    private ArrayList<Appointment> appointmentList = new ArrayList<>();

    public void addPatient(Patient patient) {

        if (searchPatientById(patient.getId()) == null) {
            patientList.add(patient);
        } else {
            System.out.println("Patient with this ID already exists.");
        }
    }

    public Patient searchPatientById(int id) {

        for (Patient p : patientList) {

            if (p.getId() == id)
                return p;
        }

        return null;
    }

    public void updatePatient(int id, String name, int age,
                              String disease, String contact) {

        Patient p = searchPatientById(id);

        if (p != null) {

            p.setName(name);
            p.setAge(age);
            p.setDisease(disease);
            p.setContactNumber(contact);

            System.out.println("Patient updated successfully.");

        } else {

            System.out.println("Patient not found.");
        }
    }

    public void deletePatient(int id) {

        Patient p = searchPatientById(id);

        if (p != null) {

            patientList.remove(p);
            System.out.println("Patient deleted successfully.");

        } else {

            System.out.println("Patient not found.");
        }
    }

    public void displayAllPatients() {

        if (patientList.isEmpty()) {

            System.out.println("No patients found.");

        } else {

            for (Patient p : patientList) {

                p.displayInfo();
            }
        }
    }

    public void addDoctor(Doctor doctor) {

        doctorList.add(doctor);
    }

    public void displayAllDoctors() {

        for (Doctor d : doctorList) {

            d.displayInfo();
        }
    }

    public void addAppointment(Appointment appointment) {

        appointmentList.add(appointment);
    }

    public void displayAllAppointments() {

        for (Appointment a : appointmentList) {

            a.displayInfo();
        }
    }

    public ArrayList<Patient> getPatients() {
        return patientList;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctorList;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointmentList;
    }
}

public class Main extends JFrame {

    HospitalManagement hm = new HospitalManagement();

    JTextField pidField, pnameField, pageField,
            diseaseField, pcontactField;

    JTextField didField, dnameField, dageField,
            specField, dcontactField;

    JTextField aidField, apidField, adidField,
            dateField, timeField;

    DefaultTableModel patientModel;
    DefaultTableModel doctorModel;
    DefaultTableModel appointmentModel;

    public Main() {

        setTitle("Hospital Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // TITLE
        JLabel title = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.BLUE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Patients", patientPanel());
        tabs.add("Doctors", doctorPanel());
        tabs.add("Appointments", appointmentPanel());

        add(tabs);

        setVisible(true);
    }

    public JPanel patientPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));

        pidField = new JTextField();
        pnameField = new JTextField();
        pageField = new JTextField();
        diseaseField = new JTextField();
        pcontactField = new JTextField();

        form.add(new JLabel("Patient ID"));
        form.add(pidField);

        form.add(new JLabel("Name"));
        form.add(pnameField);

        form.add(new JLabel("Age"));
        form.add(pageField);

        form.add(new JLabel("Disease"));
        form.add(diseaseField);

        form.add(new JLabel("Contact"));
        form.add(pcontactField);

        JButton addBtn = new JButton("Add Patient");
        JButton updateBtn = new JButton("Update Patient");
        JButton deleteBtn = new JButton("Delete Patient");

        form.add(addBtn);
        form.add(updateBtn);

        panel.add(form, BorderLayout.NORTH);

        patientModel = new DefaultTableModel();

        patientModel.setColumnIdentifiers(
                new String[]{"ID", "Name", "Age", "Disease", "Contact"});

        JTable table = new JTable(patientModel);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(deleteBtn, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {

            try {

                int id = Integer.parseInt(pidField.getText());

                // SAME ID CHECK
                for (Patient p : hm.getPatients()) {

                    if (p.getId() == id) {

                        throw new Exception("Patient ID already exists!");
                    }
                }

                for (Doctor d : hm.getDoctors()) {

                    if (d.getId() == id) {

                        throw new Exception("This ID already used by Doctor!");
                    }
                }

                String name = pnameField.getText();
                int age = Integer.parseInt(pageField.getText());
                String disease = diseaseField.getText();
                String contact = pcontactField.getText();

                Patient p = new Patient(id, name, age,
                        disease, contact);

                hm.addPatient(p);

                patientModel.addRow(new Object[]{
                        id, name, age, disease, contact
                });

                JOptionPane.showMessageDialog(null,
                        "Patient Added Successfully");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {

            try {

                int id = Integer.parseInt(pidField.getText());

                hm.updatePatient(
                        id,
                        pnameField.getText(),
                        Integer.parseInt(pageField.getText()),
                        diseaseField.getText(),
                        pcontactField.getText()
                );

                refreshPatients();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        "Invalid Input");
            }
        });

        deleteBtn.addActionListener(e -> {

            try {

                int id = Integer.parseInt(pidField.getText());

                hm.deletePatient(id);

                refreshPatients();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        "Invalid Input");
            }
        });

        return panel;
    }

    public JPanel doctorPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));

        didField = new JTextField();
        dnameField = new JTextField();
        dageField = new JTextField();
        specField = new JTextField();
        dcontactField = new JTextField();

        form.add(new JLabel("Doctor ID"));
        form.add(didField);

        form.add(new JLabel("Name"));
        form.add(dnameField);

        form.add(new JLabel("Age"));
        form.add(dageField);

        form.add(new JLabel("Specialization"));
        form.add(specField);

        form.add(new JLabel("Contact"));
        form.add(dcontactField);

        JButton addBtn = new JButton("Add Doctor");

        form.add(addBtn);

        panel.add(form, BorderLayout.NORTH);

        doctorModel = new DefaultTableModel();

        doctorModel.setColumnIdentifiers(
                new String[]{"ID", "Name", "Age",
                        "Specialization", "Contact"});

        JTable table = new JTable(doctorModel);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        addBtn.addActionListener(e -> {

            try {

                int id = Integer.parseInt(didField.getText());

                // SAME ID CHECK
                for (Doctor d : hm.getDoctors()) {

                    if (d.getId() == id) {

                        throw new Exception("Doctor ID already exists!");
                    }
                }

                for (Patient p : hm.getPatients()) {

                    if (p.getId() == id) {

                        throw new Exception("This ID already used by Patient!");
                    }
                }

                String name = dnameField.getText();
                int age = Integer.parseInt(dageField.getText());
                String spec = specField.getText();
                String contact = dcontactField.getText();

                Doctor d = new Doctor(id, name, age,
                        spec, contact);

                hm.addDoctor(d);

                doctorModel.addRow(new Object[]{
                        id, name, age, spec, contact
                });

                JOptionPane.showMessageDialog(null,
                        "Doctor Added Successfully");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        ex.getMessage());
            }
        });

        return panel;
    }

    public JPanel appointmentPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));

        aidField = new JTextField();
        apidField = new JTextField();
        adidField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();

        form.add(new JLabel("Appointment ID"));
        form.add(aidField);

        form.add(new JLabel("Patient ID"));
        form.add(apidField);

        form.add(new JLabel("Doctor ID"));
        form.add(adidField);

        form.add(new JLabel("Date"));
        form.add(dateField);

        form.add(new JLabel("Time"));
        form.add(timeField);

        JButton addBtn = new JButton("Add Appointment");

        form.add(addBtn);

        panel.add(form, BorderLayout.NORTH);

        appointmentModel = new DefaultTableModel();

        appointmentModel.setColumnIdentifiers(
                new String[]{"App ID", "Patient",
                        "Doctor", "Date", "Time"});

        JTable table = new JTable(appointmentModel);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        addBtn.addActionListener(e -> {

            try {

                int aid = Integer.parseInt(aidField.getText());
                int pid = Integer.parseInt(apidField.getText());
                int did = Integer.parseInt(adidField.getText());

                Patient patient = hm.searchPatientById(pid);

                Doctor doctor = null;

                for (Doctor d : hm.getDoctors()) {

                    if (d.getId() == did) {

                        doctor = d;
                    }
                }

                if (patient == null || doctor == null) {

                    throw new Exception(
                            "Patient or Doctor not found!");
                }

                Appointment a = new Appointment(
                        aid,
                        patient,
                        doctor,
                        dateField.getText(),
                        timeField.getText()
                );

                hm.addAppointment(a);

                appointmentModel.addRow(new Object[]{
                        aid,
                        patient.getName(),
                        doctor.getName(),
                        dateField.getText(),
                        timeField.getText()
                });

                JOptionPane.showMessageDialog(null,
                        "Appointment Added Successfully");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        ex.getMessage());
            }
        });

        return panel;
    }

    public void refreshPatients() {

        patientModel.setRowCount(0);

        for (Patient p : hm.getPatients()) {

            patientModel.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getAge(),
                    p.getDisease(),
                    p.getContactNumber()
            });
        }
    }

    public static void main(String[] args) {

        new Main();
    }
}