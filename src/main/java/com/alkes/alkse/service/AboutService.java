package com.alkes.alkse.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkes.alkse.model.About;
import com.alkes.alkse.repository.AboutRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AboutService {
    private final
    AboutRepository aboutRepository;

    @Autowired
    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    public List<About> findAll() {
        return aboutRepository.findAll();
    }
    public Optional<About> findById(Long id) {
        return aboutRepository.findById(id);
    }
    public About saveAbout(About about) {
        // Validasi atau logika bisnis tambahan dapat ditambahkan di sini

        return aboutRepository.save(about);
    }

    public void deletAbout(Long id) {
        aboutRepository.deleteById(id);
    }

}
