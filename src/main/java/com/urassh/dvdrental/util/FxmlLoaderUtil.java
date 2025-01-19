package com.urassh.dvdrental.util;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.urassh.dvdrental.RentalApp;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Map;

public class FxmlLoaderUtil {
    private final Injector injector;

    private static final Map<String, String> FXML_PATHS = Map.ofEntries(
            Map.entry("home", "home/view.fxml"),
            Map.entry("goods", "goods/view.fxml"),
            Map.entry("goods_new", "goods/new/view.fxml"),
            Map.entry("goods_detail", "goods/detail/view.fxml"),
            Map.entry("goods_edit", "goods/edit/view.fxml"),
            Map.entry("members", "members/view.fxml"),
            Map.entry("members_new", "members/new/view.fxml"),
            Map.entry("member_detail", "members/detail/view.fxml"),
            Map.entry("rental", "rental/view.fxml"),
            Map.entry("return", "return/view.fxml"),
            Map.entry("rental_detail", "rental/detail/view.fxml"),
            Map.entry("rental_cart", "rental/cart/view.fxml"),
            Map.entry("return_detail", "return/detail/view.fxml")
    );

    @Inject
    public FxmlLoaderUtil(Injector injector) {
        this.injector = injector;
    }

    public FXMLLoader loadFXML(String key) throws IOException {
        String fxmlPath = FXML_PATHS.get(key);
        if (fxmlPath == null) {
            throw new IllegalArgumentException("Invalid FXML key: " + key);
        }

        FXMLLoader loader = new FXMLLoader(RentalApp.class.getResource(fxmlPath));
        loader.setControllerFactory(injector::getInstance);
        loader.load();
        return loader;
    }
}