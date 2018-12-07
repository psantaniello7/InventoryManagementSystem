
package paolosantaniello.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class InhousePart extends Part{
    
    private final IntegerProperty machineID;
    
    //creates a new inhouse part
    public InhousePart(int partID, String name, double price, int inStock, int min, int max, int machineID){
        this.partID = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.machineID = new SimpleIntegerProperty(machineID);
    }
    
    
    public InhousePart(){
        this.partID = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0.0);
        this.inStock = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.machineID = new SimpleIntegerProperty(0);        
    }
    
    //machineID
    public void setMachineID(int machineID){
        this.machineID.set(machineID);
    }
    
    public int getMachineID(){
        return this.machineID.get();
    }
    
    public IntegerProperty machineIDProperty(){
        return machineID;
    }
}
 