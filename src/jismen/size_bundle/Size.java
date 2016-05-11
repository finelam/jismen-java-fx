package jismen.size_bundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.json.JSONObject;

/**
 * Created by Finelam on 24/04/2016.
 */
public class Size {

    private IntegerProperty id;
    private StringProperty name;

    public Size(){
        this(0, "");
    }

    public Size(Integer id, String name){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public Size(JSONObject json){
        this.id = new SimpleIntegerProperty(json.getInt("id"));
        this.name = new SimpleStringProperty(json.getString("name"));
    }

    public int getId(){
        return id.get();
    }

    public void setId(int id){
        this.id.set(id);
    }

    public IntegerProperty idProperty(){
        return id;
    }

    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public StringProperty nameProperty(){
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
