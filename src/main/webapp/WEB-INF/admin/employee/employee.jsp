<%--
  Created by IntelliJ IDEA.
  User: qymls
  Date: 2020/3/28
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .upload_style .ivu-icon {
        line-height: unset;
    }

    .page_class .ivu-icon {
        line-height: unset;
    }
</style>
<style>
    .demo-upload-list {
        display: inline-block;
        width: 60px;
        height: 60px;
        text-align: center;
        line-height: 60px;
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        background: #fff;
        position: relative;
        box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
        margin-right: 4px;
    }

    .demo-upload-list img {
        width: 100%;
        height: 100%;
    }

    .demo-upload-list-cover {
        display: none;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0, 0, 0, .6);
    }

    .demo-upload-list:hover .demo-upload-list-cover {
        display: block;
    }

    .demo-upload-list-cover i {
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
</style>
<div id="app">

    <Card>
        <p slot="title">
            <Icon type="ios-list-box-outline" size="20"></Icon>
            {{title}}
        </p>

        <Row>
            <i-col span="3">
                <i-button type="primary" icon="md-add" @click="newAdd">员工管理</i-button>
                <i-button v-if="rows.length>0" type="error" icon="ios-trash" @click="deleteEmployee">删除</i-button>
            </i-col>

            <i-col span="21">
                <i-Form ref="formInline" :model="formInline" inline style="margin-left: 20px;">
                    <Form-Item prop="username">
                        <i-Input type="text" v-model="formInline.username" placeholder="请输入查找的名称">
                            <Icon type="ios-menu" slot="prepend"></Icon>
                        </i-Input>
                    </Form-Item>
                    <Form-Item>
                        <Row>
                            <Col span="11">
                            <Form-Item prop="age1">
                                <i-Input type="text" v-model="formInline.age1" placeholder="请输入年龄段">
                                </i-Input>
                            </Form-Item>
                            </Col>
                            <Col span="2" style="text-align: center">
                            <p style="display: inline-block;padding-right: 8px;">-</p>
                            </Col>
                            <Col span="11">
                            <Form-Item prop="age2">
                                <i-Input type="text" v-model="formInline.age2" placeholder="请输入年龄段">
                                </i-Input>
                            </Form-Item>
                            </Col>
                        </Row>
                    </Form-Item>


                    <Form-Item>
                        <i-Button type="info" icon="ios-search" @click="handleSubmit('formInline')">查找</i-Button>
                    </Form-Item>
                </i-Form>
            </i-col>

        </Row>

        <Row justify="center" align="middle">
            <div style="margin-top:20px">
                <i-Table row-key="name" :columns="columns" :data="EmployeeData" border max-height="650"
                         @on-selection-change="deleteRows"
                         @on-row-dblclick="updateModelShow">
                    <template slot-scope="{ row, index }" slot="icon_show">
                        <Icon :type="row.icon"></Icon>
                    </template>
                    <template slot-scope="{ row, index }" slot="department_show">
                        <p>{{getDepatmentName(row)}}</p>
                    </template>
                </i-Table>
            </div>
            <div style="margin: 10px;overflow: hidden">
                <div style="float: right;">
                    <Page :total="total" show-total :page-size="pageSize" :page-size-opts="[5,10,20]" :current="page"
                          show-sizer transfer show-elevator
                          @on-change="changePage" @on-page-size-change="sizeChange"
                          class-name="page_class" style="margin-top: 10px;"></Page>
                </div>
            </div>
            <Modal title="员工管理" v-model="updateModel" class-name="vertical-center-modal" footer-hide>
                <i-Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                    <Form-Item prop="id">
                        <input type="hidden" v-model="formValidate.id"/><%--菜单id--%>
                    </Form-Item>
                    <Form-Item label="用户名" prop="username">
                        <i-Input v-model="formValidate.username" placeholder="请输入用户名"></i-Input>
                    </Form-Item>
                    <Form-Item label="密码" prop="password">
                        <i-Input v-model="formValidate.password" placeholder="请输入密码"></i-Input>
                    </Form-Item>
                    <Form-Item label="邮箱" prop="email">
                        <i-Input v-model="formValidate.email" placeholder="请输入邮箱"></i-Input>
                    </Form-Item>
                    <Form-Item label="年龄" prop="age">
                        <i-Input v-model="formValidate.age" placeholder="请输入年龄"></i-Input>
                    </Form-Item>
                    <Form-Item label="部门" prop="department.id">
                        <i-Select v-model="formValidate.department.id">
                            <template v-for="item in departmentAll">
                                <i-Option v-model="item.id">{{item.name}}</i-Option>
                            </template>
                        </i-Select>
                    </Form-Item>
                    <%--<input type="hidden" v-model="formValidate.headImage"/>
                    <Form-Item label="头像">
                        <div class="demo-upload-list">
                            <img src="admin/images/GitHub.svg">
                            <div class="demo-upload-list-cover">
                                <Icon type="ios-eye-outline" @click.native="handleView(item.name)"></Icon>
                                <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
                            </div>
                        </div>

                        <Upload action="#" type="drag" style="width: 58px;display: inline-block;" class="upload_style"
                                :show-upload-list="false">
                            <div style="width: 58px;height:58px;line-height: 58px;">
                                <Icon type="ios-camera" size="20"></Icon>
                            </div>
                        </Upload>
                    </Form-Item>--%>
                    <Form-Item>
                        <i-Button type="primary" @click="handleSubmitUpdate('formValidate')">确认</i-Button>
                        <i-Button @click="handleReset('formValidate')" style="margin-left: 8px">重置</i-Button>
                    </Form-Item>
                </i-Form>

            </Modal>

        </Row>


    </Card>


</div>
<script type="module">
    new Vue({
        el: "#app",
        data: function () {
            const nameplates = (rule, value, callback) => {/*异步验证菜单名称*/
                console.log("input改变了")
                if (value === '') {
                    callback(new Error('请输入菜单名称'));

                } else {
                    const result = this.getRepetitionName(value);
                    if (result != null) {
                        if (result.name != this.menuName) {
                            callback(new Error("该菜单已存在"));/*修改当前菜单，不是重复菜单，去掉这种情况*/
                        } else {
                            callback();/*通过*/
                            console.log("修改当前菜单")
                        }
                    } else {
                        callback();
                    }
                }
            };
            return {
                title: "员工管理",
                rows: [],
                updateModel: false,
                formValidate: {
                    id: '',
                    username: '',
                    password: '',
                    age: '',
                    email: '',
                    headImage: '',
                    department: {
                        id: ''
                    }

                },
                ruleValidate: {
                    username: [
                        {required: true, validator: nameplates, trigger: 'change'}/*异步验证*/
                    ],
                    password: [
                        {required: true, message: '密码不能为空', trigger: 'blur'},
                    ],
                    email: [
                        {required: true, message: '邮箱不能为空', trigger: 'blur'}
                    ],
                    age: [
                        {required: true, message: '年龄不能为空', trigger: 'blur'}
                    ],


                },
                formInline: {
                    username: '',
                    age1: '',
                    age2: ''
                },
                columns: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '序号',
                        type: 'index',
                        width: 100,
                        align: 'center',
                    },
                    {
                        title: '用户名',
                        key: 'username',
                        sortable: true
                    },
                    {
                        title: '密码',
                        key: 'password',
                    },
                    {
                        title: '年龄',
                        key: 'age',
                        width: 150,
                        align: "center",
                        resizable: true,
                        sortable: true,
                        filters: [
                            {
                                label: '大于20',
                                value: 1
                            },
                            {
                                label: '小于50',
                                value: 2
                            }
                        ],
                        filterMultiple: false,
                        filterMethod(value, row) {
                            if (value === 1) {
                                return row.age > 20;
                            } else if (value === 2) {
                                return row.age < 100;
                            }
                        }
                    },
                    {
                        title: '邮箱',
                        key: 'email',
                        resizable: true,
                        sortable: true
                    },
                    {
                        title: '部门',
                        slot: 'department_show',
                        key: 'department',
                        width: 150,
                        align: "center",
                        sortable: true
                    },

                    {
                        title: '头像',
                        key: 'headImage',
                        /* slot: 'icon_show',*/
                        align: 'center',
                    },

                ],
                EmployeeData: [],
                departmentAll: [],/*部门列表信息*/
                total: 0,
                page: 1,/*当前页默认为1*/
                pageSize: 5,/* 默认5条*/
            }
        },
        created() {
            this.getFirstMenuData(this.page, this.pageSize);
        },
        methods: {

            getDepatmentName(row) {/*回显列表的部门*/
                if (row.department) {
                    return row.department.name
                } else {
                    return "暂无部门"
                }
            },

            getAllDepartment() {/*获取所有部门*/
                var $page = this;
                $.ajax({
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    url: "Admin/Employee/department/findAll",
                    dataType: 'json',
                    async: false,/*取消异步加载*/
                    success: function (result) {
                        $page.departmentAll = result;
                    }
                });
            },
            getRepetitionName(value) {
                let data;
                $.ajax({
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    url: "Admin/Menu/findByName",
                    data: {"name": value},
                    dataType: 'json',
                    async: false,/*取消异步加载*/
                    success: function (result) {
                        data = result;/*只有前端返回的有值，才会执行这一句话*/
                    }
                });
                return data;
            },
            updateModelShow(data) {
                this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
                this.updateModel = true;
                this.getAllDepartment();
                if (!data.department) {/*如果没有这个属性就添加一个*/
                    data["department"] = {id: ''};
                }
                if (data.age) {/*这转换的是因为，age是long类型，但是表单检验是string类型的*/
                    data.age = data.age.toString();
                }
                this.formValidate = data;
            },
            handleSubmitUpdate: function (name) {//提交方法
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        var $page = this;
                        var param = $.extend({}, this.formValidate)/*复制一份，应为要删除*/
                        if (this.formValidate.department.id) {/*选择部门才传递*/
                            param["department.id"] = this.formValidate.department.id;/*当选择这个部门时才添加这个属性*/
                        }
                        if (this.formValidate.id) {/*修改*/
                            url = "Admin/Employee/update"
                            param.action = "update"/*传递这个参数是配合 @ModelAttribute注解使用的，只用于修改*/
                        } else {/*添加*/
                            var url = "Admin/Employee/save";
                            param.action = "save";
                        }
                        delete param["department"]/*这里要department.id不能有department*/
                        $.ajax({
                            type: "POST",
                            contentType: "application/x-www-form-urlencoded",
                            url: url,
                            data: param,
                            dataType: 'json',
                            async: false,/*取消异步加载*/
                            traditional: true,//防止深度序列化
                            success: function (result) {
                                $page.updateModel = false;
                                $page.$Message.success('操作数据成功');
                                $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                                delete param["department.id"]/*必须清空*/
                            }
                        });
                    } else {
                        this.$Message.error('请按照表单要求填写');
                    }
                })
            },

            handleReset: function (name) {//重置方法
                this.$refs[name].resetFields();
            },

            handleSubmit() {
                this.getFirstMenuData(this.page, this.pageSize)
            },
            changePage(page) {
                this.page = page/*改变就设置值*/
                this.getFirstMenuData(page, this.pageSize);
            },
            sizeChange(pageSize) {/*改变就设置值*/
                this.pageSize = pageSize
                this.getFirstMenuData(this.page, pageSize);/*改变后page默认会变成1*/
            },

            getFirstMenuData(page, pageSize) {
                var $page = this;
                $.ajax({
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    url: "Admin/Employee/findAll",
                    data: {
                        "username": this.formInline.username,
                        "age1": this.formInline.age1,
                        "age2": this.formInline.age2,
                        "currentPage": page,
                        "pageSize": pageSize
                    },
                    dataType: 'json',
                    traditional: true,//防止深度序列化
                    async: false,/*取消异步加载*/
                    success: function (result) {/*用了框架的*/
                        $page.EmployeeData = result.content;
                        $page.total = result.totalElements;
                        $page.page = result.number + 1/*处理一个小bug*/
                    }
                });
            },

            newAdd: function () {
                this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
                this.updateModel = true;
                this.getAllDepartment();/*获取所有部门*/
            },
            deleteRows: function (selection) {
                this.rows = [];
                for (let i = 0; i < selection.length; i++) {
                    this.rows.push(selection[i].id)
                }
            },

            deleteEmployee() {
                var $page = this;
                $.ajax({
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    url: "Admin/Employee/delete",
                    data: {"ids": this.rows.toString()},
                    dataType: 'json',
                    traditional: true,//防止深度序列化
                    async: false,/*取消异步加载*/
                    success: function (result) {/*用了框架的*/
                        $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                        $page.$Notice.success({
                            title: '通知提醒',
                            desc: "删除成功",
                        });
                        $page.rows = [];
                    }
                });
            }
        }

    });
</script>
