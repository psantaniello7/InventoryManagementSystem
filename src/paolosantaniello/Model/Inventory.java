
package paolosantaniello.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    
    private static ObservableList<Product> products = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static int partIDCount = 0;
    private static int productIDCount = 0;
    
//Parts
public static ObservableList<Part> getAllParts(){
    return allParts;
}

//adds part to Part inventory
public static void addPart(Part part){
    allParts.add(part);
}

//deletes part from Part inventory
public static void deletePart(Part part){
    allParts.remove(part);
}


//update part in Part inventory
public static void updatePart(int index, Part part){
    allParts.set(index, part);
}

//lookup part in Part inventory
public static Part lookupPart(int partNumber){
    for(Part p: getAllParts()){
        if(p.getPartID()== partNumber){
            return p;
        }
    }
    return null;
}

public static int getPartIDCount(){
    partIDCount++;
    return partIDCount;
}

public static int cancelPartIDCount(){
    partIDCount--;
    return partIDCount;
}


//Products
public static ObservableList<Product> getProducts(){
    return products;
}

//add product to Product inventory
public static void addProduct(Product product){
    products.add(product);
}

//deletes product from Product inventory
public static void removeProduct(Product product){
    products.remove(product);
}



//updates product in Product inventory
public static void updateProduct(int index, Product product){
    products.set(index, product);
}

//lookup product in Product inventory
public static Product lookupProduct(int productNumber){
    for(Product p: getProducts()){
        if(p.getProductID() == productNumber){
            return p;
        }
    }
    return null;
}

public static int getProductIDCount(){
    productIDCount++;
    return productIDCount;
}

public static int cancelProductIDCount(){
    productIDCount--;
    return productIDCount;
}
}
