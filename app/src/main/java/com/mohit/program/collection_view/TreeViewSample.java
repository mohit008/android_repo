package com.mohit.program.collection_view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mohit.program.R;
import com.mohit.program.util.Bean;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Author @ Mohit Soni on 11-05-2018 11:40 AM.
 */

public class TreeViewSample extends Activity {

    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_download);

        linear = (LinearLayout) findViewById(R.id.linear);
        linear.setVisibility(View.VISIBLE);

        LoadJson();
    }

    /**
     * load json from local file
     */
    public void LoadJson() {
        String json_string = "";
        try {
            InputStream in = getAssets().open("a.json");
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            json_string = new String(buffer, "UTF-8");
            in.close();
            parseJson(json_string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * extract data from json
     *
     * @param json
     */
    public void parseJson(String json) {
        try {

            JSONObject jObj = new JSONObject(json);
            JSONArray root = jObj.getJSONArray("root");
            ArrayList<Bean> rootBean = new ArrayList<>();
            for (int i = 0; i < root.length(); i++) {
                JSONObject root_obj = (JSONObject) root.getJSONObject(i);

                Bean bean = new Bean();
                bean.setText(root_obj.getString("text"));
                bean.setId(root_obj.getString("id"));

                JSONArray sub_root = root_obj.getJSONArray("child_tree");
                ArrayList<Bean> subrootBean = new ArrayList<>();
                for (int j = 0; j < sub_root.length(); j++) {
                    JSONObject subroot_obj = (JSONObject) sub_root.getJSONObject(j);

                    Bean bean_ = new Bean();
                    bean_.setText(subroot_obj.getString("text"));
                    bean_.setId(subroot_obj.getString("id"));

                    JSONArray sub_sub_root = subroot_obj.getJSONArray("child_tree");
                    ArrayList<Bean> subsubrootBean = new ArrayList<>();
                    for (int k = 0; k < sub_sub_root.length(); k++) {
                        JSONObject sub_sub_root_obj = (JSONObject) sub_sub_root.getJSONObject(k);

                        Bean bean__ = new Bean();
                        bean__.setText(sub_sub_root_obj.getString("text"));
                        bean__.setId(sub_sub_root_obj.getString("id"));

                        subsubrootBean.add(bean__);
                    }
                    bean_.setArrayList(subsubrootBean);
                    subrootBean.add(bean_);
                }
                bean.setArrayList(subrootBean);
                rootBean.add(bean);
            }
            Log.i("json", root.toString());
            createTree(rootBean);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * create a tree with data
     *
     * @param Bean
     */
    public void createTree(ArrayList<Bean> Bean) {
        TreeNode node = TreeNode.root();
        for (Bean bean : Bean) {

            LeafItem parentnodeItem = new LeafItem();
            parentnodeItem.text = bean.getText();
            parentnodeItem.icon = R.drawable.plus;
            TreeNode parent = new TreeNode(parentnodeItem).setViewHolder(new MyTreeHolder(this));

            ArrayList<Bean> subBean = new ArrayList<>();
            subBean = bean.getArrayList();
            for (Bean bean1 : subBean) {
                LeafItem subnodeItem = new LeafItem();
                subnodeItem.text = "  " + bean1.getText();
                subnodeItem.icon = R.drawable.plus;
                TreeNode subnode = new TreeNode(subnodeItem).setViewHolder(new MyTreeHolder(this));

                ArrayList<Bean> subsubBean = new ArrayList<>();
                subsubBean = bean1.getArrayList();
                for (Bean bean2 : subsubBean) {
                    LeafItem subsubnodeItem = new LeafItem();
                    subsubnodeItem.text = "    " + bean2.getText();
                    subsubnodeItem.icon = R.drawable.plus;
                    TreeNode subsubnode = new TreeNode(subsubnodeItem).setViewHolder(new MyTreeHolder(this));

                    ArrayList<Bean> subsub_Bean = new ArrayList<>();
                    subsub_Bean = bean2.getArrayList();
                    for (Bean bean3 : subsub_Bean) {
                        LeafItem subsubnode_Item = new LeafItem();
                        subsubnode_Item.text = "      " + bean3.getText();
                        subsubnode_Item.icon = R.drawable.plus;
                        TreeNode subsubsubnode = new TreeNode(subsubnode_Item).setViewHolder(new MyTreeHolder(this));

                        subsubnode.addChild(subsubsubnode);
                    }
                    subnode.addChild(subsubnode);
                }
                parent.addChild(subnode);
            }
//            LeafItem childnode0 = new LeafItem();
//            childnode0.text = "ChildNode0";
//            childnode0.icon = R.drawable.ic_launcher_background;
//            TreeNode child0 = new TreeNode(childnode0).setViewHolder(new MyTreeHolder(TreeMainActivity.this));
//
//            LeafItem childnode1 = new LeafItem();
//            childnode1.text = "ChildNode1";0
//            childnode1.icon = R.drawable.ic_launcher_background;
//
//            TreeNode child1 = new TreeNode(childnode1).setViewHolder(new MyTreeHolder(TreeMainActivity.this));
//
//            LeafItem subchildnode1 = new LeafItem();
//            subchildnode1.text = "SubChildNode1";
//            subchildnode1.icon = R.drawable.ic_launcher_background;

//            TreeNode subchild1 = new TreeNode(subchildnode1).setViewHolder(new MyTreeHolder(TreeMainActivity.this));
//            subchild1.setClickListener(new TreeNode.TreeNodeClickListener() {
//                @Override
//                public void onClick(TreeNode node, Object value) {
//                    LeafItem iconItem = (LeafItem) value;
//                    Toast.makeText(TreeMainActivity.this,iconItem.text,Toast.LENGTH_SHORT).show();
//                }
//            });


//            child1.addChild(subchild1);
//            parent.addChildren(child0, child1);
            node.addChild(parent);
        }
        AndroidTreeView tView = new AndroidTreeView(this, node);
        tView.setSelectionModeEnabled(true);
        linear.addView(tView.getView());
    }

    public class MyTreeHolder extends TreeNode.BaseNodeViewHolder<LeafItem> {

        Context context;

        public MyTreeHolder(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        public View createNodeView(final TreeNode node, LeafItem value) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(android.R.layout.activity_list_item, null, false);
            TextView tvValue = (TextView) view.findViewById(android.R.id.text1);
            final ImageView image = (ImageView) view.findViewById(android.R.id.icon);

            if (node.size() > 0) {
                image.setBackgroundResource(R.drawable.plus);
            }

            node.setClickListener(new TreeNode.TreeNodeClickListener() {
                @Override
                public void onClick(TreeNode nodes, Object value) {
                    if (node.isExpanded()) {
                        image.setBackgroundResource(R.drawable.plus);
                    } else {
                        image.setBackgroundResource(R.drawable.minus);
                    }
                }
            });
            tvValue.setText(value.text);
//        if(Constants.checkVersion21()){
//            image.setImageDrawable(context.getResources().getDrawable(value.icon,null));
//        }
            return view;
        }
    }

    public class LeafItem {
        public int icon;
        public String text;
    }
}
