/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paolosantaniello.View_Controller;

import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import paolosantaniello.Model.Product;
import paolosantaniello.Model.*;
import static paolosantaniello.Model.Inventory.getAllParts;
import static paolosantaniello.View_Controller.MainController.modIndex;


public class ModifyProductController {

@FXML
    private AnchorPane modifyProduct;

    @FXML
    private Button lookupPart;

    @FXML
    private TextField partSearch;

    @FXML
    private TextField max;

    @FXML
    private TextField min;

    @FXML
    private TextField productID;

    @FXML
    private TextField name;

    @FXML
    private TextField inStock;

    @FXML
    private TextField price;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partsIDColumn;

    @FXML
    private TableColumn<Part, String> partsNameColumn;

    @FXML
    private TableColumn<Part, Integer> partsInventoryColumn;

    @FXML
    private TableColumn<Part, Double> partsPriceColumn;

    @FXML
    private Button addPart;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, Integer> associatedPartsIDColumn;

    @FXML
    private TableColumn<Part, String> associatedPartsNameColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartsInventoryColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartsPriceColumn;

    @FXML
    private Button deletePart;

    @FXML
    private Button saveProduct;

    @FXML
    private Button cancelScreen;
    
    private Product selectedProduct;
    private int productId;
    private boolean okClicked = false;
    private Stage dialogStage;
    private ObservableList<Part> includedParts = FXCollections.observableArrayList();
    public ObservableList<Part> modPartSearch = FXCollections.observableArrayList();
    int prodIndex = modIndex(); 
    

    @FXML
    void addPartHandler(ActionEvent event) {
        if(productValidation()){
            Part part = partsTable.getSelectionModel().getSelectedItem();
            if(part != null){
            includedParts.add(part);
            associatedPartsTable.setItems(includedParts);          
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Add Error");
                alert.setHeaderText("You must select a part to add!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void cancelScreenHandler(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to Cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() ==ButtonType.OK){
            productId = Inventory.cancelProductIDCount();
            dialogStage.close();
        } else {
            alert.close();
        }
    }
    
    @FXML
    void deletePartHandler(ActionEvent event) {
        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();
        int partSize = associatedPartsTable.getItems().size();
        if (partSize > 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete " + part.getName() + " from Associated Parts?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                includedParts.remove(part);
                associatedPartsTable.setItems(includedParts);
            } else {
            alert.close();
            }   
        } else {        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Parts Error");
        alert.setHeaderText("Cannot delete " + part.getName());
        alert.setContentText("Product must have at least one Part!\n");
        alert.showAndWait();
        }
    }

    @FXML
    void lookupPartHandler(ActionEvent event) {
        String searchItem = partSearch.getText();
        if (searchItem.equals("")){
                partsTable.setItems(getAllParts());
        } else{
            boolean found = false;
        try{
            int partNumber = Integer.parseInt(searchItem);
            for(Part p: Inventory.getAllParts()){
                if(p.getPartID() == partNumber){
                    System.out.println("This is product: " + partNumber);
                    found = true;
                    modPartSearch.clear();
                    modPartSearch.add(p);
                    
                    partsTable.setItems(modPartSearch);
                    partSearch.clear();
                }
            }
                if (found==false){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Product not found");

                    alert.showAndWait();
                    
                }
                
        }
            
         catch(NumberFormatException e){
            for(Part p: Inventory.getAllParts()){
                if(p.getName().equals(searchItem)){
                System.out.println("This is part: " + p.getName());
                found = true;
                partSearch.clear();
                modPartSearch.add(p);
                    
                partsTable.setItems(modPartSearch);
                partSearch.clear();
            }
         }    
            if (found==false){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Part not found");

                    alert.showAndWait();
                    
            }
        }
    }
    }

    @FXML
    void saveModProductHandler(ActionEvent event) {
        if(productValidation()){
       String prodID = productID.getText();
       String prodName = name.getText();
       String prodinStock = inStock.getText();
       String prodPrice = price.getText();
       String prodMin = min.getText();
       String prodMax = max.getText();
       
       
       
        Product modifiedProduct = new Product();
        modifiedProduct.setProductID(Integer.parseInt(prodID));
        modifiedProduct.setProductName(prodName);
        modifiedProduct.setProductInStock(Integer.parseInt(prodinStock));
        modifiedProduct.setProductPrice(Double.parseDouble(prodPrice));
        modifiedProduct.setProductMin(Integer.parseInt(prodMin));
        modifiedProduct.setProductMax(Integer.parseInt(prodMax));  
        ArrayList<Part> parts = new ArrayList<>();
        parts.addAll(associatedPartsTable.getItems());
        modifiedProduct.setAssociatedParts(parts);
        Inventory.updateProduct(modIndex(),modifiedProduct);
        
        okClicked = true;
        Inventory.cancelProductIDCount();
        dialogStage.close();
        }
    }
    
    public void selectedProductFill(Product product){
    selectedProduct = product;
    productID.setText(Integer.toString(product.getProductID()));
    name.setText(product.getProductName());
    price.setText(Double.toString(product.getProductPrice()));
    inStock.setText(Integer.toString(product.getProductInStock()));
    min.setText(Integer.toString(product.getProductMin()));
    max.setText(Integer.toString(product.getProductMax()));
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public boolean isOKClicked() {
        return okClicked;
    }
    
    private boolean productValidation() {
        String prodName = name.getText();
        String prodInStock = inStock.getText();
        String prodPrice = price.getText();
        String prodMin = min.getText();
        String prodMax = max.getText();
        int partSize = associatedPartsTable.getItems().size();
        ArrayList<Part> parts = new ArrayList<>();
        parts.addAll(associatedPartsTable.getItems());
   
        String errorMessage = "";
        if (prodName == null || prodName.length() == 0) {
            errorMessage += "No valid product name!\n"; 
        }
        if (prodInStock == null || prodInStock.length() == 0) {
            errorMessage += "No valid Inventory value!\n";  
        } else {
            try {
                int inStockComp = Integer.parseInt(prodInStock);
                int minComp = Integer.parseInt(prodMin);
                int maxComp = Integer.parseInt(prodMax);
                if (inStockComp < minComp || inStockComp > maxComp) {
                errorMessage += "Inventory must be between the minimum or maximum value!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "No valid Inventory value (must be an integer)!\n"; 
            }
        }
        if (prodPrice == null || prodPrice.length() == 0) {
            errorMessage += "No valid price!\n"; 
        } else {
            try {
                double productPrice = Double.parseDouble(prodPrice);
                double partPrice = 0;
                for (Part part : parts) {
                partPrice = partPrice + part.getPrice();
                }

                if (partPrice > productPrice) {
                errorMessage += "Product price must be higher than the sum of its parts!\n";
                }
                
            } catch (NumberFormatException e) {
                errorMessage += "No valid Price (must be a double)!\n"; 
            }
        }
        if (prodMin == null || prodMin.length() == 0) {
            errorMessage += "No valid Min value!\n"; 
        } else {
            try {
                int minCompare = Integer.parseInt(prodMin);
                int maxCompare = Integer.parseInt(prodMax);
                if (maxCompare < minCompare || minCompare >= maxCompare ) {
                    errorMessage += "Maximum value must be greater than Minimum!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "No valid Min value (must be an integer)!\n"; 
            }
        }        
        if (prodMax == null || prodMax.length() == 0) {
            errorMessage += "No valid Max value!\n"; 
        } else {
            try {
                int minCompare = Integer.parseInt(prodMin);
                int maxCompare = Integer.parseInt(prodMax);
                if (maxCompare < minCompare || minCompare >= maxCompare ) {
                    errorMessage += "Maximum value must be greater than Minimum!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "No valid Max value (must be an integer)!\n"; 
            }
        }
        if (partSize == 0) {
            errorMessage += "Product must have at least one Part!\n"; 
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
        partsIDColumn.setCellValueFactory(
                cellData -> cellData.getValue().partIDProperty().asObject());
        partsNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        partsInventoryColumn.setCellValueFactory(
                cellData -> cellData.getValue().inStockProperty().asObject());
        partsPriceColumn.setCellValueFactory(
                cellData -> cellData.getValue().priceProperty().asObject());
        associatedPartsIDColumn.setCellValueFactory(
                cellData -> cellData.getValue().partIDProperty().asObject());
        associatedPartsNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        associatedPartsInventoryColumn.setCellValueFactory(
                cellData -> cellData.getValue().inStockProperty().asObject());
        associatedPartsPriceColumn.setCellValueFactory(
                cellData -> cellData.getValue().priceProperty().asObject());
        partsTable.setItems(getAllParts());
        associatedPartsTable.setItems(includedParts);
    }    
    
}
