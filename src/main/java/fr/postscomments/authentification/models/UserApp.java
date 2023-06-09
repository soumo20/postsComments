package fr.postscomments.authentification.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "user_app")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

    private Boolean enabled;

    public UserApp(String email, String passeword, String phone, Set<Role> roles) {
        this.email = email;
        this.password = passeword;
        this.phone = phone;
        this.roles = roles;
        this.enabled = false;
    }
}
