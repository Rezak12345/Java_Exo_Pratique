package Model;

public class Client {

    private String email;
    private String name;
    private String phone;
    private String adresse;
    private String message;
    private String produit;

    public Client(String email, String name, String phone,
                  String adresse, String message, String produit) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.adresse = adresse;
        this.message = message;
        this.produit = produit;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMessage() {
        return message;
    }

    public String getProduit() {
        return produit;
    }
}
