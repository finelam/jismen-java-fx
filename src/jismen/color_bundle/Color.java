package jismen.color_bundle;

import javafx.beans.property.*;
import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONObject;

/**
 * Created by Finelam on 24/04/2016.
 */
public class Color {

    private IntegerProperty id;
    private StringProperty name;

    public Color(){
        this(0, "");
    }

    public Color(Integer id, String name){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public Color(JSONObject json){
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
