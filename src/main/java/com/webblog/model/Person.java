package com.webblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Person extends TimerController implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int IsDelete;

    @Column(name = "removeDate")
    private String removeDate;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int personDeactivated;

    @Column(columnDefinition = "MEDIUMBLOB")
    private String profilePics;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Post> post;

}

