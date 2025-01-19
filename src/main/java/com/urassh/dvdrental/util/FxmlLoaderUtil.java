package com.urassh.dvdrental.util;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.urassh.dvdrental.RentalApp;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Map;

public class FxmlLoaderUtil {
    private final Injector injector;

    private static final Map<String, String> FXML_PATHS = Map.of(
        "home", "home/view.fxml",
        "goods", "goods/view.fxml",
        "goods_new", "goods/new/view.fxml",
        "members", "members/view.fxml",
        "members_new", "members/new/view.fxml",
        "rental", "rental/view.fxml",
        "return", "return/view.fxml",
        "rental_detail", "rental/detail/view.fxml",
        "rental_cart", "rental/cart/view.fxml",
        "return_detail", "return/detail/view.fxml"
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