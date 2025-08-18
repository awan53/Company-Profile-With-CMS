package com.alkes.alkse.service;

import com.alkes.alkse.model.ContactInfo;
import com.alkes.alkse.repository.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContactInfoService {

    private ContactInfoRepository contactInfoRepository;

    @Autowired
    public ContactInfoService(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    public List<ContactInfo> findAllContactInfo() {
        return contactInfoRepository.findAll();
    }

    public Optional<ContactInfo> findContactInfoById(Long id) {
        return contactInfoRepository.findById(id);
    }

    public ContactInfo saveContactInfo(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }

    public void deleteContactInfo(Long id) {
        contactInfoRepository.deleteById(id);
    }
}
