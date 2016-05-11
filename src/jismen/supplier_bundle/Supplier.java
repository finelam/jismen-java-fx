package jismen.supplier_bundle;

import javafx.beans.property.*;
import org.json.JSONObject;

/**
 * Created by Finelam on 24/04/2016.
 */
public class Supplier {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty address;
    private StringProperty postalCode;
    private StringProperty city;
    private StringProperty contract;

    public Supplier(){
        this(0, null, null, null, null, null);
    }

    public Supplier(Integer id, String name, String address, String postalCode, String city, String contract){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.city = new SimpleStringProperty(city);
        this.contract = new SimpleStringProperty(contract);
    }

    public Supplier(JSONObject json){
        this.id = new SimpleIntegerProperty(json.getInt("id"));
        this.name = new SimpleStringProperty(json.getString("name"));
        this.address = new SimpleStringProperty(json.getString("address"));
        this.postalCode = new SimpleStringProperty(json.getString("postalCode"));
        this.city = new SimpleStringProperty(json.getString("city"));
        this.contract = new SimpleStringProperty(json.getString("contract"));
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getContract() {
        return contract.get();
    }

    public StringProperty contractProperty() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract.set(contract);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", postalCode=" + postalCode +
                ", city=" + city +
                ", contract=" + contract +
                '}';
    }
}
