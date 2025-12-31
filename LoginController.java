package com.GearRentPro.controller.auth;

import com.GearRentPro.entity.User;
import com.GearRentPro.enums.UserRole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;
    @FXML private CheckBox chkRemember;
    
    @FXML
    public void initialize() {
        lblError.setVisible(false);
    }
    
    @FXML
    private void onLoginClicked() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        
        System.out.println("Login attempt: " + username);
        
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter username and password");
            return;
        }
        
        // HARDCODED LOGIN FOR TESTING
        if (username.equals("admin") && password.equals("admin123")) {
            User adminUser = createUser("U001", "admin", "admin123", 
                                       UserRole.ADMIN, "BR001", true);
            loginSuccessful(adminUser);
            
        } else if (username.equals("staff") && password.equals("staff123")) {
            User staffUser = createUser("U002", "staff", "staff123", 
                                       UserRole.STAFF, "BR001", true);
            loginSuccessful(staffUser);
            
        } else if (username.equals("manager") && password.equals("manager123")) {
            User managerUser = createUser("U003", "manager", "manager123", 
                                         UserRole.MANAGER, "BR001", true);
            loginSuccessful(managerUser);
            
        } else if (username.equals("cashier") && password.equals("cashier123")) {
            User cashierUser = createUser("U004", "cashier", "cashier123", 
                                         UserRole.CASHIER, "BR001", true);
            loginSuccessful(cashierUser);
            
        } else {
            showError("Invalid credentials. Try:\n" +
                     "• Admin: admin / admin123\n" +
                     "• Manager: manager / manager123\n" +
                     "• Staff: staff / staff123\n" +
                     "• Cashier: cashier / cashier123");
        }
    }
    
    @FXML
    private void onForgotPasswordClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Forgot Password");
        alert.setHeaderText(null);
        alert.setContentText("Please contact system administrator.\n" +
                           "Email: admin@gearrentpro.lk\n" +
                           "Phone: +94 11 234 5678");
        alert.showAndWait();
    }
    
    private User createUser(String userId, String username, String password,
                           UserRole role, String branchId, boolean status) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setBranchId(branchId);
        user.setStatus(status);
        return user;
    }
    
    private void loginSuccessful(User user) {
        try {
            // Store current user in session
            CurrentSession.getInstance().setCurrentUser(user);
            
            // Open appropriate dashboard based on role
            openDashboard(user);
            
        } catch (Exception e) {
            showError("Failed to open dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void openDashboard(User user) throws IOException {
        String fxmlFile;
        String title;
        
        switch (user.getRole()) {
            case ADMIN:
                fxmlFile = "/fxml/dashboard/admin-dashboard.fxml";
                title = "Admin Dashboard";
                break;
            case MANAGER:
                fxmlFile = "/fxml/dashboard/manager-dashboard.fxml";
                title = "Manager Dashboard";
                break;
            case STAFF:
                fxmlFile = "/fxml/dashboard/staff-dashboard.fxml";
                title = "Staff Dashboard";
                break;
            case CASHIER:
                fxmlFile = "/fxml/dashboard/cashier-dashboard.fxml"; // Create this if needed
                title = "Cashier Dashboard";
                break;
            default:
                fxmlFile = "/fxml/dashboard/staff-dashboard.fxml";
                title = "Dashboard";
        }
        
        // If dashboard FXML doesn't exist, open rental form directly for testing
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("GearRent Pro - " + title);
            stage.centerOnScreen();
            
            System.out.println("✅ Login successful! User: " + user.getUsername() + 
                              ", Role: " + user.getRole());
            
        } catch (IOException e) {
            // If dashboard FXML not found, open rental form directly
            System.out.println("Dashboard not found, opening rental form directly...");
            openRentalFormDirectly(user);
        }
    }
    
    private void openRentalFormDirectly(User user) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/transactions/NewRental.fxml"));
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.setScene(new Scene(root, 900, 700));
            stage.setTitle("GearRent Pro - New Rental (Logged in as: " + user.getUsername() + ")");
            stage.centerOnScreen();
            
            System.out.println("✅ Opened rental form directly");
            
        } catch (IOException e) {
            showError("Failed to load rental form: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void showError(String message) {
        lblError.setText(message);
        lblError.setVisible(true);
    }
}

// Session manager class to store current user
class CurrentSession {
    private static CurrentSession instance;
    private User currentUser;
    
    private CurrentSession() {}
    
    public static CurrentSession getInstance() {
        if (instance == null) {
            instance = new CurrentSession();
        }
        return instance;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    
    public boolean isAdmin() {
        return currentUser != null && currentUser.getRole() == UserRole.ADMIN;
    }
    
    public boolean isManager() {
        return currentUser != null && currentUser.getRole() == UserRole.MANAGER;
    }
    
    public boolean isStaff() {
        return currentUser != null && currentUser.getRole() == UserRole.STAFF;
    }
    
    public boolean isCashier() {
        return currentUser != null && currentUser.getRole() == UserRole.CASHIER;
    }
    
    public String getBranchId() {
        return currentUser != null ? currentUser.getBranchId() : null;
    }
}