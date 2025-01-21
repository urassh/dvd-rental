package com.urassh.dvdrental.domain.interfaces;

import com.urassh.dvdrental.domain.Member;

public interface ImageRepository {
    byte[] get(Member member);
}
