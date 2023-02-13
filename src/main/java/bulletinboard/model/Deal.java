package bulletinboard.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_owner_id", referencedColumnName = "id")
    private User userOwner;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_buyer_id", referencedColumnName = "id")
    private User userBuyer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "advertisement_id", referencedColumnName = "id")
    private Advertisement advertisement;

    @Column(columnDefinition = "DATETIME", name = "deal_time", nullable = false)
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PENDING', 'PURCHASED', 'INTERRUPTED')", nullable = false)
    private DealStatus dealStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public User getUserBuyer() {
        return userBuyer;
    }

    public void setUserBuyer(User userBuyer) {
        this.userBuyer = userBuyer;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }
}
