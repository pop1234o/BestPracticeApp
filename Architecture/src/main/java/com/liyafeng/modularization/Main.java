package com.liyafeng.architecture;

/**
 * Created by liyafeng on 2019/07/04.
 */

public class Main {

    /**
     *
     * 组件化
     */
    public static void main(String[] args) {

    }

    /**
     * 问题
     *
     * Resource ID In Android Library Project
     *
     * R.id.xxx是变量，所以不能在Library中用switch
     * 得用if
     * As others have pointed out, you need to change your switch() statement to if()/else if()/else statements. R.id.menu_search is not a constant (static final) and cannot be used in a case statement. That is because R.id.menu_search is coming from your Android library project. android.R.id.home is a constant, because that is part of the OS and is not changing.
     *
     *
     */
    void a1() {
    }


    /**
     * 设计思想
     * =======配置文件==========
     * 很多软件把很多参数写入配置文件，这样增加了软件的灵活性，比如ss的配置文件
     */
    void a2() {
    }
}
