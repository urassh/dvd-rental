package com.urassh.dvdrental.infrastructure;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;

import java.util.*;
import java.util.concurrent.CompletableFuture;


public class MemberDummyRepository implements MemberRepository {
    private static List<Member> membersList = new ArrayList<>(List.of(
            new Member("1", "Alice", 123456789, "123 Main St", new Date(1990 - 1900, Calendar.JANUARY, 1)),
            new Member("2", "Bob", 987654321, "456 Elm St", new Date(1985 - 1900, Calendar.MAY, 15)),
            new Member("3", "Charlie", 456789123, "789 Oak St", new Date(1992 - 1900, Calendar.AUGUST, 10)),
            new Member("4", "Diana", 321654987, "321 Pine St", new Date(1988 - 1900, Calendar.DECEMBER, 25)),
            new Member("5", "Edward", 654123987, "654 Maple St", new Date(1995 - 1900, Calendar.JUNE, 5)),
            new Member("6", "Fiona", 111222333, "987 Birch St", new Date(1993 - 1900, Calendar.SEPTEMBER, 12)),
            new Member("7", "George", 444555666, "123 Cedar St", new Date(1990 - 1900, Calendar.OCTOBER, 19)),
            new Member("8", "Hannah", 777888999, "456 Spruce St", new Date(1987 - 1900, Calendar.NOVEMBER, 28)),
            new Member("9", "Ian", 101010101, "789 Walnut St", new Date(1996 - 1900, Calendar.JULY, 4)),
            new Member("10", "Jane", 202020202, "321 Cypress St", new Date(1989 - 1900, Calendar.APRIL, 14)),
            new Member("11", "Kyle", 303030303, "654 Redwood St", new Date(1994 - 1900, Calendar.FEBRUARY, 22)),
            new Member("12", "Laura", 404040404, "987 Aspen St", new Date(1991 - 1900, Calendar.MARCH, 3)),
            new Member("13", "Michael", 505050505, "123 Willow St", new Date(1986 - 1900, Calendar.JANUARY, 29)),
            new Member("14", "Nina", 606060606, "456 Chestnut St", new Date(1992 - 1900, Calendar.DECEMBER, 11)),
            new Member("15", "Oscar", 707070707, "789 Beech St", new Date(1997 - 1900, Calendar.JUNE, 21))
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
    public void add(Member member) {
        membersList.add(member);
    }

    @Override
    public void update(Member member) {
        for (int i = 0; i < membersList.size(); i++) {
            if (membersList.get(i).getId().equals(member.getId())) {
                membersList.set(i, member);
            }
        }
    }

    @Override
    public void delete(Member member) {
        membersList.removeIf(existingMember -> existingMember.getId().equals(member.getId()));
    }
}
