/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic;

import app.db.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author nedsoft
 */
public class Runner {

    private static final int EXIT = 0;
    private static final int MAIN_MENU = 9;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
//
        PatientDb.seed(10);
        PhysicianDb.seed();
        TreatmentDb.seed();
        AppointmentDb.seed(5);

        init();

    }

    private static void init() {

        System.out.println("====Welcome to PSIC booking system====\n");
        System.out.println("To let us know how we can help you, choose an option below\nEnter 0 to exit\nEnter 9 to go back to main menu\nEnter 1 to Search Physician by name\nEnter 2 to look up areas of expertise\nEnter 3 to change appointment");
        int cmd = input.nextInt();

        switch (cmd) {

            case 9:
                init();
                break;
            case 1:
                searchPhysicianByName();
                break;
            case 2:
                searchAreaOfExpertise(input);
                break;
            case 3:
//                changeAppointment(input);
                break;
            case EXIT:
            default:
                exit();
        }
    }

    private static void searchPhysicianByName() {
        ArrayList<Physician> phys = PhysicianDb.all();
        for (Physician ph : phys) {
            System.out.println(ph);

        }

        System.out.println("Enter the physicain ID to select a physician or Enter the physician name tp look up");

        try {
            int num_cmd = input.nextInt();
            Physician ph = PhysicianDb.findById(num_cmd);
            if (ph == null) {
                System.out.println("There is no Physicain with the specified ID");
                searchPhysicianAgain();

            } else {
                ArrayList<Treatment> avTrs = ph.getTreatments();
                if (avTrs.size() != 0) {
                    for (Treatment _tr : avTrs) {
                        System.out.println(_tr);
                    }

                    System.out.println("Enter the Treatment ID to book a appointment");
                    int tr_cmd = input.nextInt();
                    Treatment tr = TreatmentDb.findById(tr_cmd);
                    if (tr == null) {
                        System.out.println("There's no treatment with the specified ID");
                        exit();
                    } else {
                        System.out.println("Perfect, Now, we need your info to effect the booking");
                        String fn = "";
                        String adr = "";
                        String tel = "";
                        do {
                            System.out.println("Enter your full name");
                            fn = input.nextLine();
                        } while (fn.isEmpty());
                        do {
                            System.out.println("Enter your Address");
                            adr = input.nextLine();
                        } while (adr.isEmpty());
                        do {
                            System.out.println("Enter your telephone number");
                            tel = input.nextLine();
                        } while (tel.isEmpty());
                        int id = PatientDb.all().size() + 1;
                        Patient newPa = Patient.create(id, fn, adr, tel);
                        int apId = AppointmentDb.all().size() + 1;
                        int trId = tr.getId();
                        Appointment newAp = Appointment.create(apId, tr.getId(), id);
                        System.out.println("\n::::Congratulation, booking successful::::\n::::Below is the details of your new booking::::\n");

                        System.out.println(newAp);
                        System.out.println("Would you like to change the status of your appointment? Enter Y or N");
                        String chApCmd = input.nextLine();
                        if (chApCmd.equalsIgnoreCase("y")) {
                            System.out.println("Select an action below to apply to your appointment\nEnter 1 --- to Attend\nEnter 2 --- to Cancel ");
                            int _chApCmd = input.nextInt();
                            switch (_chApCmd) {
                                case 1:
                                    changeAppointment(apId, "attended");
                                    break;
                                case 2:
                                    changeAppointment(apId, "canceled");
                                    break;
                                default:
                                    exit();
                            }
                        } else {
                            exit();
                        }

                    }

                } else {
                    System.out.println("There's no available treatemnt for " + ph.getFullName());
                    exit();
                }

            }
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Oops, you have entered an invalid command");
            searchPhysicianAgain();
        }

    }

    private static void searchPhysicianAgain() {

        try {
            System.out.println("To search again, enter 1\nTo go back to main menu, enter 9\nTo exit, enter 0\n");
            int _cmd = input.nextInt();
            switch (_cmd) {
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
            System.out.println("Oops, you have entered an invalid command twice");
            exit();

        }
    }

    private static void searchAreaOfExpertise(Scanner input) {
        System.out.println("WIP");
        System.exit(EXIT);
    }

    private static void changeAppointment(int apId, String newStatus) {
        Appointment ap = AppointmentDb.findById(apId);
        if (ap == null) {
            System.out.println("Could not find the specified appointment record");
            exit();
        } else {
            if (newStatus.equalsIgnoreCase("canceled")) {
                Treatment tr = ap.getTreatment();
                tr.setStatus("available");
            }
            ap.setStatus(newStatus);

        }

        System.out.println(":::Below is the detail of your updated appointment:::\n" + ap);

    }

    public static void exit() {
        System.out.println("Good Bye!");
        System.exit(EXIT);
    }

}
