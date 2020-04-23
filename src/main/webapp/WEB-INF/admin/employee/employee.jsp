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
    <script src="admin/employee/employee.js" type="module"></script>
</div>

