package org.example;

import Repo.Repository;
import Service.Service;
import View.HotelView;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, UnknownHostException {
        Repository repository = new Repository();
        Service service = Service.getInstance(repository);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the radius in kilometers: ");
        try{
            int radius = scanner.nextInt();
            HotelView hotelView = new HotelView(service.getNearbyHotels(radius));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }
}