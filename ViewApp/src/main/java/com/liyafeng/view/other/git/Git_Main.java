package com.liyafeng.view.other.git;

public class Git_Main {

    /**
     * gitlab有namespace的概念，
     * 你登录你的账户在 company的命名空间下创建一个项目，然后再fork到你的命名空间下，当然你要有权限
     *
     * =========git work flow========
     * workspace -> index -> local repository -> remote repository
     *  工作区 缓存区 本地仓库 远程仓库
     *
     * ========init=========
     * 在当前目录 git init
     * 在当前目录生成一个 .git 目录
     *
     *
     * =======git add =======
     * 三者的区别
     * git add -A stages all changes
     * git add . stages new files and modifications, without deletions
     * git add -u stages modifications and deletions, without new files
     *
     * stages:暂存
     * (use "git reset HEAD <file>..." to unstage
     *
     *
     * ==========git常用操作==========
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
     * git丢弃工作区 git checkout .  #本地所有修改的。没有的提交的，都返回到原来的状态
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
     * =========删除分支==========
     * git branch -d 分支名 //删除本地分支
     * 如果报错 error: The branch 'xxx' is not fully merged.
     * If you are sure you want to delete it, run 'git branch -D xxx'.
     * git push -d [仓库名] [分支名] //删除远程分支
     *
     * 删除远程分支报错：
     * error: By default, deleting the current branch is denied, because the next
     * remote: 'git clone' won't result in any file checked out, causing confusion.
     * 是因为你删除的分支是远程的默认分支，你需要改变一下默认分支
     *
     * =============================
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
     * <p>
     * git show [commit_hash] 查看某次commit的所有改变 前六位即可
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
     * ---------push所有分支-------
     * git push [originName] --all
     *
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
     *
     * 重命名本地分支
     * git branch -m devel develop
     *
     *
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
     * <p>
     * =======================tag==================
     *
     * https://git-scm.com/book/zh/v1/Git-%E5%9F%BA%E7%A1%80-%E6%89%93%E6%A0%87%E7%AD%BE
     * git tag v1.0 打标签
     *
     * git tag 查看标签列表
     * git tag --list
     * <p>
     * git show <tagname>查看标签信息
     * <p>
     * <p>
     * git push origin v1.0 推送标签到远程
     * git push origin --tags 推送所有标签
     *
     * 获取远程tag
     * git fetch origin tag <tagname>
     * git fetch origin (获取远程所有tag)
     * <p>
     * <p>
     * 【删除远程标签】
     * git tag -d v0.9 删除本地标
     * git push origin :refs/tags/v0.9
     * 或者
     * git push -d origin tagname
     * <p>
     * 查看tag代码（但是不能编辑）
     * git checkout tag_name
     *
     * 如果要在 tag 代码的基础上做修改，你需要一个分支：
     * git checkout -b branch_name tag_name
     *
     *
     * =========================删除远程仓库============
     * git remote rm origin   （只是删除这个名字，并没有真的将远程仓库删除）
     *
     * ==============关联远程分支=========
     *  git branch --set-upstream-to=origin/<branch> master
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
     *
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
     */
    void fun1() {
    }


    /**
     * http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html
     * 格式化的commit message 有利于log信息的筛选，而且用命令能自动生成change log
     * <p>
     * <p>
     * commit提交格式
     * feat：新功能（feature）
     * fix：修补bug
     * docs：文档（documentation）
     * style： 格式（不影响代码运行的变动）
     * refactor：重构（即不是新增功能，也不是修改bug的代码变动）
     * test：增加测试
     * chore：构建过程或辅助工具的变动
     * <p>
     * Header部分只有一行，包括三个字段：type（必需）、scope（可选）和subject（必需）。
     * 类型，影响范围，简短描述
     * <p>
     * Body 部分是对本次 commit 的详细描述，可以分成多行。下面是一个范例。
     * <p>
     * ============================
     * Commitizen是一个撰写合格 Commit message 的工具。
     * 运行下面的命令，使其支持 Angular 的 Commit message 格式。
     * commitizen init cz-conventional-changelog --save --save-exact
     * 凡是用到git commit命令，一律改为使用git cz。这时，就会出现选项，用来生成符合格式的 Commit message。
     *
     * =================git显示远程仓库的地址===========
     * git remote -v
     */
    void fun2() {
    }

    /**
     * https://help.github.com/articles/removing-sensitive-data-from-a-repository/
     * https://blog.csdn.net/meteor1113/article/details/4407209
     * https://git-scm.com/book/zh/v1/Git-%E5%B7%A5%E5%85%B7-%E9%87%8D%E5%86%99%E5%8E%86%E5%8F%B2 (Git 工具 - 重写历史)
     * git 永久删除文件和永久删除文件commit
     *
     * ==============git永久删除文件=========
     * 比如某次commit中存在大文件，或者敏感文件
     * $ git filter-branch --tree-filter 'rm -f passwords.txt' HEAD
     *
     * --tree-filter选项会在每次检出项目时先执行指定的命令然后重新提交结果
     *
     */
    void fun3() {
    }

    /**
     * ====================重置提交==============
     * 删除某次commit
     * git reset --hard <commit_id> 这个是回到这个提交  （丢失历史甚至整个版本库的操作）
     * git push origin HEAD --force  这个是将HEAD指向这个提交
     * <p>
     * ----------------------
     * git reset --hard HEAD~5  回退到5次commit之前
     * git push origin master --force
     * <p>
     * ==========work flow================
     * https://git-scm.com/book/zh/v2/Git-%E5%B7%A5%E5%85%B7-%E9%87%8D%E7%BD%AE%E6%8F%AD%E5%AF%86
     * head总是指向上一次commit
     * work space =>(add)=> index =>(commit)=> Head->master（local repository）
     * <p>
     * git reset  --soft <commit_id>  Head指向这次commit 且 workspace和index都保留变动
     * git reset  --soft HEAD~   回到上一次commit
     * git reset  --soft HEAD~5  上5次
     * <p>
     * git reset --mixed <commit_id>  workspace 会保留<commit_id>之后的变动，index不会
     * <p>
     * git reset --hard <commit_id>   workspace 、index、Head都会重置，
     * //这样我们的commit流中就没有那个commit了，但是我们git数据库中还有
     * //git reflog 查看每一次命令，找到那个commitid，reset到那即可
     *  ===================版本回退==============
     *  git log查看commit的sha1值，就是commit的id
     *  然后git reset --hard [commitid]
     *  就回退到那个版本了
     *  <p>
     *  --hard
     *  Resets the index and working tree. Any changes to tracked files
     *  in the working tree since <commit> are discarded.
     *  <p>
     * 这样HEAD就指向这个commit
     *
     *
     *  git reflog 查看未来版本的commit
     *   然后同样的命令来前进
     *
     *
     */
    void fun4() {
    }

    /**
     * =======显示一行的commit============
     * git log --pretty=oneline
     *
     * 显示分叉结构
     * git log --graph --pretty=oneline --abbrev-commit
     */
    void fun5() {
    }


    /**
     * 取消追踪文件
     * https://www.cnblogs.com/youyoui/p/8337147.html
     *
     * 我们添加.gitignore文件，里面写上需要忽略的文件或者文件夹
     * 子文件夹中可以有自己的忽略规则
     * 忽略规则是先看命令行中定义的忽略规则，再看本文件夹中的忽略规则，再看父文件夹中的忽略规则
     * /bin 忽略根文件中的bin文件夹，子文件中的bin文件夹不匹配
     * bin/忽略bin文件夹下的内容,子目录中所有的bin都会被忽略
     * 我们有的新文件设置ignore无效，是因为他们已经在index中了（查看状态是绿色的），我们需要清除
     * git rm -r --cache file
     *
     * =========
     * 如果文件已经被track，那么需要git rm -r --cache filename 删除文件
     * 然后工作区需要rm filename来进行删除
     *
     * -r 代表递归， --cache代表只删除index上的，本地工作区还保留
     * 然后再写上忽略规则
     * 然后我们git commit -m"update"，
     * 然后git push 即可
     *
     * ----------
     * 我们可以删除.idea下所有文件，但是项目一下就没了，我们需要重新打开ide即可
     * ----------
     *
     * 工作区-add->index-commit->local repository -push -> remote repository
     *
     * remote repository =pull=> 工作区
     * remote repository =fetch/clone=> local repository
     * local repository => checkout => 工作区
     *
     */
    void fun6(){

    }



    /**
     * git 子模块
     * https://git-scm.com/book/zh/v2/Git-%E5%B7%A5%E5%85%B7-%E5%AD%90%E6%A8%A1%E5%9D%97
     * 当我们一个项目中需要另个项目的代码，我们可以拷贝，但是当那个项目代码有更新的时候我们就很麻烦了
     * 所以引入子模块概念，就是一个git项目可以引用另一个git项目当做子模块
     * 同时跟目录产生一个.gitmodules 文件来对应 模块名称和url
     *
     * git clone --recurse-submodules <repo>来拉取子模块的代码，否则子模块只是个空文件夹
     *
     *
     *
     */
    void fun7(){}


    /**
     * 解决冲突
     * 当你拉下代码有冲突的时候，我们解决完冲突，git add filename 就可以标记为已解决了
     */
    void fun8(){}


    /**
     * =======git暂存操作==========
     * https://www.jianshu.com/p/afeeaea8c0c8 stash暂存
     *
     * 当你当前修改代码到一半的时候，你不能commit，或者你写了一些临时的测试代码，
     * 这个时候你要git pull ,代码，你必须保证工作区clean才能pull
     * 所以这个时候我们需要暂存操作
     *
     * git stash 暂存当前改动
     * git stash pop 使用上一次暂存，并将这个暂存删除，使用该命令后，如果有冲突，终端会显示，如果有冲突需要先解决冲突
     * git stash apply 使用上一次缓存，但是不删除
     *
     * git stash pop 1 使用指定缓存队列中的缓存
     *
     * git stash list 查看所有暂存
     * git stash clear 清空所有缓存
     * git stash drop [-q|--quiet] [<stash>] 删除某一个暂存，在中括号里面放置需要删除的暂存ID
     *
     * ----查看stash内容
     * git stash show 默认是0
     * git stash show 0
     *
     * ===========git stash pop/apply冲突==========
     * 找到冲突文件，解决冲突，执行git add . 即可
     *
     * 如果要丢弃，可以git reset HEAD
     * git checkout .
     *
     *
     */
    void fun9(){}


    /**
     * ==========git diff========
     * git diff
     * 工作区和暂存区的区别，如果执行 git add -a 那么所有变动都提交到暂存区了，这个时候diff就没有区别了
     *
     * git diff HEAD
     * 工作区和本地仓库之间的区别
     *
     * git diff --cached
     * 暂存区和本地仓库之间的区别，也就是你git add 后的暂存区和本地仓库之间的区别
     *
     *
     * (use "git reset HEAD <file>..." to unstage)
     * 取消暂存，这个文件是已经通过 git add <file> 加入到暂存区了，取消暂存就是取消暂存区的改动
     *
     *
     * use "git checkout -- <file>..." to discard changes in working directory)
     * 丢弃工作区的变动
     *
     *
     *
     *
     */
    void fun10(){}


    /**
     *
     * ============git rebase============
     * https://www.jianshu.com/p/f7ed3dd0d2d8 (https://www.jianshu.com/p/f7ed3dd0d2d8)
     * 作用：让Git的提交历史是一条干净的直线
     * “变基”,将一条分支的"基于版本"改变
     *
     * git pull --rebase
     *
     * git pull origin dev --rebase
     * 或者直接
     * git rebase
     *
     * A-B-C-E(master)  这个E就是 merge branch master of http:xxx into master
     *    \ /
     *     D(origin/master)
     * C是你基于B开发的，D是同事基于B开发的，而且提交到了origin远程仓库
     * 你这个时候push是被拒绝的，你pull会产生额外的一个合并记录，污染正常的记录
     * 这个时候 git pull origin master --rebase
     * 你的提交（本地未提交到远程的所有commit ）就会被至于顶层，这叫变基(rebase) 你以前是base(基于) B的
     * 现在变成 基于远程提交的D了 （如果有冲突需要解决）
     *
     * A-B-D-C 你开发的C就变基了
     *
     * -------rebase遇到冲突--------
     * 流程是把服务端的commit放到底部，把你未提交的commit一个一个加到顶部
     * 如果遇到某个commit有冲突，那么rebase会停止，
     * 然后你解决冲突，git add . 标记已解决  然后执行  git rebase --continue 即可继续
     * 修改的冲突会合并到你的那次提交中
     *
     * When you have resolved this problem, run "git rebase --continue".
     * If you prefer to skip this patch, run "git rebase --skip" instead.
     * To check out the original branch and stop rebasing, run "git rebase --abort".
     *
     * -----------什么情况不能使用rebase-------
     * ???好像不对。。
     * 在同事合并了你的代码以后，并且增加了提交就不能使用git rebase了，这时他已经在你的提交节点上产生了新的提交节点，
     * 如果此时你在本地使用git rebase你们两者的提交历史将会不一致，再次合并时又会产生一个全新的合并记录，
     * 这样git rebase就失去了意义。（一般多人开发时基本不用git rebase这个命令，因为你大多数情况下是不知道同事是否已经提交过代码的）
     *
     * ------------git为什么会产生分叉？----------
     * https://www.cnblogs.com/Sinte-Beuve/p/9195018.html （Git push 时如何避免出现 "Merge branch 'master' of ..."）
     *
     * 简单说就是基于同一个提交版本做出不同的改动，会产生分叉
     *
     * A-B-C(master)
     *    \
     *     D(origin/master)
     * 我当前拉取的远端版本为 B，修改代码后commit，产生C
     * 同时另一个同事基于B，同样进行commit D ,然后push到远程分支
     * 我这个时候push会报错
     * ! [rejected]        master -> master (fetch first)
     * error: failed to push some refs to 'git@github.com:xxx/xxx.git'
     * hint: Updates were rejected because the remote contains work that you do
     * hint: not have locally. This is usually caused by another repository pushing
     * hint: to the same ref. You may want to first integrate the remote changes
     * hint: (e.g., 'git pull ...') before pushing again.
     * 大概意思就是远程仓库有一些你本地没有的提交，你需要先git pull 来整合一下
     *
     * 此时我们查看 log 就会发现除了我们自己提交的那条日志之外，会多出一条 "Merge branch 'master' of ..."
     * 在进行 pull 操作的同时，其实就是 fetch+merge 的一个过程
     *
     * 如果远程仓库超前于本地分支，并且本地分支没有任何 commit 的，那么直接pull不会产生额外的log
     *
     *
     * # fast-forword
     * A-B-D(origin/master)
     *      \
     *       C'(master)
     *
     * # merge
     * A-B-C-E(master)
     *    \ /
     *     D(origin/master)
     *
     *
     *
     *
     */
    void fun11(){}


    /**
     * ==========gerrit-vs-gitlab==========
     * https://about.gitlab.com/devops-tools/gerrit-vs-gitlab.html (Gerrit vs. GitLab)
     * https://blog.csdn.net/bjstyle/article/details/79107086 (GitLab 与 Gerrit对比)
     *
     * Gerrit is a free, web-based team code collaboration(合作) tool
     *
     *
     */
    void fun12(){}

}
