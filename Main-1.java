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
public int getId() { return id; }
public void setId(int id) { this.id = id; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public int getAge() { return age; }
public void setAge(int age) { this.age = age; }
public abstract void displayInfo();
}
class Patient extends Person {
private String disease;
private String contactNumber;
public Patient(int id, String name, int age, String disease, String contactNumber) {
super(id, name, age);
this.disease = disease;
this.contactNumber = contactNumber;
}
public String getDisease() { return disease; }
public void setDisease(String disease) { this.disease = disease; }
public String getContactNumber() { return contactNumber; }
public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
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
public Doctor(int id, String name, int age, String specialization, String contactNumber) {
super(id, name, age);
this.specialization = specialization;
this.contactNumber = contactNumber;
}
public String getSpecialization() { return specialization; }
public void setSpecialization(String specialization) { this.specialization = specialization; }
public String getContactNumber() { return contactNumber; }
public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
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
public Appointment(int appointmentId, Patient patient, Doctor doctor, String date, String time) {
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
if (p.getId() == id) return p;
}
return null;
}
public void updatePatient(int id, String name, int age, String disease, String contact) {
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
}
public class Main {
public static void main(String[] args) {
HospitalManagement hm = new HospitalManagement();
Patient p1 = new Patient(1, "Ali", 25, "Fever", "03001234567");
Patient p2 = new Patient(2, "Ahmed", 30, "Cold", "03111234567");
hm.addPatient(p1);
hm.addPatient(p2);
System.out.println("\nAll Patients:");
hm.displayAllPatients();
hm.updatePatient(1, "Ali Khan", 26, "Flu", "03009999999");
System.out.println("\nSearch Patient ID 1:");
Patient found = hm.searchPatientById(1);
if (found != null) found.displayInfo();
hm.deletePatient(2);
System.out.println("\nAfter Deletion:");
hm.displayAllPatients();
Doctor d1 = new Doctor(101, "Dr. Sara", 40, "Cardiologist", "03211234567");
hm.addDoctor(d1);
System.out.println("\nDoctors:");
hm.displayAllDoctors();
Appointment a1 = new Appointment(1001, p1, d1, "2026-05-10", "10:00 AM");
hm.addAppointment(a1);
System.out.println("\nAppointments:");
hm.displayAllAppointments();
}
}