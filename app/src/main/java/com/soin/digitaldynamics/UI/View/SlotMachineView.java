package com.soin.digitaldynamics.UI.View;

import com.soin.digitaldynamics.Model.SlotMachine.MSlotItem;

import java.util.ArrayList;

/**
 * Created by neilgarciavargas on 21/6/16.
 */
public interface SlotMachineView {

    /**
     * Show progress indicator.
     */
    void showProgress();

    /**
     * Hide progress indicator
     */
    void hideProgress();


    /**
     * Display slot machine items.
     */
    void diplaySlotMachineItems(ArrayList<MSlotItem> slotItems1, ArrayList<MSlotItem> slotItems2, ArrayList<MSlotItem> slotItems3);

    void spinOn();

}
