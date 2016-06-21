package com.soin.digitaldynamics.Model.SlotMachine;

/**
 * Created by neilgarciavargas on 21/6/16.
 * Slot item model, this class contain a image and text about the item.
 */
public class MSlotItem {
    private int _image = 0;
    private String _text = "";

    public MSlotItem(int image, String text) {
        _image = image;
        _text = text;
    }

    /**
     * Get Text
     *
     * @return Text as String
     */
    public String getText() {
        return _text;
    }

    /**
     * Set Text
     */
    public void setText(String text) {
        _text = text;
    }

    /**
     * Get Image
     *
     * @return Image as $field.typeName
     */
    public int getImage() {
        return _image;
    }

    /**
     * Set Image
     */
    public void setImage(int image) {
        _image = image;
    }

    @Override
    public String toString() {
        return "MSlotItem{" +
                "_image=" + _image +
                ", _text='" + _text + '\'' +
                '}';
    }


}
