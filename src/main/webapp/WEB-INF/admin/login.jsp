<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="iview/iview.css">
    <script src="iview/vue.js"></script>
    <script src="iview/iview.min.js"></script>
    <script src="iview/jquery-3.4.1.min.js"></script>

    <style>
        body, #app {
            display: flex;
            justify-content: center;
            align-items: center;
            position: absolute;
            right: 20px;
        }

        #app {
            background-color: #97979514;
        }

        h2 {
            margin-bottom: 25px;
            text-align: center;
            font-size: 24px;
            color: #ebece8;
        }

        form {
            width: 300px;
        }

        body {
            width: 100%;
            height: 100%;
            background:url(<%=basePath%>iview/images/login.jpg) no-repeat;/*不添加ideal会报错，但不影响运行的，无关紧要*/
            background-size: cover;
        }
    </style>
</head>
<body>
<div id="app" style="width: 500px;height: 600px">
    <i-Form ref="formInline" :model="formInline" :rules="ruleInline">
        <Form-Item>
            <h2>欢迎登录</h2>
        </Form-Item>
        <Form-Item prop="username">
            <i-Input type="text" size="large" v-model="formInline.username" placeholder="Username">
                <Icon type="ios-contact" slot="prefix"/>
            </i-Input>
        </Form-Item>
        <Form-Item prop="password" :error="showError">
            <i-Input type="password" password size="large" v-model="formInline.password" placeholder="Password"
                     @keyup.enter.native="click_enter">
                <Icon type="ios-lock" slot="prefix"></Icon>
            </i-Input>
        </Form-Item>
        <Form-Item prop="interest">
            <Checkbox-Group v-model="formInline.interest">
                <Checkbox label="记住密码"></Checkbox>

            </Checkbox-Group>
        </Form-Item>
        <Form-Item>
            <i-Button type="primary" size="large" long @click="handleSubmit('formInline')">登录</i-Button>
        </Form-Item>
        <Form-Item>
            <div style="text-align: center">
                <div style=" width: auto; display: inline-block;">
                    <a href="https://github.com/login/oauth/authorize?client_id=3ca472fea411731bca58&state=github"
                       style="{right: 26px;}">
                        <img class="icon" src="iview/images/GitHub.svg" style="width: 35px;height: 40px;"/>
                    </a>
                </div>
                <div style=" width: auto; display: inline-block;">
                    <a href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101525509&redirect_uri=http://127.0.0.1/Admin/qqLoginAccessToken&state=qq"
                       style="{right: 26px;}">
                        <img class="icon" src="iview/images/social-qq.svg" style="width: 35px;height: 40px;"/>
                    </a>
                </div>
            </div>
        </Form-Item>
    </i-Form>

</div>

<script>
    new Vue({
        el: '#app',
        data: {
            formInline: {
                username: 'admin',
                password: '123456'
            },
            ruleInline: {
                username: [
                    {required: true, message: '请输入您的用户名', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                    {type: 'string', min: 6, message: '密码需要超过6位', trigger: 'blur'}
                ]
            },
            showError: '',
        },
        created() {
            if (window != window.top) {/*如果当前登录页面不是第一级窗口，就重新请求登录页面，解决登录页面嵌套*/
                window.top.location.href = "Admin/login"
            }
            var page = this
            document.onkeydown = function (e) {/*全局键盘事件*/
                let key = window.event.keyCode;
                if (key == 13) {
                    page.click_enter();
                }
            }
        },
        methods: {
            click_enter() {/*回车事事件*/
                this.handleSubmit('formInline'); /*触发表单*/
            }
            ,
            handleSubmit(name) {
                var page = this;
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        $.ajax({
                            type: "POST",
                            contentType: "application/x-www-form-urlencoded",
                            url: "Admin/login",
                            data: this.formInline,
                            dataType: 'json',
                            async: false,/*取消异步加载*/
                            traditional: true,//防止深度序列化
                            success: function (result) {
                                if (result.success) {/*登录成功跳转*/
                                    window.location.href = "Admin/admin";
                                } else {
                                    page.showError = result.msg;/*信息不能重复，否则error只显示一次*/
                                    //page.$Message.error(result.errorMessage);
                                }
                            }
                        });
                    }
                })
            }
        }
    })
</script>
</body>
</html>
