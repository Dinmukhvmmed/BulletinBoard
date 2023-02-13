package bulletinboard.dto;

import bulletinboard.model.Advertisement;

public class AdvertisementMapper {
    public static AdvertisementDto mapper(Advertisement advertisement) {
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setName(advertisement.getName());
        advertisementDto.setDescription(advertisement.getDescription());
        advertisementDto.setMinPrice(advertisement.getMinPrice());
        advertisementDto.setDateTime(advertisement.getStatusTime().toString());
        advertisementDto.setStatus(advertisement.getStatus());
        advertisementDto.setCategory(advertisement.getCategory());
        return advertisementDto;
    }
}
