package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a user in the PayMyBuddy application.
 * User can have associated account, friends, and financial transactions.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="user")
public class User implements Serializable {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    /**
     * The firstname of the user.
     */
    @Column(name="firstname")
    private String firstname;

    /**
     * The lastname of the user.
     */
    @Column(name="lastname")
    private String lastname;

    /**
     * The birthdate of the user.
     */
    @Column(name="birthdate")
    private LocalDate birthdate;

    /**
     * The phone of the user.
     */
    @Column(name="phone")
    private String phone;

    /**
     * The address of the user.
     */
    @Column(name="address")
    private String address;

    /**
     * The app account of the user.
     */
    @OneToOne
    @JoinColumn(name="app_account_id")
    private AppAccount appAccount;

    /**
     * The list of friends associated with the user.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="assoc_user_friend",
            joinColumns = @JoinColumn(name="user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="friend_id", nullable = false))
    private List<User> friends;

    /**
     * The list of transfers sent by the user.
     */
    @OneToMany(mappedBy = "author",
            orphanRemoval = true)
    private List<Deposit> sourceTransac;

    /**
     * The list of transfers received by the user.
     */
    @OneToMany(mappedBy = "recipient",
            orphanRemoval = true)
    private List<Transfert> receivedTransferts;

    /**
     * The email of the user.
     */
    @Column(name="email")
    private String email;

    /**
     * The password of the user.
     */
    @Column(name="password")
    private String password;

    /**
     * The date of the last connection on the application.
     */
    @Column(name="last_connection_date")
    private LocalDateTime lastConnectionDate;

    /**
     * The status of the account (true if the account is active).
     */
    @Column(name="is_active")
    private Boolean isActive;

    /**
     * The role of the user
     */
    @Column(name="role")
    private String role;

    /**
     * Constructor with essential fields.
     *
     * @param firstname The first name of the user
     * @param lastname The last name of the user
     * @param birthdate The birthdate of the user
     * @param phone The phone of the user
     * @param address The address of the user
     * @param appAccount The appAccount of the user
     * @param friends The list of friends of the user
     * @param email The email of the user
     * @param password  The password of the user
     * @param lastConnectionDate The last date of connection on the application
     * @param isActive The status of the account
     * @param role The role of the user
     */
    @Builder
    public User(String firstname, String lastname, LocalDate birthdate, String phone, String address, AppAccount appAccount, List<User> friends, String email, String password, LocalDateTime lastConnectionDate, Boolean isActive, String role){
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.phone = phone;
        this.address = address;
        this.appAccount = appAccount;
        this.friends = friends;
        this.email = email;
        this.password = password;
        this.lastConnectionDate = lastConnectionDate;
        this.isActive = isActive;
        this.role = role;
    }

}