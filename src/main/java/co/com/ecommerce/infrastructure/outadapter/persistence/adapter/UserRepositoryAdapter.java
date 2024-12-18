package co.com.ecommerce.infrastructure.outadapter.persistence.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ecommerce.application.dto.request.LoginRequestDTO;
import co.com.ecommerce.application.dto.response.FrequentCustomersTopDto;
import co.com.ecommerce.domain.model.User;
import co.com.ecommerce.domain.port.outound.UserRepositoryPort;
import co.com.ecommerce.infrastructure.outadapter.persistence.dto.FrequentCustomersTopProjection;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.PersonEntity;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.UserEntity;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.UserEntity.RolStatus;
import co.com.ecommerce.infrastructure.outadapter.persistence.mapper.UserMapper;
import co.com.ecommerce.infrastructure.outadapter.persistence.repository.PersonJpaRepository;
import co.com.ecommerce.infrastructure.outadapter.persistence.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserRepositoryAdapter implements UserRepositoryPort {
  private final UserJpaRepository jpaRepository;
  private final PersonJpaRepository personJpaRepository;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public User create(User user) {
    UserEntity entity = userMapper.toEntity(user);

    PersonEntity person = entity.getPerson();
    PersonEntity personEntity = personJpaRepository.save(person);

    entity.setPerson(personEntity);

    UserEntity savedEntity = jpaRepository.save(entity);
    return userMapper.toDomain(savedEntity);
  }

  @Override
  public User login(LoginRequestDTO login) {
    String rol = login.getRol();
    if(rol == null || rol.isEmpty()) {
      rol = "CLIENT";
    }
    RolStatus rolStatus = RolStatus.valueOf(rol);
    UserEntity response = jpaRepository.findByUsernameAndPasswordAndRol(login.getUsername(), login.getPassword(), rolStatus);
    return userMapper.toDomain(response);
  }

  @Override
  public User findByUsername(String username) {
    UserEntity response = jpaRepository.findByUsername(username);
    return userMapper.toDomain(response);
  }

  @Override
  public List<User> findAll() {
    return jpaRepository.findAll().stream().map(userMapper::toDomain).toList();
  }

  @Override
  @Transactional
  public User updated(User user) {
    UserEntity entity = userMapper.toEntity(user);

    UserEntity result = jpaRepository.findById(user.getId()).orElse(null);
    
    Long personId = result.getPerson().getId();
    UserEntity merge = userMapper.merge(entity, result);

    PersonEntity person = merge.getPerson();
    person.setId(personId);

    PersonEntity personEntity = personJpaRepository.save(person);

    merge.setPerson(personEntity);

    UserEntity savedEntity = jpaRepository.save(merge);
    return userMapper.toDomain(savedEntity);
  }

  @Override
  public List<FrequentCustomersTopDto> findFrequentCustomersTop() {
    List<FrequentCustomersTopProjection> entities = jpaRepository.frequentCustomersTop(5);
    return entities.stream().map(projection -> new FrequentCustomersTopDto(projection.getId(), projection.getName(), projection.getLastName(), projection.getTotalSales(), projection.getTotalAmount())).toList();
  }
  
}
