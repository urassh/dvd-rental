package com.urassh.dvdrental.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member(UUID.randomUUID(), "John Doe", "1234567890", "123 Main St", new Date());
    }

    @Test
    void testConstructor_WithId() {
        UUID id = UUID.randomUUID();
        Member member = new Member(id, "Jane Doe", "0987654321", "456 Elm St", new Date());
        assertEquals(id, member.getId());
        assertEquals("Jane Doe", member.getName());
        assertEquals("0987654321", member.getPhoneNumber());
        assertEquals("456 Elm St", member.getAddress());
    }

    @Test
    void testConstructor_WithoutId() {
        Member member = new Member("Jane Doe", "0987654321", "456 Elm St", new Date());
        assertNotNull(member.getId());
        assertEquals("Jane Doe", member.getName());
        assertEquals("0987654321", member.getPhoneNumber());
        assertEquals("456 Elm St", member.getAddress());
    }

    @Test
    void testNewMember() {
        Member newMember = Member.newMember();
        assertNotNull(newMember.getId());
        assertEquals("", newMember.getName());
        assertEquals("", newMember.getPhoneNumber());
        assertEquals("", newMember.getAddress());
        assertNotNull(newMember.getBirthDate());
    }

    @Test
    void testSetName() {
        Member updatedMember = member.setName("Updated Name");
        assertEquals("Updated Name", updatedMember.getName());
        assertNotEquals(member.getName(), updatedMember.getName());
    }

    @Test
    void testSetPhoneNumber() {
        Member updatedMember = member.setPhoneNumber("1112223333");
        assertEquals("1112223333", updatedMember.getPhoneNumber());
        assertNotEquals(member.getPhoneNumber(), updatedMember.getPhoneNumber());
    }

    @Test
    void testSetAddress() {
        Member updatedMember = member.setAddress("789 Oak St");
        assertEquals("789 Oak St", updatedMember.getAddress());
        assertNotEquals(member.getAddress(), updatedMember.getAddress());
    }

    @Test
    void testSetBirthDate() {
        Date initialBirthDate = member.getBirthDate();
        Date newBirthDate = new Date(initialBirthDate.getTime() + 1000);
        Member updatedMember = member.setBirthDate(newBirthDate);
        assertEquals(newBirthDate, updatedMember.getBirthDate());
        assertNotEquals(initialBirthDate, updatedMember.getBirthDate());
    }
}