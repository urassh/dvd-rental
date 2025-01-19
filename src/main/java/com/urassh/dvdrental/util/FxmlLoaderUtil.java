package com.urassh.dvdrental.util;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Map;

public class FxmlLoaderUtil {
    private static final Map<String, String> FXML_PATHS = Map.of(
        "home", "home/view.fxml",
        "goods", "goods/view.fxml",
        "goods_new", "goods/new/view.fxml",
        "members", "members/view.fxml",
        "rental", "rental/view.fxml",
        "return", "return/view.fxml",
        "rental_detail", "rental/detail/view.fxml",
        "rental_cart", "rental/cart/view.fxml",
        "return_detail", "return/detail/view.fxml"
    );

    public static FXMLLoader loadFXML(String key) throws IOException {
        String fxmlPath = FXML_PATHS.get(key);
        if (fxmlPath == null) {
            throw new IllegalArgumentException("Invalid FXML key: " + key);
        }

        FXMLLoader loader = new FXMLLoader(FxmlLoaderUtil.class.getResource(fxmlPath));
        loader.load();
        return loader;
    }
}