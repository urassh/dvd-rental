package com.urassh.dvdrental.infrastructure.records;

import com.google.gson.annotations.SerializedName;
import com.urassh.dvdrental.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRecord {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("address")
    private String address;

    @SerializedName("birth_date")
    private Date birthDate;

    public static MemberRecord from(Member member) {
        return new MemberRecord(
                member.getId().toString(),
                member.getName(),
                member.getPhoneNumber(),
                member.getAddress(),
                member.getBirthDate()
        );
    }

    public Member toDomain() {
        return new Member(
                UUID.fromString(id),
                name,
                phoneNumber,
                address,
                birthDate
        );
    }
}