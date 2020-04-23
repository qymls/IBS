new Vue({
    el: "#app",
    data: function () {
        return {
            title: "信息管理",
            rows: [],
            updateModel: false,
            formValidate: {
                id : '' ,
                name : '' ,
                color : '' ,
                pic : '' ,
                smallpic : '' ,
                costprice : '' ,
                saleprice : '' ,
                typesId : '' ,
                unitId : '' ,
                brandId : '' ,
            
            },
            ruleValidate: {
                name : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                color : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                pic : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                smallpic : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                costprice : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                saleprice : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                typesId : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                unitId : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                brandId : [
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
                    title: 'color',
                    key: 'color',
                },
                                {
                    title: 'pic',
                    key: 'pic',
                },
                                {
                    title: 'smallpic',
                    key: 'smallpic',
                },
                                {
                    title: 'costprice',
                    key: 'costprice',
                },
                                {
                    title: 'saleprice',
                    key: 'saleprice',
                },
                                {
                    title: 'typesId',
                    key: 'typesId',
                },
                                {
                    title: 'unitId',
                    key: 'unitId',
                },
                                {
                    title: 'brandId',
                    key: 'brandId',
                },
                            ],
            ProductData: [],
            total: 0,
            page: 1,/*当前页默认为1*/
            pageSize: 5,/* 默认5条*/
        }
    },
    created() {
        this.getFirstMenuData(this.page, this.pageSize);
    },
    methods: {
        updateModelShow(data) {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.updateModel = true;
            this.formValidate = data;
        },
        handleSubmitUpdate: function (name) {//提交方法
          this.$refs['formValidate'].validate'((valid)=>{
            if (valid) {
            var $page = this;
            var param = this.formValidate;
            var url;
            if (this.formValidate.id) {/*修改*/
                url = "Admin/Product/update"
                param.action = "update"/*传递这个参数是配合 @ModelAttribute注解使用的，只用于修改*/
                } else {/*添加*/
                var url = "Admin/Product/save";
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
                $page.updateModel = false;
                $page.$Message.success("操作数据成功");
                $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                    }
                });
            } else {
                this.$Message.error("请按照表单要求填写");
                }
            })
        },

        handleReset: function (name) {//重置方法
            this.$refs['formValidate'].resetFields();
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
                url: "Admin/Product/findAll",
                data: {
                    "name": this.formInline.name,
                    "currentPage": page,
                    "pageSize": pageSize
                },
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.ProductData = result.content;
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

        deleteProduct() {
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Product/delete",
                data: {"ids": this.rows.toString()},
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                    $page.$Notice.success'({
                     title: "通知提醒",
                     desc: "删除成功",
                        });
                    $page.rows = [];
                }
            });
        }
    }

});