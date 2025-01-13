package com.urassh.dvdrental.infrastructure;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;

import java.util.*;
import java.util.concurrent.CompletableFuture;


public class MemberDummyRepository implements MemberRepository {
    private static List<Member> membersList = new ArrayList<>(List.of(
            new Member("1", "Alice", 123456789, "123 Main St", new Date(1990, Calendar.JANUARY, 1)),
            new Member("2", "Bob", 987654321, "456 Elm St", new Date(1985, Calendar.MAY, 15)),
            new Member("3", "Charlie", 456789123, "789 Oak St", new Date(1992, Calendar.AUGUST, 10)),
            new Member("4", "Diana", 321654987, "321 Pine St", new Date(1988, Calendar.DECEMBER, 25)),
            new Member("5", "Edward", 654123987, "654 Maple St", new Date(1995, Calendar.JUNE, 5))
    ));

    @Override
    public CompletableFuture<List<Member>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate delay
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return membersList;
        });
    }

    @Override
    public CompletableFuture<Member> getById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate delay
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Member member : membersList) {
                if (member.getId().equals(id)) {
                    return member;
                }
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> add(Member member) {
        membersList.add(member);
        return null;
    }

    @Override
    public CompletableFuture<Void> update(Member member) {
        for (int i = 0; i < membersList.size(); i++) {
            if (membersList.get(i).getId().equals(member.getId())) {
                membersList.set(i, member);
                return null;
            }
        }
        return null;
    }

    @Override
    public CompletableFuture<Void> delete(Member member) {
        membersList.removeIf(existingMember -> existingMember.getId().equals(member.getId()));
        return null;
    }
}
