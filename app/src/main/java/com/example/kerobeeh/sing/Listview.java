package com.example.kerobeeh.sing;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Listview extends AppCompatActivity {
    Button btnadd;
    EditText name;
    static ArrayList<Listitem>Items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        name = (EditText)findViewById(R.id.name2);


        Items=new ArrayList<Listitem>();
        final MyCutomAdapter myadapter =new MyCutomAdapter(getApplicationContext(),R.layout.listitems);
        ListView ls =(ListView)findViewById(R.id.Listview);


        //


         ls.setAdapter(myadapter);

        btnadd=(Button)findViewById(R.id.btnadd2);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Items.add(new Listitem(name.getText().toString()));
                myadapter.notifyDataSetChanged();
            }
        });




    }

     class MyCutomAdapter extends ArrayAdapter
    {


        public MyCutomAdapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
       return Items.size();
        }

        @Override
        public String getItem (int position) {
        return Items.get(position).name;
        }

        @Override
        public long getItemId(int position) {
       return position;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            LayoutInflater LinfInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view1= LinfInflater.inflate(R.layout.listitems,null);

            TextView txtname = (TextView) view1.findViewById(R.id.textView2);

            txtname.setText(Items.get(i).name);


            return view1;

        }
    }
}
