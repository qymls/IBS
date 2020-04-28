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
    #app {
        height: calc(100% - 30px);
    }

    /*   .ivu-icon { !*图标不对齐的情况*!
           line-height: unset;
       }*/

    .ivu-icon-md-add {
        cursor: pointer;
    }

    .max_model_icon .ivu-modal-body {
        max-height: 500px;
        overflow: auto;
    }

    .icon_style {
        float: left;
        margin: 6px 6px 6px 0;
        width: 90px;
        text-align: center;
        list-style: none;
        height: 90px;
        color: #5c6b77;
        transition: all .2s ease;
        position: relative;
        padding-top: 10px;
    }

    .page_class .ivu-icon {
        line-height: unset;
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
                <i-button type="primary" icon="md-add" @click="newMenu">菜单管理</i-button>
            </i-col>
            <i-col span="21">
                <i-Form ref="formInline" :model="formInline" inline>
                    <Form-Item prop="name">
                        <i-Input type="text" v-model="formInline.name" placeholder="请输入查找的菜单名称">
                            <Icon type="ios-menu" slot="prepend"></Icon>
                        </i-Input>
                    </Form-Item>
                    <Form-Item prop="time">
                        <Date-Picker type="datetimerange" v-model="formInline.time" format="yyyy-MM-dd HH:mm"
                                     placeholder="请选择查询的时间段" transfer :editable="false" style="width: 300px"
                                     @on-change="getTime"></Date-Picker>
                    </Form-Item>

                    <Form-Item>
                        <i-Button type="info" icon="ios-search" @click="handleSubmit('formInline')">查找</i-Button>
                    </Form-Item>
                </i-Form>
            </i-col>

        </Row>

        <Row justify="center" align="middle">
            <div style="margin-top:20px">
                <i-Table row-key="name" :columns="columns" :data="menuData" border max-height="650"
                         @on-row-dblclick="updateModelShow">
                    <template slot-scope="{ row, index }" slot="icon_show">
                        <Icon :type="row.icon"></Icon>
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
            <Modal title="修改菜单" v-model="updateModel" class-name="vertical-center-modal" footer-hide>
                <i-Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                    <input type="hidden" v-model="formValidate.id"/><%--菜单id--%>
                    <Form-Item label="菜单名称" prop="name">
                        <i-Input v-model="formValidate.name" placeholder="请输入菜单名" @on-blur="getEnglishName"></i-Input>
                    </Form-Item>
                    <Form-Item label="菜单图标" prop="icon">
                        <template v-if="formValidate.icon !=''">
                            <i-Input v-model="formValidate.icon" :prefix="formValidate.icon" icon="md-add"
                                     placeholder="请输入菜单图标"
                                     @on-click="showIcon"/>
                        </template>
                        <template v-else>
                            <i-Input v-model="formValidate.icon" icon="md-add" placeholder="请输入菜单图标"
                                     @on-click="showIcon"/>
                        </template>
                    </Form-Item>
                    <Form-Item label="英文名称" prop="englishName">
                        <i-Input v-model="formValidate.englishName" placeholder="请输入英文名称"></i-Input>
                    </Form-Item>
                    <Form-Item label="菜单地址" prop="url">
                        <i-Input v-model="formValidate.url" placeholder="请输入菜单地址"></i-Input>
                    </Form-Item>
                    <Form-Item label="菜单描述" prop="description">
                        <i-Input v-model="formValidate.description" type="textarea" :autosize="{minRows: 2,maxRows: 20}"
                                 placeholder="一些简单描述"></i-Input>
                    </Form-Item>
                    <Form-Item>
                        <i-Button type="primary" @click="handleSubmitUpdate('formValidate')">确认</i-Button>
                        <i-Button @click="handleReset('formValidate')" style="margin-left: 8px">重置</i-Button>
                    </Form-Item>

                </i-Form>
            </Modal>
            <Modal v-model="iconModal" footer-hide class-name="max_model_icon">
                <div slot="header" style="text-align: center;">
                    <i-input type="text" v-model="searchInput" placeholder="输入英文关键词搜索，比如 add" style="width: 260px;"
                             @on-change="search_icon"></i-input>
                </div>
                <template v-if="allIconData.length !=0">
                    <a href="javascript:void(0)" class="icon_style" v-for="item in allIconData"
                       @click="chooseIcon(item)">
                        <Icon :type="item" size="25"></Icon>
                        <p>{{item}}</p>
                    </a>
                </template>
                <template v-else>
                    <div style="text-align: center">
                        <p>无符合要求的数据</p>
                    </div>
                </template>
            </Modal>
        </Row>


    </Card>


</div>
<script type="module">
    import iconData from './admin/public/icons.js';

    new Vue({
        el: "#app",
        components: {
            iconData,
        },
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
                title: "菜单详情",
                rows: [],
                updateModel: false,
                iconModal: false,
                allIconData: [],
                searchInput: '',/*图标选择器*/
                formValidate: {
                    id: '',
                    name: '',
                    icon: '',
                    englishName: '',
                    url: '',
                    description: '',
                    parent: '',
                },
                ruleValidate: {
                    name: [
                        {required: true, validator: nameplates, trigger: 'change'}/*异步验证*/
                    ],
                    icon: [
                        {required: true, message: '图标不能为空', trigger: 'blur'},
                    ],
                    englishName: [
                        {required: true, message: '英文名称不能为空', trigger: 'blur'}
                    ],


                },
                formInline: {
                    name: '',
                    time: ''
                },
                columns: [
                    {
                        title: '菜单名称',
                        key: 'name',
                        tree: true
                    },
                    {
                        title: '菜单英文名',
                        key: 'englishName',
                    },
                    {
                        title: '菜单地址',
                        key: 'url',
                        resizable: true,
                        width: 300,
                    },
                    {
                        title: '菜单图标',
                        key: 'icon',
                        slot: 'icon_show',
                        align: 'center',
                        width: 150,
                    },
                    {
                        title: '菜单描述',
                        key: 'description',
                        ellipsis: true,
                    },
                    {
                        title: '创建时间',
                        key: 'createTime',
                        width: 200
                    },
                    {
                        title: '创建人',
                        key: 'operator',
                        width: 100
                    },

                ],
                menuData: [],
                total: 0,
                page: 1,/*当前页默认为1*/
                pageSize: 5,/* 默认5条*/
                menuName: '',/*解决当前菜单不被当成重复菜单的标识*/
            }
        },
        created() {
            this.getFirstMenuData(this.page, this.pageSize);
        },
        methods: {
            showIcon() {/*弹出图标窗体*/
                this.searchInput = '';
                this.iconModal = true;
                this.allIconData = iconData;
            },
            chooseIcon(iconChoose) {/*选择图标*/
                this.formValidate.icon = iconChoose;
                this.iconModal = false;
            },
            search_icon() {/*搜索图标*/
                var searchResult = [];
                for (let i = 0; i < iconData.length; i++) {
                    if (iconData[i].indexOf(this.searchInput) != -1) {
                        searchResult.push(iconData[i])
                    }
                }
                this.allIconData = searchResult;
            },
            getEnglishName() {/*通过菜单名称，自动翻译成英文*/
                var $apge = this;
                if (this.formValidate.name != '') {/*不能为空*/
                    $.ajax({
                        type: "POST",
                        contentType: "application/x-www-form-urlencoded",
                        url: "Admin/Menu/getEnglishNameByBaiduApi",
                        data: {"name": this.formValidate.name},
                        dataType: 'json',
                        async: false,/*取消异步加载*/
                        success: function (result) {
                            if (JSON.parse(result).trans_result != null) {
                                let englishName = JSON.parse(result).trans_result[0].dst;
                                $apge.formValidate.englishName = englishName;
                                console.log("翻译成功")
                            } else {
                                console.log(result)
                            }

                        }
                    });
                } else {
                    console.log("我是空,百度翻译不执行")
                }
            },
            getRepetitionName(value) {/*验证菜单名称是否重复*/
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
                this.menuName = data.name;
                this.formValidate.id = data.id;
                this.formValidate.name = data.name;
                this.formValidate.icon = data.icon;
                this.formValidate.englishName = data.englishName;
                this.formValidate.url = data.url;
                this.formValidate.description = data.description;
                this.formValidate.parent = data.parent;

            },
            handleSubmitUpdate: function (name) {//提交方法
                var param = $.extend({}, this.formValidate)/*复制一份，应为要删除*/
                param['action'] = 'update';
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        var $page = this;
                        $.ajax({
                            type: "POST",
                            contentType: "application/x-www-form-urlencoded",
                            url: "Admin/Menu/update",
                            data: param,
                            dataType: 'json',
                            async: false,/*取消异步加载*/
                            success: function (result) {
                                if (result.msg) {/*操作失败，无权限*/
                                    $page.$Message.error(result.msg);
                                } else {
                                    $page.updateModel = false;
                                    $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                                    if (result.length > 0) {/*result返回的是修改菜单的父菜单*/
                                        $page.getDelInfo($page.menuData, result);/*刷新数据后，打开修改后的children，添加一个属性*/
                                    } else {
                                        console.log('一级菜单不用展开')
                                    }
                                    /* $page.$refs[name].resetFields();/!*清除model的表单数据*!/*/
                                    $page.$Message.success('修改数据成功');
                                }
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
            getTime(Date) {
                this.formInline.time = Date;
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
                    url: "Admin/Menu/findAllMenu",
                    data: {
                        "name": this.formInline.name,
                        "time": this.formInline.time,
                        "currentPage": page,
                        "pageSize": pageSize
                    },
                    dataType: 'json',
                    traditional: true,//防止深度序列化
                    async: false,/*取消异步加载*/
                    success: function (result) {
                        if (result.msg) {/*操作失败，无权限*/
                            this.$Notice.notice.error({
                                title: '通知提醒',
                                desc: result.msg,
                            });
                        } else {
                            $page.menuData = result.list;
                            $page.total = result.totalRows;
                            $page.page = result.currentPage/*处理一个小bug*/
                        }
                    }
                });
            },

            getDelInfo: function (data, parentNameList) {//递归菜单，用于找出当前修改的是哪个子菜单，并且把它打开
                for (let i = 0; i < data.length; i++) {
                    if (data[i].children && data[i].children.length > 0) {/*传过来的都是当前修改菜单的父菜单*/
                        for (let j = 0; j < parentNameList.length; j++) {
                            if (data[i].name == parentNameList[j]) {
                                data[i] = $.extend({}, data[i], {_showChildren: true});
                            }
                        }
                        this.getDelInfo(data[i].children, parentNameList)
                    }
                }

            },
            newMenu: function () {
                window.location.href = 'Admin/Menu/forwardMenuEdit';/*跳转edit.jsp*/
            },
            deleteMenu: function () {

            },
        },

    });
</script>
