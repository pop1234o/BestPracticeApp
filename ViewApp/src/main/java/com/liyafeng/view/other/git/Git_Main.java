package com.liyafeng.view.other.git;

public class Git_Main {

    /**
     * github新建个Repository，然后本地git init 一个仓库
     * 本地添加远程 git remote add origin git@github.com:pop1234o/Algorithm.git
     * git pull orgin
     * <p>
     * git remote add [name] [url] //添加一个远程仓库
     * git pull [name]
     * <p>
     * git push
     * <p>
     * git 查看修改 git diff 这个是查看暂存区的区别，就是git add 之前的文件区别
     * git 查看状态 git status 查看本地的状态，commit之前的
     * git status -v 查看具体的改动
     * <p>
     * git丢弃工作区 git checkout .
     * <p>
     * git添加 git add .
     * git 提交 git commit -m"aa"
     * git 推送 git push 远程名称 （不写默认是关联的远程分支）
     * <p>
     * git push --set-upstream origin master (如果没有关联则要关联一下)
     * <p>
     * <p>
     * 添加一个远程仓库
     * git remote add 远程名称 地址
     * git remote remove 删除远程
     * <p>
     * 更新远程仓库的分支信息 fetch
     * git remote update [-p] [remote]
     * -p代表prune 更新同时删除远程已经删除的分支的本地关联
     * <p>
     * 从远程拉取 到当前分支！
     * git pull 远程名 分支 （默认是关联的分支拉）
     * <p>
     * 关联的远程的仓库一般是一个origin和一个upstream
     * <p>
     * 删除本地分支
     * git branch -d 分支名
     * <p>
     * 拉取远程分支到本地 创建的一个新分支，并切换到这个分支
     * git checkout -b  本地新分支名  remote仓库名/远程分支名
     * -b代表 new_Branch的意思
     * <p>
     * 推送本地分支到远程（并 创建远程分支 然后关联）
     * git push -u 远程仓库名 分支名
     * 这里要用空格分割！
     * -u 代表关联 ，- set_Upstream
     * <p>
     * <p>
     * 查看当前分支的commit
     * git log [文件名]
     * <p>
     * 按q退出
     *
     * git show [commit_hash] 查看某次commit的所有改变
     * -----------------------
     * git 初始化，我们提交代码要设置 用户名 和 邮箱
     * <p>
     * git config --global user.name "pop1234o"
     * git config --global user.email "859405648@qq.com"
     * 这个要设置，否则commit记录那个色块就没有了
     * 然后我们第一次push 到master ，关联，会弹出框要求我们填写github的用户名和密码
     * <p>
     * 这时时编辑的是用户目录下的 .gitconfig 文件
     * 查看git 配置
     * git config --list
     * <p>
     * 我们这里还可以指定文本编辑器，和差异比较工具
     * <p>
     * >参考 https://git-scm.com/book/zh/v1/起步-初次运行-Git-前的配置
     * <p>
     * ---
     * 新建分支
     * https://git-scm.com/book/zh/v1/Git-分支-分支的新建与合并
     * <p>
     * git checkout -b [name]  //新建本地分支
     * //相当于
     * git branch [name]
     * git checkout [name]
     * <p>
     * <p>
     * git branch   //查看本地分支
     * //本地分支推送到远程，相当于远程新建了一个分支
     * git push origin branch_fast:branch_fast
     * //查看所有分支
     * git branch -a
     * <p>
     * //查看远程分支
     * git branch -r
     * <p>
     * git branch -vv查看当前本地分支关联的远程分支
     * <p>
     * =================
     * 取消当前分支和远程分支的关联
     * git branch --unset-upstream master
     * <p>
     * <p>
     * git branch --set-upstream origin/master
     * <p>
     * ===========创建一个新的本地分支，并关联到远程=======
     * <p>
     * git checkout -b 分支名
     * 这样就创建了一个新的分支，并且切换到那个分支，这个分支的代码和你当前分支是一样的
     * 然后你git push -u origin 远程分支名
     * 这样远程就会创建一个分支，并且与本地分支关联，以后修改代码就直接
     * git push就能自动推送到 origin/分支名 中了。
     * <p>
     * ====================
     * 在git reomote add origin 地址.git 后，添加了远程仓库
     * 然后需要git fetch 远程仓库名，一下才能获取到远程仓库的信息
     * git fetch origin ，这样就把远程仓库获取到本地了
     * 这个时候我们可以本地代码分支关联到远程了
     * 当前我们可以获取拉取本地没有的远程分支，并且关联
     * <p>
     * git checkout -b branch1.1 origin/branch1.1
     * <p>
     * ============当一个版本发行后===========
     * git checkout master 切换到master分支
     * git merge [分支名] 将分支合并到当前的分支
     * git branch -d [分支] 删除本地的分支
     * git push -d [仓库名] [分支名] 删除远程分支
     *
     * =======================tag==================
     * git tag v1.0 打标签
     * git tag
     *
     * git show <tagname>查看标签信息
     *
     * git tag -d v0.1 删除
     *
     * git push origin v1.0 推送标签到远程
     * git push origin --tags 推送所有标签
     *
     *
     * 【删除远程标签】
     * git tag -d v0.9
     * git push origin :refs/tags/v0.9
     *
     * =========================删除远程仓库============
     * git remote rm origin   （只是删除这个名字，并没有真的将远程仓库删除）
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * Command line instructions
     * <p>
     * <p>
     * Git global setup
     * <p>
     * git config --global user.name "pop"
     * git config --global user.email "859405648@qq.com"
     * <p>
     * Create a new repository
     * <p>
     * git clone http://code.jiaoyinhudong.com/baoguan/Android-InsurView.git
     * cd Android-InsurView
     * touch README.md
     * git add README.md
     * git commit -m "add README"
     * git push -u origin master
     * <p>
     * Existing folder  存在工程，但是没有关联仓库
     * <p>
     * cd existing_folder
     * git init
     * git remote add origin http://code.jiaoyinhudong.com/baoguan/Android-InsurView.git
     * git add .
     * git commit -m "Initial commit"
     * git push -u origin master
     * <p>
     * Existing Git repository 已经存在了一个仓库
     * <p>
     * cd existing_repo
     * git remote rename origin old-origin
     * git remote add origin http://code.jiaoyinhudong.com/baoguan/Android-InsurView.git
     * git push -u origin --all
     * git push -u origin --tags
     * <p>
     * <p>
     * <p>
     * ===================版本回退==============
     * git log查看commit的sha1值，就是commit的id
     * 然后git reset --hard [commitid]
     * 就回退到那个版本了
     * <p>
     * --hard
     * Resets the index and working tree. Any changes to tracked files
     * in the working tree since <commit> are discarded.
     * <p>
     * git reflog 查看未来版本的commit
     * 然后同样的命令来前进
     */
    void a1() {
    }

    /**
     * gitlab
     * gitlab先建立组，然后里面是项目
     * Brandy/courseware
     * 然后我们需要fork到自己的命名空间下
     * yafeng.li/courseware
     * 然后我们本地需要两个远程仓库
     * origin 指向 yafeng.li/courseware
     * originMaster 指向 Brandy/courseware
     * 每次push之前，要先拉取originMaster的分支更新（这个仓库相当于大家都merge request的仓库）
     *
     *
     *
     *
     */
    void fun1(){}



     /**
      * http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html
      * 格式化的commit message 有利于log信息的筛选，而且用命令能自动生成change log
      *
      *
      * commit提交格式
      * feat：新功能（feature）
      * fix：修补bug
      * docs：文档（documentation）
      * style： 格式（不影响代码运行的变动）
      * refactor：重构（即不是新增功能，也不是修改bug的代码变动）
      * test：增加测试
      * chore：构建过程或辅助工具的变动
      *
      * Header部分只有一行，包括三个字段：type（必需）、scope（可选）和subject（必需）。
      * 类型，影响范围，简短描述
      *
      * Body 部分是对本次 commit 的详细描述，可以分成多行。下面是一个范例。
      *
      * ============================
      * Commitizen是一个撰写合格 Commit message 的工具。
      * 运行下面的命令，使其支持 Angular 的 Commit message 格式。
      * commitizen init cz-conventional-changelog --save --save-exact
      * 凡是用到git commit命令，一律改为使用git cz。这时，就会出现选项，用来生成符合格式的 Commit message。
      *
      *
      *
      *
      */
     void fun2(){}

      /**
       * https://help.github.com/articles/removing-sensitive-data-from-a-repository/
       * https://blog.csdn.net/meteor1113/article/details/4407209
       * git 永久删除
       *
       *
       */
      void fun3(){}
}
