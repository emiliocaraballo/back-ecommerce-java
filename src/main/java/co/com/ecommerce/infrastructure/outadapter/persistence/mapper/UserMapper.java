package co.com.ecommerce.infrastructure.outadapter.persistence.mapper;

import org.springframework.stereotype.Component;

import co.com.ecommerce.application.dto.request.UserCreateDTO;
import co.com.ecommerce.application.dto.response.LoginResponse;
import co.com.ecommerce.domain.model.Person;
import co.com.ecommerce.domain.model.User;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.PersonEntity;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.UserEntity;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.UserEntity.RolStatus;


@Component
public class UserMapper {

  public UserEntity toEntity(User user) {
    UserEntity entity = new UserEntity();
    entity.setUsername(user.getUsername());
    entity.setPassword(user.getPassword());
    entity.setRol(RolStatus.valueOf(user.getRol()));

    PersonEntity person = new PersonEntity();
    person.setDocumentNumber(user.getPerson().getDocumentNumber());
    person.setDocumentType(user.getPerson().getDocumentType());
    person.setName(user.getPerson().getName());
    person.setLastName(user.getPerson().getLastName());
    person.setEmail(user.getPerson().getEmail());
    person.setPhone(user.getPerson().getPhone());

    entity.setPerson(person);
    return entity;
  }
  
  public User toDomain(UserEntity entity) {
    User user = new User();
    user.setId(entity.getId());
    user.setUsername(entity.getUsername());
    user.setPassword(entity.getPassword());
    user.setRol(entity.getRol().toString());

    Person person = new Person();
    person.setId(entity.getPerson().getId());
    person.setDocumentNumber(entity.getPerson().getDocumentNumber());
    person.setDocumentType(entity.getPerson().getDocumentType());
    person.setName(entity.getPerson().getName());
    person.setLastName(entity.getPerson().getLastName());
    person.setEmail(entity.getPerson().getEmail());
    person.setPhone(entity.getPerson().getPhone());

    user.setPerson(person);
    return user;
  }

  public User dtoToModel(UserCreateDTO dto) {
    User user = new User();
    user.setUsername(dto.getDocumentNumber());
    user.setPassword(dto.getPassword());
    user.setRol(dto.getRol());
    user.setPerson(new Person());
    user.getPerson().setDocumentNumber(dto.getDocumentNumber());
    user.getPerson().setDocumentType(dto.getDocumentType());
    user.getPerson().setName(dto.getName());
    user.getPerson().setLastName(dto.getLastName());
    user.getPerson().setEmail(dto.getEmail());
    user.getPerson().setPhone(dto.getPhone());
    return user;
  }

  public UserEntity merge(UserEntity newData, UserEntity existingData) {

    if(newData.getId() != null) {
      existingData.setId(newData.getId());
    }
    
    if (newData.getUsername() != null) {
      existingData.setUsername(newData.getUsername());
    }

    if (newData.getPassword() != null) {
      existingData.setPassword(newData.getPassword());
    }

    if (newData.getRol() != null) {
      existingData.setRol(newData.getRol());
    }

    PersonEntity person = existingData.getPerson();

    if (newData.getPerson().getId() != null) {
      person.setId(newData.getPerson().getId());
    }

    if (newData.getPerson().getDocumentNumber() != null) {
      person.setDocumentNumber(newData.getPerson().getDocumentNumber());
    }

    if (newData.getPerson().getDocumentType() != null) {
      person.setDocumentType(newData.getPerson().getDocumentType());
    }

    if (newData.getPerson().getName() != null) {
      person.setName(newData.getPerson().getName());
    }

    if (newData.getPerson().getLastName() != null) {
      person.setLastName(newData.getPerson().getLastName());
    }

    if (newData.getPerson().getEmail() != null) {
      person.setEmail(newData.getPerson().getEmail());
    }

    if (newData.getPerson().getPhone() != null) {
      person.setPhone(newData.getPerson().getPhone());
    }

    if(newData.getPerson().getCreatedAt() != null) {
      person.setCreatedAt(newData.getPerson().getCreatedAt());
    }

    if(newData.getPerson().getCreatedBy() != null) {
      person.setCreatedBy(newData.getPerson().getCreatedBy());
    }


    if(newData.getPerson().getUpdatedAt() != null) {
      person.setUpdatedAt(newData.getPerson().getUpdatedAt());
    }

    if(newData.getPerson().getUpdatedBy() != null) {
      person.setUpdatedBy(newData.getPerson().getUpdatedBy());
    }
    

    existingData.setPerson(person);

    return existingData;
  }


  public LoginResponse toLoginResponse(User user) {
    LoginResponse response = new LoginResponse();
    response.setId(user.getId());
    response.setToken("");
    response.setDocumentType(user.getPerson().getDocumentType());
    response.setDocumentNumber(user.getPerson().getDocumentNumber());
    response.setEmail(user.getPerson().getEmail());
    response.setName(user.getPerson().getName());
    response.setLastName(user.getPerson().getLastName());
    response.setPhone(user.getPerson().getPhone());
    response.setRol(user.getRol());
    return response;
  }
}
