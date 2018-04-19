package com.reviracio.gui_03;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by reviracio on 12.04.18.
 */

public class RateAdapter extends ArrayAdapter<RateModel> {
    private List<RateModel> RateList;
    private Activity context;

    public RateAdapter(Activity activity, List<RateModel> list) {
        super(activity, R.layout.activity_rate_list, list);
        RateList = list;
        context = activity;

    }

    @Override
    public View getView(int rawNumber, View viewEdit, ViewGroup parent) {
        View viewModel = null;

        if (viewEdit == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            viewModel = layoutInflater.inflate(R.layout.lay_mark, null);
            RadioGroup markGroup = (RadioGroup) viewModel.findViewById(R.id.markRadioGroup);
            markGroup.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(
                                RadioGroup group, int checkButtonId) {
                            updateMarkModel(group, checkButtonId);
                        }
                    });

            markGroup.setTag(RateList.get(rawNumber));
        } else {
            viewModel = viewEdit;
            RadioGroup markGroup = (RadioGroup) viewModel.findViewById(R.id.markRadioGroup);
            markGroup.setTag(RateList.get(rawNumber));
        }
        TextView nameRate = (TextView) viewModel.findViewById(R.id.tvNameRate);
        nameRate.setText(RateList.get(rawNumber).getName());
        RadioGroup rateGrup = (RadioGroup) viewModel.findViewById(R.id.markRadioGroup);
        setMark(rateGrup, rawNumber);
        return viewModel;

    }


    private void setMark(RadioGroup group, int rowNumber) {
        switch (RateList.get(rowNumber).getRate()) {
            case 2:
                group.check(R.id.mark2);
                break;
            case 3:
                group.check(R.id.mark3);
                break;
            case 4:
                group.check(R.id.mark4);
                break;
            case 5:
                group.check(R.id.mark5);
                break;
        }
    }

    private void updateMarkModel(RadioGroup group, int id) {
        RateModel element = (RateModel) group.getTag();
        switch (id) {
            case R.id.mark2:
                element.setRate(2);
                break;
            case R.id.mark3:
                element.setRate(3);
                break;
            case R.id.mark4:
                element.setRate(4);
                break;
            case R.id.mark5:
                element.setRate(5);
                break;
        }
    }

}