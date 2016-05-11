package jismen.utils_bundle.sample_bundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Finelam on 24/04/2016.
 */
public class Entity {

    private IntegerProperty id;
    private DoubleProperty rate;

    public Entity(){
        this(0, 0.0);
    }

    public Entity(Integer id, Double rate){
        this.id = new SimpleIntegerProperty(id);
        this.rate = new SimpleDoubleProperty(rate);
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
