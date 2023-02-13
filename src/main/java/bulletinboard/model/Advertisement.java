package bulletinboard.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String description;

    @Column(name = "min_price", columnDefinition = "FLOAT", nullable = false)
    private float minPrice;

    @Column(name = "current_price", columnDefinition = "FLOAT", nullable = false)
    private float currentPrice;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE')", nullable = false)
    private Status status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('HOME', 'PHARMACY', 'CLOTH', 'SPORT', 'TECHNIQUE', 'LEISURE')", nullable = false)
    private Category category;

    @Column(columnDefinition = "DATETIME", name = "status_time", nullable = false)
    private LocalDateTime statusTime;

    private boolean hasBuyer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(LocalDateTime statusTime) {
        this.statusTime = statusTime;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean isHasBuyer() {
        return hasBuyer;
    }

    public void setHasBuyer(boolean hasBuyer) {
        this.hasBuyer = hasBuyer;
    }
}
