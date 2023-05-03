package com.tem.TransportApp.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@NoArgsConstructor
public class Attachment {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    @OneToOne(mappedBy = "attachment")
    private AppUser appUser;

    public Attachment(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

}
