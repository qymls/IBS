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
                descs: '',
                parentId: '',

            },
            ruleValidate: {
                name: [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                descs: [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                parentId: [
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
                    title: '名称',
                    key: 'name',
                    tree: true
                },
                {
                    title: '描述',
                    key: 'descs',
                },

            ],
            ProducttypeData: [],
            total: 0,
            page: 1,/*当前页默认为1*/
            pageSize: 5,/* 默认5条*/
        }
    },
    created() {
        this.getFirstMenuData(this.page, this.pageSize);
    },
    methods: {
        click_enter() {/*键盘事件,调用查找方法*/
            this.handleSubmit();
        },
        updateModelShow(data) {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.updateModel = true;
            this.formValidate = data;
        },
        handleSubmitUpdate: function (name) {//提交方法
            var refs = this.$refs;
            refs[name].validate((valid) => {
                if (valid) {
                    var $page = this;
                    var messagePage = this.$Message;
                    var param = $.extend({}, this.formValidate)/*复制一份，因为要删除*/
                    var url;
                    if (this.formValidate.id) {/*修改*/
                        url = "Admin/Producttype/update"
                        param.action = "update"/*传递这个参数是配合 @ModelAttribute注解使用的，只用于修改*/
                    } else {/*添加*/
                        var url = "Admin/Producttype/save";
                        param.action = "save";
                    }
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
                                $page.$Message.success('操作数据成功');
                                $page.updateModel = false;
                                $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                            }
                        }
                    });
                } else {
                    this.$Message.error("请按照表单要求填写");
                }
            })
        },

        handleReset: function (name) {//重置方法
            var ref = this.$refs;
            ref[name].resetFields();
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
                url: "Admin/Producttype/findAll",
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
                        $page.ProducttypeData = result.list;
                        $page.total = result.totalRows;
                        $page.page = result.currentPage/*处理一个小bug*/
                    }

                }
            });
        },

        newAdd: function () {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.updateModel = true;
        },
        deleteRows: function (selection) {
            this.rows = [];
            for (let i = 0; i < selection.length; i++) {
                this.rows.push(selection[i].id)
            }
        },

        deleteProducttype() {
            var $page = this;
            var notice = this.$Notice;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Producttype/delete",
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
        }
    }

});