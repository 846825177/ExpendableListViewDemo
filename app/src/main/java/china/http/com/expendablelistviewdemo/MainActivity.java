package china.http.com.expendablelistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
   private ArrayList<String> groups  = new ArrayList<>();
    private ArrayList<ArrayList<Entity>> childs  = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView .setGroupIndicator(null);
              expandableListView.setAdapter(new MyExpandableListView(groups,childs,this));

        initData();
    }
    public void initData(){
        for (int i = 0; i < 4; i++) {
            groups.add("IPhone"+(i+6));
            ArrayList<Entity> mList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ArrayList<String> items = new ArrayList<>();
                items.add("10000元");
                items.add("12000元");
                items.add("14000元");
                if(j==1){
                    items.add("16000元");
                    items.add("18000元");
                    items.add("22000元");
                }
                mList.add(new Entity("64G","黑色","A52",items));
            }
            childs.add(mList);
        }
    }




}
