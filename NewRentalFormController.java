package com.GearRentPro.controller.transactions;

import com.GearRentPro.controller.RentalController;
import com.GearRentPro.entity.Equipment;
import com.GearRentPro.entity.Customer;
import com.GearRentPro.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class NewRentalFormController {
    
    @FXML private ComboBox<Customer> cmbCustomer;
    @FXML private ComboBox<Equipment> cmbEquipment;
    @FXML private DatePicker dpStartDate;
    @FXML private DatePicker dpEndDate;
    @FXML private Label lblTotalDays, lblSubtotal, lblLongDiscount;
    @FXML private Label lblMembershipDiscount, lblFinalAmount, lblDeposit;
    @FXML private Label lblTotalPayable, lblMessage;
    @FXML private Button btnCreateRental;
    
    private RentalController rentalController = new RentalController();
    private User currentUser;
    
    @FXML
    public void initialize() {
        // Get current user from session
        currentUser = CurrentSession.getInstance().getCurrentUser();
        
        if (currentUser == null) {
            System.out.println("⚠️ No user logged in. Using test mode.");
            // Create a test user for demonstration
            currentUser = createTestUser();
        }
        
        System.out.println("Current user: " + currentUser.getUsername() + 
                          ", Role: " + currentUser.getRole() +
                          ", Branch: " + currentUser.getBranchId());
        
        resetCalculationDisplay();
        
        // TODO: Load customers and equipment from database
        // loadCustomers();
        // loadEquipment();
    }
    
    private User createTestUser() {
        User testUser = new User();
        testUser.setUserId("TEST001");
        testUser.setUsername("testuser");
        testUser.setRole(com.GearRentPro.enums.UserRole.STAFF);
        testUser.setBranchId("BR001");
        testUser.setStatus(true);
        return testUser;
    }
    
    @FXML
    private void onCalculateClicked() {
        try {
            // For now, just test with dummy data
            testPricingCalculation();
            
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage(), true);
            e.printStackTrace();
        }
    }
    
    private void testPricingCalculation() {
        // Dummy calculation for testing
        double dailyRate = 5000.00;
        int totalDays = 10;
        int weekendDays = 2;
        String membership = "GOLD";
        
        // Calculate
        double subtotal = dailyRate * totalDays;
        double longDiscount = totalDays >= 7 ? subtotal * 0.10 : 0;
        double membershipDiscount = 0;
        
        if ("SILVER".equalsIgnoreCase(membership)) {
            membershipDiscount = subtotal * 0.05;
        } else if ("GOLD".equalsIgnoreCase(membership)) {
            membershipDiscount = subtotal * 0.10;
        }
        
        double finalAmount = subtotal - longDiscount - membershipDiscount;
        double deposit = Math.max(10000.00, finalAmount * 0.5);
        
        // Display
        lblTotalDays.setText(totalDays + " days (" + weekendDays + " weekend days)");
        lblSubtotal.setText(String.format("LKR %,.2f", subtotal));
        lblLongDiscount.setText(String.format("-LKR %,.2f", longDiscount));
        lblMembershipDiscount.setText(String.format("-LKR %,.2f", membershipDiscount));
        lblFinalAmount.setText(String.format("LKR %,.2f", finalAmount));
        lblDeposit.setText(String.format("LKR %,.2f", deposit));
        lblTotalPayable.setText(String.format("LKR %,.2f", finalAmount + deposit));
        
        // Show all labels
        setCalculationVisible(true);
        btnCreateRental.setDisable(false);
        
        showMessage("Price calculated successfully! This is a test calculation.", false);
    }
    
    @FXML
    private void onCreateRentalClicked() {
        try {
            // Get current user's branch
            String branchId = currentUser.getBranchId();
            
            // Generate rental ID
            String rentalId = "RENT" + System.currentTimeMillis();
            
            showMessage("Rental would be created with ID: " + rentalId + 
                       "\nBranch: " + branchId + 
                       "\nCreated by: " + currentUser.getUsername(), false);
            
            // TODO: Actually save to database
            // rentalController.saveRental(...);
            
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage(), true);
        }
    }
    
    @FXML
    private void onCancelClicked() {
        // Go back or close
        Stage stage = (Stage) cmbCustomer.getScene().getWindow();
        stage.close();
    }
    
    private void setCalculationVisible(boolean visible) {
        lblSubtotal.setVisible(visible);
        lblLongDiscount.setVisible(visible);
        lblMembershipDiscount.setVisible(visible);
        lblFinalAmount.setVisible(visible);
        lblDeposit.setVisible(visible);
        lblTotalPayable.setVisible(visible);
    }
    
    private void resetCalculationDisplay() {
        lblTotalDays.setText("0 days");
        lblSubtotal.setText("LKR 0.00");
        lblLongDiscount.setText("-LKR 0.00");
        lblMembershipDiscount.setText("-LKR 0.00");
        lblFinalAmount.setText("LKR 0.00");
        lblDeposit.setText("LKR 0.00");
        lblTotalPayable.setText("LKR 0.00");
        
        setCalculationVisible(false);
        btnCreateRental.setDisable(true);
        lblMessage.setText("");
    }
    
    private void showMessage(String message, boolean isError) {
        lblMessage.setText(message);
        if (isError) {
            lblMessage.setStyle("-fx-text-fill: #e74c3c;");
        } else {
            lblMessage.setStyle("-fx-text-fill: #27ae60;");
        }
    }
    
}