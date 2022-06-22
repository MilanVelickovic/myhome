package com.myhome.main;

import com.myhome.models.Address;
import com.myhome.models.Advertisement;
import com.myhome.models.RealEstate;
import com.myhome.models.User;
import com.myhome.repository.*;
import com.myhome.services.address.AddressService;
import com.myhome.services.advertisement.AdvertisementService;
import com.myhome.services.realEstate.RealEstateService;
import com.myhome.services.realEstateType.RealEstateTypeService;
import com.myhome.services.role.RoleService;
import com.myhome.services.serviceType.ServiceTypeService;
import com.myhome.services.user.UserDetailsImpl;
import com.myhome.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class MyhomeApplicationTests {

    @MockBean
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementService advertisementService;

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private RealEstateTypeService realEstateTypeService;

    @MockBean
    private RealEstateRepository realEstateRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Test
    void findAllUsersTest() {
        List<Advertisement> advertisements = advertisementService.findAll();
        when(advertisementRepository.findAll()).thenReturn(advertisements);
        Integer expectedSize = advertisements.size();
        assertEquals(expectedSize, advertisementService.findAll().size());
    }

    @Test
    void saveUserTest() {

        User user = new User();
        user.setId(null);
        user.setEmail("test@test.com");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPhoneNumber("+123 456 789");
        user.setPassword("$2a$10$QidWQSTykYqWZHMM9OKi7uIiki1.D1lKjML0u5kLel5f6QgYjcfGC");
        user.setRole(roleService.findById(1));
        user.setAvatar("ava1");
        user.setJoinedOn(Instant.now());

        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.save(user));
    }

    @Test
    void updateUserTest() {

        User user = new User();
        user.setId(null);
        user.setEmail("new.email.test@test.com");
        user.setFirstName("Tester");
        user.setLastName("Testić");
        user.setPhoneNumber("+123 001 722");
        user.setPassword("$2a$10$QidWQSTykYqWZHMM9OKi7uIiki1.D1lKjML0u5kLel5f6QgYjcfGC");
        user.setRole(roleService.findById(1));
        user.setAvatar("ava2");
        user.setJoinedOn(Instant.now());

        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.update(user));
    }

    @Test
    void saveAdvertisementTest() {
        Address address = new Address();
        address.setId(null);
        address.setCountry("Country test");
        address.setCity("City test");
        address.setStreet("Street test");
        address.setNumber("Number test");

        RealEstate realEstate = new RealEstate();
        realEstate.setId(null);
        realEstate.setType(realEstateTypeService.findById(Integer.parseInt("1")));
        realEstate.setAddress(address);
        realEstate.setSize(100);
        realEstate.setBedrooms(3);
        realEstate.setBathrooms(1);
        realEstate.setCarSpaces(1);

        Advertisement advertisement = new Advertisement();
        advertisement.setId(null);
        advertisement.setTitle("Title test");
        advertisement.setDescription("Description test");
        advertisement.setRealEstate(realEstate);
        advertisement.setUser(new User());
        advertisement.setService(serviceTypeService.findById(Integer.parseInt("1")));
        advertisement.setPrice(new Long(1000));
        advertisement.setPublishedOn(Instant.now());

        when(addressRepository.save(address)).thenReturn(address);
        when(realEstateRepository.save(realEstate)).thenReturn(realEstate);
        when(advertisementRepository.save(advertisement)).thenReturn(advertisement);
        assertEquals(advertisement, advertisementService.save(advertisement));
    }

}