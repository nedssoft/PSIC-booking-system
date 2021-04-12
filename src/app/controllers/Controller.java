/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.AppointmentModel;
import app.models.ConsultationModel;
import app.models.ExpertiseModel;
import app.models.PatientModel;
import app.models.TreatmentModel;
import app.models.PhysicianModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nedsoft
 */
public class Controller {

    private static final int EXIT = 0;
    private static final int MAIN_MENU = 9;
    static Scanner input = new Scanner(System.in);

    public Controller() {

        PatientModel.seed();
        PhysicianModel.seed();
        TreatmentModel.seed();
        AppointmentModel.seed();
        ConsultationModel.seed();
        print(":::Welcome to PSIC booking system:::\nTo let us know how we can help you\n");
        init();

    }

    private static void init() {

        print("Choose an option below\n0 -- To exit\n1 -- To look up Physicians by name\n2 -- To look up physicians' areas of expertise\n3 -- To change appointment\n4 -- To view all appointments\n5 -- To view a patient's appointments\n6 -- To create a patient\n7 -- To book vsistor's consultation");

        try {
            int cmd = input.nextInt();
            switch (cmd) {

                case 9:
                    init();
                    break;
                case 1:
                    searchPhysicianByName();
                    break;
                case 2:
                    searchAreaOfExpertise();
                    break;
                case 3:
                    changeAppointmentEntry();
                    break;
                case 4:
                    listAllAppointments();
                    break;
                case 5:
                    listPatientAppointment();
                    break;
                case 6:
                    newPatientEntry();
                    break;
                case 7:
                    bookConsultation();
                    break;
                case EXIT:
                default:
                    exit();
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }
    }

    private static void searchPhysicianByName() {
        ArrayList<Physician> phys = PhysicianModel.all();
        bookAppointmentByPhysicians(phys);
    }

    private static void bookAppointmentByPhysicians(ArrayList<Physician> phys) {
        List<Integer> col = new ArrayList<Integer>();
        for (Physician ph : phys) {
            print(ph.toString());

        }

        print("Enter the physicain ID to select a physician");

        try {
            int cmd = input.nextInt();
            checkExit(cmd);
            Physician ph = PhysicianModel.findById(cmd);
            if (ph == null) {
                print("There is no Physician with the specified ID");
                searchPhysicianAgain();

            } else {
                ArrayList<Treatment> avTrs = ph.getTreatments("available");
                if (avTrs.size() != 0) {
                    print("\n:::Available treatments for: " + ph.getFullName() + ":::");
                    for (Treatment _tr : avTrs) {
                        col.add(_tr.getId());
                        print("\n" + _tr);
                    }

                    print("\nEnter the Treatment ID to book a appointment");
                    cmd = input.nextInt();
                    if (!col.contains(cmd)) {
                        print("The ID you entered is not a valid option");
                        bookAppointmentByPhysicians(phys);
                    }
                    Treatment tr = TreatmentModel.findById(cmd);
                    if (tr == null) {
                        print("\nThere's no treatment with the specified ID");
                        exit();
                    } else {
                        print("Perfect, Now, we need your info to effect the booking\nEnter 1 -- to select an existing patient\nEnter 2 -- To create a new  patient\n");
                        Appointment newAp = null;
                        cmd = input.nextInt();
                        switch (cmd) {
                            case 1:
                                newAp = bookByExistingPatient(tr.getId());
                                break;
                            case 2:
                                newAp = bookByNewPatient(tr.getId());
                                break;
                            default:
                                exitOrMainMenu();

                        }
                        print("\n::::Congratulation, booking successful::::\n::::Below is the details of your new booking::::\n");

                        print(newAp.toString()+"\n", true);
                        print("Would you like to change the status of your appointment? Enter Y or N");
                        input.nextLine();
                        String str_cmd = input.nextLine();
                        if (str_cmd.equalsIgnoreCase("y")) {
                            changeAppointment(newAp.getId());
                        } else {
                            exitOrMainMenu();
                        }

                    }

                } else {
                    print("There's no available treatemnt for " + ph.getFullName() + "\nEnter 0 -- To exit\nEnter 1  -- To try again");
                    cmd = input.nextInt();
                    if (cmd == 1) {
                        bookAppointmentByPhysicians(phys);
                    } else {
                        exitOrMainMenu();
                    }

                }

            }
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            searchPhysicianAgain();
        }

    }

    public static Appointment bookByNewPatient(int trId) {
        Patient newPa = createNewPatient();
        
        Appointment newAp = Appointment.create(trId, newPa.getId());
        return newAp;
    }

    public static Appointment bookByExistingPatient(int trId) {
        Appointment newAp = null;
        try {
            ArrayList<Patient> pas = PatientModel.all();
            for (Patient pa : pas) {
                print(pa.toString(), true);
            }
            print("\nEnter the ID of the patient to create an appointment\n");
            int cmd = input.nextInt();
            Patient pa = PatientModel.findById(cmd);
            if (pa == null) {
                print("The patient with the specified ID does not exist\nWould you like to try again? Y or N");
                String str_cmd = input.nextLine();
                if (str_cmd.equalsIgnoreCase("y")) {
                    bookByExistingPatient(trId);
                } else {
                    exitOrMainMenu();
                }
            } else {
                newAp = Appointment.create(trId, pa.getId());
            }

        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }
        return newAp;
    }

    private static void searchPhysicianAgain() {

        try {
            print("1 -- Try again\n2 -- Go back to main menu\n0 -- Exit\n");
            int cmd = input.nextInt();
            switch (cmd) {
                case 9:
                    init();
                    break;
                case 1:
                    searchPhysicianByName();
                case 0:
                default:
                    exit();
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }
    }

    private static void searchAreaOfExpertise() {
        ArrayList<Expertise> exps = ExpertiseModel.all();
        for (Expertise exp : exps) {
            print(exp.toString());
        }

        print("Enter an expertise ID to view related physicians");
        try {
            int cmd = input.nextInt();
            if (cmd == EXIT) {
                exitOrMainMenu();
            }

            Expertise exp = ExpertiseModel.findById(cmd);
            if (exp == null) {
                print("There's no Experise with the sepcified ID\nEnter 1 -- To try again\nEnter 0 -- Exit\n Enter 9 -- Go back to main menu");
                cmd = input.nextInt();
                switch (cmd) {
                    case 9:
                        init();
                        break;
                    case 1:
                        searchAreaOfExpertise();
                    case EXIT:
                    default:
                        exitOrMainMenu();
                }
            } else {
                ArrayList<Physician> phys = PhysicianModel.findByExpertise(exp.getName());
                bookAppointmentByPhysicians(phys);

            }
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }
    }

    private static void changeAppointment(int apId) {
        Appointment ap = AppointmentModel.findById(apId);
        boolean changed = false;
        try {
            if (ap == null) {
                print("Could not find the specified appointment record");
                exit();
            } else {

                print("Select an action below:\n1 -- Update appointment status\n2 -- Change appointment treatment");
                int cmd = input.nextInt();
                switch (cmd) {
                    case 1:
                         if(ap.getStatus().equalsIgnoreCase("attended")){
                             print("You cannot change the status of an appointment that has been attended to\n");
                             break;
                         }
                        changed = updateAppointStatus(ap);
                        break;
                    case 2:
                        if(ap.getStatus().equalsIgnoreCase("attended") || ap.getStatus().equalsIgnoreCase("canceled")){
                             print("You cannot change the the treatment of a canceled or attended appointment\n");
                             break;
                         }
                        changed = changeApTreatment(ap);
                        break;
                    default:
                        exitOrMainMenu();
                }

            }

            if (changed) {
                print(":::Below is the detail of your updated appointment:::\n" + ap);
            } else {
                print("Appointment could not be changed\n");
            }
            exitOrMainMenu();
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }

    }

    public static boolean updateAppointStatus(Appointment ap) {
        boolean changed = false;
        try {
            print("Select an action below to apply to your appointment\nEnter 1 --- to Attend\nEnter 2 --- to Cancel ");
            int cmd = input.nextInt();
            switch (cmd) {
                case 1:
                    ap.setStatus("attended");
                    changed = true;
                    break;
                case 2:
                    Treatment tr = ap.getTreatment();
                    tr.setStatus("available");
                    ap.setStatus("canceled");
                    changed = true;
                    break;
                case 0:
                    exitOrMainMenu();
                default:
                    print("\nYou have entered an invalid option\nWould you like to try again? Y or N");
                    input.nextLine();
                    String str_cmd = input.nextLine();
                    if (str_cmd.equalsIgnoreCase("y")) {
                        updateAppointStatus(ap);
                    } else {
                        exitOrMainMenu();
                    }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();
        }
        return changed;

    }

    public static boolean changeApTreatment(Appointment ap) {
        boolean changed = false;
        if (ap.getStatus().equalsIgnoreCase("canceled")) {
            print("You cannot change the treatment for a canceled appointment");
            return changed;
        }
        try {
            ArrayList<Treatment> trs = TreatmentModel.all("available");
            if (trs.size() == 0) {
                print("There's no available treatment by any physician at the moment\n");
                exitOrMainMenu();
            } else {
                List<Integer> col = new ArrayList<Integer>();
                print(":::Available treatments\n");
                for (Treatment tr : trs) {
                    col.add(tr.getId());
                    print(tr.toString(), true);
                }
                print("Enter the ID of the new treatment to update");
                int cmd = input.nextInt();
                if (!col.contains(cmd)) {
                    print("You have entered an invalid option");
                    exitOrMainMenu();
                } else {
                    ap.setTreatmentId(cmd);
                    changed = true;
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();
        }
        return changed;
    }

    public static void exit() {
        print("Good Bye!");
        System.exit(EXIT);
    }

    public static boolean isValidOption(List<Integer> col, int id) {
        return col.contains(id);
    }

    public static void checkExit(int cmd) {
        if (cmd == 0) {
            exit();
        }
    }

    public static void changeAppointmentEntry() {
        ArrayList<Patient> pas = PatientModel.all();
        List<Integer> col = new ArrayList<Integer>();

        print("\n:::: PATIENTS ::::\n");
        for (Patient pa : pas) {
            print(pa.toString(), true);
        }
        print("\nEnter the ID of the patient you want to change appointment for\n");
        try {
            int cmd = input.nextInt();
            if (cmd == EXIT) {
                exitOrMainMenu();
            }
            Patient pa = PatientModel.findById(cmd);
            if (pa == null) {
                print("The patient with the specified ID does not exit\n");
                changeAppointmentEntry();
            }
            ArrayList<Appointment> aps = pa.getAppointments();
            if (aps.size() == 0) {
                print("No appointment found for " + pa.getFullName());
                exitOrMainMenu();
            }

            print("::: Appointments for: " + pa.getFullName() + " :::");
            for (Appointment ap : aps) {
                col.add(ap.getId());
                print(ap.toString(false), true);
            }
            print("\nEnter the ID of the appointment to update\n");
            cmd = input.nextInt();
            if (cmd == EXIT) {
                exitOrMainMenu();
            }
            if (!col.contains(cmd)) {
                System.out.print("\nYou've entered an invalid option");
                changeAppointmentEntry();
            } else {
                changeAppointment(cmd);
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }

    }

    public static void exitOrMainMenu() {
        print("\nEnter 0 to exit\nEnter 9 to go back to main menu");
        try {
            int cmd = input.nextInt();

            if (cmd == 9) {
                init();
            } else if (cmd == EXIT) {
                exit();
            } else {
                print("You've enetered an invalid option");
                exitOrMainMenu();
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exit();

        }
    }

    public static void print(String value) {
        System.out.println(value);
    }

    public static void print(String value, boolean addLines) {
        System.out.println("----------------------------------------------");
        System.out.println(value);
    }

    public static void listPatientAppointment() {

        ArrayList<Patient> pas = PatientModel.all();
        for (Patient pa : pas) {
            print(pa.toString(), true);
        }
        try {
            print("\nEnter the ID of the patient to view they appointment");
            int cmd = input.nextInt();
            Patient pa = PatientModel.findById(cmd);
            if (pa == null) {
                print("\nPatient with the specified ID does not exist");
                exitOrMainMenu();
            }
            ArrayList<Appointment> aps = pa.getAppointments();

            if (aps.size() > 0) {
                print(":::" + pa.getFullName() + "'s appointments\n");
                for (Appointment ap : aps) {
                    print(ap.toString(), true);
                }
            } else {
                print("There's no appointment for: " + pa.getFullName());
            }
            exitOrMainMenu();
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }
    }

    public static void listAllAppointments() {
        ArrayList<Appointment> aps = AppointmentModel.all();
        print("::: All appointments\n");
        for (Appointment ap : aps) {
            print(ap.toString() + "\n", true);
        }

        ArrayList<Consultation> cs = ConsultationModel.all();
        print(":::Visitors Consultations", true);
        for (Consultation c : cs) {
            print(c.toString(), true);
        }

        exitOrMainMenu();
    }

    public static Patient createNewPatient() {
        String fn = "";
        String adr = "";
        String tel = "";

        print("Enter your full name");
        do {
            fn = input.nextLine();
        } while (fn.isEmpty());
        do {
            print("Enter your Address");
            adr = input.nextLine();
        } while (adr.isEmpty());
        do {
            print("Enter your telephone number");
            tel = input.nextLine();
        } while (tel.isEmpty());
 
        Patient newPa = Patient.create(fn, adr, tel);

        return newPa;
    }

    public static void newPatientEntry() {
        try {
            Patient pa = createNewPatient();
            print("Patient created successfully\n" + pa.toString() + "\n");
            print("--------------------------------------");
            print("\nWhat would you like to do?\n1 -- Book appointment by area of expertise\n2 -- Book appointment by Physician");
            int cmd = input.nextInt();
            switch (cmd) {
                case 1:
                    searchAreaOfExpertise();
                    break;
                case 2:
                    searchPhysicianByName();
                    break;
                default:
                    exitOrMainMenu();
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }

    }

    public static void bookConsultation() {
        print("\nWhat would you like to do?\n1 -- Book Consultation by area of expertise\n2 -- Book consultation by Physician");
        int cmd = input.nextInt();
        switch (cmd) {
            case 1:
                bookConsultationByExpertise();
                break;
            case 2:
                bookConsultationByPhysician();
                break;
            default:
                exitOrMainMenu();
        }
    }

    public static void bookConsultationByPhysician() {
        List<Integer> col = new ArrayList<Integer>();
        ArrayList<Physician> phys = PhysicianModel.all();
        for (Physician ph : phys) {
            if (!ph.isFullyBooked()) {
                col.add(ph.getId());
                print(ph.toString());
            }
        }
        try {
            print("Enter the ID of the physician to book consultation");
            int cmd = input.nextInt();
            if (!isValidOption(col, cmd)) {
                print("You've selected an invalid option");
                exitOrMainMenu();
            }

            print("\nEnter your name: ");
            String name = "";
            do {
                name = input.nextLine();
            } while (name.isEmpty());

            int id = ConsultationModel.all().size() + 1;
            Consultation c = Consultation.create(id, cmd, name);
            print("\n:::Consultation successully booked:::\n" + c.toString());
            exitOrMainMenu();
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }

    }

    public static void bookConsultationByPhysician(ArrayList<Physician> phys) {

        List<Integer> col = new ArrayList<Integer>();
        for (Physician ph : phys) {

            col.add(ph.getId());
            print(ph.toString());

        }
        try {
            print("Enter the ID of the physician to book consultation");
            int cmd = input.nextInt();
            if (!isValidOption(col, cmd)) {
                print("You've selected an invalid option");
                exitOrMainMenu();
            }

            print("\nEnter your name: ");
            String name = "";
            do {
                name = input.nextLine();
            } while (name.isEmpty());

            int id = ConsultationModel.all().size() + 1;
            Consultation c = Consultation.create(id, cmd, name);
            print("\n:::Consultation successully booked:::\n" + c.toString());
            exitOrMainMenu();
        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }

    }

    public static void bookConsultationByExpertise() {
        ArrayList<Expertise> exps = ExpertiseModel.all();
        List<Integer> col = new ArrayList<Integer>();
        for (Expertise exp : exps) {
            col.add(exp.getId());
            print(exp.toString());
        }

        print("Enter an expertise ID to view related physicians");
        try {
            int cmd = input.nextInt();
            if (cmd == EXIT) {
                exitOrMainMenu();
            }
            if (!isValidOption(col, cmd)) {
                print("You've entered an invalid option");
                exitOrMainMenu();
            }
            Expertise exp = ExpertiseModel.findById(cmd);

            ArrayList<Physician> phys = PhysicianModel.findByExpertise(exp.getName());
 
            ArrayList<Physician> avPhys = new ArrayList<Physician>();
            for (Physician p : phys) {
                if (!p.isFullyBooked()) {
                    avPhys.add(p);
                }
            }
            bookConsultationByPhysician(avPhys);

        } catch (InputMismatchException e) {
            input.nextLine();
            print("Oops, you have entered an invalid command");
            exitOrMainMenu();

        }
    }

}
