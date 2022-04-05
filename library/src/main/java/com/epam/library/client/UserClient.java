package com.epam.library.client;

import com.epam.library.dtos.UserDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@LoadBalancerClient(name="user-service")
@FeignClient(name = "user-service",fallback = UserClientImpl.class)
public interface UserClient {

    @GetMapping("users")
    public List<UserDto> getAllUsers();

    @GetMapping("users/{userName}")
    public UserDto getAUser(@PathVariable String userName);

    @PostMapping("users")
    public UserDto addAUser(@RequestBody UserDto userDto);

    @DeleteMapping("users/{userName}")
    public void removeAUser(@PathVariable String userName);

    @PutMapping("users/{userName}")
    public UserDto updateAUser(@RequestBody UserDto userDto, @PathVariable String userName);
}