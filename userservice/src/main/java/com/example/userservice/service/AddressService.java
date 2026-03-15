package com.example.userservice.service;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.entity.Address;
import com.example.userservice.entity.Users;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public List<AddressDTO> getAddresses(Long userId) {
        userService.findUserById(userId);
        return addressRepository.findByUserId(userId)
                .stream()
                .map(this::toAddressDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AddressDTO addAddress(Long userId, AddressDTO addressDTO){
        Users user= userService.findUserById(userId);

        if(addressDTO.isDefault()){
            addressRepository.clearDefaultByUserId(userId);
        }
        Address address= new Address();
        address.setUser(user);
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCountry(addressDTO.getCountry());
        address.setDefault(addressDTO.isDefault());

        return toAddressDTO(addressRepository.save(address));
    }

    @Transactional
    public void deleteAddress(Long userId, Long addressId){
        Address address = findAddressOwnedByUser(userId, addressId);
        addressRepository.delete(address);
    }

    @Transactional
    public AddressDTO setDefaultAddress(Long userId, Long addressId) {
        addressRepository.clearDefaultByUserId(userId);

        Address address = findAddressOwnedByUser(userId, addressId);
        address.setDefault(true);

        return toAddressDTO(addressRepository.save(address));
    }

    public AddressDTO getDefaultAddress(Long userId) {
        userService.findUserById(userId);
        return addressRepository.findByUserIdAndIsDefaultTrue(userId)
                .map(this::toAddressDTO)
                .orElseThrow(() -> new UserNotFoundException(
                        "No default address set for user: " + userId
                ));
    }

    public Integer countAddresses(Long userId) {
        userService.findUserById(userId);
        return addressRepository.countByUserId(userId);
    }

    private Address findAddressOwnedByUser(Long userId, Long addressId) {
        return addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "Address " + addressId + " not found for user " + userId
                ));
    }
    private AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .isDefault(address.isDefault())
                .build();
    }
}
