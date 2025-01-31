package com.urassh.dvdrental.infrastructure.gateway;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.urassh.dvdrental.infrastructure.records.GoodsRecord;
import com.urassh.dvdrental.repository.interfaces.GoodsGateway;
import com.urassh.dvdrental.util.EnvConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GoodsGatewayImpl implements GoodsGateway {
    private static final String BASE_URL = EnvConfig.get("BASE_URL");
    private static final String API_KEY = EnvConfig.get("API_KEY");
    private static final String GOODS_URL = BASE_URL + "/goods";
    private final HttpClient client;
    private final Gson gson;

    public GoodsGatewayImpl() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    @Override
    public CompletableFuture<List<GoodsRecord>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GOODS_URL))
                    .header("X-Api-Key", API_KEY)
                    .GET()
                    .build();

            try {
                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return gson.fromJson(response.body(), new TypeToken<List<GoodsRecord>>() {
                }.getType());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public CompletableFuture<Void> add(GoodsRecord goods) {
        return CompletableFuture.supplyAsync(() -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GOODS_URL))
                    .header("Content-Type", "application/json")
                    .header("X-Api-Key", API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(goods)))
                    .build();

            try {
                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 201) {
                    throw new RuntimeException("Failed to add goods");
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            return null;
        });
    }

    @Override
    public CompletableFuture<Void> update(GoodsRecord goods) {
        return CompletableFuture.supplyAsync(() -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GOODS_URL + "/" + goods.getId()))
                    .header("Content-Type", "application/json")
                    .header("X-Api-Key", API_KEY)
                    .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(goods)))
                    .build();

            try {
                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    throw new RuntimeException("Failed to update goods");
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            return null;
        });
    }

    @Override
    public CompletableFuture<Void> delete(GoodsRecord goods) {
        return CompletableFuture.supplyAsync(() -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GOODS_URL + "/" + goods.getId()))
                    .header("X-Api-Key", API_KEY)
                    .DELETE()
                    .build();

            try {
                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 204) {
                    throw new RuntimeException("Failed to delete goods");
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            return null;
        });
    }
}
