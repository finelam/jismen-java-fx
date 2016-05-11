package jismen.product_bundle;

import javafx.beans.property.*;
import jismen.color_bundle.Color;
import jismen.size_bundle.Size;
import jismen.subcat_bundle.Subcat;
import jismen.supplier_bundle.Supplier;
import jismen.tva_bundle.Tva;

/**
 * Created by Finelam on 24/04/2016.
 */
public class Product {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty description;
    private IntegerProperty quantity;
    private StringProperty path;
    private DoubleProperty priceHt;
    private BooleanProperty enabled;
    private BooleanProperty vip;
    private BooleanProperty promo;
    private BooleanProperty news;
    private Tva tva;
    private Color color;
    private Size size;
    private Supplier supplier;
    private Subcat subcat;

    public Product() {
        this(0, "", "", 0, "", 0, false, false, false, false, null, null, null, null, null);
    }

    public Product(int id, String name, String description, int quantity, String path, double priceHt, boolean enabled, boolean vip, boolean promo, boolean news, Tva tva, Color color, Size size, Supplier supplier, Subcat subcat) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.path = new SimpleStringProperty(path);
        this.priceHt = new SimpleDoubleProperty(priceHt);
        this.enabled = new SimpleBooleanProperty(enabled);
        this.vip = new SimpleBooleanProperty(vip);
        this.promo = new SimpleBooleanProperty(promo);
        this.news = new SimpleBooleanProperty(news);
        this.tva = tva;
        this.color = color;
        this.size = size;
        this.supplier = supplier;
        this.subcat = subcat;
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

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public double getPriceHt() {
        return priceHt.get();
    }

    public DoubleProperty priceHtProperty() {
        return priceHt;
    }

    public void setPriceHt(double priceHt) {
        this.priceHt.set(priceHt);
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

    public boolean isVip() {
        return vip.get();
    }

    public BooleanProperty vipProperty() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip.set(vip);
    }

    public boolean isPromo() {
        return promo.get();
    }

    public BooleanProperty promoProperty() {
        return promo;
    }

    public void setPromo(boolean promo) {
        this.promo.set(promo);
    }

    public boolean isNews() {
        return news.get();
    }

    public BooleanProperty newsProperty() {
        return news;
    }

    public void setNews(boolean news) {
        this.news.set(news);
    }

    public Tva getTva() {
        return tva;
    }

    public void setTva(Tva tva) {
        this.tva = tva;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Subcat getSubcat() {
        return subcat;
    }

    public void setSubcat(Subcat subcat) {
        this.subcat = subcat;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", quantity=" + quantity +
                ", path=" + path +
                ", priceHt=" + priceHt +
                ", enabled=" + enabled +
                ", vip=" + vip +
                ", promo=" + promo +
                ", news=" + news +
                ", tva=" + tva +
                ", color=" + color +
                ", size=" + size+
                ", supplier=" + supplier +
                ", subcat=" + subcat +
                '}';
    }
}

