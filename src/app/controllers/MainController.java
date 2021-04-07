/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.AppointmentModel;
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
public class MainController {

    private static final int EXIT = 0;
    private static final int MAIN_MENU = 9;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        PatientModel.seed(10);
        PhysicianModel.seed();
        TreatmentModel.seed();
        AppointmentModel.seed(5);
        print(":::Welcome to PSIC booking system:::\nTo let us know how we can help you\n");
        init();

    }

    private static void init() {

        print("Choose an option below\nEnter 0 to exit\nEnter 1 to look up Physicians\nEnter 2 to look up physicians' areas of expertise\nEnter 3 to change appointment\nEnter 4 to view all appointments\nEnter 5 to view a patient's appointments");
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
            case EXIT:
            default:
                exit();
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

        print("Enter the physicain ID to select a physician or Enter the physician name to look up");

        try {
            int cmd = input.nextInt();
            checkExit(cmd);
            Physician ph = PhysicianModel.findById(cmd);
            if (ph == null) {
                print("There is no Physicain with the specified ID");
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
                        print("Perfect, Now, we need your info to effect the booking");
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
                        int id = PatientModel.all().size() + 1;
                        Patient newPa = Patient.create(id, fn, adr, tel);
                        int apId = AppointmentModel.all().size() + 1;
                        int trId = tr.getId();
                        Appointment newAp = Appointment.create(apId, tr.getId(), id);
                        print("\n::::Congratulation, booking successful::::\n::::Below is the details of your new booking::::\n");

                        print(newAp.toString());
                        print("Would you like to change the status of your appointment? Enter Y or N");
                        String str_cmd = input.nextLine();
                        if (str_cmd.equalsIgnoreCase("y")) {
                            changeAppointment(apId);
                        } else {
                            exitOrMainMenu();
                        }

                    }

                } else {
                    print("There's no available treatemnt for " + ph.getFullName() + "\nEnter 0 to exit\nEnter 1 to try again");
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

    private static void searchPhysicianAgain() {

        try {
            print("To search again, enter 1\nTo go back to main menu, enter 9\nTo exit, enter 0\n");
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
            print("Oops, you have entered an invalid command twice");
            exitOrMainMenu();

        }
    }

    private static void searchAreaOfExpertise() {
        ArrayList<Expertise> exps = ExpertiseModel.all();
        for (Expertise exp : exps) {
            print(exp.toString());
        }

        print("Enter an expertise ID to view related physicians");

        int cmd = input.nextInt();
        if (cmd == EXIT) {
            exitOrMainMenu();
        }

        Expertise exp = ExpertiseModel.findById(cmd);
        if (exp == null) {
            print("There's no Experise with the sepcified ID\nEnter 1 to try again\nEnter 0 to exit\nEnter 9 to go cak to main menu");
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
    }

    private static void changeAppointment(int apId) {
        Appointment ap = AppointmentModel.findById(apId);
        boolean changed = false;
        if (ap == null) {
            print("Could not find the specified appointment record");
            exit();
        } else {
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
                    exit();
                default:
                    print("\nYou have entered an invalid option\nWould you like to try again? Y or N");
                    input.nextLine();
                    String str_cmd = input.nextLine();
                    if (str_cmd.equalsIgnoreCase("y")) {
                        changeAppointment(apId);
                    } else {
                        exit();
                    }
            }

        }

        if (changed) {
            print(":::Below is the detail of your updated appointment:::\n" + ap);
        }
        exitOrMainMenu();

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

    }

    public static void exitOrMainMenu() {
        print("\nEnter 0 to exit\nEnter 9 to go back to main menu");
        int cmd = input.nextInt();

        if (cmd == 9) {
            init();
        } else if (cmd == EXIT) {
            exit();
        } else {
            print("You've enetered an invalid option");
            exitOrMainMenu();
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
        print("\nEnter the ID of the patient to view they appointment");
        int cmd = input.nextInt();
        Patient pa = PatientModel.findById(cmd);
        if (pa == null) {
            print("\nPatient with the specified ID does not exist");
            exitOrMainMenu();
        }
        ArrayList<Appointment> aps = pa.getAppointments();
        print(":::" + pa.getFullName() + "'s appointments\n");
        for (Appointment ap : aps) {
            print(ap.toString(), true);
        }
        exitOrMainMenu();
    }

    public static void listAllAppointments() {
        ArrayList<Appointment> aps = AppointmentModel.all();
        print("::: All appointments\n");
        for (Appointment ap : aps) {
            print(ap.toString() + "\n", true);
        }
        exitOrMainMenu();
    }
}
