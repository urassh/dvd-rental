package com.urassh.dvdrental.util;

import com.urassh.dvdrental.controller.goods.EditGoodsController;
import com.urassh.dvdrental.controller.goods.GoodsDetailController;
import com.urassh.dvdrental.controller.members.EditMemberController;
import com.urassh.dvdrental.controller.members.MemberDetailController;
import com.urassh.dvdrental.controller.rental.RentalDetailController;
import com.urassh.dvdrental.controller.returns.detail.ReturnDetailController;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.errors.NavigationException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.function.Consumer;

public class Navigator {
    private final FxmlLoaderUtil loaderUtil;
    private final Stage stage;

    public Navigator(Stage stage, FxmlLoaderUtil loaderUtil) {
        this.stage = stage;
        this.loaderUtil = loaderUtil;
    }

    public void navigateTo(String key, String title) {
        navigateTo(key, title, null);
    }

    public void navigateTo(String key, String title, Consumer<FXMLLoader> consumer) {
        try {
            final FXMLLoader loader = loaderUtil.loadFXML(key);
            if (consumer != null) {
                consumer.accept(loader);
            }

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            final double screenWidth = screenBounds.getWidth();
            final double screenHeight = screenBounds.getHeight();
            final Scene scene = new Scene(loader.getRoot(), screenWidth, screenHeight);

            stage.setMaximized(true);
            stage.setScene(scene);
            stage.setTitle(appTitle(title));
            stage.show();
        } catch (IOException e) {
            throw new NavigationException("Failed to navigate to " + key, e);
        }
    }

    public void navigateToHome() {
        navigateTo("home", "Home");
    }

    public void navigateToGoods() {
        navigateTo("goods", "商品情報");
    }

    public void navigateToGoodsNew() {
        navigateTo("goods_new", "商品追加");
    }

    public void navigateToGoodsDetail(Goods goods) {
        navigateTo("goods_detail", "商品詳細", loader -> {
            GoodsDetailController controller = loader.getController();
            controller.setGoods(goods);
        });
    }

    public void navigateToGoodsEdit(Goods goods) {
        navigateTo("goods_edit", "商品編集", loader -> {
            EditGoodsController controller = loader.getController();
            controller.setGoods(goods);
        });
    }

    public void navigateToMembers() {
        navigateTo("members", "会員情報");
    }

    public void navigateToMembersNew() {
        navigateTo("members_new", "会員追加");
    }

    public void navigateToMemberDetail(Member member) {
        navigateTo("member_detail", "会員詳細", loader -> {
            MemberDetailController controller = loader.getController();
            controller.setMember(member);
        });
    }

    public void navigateToMemberEdit(Member member) {
        navigateTo("member_edit", "会員編集", loader -> {
            EditMemberController controller = loader.getController();
            controller.setMember(member);
        });
    }

    public void navigateToRental() {
        navigateTo("rental", "貸し出し");
    }

    public void navigateToReturn() {
        navigateTo("return", "返却");
    }

    public void navigateToRentalDetail(Goods goods) {
        navigateTo("rental_detail", goods.getTitle(), loader -> {
            RentalDetailController controller = loader.getController();
            controller.setGoods(goods);
        });
    }

    public void navigateToRentalCart() {
        navigateTo("rental_cart", "商品カート");
    }

    public void navigateToReturnDetail(Rental rental) {
        navigateTo("return_detail", "返却詳細", loader -> {
            ReturnDetailController controller = loader.getController();
            controller.setRental(rental);
        });
    }

    private String appTitle(String title) {
        return "DVD貸し出し管理アプリ - " + title;
    }
}