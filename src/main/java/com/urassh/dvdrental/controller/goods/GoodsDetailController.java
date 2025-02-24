package com.urassh.dvdrental.controller.goods;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.goods.DeleteGoodsUseCase;
import com.urassh.dvdrental.util.ConfirmationAlertUtil;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.util.Optional;

public class GoodsDetailController {
    @FXML
    private Label titleLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private Label rentalCountLabel;

    @FXML
    private Label belongToStoreLabel;

    @FXML
    private Label isNewLabel;

    private Goods goods;

    private final Navigator navigator;
    private final DeleteGoodsUseCase deleteGoodsUseCase;

    @Inject
    public GoodsDetailController(Navigator navigator, DeleteGoodsUseCase deleteGoodsUseCase) {
        this.navigator = navigator;
        this.deleteGoodsUseCase = deleteGoodsUseCase;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
        final String GOODS_DATE = new SimpleDateFormat("yyyy-MM-dd").format(goods.getReleaseDate());
        titleLabel.setText(goods.getTitle());
        idLabel.setText(goods.getId().toString());
        genreLabel.setText(goods.getGenre());
        releaseDateLabel.setText(GOODS_DATE);
        rentalCountLabel.setText(String.valueOf(goods.getLoanCount()));
        belongToStoreLabel.setText(goods.getBelongToStore());
        isNewLabel.setText(goods.isNew() ? "新作" : "旧作");
    }

    public void onEdit() {
        navigator.navigateToGoodsEdit(goods);
    }

    public void onRemove() {
        String content = "商品ID     : " + goods.getId() + "\nタイトル : " + goods.getTitle();
        ConfirmationAlertUtil alertUtil = new ConfirmationAlertUtil("商品削除", "商品情報を削除しますか？", content);
        alertUtil.setAcceptButtonText("削除");
        alertUtil.acceptIsDanger();

        Optional<ButtonType> result = alertUtil.showAndWait();

        if (result.isPresent() && result.get() == alertUtil.getAcceptButtonType()) {
            deleteGoodsUseCase.execute(goods);
            navigator.navigateToGoods();
        }
    }
}
