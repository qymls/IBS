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
                    slot: 'icon_show',
                    align: 'center',
                    width: 100
                },

            ],
            EmployeeData: [],
            departmentAll: [],/*部门列表信息*/
            total: 0,
            page: 1,/*当前页默认为1*/
            pageSize: 5,/* 默认5条*/
            visible: false,/*预览图片*/
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
                            //delete param["department.id"]/*必须清空*/
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
        },
        /*图片上传的相关方法*/
        upload_success(response, file, fileList) {
            this.formValidate.headImage = response;
        },
        handleProgress(event, file, fileList) {/*没有调试好，无法使用*/
            // 调用监听 上传进度 的事件
            event.target.onprogress = function (event) {
                console.log('上传中'); // 继承了原生函数的 event 事件
                let uploadPercent = parseFloat(((event.loaded / event.total) * 100).toFixed(2))	// 保留两位小数，具体根据自己需求做更改
                console.log('上传中', event); // 继承了原生函数的 event 事件
                // 手动设置显示上传进度条 以及上传百分比
                file.showProgress = true
                file.percentage = uploadPercent
            }
        },
        handleView(name) {
            this.visible = true;
        },
        handleRemove(path) {
            var page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Employee/deleteImg",
                data: {"path": path},
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    page.formValidate.headImage = '';/*删除了重置headimg*/
                }
            });
        },
    }

});