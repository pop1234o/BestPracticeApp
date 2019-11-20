package com.liyafeng.architecture.mvc;

public class Main_MVC {

    /**
     * mvc
     * view,controller,model
     * view持有controller，controller 不持有view
     * controller持有model ，model不持有controller
     * model持有view(可以是通过handler将数据发送到activity中进行ui更新)，将数据更新到ui
     * 他们的关系是一个顺时针的闭环
     *
     * ========Android中的mvc=====
     * https://www.tianmaying.com/tutorial/AndroidMVC
     *
     * Android中的activity可以看做是一个controller
     * activity中持有model，model中有callback，callback中更新视图
     * 当然这样会使得activity的代码臃肿，所以我们新建一个controller类，
     * 把activity中的一些逻辑放入到controller类中
     *
     * 伪代码：
     * class Activity{   //controller
     *     model.getData(new Callback(data){  //model
     *         view.setText(data.text);  //view
     *     });
     * }
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
