<%--
  Created by IntelliJ IDEA.
  User: qymls
  Date: 2020/3/28
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .demo-tree-render .ivu-tree-title {
        width: 100%;
    }

    #app { /*页面滑动*/
        height: calc(100% - 50px);
    }

    .ivu-icon-md-add {
        cursor: pointer
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

    /*cursor: pointer;带上a标签的属性*/
</style>
<div id="app">

    <Card>
        <p slot="title">
            <Icon type="ios-list-box-outline" size="20"></Icon>
            {{title}}
        </p>
        <Tooltip content="返回菜单管理界面" transfer slot="extra">
            <Icon type="md-return-left" style="font-size: 23px;margin-right: 20px;cursor: pointer;"
                  @click="returnMenu"/>
        </Tooltip>
        <Row justify="center" align="middle">
            <Tree :data="data5" :render="renderContent" class="demo-tree-render"></Tree>
        </Row>
    </Card>

    <Modal title="新增菜单" v-model="addModel" class-name="vertical-center-modal" footer-hide draggable>
        <i-Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
            <Form-Item label="父级菜单">
                <i-Input v-model="parentMenu" disabled></i-Input>
            </Form-Item>
            <Form-Item prop="parent" v-show="false">
                <i-input type="text" v-model="formValidate.parent"/>
                <%--父菜单的id--%>
            </Form-Item>
            <Form-Item label="菜单名称" prop="name">
                <i-Input v-model="formValidate.name" placeholder="请输入菜单名" @on-blur="getEnglishName"></i-Input>
            </Form-Item>
            <Form-Item label="菜单图标" prop="icon">
                <template v-if="formValidate.icon !=''">
                    <i-Input v-model="formValidate.icon" :prefix="formValidate.icon" icon="md-add" placeholder="请输入菜单图标"
                             @on-click="showIcon"/>
                </template>
                <template v-else>
                    <i-Input v-model="formValidate.icon" icon="md-add" placeholder="请输入菜单图标" @on-click="showIcon"/>
                </template>
            </Form-Item>
            <Form-Item label="英文名称" prop="englishName">
                <i-Input v-model="formValidate.englishName" placeholder="请输入英文名称"></i-Input>
            </Form-Item>
            <Form-Item label="页面地址" prop="url">
                <i-Input v-model="formValidate.url" placeholder="请输入页面地址"></i-Input>
            </Form-Item>
            <Form-Item label="菜单描述" prop="description">
                <i-Input v-model="formValidate.description" type="textarea" :autosize="{minRows: 2,maxRows: 20}"
                         placeholder="一些简单描述"></i-Input>
            </Form-Item>
            <Form-Item>
                <i-Button type="primary" @click="handleSubmit('formValidate')">确认</i-Button>
                <i-Button @click="handleReset('formValidate')" style="margin-left: 8px">重置</i-Button>
            </Form-Item>
        </i-Form>
    </Modal>

    <Modal v-model="delModel" width="360">
        <p slot="header" style="color:#f60;text-align:center">
            <Icon type="ios-information-circle"></Icon>
            <span>删除确认</span>
        </p>
        <div style="text-align:center">
            <p style="color: red;margin-top: 5px;">即将删除菜单</p>
            <p v-for="item in delArray">{{item.name}}</p>
            <p style="color: red;margin-top: 5px;">后无法恢复,请谨慎操作!</p>
        </div>
        <div slot="footer">
            <i-Button type="error" size="large" long @click="del">删除</i-Button>
        </div>
    </Modal>

    <Modal v-model="iconModal" footer-hide class-name="max_model_icon">
        <div slot="header" style="text-align: center;">
            <i-input type="text" v-model="searchInput" placeholder="输入英文关键词搜索，比如 add" style="width: 260px;"
                     @on-change="search_icon"></i-input>
        </div>
        <template v-if="allIconData.length !=0">
            <a href="javascript:void(0)" class="icon_style" v-for="item in allIconData" @click="chooseIcon(item)">
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
</div>
<script type="module">
    import iconData from './admin/public/icons.js';

    new Vue({
        el: "#app",
        components: {
            iconData,
        },
        data() {
            const nameplates = (rule, value, callback) => {/*异步验证菜单名称*/
                console.log("input改变了")
                if (value === '') {
                    callback(new Error('请输入菜单名称'));

                } else {
                    const result = this.getRepetitionName(value);
                    if (result != null) {
                        callback(new Error("该菜单已存在"));
                    } else {
                        callback();
                    }
                }
            };
            return {
                title: "菜单管理",
                addModel: false,
                delModel: false,
                iconModal: false,
                allIconData: [],
                searchInput: '',/*图标选择器*/
                formValidate: {
                    name: '',
                    icon: '',
                    url: '',
                    englishName: '',
                    description: '',
                    parent: '',
                },
                parentMenu: '无',
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
                tempAppendData: null,
                tempRemoveData: null,
                tempRemoveParent: null,//temp都是临时使用数组
                delArray: [],//汇总需要删除的菜单id
                data5: [
                    {
                        title: '菜单列表',
                        expand: true,
                        children: [],
                        id: 0,/*一级菜单默认为0*/
                        render: (h, {root, node, data}) => {
                            return h('span', {
                                style: {
                                    display: 'inline-block',
                                    width: '100%'
                                }
                            }, [
                                h('span', [
                                    h('Icon', {
                                        props: {
                                            type: 'ios-folder-outline'
                                        },
                                        style: {
                                            marginRight: '8px'
                                        }
                                    }),
                                    h('span', data.title)
                                ]),
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        float: 'right',
                                        marginRight: '59px'
                                    }
                                }, [
                                    h('Button', {
                                        props: Object.assign({}, this.buttonProps, {
                                            icon: 'ios-add',
                                            type: 'primary'
                                        }),
                                        style: {
                                            width: '89px'
                                        },
                                        on: {
                                            click: () => {
                                                this.append(data);
                                            }

                                        }
                                    })
                                ])
                            ]);
                        },

                    }
                ],
                buttonProps: {
                    type: 'default',
                    size: 'small',
                }
            }
        },

        created() {
            var Data = [];
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Menu/findAll",
                dataType: 'json',
                async: false,/*取消异步加载*/
                success: function (result) {
                    Data = result;
                }
            });
            this.getlangData(Data)
            this.data5[0].children = Data;
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
            returnMenu() {/*返回菜单界面*/
                window.location.href = 'Admin/Menu/forwardMenuList';/*返回菜单管理界面*/
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
                        data = result;
                    }
                });
                return data;
            },

            handleSubmit: function (name) {//提交方法
                var param = $.extend({}, this.formValidate)/*复制一份，应为要删除*/
                if (param.parent == 0) {/*一级菜单需要加到firstmenuid字段中*/
                    param["firstmenuid"] = param.parent
                } else {
                    param['parent.id'] = param.parent;
                }
                delete param['parent']
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        var $page = this;
                        var newMenu;
                        $.ajax({
                            type: "POST",
                            contentType: "application/x-www-form-urlencoded",
                            url: "Admin/Menu/save",
                            data: param,
                            dataType: 'json',
                            async: false,/*取消异步加载*/
                            success: function (result) {
                                if (result.msg) {/*操作失败，无权限*/
                                    $page.$Message.error(result.msg);
                                } else {
                                    newMenu = result;
                                    var data = $page.tempAppendData;
                                    var newMenuData = $.extend({}, result, {title: result.name});/*需要为展开状态*/
                                    const children = data.children || [];
                                    children.push(newMenuData);
                                    data.expand = true;/*将该菜单打开*/
                                    $page.$set(data, 'children', children);//是处理data没有children属性的情况的
                                    $page.addModel = false//关闭model
                                    /*$page.$refs['formValidate'].resetFields();// 清空值,打开model就清空*/
                                }
                            }
                        });
                        this.$Message.success('添加菜单' + newMenu.name + "成功");

                    } else {
                        this.$Message.error('请按照表单要求填写');
                    }
                })
            },

            del: function () {//删除从菜单方法
                this.delModel = false;
                var parent = this.tempRemoveParent;
                var data = this.tempRemoveData;
                /*下面就可以ajax去删除对应的菜单了*/
                var ids = [];
                var isMenu = false;/*是否有菜单管理*/
                for (let i = 0; i < this.delArray.length; i++) {
                    if (this.delArray[i].name != "菜单管理" && this.delArray[i].name != "首页") {
                        ids.push(this.delArray[i].id)
                    } else {
                        isMenu = true;
                    }
                }
                if (!isMenu) {/*判断是否有菜单管理，菜单管理不让删除*/
                    var $page = this;
                    $.ajax({
                        type: "POST",
                        contentType: "application/x-www-form-urlencoded",
                        url: "Admin/Menu/delete",
                        data: {"ids": ids.toString()},
                        dataType: 'json',
                        async: false,/*取消异步加载*/
                        success: function (result) {
                            if (result.msg) {/*操作失败，无权限*/
                                $page.$Notice.error({
                                    title: '通知提醒',
                                    desc: result.msg,
                                });
                            } else {
                                $page.$Notice.success({
                                    title: '通知提醒',
                                    desc: "删除成功",
                                });
                            }
                            const index = parent.children.indexOf(data);
                            parent.children.splice(index, 1);
                        }
                    });
                } else {
                    this.$Notice.error({
                        title: '通知提醒',
                        desc: "您删除的菜单，包括了菜单管理或首页核心菜单，删除失败",
                    });
                }

            },

            getDelInfo: function (data) {//递归删除菜单
                for (let i = 0; i < data.length; i++) {
                    if (data[i].children && data[i].children.length > 0) {
                        this.delArray.push(data[i])
                        this.getDelInfo(data[i].children)
                    } else {
                        this.delArray.push(data[i])
                    }
                }

            },

            handleReset: function (name) {//重置方法
                this.$refs[name].resetFields();
            },
            getlangData(data) {//特殊处理一下菜单数据，加入一些其他的属性
                for (let i = 0; i < data.length; i++) {
                    if (data[i].children != undefined && data[i].children.length > 0) {
                        data[i] = $.extend({}, data[i], {title: data[i].name, expand: false});
                        this.getlangData(data[i].children);
                    } else {
                        data[i] = $.extend({}, data[i], {title: data[i].name});
                    }
                }
            },
            renderContent(h, {root, node, data}) {
                return h('span', {
                    style: {
                        display: 'inline-block',
                        width: '100%'
                    }
                }, [
                    h('span', [
                        h('Icon', {
                            props: {
                                type: data.icon
                            },
                            style: {
                                marginRight: '8px'
                            }
                        }),
                        h('span', data.title)
                    ]),
                    h('span', {
                        style: {
                            display: 'inline-block',
                            float: 'right',
                            marginRight: '64px'
                        }
                    }, [h('Tooltip', {
                        props: {
                            content: "添加",
                            placement: "left",
                            transfer: true
                        }
                    }, [
                        h('Button', {
                            props: Object.assign({}, this.buttonProps, {
                                icon: 'ios-add'
                            }),
                            style: {
                                marginRight: '30px'
                            },
                            on: {
                                click: () => {
                                    this.append(data)
                                }
                            }
                        }),
                    ]),
                        h('Tooltip', {
                            props: {
                                content: "删除",
                                placement: "right",
                                transfer: true
                            }
                        }, [
                            h('Button', {
                                props: Object.assign({}, this.buttonProps, {
                                    icon: 'ios-remove'
                                }),

                                on: {
                                    click: () => {
                                        this.remove(root, node, data)
                                    }
                                }
                            })
                        ])
                    ])
                ]);
            },

            append(data) {
                this.$refs['formValidate'].resetFields();// 清空值,打开model就清空
                this.addModel = true;
                this.parentMenu = data.title;
                this.formValidate.parent = data.id;
                this.tempAppendData = data;
            },
            remove(root, node, data) {
                this.delArray = []
                this.delModel = true;
                const parentKey = root.find(el => el === node).parent;
                const parent = root.find(el => el.nodeKey === parentKey).node;
                if (data.children && data.children.length > 0) {
                    this.getDelInfo(data.children)
                } else {
                    console.log("只有一级，直接删除" + data.name)
                }
                this.delArray.push(data)//删除成功后，需要把删除列表清空
                this.tempRemoveParent = parent;
                this.tempRemoveData = data;
            }
        }

    });
</script>
