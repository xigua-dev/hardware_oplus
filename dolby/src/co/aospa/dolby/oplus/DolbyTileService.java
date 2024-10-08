/*
 * Copyright (C) 2023-24 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package co.aospa.dolby.oplus;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class DolbyTileService extends TileService {

    private static final String TAG = "DolbyTileService";

    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
        DolbyUtils dolbyUtils = DolbyUtils.getInstance(getApplicationContext());
        if (dolbyUtils.getDsOn()) {
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setState(Tile.STATE_INACTIVE);
        }
        tile.setSubtitle(dolbyUtils.getProfileName());
        tile.updateTile();
        super.onStartListening();
    }

    @Override
    public void onClick() {
        Tile tile = getQsTile();
        DolbyUtils dolbyUtils = DolbyUtils.getInstance(getApplicationContext());
        if (dolbyUtils.getDsOn()) {
            dolbyUtils.setDsOn(false);
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            dolbyUtils.setDsOn(true);
            tile.setState(Tile.STATE_ACTIVE);
        }
        tile.updateTile();
        super.onClick();
    }
}
