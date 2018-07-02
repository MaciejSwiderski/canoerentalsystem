package com.canoerent.service;

import com.canoerent.model.Canoe;
import com.canoerent.model.CanoeTrip;
import com.canoerent.repository.CanoeForTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CanoeForTripService {


    @Autowired
    CanoeForTripRepository canoeForTripRepository;

    public List<CanoeTrip> getCanoe() {
        return canoeForTripRepository.findAll();
    }

    public CanoeTrip save(CanoeTrip canoeTrip) {
        return canoeForTripRepository.save(canoeTrip);
    }

    public CanoeTrip findCanoeTripById(long id) {
        return canoeForTripRepository.findCanoeTripByCanoeTripId(id);
    }

    @Transactional
    public Optional<CanoeTrip> deleteCanoeTripById(long id) {
        return canoeForTripRepository.deleteCanoeTripByCanoeTripId(id);
    }
}
