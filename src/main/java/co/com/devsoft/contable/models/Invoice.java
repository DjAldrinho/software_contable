package co.com.devsoft.contable.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 20)
    private String invoice;
    @Column(nullable = false, length = 30)
    private String customer;
    @Column(nullable = false)
    private String details;
    @ManyToMany
    List<Product> productsList;


    public Invoice() {
    }

    public Invoice(String invoice, String customer, String details, List<Product> productsList) {
        this.invoice = invoice;
        this.customer = customer;
        this.details = details;
        this.productsList = productsList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice1 = (Invoice) o;
        return getId() == invoice1.getId() &&
                Objects.equals(getInvoice(), invoice1.getInvoice()) &&
                Objects.equals(getCustomer(), invoice1.getCustomer()) &&
                Objects.equals(getDetails(), invoice1.getDetails()) &&
                Objects.equals(productsList, invoice1.productsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInvoice(), getCustomer(), getDetails(), productsList);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoice='" + invoice + '\'' +
                ", customer='" + customer + '\'' +
                ", details='" + details + '\'' +
                ", productsList=" + productsList +
                '}';
    }
}
