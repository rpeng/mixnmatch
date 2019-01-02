package com.penguins.model;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public enum Tiles {
    CAMPFIRE("tileCampfire"),
    FLOWERS("tileFlowers"),
    LOLLIPOP("tileLollipop"),
    OTOMATONE_BLOODY("tileOtamatoneBloody"),
    OTOMATONE_HAPPY("tileOtamatoneHappy"),
    PIE("tilePie"),
    RABBIT_BLOODY("tileRabbitBloody"),
    RABBIT_HAPPY("tileRabbitHappy");

    private final String assetName;
    private Image image;

    Tiles(String assetName) {
        this.assetName = assetName;
    }

    public static void loadTiles() throws SlickException {
        for (Tiles value : Tiles.values()) {
            value.image = new Image(String.format("tiles/%s.png", value.assetName));
        }
    }

    public Image getImage() {
        return image;
    }
}
