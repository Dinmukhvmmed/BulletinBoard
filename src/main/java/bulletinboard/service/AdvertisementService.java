package bulletinboard.service;

import bulletinboard.dto.AdvertisementDto;
import bulletinboard.dto.AdvertisementMapper;
import bulletinboard.model.Advertisement;
import bulletinboard.model.Category;
import bulletinboard.model.Status;
import bulletinboard.model.User;
import bulletinboard.repositories.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementService {
    @Autowired
    AdvertisementRepository advertisementRepository;

    public List<AdvertisementDto> getAllAdvertisement() {
        List<AdvertisementDto> advertisementDtoList = new ArrayList<>();
        Iterable<Advertisement> advertisements = advertisementRepository.findAll();
        for (Advertisement advertisement : advertisements) {
            advertisementDtoList.add(AdvertisementMapper.mapper(advertisement));
        }
        return advertisementDtoList;
    }

    public List<AdvertisementDto> getAdvertisementWithFilter(Category category) {
        List<AdvertisementDto> advertisementDtoList = new ArrayList<>();
        Iterable<Advertisement> advertisements = advertisementRepository.findAll();
        for (Advertisement advertisement : advertisements) {
            if (advertisement.getCategory().equals(category)) {
                advertisementDtoList.add(AdvertisementMapper.mapper(advertisement));
            }
        }
        return advertisementDtoList;
    }

    public AdvertisementDto createAdvertisement(User user, String name, String description, float minPrice, Category category) {
        Advertisement advertisement = new Advertisement();
        advertisement.setUser(user);
        advertisement.setName(name);
        advertisement.setDescription(description);
        advertisement.setMinPrice(minPrice);
        advertisement.setCurrentPrice(minPrice);
        advertisement.setCategory(category);
        advertisement.setStatus(Status.ACTIVE);
        advertisement.setStatusTime(LocalDateTime.now());
        advertisementRepository.save(advertisement);
        return AdvertisementMapper.mapper(advertisement);
    }


}
