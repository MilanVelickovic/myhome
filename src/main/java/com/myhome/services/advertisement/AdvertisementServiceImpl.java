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
import com.myhome.utils.forms.AdvertisementForm;
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
        System.out.println(advertisementRepository.findById(id).get().getTitle());
        return advertisementRepository.findById(id).get();
    }

    @Override
    public List<Advertisement> findByUser(User user) {
        return advertisementRepository.findByUser(user).get();
    }

    @Override
    public Advertisement save(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
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

        RealEstate realEstate = new RealEstate();
        realEstate.setId(advertisementForm.getRealEstateId());
        realEstate.setType(realEstateTypeService.findById(Integer.parseInt(advertisementForm.getType())));
        realEstate.setAddress(address);
        realEstate.setSize(advertisementForm.getSize());
        realEstate.setBedrooms(advertisementForm.getBedrooms());
        realEstate.setBathrooms(advertisementForm.getBathrooms());
        realEstate.setCarSpaces(advertisementForm.getCarSpaces());

        Advertisement advertisement = new Advertisement();
        advertisement.setId(advertisementForm.getAdvertisementId());
        advertisement.setTitle(advertisementForm.getTitle());
        advertisement.setDescription(advertisementForm.getDescription());
        advertisement.setUser(user);
        advertisement.setRealEstate(realEstate);
        advertisement.setService(serviceTypeService.findById(Integer.parseInt(advertisementForm.getService())));
        advertisement.setPrice(advertisementForm.getPrice());
        advertisement.setPublishedOn(Instant.now());

        addressService.save(address);
        realEstateService.save(realEstate);
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
    public void deleteAdvertisementById(Integer id) {

        Advertisement advertisement = this.findById(id);

        this.deleteById(advertisement.getId());
        realEstateService.deleteById(advertisement.getRealEstate().getId());
        addressService.deleteById(advertisement.getRealEstate().getAddress().getId());

        User user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        if (!advertisement.getUser().equals(user)) {
            user = advertisement.getUser();
        }

        HashCreator hashCreator = new HashCreator();

        try {
            String path = Paths.get(".").toAbsolutePath().toString();
            path = path.substring(0, path.length() - 1) + "src/main/resources/static/fileServer/gallery/" + hashCreator.createHash(user.getEmail()) + "/" + hashCreator.createHash(advertisement.getId().toString());

            System.out.println(path);

            if (new File(path).delete()) {
                System.out.println("Advertisement directory deleted successfully!");
            } else {
                System.out.println("Failed to delete advertisement directory!");
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
    public Advertisement update(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement update(AdvertisementForm advertisementForm) {

        Advertisement oldVersionAdvertisement = this.findById(advertisementForm.getAdvertisementId());

        User user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        if (!oldVersionAdvertisement.getUser().equals(user)) {
            user = oldVersionAdvertisement.getUser();
        }

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

        return this.update(advertisement);

    }


}
