/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paolosantaniello;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import paolosantaniello.Model.Part;
import paolosantaniello.Model.Product;
import paolosantaniello.View_Controller.MainController;
import paolosantaniello.View_Controller.AddInHousePartController;
import paolosantaniello.View_Controller.AddProductController;
import paolosantaniello.View_Controller.ModifyInHousePartController;
        

public class PaoloSantaniello extends Application {

    private Stage primaryStage;
    private AnchorPane mainScreen;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inventory Management System");

        showMainScreen();    
    }
    
    
        public void showMainScreen() {
        try {
            // Load Parts overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PaoloSantaniello.class.getResource("/paolosantaniello/View_Controller/Main.fxml"));
            AnchorPane mainScreen = (AnchorPane) loader.load();   

            // Give the controller access to the main app.
            MainController controller = loader.getController();
            controller.setMainApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(mainScreen);
            primaryStage.setScene(scene);
            primaryStage.show();

            }   catch (IOException e) {
                    e.printStackTrace();
                }
        }    
        
        
        public boolean showAddPartScreen() {
            try {
                //Load FXML file and create new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();            
                loader.setLocation(PaoloSantaniello.class.getResource("/paolosantaniello/View_Controller/AddInHousePart.fxml"));
                AnchorPane inhousePartScreen = (AnchorPane) loader.load();
                
                //Crate the dialog stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Add New Part");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(inhousePartScreen);
                dialogStage.setScene(scene);
                
                //Set the part into the controller
                AddInHousePartController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                
                //Show the dialog and wait until the user closes it.
                dialogStage.showAndWait();
                
                return controller.isOKClicked();
            } catch (IOException e){
                e.printStackTrace();
                return false;
                
            }
        }
        
        public boolean showModPartScreen(Part part) {
            try {
                //Load FXML file and create new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();            
                loader.setLocation(PaoloSantaniello.class.getResource("/paolosantaniello/View_Controller/ModifyInHousePart.fxml"));
                AnchorPane modPartScreen = (AnchorPane) loader.load();
                
                //Crate the dialog stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modify Part");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(modPartScreen);
                dialogStage.setScene(scene);
                
                //Set the part into the controller
                ModifyInHousePartController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.selectedPartFill(part);
                
                //Show the dialog and wait until the user closes it.
                dialogStage.showAndWait();
                
                return controller.isOKClicked();
            } catch (IOException e){
                e.printStackTrace();
                return false;
                
            }
        }
        
        

        public boolean showModProductScreen(Product product) {
            try {
                //Load FXML file and create new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();            
                loader.setLocation(PaoloSantaniello.class.getResource("/paolosantaniello/View_Controller/ModifyProduct.fxml"));
                AnchorPane modProductScreen = (AnchorPane) loader.load();
                
                //Crate the dialog stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modify Product");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(modProductScreen);
                dialogStage.setScene(scene);
                
                //Set the part into the controller
                AddProductController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.selectedProductFill(product);
                
                //Show the dialog and wait until the user closes it.

                dialogStage.showAndWait();
                
                return controller.isOKClicked();
            } catch (IOException e){
                e.printStackTrace();
                return false;
                
            }
        }        
        public boolean showAddProdScreen() {
            try {
                //Load FXML file and create new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();            
                loader.setLocation(PaoloSantaniello.class.getResource("/paolosantaniello/View_Controller/AddProduct.fxml"));
                AnchorPane productScreen = (AnchorPane) loader.load();
                
                //Crate the dialog stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Add New Product");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(productScreen);
                dialogStage.setScene(scene);
                
                //Set the part into the controller
                AddProductController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                
                //Show the dialog and wait until the user closes it.
                dialogStage.showAndWait();
                
                return controller.isOKClicked();
            } catch (IOException e){
                e.printStackTrace();
                return false;
                
            }
        }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
