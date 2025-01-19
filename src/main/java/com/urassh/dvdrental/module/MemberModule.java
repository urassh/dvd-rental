package com.urassh.dvdrental.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.urassh.dvdrental.usecase.members.*;

public class MemberModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AddMemberUseCase.class).in(Singleton.class);
        bind(DeleteMemberUseCase.class).in(Singleton.class);
        bind(GetAllMembersUseCase.class).in(Singleton.class);
        bind(GetMemberUseCase.class).in(Singleton.class);
        bind(UpdateMemberUseCase.class).in(Singleton.class);
    }
}