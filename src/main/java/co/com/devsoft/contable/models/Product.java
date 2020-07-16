package co.com.devsoft.contable.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Character status;
    @ManyToMany
    List<Invoice> invoiceList;

    public Product() {
    }

    public Product(String name, Character status, List<Invoice> invoiceList) {
        this.name = name;
        this.status = status;
        this.invoiceList = invoiceList;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getId() == product.getId() &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getStatus(), product.getStatus()) &&
                Objects.equals(getInvoiceList(), product.getInvoiceList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStatus(), getInvoiceList());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", invoiceList=" + invoiceList +
                '}';
    }
}
