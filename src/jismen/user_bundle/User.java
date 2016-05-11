package jismen.user_bundle;

import javafx.beans.property.*;

/**
 * Created by Finelam on 24/04/2016.
 */
public class User {

    private IntegerProperty id;
    private StringProperty username;
    private StringProperty email;
    private StringProperty password;
    private StringProperty firstname;
    private StringProperty familyname;
    private StringProperty address;
    private StringProperty postalCode;
    private StringProperty city;
    private StringProperty birthday;
    private StringProperty lastLogin;
    private IntegerProperty nbConnection;
    private BooleanProperty newsletter;
    private BooleanProperty admin;

    public User(){
        this(0, "", "", "", "", "", "", "", "", "", "", 0, false, false);
    }

    public User(Integer id, String username, String email, String password, String firstname, String familyname, String address, String postalCode, String city, String birthday, String lastLogin, int nbConnection, boolean newsletter, boolean admin){
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.firstname = new SimpleStringProperty(firstname);
        this.familyname = new SimpleStringProperty(familyname);
        this.address = new SimpleStringProperty(address);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.city = new SimpleStringProperty(city);
        this.birthday = new SimpleStringProperty(birthday);
        this.lastLogin = new SimpleStringProperty(lastLogin);
        this.nbConnection = new SimpleIntegerProperty(nbConnection);
        this.newsletter = new SimpleBooleanProperty(newsletter);
        this.admin = new SimpleBooleanProperty(admin);
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

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getFamilyname() {
        return familyname.get();
    }

    public StringProperty familynameProperty() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname.set(familyname);
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

    public String getBirthday() {
        return birthday.get();
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public String getLastLogin() {
        return lastLogin.get();
    }

    public StringProperty lastLoginProperty() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin.set(lastLogin);
    }

    public int getNbConnection() {
        return nbConnection.get();
    }

    public IntegerProperty nbConnectionProperty() {
        return nbConnection;
    }

    public void setNbConnection(int nbConnection) {
        this.nbConnection.set(nbConnection);
    }

    public boolean isNewsletter() {
        return newsletter.get();
    }

    public BooleanProperty newsletterProperty() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter.set(newsletter);
    }

    public boolean isAdmin() {
        return admin.get();
    }

    public BooleanProperty adminProperty() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin.set(admin);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", email=" + email +
                ", password=" + password +
                ", firstname=" + firstname +
                ", familyname=" + familyname +
                ", address=" + address +
                ", postalCode=" + postalCode +
                ", city=" + city +
                ", birthday=" + birthday +
                ", lastLogin=" + lastLogin +
                ", nbConnection=" + nbConnection +
                ", newsletter=" + newsletter +
                ", admin=" + admin +
                '}';
    }
}
