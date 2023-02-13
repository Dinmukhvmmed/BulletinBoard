package bulletinboard.dto;

import bulletinboard.model.Deal;

public class DealMapper {
    public static DealDto mapper(Deal deal) {
        DealDto dealDto = new DealDto();
        dealDto.setUserOwner(deal.getUserOwner().getEmail());
        dealDto.setUserBuyer(deal.getUserBuyer().getEmail());
        dealDto.setDealPrice(deal.getAdvertisement().getCurrentPrice());
        dealDto.setMinPrice(deal.getAdvertisement().getMinPrice());
        dealDto.setDateTime(deal.getLocalDateTime().toString());
        dealDto.setDealStatus(deal.getDealStatus());
        return dealDto;
    }
}
