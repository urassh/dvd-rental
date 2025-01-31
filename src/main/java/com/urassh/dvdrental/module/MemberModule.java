package com.urassh.dvdrental.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.infrastructure.gateway.MemberGatewayImpl;
import com.urassh.dvdrental.repository.dummy.MemberDummyRepository;
import com.urassh.dvdrental.repository.impl.MemberRepositoryImpl;
import com.urassh.dvdrental.repository.interfaces.MemberGateway;
import com.urassh.dvdrental.usecase.members.*;

public class MemberModule extends AbstractModule {
    @Override
    protected void configure() {
        // Gateway
        bind(MemberGateway.class).to(MemberGatewayImpl.class).in(Singleton.class);

        // Repository
        bind(MemberRepository.class).to(MemberRepositoryImpl.class).in(Singleton.class);

        // UseCase
        bind(AddMemberUseCase.class).in(Singleton.class);
        bind(DeleteMemberUseCase.class).in(Singleton.class);
        bind(GetAllMembersUseCase.class).in(Singleton.class);
        bind(GetMemberUseCase.class).in(Singleton.class);
        bind(UpdateMemberUseCase.class).in(Singleton.class);
        bind(GetMemberIconUseCase.class).in(Singleton.class);
    }
}