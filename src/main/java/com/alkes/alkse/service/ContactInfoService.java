package com.alkes.alkse.service;

import com.alkes.alkse.model.ContactInfo;
import com.alkes.alkse.repository.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;



@Service
public class ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Simpan ContactInfo dan kirim notifikasi email
    public ContactInfo saveAndNotify(ContactInfo contactInfo){
        ContactInfo saved = contactInfoRepository.save(contactInfo);

        //kirim email ke admin
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("mg3248193@gmail.com");
        message.setSubject("Pesan Baru dari" + contactInfo.getNmUser());
        message.setText(
                "Nama: " + contactInfo.getNmUser() + "\n" + "Email: " + contactInfo.getEmail() + "\n" + "Phone: " + contactInfo.getPhone() + "\n" +
                        "Address: " + contactInfo.getAddress() + "\n\n" +
                        "Pesan:\n" + contactInfo.getMessage()
        );
        mailSender.send(message);
        return saved;
    }

    public List<ContactInfo> getAllContactInfos() {
        return contactInfoRepository.findAll();
    }

    public ContactInfo getContactInfoById(Long id) {
        return contactInfoRepository.findById(id).orElse(null);
    }

}
