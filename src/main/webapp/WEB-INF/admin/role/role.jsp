<%--
  Created by IntelliJ IDEA.
  User: qymls
  Date: 2020/4/5
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .ivu-modal-body {
        max-height: 500px;
        overflow: auto;

    }

    ::-webkit-scrollbar { /*不要滚动条*/
        width: 0;
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
                <i-button type="primary" icon="md-add" @click="addRole">添加角色</i-button>
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
                <i-Table :columns="columns" :data="data">
                    <template slot-scope="{ row, index }" slot="name">
                        <template v-if="editIndex === index">
                            <i-Input type="text" v-model="editName"/>
                        </template>
                        <template v-else>
                            <span>{{ row.name }}</span>
                        </template>
                    </template>

                    <template slot-scope="{ row, index }" slot="age">
                        <template v-if="editIndex === index">
                            <i-Input type="text" v-model="editAge"/>
                        </template>
                        <template v-else>
                            <span>{{ row.age }}</span>
                        </template>
                    </template>

                    <template slot-scope="{ row, index }" slot="birthday">
                        <template v-if="editIndex === index">
                            <i-Input type="text" v-model="editBirthday"/>
                        </template>
                        <template v-else>
                            <span>{{getBirthday(row.birthday)}}</span>
                        </template>
                    </template>

                    <template slot-scope="{ row, index }" slot="address">
                        <template v-if="editIndex === index">
                            <i-Input type="text" v-model="editAddress"/>
                        </template>
                        <template v-else>
                            <span>{{ row.address }}</span>
                        </template>
                    </template>

                    <template slot-scope="{ row, index }" slot="action">
                        <div v-if="editIndex === index">
                            <i-Button @click="handleSave(index)">保存</i-Button>
                            <i-Button @click="editIndex = -1">取消</i-Button>
                        </div>
                        <div v-else>
                            <i-Button @click="handleEdit(row, index)">操作</i-Button>
                        </div>
                    </template>
                </i-Table>
            </div>
            <div style="margin: 10px;overflow: hidden">
                <div style="float: right;">
                    <Page :total="10" show-total :page-size="5" :page-size-opts="[5,10,20]" :current="1"
                          show-sizer transfer show-elevator
                          class-name="page_class" style="margin-top: 10px;"></Page>
                </div>
            </div>
        </Row>


    </Card>
    <Modal v-model="authority" draggable :scrollable="true" width="700">
        <div slot="header">
            <h2>
                <Icon type="md-options"></Icon>
                菜单权限控制
            </h2>
        </div>
        <Row>
            <i-col span="12">
                <div>
                    <Tree :data="authorityTree" transfer show-checkbox ref="tree"
                          @on-check-change="getAuthority" :render="renderContent"></Tree>
                </div>
            </i-col>
            <Divider type="vertical" style="height: auto;width: 2px"/>
            <i-col span="12">
                <div>
                    <Tree :data="authorityTreeShow" :render="renderContent"></Tree>
                </div>
            </i-col>

        </Row>

        <div slot="footer">
            <i-button type="primary" @click="saveChang">确认保存</i-button>
        </div>

    </Modal>
</div>
<script>
    new Vue({
        el: '#app',
        data() {
            return {
                title: '角色管理',
                formInline: {
                    name: '',
                    time: ''
                },
                authority: false,
                /*权限树*/
                authorityTree: [],
                authorityTreeShow: [],
                menuIds: [],
                columns: [
                    {
                        title: '姓名',
                        slot: 'name'
                    },
                    {
                        title: '年龄',
                        slot: 'age'
                    },
                    {
                        title: '出生日期',
                        slot: 'birthday'
                    },
                    {
                        title: '地址',
                        slot: 'address'
                    },
                    {
                        title: '操作',
                        slot: 'action'
                    }
                ],
                data: [
                    {
                        name: '王小明',
                        age: 18,
                        birthday: '919526400000',
                        address: '北京市朝阳区芍药居'
                    },
                    {
                        name: '张小刚',
                        age: 25,
                        birthday: '696096000000',
                        address: '北京市海淀区西二旗'
                    },
                    {
                        name: '李小红',
                        age: 30,
                        birthday: '563472000000',
                        address: '上海市浦东新区世纪大道'
                    },
                    {
                        name: '周小伟',
                        age: 26,
                        birthday: '687024000000',
                        address: '深圳市南山区深南大道'
                    },
                    {
                        name: '娃哈哈',
                        age: 26,
                        birthday: '687024000000',
                        address: '深圳市南山区深南大道'
                    }

                ],
                editIndex: -1,  // 当前聚焦的输入框的行数
                editName: '',  // 第一列输入框，当然聚焦的输入框的输入内容，与 data 分离避免重构的闪烁
                editAge: '',  // 第二列输入框
                editBirthday: '',  // 第三列输入框
                editAddress: '',  // 第四列输入框
            }

        },
        mounted() {
            var roleauthority = [];
            $.ajax({/*获取有权限的菜单*/
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Menu/findOne",
                dataType: 'json',
                async: false,/*取消异步加载*/
                success: function (result) {
                    roleauthority = result
                }
            });
            this.getAuthorityDateInfo(this.authorityTree, roleauthority);/*默认勾选已有权限的菜单*/
            var firstCopyArr = $.extend(true, [], roleauthority);//数组的深度复制,不影响原数组
            for (let i = 0; i < firstCopyArr.length; i++) {/*为每个添加title属性并且默认展开*/
                firstCopyArr[i] = $.extend({}, firstCopyArr[i], {title: firstCopyArr[i].name, expand: true});
            }
            var newMenuList = this.getNewChildren(firstCopyArr);
            this.authorityTreeShow = newMenuList

        },
        created() {
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Menu/findAll",
                dataType: 'json',
                async: false,/*取消异步加载*/
                success: function (result) {
                    $page.getlangData(result)
                    $page.authorityTree = result;

                }
            });
        },
        methods: {
            addRole() {
                this.authority = true;
            },
            handleEdit(row, index) {
                this.editName = row.name;
                this.editAge = row.age;
                this.editAddress = row.address;
                this.editBirthday = row.birthday;
                this.editIndex = index;
            },
            handleSave(index) {
                this.data[index].name = this.editName;
                this.data[index].age = this.editAge;
                this.data[index].birthday = this.editBirthday;
                this.data[index].address = this.editAddress;
                this.editIndex = -1;
            },
            getBirthday(birthday) {
                console.log(birthday)
                const date = new Date(parseInt(birthday));
                const year = date.getFullYear();
                const month = date.getMonth() + 1;
                const day = date.getDate();
                return year + '-' + month + '-' + day;
            },
            getTime(Date) {
                this.formInline.time = Date;
            },
            handleSubmit() {
                console.log(this.formInline)
            },
            renderContent(h, {root, node, data}) {/*自定义显示tree的图标，render函数*/
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
                ]);
            },
            getAuthorityDateInfo: function (data, roleauthority) {//递归菜单,获取已经有权限的菜单并且打开，选中
                for (let i = 0; i < data.length; i++) {
                    if (data[i].children && data[i].children.length > 0) {/*传过来的都是当前修改菜单的父菜单*/
                        for (let k = 0; k < roleauthority.length; k++) {
                            if (roleauthority[k].id == data[i].id) {/*当最后一层的id等于原来的权限id，就选中*/
                                data[i].expand = true/*将有children的展开*/
                            }
                        }
                        this.getAuthorityDateInfo(data[i].children, roleauthority)
                    } else {
                        for (let j = 0; j < roleauthority.length; j++) {
                            if (roleauthority[j].id == data[i].id) {/*当最后一层的id等于原来的权限id，就选中*/
                                data[i] = $.extend({}, data[i], {checked: true});/*选中原来的权限*/
                            }
                        }
                    }
                }
            },
            saveChang() {/*保存权限修改*/
                console.log(this.menuIds)
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
            getAuthority() {
                let checkedNode = this.$refs.tree.getCheckedAndIndeterminateNodes()/*勾选复选框时触发，获取勾选和半勾选的状态的*/
                var copyArr = $.extend(true, [], checkedNode);//数组的深度复制,不影响原数组
                var newMenuList = this.getNewChildren(copyArr)
                this.authorityTreeShow = newMenuList;
            },
            getNewChildren(copyArr) {/*封装方法，前面也要用的*/
                var $page = this;
                $page.menuIds = []/*每次改变都重新获取值*/
                var firstMenu = [];
                var childrenList = [];
                $.each(copyArr, function (i, object) {
                    $page.menuIds.push(object.id)
                    if (object.parent == 0) {/*获取一级菜单和非一级菜单*/
                        firstMenu.push(object);
                    } else {
                        childrenList.push(object);
                    }
                });
                var newMenuList = [];
                $.each(firstMenu, function (i, object) {
                    object.children = $page.getNewMenu(object.id, childrenList);
                    newMenuList.push(object);
                });
                console.log(newMenuList)/*获得预览菜单数据*/
                return newMenuList;
            },

            getNewMenu(id, childrenList) {/*递归组成新的预览菜单*/
                var newList = [];
                for (let i = 0; i < childrenList.length; i++) {
                    if (id == childrenList[i].parent) {
                        childrenList[i].children = this.getNewMenu(childrenList[i].id, childrenList);
                        newList.push(childrenList[i]);
                    }
                }
                return newList;
            }
        },

    })
    ;
</script>
