/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paolosantaniello.View_Controller;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import paolosantaniello.Model.InhousePart;
import paolosantaniello.Model.OutsourcedPart;
import paolosantaniello.Model.Part;
import paolosantaniello.Model.Inventory;


public class AddInHousePartController {

@FXML
    private AnchorPane addPartInHouse;

    @FXML
    private RadioButton addPartInHouseBtn;

    @FXML
    private RadioButton addPartOutBtn;

    @FXML
    private Button addPartInHouseSave;

    @FXML
    private Button addPartInhouseCancel;

    @FXML
    private TextField partMax;

    @FXML
    private TextField partMin;

    @FXML
    private TextField partMachId;

    @FXML
    private TextField partPrice;

    @FXML
    private TextField partName;

    @FXML
    private TextField partInv;

    @FXML
    private TextField partId;
    
    @FXML
    private ToggleGroup Source;
    
    @FXML
    private Label partMachIdLbl;
    
    
    private Part selectedPart;
    private int partID;
    private boolean okClicked = false;
    private Stage dialogStage;
    
    @FXML
    void radioButtonHandler() {
        if (Source.getSelectedToggle().equals(this.addPartInHouseBtn)){
            partMachId.setPromptText("Mach ID");
            partMachIdLbl.setText("Machine ID");
        }
        
        if (Source.getSelectedToggle().equals(this.addPartOutBtn)){
            partMachId.setPromptText("Comp Name");
            partMachIdLbl.setText("Company ID");
        }
    }

    @FXML
    void addPartInhouseCancelHandler(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to Cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() ==ButtonType.OK){
            partID = Inventory.cancelPartIDCount();
            dialogStage.close();
        } else {
            alert.close();
        }
    }

    @FXML
    void addPartInhouseSaveHandler(ActionEvent event) throws IOException{
        if( partValidation()){
       String name = partName.getText();
       String inStock = partInv.getText();
       String price = partPrice.getText();
       String min = partMin.getText();
       String max = partMax.getText();
       String machID = partMachId.getText();
       
       if ( this.Source.getSelectedToggle().equals(this.addPartInHouseBtn)){
           InhousePart inPart = new InhousePart();
           inPart.setPartID(partID);
           inPart.setName(name);
           inPart.setInStock(Integer.parseInt(inStock));
           inPart.setPrice(Double.parseDouble(price));
           inPart.setMin(Integer.parseInt(min));
           inPart.setMax(Integer.parseInt(max));
           inPart.setMachineID(Integer.parseInt(machID));
           Inventory.addPart(inPart);
        }
       else {
           OutsourcedPart outPart = new OutsourcedPart();
           outPart.setPartID(partID);
           outPart.setName(name);
           outPart.setInStock(Integer.parseInt(inStock));
           outPart.setPrice(Double.parseDouble(price));
           outPart.setMin(Integer.parseInt(min));
           outPart.setMax(Integer.parseInt(max));
           outPart.setCompanyName(machID);
           Inventory.addPart(outPart);
       }       
       okClicked = true;
       dialogStage.close(); 
        }
    }

    private boolean partValidation(){
       String name = partName.getText();
       String inStock = partInv.getText();
       String price = partPrice.getText();
       String min = partMin.getText();
       String max = partMax.getText();
       String machID = partMachId.getText();
       String errorMessage = "";
       
       if(name == null || name.length() == 0){
           errorMessage += "Name Invalid";
       }
       if( inStock == null || inStock.length() == 0){
           errorMessage += "Inventory Invalid";
        } else {
           try{
               int inStockComp = Integer.parseInt(inStock);
                int minComp = Integer.parseInt(min);
                int maxComp = Integer.parseInt(max);
                if (inStockComp < minComp || inStockComp > maxComp) {
                errorMessage += "Inventory must be between the minimum or maximum value!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "No valid Inventory value (must be an integer)!\n"; 
            }
        }
        if (price == null || price.length() == 0) {
            errorMessage += "No valid price!\n"; 
        } else {
            try {
                Double.parseDouble(price);
            } catch (NumberFormatException e) {
                errorMessage += "No valid Price (must be a double)!\n"; 
            }
        }
        if (min == null || min.length() == 0) {
            errorMessage += "No valid Min value!\n"; 
        } else {
            try {
                int minComp = Integer.parseInt(min);
                int maxComp = Integer.parseInt(max);
                if (maxComp < minComp || minComp >= maxComp ) {
                    errorMessage += "Maximum value must be greater than Minimum!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "No valid Min value (must be an integer)!\n"; 
            }
        }        
        if (max == null || max.length() == 0) {
            errorMessage += "No valid Max value!\n"; 
        } else {
            try {
                int minComp = Integer.parseInt(min);
                int maxComp = Integer.parseInt(max);
                if (maxComp < minComp || minComp >= maxComp ) {
                    errorMessage += "Maximum value must be greater than Minimum!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "No valid Max value (must be an integer)!\n"; 
            }
        }
        if (machID == null || machID.length() == 0) {
            errorMessage += "No valid Machine ID or Company Name!\n"; 
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
       }
    }
    
    @FXML
    public void initialize() {
        Source = new ToggleGroup();
        this.addPartInHouseBtn.setToggleGroup(Source);
        this.addPartOutBtn.setToggleGroup(Source);
        partID = Inventory.getPartIDCount();
        partId.setText("Auto-Gen: " + partID);
        partInv.setText("0");
    }    

    public void setDialogStage(Stage dialogStage) {
         this.dialogStage = dialogStage;
    }

    public boolean isOKClicked() {
        return okClicked;
    }
    
}
