package com.soin.digitaldynamics.Presenter;

import com.soin.digitaldynamics.Model.SlotMachine.MSlotItem;
import com.soin.digitaldynamics.Model.SlotMachine.SlotMachineDynamic;
import com.soin.digitaldynamics.Model.SocketManager;
import com.soin.digitaldynamics.R;
import com.soin.digitaldynamics.UI.View.SlotMachineView;
import com.soin.digitaldynamics.Util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by neilgarciavargas on 21/6/16.
 */
public class SlotMachinePresenter extends BasePresenter {

    private SlotMachineDynamic _slotMachineDynamic;

    private SlotMachineView _slotMachineView;

    private ArrayList<MSlotItem> _slotItems;
    private ArrayList<MSlotItem> _slotItems2;
    private ArrayList<MSlotItem> _slotItems3;

    public SlotMachinePresenter(SlotMachineView slotMachineView){
        _slotMachineView = slotMachineView;

        initSocket();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }


    public void initSocket()
    {
        String beaconString = "85A98C98-58E1-485E-948F-EAEED9AA1EA6-76-21";
        SocketManager socketManager = new SocketManager();
        _slotMachineDynamic = new SlotMachineDynamic(socketManager,beaconString);

    }

    /**
     * Connect to socket.
     */
    public void connectSocket()
    {
        _slotMachineView.showProgress();
        _slotMachineDynamic.initSocket();
    }


    /**
     * Get Slot machine configuration.
     * The configuration can be, rewards, machine layout, etc.
     */
    public void getSlotMachineConfiguration()
    {

    }

    public void getSlotMachineItems()
    {
        MSlotItem item1 = new MSlotItem(R.drawable.coffee_grounds,"coffe");
        MSlotItem item2 = new MSlotItem(R.drawable.loose_tea,"te");
        MSlotItem item3 = new MSlotItem(R.drawable.coffee_maker,"coffe maker");
        MSlotItem item4 = new MSlotItem(R.drawable.coffee_filter,"filtro");
        _slotItems = new ArrayList<MSlotItem>();
        _slotItems.add(item1);
        _slotItems.add(item2);
        _slotItems.add(item3);
        _slotItems.add(item4);

        _slotItems2 = _slotItems;
        _slotItems3 = _slotItems;
        ArrayUtil.shuffleArray(_slotItems);
        ArrayUtil.shuffleArray(_slotItems2);
        ArrayUtil.shuffleArray(_slotItems3);
        _slotMachineView.diplaySlotMachineItems(_slotItems,_slotItems2,_slotItems3);

    }


}
