package com.liyafeng.view.flow;

public class Main {


    /**
     * 鸿祥的标签流控件
     * https://github.com/hongyangAndroid/FlowLayout
     *
     *
     *  <com.zhy.view.flowlayout.TagFlowLayout
     *         android:id="@+id/id_flowlayout"
     *         zhy:max_select="-1"
     *         android:layout_width="fill_parent"
     *         android:layout_height="wrap_content"
     *         android:padding="20dp">
     *     </com.zhy.view.flowlayout.TagFlowLayout>
     *
     * max_select：-1为不限制选择数量，>=1的数字为控制选择tag的数量
     *
     * 支持通过state=checked来控制选中和取消,也可以自己在Adapter 的onSelected和unSelected中分别处理显示
     *
     * mFlowLayout.setAdapter(new TagAdapter<String>(mVals)
     *    {
     *        @Override
     *        public View getView(FlowLayout parent, int position, String s)
     *        {
     *            TextView tv = (TextView) mInflater.inflate(R.layout.tv,
     *                    mFlowLayout, false);
     *            tv.setText(s);
     *            return tv;
     *        }
     *    });
     *
     *    设置个background，上面一个状态为android:state_checked，另一个为正常。写写布局文件我都嫌慢，怎么能写一堆代码控制效果，设置改个效果，岂不是没时间dota了。
     *
     *
     * mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
     * {
     *   @Override
     *   public boolean onTagClick(View view, int position, FlowLayout parent)
     *   {
     *       Toast.makeText(getActivity(), mVals[position], Toast.LENGTH_SHORT).show();
     *       return true;
     *   }
     * });
     *
     *
     * 点击标签时的回调。
     *
     * mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener()
     * {
     *   @Override
     *   public void onSelected(Set<Integer> selectPosSet)
     *   {
     *       getActivity().setTitle("choose:" + selectPosSet.toString());
     *   }
     * });
     *
     *
     * #预先设置Item选中
     *
     * //预先设置选中
     * mAdapter.setSelectedList(1,3,5,7,8,9);
     * //获得所有选中的pos集合
     * flowLayout.getSelectedList();
     *
     * @param args
     */
    public static void main(String[] args) {

//        也可以不依赖state_checked,在下面的回调中自行设置:
//
//#Adapter
//        @Override
//        public void onSelected(int position, View view) {
//            super.onSelected(position, view);
//        }
//
//        @Override
//        public void unSelected(int position, View view) {
//            super.unSelected(position, view);
//        }
    }
}
