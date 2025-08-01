package dev.tori.fovu.client;

import net.fabricmc.api.ClientModInitializer;

public class FovUClient implements ClientModInitializer {

    public static final String MOD_ID = "fovu";
    public static final String MOD_NAME = "FOVU";
    public static final String MOD_VERSION = "1.0.0";

    public static final int MIN_FOV = 1;
    public static final int MAX_FOV = 170;

    @Override
    public void onInitializeClient() {

    }
}