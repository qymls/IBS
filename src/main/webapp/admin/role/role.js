new Vue({
    el: "#app",
    data: function () {
        return {
            title: "信息管理",
            rows: [],
            updateModel: false,
            formValidate: {
                id: '',
                name: '',
                sn: '',
            },
            ruleValidate: {
                name: [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                sn: [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
            },
            formInline: {
                name: '',
            },
            columns: [
                {
                    type: 'expand',
                    width: 50,
                    render: (h, params) => {
                        var permissions = this.getPermissionName(params.row).split(";")
                        return h('div',
                            [
                                permissions.map((item, index) => {
                                    return h('Tag',
                                        {
                                            props: {color: "blue"},
                                            style: {cursor: "pointer"}
                                        }
                                        , item)
                                })
                            ]);
                    }
                },
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
                    title: 'name',
                    key: 'name',
                },
                {
                    title: 'sn',
                    key: 'sn',
                },
                {
                    title: '权限列表',
                    slot: "permission_list",
                    width: 200,
                    ellipsis: true
                },
                {
                    title: "操作",
                    slot: "action",
                    align: "center",
                    width: 100
                },
            ],
            RoleData: [],
            total: 0,
            page: 1,/*当前页默认为1*/
            pageSize: 5,/* 默认5条*/
            roleSetting: false,
            columnSource: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },
                {
                    type: 'index',
                    width: 60,
                    align: 'center'
                },
                {
                    title: '名称',
                    key: 'name',
                },
                {
                    title: '对应的资源',
                    key: 'url',
                },
                {
                    title: '对象的权限',
                    key: 'sn',
                },
            ],
            sourceData: [],
            columnTraget: [
                {
                    title: '名称',
                    key: 'name',
                },
                {
                    title: '对应的资源',
                    key: 'url',
                },
                {
                    title: '对象的权限',
                    key: 'sn',
                },
            ],
            TragetData: [],
            roleSettingTotal: 0,/*权限选择时定的分页*/
            roleSettingPage: 1,
            roleSettingPageSize: 10,

        }
    },
    created() {
        this.getFirstMenuData(this.page, this.pageSize);
    },
    methods: {
        getPermissionName(row) {/*基础列表回显权限*/
            var roleMsg = '';
            if (row.permissionList.length > 0) {
                $.each(row.permissionList, function (i, o) {
                    if (i == row.permissionList.length - 1) {
                        roleMsg += o.name
                    } else {
                        roleMsg += o.name + ';'
                    }
                })
                return roleMsg;
            }
            return "暂无权限"
        },
        changeSourceDataSelect(selection, row) {/*勾选多选框触发*/
            this.changSetting(row);
        },
        cancelSourceDataSelect(selection, row) {/*取消勾选，取消勾选后，目标的权限也应该删除*/
            for (let i = 0; i < this.TragetData.length; i++) {
                if (this.TragetData[i].id == row.id) {
                    this.TragetData.splice(i, 1);/*!*移除*!*/

                }
            }

        },
        allSourceDataSelect(selection) {/*设置全选*/
            loop:for (let i = 0; i < selection.length; i++) {
                for (let j = 0; j < this.TragetData.length; j++) {
                    if (selection[i].id == this.TragetData[j].id) {/*重复了，不添加*/
                        continue loop;/*跳出到loop的地方去*/
                    }
                }
                this.changSetting(selection[i]);/*未重复的新添加过去*/
            }
        },
        allCancelSourceDataSelect(selection) {/*取消全选,只有设置了全选才能取消全选*/
            for (let i = 0; i < this.sourceData.length; i++) {
                for (let j = 0; j < this.TragetData.length; j++) {
                    if (this.sourceData[i].id == this.TragetData[j].id) {/*重复了，不添加*/
                        this.TragetData.splice(j, 1);/*!*移除*!*/
                    }
                }
            }
        },
        roleSettingChangePage(roleSettingPage) {/*权限翻页*/
            this.roleSettingPage = roleSettingPage/*改变就设置值*/
            this.getAllPermission(roleSettingPage, this.roleSettingPageSize);
        },
        roleSettingSizeChange(roleSettingPageSize) {/*权限的pageSize*/
            this.roleSettingPageSize = roleSettingPageSize
            this.getAllPermission(this.roleSettingPage, roleSettingPageSize);/*改变后page默认会变成1*/
        },
        changSetting(sourceData) {/*双击移动数据*/
            for (let i = 0; i < this.TragetData.length; i++) {
                if (this.TragetData[i].id == sourceData.id) {
                    this.$Message['error']({
                        background: true,
                        content: '不能重复添加 [' + sourceData.name + '] 权限'
                    });
                    return
                }
            }
            this.TragetData.push(sourceData)/*添加到目标中,*/
            this.changeStyleAdd(sourceData)
            this.scrollToBottom();/*添加数据滚动条到底部*/
            this.getAllPermission(this.roleSettingPage, this.roleSettingPageSize);/*再次查询，同步权限勾选框*/
        },
        changeStyleAdd(sourceData) {/*给添加的数据添加一个样式*/
            for (let i = 0; i < this.TragetData.length; i++) {
                if (sourceData.id == this.TragetData[i].id) {
                    this.TragetData[i] = $.extend({}, this.TragetData[i], {
                        cellClassName: {
                            name: "demo-table-info-cell-style",
                            url: "demo-table-info-cell-style",
                            sn: "demo-table-info-cell-style"
                        }
                    });
                }
            }
        },
        changSettingReturn(tragetData, index) {/*双击又返回去*/
            this.TragetData.splice(index, 1);
            this.getAllPermission(this.roleSettingPage, this.roleSettingPageSize);/*再次查询，同步权限勾选框*/
        },
        getAllPermission(roleSettingPage, roleSettingPageSize) {/*获取所有权限*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Role/permission/findPageByQuery",
                data: {
                    "currentPage": roleSettingPage,
                    "pageSize": roleSettingPageSize
                },
                dataType: "json",
                async: false,/*取消异步加载*/
                traditional: true,//防止深度序列化
                success: function (result) {
                    var dataSource = [];
                    for (let i = 0; i < result.content.length; i++) {
                        result.content[i]._checked = false;
                        dataSource.push(result.content[i])
                    }
                    $page.sourceData = dataSource;
                    $page.roleSettingTotal = result.totalElements;
                    $page.roleSettingPage = result.number + 1/*处理一个小bug*/
                    $page.checkBooks();/*勾选上有权限的列表*/
                }
            });
        },
        click_enter() {/*键盘事件,调用查找方法*/
            this.handleSubmit();
        },
        checkBooks() {/*对比源资源框，和以拥有框对比，有的话就勾选上*/
            for (let i = 0; i < this.sourceData.length; i++) {
                for (let j = 0; j < this.TragetData.length; j++) {
                    if (this.sourceData[i].id == this.TragetData[j].id) {
                        //this.$refs.selection.objData[i]._isChecked = true;/*只能用这个方式，给数据直接加无效,如果初始值没有该属性的话*/
                        this.sourceData[i]._checked = true;
                    }
                }
            }

        },
        updateModelShow(data) {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.TragetData = [];/*清空目标权限*/
            this.updateModel = true;
            this.formValidate = data;
            this.TragetData = data.permissionList
            this.roleSettingPage = 1;/*默认的第一页*/
            this.getAllPermission(this.roleSettingPage, this.roleSettingPageSize);/*源权限的数据加载了在查询，为了勾选上权限框*/
        },
        close_modal() {/*modal框关闭触发*/
            this.$refs.selection.selectAll(false);/*全部设置成未选中的状态*/
            this.updateModel = false;
        },
        handleSubmitUpdate: function (name) {//提交方法
            var refs = this.$refs;
            refs['formValidate'].validate((valid) => {
                if (valid) {
                    var $page = this;
                    var messagePage = this.$Message;
                    var param = $.extend({}, this.formValidate)/*复制一份，应为要删除*/
                    var url;
                    for (let i = 0; i < this.TragetData.length; i++) {
                        param["permissionList[" + i + "].id"] = this.TragetData[i].id
                    }
                    if (this.formValidate.id) {/*修改*/
                        url = "Admin/Role/update"
                        param.action = "update"/*传递这个参数是配合 @ModelAttribute注解使用的，只用于修改*/
                    } else {/*添加*/
                        var url = "Admin/Role/save";
                        param.action = "save";
                    }
                    delete param["permissionList"]
                    $.ajax({
                        type: "POST",
                        contentType: "application/x-www-form-urlencoded",
                        url: url,
                        data: param,
                        dataType: "json",
                        async: false,/*取消异步加载*/
                        traditional: true,//防止深度序列化
                        success: function (result) {
                            if (result.msg) {/*操作失败，无权限*/
                                messagePage.error(result.msg);
                            } else {
                                $page.updateModel = false;
                                $page.$Message.success("操作数据成功");
                                $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                                $page.$refs.selection.selectAll(false);/*全部设置成未选中的状态*/
                            }
                        }
                    });
                } else {
                    this.$Message.error("请按照表单要求填写");
                }
            })
        },

        handleReset: function (name) {//重置方法
            this.$refs['formValidate'].resetFields();
            this.$refs.selection.selectAll(false);/*全部设置成未选中的状态*/
            this.TragetData = [];/*清空目标权限*/
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
            var notice = this.$Notice;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Role/findAll",
                data: {
                    "name": this.formInline.name,
                    "currentPage": page,
                    "pageSize": pageSize
                },
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    if (result.msg) {/*操作失败，无权限*/
                        notice.error({
                            title: '通知提醒',
                            desc: result.msg,
                        });
                    } else {
                        $page.RoleData = result.content;
                        $page.total = result.totalElements;
                        $page.page = result.number + 1/*处理一个小bug*/
                    }
                }
            });
        },

        newAdd: function () {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.TragetData = [];/*清空目标权限*/
            this.updateModel = true;
            this.roleSettingPage = 1;/*默认的第一页*/
            this.getAllPermission(this.roleSettingPage, this.roleSettingPageSize);/*查询所有权限*/
        },
        deleteRows: function (selection) {
            this.rows = [];
            for (let i = 0; i < selection.length; i++) {
                this.rows.push(selection[i].id)
            }
        },

        deleteRole() {
            var $page = this;
            var notice = this.$Notice;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Role/delete",
                data: {"ids": this.rows.toString()},
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    if (result.msg) {/*操作失败，无权限*/
                        notice.error({
                            title: '通知提醒',
                            desc: result.msg,
                        });
                    } else {
                        notice.success({
                            title: '通知提醒',
                            desc: "删除成功",
                        });
                        $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                        $page.rows = [];
                    }
                }
            });
        },
        scrollToBottom: function () {/*滚动条到底部的方法,出现滚动条之后下一次点击才会到底部*/
            this.$nextTick(() => {
                var container = $(".tragetTable .ivu-table-body");/*滚动条在谁身上就找谁*/
                container[0].scrollTop = container[0].scrollHeight
            })
        },
    }

});