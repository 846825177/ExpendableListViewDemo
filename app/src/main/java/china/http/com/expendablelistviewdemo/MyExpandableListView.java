package china.http.com.expendablelistviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 农民伯伯
 * @version 2017/12/13
 */

public  //为ExpandableListView自定义适配器
class MyExpandableListView extends BaseExpandableListAdapter {
    private ArrayList<String> groups;
    private ArrayList<ArrayList<Entity>> childs;
    private Context context;
    private boolean  flags=false;

    public MyExpandableListView(ArrayList<String> groups, ArrayList<ArrayList<Entity>> childs, Context context) {
        this.groups = groups;
        this.childs = childs;
        this.context = context;
    }

    //返回一级列表的个数
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    //返回每个二级列表的个数
    @Override
    public int getChildrenCount(int groupPosition) { //参数groupPosition表示第几个一级列表
        Log.d("smyhvae", "-->" + groupPosition);
        return childs.get(groupPosition).size();
    }

    //返回一级列表的单个item（返回的是对象）
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    //返回二级列表中的单个item（返回的是对象）
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;  //不要误写成groups[groupPosition][childPosition]
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //每个item的id是否是固定？一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //【重要】填充一级列表
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group, null);
        }
        TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group);
        ImageView mOpen = convertView.findViewById(R.id.mOpen);
        tv_group.setText(groups.get(groupPosition));
        if (isExpanded) {
            mOpen.setImageResource(R.mipmap.dowm);
        } else {
            mOpen.setImageResource(R.mipmap.up);
        }
        return convertView;
    }

    //【重要】填充二级列表
    @Override
    public View getChildView(int groupPosition, int childPosition, final boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child, null);
        }
        final ImageView mOpen = convertView.findViewById(R.id.mOpen);
        TextView tv_memory = convertView.findViewById(R.id.tv_memory);
        TextView tv_Color = convertView.findViewById(R.id.tv_Color);
        TextView tv_model = convertView.findViewById(R.id.tv_model);
        final MyRadioGroup mRadioGroup = convertView.findViewById(R.id.mRadioGroup);
        mRadioGroup.removeAllViews();
        tv_memory.setText(childs.get(groupPosition).get(childPosition).getMemory());
        tv_Color.setText(childs.get(groupPosition).get(childPosition).getColor());
        tv_model.setText(childs.get(groupPosition).get(childPosition).getMemory());
        List<String> price = childs.get(groupPosition).get(childPosition).getPrice();

        for (int i = 0; i < price.size(); i++) {
            final RadioButton radioButton = new RadioButton(context);
            radioButton.setPadding(10,5,10,5);
            radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            radioButton.setBackgroundResource(R.drawable.shape_false);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        radioButton.setBackgroundResource(R.drawable.shape_true);
                    }else {
                        radioButton.setBackgroundResource(R.drawable.shape_false);

                    }
                }
            });
            radioButton.setText(price.get(i));
            mRadioGroup.addView(radioButton);
            mOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!flags){
                        mOpen.setImageResource(R.mipmap.dowm);
                        mRadioGroup.setLineModel(true);
                        Log.e("TAG",flags+"");
                        flags=true;
                    }else {
                        Log.e("TAG",flags+"");
                        mOpen.setImageResource(R.mipmap.up);
                        mRadioGroup.setLineModel(false);
                        flags=false;
                    }
                }
            });
        }


        return convertView;
    }

    //二级列表中的item是否能够被选中？可以改为true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
