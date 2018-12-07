
package paolosantaniello.Model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class OutsourcedPart extends Part{
    
    private final StringProperty companyName;
    
    //creates a new inhouse part
    public OutsourcedPart(int partID, String name, double price, int inStock, int min, int max, String companyName){
        this.partID = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.companyName = new SimpleStringProperty(companyName);
    }
    
    
    public OutsourcedPart(){
        this.partID = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0.0);
        this.inStock = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.companyName = new SimpleStringProperty("");        
    }
    
    //companyName
    public void setCompanyName(String companyName){
        this.companyName.set(companyName);
    }
    
    public String getCompanyName(){
        return this.companyName.get();
    }
    
    public StringProperty companyNameProperty(){
        return companyName;
    }
}
 