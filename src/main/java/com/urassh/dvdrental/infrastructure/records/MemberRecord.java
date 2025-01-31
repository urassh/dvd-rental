package com.urassh.dvdrental.infrastructure.records;

import com.google.gson.annotations.SerializedName;
import com.urassh.dvdrental.domain.Member;

import java.util.Date;
import java.util.UUID;

public record MemberRecord(
        @SerializedName("id") String id,
        @SerializedName("name") String name,
        @SerializedName("phone_number") String phoneNumber,
        @SerializedName("address") String address,
        @SerializedName("birth_date") Date birthDate
) {
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
