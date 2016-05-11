package jismen.category_bundle;

import javafx.beans.property.*;
import org.json.JSONObject;

/**
 * Created by Finelam on 24/04/2016.
 */
public class Category {

    private IntegerProperty id;
    private StringProperty name;
    private BooleanProperty enabled;

    public Category(){
        this(0, "", false);
    }

    public Category(Integer id, String name, Boolean enabled){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.enabled = new SimpleBooleanProperty(enabled);
    }

    public Category(JSONObject json){
        this.id = new SimpleIntegerProperty(json.getInt("id"));
        this.name = new SimpleStringProperty(json.getString("name"));
        this.enabled = new SimpleBooleanProperty(json.getBoolean("enabled"));
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

    public boolean isEnabled(){
        return enabled.get();
    }

    public void setEnabled(boolean enabled){
        this.enabled.set(enabled);
    }

    public BooleanProperty enabledProperty(){
        return enabled;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name=" + name +
                ", enabled=" + enabled +
                '}';
    }
}
