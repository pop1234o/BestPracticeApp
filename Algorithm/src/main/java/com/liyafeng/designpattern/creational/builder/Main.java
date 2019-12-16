package com.liyafeng.designpattern.creational.builder;

/**
 * Created by liyafeng on 2018/1/2.
 */

public class Main {

    /**
     * https://design-patterns.readthedocs.io/zh_CN/latest/creational_patterns/builder.html
     * <p>
     * Build是内部类，最后build中new Entity(builder);
     * 然后将属性赋值
     * -------------------
     * 这种模式可以免去 实体类 配置的具体过程
     * <p>
     * 比如某个方法必须要在另一个之前调用，那么这种模式用户不必知道调用顺序
     * 还比如new的时候必须有个默认值，那么builder中可以设置默认值
     * ----------------------
     * 而且一个builder可以创建多个Entity，这样如果修改多个Entity的属性的时候
     * 只需要修改Builder属性即可。提高可维护性
     *
     * ========适用情况=============
     * 配置比较复杂，而且可能要创建多个对象
     * 用这种模式，可以高效配置，链式配置
     * 快速复制对象
     *
     * =============
     * OkHttpClient 就是持有 Builder内部类，Builder.build()
     * 就是new OkHttpClient(this) 通过构造方法吧builder传进去了
     * OkHttpClient 和 Builder成员变量是一样的
     *
     *
     *
     */
    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient.Builder()
                .config1(1)
                .config2(2)
                .build();


        //除了config2其他都和client配置相同
        OkHttpClient client1 = new OkHttpClient.Builder(client)
                .config2(3)
                .build();

    }


    public static class OkHttpClient {
        int config_1;
        int config_2;

        public OkHttpClient() { //使用默认builder
            this(new Builder());
        }

        //这里外部不能访问，要么选择默认builder，要么自己new Builder().build();
        OkHttpClient(Builder builder) {
            this.config_1 = builder.config_1;
            this.config_2 = builder.config_2;
        }


        public static final class Builder {
            private int config_1;
            private int config_2;

            public Builder() {//这里是默认参数
                config_1 = 1;
                config_2 = 2;
            }


            //这时你只有一个client对象，你想稍微修改再创建一个，那么这里就很高效了
            public Builder(OkHttpClient client) {
                config_1 = client.config_1;
                config_2 = client.config_2;
            }

            public Builder config1(int config_1) {
                this.config_1 = config_1;
                return this;
            }

            public Builder config2(int config_2) {

                this.config_2 = config_2;
                return this;
            }

            public OkHttpClient build() {
                return new OkHttpClient(this);
            }
        }
    }


}
