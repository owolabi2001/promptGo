package com.tem.TransportApp.repository;

import com.tem.TransportApp.domain.AppUser;
import com.tem.TransportApp.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,String> {

    Attachment findAttachmentByAppUser(AppUser appUser);
}
