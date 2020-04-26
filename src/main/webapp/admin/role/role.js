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
        this.getAllPermission(this.roleSettingPage, this.roleSettingPageSize);
    },
    methods: {
        roleSettingChangePage(roleSettingPage) {/*权限翻页*/
            this.roleSettingPage = roleSettingPage/*改变就设置值*/
            this.getAllPermission(roleSettingPage, this.roleSettingPageSize);
        },
        roleSettingSizeChange(roleSettingPageSize) {/*权限的pageSize*/
            this.roleSettingPageSize = roleSettingPageSize
            this.getAllPermission(this.roleSettingPage, roleSettingPageSize);/*改变后page默认会变成1*/
        },
        changSetting(sourceData, index) {/*双击移动数据*/
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
        },
        changSettingReturn(tragetData, index) {/*双击又返回去*/
            this.TragetData.splice(index, 1);
        },
        getAllPermission(roleSettingPage, roleSettingPageSize) {/*获取所有权限*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Permission/findAll",
                data: {
                    "currentPage": roleSettingPage,
                    "pageSize": roleSettingPageSize
                },
                dataType: "json",
                async: false,/*取消异步加载*/
                traditional: true,//防止深度序列化
                success: function (result) {
                    $page.sourceData = result.content;
                    $page.roleSettingTotal = result.totalElements;
                    $page.roleSettingPage = result.number + 1/*处理一个小bug*/
                }
            });
        },
        click_enter() {/*键盘事件,调用查找方法*/
            this.handleSubmit();
        },
        updateModelShow(data) {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.TragetData = [];/*清空目标权限*/
            this.updateModel = true;
            this.formValidate = data;
            this.TragetData = data.permissionList
        },
        handleSubmitUpdate: function (name) {//提交方法
            var refs = this.$refs;
            refs['formValidate'].validate((valid) => {
                if (valid) {
                    var $page = this;
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
                            $page.updateModel = false;
                            $page.$Message.success("操作数据成功");
                            $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                            for (let i = 0; i < $page.TragetData.length; i++) {
                                delete param["permissionList[" + i + "].id"]/*不删除的话，你执行update后，在新增，不选权限，会出现，添加上一次的数据权限的*/
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
                    $page.RoleData = result.content;
                    $page.total = result.totalElements;
                    $page.page = result.number + 1/*处理一个小bug*/
                }
            });
        },

        newAdd: function () {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.TragetData = [];/*清空目标权限*/
            this.updateModel = true;
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
                    $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                    notice.success({
                        title: "通知提醒",
                        desc: "删除成功",
                    });
                    $page.rows = [];
                }
            });
        }
    }

});