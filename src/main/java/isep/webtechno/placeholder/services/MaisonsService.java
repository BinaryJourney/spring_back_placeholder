package isep.webtechno.placeholder.services;

import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.repositories.MaisonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaisonsService {

    @Autowired
    MaisonsRepository maisonsRepository;

    public List<Maisons> findAllElements() {
        return maisonsRepository.findAll();
    }
}
