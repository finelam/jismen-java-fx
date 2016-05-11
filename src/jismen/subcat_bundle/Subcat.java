package jismen.subcat_bundle;

import javafx.beans.property.*;
import jismen.category_bundle.Category;
import org.json.JSONObject;

/**
 * Created by Finelam on 24/04/2016.
 */
public class Subcat {

    private IntegerProperty id;
    private StringProperty name;
    private Category category;
    private BooleanProperty enabled;

    public Subcat() {
        this(0, "", null, false);
    }

    public Subcat(int id, String name, Category category, boolean enabled) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.category = category;
        this.enabled = new SimpleBooleanProperty(enabled);
    }

    public Subcat(JSONObject json) {
        this.id = new SimpleIntegerProperty(json.getInt("id"));
        this.name = new SimpleStringProperty(json.getString("name"));
        this.category = new Category(json.getJSONObject("category"));
        this.enabled = new SimpleBooleanProperty(json.getBoolean("enabled"));
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isEnabled() {
        return enabled.get();
    }

    public BooleanProperty enabledProperty() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled.set(enabled);
    }

    @Override
    public String toString() {
        return "Subcat{" +
                "id=" + id +
                ", name=" + name +
                ", category=" + category +
                ", enabled=" + enabled +
                '}';
    }
}
