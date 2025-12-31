// src/com/GearRentPro/test/TestAllControllers.java
package com.GearRentPro.test;

import com.GearRentPro.controller.*;

public class TestAllControllers {
    public static void main(String[] args) {
        try {
            System.out.println("✅ Testing all controllers...\n");
            
            // Test UserController
            UserController userController = new UserController();
            System.out.println("1. UserController created successfully");
            
            // Test BranchController
            BranchController branchController = new BranchController();
            System.out.println("2. BranchController created successfully");
            
            // Test CategoryController
            CategoryController categoryController = new CategoryController();
            System.out.println("3. CategoryController created successfully");
            
            // Test CustomerController
            CustomerController customerController = new CustomerController();
            System.out.println("4. CustomerController created successfully");
            
            // Test EquipmentController
            EquipmentController equipmentController = new EquipmentController();
            System.out.println("5. EquipmentController created successfully");
            
            // Test ReservationController
            ReservationController reservationController = new ReservationController();
            System.out.println("6. ReservationController created successfully");
            
            // Test RentalController
            RentalController rentalController = new RentalController();
            System.out.println("7. RentalController created successfully");
            
            // Test MembershipController
            MembershipController membershipController = new MembershipController();
            System.out.println("8. MembershipController created successfully");
            
            System.out.println("\n🎉 ALL 8 CONTROLLERS CREATED SUCCESSFULLY!");
            System.out.println("Your layered architecture is complete!");
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}