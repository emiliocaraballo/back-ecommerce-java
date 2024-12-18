package co.com.ecommerce.infrastructure.inadapter.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.ecommerce.application.business.interfaces.UserBusiness;
import co.com.ecommerce.application.dto.request.LoginRequestDTO;
import co.com.ecommerce.application.dto.request.UserCreateDTO;
import co.com.ecommerce.application.dto.response.FrequentCustomersTopDto;
import co.com.ecommerce.application.dto.response.LoginResponse;
import co.com.ecommerce.domain.model.User;
import co.com.ecommerce.infrastructure.outadapter.persistence.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${application.request.mappings}/v1/user")
@Tag(name = "user", description = "User")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
@Slf4j
public class UserController {
    private final UserBusiness userBusiness;
    private final UserMapper userMapper;


    @PostMapping(value = "create", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(
        @Validated @RequestBody UserCreateDTO body
    ) {
        log.info("start process: Creating user DTO: {}", body);
        User user = userMapper.dtoToModel(body);
        User response = userBusiness.createUser(user);
        return Mono.just(response);
        
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Mono<LoginResponse> login(
        @Validated @RequestBody LoginRequestDTO body
    ){
        log.info("start process: Login user DTO: {}", body);
        User response = userBusiness.login(body);
        return Mono.just(userMapper.toLoginResponse(response));
    }


    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> update(
        @Validated @RequestBody UserCreateDTO body,
        @PathVariable Long id
    ){
        log.info("start process: Update user DTO: {}", body);
        User user = userMapper.dtoToModel(body);
        user.setId(id);
        User response = userBusiness.updatedUser(user);
        return Mono.just(response);
    }

    @GetMapping( value = "/list")
    public Mono<List<LoginResponse>> getList(){
        List<LoginResponse> list = new ArrayList<>();
        List<User> result = userBusiness.findAll();
        if(result.size() > 0) {
            list.addAll(result.stream().map(userMapper::toLoginResponse).toList());
        }
        return Mono.just(list);
    }

    @GetMapping( value = "/frequent-customers")
    public Mono<List<FrequentCustomersTopDto>> getFrequentCustomers(){
        List<FrequentCustomersTopDto> list = new ArrayList<>();

        List<FrequentCustomersTopDto> result = userBusiness.findFrequentCustomersTop();
        if(result.size() > 0) {
            list.addAll(result);
        }
        return Mono.just(list);
    }
}
