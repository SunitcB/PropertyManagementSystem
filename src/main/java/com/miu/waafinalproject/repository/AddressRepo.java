package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepo extends CrudRepository<Address, Long> {
}
