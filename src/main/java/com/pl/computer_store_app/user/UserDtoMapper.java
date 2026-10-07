package com.pl.computer_store_app.user;

import com.pl.computer_store_app.address.AddressDtoMapper;
import com.pl.computer_store_app.user.dto.UserDto;

public class UserDtoMapper {
    public static UserDto map(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getUserRole(),
                AddressDtoMapper.map(user.getAddress())
        );
    }
}
