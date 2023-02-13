package bulletinboard.contoller;

import bulletinboard.dto.AdvertisementDto;
import bulletinboard.dto.DealDto;
import bulletinboard.model.Advertisement;
import bulletinboard.model.Category;
import bulletinboard.model.User;
import bulletinboard.service.AdvertisementService;
import bulletinboard.service.DealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    private final AdvertisementService advertisementService;
    private final DealService dealService;

    public ApiController(AdvertisementService advertisementService, DealService dealService) {
        this.advertisementService = advertisementService;
        this.dealService = dealService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AdvertisementDto>> allAdvertisement() {
        return new ResponseEntity(advertisementService.getAllAdvertisement(), HttpStatus.OK);
    }

    @GetMapping("/withCategory")
    public ResponseEntity<List<AdvertisementDto>> advertisementWithFilter(
            @RequestParam(name = "category") Category category) {
        if (category != null) {
            return new ResponseEntity(advertisementService.getAdvertisementWithFilter(category), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PostMapping("/create_advertisement")
    public ResponseEntity<AdvertisementDto> createAdvertisement(
            User user,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "minPrice") float minPrice,
            @RequestParam(name = "category") Category category) {
        if (user != null) {
            return new ResponseEntity(advertisementService.createAdvertisement(user, name, description, minPrice, category),
                    HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @PostMapping("/buy_advertisement")
    public ResponseEntity<DealDto> byuAdvertisement(
            User user, Advertisement advertisement,
            @RequestParam(name = "price") float yourPriceForBuy) {
        if (user != null && advertisement != null) {
            return new ResponseEntity(dealService.buyAdd(user, advertisement, yourPriceForBuy), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }
}
