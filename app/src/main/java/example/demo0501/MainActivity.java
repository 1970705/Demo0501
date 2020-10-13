package example.demo0501;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)this.findViewById(R.id.id_listview);
        MyListAdapter listAdapter = new MyListAdapter();
        listView.setAdapter(listAdapter);
    }

    class MyListAdapter extends BaseAdapter
    {
        ArrayList<Student> students = new ArrayList<Student>();
        Drawable ic_male,ic_female;

        public MyListAdapter()
        {
            students.add( new Student(20080001, "shao", true));
            students.add( new Student(20080002, "wang", true));
            students.add( new Student(20080003, "li", false));
        }
        @Override
        public int getCount()   // 获取行数
        {
            return students.size();
        }

        // 获取某行数据
        // 你的AS生成的参数名可能有点差异，但没有影响，只要参数类型一致就行
        @Override
        public Object getItem(int position)
        {
            return students.get(position);
        }

        @Override
        public long getItemId(int position) // 暂时不管
        {
            return position;
        }

        // 获取某一行的显示
        // 你的AS生成的参数名可能有点差异，但没有影响，只要参数类型一致就行
        @Override
        public View getView(int position, View convertView, ViewGroup parent)  //获取显示某一行的控件
        {
            // View : 如何创建View是一个难点，后面会有展开说明

            if(convertView == null)
            {
                //view = new TextView(MainActivity.this);
                //现在，同inflate方法，把xml布局文件转换为控件(一个View)对象
                convertView = getLayoutInflater().inflate(R.layout.layout_studengt_item,parent,false);
                /*
                拆分来写
                LayoutInflater layoutInflater = MainActivity.this.getLayoutInflater();
                convertView = layoutInflater.inflate(R.layout.layout_studengt_item,parent,false);
                 */

                //第二个参数parent:父控件
                /*
                (2) inflate() 方法用于将XML转换成View控件
                第一个参数为资源ID，第二个参数为父控件，第三个参数设为false
                所谓convertView ，convert就是转换的意思。
                所谓父控件，就是getView() 方法传进来的第3个参数，参数名字可以一般叫parent或root。
                 */
            }
            //此时的convertView是LinearLayout
            //取出TextView(从id_item_text大控件中取出TextView子控件，强转)
            //其中，convertView.findViewById() 表示在convertView下寻找某个子控件
            TextView textView = (TextView)convertView.findViewById(R.id.id_item_text);

            // Data : 将数据显示到View,有了这个convertView，就可以来显示数据了。
            Student data = (Student) getItem(position);
            textView.setText( data.name);

            //设置图标显示
            ImageView iconView = (ImageView)convertView.findViewById(R.id.id_item_icon);
            if(data.sex)
                iconView.setImageDrawable(ic_male);
            else
                iconView.setImageDrawable(ic_female);


            //convertView就代表了这一行的显示，所以最后要返回convertView
            return convertView;  // 获取显示控件
        }
    }
}


