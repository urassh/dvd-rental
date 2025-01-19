package com.urassh.dvdrental.domain;

import java.util.Date;
import java.util.UUID;

public class Member {
    private final UUID id;
    private final String name;
    private final int phoneNumber;
    private final String address;
    private final Date birthDate;

    public Member(UUID id, String name, int phoneNumber, String address, Date birthDate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
    }

    public Member(String name, int phoneNumber, String address, Date birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
    }

    public static Member newMember() {
        return new Member("", 0, "", new Date());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Member setName(String name) {
        return new Member(name, phoneNumber, address, birthDate);
    }

    public Member setPhoneNumber(int phoneNumber) {
        return new Member(name, phoneNumber, address, birthDate);
    }

    public Member setAddress(String address) {
        return new Member(name, phoneNumber, address, birthDate);
    }

    public Member setBirthDate(Date birthDate) {
        return new Member(name, phoneNumber, address, birthDate);
    }
}
