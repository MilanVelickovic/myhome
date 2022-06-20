package com.myhome.services.advertisement;

import com.myhome.models.Address;
import com.myhome.models.Advertisement;
import com.myhome.models.RealEstate;
import com.myhome.models.User;
import com.myhome.repository.AdvertisementRepository;
import com.myhome.services.address.AddressService;
import com.myhome.services.realEstate.RealEstateService;
import com.myhome.services.realEstateType.RealEstateTypeService;
import com.myhome.services.serviceType.ServiceTypeService;
import com.myhome.services.user.UserDetailsImpl;
import com.myhome.utils.AdvertisementForm;
import com.myhome.utils.HashCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    private final AddressService addressService;

    private final RealEstateService realEstateService;

    private final RealEstateTypeService realEstateTypeService;

    private final ServiceTypeService serviceTypeService;

    @Override
    public List<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    @Override
    public Advertisement findById(Integer id) {
        return advertisementRepository.findById(id).get();
    }

    @Override
    public List<Advertisement> findByUser(User user) {
        return advertisementRepository.findByUser(user).get();
    }

    @Override
    public void save(Advertisement advertisement) {
        advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement findByPublishedOn(Instant instant) {
        return advertisementRepository.findByPublishedOn(instant).get();
    }

    @Override
    public void createAdvertisement(AdvertisementForm advertisementForm) {

        User user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        Address address = new Address();
        address.setId(advertisementForm.getAddressId());
        address.setCountry(advertisementForm.getCountry());
        address.setCity(advertisementForm.getCity());
        address.setStreet(advertisementForm.getStreet());
        address.setNumber(advertisementForm.getNumber());

        addressService.save(address);

        RealEstate realEstate = new RealEstate();
        realEstate.setId(advertisementForm.getRealEstateId());
        realEstate.setType(realEstateTypeService.findById(Integer.parseInt(advertisementForm.getType())));
        realEstate.setAddress(address);
        realEstate.setSize(advertisementForm.getSize());
        realEstate.setBedrooms(advertisementForm.getBedrooms());
        realEstate.setBathrooms(advertisementForm.getBathrooms());
        realEstate.setCarSpaces(advertisementForm.getCarSpaces());

        realEstateService.save(realEstate);

        Advertisement advertisement = new Advertisement();
        advertisement.setId(advertisementForm.getAdvertisementId());
        advertisement.setTitle(advertisementForm.getTitle());
        advertisement.setDescription(advertisementForm.getDescription());
        advertisement.setUser(user);
        advertisement.setRealEstate(realEstate);
        advertisement.setService(serviceTypeService.findById(Integer.parseInt(advertisementForm.getService())));
        advertisement.setPrice(advertisementForm.getPrice());
        advertisement.setPublishedOn(Instant.now());

        this.save(advertisement);

        HashCreator hashCreator = new HashCreator();

        Advertisement createdAdvertisement = this.findByPublishedOn(advertisement.getPublishedOn());

        try {
            String path = Paths.get(".").toAbsolutePath().toString();
            path = path.substring(0, path.length() - 1) + "src/main/resources/static/fileServer/gallery/" + hashCreator.createHash(user.getEmail()) + "/" + hashCreator.createHash(createdAdvertisement.getId().toString());

            if (new File(path).mkdir()) {
                System.out.println("Advertisement directory created successfully!");
            } else {
                System.out.println("Failed to create advertisement directory!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void deleteById(Integer id) {
        advertisementRepository.deleteById(id);
    }

    @Override
    public void update(Advertisement advertisement) {
        advertisementRepository.save(advertisement);
    }

    @Override
    public void updateAdvertisement(AdvertisementForm advertisementForm) {

        User user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        Address address = new Address();
        address.setId(advertisementForm.getAddressId());
        address.setCountry(advertisementForm.getCountry());
        address.setCity(advertisementForm.getCity());
        address.setStreet(advertisementForm.getStreet());
        address.setNumber(advertisementForm.getNumber());

        addressService.update(address);

        RealEstate realEstate = new RealEstate();
        realEstate.setId(advertisementForm.getRealEstateId());
        realEstate.setType(realEstateTypeService.findById(Integer.parseInt(advertisementForm.getType())));
        realEstate.setAddress(address);
        realEstate.setSize(advertisementForm.getSize());
        realEstate.setBedrooms(advertisementForm.getBedrooms());
        realEstate.setBathrooms(advertisementForm.getBathrooms());
        realEstate.setCarSpaces(advertisementForm.getCarSpaces());

        realEstateService.update(realEstate);

        Advertisement advertisement = new Advertisement();
        advertisement.setId(advertisementForm.getAdvertisementId());
        advertisement.setTitle(advertisementForm.getTitle());
        advertisement.setDescription(advertisementForm.getDescription());
        advertisement.setRealEstate(realEstate);
        advertisement.setUser(user);
        advertisement.setService(serviceTypeService.findById(Integer.parseInt(advertisementForm.getService())));
        advertisement.setPrice(advertisementForm.getPrice());
        advertisement.setPublishedOn(Instant.now());

        this.update(advertisement);

    }


}
