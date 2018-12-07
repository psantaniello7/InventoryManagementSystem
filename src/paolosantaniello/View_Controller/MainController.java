/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paolosantaniello.View_Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import paolosantaniello.Model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import static paolosantaniello.Model.Inventory.deletePart;
import paolosantaniello.PaoloSantaniello;
import static paolosantaniello.Model.Inventory.getAllParts;
import static paolosantaniello.Model.Inventory.getPartIDCount;
import static paolosantaniello.Model.Inventory.getProducts;
import static paolosantaniello.Model.Inventory.getProductIDCount;
import static paolosantaniello.Model.Inventory.removeProduct;
import paolosantaniello.Model.Part;


public class MainController {

@FXML
    private AnchorPane mainView;

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
    private Button MAINSearchParts;

    @FXML
    private TextField MAINSearchPartText;

    @FXML
    private Button MAINAddParts;

    @FXML
    private Button MAINModParts;

    @FXML
    private Button MAINDelParts;

    @FXML
    private Button MAINExit;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> productsIDColumn;

    @FXML
    private TableColumn<Product, String> productsNameColumn;

    @FXML
    private TableColumn<Product, Integer> productsInventoryColumn;

    @FXML
    private TableColumn<Product, Double> productsPriceColumn;

    @FXML
    private Button MAINSearchProd;

    @FXML
    private TextField MAINSearchProdText;

    @FXML
    private Button MAINAddProd;

    @FXML
    private Button MAINModProd;

    @FXML
    private Button MAINDelProd;
    
    public ObservableList<Part> partSearch = FXCollections.observableArrayList();
    public ObservableList<Product> productSearch = FXCollections.observableArrayList();
    private static int index;
    boolean found = false;
    private PaoloSantaniello mainApp;
    

    
    
    void currentPartsInventory(){
        int partID = Inventory.getPartIDCount();
        
        Inventory.addPart(new InhousePart
        (partID, "Alloy Gear", 3.50, 200, 100, 1000, 4528));
        Inventory.addPart(new OutsourcedPart
        (getPartIDCount(), "Stepper Motor", 7, 250, 50, 500, "Rotory Inc."));
        Inventory.addPart(new OutsourcedPart
        (getPartIDCount(), "LED Diode", .73, 1000, 250, 100000, "Illuminate.LTD"));

        
    }
    
    void currentProductsInventory(){
        int productID = Inventory.getProductIDCount();
        ArrayList<Part> addParts1 = new ArrayList<>();
        addParts1.add(Inventory.lookupPart(1));
        Inventory.addProduct(new Product
        (productID,"USB Fan", 33, 10, 0, 53, addParts1));
         ArrayList<Part> addParts2 = new ArrayList<>();
        addParts2.add(Inventory.lookupPart(2));
        Inventory.addProduct(new Product
        (getProductIDCount(),"Flux Capacitor", 1001.21, 2, 1, 5, addParts2));
        
        
    }
 
    public static int modIndex(){
        return index;
    }
    @FXML
    void MAINAddPartsHandler(ActionEvent event)  throws IOException{

        boolean okClicked = mainApp.showAddPartScreen();
       
    }

    @FXML
    void MAINAddProdHandler(ActionEvent event) throws Exception{
        boolean okClicked = mainApp.showAddProdScreen();
    }

    @FXML
    void MAINDelPartsHandler(ActionEvent event) {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        if (part != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part");
            alert.setHeaderText("Are you sure you want to delete " + part.getName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                deletePart(part);
            } else {
                alert.close();
            }
        }
    }

    @FXML
    void MAINDelProdHandler(ActionEvent event) {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setHeaderText("Are you sure you want to delete " + product.getProductName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                removeProduct(product);
            } else {
                alert.close();
            }
        }
    }

    @FXML
    void MAINExitHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Please confirm you want to exit.");
        alert.setContentText("All unsaved changes will be lost.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
        else {
            System.out.println("You canceled our request.");
        }
    }

    @FXML
    void MAINModPartsHandler(ActionEvent event) throws Exception{
        Part selectPart = partsTable.getSelectionModel().getSelectedItem();
        index = getAllParts().indexOf(selectPart);
        if (selectPart != null){
            boolean saveClicked = mainApp.showModPartScreen(selectPart);
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part.");
            alert.showAndWait();            
        }
    }

    @FXML
    void MAINModProdHandler(ActionEvent event) throws Exception{
        Product selectProduct = productsTable.getSelectionModel().getSelectedItem();
        index = getProducts().indexOf(selectProduct);
        if (selectProduct != null){
            boolean saveClicked = mainApp.showModProductScreen(selectProduct);
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product.");
            alert.showAndWait();            
        }        
    }

    @FXML
    void MAINSearchPartsHandler(ActionEvent event) {
    String searchItem = MAINSearchPartText.getText();
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
                    partSearch.clear();
                    partSearch.add(p);
                    
                    partsTable.setItems(partSearch);
                    MAINSearchPartText.clear();
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
                partSearch.add(p);
                    
                partsTable.setItems(partSearch);
                MAINSearchPartText.clear();
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
    void MAINSearchProdHandler(ActionEvent event) {
        String searchItem = MAINSearchProdText.getText();
        if (searchItem.equals("")){
                productsTable.setItems(getProducts());
        } else{
            boolean found = false;
        try{
            int productNumber = Integer.parseInt(searchItem);
            Product p = Inventory.lookupProduct(productNumber);
            if( p != null){
                
                found = true;
                productSearch.clear();
                productSearch.add(p);
                productsTable.setItems(productSearch);
                MAINSearchProdText.clear();
                
            }
                if (found==false){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Part not found");

                    alert.showAndWait();
                    
                }
                
        }
            
         catch(NumberFormatException e){
            for(Product p: Inventory.getProducts()){
                if(p.getProductName().equals(searchItem)){
                System.out.println("This is part: " + p.getProductID());
                found = true;
                
                
                productSearch.clear();
                productSearch.add(p);
                    
                productsTable.setItems(productSearch);
                MAINSearchProdText.clear();
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
    public void initialize() {
        partsIDColumn.setCellValueFactory(
                cellData -> cellData.getValue().partIDProperty().asObject());
        partsNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        partsInventoryColumn.setCellValueFactory(
                cellData -> cellData.getValue().inStockProperty().asObject());
        partsPriceColumn.setCellValueFactory(
                cellData -> cellData.getValue().priceProperty().asObject());
        productsIDColumn.setCellValueFactory(
                cellData -> cellData.getValue().productIDProperty().asObject());
        productsNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().productNameProperty());
        productsInventoryColumn.setCellValueFactory(
                cellData -> cellData.getValue().productInStockProperty().asObject());
        productsPriceColumn.setCellValueFactory(
                cellData -> cellData.getValue().productPriceProperty().asObject());
    }    

public void setMainApp(PaoloSantaniello mainApp){
    this.mainApp = mainApp;
    currentPartsInventory();
    partsTable.setItems(Inventory.getAllParts());
    currentProductsInventory();
    productsTable.setItems(Inventory.getProducts());
    
}

    private void deleteProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
}
