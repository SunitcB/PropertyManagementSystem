package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Address;
import com.miu.waafinalproject.service.charts.StatePropertyChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepo extends CrudRepository<Address, Long> {
    @Query("Select new com.miu.waafinalproject.service.charts.StatePropertyChartData(a.state, count(p.id))  from Property p left join p.address a group by a.state")
    List<StatePropertyChartData> findAllStateCount();
}
