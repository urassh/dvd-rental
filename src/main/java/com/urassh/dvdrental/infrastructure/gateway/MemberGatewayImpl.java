package com.urassh.dvdrental.infrastructure.gateway;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.urassh.dvdrental.infrastructure.records.GsonProvider;
import com.urassh.dvdrental.infrastructure.records.MemberRecord;
import com.urassh.dvdrental.repository.interfaces.MemberGateway;
import com.urassh.dvdrental.util.EnvConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class MemberGatewayImpl implements MemberGateway {
    private static final String BASE_URL = EnvConfig.get("BASE_URL");
    private static final String API_KEY = EnvConfig.get("API_KEY");
    private static final String MEMBERS_URL = BASE_URL + "/member";

    private final HttpClient client;
    private final Gson gson;

    public MemberGatewayImpl() {
        this.client = HttpClient.newHttpClient();
        this.gson = GsonProvider.getGsonForRecord();
    }

    @Override
    public CompletableFuture<List<MemberRecord>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MEMBERS_URL))
                    .header("X-Api-Key", API_KEY)
                    .GET()
                    .build();

            try {
                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                return gson.fromJson(response.body(), new TypeToken<List<MemberRecord>>() {}.getType());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<MemberRecord> getById(UUID id) {
        final String idStr = id.toString();

        return CompletableFuture.supplyAsync(() -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MEMBERS_URL + "/" + idStr))
                    .header("X-Api-Key", API_KEY)
                    .GET()
                    .build();

            try {
                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return gson.fromJson(response.body(), MemberRecord.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<Void> add(MemberRecord member) {
        return CompletableFuture.supplyAsync(() -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MEMBERS_URL))
                    .header("Content-Type", "application/json")
                    .header("X-Api-Key", API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(member)))
                    .build();

            try {
                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 201) {
                    throw new RuntimeException("Failed to add member");
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            return null;
        });
    }

    @Override
    public CompletableFuture<Void> update(MemberRecord member) {
        return CompletableFuture.supplyAsync(() -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MEMBERS_URL + "/" + member.getId()))
                    .header("Content-Type", "application/json")
                    .header("X-Api-Key", API_KEY)
                    .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(member)))
                    .build();

            try {
                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    throw new RuntimeException("Failed to update member");
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            return null;
        });
    }

    @Override
    public CompletableFuture<Void> delete(MemberRecord member) {
        return CompletableFuture.supplyAsync(() -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MEMBERS_URL + "/" + member.getId()))
                    .header("X-Api-Key", API_KEY)
                    .DELETE()
                    .build();

            try {
                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 204) {
                    throw new RuntimeException("Failed to delete member");
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            return null;
        });
    }
}