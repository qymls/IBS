<%--
  Created by IntelliJ IDEA.
  User: qymls
  Date: 2020/3/28
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .ivu-tabs-bar {
        height: fit-content;
        display: none;
    }

    .setting-account-info-avatar {
        width: 90px;
        height: 90px;
    }

    .ivu-row-flex {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }

</style>
<div id="app">
    <div>
        <Row style="margin-top: 20px;">
            <i-col :xs="{span: 0}" :lg="{span: 6}" :xl="{span: 6}" :sm="{span: 0}" :md="{span: 0}"
                   style="padding-left: 12px; padding-right: 12px;">
                <Card>
                    <p slot="title">
                        <Icon type="md-options" size="20"></Icon>
                        {{title}}
                    </p>
                    <Cell-Group @on-click="settingChang">
                        <Cell v-for="item in baseInfo" :title="item.name" :label="item.deslabel"
                              :name="item.name"></Cell>
                    </Cell-Group>

                </Card>
            </i-col>
            <i-col :xs="{span: 24}" :lg="{span: 18}" :xl="{span: 18}" :sm="{span: 24}" :md="{span: 24}"
                   style="padding-left: 12px; padding-right: 12px;">
                <Card>

                    <h2>{{activeTab}}</h2>


                    <Tabs :animated="false" v-model="activeTab">
                        <Tab-Pane label="基本设置" name="基本设置">
                            <div style="padding-top: 20px">
                                <i-Form ref="formInline" autocomplete="off" :model="formInline" :rules="ruleInline"
                                        label-position="top">
                                    <Row class="ivu-row-flex" style="margin-left: -24px; margin-right: -24px;">
                                        <i-col :xs="{ span: 24, order: 1 }" :sm="{ span: 24, order: 1 }"
                                               :md="{ span: 24, order: 1 }" :lg="{ span: 24, order: 1 }"
                                               :xl="{ span: 12, order: 2 }"
                                               style="padding-left: 24px; padding-right: 24px;">
                                            <Form-Item label="头像">
                                                <div class="demo-avatar">
                                                    <Avatar class="setting-account-info-avatar" shape="square"
                                                            size="large"
                                                            src="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"/>
                                                </div>
                                                <div class=""
                                                     style="margin-top: 16px!important;margin-bottom: 16px!important;">
                                                    <Upload action="//jsonplaceholder.typicode.com/posts/">
                                                        <i-Button icon="md-camera">更改头像</i-Button>
                                                    </Upload>
                                                </div>
                                            </Form-Item>
                                        </i-col>
                                        <i-col :xs="{ span: 24, order: 2 }" :sm="{ span: 24, order: 2 }"
                                               :md="{ span: 24, order: 2 }" :lg="{ span: 24, order: 2 }"
                                               :xl="{ span: 12, order: 1 }"
                                               style="padding-left: 24px; padding-right: 24px;">
                                            <Form-Item label="昵称" prop="nickname">
                                                <i-Input type="text" v-model="formInline.nickname"/>
                                            </Form-Item>
                                            <Form-Item label="邮箱" prop="email">
                                                <i-Input type="text" v-model="formInline.email"/>
                                            </Form-Item>
                                            <Form-Item label="个人介绍" prop="introduction">
                                                <i-Input type="textarea" v-model="formInline.introduction"/>
                                            </Form-Item>
                                            <Form-Item label="公司" prop="company">
                                                <i-Input type="text" v-model="formInline.company"/>
                                            </Form-Item>
                                            <Form-Item label="居住地" prop="address">
                                                <i-Input type="text" v-model="formInline.address"/>
                                            </Form-Item>
                                            <Form-Item>
                                                <i-Button type="primary" @click="handleSubmit('formInline')">更新基本信息
                                                </i-Button>
                                            </Form-Item>
                                        </i-col>
                                    </Row>
                                </i-Form>
                            </div>
                        </Tab-Pane>
                        <Tab-Pane label="安全设置" name="安全设置">安全设置</Tab-Pane>
                        <Tab-Pane label="账号绑定" name="账号绑定">账号绑定</Tab-Pane>
                    </Tabs>

                </Card>
            </i-col>

        </Row>
    </div>
</div>
<script>
    new Vue({
        el: "#app",
        data: function () {
            return {
                title: "个人设置",
                activeTab: null,
                baseInfo: [],
                formInline: {
                    user: '',
                    password: '',
                },
                ruleInline: {
                    user: [
                        {required: true, message: 'Please fill in the user name', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: 'Please fill in the password.', trigger: 'blur'},
                        {
                            type: 'string',
                            min: 6,
                            message: 'The password length cannot be less than 6 bits',
                            trigger: 'blur'
                        }
                    ]
                }

            }
        },
        mounted() {
            this.baseInfo = [{name: "基本设置", deslabel: "个人信息账户设置"}, {name: "安全设置", deslabel: "密码，邮箱设置"},
                {name: "账号绑定", deslabel: "绑定第三方社交账户"}]
            this.activeTab = "基本设置"
        },
        created() {

        },
        methods: {
            settingChang(name) {
                this.activeTab = name//设置tabs的activeTab
            }
        },

    });
</script>
