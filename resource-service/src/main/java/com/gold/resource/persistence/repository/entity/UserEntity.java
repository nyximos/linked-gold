package com.gold.resource.persistence.repository.entity;

import com.gold.core.util.EncryptUtils;
import jakarta.persistence.*;
import lombok.*;

import java.security.NoSuchAlgorithmException;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(name = "username_uk", columnNames = {"username"})})
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public void updatePassword(String rawPassword) {
        try {
            this.password = EncryptUtils.encryptSHA256(rawPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to encrypt password", e);
        }
    }
}
