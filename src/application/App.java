package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");

        System.out.println("Type the rent data: ");
        System.out.print("Car model: ");
        String carModel = sc.nextLine();
        System.out.print("Pickup (dd/MM/yyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("Return (dd/MM/yyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);
        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Type the price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Type the price per day: ");
        double pricePerDay = sc.nextDouble();
        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

        rentalService.processInvoice(cr);
        System.out.println();
        System.out.println("Invoice: ");
        System.out.printf("Basic payment: %.2f%n",cr.getInvoice().getBasicPayment());
        System.out.printf("Tax: %.2f%n",cr.getInvoice().getTax());
        System.out.printf("Total: %.2f%n",cr.getInvoice().getTotalPayment());

        sc.close();

//        Civic
//        25/06/2018 10:30
//        27/06/2018 14:40      25/06/2018 14:40
//        10.00
//        130.00
    }
}
