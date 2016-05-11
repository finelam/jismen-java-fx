package jismen.tva_bundle;

import javafx.beans.property.*;
import org.json.JSONObject;

/**
 * Created by Finelam on 24/04/2016.
 */
public class Tva {

    private IntegerProperty id;
    private DoubleProperty rate;

    public Tva(){
        this(0, 0.0);
    }

    public Tva(Integer id, Double rate){
        this.id = new SimpleIntegerProperty(id);
        this.rate = new SimpleDoubleProperty(rate);
    }

    public Tva(JSONObject json) {
        this.id = new SimpleIntegerProperty(json.getInt("id"));
        this.rate = new SimpleDoubleProperty(json.getDouble("rate"));
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

    public double getRate(){
        return rate.get();
    }

    public void setRate(double rate){
        this.rate.set(rate);
    }

    public DoubleProperty rateProperty(){
        return rate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", rate=" + rate +
                '}';
    }
}
