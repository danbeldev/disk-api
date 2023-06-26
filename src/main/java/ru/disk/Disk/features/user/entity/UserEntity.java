package ru.disk.Disk.features.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.disk.Disk.features.user.dto.RegisterDto;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
@Schema
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    public UserEntity() {}

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserEntity(RegisterDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }
}