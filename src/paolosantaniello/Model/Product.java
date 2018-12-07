
package paolosantaniello.Model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Product {
    
    private ArrayList<Part> associatedParts;
    private IntegerProperty productID;
    private StringProperty productName;
    private DoubleProperty productPrice;
    private IntegerProperty productInStock;
    private IntegerProperty productMin;
    private IntegerProperty productMax;
    
    //public Product
    public Product(int productID, String productName, double productPrice, int productInStock, 
            int productMin, int productMax, ArrayList<Part> associatedParts){
        this.productID = new SimpleIntegerProperty(productID);
        this.productName = new SimpleStringProperty(productName);
        this.productPrice = new SimpleDoubleProperty(productPrice);
        this.productInStock = new SimpleIntegerProperty(productInStock);
        this.productMin = new SimpleIntegerProperty(productMin);
        this.productMax = new SimpleIntegerProperty(productMax);
        this.associatedParts = new ArrayList<>(associatedParts);
    }
    
    //public Product set defaults
    public Product(){
        this.productID = new SimpleIntegerProperty(0);
        this.productName = new SimpleStringProperty("");
        this.productPrice = new SimpleDoubleProperty(0.0);
        this.productInStock = new SimpleIntegerProperty(0);
        this.productMin = new SimpleIntegerProperty(0);
        this.productMax = new SimpleIntegerProperty(0);
        this.associatedParts = new ArrayList<>();    
    }
  
    //ProductID
    public void setProductID(int productID){
        this.productID.set(productID);
    }
  
    public int getProductID(){
        return this.productID.get();
    }
    
    public IntegerProperty productIDProperty(){
        return productID;
    }
    
    //Name
    public void setProductName(String productName){
        this.productName.set(productName);
    }
    
    public String getProductName(){
        return this.productName.get();
    }
    public StringProperty productNameProperty(){
        return productName;
    }
    
    //Price
    public void setProductPrice(double productPrice){
        this.productPrice.set(productPrice);
    }
    
    public double getProductPrice(){
        return this.productPrice.get();
    }
    
    public DoubleProperty productPriceProperty(){
        return productPrice;
    }
    
    //InStock
    public void setProductInStock(int productInStock){
        this.productInStock.set(productInStock);
    }
  
    public int getProductInStock(){
        return this.productInStock.get();
    }
    
    public IntegerProperty productInStockProperty(){
        return productInStock;
    }
    
    //Min
    public void setProductMin(int productMin){
        this.productMin.set(productMin);
    }
  
    public int getProductMin(){
        return this.productMin.get();
    }
    
    public IntegerProperty productMinProperty(){
        return productMin;
    }
    //Max
    public void setProductMax(int productMax){
        this.productMax.set(productMax);
    }
  
    public int getProductMax(){
        return this.productMax.get();
    }
    
    public IntegerProperty productMaxProperty(){
        return productMax;
    }
    
    //addAssociatedPart
    public  void addAssociatedPart(Part part){
    associatedParts.add(part);
    }
    
    //removeAssociatedPart
    public boolean removeAssociatedPart(){
        return false;
    } 
    
    public void setAssociatedParts(ArrayList<Part> associatedParts){
        this.associatedParts = associatedParts;
    }
    
    public ArrayList<Part> getAssociatedParts(){
        return this.associatedParts;
    }
        
    public ObservableList<Part> getObservableAssociatedParts(){
        ObservableList<Part> parts = FXCollections.observableArrayList(this.associatedParts);
        return parts;
    }
    //lookupAssociatedPart
    public Part lookupAssociatedPart(int associatedPartNumber){
    for(Part p: getAssociatedParts()){
        if(p.getPartID() == associatedPartNumber){
            return p;
        }
    }
    return null;
    }
}            
