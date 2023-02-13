package bulletinboard.service;

import bulletinboard.dto.DealDto;
import bulletinboard.dto.DealMapper;
import bulletinboard.model.*;
import bulletinboard.repositories.AdvertisementRepository;
import bulletinboard.repositories.DealRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class DealService {
    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    DealRepository dealRepository;

    private static Logger logger = LogManager.getRootLogger();

    public DealDto buyAdd(User userBuyer, Advertisement advertisement, float price) {
        if (price > advertisement.getCurrentPrice() && advertisement.getStatus().equals(Status.ACTIVE)) {
            try {
                Deal deal = createDeal(userBuyer, advertisement, price);
                Thread.sleep(5000);
                if (price >= advertisement.getCurrentPrice()) {
                    logger.info(userBuyer.getEmail() + " - Поздравляем вы приобрели товар за " + price + " !!!");
                    logger.info(advertisement.getUser().getEmail() + " - Поздравляем вы продали товар за " + price + " !!!");
                    successfulDeal(deal, userBuyer, advertisement);
                } else {
                    unsuccessfulDeal(deal);
                    logger.info(userBuyer.getEmail() + " - Цена перебита, минимальная цена составляет " +
                            advertisement.getCurrentPrice() + " !!!");
                    logger.info(advertisement.getUser().getEmail() + " - Цена перебита, минимальная цена составляет " +
                            advertisement.getCurrentPrice() + " !!!");
                }
                return DealMapper.mapper(deal);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (price <= advertisement.getCurrentPrice()) {
            logger.warn(userBuyer.getEmail() + " - Неприемлемая цена за товар");
        } else if (advertisement.getStatus().equals(Status.INACTIVE)) {
            logger.warn(userBuyer.getEmail() + " - Товар в данное время недоступен");
        }
        return null;
    }

    private Deal createDeal(User userBuyer, Advertisement advertisement, float price) {
        Deal deal = new Deal();
        advertisement.setHasBuyer(true);
        advertisement.setStatusTime(LocalDateTime.now());
        advertisement.setCurrentPrice(price);
        deal.setAdvertisement(advertisement);
        deal.setUserBuyer(userBuyer);
        deal.setUserOwner(advertisement.getUser());
        deal.setDealStatus(DealStatus.PENDING);
        advertisementRepository.save(advertisement);
        dealRepository.save(deal);
        return deal;
    }

    private void successfulDeal(Deal deal, User userBuyer, Advertisement advertisement) {
        deal.setDealStatus(DealStatus.PURCHASED);
        advertisement.setUser(userBuyer);
        advertisement.setStatus(Status.INACTIVE);
        advertisement.setStatusTime(LocalDateTime.now());
        advertisementRepository.save(advertisement);
        dealRepository.save(deal);
    }

    private void unsuccessfulDeal(Deal deal) {
        deal.setDealStatus(DealStatus.INTERRUPTED);
        dealRepository.save(deal);
    }
}
