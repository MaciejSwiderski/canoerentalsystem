package com.canoerent.service;

import com.canoerent.model.Canoe;
import com.canoerent.repository.CanoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CanoeService {

    @Autowired
    CanoeRepository canoeRepository;

    public List<Canoe> getCanoe() {
        return canoeRepository.findAll();
    }


    public Canoe save(Canoe canoe) {
        return canoeRepository.save(canoe);
    }

    public Canoe findCanoeById(long id) {
        return canoeRepository.findCanoeById(id);
    }

    @Transactional
    public Optional<Canoe> deleteCanoeById(long id) {
        return canoeRepository.deleteCanoeById(id);
    }

}
