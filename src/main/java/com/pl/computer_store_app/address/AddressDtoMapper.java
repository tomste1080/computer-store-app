package com.pl.computer_store_app.address;

import com.pl.computer_store_app.address.dto.AddressDto;

public class AddressDtoMapper {
    public static AddressDto map(Address address) {
        return new AddressDto(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry(),
                address.getAddressType()
        );
    }
}
