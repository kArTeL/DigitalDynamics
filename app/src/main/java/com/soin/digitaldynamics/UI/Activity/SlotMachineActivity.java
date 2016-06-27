package com.soin.digitaldynamics.UI.Activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soin.digitaldynamics.Model.SlotMachine.MSlotItem;
import com.soin.digitaldynamics.Presenter.BasePresenter;
import com.soin.digitaldynamics.Presenter.SlotMachinePresenter;
import com.soin.digitaldynamics.R;
import com.soin.digitaldynamics.UI.View.SlotMachineView;
import com.soin.digitaldynamics.Wheel.ISlotMachineItem;
import com.soin.digitaldynamics.Wheel.WheelView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by neilgarciavargas on 20/6/16.
 */
public class SlotMachineActivity extends BaseActivity implements SlotMachineView {


    /** Wheels **/
    @Bind(R.id.wheel1)
    WheelView wheelView1;

    @Bind(R.id.wheel2)
    WheelView wheelView2;

    @Bind(R.id.wheel3)
    WheelView wheelView3;

    @Bind(R.id.spin)
    Button spin;

    SlotMachinePresenter _presenter;


    private int wheel1Selection;
    private int wheel2Selection;
    private int wheel3Selection;


    private final int SPIN_TIME = 2500;
    private final int MESSAGE_CHECK_MATCH = 0;



    /** Data slot machine **/
    ArrayList<MSlotItem> _slotItems1;
    ArrayList<MSlotItem> _slotItems2;
    ArrayList<MSlotItem> _slotItems3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _presenter = new SlotMachinePresenter(this);
        _presenter.initSocket();
        _presenter.connectSocket();
        _presenter.getSlotMachineItems();

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_slotmachine;
    }

    @Nullable
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initObs() {

    }

    @Override
    protected void initUI() {
        configureWheelLayout();
        spin.setEnabled(false);
        //spin
    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void diplaySlotMachineItems(ArrayList<MSlotItem> slotItems1, ArrayList<MSlotItem> slotItems2, ArrayList<MSlotItem> slotItems3)
    {
        _slotItems1 = slotItems1;
        _slotItems2 = slotItems2;
        _slotItems3 = slotItems3;


        configureWheels();
        spin.setEnabled(true);
        //();

        //ArrayUtil.shuffleArray(_slotItems1);

    }

    @Override
    public void spinOn()
    {
        startWheelsMovement();
    }

    //Concrete implementation of interface ISlotMachineItem
    class SlotItemView implements ISlotMachineItem {

        int wheelPos;
        int slotItemPos;

        public SlotItemView(
                int wheelPos,
                int slotItemPos) {
            this.wheelPos = wheelPos;
            this.slotItemPos = slotItemPos;
        }

        @Override
        public View getView() {
            View view = getLayoutInflater().inflate(R.layout.slot_item_layout, null, false);
            ImageView itemImageView = (ImageView) view.findViewById(R.id.itemImage);
            TextView itemTextView = (TextView) view.findViewById(R.id.itemTxt);

                Resources resources = getResources();
                switch (wheelPos)
                {
                    case 1 :
                    {
                        itemImageView.setImageResource(_slotItems1.get(slotItemPos).getImage());
                         itemTextView.setText(_slotItems1.get(slotItemPos).getText());
                    }
                    case 2 :
                {
                    itemImageView.setImageResource(_slotItems2.get(slotItemPos).getImage());
                    itemTextView.setText(_slotItems2.get(slotItemPos).getText());
                }
                case 3 :
                {
                    itemImageView.setImageResource(_slotItems3.get(slotItemPos).getImage());
                    itemTextView.setText(_slotItems3.get(slotItemPos).getText());
                }
                default:
                    break;
            }
            return view;
        }
    }


    private void configureWheelLayout()
    {
        // Set the width of the Wheels based on total screen width.
        // So that it is well calculated on all devices.
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        final float density = getResources().getDisplayMetrics().density;
        int marginBetweenWheels = (int) (5 * density + 0.5f);
        int wheelWidth;
        int wheelHeight;
        boolean isPortrait = false;;

        if (width > height) {
            //Landscape: Take 70% of screen width
            width = (width * 65 )/100;
            wheelHeight = (height * 60)/100;
        } else {
            isPortrait = true;
            //Portrait: Take 90% of screen width
            width = (width * 90 )/100;
            wheelHeight = (height * 40)/100;
        }

        //Subtract the margin between two wheels
        width -= ( 2 * marginBetweenWheels);

        //Divide the total number of wheels ( which is 3 for this slot machine)
        wheelWidth = width / 3;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) wheelView1.getLayoutParams();
        params.width = wheelWidth;
        params.height = wheelHeight;

        params = (LinearLayout.LayoutParams) wheelView2.getLayoutParams();
        params.width = wheelWidth;
        params.height = wheelHeight;
        params.leftMargin = marginBetweenWheels;

        params = (LinearLayout.LayoutParams) wheelView3.getLayoutParams();
        params.width = wheelWidth;
        params.height = wheelHeight;
        params.leftMargin = marginBetweenWheels;

        View thickLine1 = (View) findViewById(R.id.thickLine1);
        View thickLine2 = (View) findViewById(R.id.thickLine2);
        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) thickLine1.getLayoutParams();
        params1.width = width + ( 2 * marginBetweenWheels);

        params1 = (RelativeLayout.LayoutParams) thickLine2.getLayoutParams();
        params1.width = width + ( 2 * marginBetweenWheels);

        if (isPortrait == false) {
            //If landscape set the width for result layout
            params1 = (RelativeLayout.LayoutParams) ((View) findViewById(R.id.slotSpinLayout)).getLayoutParams();
            params1.width = wheelWidth + wheelWidth/2;
        }
    }

    private void configureWheels()
    {
        List<ISlotMachineItem> slotItemsView1 = new ArrayList<ISlotMachineItem>();
        List<ISlotMachineItem> slotItemsView2 = new ArrayList<ISlotMachineItem>();
        List<ISlotMachineItem> slotItemsView3 = new ArrayList<ISlotMachineItem>();
        //Set the slot items for each wheels.
        for (int i = 0; i<_slotItems1.size();i++) {
            slotItemsView1.add(new SlotItemView(1,i));
            slotItemsView2.add(new SlotItemView(2,i));
            slotItemsView3.add(new SlotItemView(3,i));
        }

        wheelView1.setSlotItems(slotItemsView1);

        wheelView2.setSlotItems(slotItemsView2);

        wheelView3.setSlotItems(slotItemsView3);

        wheelView1.setNumberOfVisibleItems(3);
        wheelView2.setNumberOfVisibleItems(3);
        wheelView3.setNumberOfVisibleItems(3);

        wheelView1.setWheelBackground(getResources().getDrawable(R.drawable.wheel_frame));
        wheelView2.setWheelBackground(getResources().getDrawable(R.drawable.wheel_frame));
        wheelView3.setWheelBackground(getResources().getDrawable(R.drawable.wheel_frame));

        wheelView1.setWheelScrollingDirection(false);
        wheelView2.setWheelScrollingDirection(true);
        wheelView3.setWheelScrollingDirection(false);
        //wheelView1
        wheelView1.setScrollFinishedListener(new WheelView.OnScrollFinishedListener() {
            @Override
            public void onWheelFinishedScrolling(int position) {
                wheel1Selection = position;
            }
        });

        wheelView2.setScrollFinishedListener(new WheelView.OnScrollFinishedListener() {
            @Override
            public void onWheelFinishedScrolling(int position) {
                wheel2Selection = position;
            }
        });

        wheelView3.setScrollFinishedListener(new WheelView.OnScrollFinishedListener() {
            @Override
            public void onWheelFinishedScrolling(int position) {
                wheel3Selection = position;
            }
        });
    }
    private void startWheelsMovement()
    {
        //mediaPlayer.start();
        Random random = new Random();
        spin.setEnabled(true);

        // Vary the time and distance range to obtain
        // randomness of wheel scrolling.
        int randomMultipler = random.nextInt(9);

        int randomValue =  4000 + ( 100 * randomMultipler);
        wheelView1.scroll(randomValue, 1000);

        //randomMultipler = random.nextInt(18);
        wheelView2.scroll(randomValue*2, 2000);


        //randomMultipler = random.nextInt(26);
        wheelView3.scroll(randomValue*3, 3000);

        Message msg = Message.obtain();
        msg.what = MESSAGE_CHECK_MATCH;
       // detectAnyMatchHandler.sendMessageDelayed(msg, SPIN_TIME + 1000);
    }


    @OnClick(R.id.spin)
    public void spinBtnTaped(){

        startWheelsMovement();
       // _presenter.getSlotMachineItems();
//        if(validateFields()) {
//            _presenter.startLogin();
//        }

    }



}

