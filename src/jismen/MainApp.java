package jismen;/**
 * Created by Finelam on 24/04/2016.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import jismen.category_bundle.CategoryMngr;
import jismen.color_bundle.ColorMngr;
import jismen.login_bundle.LoginMngr;
import jismen.product_bundle.ProductMngr;
import jismen.size_bundle.SizeMngr;
import jismen.stat_bundle.stat_product_bundle.StatProductMngr;
import jismen.stat_bundle.stat_supplier_bundle.StatSupplierMngr;
import jismen.stat_bundle.stat_user_bundle.StatUserMngr;
import jismen.subcat_bundle.SubcatMngr;
import jismen.supplier_bundle.SupplierMngr;
import jismen.tva_bundle.TvaMngr;
import jismen.user_bundle.UserMngr;
import jismen.utils_bundle.LayoutMngr;

public class MainApp extends Application {

    private Stage primaryStage;

    private LayoutMngr layoutMngr;
    private CategoryMngr categoryMngr;
    private ColorMngr colorMngr;
    private LoginMngr loginMngr;
    private ProductMngr productMngr;
    private SizeMngr sizeMngr;
    private SubcatMngr subcatMngr;
    private SupplierMngr supplierMngr;
    private TvaMngr tvaMngr;
    private UserMngr userMngr;
    private StatUserMngr statUserMngr;
    private StatProductMngr statProductMngr;
    private StatSupplierMngr statSupplierMngr;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage   = primaryStage;
        loginMngr           = new LoginMngr(this);
        layoutMngr          = new LayoutMngr(this);
        categoryMngr        = new CategoryMngr(this);
        colorMngr           = new ColorMngr(this);
        productMngr         = new ProductMngr(this);
        sizeMngr            = new SizeMngr(this);
        subcatMngr          = new SubcatMngr(this);
        supplierMngr        = new SupplierMngr(this);
        tvaMngr             = new TvaMngr(this);
        userMngr            = new UserMngr(this);
        statUserMngr        = new StatUserMngr(this);
        statProductMngr    = new StatProductMngr(this);
        statSupplierMngr    = new StatSupplierMngr(this);

        loginMngr.initLogin();
//        layoutMngr.initLayout();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public LayoutMngr getLayoutMngr() {
        return layoutMngr;
    }

    public void setLayoutMngr(LayoutMngr layoutMngr) {
        this.layoutMngr = layoutMngr;
    }

    public CategoryMngr getCategoryMngr() {
        return categoryMngr;
    }

    public void setCategoryMngr(CategoryMngr categoryMngr) {
        this.categoryMngr = categoryMngr;
    }

    public ColorMngr getColorMngr() {
        return colorMngr;
    }

    public void setColorMngr(ColorMngr colorMngr) {
        this.colorMngr = colorMngr;
    }

    public LoginMngr getLoginMngr() {
        return loginMngr;
    }

    public void setLoginMngr(LoginMngr loginMngr) {
        this.loginMngr = loginMngr;
    }

    public ProductMngr getProductMngr() {
        return productMngr;
    }

    public void setProductMngr(ProductMngr productMngr) {
        this.productMngr = productMngr;
    }

    public SizeMngr getSizeMngr() {
        return sizeMngr;
    }

    public void setSizeMngr(SizeMngr sizeMngr) {
        this.sizeMngr = sizeMngr;
    }

    public SubcatMngr getSubcatMngr() {
        return subcatMngr;
    }

    public void setSubcatMngr(SubcatMngr subcatMngr) {
        this.subcatMngr = subcatMngr;
    }

    public SupplierMngr getSupplierMngr() {
        return supplierMngr;
    }

    public void setSupplierMngr(SupplierMngr supplierMngr) {
        this.supplierMngr = supplierMngr;
    }

    public TvaMngr getTvaMngr() {
        return tvaMngr;
    }

    public void setTvaMngr(TvaMngr tvaMngr) {
        this.tvaMngr = tvaMngr;
    }

    public UserMngr getUserMngr() {
        return userMngr;
    }

    public void setUserMngr(UserMngr userMngr) {
        this.userMngr = userMngr;
    }

    public StatUserMngr getStatUserMngr() {
        return statUserMngr;
    }

    public void setStatUserMngr(StatUserMngr statUserMngr) {
        this.statUserMngr = statUserMngr;
    }

    public StatSupplierMngr getStatSupplierMngr() {
        return statSupplierMngr;
    }

    public void setStatSupplierMngr(StatSupplierMngr statSupplierMngr) {
        this.statSupplierMngr = statSupplierMngr;
    }

    public StatProductMngr getStatProductMngr() {
        return statProductMngr;
    }

    public void setStatProductMngr(StatProductMngr statProductMngr) {
        this.statProductMngr = statProductMngr;
    }
}
